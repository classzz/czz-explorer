package io.czz.explorer.syn;

import com.google.common.collect.ComparisonChain;
import com.google.inject.Inject;
import io.czz.explorer.SynServerConfig;
import io.czz.explorer.chain.CzzChainService;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.exception.ServiceException;
import io.czz.explorer.model.Tables;
import io.czz.explorer.model.tables.pojos.Block;
import io.czz.explorer.model.tables.pojos.SyncNode;
import io.czz.explorer.model.tables.pojos.Transaction;
import io.czz.explorer.model.tables.records.BlockRecord;
import io.czz.explorer.model.tables.records.TransactionRecord;
import io.czz.explorer.service.BlockService;
import io.czz.explorer.service.TransactionService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static io.czz.explorer.model.Tables.*;
import static io.czz.explorer.model.Tables.ORPHAN_BLOCK;
import static io.czz.explorer.model.tables.Block.BLOCK;
import static io.czz.explorer.model.tables.SyncNode.SYNC_NODE;
import static io.czz.explorer.model.tables.TransferUtxo.TRANSFER_UTXO;

public class SynNodeBlock {

    private DSLContext dslContext;

    private final long FIX_SYNC_BLOCK_POOL_SIZE = 100;

    private BlockService blockService;

    private SynServerConfig config;

    private CzzChainService czzChainService;

    private TransactionService txService;

    private static final Logger logger = LoggerFactory.getLogger(SynBlock.class);

    @Inject
    public SynNodeBlock(DSLContext dslContext,SynServerConfig config,BlockService blockService, TransactionService txService, CzzChainService czzChainService) {
        this.dslContext = dslContext;
        this.blockService = blockService;
        this.txService = txService;
        this.czzChainService = czzChainService;
        this.config = config;
    }

    /**
     * Decide which blocks will be assigned to this node
     * @param currentBlockNum
     */
    public void prepareFullNodeSync(long currentBlockNum) {

        SyncNode syncNode = this.dslContext.select(SYNC_NODE.fields()).from(SYNC_NODE).where(SYNC_NODE.NODE_ID.eq(config.getNodeId()))
                .fetchOneInto(SyncNode.class);

        if (syncNode.getStartFullDate()!=null && syncNode.getEndFullDate()==null) {
            logger.info("=> Previous fullnode sync didn't seem to went well ... resyncing the batch ...");
            Integer result = syncFullNodeNodeBlocks();
            if(result == 1 ){
                return;
            }
        }

        Long maxBlock = this.dslContext.select(DSL.max(SYNC_NODE.SYNC_END_FULL)).from(SYNC_NODE).fetchOneInto(Long.class);

        Long syncStart = 0L;
        Long syncStop = currentBlockNum;

        if (maxBlock==null) {
            maxBlock= 0L;
        }

        if (maxBlock== 0L) {
            syncStart = 0L;
            syncStop = (long)config.getSyncBatchSize();
        }else {
            syncStart = maxBlock;
            syncStop = maxBlock + config.getSyncBatchSize();
        }

        if (syncStop>currentBlockNum) {
            syncStop = currentBlockNum;
        }

        this.dslContext.update(SYNC_NODE)
                .set(SYNC_NODE.SYNC_START_FULL,syncStart)
                .set(SYNC_NODE.SYNC_END_FULL,syncStop)
                .where(SYNC_NODE.NODE_ID.eq(config.getNodeId()))
                .execute();


    }


    public Integer syncFullNodeNodeBlocks() {

        SyncNode syncNode = this.dslContext.select(SYNC_NODE.fields()).from(SYNC_NODE).where(SYNC_NODE.NODE_ID.eq(config.getNodeId()))
                .fetchOneInto(SyncNode.class);
        logger.info("==> Ready to sync full node block from {} to {} ...",syncNode.getSyncStartFull(),syncNode.getSyncEndFull());

        this.dslContext.update(SYNC_NODE)
                .set(SYNC_NODE.START_FULL_DATE, Timestamp.valueOf(LocalDateTime.now()))
                .set(SYNC_NODE.END_FULL_DATE,DSL.val((Timestamp)null))
                .where(SYNC_NODE.NODE_ID.eq(config.getNodeId()))
                .execute();

        Integer result = syncBlocks(syncNode.getSyncStartFull(), syncNode.getSyncEndFull());

        if(result == 0) {
            this.dslContext.update(SYNC_NODE)
                    .set(SYNC_NODE.END_FULL_DATE, Timestamp.valueOf(LocalDateTime.now()))
                    .where(SYNC_NODE.NODE_ID.eq(config.getNodeId()))
                    .execute();
            return 0;
        }
        return  1;
    }


