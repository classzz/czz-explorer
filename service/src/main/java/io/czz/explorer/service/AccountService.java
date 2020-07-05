package io.czz.explorer.service;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import io.czz.explorer.chain.CzzChainService;
import io.czz.explorer.constants.InbConstants;
import io.czz.explorer.dto.JsonParam;
import io.czz.explorer.dto.ListModel;
import io.czz.explorer.dto.ListResult;
import io.czz.explorer.dto.account.AccountCriteria;
import io.czz.explorer.dto.account.Accountdto;
import io.czz.explorer.dto.block.BlockDTO;
import io.czz.explorer.dto.transaction.*;
import io.czz.explorer.model.tables.pojos.Transaction;
import io.czz.explorer.model.tables.pojos.TransferIn;
import io.czz.explorer.model.tables.records.AccountRecord;
import io.czz.explorer.model.tables.records.TransactionRecord;
import io.czz.explorer.utils.CompressUtil;
import io.czz.explorer.utils.HttpUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.czz.explorer.model.tables.Account.ACCOUNT;
import static io.czz.explorer.model.tables.Block.BLOCK;
import static io.czz.explorer.model.tables.TransferIn.TRANSFER_IN;
import static io.czz.explorer.model.tables.TransferUtxo.TRANSFER_UTXO;
import static io.czz.explorer.model.tables.TransferOut.TRANSFER_OUT;
import static io.czz.explorer.model.tables.Transaction.TRANSACTION;


public class AccountService {

	private DSLContext dslContext;

	private TransactionService txService;

	private BlockService blockService;

	private CzzChainService czzChainService;

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Inject
	public AccountService(DSLContext dslContext, TransactionService txService,BlockService blockService,CzzChainService czzChainService) {
		this.dslContext = dslContext;
		this.txService = txService;
		this.blockService = blockService;
		this.czzChainService=czzChainService;
	}


	public int getTotalAccount() {
		return this.dslContext.select(DSL.count()).from(ACCOUNT).fetchOneInto(Integer.class);
	}

	public List<Accountdto> getLatestAccounts(int limit){

		return this.dslContext.select(ACCOUNT.fields()).from(ACCOUNT).orderBy(ACCOUNT.CREATED_TIME.desc()).limit(limit).fetchInto(Accountdto.class);

	}


	public void createOrUpdateAccount(String address) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Double totalInput = this.dslContext.select(TRANSFER_UTXO.AMOUNT.sum()).from(TRANSFER_UTXO).where(TRANSFER_UTXO.ADDRESS.equal(address)).and(TRANSFER_UTXO.STATUS.notEqual(3)).and(TRANSFER_UTXO.AMOUNT.isNotNull()).fetchOneInto(Double.class);
//		Double totalOutput = this.dslContext.select(TRANSFER_UTXO.AMOUNT.sum()).from(TRANSFER_UTXO).where(TRANSFER_UTXO.ADDRESS.equal(address)).and(TRANSFER_UTXO.STATUS.equal(2)).and(TRANSFER_UTXO.AMOUNT.isNotNull()).fetchOneInto(Double.class);
		Double balance = this.dslContext.select(TRANSFER_UTXO.AMOUNT.sum()).from(TRANSFER_UTXO).where(TRANSFER_UTXO.ADDRESS.equal(address)).and(TRANSFER_UTXO.STATUS.equal(1)).and(TRANSFER_UTXO.AMOUNT.isNotNull()).fetchOneInto(Double.class);

//		Double balance = result.get("balance");
//		BigDecimal balance = new BigDecimal(totalInput.toString()).subtract(new BigDecimal(totalOutput.toString()));
//		if(balance.compareTo(new BigDecimal(0)) != 1){
//			balance = new BigDecimal(0);
//			logger.info("address balance error ", address);
//		}
		if(balance ==  null){
			balance = 0d;
		}
	    if(totalInput ==  null){
			totalInput = 0d;
		}

