package io.czz.explorer.service;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import io.czz.explorer.chain.CzzChainService;
import io.czz.explorer.constants.InbConstants;
import io.czz.explorer.dto.*;
import io.czz.explorer.dto.block.BlockChainDto;
import io.czz.explorer.dto.block.BlockCriteriaDTO;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.dto.block.BlockDiffDTO;
import io.czz.explorer.dto.node.NodeCriteriaDTO;
import io.czz.explorer.dto.transaction.TransactionDTO;
import io.czz.explorer.exception.ServiceException;
import io.czz.explorer.model.tables.pojos.Block;
import io.czz.explorer.model.tables.records.BlockRecord;
import io.czz.explorer.model.tables.records.TransactionRecord;
import io.czz.explorer.model.tables.records.TransferOutRecord;
import io.czz.explorer.utils.HttpUtil;
import io.czz.explorer.utils.Numeric;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.czz.explorer.model.Tables.*;
import static io.czz.explorer.model.tables.Block.BLOCK;
import static io.czz.explorer.model.tables.Transaction.TRANSACTION;


public class BlockService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private static final int TOTAL_COUNT_MAX_NUM = 20000;

    private DSLContext dslContext;
    private TransactionService txService;
    private CzzChainService czzChainService;

    @Inject
    public BlockService(DSLContext dslContext, TransactionService txService, CzzChainService czzChainService) {
        this.dslContext = dslContext;
        this.txService = txService;
        this.czzChainService = czzChainService;
    }




    /**
     * Import a new block into db
     *
     * @param
     * @throws ServiceException
     */
    public void importCzzBlock(BlockDTO block) throws ServiceException {

        List<String> transactionResults = block.getTx();

        String minerAddress = null;
        double reward = 0;


        BlockRecord blockRecord = this.dslContext.select().from(BLOCK).where(BLOCK.HASH.eq(block.getHash())).fetchOneInto(BlockRecord.class);
        if(blockRecord !=null) {
            Map<String,Object> map = new HashMap(2);
            if (!transactionResults.isEmpty()) {
                for (String transHash : transactionResults) {
                    TransactionRecord transRecord = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.eq(transHash)).fetchOneInto(TransactionRecord.class);
                    if (transRecord == null) {
                        TransactionDTO transactionDTO = czzChainService.getTransaction(transHash);
                        map = this.txService.saveTransaction(transactionDTO);
                        if(map.isEmpty()){
                            continue;
                        }
                        if(map.get("minerAddress")!= null){
                            minerAddress = map.get("minerAddress").toString();
                        }
                        if((double)map.get("reward") != 0){
                            reward = (double)map.get("reward");
                        }
                    }else if(transRecord != null && blockRecord.getMinerAddress()==null){
                        List<TransferOutRecord> transferOutRecords = this.dslContext.select().from(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.equal(transRecord.getId())).orderBy(TRANSFER_OUT.AMOUNT.desc()).fetchInto(TransferOutRecord.class);
                        minerAddress=transferOutRecords.get(0).getAddress();
                        this.dslContext.update(BLOCK)
                                .set(BLOCK.MINER_ADDRESS, minerAddress)
                                .where(BLOCK.HASH.eq(blockRecord.getHash()))
                                .execute();
                    }
                }
            }

            if(!map.isEmpty()) {
                this.dslContext.update(BLOCK)
                        .set(BLOCK.REWARD, reward)
                        .set(BLOCK.MINER_ADDRESS, minerAddress)
                        .where(BLOCK.HASH.eq(block.getHash()))
                        .execute();
            }
        }


        //新增区块

//        logger.info(" store block start: {} " ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        Timestamp timestamp = Timestamp.valueOf(DateFormatUtils.format(block.getTime().longValue()*1000,"yyyy-MM-dd HH:mm:ss"));

        BlockRecord record = new BlockRecord();
        record.setCreatedTime(timestamp);
        record.setHeight(ULong.valueOf(block.getHeight()));
        record.setHash(block.getHash());
        record.setPrevBlockHash(block.getPreviousblockhash());
        record.setNextBlockHash(block.getNextblockhash());
        record.setMerkleRoot(block.getMerkleRoot());
        record.setTxCount(block.getTx().size());
        record.setSize(block.getSize().longValue());
        record.setVersion(block.getVersion().longValue());
        record.setVersionHex(block.getVersionHex());
        record.setNonce(ULong.valueOf(block.getNonce()));
        record.setBits(ULong.valueOf(Numeric.decodeQuantity("0x"+block.getBits())));
        record.setConfirmations(block.getConfirmations());
        record.setDifficulty(block.getDifficulty().multiply(new BigDecimal("1048575")).doubleValue());
        //store block
        record.attach(this.dslContext.configuration());
        record.store();
