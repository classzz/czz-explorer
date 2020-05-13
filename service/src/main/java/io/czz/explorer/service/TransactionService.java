package io.czz.explorer.service;


import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.chain.CzzChainService;
import io.czz.explorer.constants.InbConstants;
import io.czz.explorer.dto.*;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.dto.transaction.*;
import io.czz.explorer.model.Tables;
import io.czz.explorer.model.tables.pojos.Block;
import io.czz.explorer.model.tables.pojos.Transaction;
import io.czz.explorer.model.tables.pojos.TransferIn;
import io.czz.explorer.model.tables.pojos.TransferOut;
import io.czz.explorer.model.tables.records.*;
import io.czz.explorer.utils.HexToStringUtil;
import io.czz.explorer.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectOnConditionStep;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static io.czz.explorer.model.Tables.TRANSFER_OUT;
import static io.czz.explorer.model.tables.Account.ACCOUNT;
import static io.czz.explorer.model.tables.Block.BLOCK;
import static io.czz.explorer.model.tables.SyncAccount.SYNC_ACCOUNT;
import static io.czz.explorer.model.tables.Transaction.TRANSACTION;
import static io.czz.explorer.model.tables.TransferIn.TRANSFER_IN;
import static io.czz.explorer.model.tables.TransferUtxo.TRANSFER_UTXO;