    public Integer syncBlocks(long start,long stop){

        Long lastBlockNum = blockService.getlastNumber();

        logger.info("import block start {}", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        List<BlockDTO> blocks = blockService.getBlocks(start,stop);

        Collections.sort(blocks, (b1, b2)->{
            final int result = ComparisonChain.start().compare(b1.getHeight(), b2.getHeight()).result();
            return result;
        });

        Iterator<BlockDTO> it = blocks.iterator();

        while(it.hasNext()) {
            BlockDTO block = it.next();

            //检查上一块是否同步进去
            if(block.getHeight()>0) {
                BlockRecord lastRecord = this.dslContext.select(BLOCK.fields()).from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(block.getHeight() - 1))).fetchOneInto(BlockRecord.class);
                if (lastRecord == null) {
                    return 1;
                }
                if(lastRecord !=null){
                    List<TransactionRecord> transactionRecords = this.dslContext.select(TRANSACTION.fields()).from(TRANSACTION).where(TRANSACTION.BLOCK_HASH.equal(lastRecord.getHash())).fetchInto(TransactionRecord.class);
                    if(lastRecord.getTxCount().intValue() != transactionRecords.size()){
                        return 1;
                    }
                }
            }

            //孤块处理
            //1.从现在块往前遍历14个块,如果分叉则删除此块及后面的块
            if(lastBlockNum.intValue() - block.getHeight().intValue() <= 300) {
                List<Block> blocksFromDB = this.dslContext.select().from(BLOCK).orderBy(BLOCK.HEIGHT.desc()).limit(300).fetchInto(Block.class);
                List<Integer> orphanBlocks = new ArrayList<>();
                Iterator<Block> blockFromDBs = blocksFromDB.iterator();
                while (blockFromDBs.hasNext()) {
                    Block blockFromDB = blockFromDBs.next();
                    String hashFromChain = czzChainService.getBlockHash(blockFromDB.getHeight().intValue());
                    if (hashFromChain.equals(blockFromDB.getHash())) {
                        continue;
                    }

                    if (!hashFromChain.equals(blockFromDB.getHash())) {
                        orphanBlocks.add(blockFromDB.getHeight().intValue());
//                    deleteOrphanBlock(blockFromDB);
//                    blockFromDBs.remove();
                        //删除孤块以及交易
                        //更新同步开始块数
//                    deleteFork();
//                    return 1;
                    }

//                if(confirmNumber == 0 && correctBlockCount !=14){
//                    return 1;
//                }


                }
                //找到出问题最小的块,删除后边所有的块
                if (orphanBlocks.size() > 0) {
                    logger.info("==> orphan block was founded ..." );
                    Collections.sort(orphanBlocks, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2.compareTo(o1);
                        }
                    });
                    deleteOrphanBlock(orphanBlocks.get(0).intValue(), blocksFromDB.get(0).getHeight().intValue(), orphanBlocks);
                    return 1;
                }
            }
//            logger.info("import block start {}",DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
            logger.info("==> Syncing block: {}",block.getHeight());
            try {

                this.blockService.importCzzBlock(block);
            }catch(Exception e) {
                logger.error("Could not import block {}",block.getHeight(),e);
            }