//        logger.info(" store block end:{} " ,DateFormatUtils.format(System.curr0entTimeMillis(),"yyyy-MM-dd HH:mm:ss"));


        //新增交易记录
//        logger.info(" sync trans start: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap(2);
        if (!transactionResults.isEmpty()) {
            for (String transHash:transactionResults) {
                TransactionDTO transactionDTO = czzChainService.getTransaction(transHash);
                map = this.txService.saveTransaction(transactionDTO);
                if(map.isEmpty()){
                    continue;
                }
                if(map.get("minerAddress")!= null){
                    minerAddress = map.get("minerAddress").toString();
                }
                if((double)map.get("reward") != 0){
                    reward = (double)map.get("reward");
                }
            }
        }


        this.dslContext.update(BLOCK)
                .set(BLOCK.REWARD,reward)
                .set(BLOCK.MINER_ADDRESS,minerAddress)
                .where(BLOCK.HASH.eq(block.getHash()))
                .execute();

        //区块算力
//        if(block.getHeight()>1) {
//            BlockRecord preBlock = this.dslContext.select().from(BLOCK).where(BLOCK.HASH.eq(block.getPreviousblockhash())).fetchOneInto(BlockRecord.class);
//            if (preBlock.getHashRate() == null || preBlock.getHashRate() == 0) {
//                addBLockHash(preBlock);
//            }
//        }
//        addBLockHash(record);


    }


    public void addBLockHash(BlockRecord block){
//        Timestamp timestamp = Timestamp.valueOf(DateFormatUtils.format(block.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));

        double hashRate = 0d;
        BigDecimal diffi = new BigDecimal("0");
        Long blockHeight = block.getHeight().longValue();
        if(blockHeight!=null && blockHeight == 0){
            this.dslContext.update(BLOCK)
//                    .set(BLOCK.DIFFICULTY,block.getDifficulty().multiply(new BigDecimal("1048575")).setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue())
                    .set(BLOCK.SUM_DIFFICULTY,diffi.doubleValue())
                    .set(BLOCK.HASH_RATE,hashRate)
                    .where(BLOCK.HASH.eq(block.getHash()))
                    .execute();
        }


        if(blockHeight != null && blockHeight > 0) {
            Double preDiffi = this.dslContext.select(BLOCK.SUM_DIFFICULTY).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(blockHeight - 1))).fetchOneInto(Double.class);
//            if(preDiffi == null){
//                BlockRecord preBlock = this.dslContext.select().from(BLOCK).where(BLOCK.HASH.eq(block.getPrevBlockHash())).fetchOneInto(BlockRecord.class);
//                addBLockHash(preBlock);
//            }
            if(preDiffi!=null) {
                if (blockHeight < 120) {
                    diffi = new BigDecimal(preDiffi).add(new BigDecimal(block.getDifficulty()).multiply(new BigDecimal("1048575"))).setScale(5, BigDecimal.ROUND_HALF_UP);
                    Timestamp firstBlockCreateTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(0))).fetchOneInto(Timestamp.class);
//            Timestamp currentBlockCreateTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(blockHeight))).fetchOneInto(Timestamp.class);
                    long interval = block.getCreatedTime().getTime() - firstBlockCreateTime.getTime();
                    hashRate = diffi.divide(new BigDecimal(interval / 1000), 3, BigDecimal.ROUND_HALF_UP).doubleValue();
                }

                if (blockHeight >= 120) {
                    Block endBLock120 = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(blockHeight.intValue() - 120))).fetchOneInto(Block.class);
                    diffi = new BigDecimal(preDiffi).add(new BigDecimal(block.getDifficulty()).multiply(new BigDecimal("1048575"))).subtract(new BigDecimal(endBLock120.getDifficulty())).setScale(5, BigDecimal.ROUND_HALF_UP);

                    Timestamp firstBlockCreateTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(blockHeight.intValue() - 120))).fetchOneInto(Timestamp.class);