@Singleton
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private DSLContext dslContext;

    private CzzChainService czzChainService;
    private List<TransferUtxoRecord> transferUtxoRecords;

    private static final int TOTAL_COUNT_MAX_NUM = 20000;

    @Inject
    public TransactionService(DSLContext dslContext, CzzChainService czzChainService) {
        this.dslContext = dslContext;
        this.czzChainService = czzChainService;
    }

    public ListModel<TransactionDTO, TransactionCriteria> listTransactions(TransactionCriteria criteria) {


        ArrayList<Condition> conditions = new ArrayList<>();
        long totalCount;
        if (criteria.getBlock() != null) {
            String blockHash = this.dslContext.select(BLOCK.HASH)
                    .from(BLOCK)
                    .where(BLOCK.HEIGHT.eq(ULong.valueOf(criteria.getBlock()))).fetchOneInto(String.class);
            conditions.add(TRANSACTION.BLOCK_HASH.eq(blockHash));
            totalCount = this.dslContext.select(DSL.count())
                    .from(TRANSACTION)
                    .where(TRANSACTION.BLOCK_HASH.eq(blockHash))
                    .fetchOneInto(Long.class);
        } else if (criteria.getType() != null) {
            conditions.add(TRANSACTION.TYPE.eq(criteria.getType()));
            totalCount = this.dslContext.select(DSL.count())
                    .from(TRANSACTION)
                    .fetchOneInto(Long.class);
        } else if (criteria.getHash() != null) {
            conditions.add(TRANSACTION.HASH.eq(criteria.getHash()));
            totalCount = this.dslContext.select(DSL.count())
                    .from(TRANSACTION)
                    .where(TRANSACTION.HASH.eq(criteria.getHash()))
                    .fetchOneInto(Long.class);
        } else {
            totalCount = this.dslContext.select(DSL.count())
                    .from(TRANSACTION)
                    .fetchOneInto(Long.class);
        }

        List<TransactionDTO> items = this.dslContext.select(TRANSACTION.ID, TRANSACTION.HASH.as("txid"), TRANSACTION.BLOCK_HASH.as("blockhash"), TRANSACTION.BLOCK_TIME.as("blockCreatedTime")
                , BLOCK.HEIGHT.as("blockHeight"), BLOCK.MINER_ADDRESS.as("minerAddress"), TRANSACTION.CREATED_TIME, TRANSACTION.SIZE, TRANSACTION.CONFIRMATIONS, TRANSACTION.VERSION
                , TRANSACTION.FEES.as("transFees"), TRANSACTION.TOTAL_INPUT, TRANSACTION.TOTAL_OUTPUT)
                .from(TRANSACTION)
                .join(BLOCK).on(TRANSACTION.BLOCK_HASH.eq(BLOCK.HASH))
                .where(conditions).orderBy(TRANSACTION.CREATED_TIME.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(TransactionDTO.class);

//        if (criteria.getAddress() ==null && criteria.getHash()==null && criteria.getBlock()==null) {
//            ListModel<TransactionDTO, TransactionCriteria> result = new ListModel<>(criteria, items, totalCount);
//            return result;
//        }
        List<TransactionDTO> item = new ArrayList<>();
        for (TransactionDTO transaction : items) {

            //获取创世块写入信息
            if(transaction.getBlockHeight()==0){
                TransactionDTO transactionDTO = czzChainService.getTransaction(transaction.getTxid());
                String creationInfo = transactionDTO.getVin().get(0).getCoinbase();
                transaction.setInput(HexToStringUtil.hexStringToString(creationInfo));
            }

            List<TransferInRecord> transferInRecords = this.dslContext.select().from(TRANSFER_IN).where(TRANSFER_IN.TRANSACTION_ID.eq(transaction.getId())).fetchInto(TransferInRecord.class);
            List<VInDTO> vInDTOS = new ArrayList<>();

            if (transferInRecords.isEmpty()) {
                vInDTOS.add(new VInDTO());
            }
            for (TransferInRecord transferInRecord : transferInRecords) {
                VInDTO vInDTO = new VInDTO();
                TransferInDTO transferInDTO = new TransferInDTO();
                transferInDTO.setAsm(transferInRecord.getAsm());
                transferInDTO.setHex(transferInRecord.getHex());
                vInDTO.setScriptSig(transferInDTO);
                vInDTO.setCoinbase(transferInRecord.getCoinBase());
                vInDTO.setTxid(transferInRecord.getTransactionHash());
                vInDTO.setSequence(transferInRecord.getSequence());
                vInDTO.setVout(transferInRecord.getVout());
                vInDTO.setValue(transferInRecord.getAmount());
                vInDTO.setAddress(transferInRecord.getAddress());
                vInDTOS.add(vInDTO);
            }

            List<TransferOutRecord> transferOutRecords = this.dslContext.select().from(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.eq(transaction.getId())).fetchInto(TransferOutRecord.class);
            List<VOutDTO> vOutDTOS = new ArrayList<>();
            if (transferOutRecords.isEmpty()) {
                VOutDTO vOutDTO= new VOutDTO();
                TransferOutDTO transferOutDTO = new TransferOutDTO();
                transferOutDTO.setAddresses(new ArrayList<>());
                vOutDTO.setScriptPubKey(transferOutDTO);
                vOutDTOS.add(vOutDTO);
            }
            for (TransferOutRecord transferOutRecord : transferOutRecords) {
                VOutDTO vOutDTO = new VOutDTO();
                TransferOutDTO transferOutDTO = new TransferOutDTO();
                transferOutDTO.setAddresses(new ArrayList<>(Arrays.asList(transferOutRecord.getAddress())));
                transferOutDTO.setAsm(transferOutRecord.getAsm());
                transferOutDTO.setHex(transferOutRecord.getHex());
                transferOutDTO.setReqsigs(transferOutRecord.getReqsigs());
                transferOutDTO.setType(transferOutRecord.getScriptPubkeyType());
                vOutDTO.setScriptPubKey(transferOutDTO);
                vOutDTO.setValue(new BigDecimal(transferOutRecord.getAmount()));
                vOutDTOS.add(vOutDTO);
            }
            transaction.setVin(vInDTOS);
            transaction.setVout(vOutDTOS);
//			transaction.setBlockCreatedTime(DateFormatUtils.format(transaction.getBlockCreatedTime().,"yyyy-MM-dd HH:mm:ss"));
//            transaction.setConfirmations(czzChainService.getBlockHeight() - transaction.getBlockHeight() + 1);
            transaction.setbStop(false);
            transaction.setResTotal(3);
//            transaction.setInput("hello world");
            transaction.setTransFees(new BigDecimal(transaction.getTransFees()).setScale(9,BigDecimal.ROUND_HALF_UP).doubleValue());
            item.add(transaction);
        }

        ListModel<TransactionDTO, TransactionCriteria> result = new ListModel<TransactionDTO, TransactionCriteria>(criteria, item, TOTAL_COUNT_MAX_NUM);

        return result;

    }

    public UTXODTO listUTXO(TransferUTXOCriteria criteria) {

        ArrayList<Condition> conditions = new ArrayList<>();

        if (criteria.getStatus() != null) {
            conditions.add(TRANSFER_UTXO.STATUS.eq(criteria.getStatus()));
        }

        if (criteria.getAddress() != null) {
            conditions.add(TRANSFER_UTXO.ADDRESS.eq(criteria.getAddress()));
        }
        if (criteria.getTransHash() != null) {
            conditions.add(TRANSFER_UTXO.TX_HASH.eq(criteria.getTransHash()));
        }

        List<TransferUtxoDTO> items = this.dslContext.select(TRANSFER_UTXO.ID, TRANSFER_UTXO.AMOUNT, TRANSFER_UTXO.TX_HASH, TRANSFER_UTXO.TRANSFER_OUT_ID, TRANSFER_UTXO.ADDRESS, TRANSFER_UTXO.SCRIPT_PUB_KEY, TRANSFER_UTXO.VOUT)
                .from(TRANSFER_UTXO).where(conditions).fetchInto(TransferUtxoDTO.class);
        for (TransferUtxoDTO transferUtxo : items) {
            Transaction transaction = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.eq(transferUtxo.getTxHash())).fetchOneInto(Transaction.class);
            if(transaction == null){
                logger.info("utxo to trans error",transferUtxo.getTxHash());
                continue;
            }
            List<TransferIn> transferIn = this.dslContext.select().from(TRANSFER_IN).where(TRANSFER_IN.TRANSACTION_ID.eq(transaction.getId())).fetchInto(TransferIn.class);
            Long blockHeight = this.dslContext.select(BLOCK.HEIGHT).from(BLOCK).where(BLOCK.HASH.eq(transaction.getBlockHash())).fetchOneInto(Long.class);
            transferUtxo.setBlockHeight(blockHeight);
            transferUtxo.setCoinBase(2);
            if(!transferIn.isEmpty()){
                transferUtxo.setCoinBase(1);
            }
        }
        UTXODTO utxodto = new UTXODTO();
        utxodto.setTotalCount(items.size());
        utxodto.setTransferUtxos(items);
        return utxodto;

    }


    public ListModel<TransactionDTO, TransactionCriteria> getTransactionsForWallet(TransactionCriteria criteria) {

        long totalCount = 0;
        if (criteria.getAddress() != null) {
            totalCount = this.dslContext.selectDistinct(DSL.count(TRANSFER_OUT.TRANSACTION_ID)).from(TRANSFER_OUT)
                    .where(TRANSFER_OUT.ADDRESS.eq(criteria.getAddress()))
                    .fetchOneInto(Long.class);
        }

        List<ULong> transIds = this.dslContext.selectDistinct(TRANSFER_OUT.TRANSACTION_ID).from(TRANSFER_OUT)
                .where(TRANSFER_OUT.ADDRESS.eq(criteria.getAddress())).orderBy(TRANSFER_OUT.TRANSACTION_ID.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(ULong.class);

        List<TransactionDTO> items = new ArrayList<>();
        for (ULong transId : transIds) {
            TransactionRecord transactionRecord = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.ID.eq(transId)).fetchOneInto(TransactionRecord.class);
            if(transactionRecord != null) {
                TransactionCriteria transactionCriteria = new TransactionCriteria();
                transactionCriteria.setHash(transactionRecord.getHash());
                transactionCriteria.setPage(1);
                transactionCriteria.setLimit(1);
                ListModel<TransactionDTO, TransactionCriteria> transactionDTOS = this.listTransactions(transactionCriteria);
                if (!transactionDTOS.getItems().isEmpty()) {
                    items.add(this.listTransactions(transactionCriteria).getItems().get(0));
                }
            }

        }
        ListModel<TransactionDTO, TransactionCriteria> result = new ListModel<TransactionDTO, TransactionCriteria>(criteria, items, TOTAL_COUNT_MAX_NUM);
        return result;
    }


    public Map<String, Double> getUserBalanceFromUTXO(String address) {

        //所有交易列表
        TransferUTXOCriteria utxoCriteria = new TransferUTXOCriteria();
        utxoCriteria.setAddress(address);
        UTXODTO utxoList = this.listUTXO(utxoCriteria);

        //已花费交易列表
        TransferUTXOCriteria atxoCriteria = new TransferUTXOCriteria();
        atxoCriteria.setAddress(address);
        atxoCriteria.setStatus(2);
        UTXODTO atxoList = this.listUTXO(atxoCriteria);

        //已花费交易列表
        TransferUTXOCriteria txoCriteria = new TransferUTXOCriteria();
        txoCriteria.setAddress(address);
        txoCriteria.setStatus(1);
        UTXODTO txoList = this.listUTXO(txoCriteria);

        BigDecimal totalInput = new BigDecimal("0");
        for (TransferUtxoDTO transferUtxo : utxoList.getTransferUtxos()) {
            totalInput = totalInput.add(new BigDecimal(transferUtxo.getAmount().toString()));
        }

        BigDecimal totalOutput = new BigDecimal("0");
        for (TransferUtxoDTO transferUtxo : atxoList.getTransferUtxos()) {
            totalOutput = totalOutput.add(new BigDecimal(transferUtxo.getAmount().toString()));
        }

//        BigDecimal balance = new BigDecimal("0");
//        for (TransferUtxoDTO transferUtxo : txoList.getTransferUtxos()) {
//            balance = balance.add(new BigDecimal(transferUtxo.getAmount().toString()));
//        }

        Map<String, Double> result = new HashMap<>(2);
        result.put("totalInput", totalInput.doubleValue());
        result.put("totalOutput", totalOutput.doubleValue());
//        result.put("balance", balance.doubleValue());
        return result;
    }


    public Map saveTransaction(TransactionDTO transactionDTO) {

//        logger.info(" store trans start: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        TransactionRecord transRecord = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.eq(transactionDTO.getTxid())).fetchOneInto(TransactionRecord.class);
        if(transRecord!=null){
            return new HashMap();
        }

        logger.info("trans is {} :" + transactionDTO.getTxid());

        Timestamp timestamp = Timestamp.valueOf(DateFormatUtils.format(transactionDTO.getTime().longValue() * 1000, "yyyy-MM-dd HH:mm:ss"));
        Timestamp blocktimestamp = Timestamp.valueOf(DateFormatUtils.format(transactionDTO.getBlocktime().longValue() * 1000, "yyyy-MM-dd HH:mm:ss"));
        TransactionRecord txRecord = this.dslContext.insertInto(TRANSACTION)
                .set(TRANSACTION.CREATED_TIME, timestamp)
                .set(TRANSACTION.HASH, transactionDTO.getTxid())
                .set(TRANSACTION.BLOCK_HASH, transactionDTO.getBlockhash())
                .set(TRANSACTION.BLOCK_TIME, blocktimestamp)
                .set(TRANSACTION.SIZE, transactionDTO.getSize())
                .set(TRANSACTION.CONFIRMATIONS, transactionDTO.getConfirmations())
                .set(TRANSACTION.VERSION, transactionDTO.getVersion())
                .returning(TRANSACTION.ID, TRANSACTION.HASH)
                .fetchOne();

        BigDecimal totalOutput = new BigDecimal("0");
        //挖矿奖励为value中的最大值
        BigDecimal maxValue = new BigDecimal(0);

//        logger.info(" store vout start: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));

        List<VInDTO> vInDTOS = transactionDTO.getVin();
        List<VOutDTO> vOutDTOS = transactionDTO.getVout();

        Map<String,BigDecimal> addressAmount = new HashMap<>();
        Map<String,BigDecimal> addressAmountIn = new HashMap<>();
        Map<String,BigDecimal> addressAmountOut = new HashMap<>();


        //矿工地址
        String minerAddress = null;

        //vIn中的地址
        HashSet vInAddress = new HashSet();
        //交易是挖矿或者普通交易
        Integer coinBase = 0;

//        logger.info(" store vin start: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        List<TransferInRecord> transferInRecords = new ArrayList<>(vInDTOS.size());
        for (VInDTO vInDTO : vInDTOS) {
            if (StringUtils.isNotBlank(vInDTO.getCoinbase())) {
                this.dslContext.insertInto(TRANSFER_IN)
                        .set(TRANSFER_IN.TRANSACTION_ID, txRecord.getId())
                        .set(TRANSFER_IN.COIN_BASE, vInDTO.getCoinbase())
                        .set(TRANSFER_IN.SEQUENCE, vInDTO.getSequence())
                        .execute();

//                List<VOutDTO> vOutDTOList = vOutDTOS.stream().sorted(Comparator.comparing(VOutDTO::getValue).reversed()).collect(Collectors.toList());
                if(!vOutDTOS.isEmpty()) {
                    if (vOutDTOS.get(0).getScriptPubKey().getAddresses() != null) {
                        minerAddress = vOutDTOS.get(0).getScriptPubKey().getAddresses().get(0);
                    }
                }

                coinBase = 1;
            }
            if (StringUtils.isNotBlank(vInDTO.getTxid())) {
                TransferInRecord transferInRecord = this.dslContext.insertInto(TRANSFER_IN)
                        .set(TRANSFER_IN.TRANSACTION_ID, txRecord.getId())
                        .set(TRANSFER_IN.TRANSACTION_HASH, vInDTO.getTxid())
                        .set(TRANSFER_IN.SEQUENCE, vInDTO.getSequence())
                        .set(TRANSFER_IN.ASM, vInDTO.getScriptSig().getAsm())
                        .set(TRANSFER_IN.HEX, vInDTO.getScriptSig().getHex())
                        .set(TRANSFER_IN.VOUT, vInDTO.getVout())
                        .returning()
                        .fetchOne();

                transferInRecords.add(transferInRecord);
            }
        }

//        logger.info(" update utxo start: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));

        BigDecimal totalInput = new BigDecimal(0);
        if (!transferInRecords.isEmpty()) {
            for (TransferInRecord transferInRecord : transferInRecords) {
                //拿到对应的输出信息
                TransactionRecord transactionRecord = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.eq(transferInRecord.getTransactionHash())).fetchOneInto(TransactionRecord.class);
                List<TransferOutRecord> transferOutRecords = this.dslContext.select().from(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.eq(transactionRecord.getId())).orderBy(TRANSFER_OUT.ID.asc()).fetchInto(TransferOutRecord.class);
                if (transferOutRecords.isEmpty()) {
                    logger.info("transHash error is {}: " + transactionRecord.getHash());
                    throw new NullPointerException("哇啦啦啦啦,出错拉");
                }
                if (!transferOutRecords.isEmpty()) {
                    this.dslContext.update(TRANSFER_IN)
                            .set(TRANSFER_IN.ADDRESS, transferOutRecords.get(transferInRecord.getVout()).getAddress())
                            .set(TRANSFER_IN.AMOUNT, transferOutRecords.get(transferInRecord.getVout()).getAmount())
                            .where(TRANSFER_IN.ID.eq(transferInRecord.getId()))
                            .execute();

                    totalInput = totalInput.add(new BigDecimal(transferOutRecords.get(transferInRecord.getVout()).getAmount()));
                    vInAddress.add(transferOutRecords.get(transferInRecord.getVout()).getAddress());

                    String address = transferOutRecords.get(transferInRecord.getVout()).getAddress();
                    BigDecimal amount = new BigDecimal(transferOutRecords.get(transferInRecord.getVout()).getAmount());
                    BigDecimal amount1 = addressAmount.get(address) == null ? new BigDecimal(0) : addressAmount.get(address);
                    BigDecimal amount2 = addressAmountIn.get(address) == null ? new BigDecimal(0) : addressAmountIn.get(address);

                    addressAmount.put(address, amount1.subtract(amount));
                    addressAmountIn.put(address, amount2.add(amount));
                }

                //删除对应UTXO
                this.dslContext.update(TRANSFER_UTXO)
                        .set(TRANSFER_UTXO.STATUS, 2)
                        .where(TRANSFER_UTXO.TRANSFER_OUT_ID.eq(transferOutRecords.get(transferInRecord.getVout()).getId())).execute();

            }
        }



        if(vOutDTOS.size()>0){
            maxValue = vOutDTOS.get(0).getValue();
        }
        int vout = 0;
        int utxoStatus  = 1;
        for (VOutDTO vOutDTO : vOutDTOS) {

            String address = null;
            Integer reqsigs = 0;
            if (vOutDTO.getScriptPubKey().getAddresses() != null) {
                // 计算out的个数
                address = vOutDTO.getScriptPubKey().getAddresses().get(0);
                BigDecimal amount = vOutDTO.getValue();
                BigDecimal amount1 = addressAmount.get(address) == null ? new BigDecimal(0) : addressAmount.get(address);
                BigDecimal amount2 = addressAmountOut.get(address) == null ? new BigDecimal(0) : addressAmountOut.get(address);

                addressAmount.put(address, amount1.add(amount));
                addressAmountOut.put(address,amount2.add(amount));

                this.dslContext.update(ACCOUNT)
                        .set(ACCOUNT.TX_COUNT, ACCOUNT.TX_COUNT.add(1))
                        .where(ACCOUNT.ADDRESS.eq(address))
                        .execute();
            }
            if (vOutDTO.getScriptPubKey().getReqsigs() != null) {
                reqsigs = vOutDTO.getScriptPubKey().getReqsigs();
            }
            TransferOutRecord transferOutRecord = this.dslContext.insertInto(TRANSFER_OUT)
                    .set(TRANSFER_OUT.TRANSACTION_ID, txRecord.getId())
                    .set(TRANSFER_OUT.AMOUNT, vOutDTO.getValue().doubleValue())
//					.set(TRANSFER_OUT.TRANSACTION_INDEX, vOutDTO.get())
                    .set(TRANSFER_OUT.ADDRESS, address)
                    .set(TRANSFER_OUT.ASM, vOutDTO.getScriptPubKey().getAsm())
                    .set(TRANSFER_OUT.HEX, vOutDTO.getScriptPubKey().getHex())
                    .set(TRANSFER_OUT.REQSIGS, reqsigs)
                    .set(TRANSFER_OUT.SCRIPT_PUBKEY_TYPE, vOutDTO.getScriptPubKey().getType())
                    .set(TRANSFER_OUT.TRANSACTION_INDEX, vOutDTO.getN())
                    .returning(TRANSFER_OUT.ID)
                    .fetchOne();

            totalOutput = totalOutput.add(vOutDTO.getValue());

            if(vInAddress.contains(address)){
                utxoStatus = 3;
            }

            this.dslContext.insertInto(TRANSFER_UTXO)
                    .set(TRANSFER_UTXO.AMOUNT, vOutDTO.getValue().doubleValue())
                    .set(TRANSFER_UTXO.TX_HASH, txRecord.getHash())
                    .set(TRANSFER_UTXO.TRANSFER_OUT_ID, transferOutRecord.getId())
                    .set(TRANSFER_UTXO.ADDRESS, address)
                    .set(TRANSFER_UTXO.SCRIPT_PUB_KEY, vOutDTO.getScriptPubKey().getAsm())
                    .set(TRANSFER_UTXO.VOUT, Integer.valueOf(vout++))
                    .set(TRANSFER_UTXO.COIN_BASE,coinBase)
                    .set(TRANSFER_UTXO.STATUS,utxoStatus)
                    .execute();
        }

        // 地址余额
        for (String k:addressAmount.keySet()) {
            createOrUpdateAccount(k,addressAmount.get(k));

            AccountRecord record = this.dslContext.select(ACCOUNT.ID,ACCOUNT.TOTAL_INPUT,ACCOUNT.TOTAL_OUTPUT)
                    .from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(k)).fetchOneInto(AccountRecord.class);

            BigDecimal TotalInput = record.getTotalInput() == null ? new BigDecimal(0) : new BigDecimal(record.getTotalInput());
            BigDecimal TotalOutput = record.getTotalOutput() == null ? new BigDecimal(0) : new BigDecimal(record.getTotalOutput());

            if (addressAmountIn.get(k) != null) {
                this.dslContext.update(ACCOUNT)
                        .set(ACCOUNT.TOTAL_INPUT, TotalInput.subtract(addressAmountIn.get(k)).doubleValue())
                        .where(ACCOUNT.ID.eq(record.getId()))
                        .execute();
            }
            if (addressAmountOut.get(k) != null) {
                this.dslContext.update(ACCOUNT)
                        .set(ACCOUNT.TOTAL_OUTPUT, TotalOutput.subtract(addressAmountOut.get(k)).doubleValue())
                        .where(ACCOUNT.ID.eq(record.getId()))
                        .execute();
            }
        }

        // 更新交易信息,总输入,总输出,手续费
        BigDecimal transFees = new BigDecimal(0);
        if (totalInput.compareTo(BigDecimal.ZERO) == 1) {
            if(totalInput.compareTo(totalOutput) ==1 ) {
                transFees = totalInput.subtract(totalOutput);
            }
        }