//            logger.info("import block end {}",DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));

        }

        return 0;
    }

    public void syncFullBlocks() {

//        Long lastNodeBlockNum = blockService.getlastNumber();
        Long lastNodeBlockNum = 50l;
        logger.info("import block start {}", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        Long lastBlockNum = this.dslContext.select(DSL.max(BLOCK.HEIGHT)).from(BLOCK).where(BLOCK.IS_MAIN.equal(1)).fetchOneInto(Long.class);

        if (lastBlockNum==null) {
            lastBlockNum= -1L;
        }

        if (lastNodeBlockNum == lastBlockNum){
            BlockDTO block = blockService.getBlockByHeight(lastBlockNum);
            //检查上一块是否是主链
            String prevBlockHash = this.dslContext.select(BLOCK.HASH).from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(lastNodeBlockNum - 1))).and(BLOCK.IS_MAIN.equal(1)).fetchOneInto(String.class);
            if (prevBlockHash == null || !prevBlockHash.equals(block.getPreviousblockhash())) {
                removeOrphanBlock(block,lastBlockNum.intValue());
                return;
            }
        }

        // 处理每个区块
        for (long i = lastBlockNum +1 ; i <= lastNodeBlockNum ;i++) {

            BlockDTO block = blockService.getBlockByHeight(i);
            logger.info("==> Syncing getBlockByHeight: {}", block.getHeight());

            // 正常处理区块
            int errcode = addBlock(block);

            switch (errcode){
                case 0:
                    break;
                case 1:
                    removeOrphanBlock(block,lastBlockNum.intValue());
            }

        }
    }

    /**
     * 处理区块
     */
    public Integer addBlock(BlockDTO block){

        //检查上一块是否是主链
        if(block.getHeight()>0) {
            String prevBlockHash = this.dslContext.select(BLOCK.HASH).from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(block.getHeight() - 1))).and(BLOCK.IS_MAIN.equal(1)).fetchOneInto(String.class);
            if (prevBlockHash == null || !prevBlockHash.equals(block.getPreviousblockhash())) {
                return 1;
            }
        }

        logger.info("==> Syncing block: {}",block.getHeight());
        try {
            this.blockService.importCzzBlock(block);
        }catch(Exception e) {
            logger.error("Could not import block {}",block.getHeight(),e);
        }

        return 0;
    }

    public void removeOrphanBlock(BlockDTO block,int lastBlockNum){

        BlockDTO ancestorBlock = new BlockDTO();

        // 计算祖先块
        for (int i =block.getHeight();i >=0; i--){
            ancestorBlock = blockService.getBlockByHeight(Long.valueOf(i));
            String prevBlockHash = this.dslContext.select(BLOCK.HASH).from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(block.getHeight() - 1))).and(BLOCK.IS_MAIN.equal(1)).fetchOneInto(String.class);
            if (prevBlockHash == null || !prevBlockHash.equals(ancestorBlock.getPreviousblockhash())) {
                continue;
            }
        }

        for(Integer i = lastBlockNum; i >= ancestorBlock.getHeight(); i--){

            logger.info("==> deal with orphan block, now is" + i);
            Block orphanBlock = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(i))).fetchOneInto(Block.class);

            //此处应为链上的hash,不对,应该是错误的hash
            List<Transaction> transactions = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.BLOCK_HASH.eq(orphanBlock.getHash())).fetchInto(Transaction.class);
            for (Transaction transaction : transactions) {
                this.txService.removeTransaction(transaction);
                this.dslContext.deleteFrom(Tables.TRANSACTION).where(Tables.TRANSACTION.HASH.eq(transaction.getHash())).execute();
                this.dslContext.deleteFrom(Tables.TRANSFER_IN).where(Tables.TRANSFER_IN.TRANSACTION_ID.eq(transaction.getId())).execute();
                this.dslContext.deleteFrom(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.eq(transaction.getId())).execute();
                this.dslContext.deleteFrom(TRANSFER_UTXO).where(TRANSFER_UTXO.TX_HASH.eq(transaction.getHash())).execute();
                this.dslContext.deleteFrom(BLOCK).where(BLOCK.HASH.equal(orphanBlock.getHash())).execute();
            }
        }
    }

    public void deleteOrphanBlock(int start,int end,List<Integer> orphanBlocks){

        for(int i = start;i<=end;i++){
            logger.info("==> deal with orphan block, now is" + i);
            Block orphanBlock = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(i))).fetchOneInto(Block.class);
            this.dslContext.deleteFrom(BLOCK).where(BLOCK.HASH.eq(orphanBlock.getHash())).execute();

            //此处应为链上的hash,不对,应该是错误的hash
            List<Transaction> transactions = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.BLOCK_HASH.eq(orphanBlock.getHash())).fetchInto(Transaction.class);
            for (Transaction transaction : transactions) {
                this.dslContext.deleteFrom(TRANSACTION).where(TRANSACTION.HASH.eq(transaction.getHash())).execute();
                this.dslContext.deleteFrom(TRANSFER_IN).where(TRANSFER_IN.TRANSACTION_ID.eq(transaction.getId())).execute();
                this.dslContext.deleteFrom(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.eq(transaction.getId())).execute();
                this.dslContext.deleteFrom(TRANSFER_UTXO).where(TRANSFER_UTXO.TX_HASH.eq(transaction.getHash())).execute();
            }
            if(orphanBlocks.contains(i)) {
                this.dslContext.insertInto(ORPHAN_BLOCK)
                        .set(ORPHAN_BLOCK.HEIGHT, orphanBlock.getHeight())
                        .set(ORPHAN_BLOCK.HASH, orphanBlock.getHash())
                        .set(ORPHAN_BLOCK.MERKLE_ROOT, orphanBlock.getMerkleRoot())
                        .set(ORPHAN_BLOCK.MINER_ADDRESS, orphanBlock.getMinerAddress())
                        .set(ORPHAN_BLOCK.TX_COUNT, orphanBlock.getTxCount())
                        .set(ORPHAN_BLOCK.DIFFICULTY, orphanBlock.getDifficulty())
                        .set(ORPHAN_BLOCK.TRANSACTION_FEES, orphanBlock.getTransactionFees())
                        .set(ORPHAN_BLOCK.SIZE, orphanBlock.getSize())
                        .set(ORPHAN_BLOCK.NONCE, orphanBlock.getNonce())
                        .set(ORPHAN_BLOCK.BITS, orphanBlock.getBits())
                        .set(ORPHAN_BLOCK.CONFIRMATIONS, orphanBlock.getConfirmations())
                        .set(ORPHAN_BLOCK.REWARD, orphanBlock.getReward())
                        .execute();
            }
        }

        this.dslContext.update(Tables.SYNC_NODE)
                .set(Tables.SYNC_NODE.SYNC_START_FULL,Long.valueOf(start))
                .where(Tables.SYNC_NODE.NODE_ID.eq(1))
                .execute();
    }

    public void syncNodeFull() throws ServiceException {

//        this.prepareFullNodeSync(currentBlockNum);
//        this.syncFullNodeNodeBlocks();
        syncFullBlocks();
    }

    public boolean isInitialSync() {

        Integer genesisBlockExists = this.dslContext.select(DSL.count()).from(BLOCK)
                .where(BLOCK.HEIGHT.eq(ULong.valueOf(1))).fetchOneInto(Integer.class);

        if (genesisBlockExists == 0) {
            return true;
        }

        return false;
    }

}