//            Timestamp currentBlockCreateTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(blockHeight))).fetchOneInto(Timestamp.class);
                    long interval = block.getCreatedTime().getTime() - firstBlockCreateTime.getTime();
                    hashRate = diffi.divide(new BigDecimal(interval / 1000), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
                this.dslContext.update(BLOCK)
                        .set(BLOCK.SUM_DIFFICULTY, diffi.doubleValue())
//                    .set(BLOCK.DIFFICULTY,block.getDifficulty().multiply(new BigDecimal("1048575")).setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue())
                        .set(BLOCK.HASH_RATE, hashRate)
                        .where(BLOCK.HASH.eq(block.getHash()))
                        .execute();
            }

        }
    }

    public List<BlockDTO> getBlocks(long start, long stop) {
        List<BlockDTO> blocks = new ArrayList<>();
//        (start == stop ? (int)stop+1:(int)stop);
//        start=89909;
//        stop=89909;
        for (int i = (int) start; i < stop ;i++) {
            String blockHash = czzChainService.getBlockHash(i);
            BlockDTO blockDTO = czzChainService.getBlock(blockHash);
            blocks.add(blockDTO);
        }
        if(start == stop)
        {
            BlockRecord blockRecord = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(start))).fetchOneInto(BlockRecord.class);
            if(blockRecord==null) {
                String blockHash = czzChainService.getBlockHash((int) start);
                BlockDTO blockDTO = czzChainService.getBlock(blockHash);
                this.dslContext.update(BLOCK).set(BLOCK.NEXT_BLOCK_HASH,blockDTO.getHash()).where(BLOCK.HASH.eq(blockDTO.getPreviousblockhash())).execute();
                blocks.add(blockDTO);
            }
        }
        return blocks;
    }

    public Long getlastNumber() {


        Long blockNumber = 0L;
        blockNumber = czzChainService.getBlockHeight().longValue();
        return blockNumber;
    }

    public BlockDTO getLastBlock() {

        return this.dslContext.select(BLOCK.HEIGHT, BLOCK.HASH, BLOCK.CREATED_TIME, BLOCK.TX_COUNT, BLOCK.SIZE, BLOCK.PREV_BLOCK_HASH, BLOCK.NEXT_BLOCK_HASH)
                .from(BLOCK).orderBy(BLOCK.HEIGHT.desc()).limit(1).fetchOneInto(BlockDTO.class);

    }

    public Integer getLastBlockHeight() {

        return czzChainService.getBlockHeight();

    }


    public BlockDTO getBlockByNum(BlockCriteriaDTO criteria) {
        ArrayList<Condition> conditions = new ArrayList<>();
        if(criteria.getHash() !=null) {
            conditions.add(BLOCK.HASH.eq(criteria.getHash()));
        }
        if(criteria.getHeight() !=null) {
            conditions.add(BLOCK.HEIGHT.eq(ULong.valueOf(criteria.getHeight())));
        }
        BlockDTO block = this.dslContext.select(BLOCK.ID, BLOCK.HEIGHT, BLOCK.HASH, BLOCK.PREV_BLOCK_HASH.as("previousblockhash"), BLOCK.NEXT_BLOCK_HASH.as("nextblockhash")
                , BLOCK.MERKLE_ROOT, BLOCK.VERSION, BLOCK.VERSION_HEX, BLOCK.DIFFICULTY, BLOCK.NONCE, BLOCK.BITS, BLOCK.SIZE, BLOCK.CONFIRMATIONS, BLOCK.CREATED_TIME
                , BLOCK.TX_COUNT, BLOCK.REWARD, BLOCK.MINER_ADDRESS, BLOCK.BITS,BLOCK.HASH_RATE)
                .from(BLOCK).where(conditions).fetchOneInto(BlockDTO.class);

//        BigDecimal reward = new BigDecimal(block.getReward() / Math.pow(10, 18));
//        block.setReward(reward.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        block.setTimestamp(block.getTimestamp().substring(0, block.getTimestamp().indexOf(".")));

        if(block==null){
            return new BlockDTO();
        }
        block.setBits(Numeric.encodeQuantity(BigInteger.valueOf(Long.parseLong(block.getBits()))));
        return block;
    }



    public ListModel<BlockDTO, BlockCriteriaDTO> listBlocks(BlockCriteriaDTO criteria) {

        ArrayList<Condition> conditions = new ArrayList<>();

        SelectJoinStep<?> listQuery = this.dslContext.select(BLOCK.ID, BLOCK.HEIGHT, BLOCK.HASH, BLOCK.PREV_BLOCK_HASH.as("previousblockhash")
                ,BLOCK.NEXT_BLOCK_HASH.as("nextblockhash"),BLOCK.MERKLE_ROOT,BLOCK.VERSION,BLOCK.VERSION_HEX,BLOCK.DIFFICULTY,BLOCK.NONCE
                ,BLOCK.BITS,BLOCK.SIZE, BLOCK.CONFIRMATIONS, BLOCK.CREATED_TIME, BLOCK.TX_COUNT, BLOCK.REWARD,BLOCK.MINER_ADDRESS,BLOCK.HASH_RATE)
                .from(BLOCK);

        long totalCount = this.dslContext.select(DSL.count())
                .from(BLOCK)
                .fetchOneInto(Long.class);

        List<BlockDTO> items = listQuery.where(conditions).orderBy(BLOCK.HEIGHT.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(BlockDTO.class);

//        for (BlockDTO item : items) {
//            Timestamp endBLockTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(item.getHeight()-119))).fetchOneInto(Timestamp.class);
//            long interval = item.getCreatedTime()-endBLockTime.getTime();
//            Double totalDiff = 0d;
//            for (int i = 0; i < 120; i++) {
//                totalDiff += this.dslContext.select(BLOCK.DIFFICULTY).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(item.getHeight()-i))).fetchOneInto(Double.class);
//            }
//            Double hashRate = new BigDecimal(totalDiff).divide(new BigDecimal(interval/1000),5,BigDecimal.ROUND_HALF_UP).doubleValue();
//            item.setHashRate(hashRate);
//
//        }
        ListModel<BlockDTO, BlockCriteriaDTO> result = new ListModel<BlockDTO, BlockCriteriaDTO>(criteria, items, TOTAL_COUNT_MAX_NUM);
//        for (BlockDTO blockDTO : result.getItems()) {
////            blockDTO.setTimestamp(blockDTO.getTimestamp().substring(0, blockDTO.getTimestamp().indexOf(".")));
//            BigDecimal reward = new BigDecimal(blockDTO.getReward() / Math.pow(10, 18));
//            blockDTO.setReward(reward.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
//        }

        return result;
    }


    public ListModel<BlockDiffDTO, BlockCriteriaDTO> listBlockDiff(BlockCriteriaDTO criteria) {

        ArrayList<Condition> conditions = new ArrayList<>();

        SelectJoinStep<?> listQuery = this.dslContext.select(BLOCK.HEIGHT,BLOCK.HASH,BLOCK.DIFFICULTY,BLOCK.HASH_RATE, BLOCK.CREATED_TIME)
                .from(BLOCK);

        long totalCount = this.dslContext.select(DSL.count())
                .from(BLOCK)
                .fetchOneInto(Long.class);

        List<BlockDiffDTO> items = listQuery.where(conditions).orderBy(BLOCK.HEIGHT.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(BlockDiffDTO.class);

        for (BlockDiffDTO item : items) {
//            if(item.getHashRate() == null) {
                Timestamp endBLockTime = this.dslContext.select(BLOCK.CREATED_TIME).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(item.getHeight() - 119))).fetchOneInto(Timestamp.class);
                long interval = item.getCreatedTime() - endBLockTime.getTime();
                Double totalDiff = 0d;
                for (int i = 0; i < 120; i++) {
                    totalDiff += this.dslContext.select(BLOCK.DIFFICULTY).from(BLOCK).where(BLOCK.HEIGHT.eq(ULong.valueOf(item.getHeight() - i))).fetchOneInto(Double.class);
                }
                Double hashRate = new BigDecimal(totalDiff).divide(new BigDecimal(interval / 1000), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
                item.setHashRate(hashRate);
//            }
        }

        ListModel<BlockDiffDTO, BlockCriteriaDTO> result = new ListModel<BlockDiffDTO, BlockCriteriaDTO>(criteria, items, TOTAL_COUNT_MAX_NUM);

        return result;
    }


    public BlockChainDto getBlockChainInfo() {

        List<Field<?>> fields = new ArrayList<>(Arrays.asList(BLOCK_CHAIN.fields()));
        BlockChainDto blockChainDto = this.dslContext.select(fields)
                .from(BLOCK_CHAIN).fetchOneInto(BlockChainDto.class);

//		blockChainDto.setInbTotalSupply(new BigDecimal(blockChainDto.getInbTotalSupply()).divide(new BigDecimal(Math.pow(10,18))).doubleValue());

        //TODO 开源时换掉此代码
        BigDecimal inbTotalSupply = new BigDecimal(blockChainDto.getLatestBlockNum() * 6.34);
        blockChainDto.setInbTotalSupply(inbTotalSupply.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        BigDecimal mortgageInb = new BigDecimal(inbTotalSupply.multiply(new BigDecimal(0.3)).doubleValue());
        blockChainDto.setMortgageNetInb(mortgageInb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return blockChainDto;
    }

    public  Map<ULong,String> getErrorBlock(){
        Map<ULong,String> errorBlock = new HashMap<>();
        boolean flag = true;
        while (flag){
            int latestHeight = this.dslContext.select(BLOCK.HEIGHT).from(BLOCK).orderBy(BLOCK.ID.desc()).limit(1).fetchOneInto(Integer.class);
            for (int i = latestHeight; i >= 0; i--) {
                BlockRecord blockRecord = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(i))).fetchOneInto(BlockRecord.class);
                BlockRecord lastRecord = this.dslContext.select().from(BLOCK).where(BLOCK.HEIGHT.equal(ULong.valueOf(i-1))).fetchOneInto(BlockRecord.class);
                if(blockRecord == null || lastRecord == null){
                    errorBlock.put(ULong.valueOf(i),"block is null");
                } else if( blockRecord.getPrevBlockHash()==null || !blockRecord.getPrevBlockHash().equals(lastRecord.getHash())){
                    errorBlock.put(blockRecord.getHeight(),blockRecord.getHash());
                }
                logger.error("现在是第"+i+"个块");
            }
            flag = false;
        }
        return errorBlock;
    }



//
//    public ListModel<Node, NodeCriteriaDTO> getNodeInfo(NodeCriteriaDTO criteria) {
//        ArrayList<Condition> conditions = new ArrayList<>();
//        SelectJoinStep<?> listQuery = this.dslContext.select()
//                .from(NODE);
//        long totalCount = this.dslContext.select(DSL.count())
//                .from(NODE)
//                .fetchOneInto(Long.class);
//        List<Node> items = listQuery.where(conditions).orderBy(NODE.VOTE_NUMBER.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(Node.class);
//
//        double total = 0;
//
//        for (Node node : items) {
//            total += node.getVoteNumber().doubleValue();
//        }
//
//        for (Node node : items) {
//            BigDecimal b = new BigDecimal(node.getVoteNumber().doubleValue() / total);
//            double voteRatio = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            node.setVoteRatio(voteRatio);
////			node.setDateCreated(node.getDateCreated().toString().substring(0,node.getDateCreated().toString().indexOf(".")));
//        }
//        ListModel<Node, NodeCriteriaDTO> result = new ListModel<Node, NodeCriteriaDTO>(criteria, items, totalCount);
//
//        return result;
//    }
//
//    public void createBlock() {
//
//        this.dslContext.update(BLOCK_CHAIN)
//                .set(BLOCK_CHAIN.TRANSACTION_NUM, BLOCK_CHAIN.TRANSACTION_NUM.add(1))
//                .set(BLOCK_CHAIN.ADDRESS_NUM, BLOCK_CHAIN.ADDRESS_NUM.add(2))
//                .where(BLOCK_CHAIN.ID.eq(ULong.valueOf("1")))
//                .execute();
//
//    }
//
//    public Node getNodeInfoByAccount(String address) {
//        Node node = this.dslContext.select().from(NODE)
//                .where(NODE.ADDRESS.eq(address))
//                .fetchOneInto(Node.class);
//
//        if (node == null) {
//            return new Node();
//        }
//        return node;
//    }


}