		AccountRecord record = this.dslContext.select(ACCOUNT.ID)
				.from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(address)).fetchOneInto(AccountRecord.class);

		// Create it if it doesn't exists yet
		if (record == null) {
			logger.info("create account");
			System.out.println("balance is" + balance);
			record = this.dslContext.insertInto(ACCOUNT)
//			.set(ACCOUNT.ACCOUNT_NAME,tronAccount.getAccountName().toStringUtf8())
					.set(ACCOUNT.ADDRESS, address)
					.set(ACCOUNT.BALANCE, new BigDecimal(balance))
					.set(ACCOUNT.TOTAL_INPUT,new BigDecimal(totalInput))
					.set(ACCOUNT.TOTAL_OUTPUT,new BigDecimal(totalInput-balance))
					.set(ACCOUNT.CREATED_TIME, Timestamp.valueOf(format.format(System.currentTimeMillis())))
					.returning(ACCOUNT.ID)
					.fetchOne();

		} else {
			logger.info("update account: " + address);

			//Update if exists
			this.dslContext.update(ACCOUNT)
					.set(ACCOUNT.BALANCE, new BigDecimal(balance))
					.set(ACCOUNT.TOTAL_INPUT,new BigDecimal(totalInput))
					.set(ACCOUNT.TOTAL_OUTPUT,new BigDecimal(totalInput-balance))
//					.set(ACCOUNT.TX_COUNT, DSL.select(DSL.count()).from(TRANSFER_OUT).where(TRANSFER_OUT.ADDRESS.eq(address)))
					.where(ACCOUNT.ID.eq(record.getId()))
					.execute();


		}
	}

	public void createOrUpdateAccount(String address,Double amount) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Double totalInput = this.dslContext.select(TRANSFER_UTXO.AMOUNT.sum()).from(TRANSFER_UTXO).where(TRANSFER_UTXO.ADDRESS.equal(address)).and(TRANSFER_UTXO.STATUS.notEqual(3)).and(TRANSFER_UTXO.AMOUNT.isNotNull()).fetchOneInto(Double.class);