//        logger.info("totalInput is :" + totalInput + " totalOuput is :" + totalOutput);
        this.dslContext.update(TRANSACTION)
                .set(TRANSACTION.TOTAL_INPUT, totalInput.doubleValue())
                .set(TRANSACTION.TOTAL_OUTPUT, totalOutput.doubleValue())
                .set(TRANSACTION.FEES, transFees.doubleValue())
                .where(TRANSACTION.ID.eq(txRecord.getId()))
                .execute();


        //更新出块奖励,矿工地址
        Map<String, Object> map = new HashMap<>(2);
        double reward = 0d;
        if (vInDTOS.get(0).getCoinbase() != null) {
            reward = maxValue.add(transFees).doubleValue();
        }
        map.put("reward", reward);
        map.put("minerAddress", minerAddress);

//        logger.info(" store trans end: {}" ,DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        return map;

    }


    public Map removeTransaction(Transaction transaction) {


        List<TransferIn> transferIns = this.dslContext.select()
                .from(TRANSFER_IN).where(TRANSFER_IN.TRANSACTION_ID.eq(transaction.getId())).fetchInto(TransferIn.class);

        List<TransferOut> transferOuts = this.dslContext.select()
                .from(TRANSFER_OUT).where(TRANSFER_OUT.TRANSACTION_ID.eq(transaction.getId())).fetchInto(TransferOut.class);

        logger.info("trans is {} :" + transaction.getHash());

        Map<String,BigDecimal> addressAmount = new HashMap<>();
        Map<String,BigDecimal> addressAmountIn = new HashMap<>();
        Map<String,BigDecimal> addressAmountOut = new HashMap<>();


        for (TransferIn transferIn : transferIns) {
            if (transferIn.getAddress() == null)
                continue;

            //拿到对应的输出信息
            String address = transferIn.getAddress();
            BigDecimal amount = new BigDecimal(transferIn.getAmount());
            BigDecimal amount1 = addressAmount.get(address) == null ? new BigDecimal(0) : addressAmount.get(address);
            BigDecimal amount2 = addressAmountIn.get(address) == null ? new BigDecimal(0) : addressAmountIn.get(address);

            addressAmount.put(address, amount1.subtract(amount));
            addressAmountIn.put(address, amount2.add(amount));

        }

        for (TransferOut transferOut : transferOuts) {
            if (transferOut.getAddress() == null)
                continue;

            // 计算out的个数
            String  address = transferOut.getAddress();
            BigDecimal amount = new BigDecimal(transferOut.getAmount());
            BigDecimal amount1 = addressAmount.get(address) == null ? new BigDecimal(0) : addressAmount.get(address);
            BigDecimal amount2 = addressAmountOut.get(address) == null ?  new BigDecimal(0)  : addressAmountOut.get(address);

            addressAmount.put(address, amount1.add(amount));
            addressAmountOut.put(address,amount2.add(amount));

        }

        // 地址余额
        for (String k:addressAmount.keySet()) {

            createOrUpdateAccount(k,addressAmount.get(k).multiply(new BigDecimal(-1)));
            AccountRecord record = this.dslContext.select(ACCOUNT.ID,ACCOUNT.TOTAL_INPUT,ACCOUNT.TOTAL_OUTPUT)
                    .from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(k)).fetchOneInto(AccountRecord.class);

            BigDecimal TotalInput = record.getTotalInput() == null ? new BigDecimal(0) : new BigDecimal(record.getTotalInput());
            BigDecimal TotalOutput = record.getTotalOutput() == null ? new BigDecimal(0) : new BigDecimal(record.getTotalOutput());

            if (addressAmountIn.get(k) != null) {
                this.dslContext.update(ACCOUNT)
                        .set(ACCOUNT.TOTAL_INPUT, TotalInput.subtract(addressAmountIn.get(k)).doubleValue())
                        .where(ACCOUNT.ID.eq(record.getId()))
                        .execute();
            }
            if (addressAmountOut.get(k) != null) {
                this.dslContext.update(ACCOUNT)
                        .set(ACCOUNT.TOTAL_OUTPUT, TotalOutput.subtract(addressAmountOut.get(k)).doubleValue())
                        .where(ACCOUNT.ID.eq(record.getId()))
                        .execute();
            }
        }

        return null;

    }

    public void createOrUpdateAccount(String address,BigDecimal amount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        AccountRecord record = this.dslContext.select(ACCOUNT.ID,ACCOUNT.BALANCE)
                .from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(address)).fetchOneInto(AccountRecord.class);

        // Create it if it doesn't exists yet
        if (record == null) {
            logger.info("create account");
            this.dslContext.insertInto(ACCOUNT)
                    .set(ACCOUNT.ADDRESS, address)
                    .set(ACCOUNT.BALANCE, amount.doubleValue())
                    .set(ACCOUNT.CREATED_TIME, Timestamp.valueOf(format.format(System.currentTimeMillis())))
                    .execute();

        } else {
            logger.info("update account: " + address+" amount:"+amount +"  record.getBalance():" + record.getBalance().doubleValue());

            //Update if exists
            this.dslContext.update(ACCOUNT)
                    .set(ACCOUNT.BALANCE, record.getBalance() + amount.doubleValue())
                    .where(ACCOUNT.ID.eq(record.getId()))
                    .execute();

        }
    }




    public void synAccount(String address)
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dslContext.insertInto(SYNC_ACCOUNT)
                .set(SYNC_ACCOUNT.ADDRESS, address)
                .set(SYNC_ACCOUNT.DATE_CREATED, Timestamp.valueOf(format.format(System.currentTimeMillis())))
                .execute();
    }

    public Map<ULong,String> getErrorUtxo(String address){
        Map<ULong,String> errorUtxo = new HashMap<>();
        TransferUTXOCriteria criteria = new TransferUTXOCriteria();
        criteria.setAddress(address);
        criteria.setStatus(1);
        UTXODTO utxos = listUTXO(criteria);
        for (TransferUtxoDTO item : utxos.getTransferUtxos()) {
            logger.error("地址"+item.getAddress()+"交易"+item.getTxHash());
            TransactionRecord transactionRecord = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.equal(item.getTxHash())).fetchOneInto(TransactionRecord.class);
            if(transactionRecord == null){
                errorUtxo.put(item.getId(),item.getTxHash());
            }
        }

        if(errorUtxo.isEmpty()){
            errorUtxo.put(ULong.valueOf(200),"no error utxo");
        }
        return errorUtxo;
    }

    public void fixTransactionMinerAndRewards(Integer start , Integer end){

        for (int i = start; i <= end; i++) {
            String blockHash = czzChainService.getBlockHash(i);
            BlockDTO block = czzChainService.getBlock(blockHash);
            List<String> transactionResults = block.getTx();
            BigDecimal reward = new BigDecimal("0");
            String minerAddress = null;
            if (!transactionResults.isEmpty()) {
                for (String transHash : transactionResults) {
                    TransactionDTO transactionDTO = czzChainService.getTransaction(transHash);
                    List<VOutDTO> vOutDTOS = transactionDTO.getVout();
                    reward = vOutDTOS.get(0).getValue();
                    minerAddress = vOutDTOS.get(0).getScriptPubKey().getAddresses().get(0);
                }
            }
            this.dslContext.update(BLOCK)
                    .set(BLOCK.MINER_ADDRESS,minerAddress)
                    .set(BLOCK.REWARD,reward.doubleValue())
                    .where(BLOCK.HEIGHT.equal(ULong.valueOf(i)))
                    .execute();
        }

    }
}