//		Double balance = result.get("balance");
//		BigDecimal balance = new BigDecimal(totalInput.toString()).subtract(new BigDecimal(totalOutput.toString()));
//		if(balance.compareTo(new BigDecimal(0)) != 1){
//			balance = new BigDecimal(0);
//			logger.info("address balance error ", address);
//		}

		if(totalInput ==  null){
			totalInput = 0d;
		}

		AccountRecord record = this.dslContext.select(ACCOUNT.ID)
				.from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(address)).fetchOneInto(AccountRecord.class);

		// Create it if it doesn't exists yet
		if (record == null) {
			logger.info("create account");
			System.out.println("balance is" + amount);
			record = this.dslContext.insertInto(ACCOUNT)
//			.set(ACCOUNT.ACCOUNT_NAME,tronAccount.getAccountName().toStringUtf8())
					.set(ACCOUNT.ADDRESS, address)
					.set(ACCOUNT.BALANCE, new BigDecimal(amount))
					.set(ACCOUNT.TOTAL_INPUT, new BigDecimal(totalInput))
					.set(ACCOUNT.TOTAL_OUTPUT,new BigDecimal(totalInput-amount))
					.set(ACCOUNT.CREATED_TIME, Timestamp.valueOf(format.format(System.currentTimeMillis())))
					.returning(ACCOUNT.ID)
					.fetchOne();

		} else {
			logger.info("update account: " + address);

			//Update if exists
			this.dslContext.update(ACCOUNT)
					.set(ACCOUNT.BALANCE, new BigDecimal(amount))
					.set(ACCOUNT.TOTAL_INPUT, new BigDecimal(totalInput))
					.set(ACCOUNT.TOTAL_OUTPUT, new BigDecimal(totalInput-amount))
//					.set(ACCOUNT.TX_COUNT, DSL.select(DSL.count()).from(TRANSFER_OUT).where(TRANSFER_OUT.ADDRESS.eq(address)))
					.where(ACCOUNT.ID.eq(record.getId()))
					.execute();


		}
	}


	public Accountdto getActByAddress(AccountCriteria accountCriteria){

		Accountdto result = this.dslContext.select(ACCOUNT.ID,ACCOUNT.BALANCE,ACCOUNT.TX_COUNT,ACCOUNT.TOTAL_INPUT,ACCOUNT.TOTAL_OUTPUT)
				.from(ACCOUNT).where(ACCOUNT.ADDRESS.eq(accountCriteria.getAddress())).fetchOneInto(Accountdto.class);

//		double amount = Double.valueOf(result.getMortgagte());
//		double balance = result.getBalance();
//		double divideNumber = Math.pow(10, 18);
//		BigDecimal a = new BigDecimal(amount / divideNumber);
//		BigDecimal b = new BigDecimal(balance / divideNumber);
//		double amountResult = a.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//		double balanceResult = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//		result.setMortgagte(String.valueOf(amountResult));
//		result.setBalance(balanceResult);
		result.setTxCount(this.dslContext.select(DSL.count()).from(TRANSFER_OUT).where(TRANSFER_OUT.ADDRESS.eq(accountCriteria.getAddress())).fetchOneInto(Integer.class));
		return result;
	}

	public ListModel<TransferUtxoDTO,TransferUTXOCriteria> listUTXO(TransferUTXOCriteria criteria){

		logger.error("list utxo begin {}", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
		ArrayList<Condition> conditions = new ArrayList<>();

		long totalCount =0 ;
		totalCount = this.dslContext.select(DSL.count()).from(TRANSFER_UTXO)
				.where(TRANSFER_UTXO.ADDRESS.eq(criteria.getAddress())).and(TRANSFER_UTXO.STATUS.equal(1)).fetchOneInto(Long.class);

		if (criteria.getStatus() != null) {
			conditions.add(TRANSFER_UTXO.STATUS.eq(criteria.getStatus()));
		}

		if (criteria.getAddress() != null) {
			conditions.add(TRANSFER_UTXO.ADDRESS.eq(criteria.getAddress()));
		}

		if(criteria.getUtxoId() != null){
		    conditions.add(TRANSFER_UTXO.ID.gt(criteria.getUtxoId()));
        }

		List<TransferUtxoDTO> items = this.dslContext.select(TRANSFER_UTXO.ID, TRANSFER_UTXO.AMOUNT, TRANSFER_UTXO.TX_HASH, TRANSFER_UTXO.TRANSFER_OUT_ID, TRANSFER_UTXO.ADDRESS, TRANSFER_UTXO.SCRIPT_PUB_KEY, TRANSFER_UTXO.VOUT,TRANSFER_UTXO.STATUS)
				.from(TRANSFER_UTXO).where(conditions).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(TransferUtxoDTO.class);

		logger.error("list utxo deal utxo {} ", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
        for (TransferUtxoDTO transferUtxo : items) {
            Transaction transaction = this.dslContext.select().from(TRANSACTION).where(TRANSACTION.HASH.eq(transferUtxo.getTxHash())).fetchOneInto(Transaction.class);
            if(transaction == null){
            	transferUtxo.setStatus(2);
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

        ListModel<TransferUtxoDTO,TransferUTXOCriteria> result = new ListModel<TransferUtxoDTO,TransferUTXOCriteria>(criteria, items, totalCount);
		logger.error("list utxo end {}", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
		return result;
	}


	public HttpServletResponse compress(HttpServletResponse response, TransferUTXOCriteria criteria){
		ListModel<TransferUtxoDTO,TransferUTXOCriteria> result = this.listUTXO(criteria);
//		try {
//			CompressUtil.excute(response,result.toString());
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CompressUtil.compressToGzip(result.toString(),"UTF-8");
		response.setHeader("Content-Encoding", "gzip");
		return  response;
	}




	public Double listTransLimit(String address,Integer limit){
		TransferUTXOCriteria criteria = new TransferUTXOCriteria();
		criteria.setAddress(address);
		criteria.setStatus(1);
		criteria.setLimit(limit);
		double limitAmount = 0d;
		ListModel<TransferUtxoDTO,TransferUTXOCriteria> utxos = listUTXO(criteria);
		for (TransferUtxoDTO item : utxos.getItems()) {
			limitAmount += item.getAmount();
		}

		return limitAmount;
	}




	public ListResult<TransferModel, AccountCriteria> listTransfersIn(AccountCriteria criteria) {

		ArrayList<Condition> conditions = new ArrayList<>();


//		List<Field<?>> fields = new ArrayList<>(Arrays.asList(TRANSFER.fields()));
//		fields.add(TRANSACTION.HASH);
//
//		SelectJoinStep<?> listQuery = this.dslContext.select(fields)
//				.from(TRANSACTION)
//				.join(TRANSFER).on(TRANSFER.TRANSACTION_ID.eq(TRANSACTION.ID)).and((TRANSFER.TO.eq(criteria.getAddress())))
//				;
//
//		Integer totalCount = null;
//
//		if (!criteria.isTrx()) {
//			totalCount = this.dslContext.select(ACCOUNT.TRANSFER_TO_COUNT)
//					.from(ACCOUNT)
//					.where(ACCOUNT.ADDRESS.eq(criteria.getAddress()))
//					.fetchOneInto(Integer.class);
//		}else {
//
//			conditions.add(TRANSFER.TOKEN.isNull());
//
//			totalCount = this.dslContext.select(DSL.count())
//					.from(TRANSACTION)
//					.join(TRANSFER).on(TRANSFER.TRANSACTION_ID.eq(TRANSACTION.ID)).and((TRANSFER.TO.eq(criteria.getAddress())))
//					.where(conditions)
//					.fetchOneInto(Integer.class);
//			;
//
//		}
//
//
//		if (totalCount==null) {
//			totalCount = 0;
//		}
//
//		List<TransferModel> items = listQuery.where(conditions).orderBy(TRANSFER.TIMESTAMP.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(TransferModel.class);


//		ListResult<TransferModel, AccountCriteria> result = new ListResult<TransferModel, AccountCriteria>(criteria, items, totalCount);
		ListResult<TransferModel, AccountCriteria> result = new ListResult<TransferModel, AccountCriteria>();

		return result;
	}


	public ListResult<TransferModel, AccountCriteria> listTransfersOut(AccountCriteria criteria) {

		ArrayList<Condition> conditions = new ArrayList<>();

//
//		List<Field<?>> fields = new ArrayList<>(Arrays.asList(TRANSFER.fields()));
//		fields.add(TRANSACTION.HASH);
//		fields.add(TRANSACTION.BLOCK_ID);
//		fields.add(TRANSACTION.TYPE);
//
//		SelectJoinStep<?> listQuery = this.dslContext.select(fields)
//				.from(TRANSACTION)
//				.join(TRANSFER).on(TRANSFER.TRANSACTION_ID.eq(TRANSACTION.ID)).and((TRANSFER.FROM.eq(criteria.getAddress())))
//				;
//
//
//		Integer totalCount = null;
//
//		if (!criteria.isTrx()) {
//			totalCount = this.dslContext.select(ACCOUNT.TRANSFER_FROM_COUNT)
//					.from(ACCOUNT)
//					.where(ACCOUNT.ADDRESS.eq(criteria.getAddress()))
//					.fetchOneInto(Integer.class);
//		}else {
//
//			conditions.add(TRANSFER.TOKEN.isNull());
//
//			totalCount = this.dslContext.select(DSL.count())
//					.from(TRANSFER).where(TRANSFER.FROM.eq(criteria.getAddress()))
//					.fetchOneInto(Integer.class);
//
//		}
//
//		if (totalCount==null) {
//			totalCount = 0;
//		}
//		conditions.add(TRANSACTION.TYPE.eq(criteria.getTransType()));
//
//		List<TransferModel> items = listQuery.where(conditions).orderBy(TRANSFER.TIMESTAMP.desc()).limit(criteria.getLimit()).offset(criteria.getOffSet()).fetchInto(TransferModel.class);
//		List<TransferModel> item = new ArrayList<>();
//		for(TransferModel transferModel:items){
//			double amount = transferModel.getAmount();
//			double divideNumber = Math.pow(10,18);
//			BigDecimal b = new BigDecimal(amount/divideNumber);
//			double result = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//			transferModel.setAmount(result);
//			transferModel.setTimestamp(transferModel.getTimestamp().substring(0,transferModel.getTimestamp().indexOf(".")));
//			item.add(transferModel);
//
//			BlockDTO blockDTO = this.blockService.getBlockById(transferModel.getBlockId());
//			transferModel.setBlockNum(blockDTO.getNum());
//		}

		ListResult<TransferModel, AccountCriteria> result = new ListResult<TransferModel, AccountCriteria>();

		return result;
	}

}
