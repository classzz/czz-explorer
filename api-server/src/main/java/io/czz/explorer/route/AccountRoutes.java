package io.czz.explorer.route;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.czz.explorer.dto.ListModel;
import io.czz.explorer.dto.ListResult;
import io.czz.explorer.dto.account.AccountCriteria;
import io.czz.explorer.dto.account.Accountdto;
import io.czz.explorer.dto.transaction.*;
import io.czz.explorer.service.AccountService;
import io.czz.explorer.service.TransactionService;
import io.czz.explorer.utils.CompressUtil;
import org.jooby.Request;
import org.jooby.Response;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooq.types.ULong;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Singleton
public class AccountRoutes {

	private AccountService accountService;
	private TransactionService transactionService;
	
	@Inject
	public AccountRoutes(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_TRANSFERS_IN)
	public ListResult<TransferModel, AccountCriteria> transfers(String address, Optional<Integer> limit, Optional<Integer> page) {

		AccountCriteria criteria = new AccountCriteria();

		criteria.setAddress(address);
		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));

		return this.accountService.listTransfersIn(criteria);
	}

	/**
	 * Get basic informations on account
	 * @param address
	 * @return {@link Accountdto}
	 * @throws Throwable
	 */
	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_SEARCH)
	public Accountdto accountInfo(String address) throws Throwable {
		AccountCriteria accountCriteria = new AccountCriteria();
		accountCriteria.setAddress(address);
		return this.accountService.getActByAddress(accountCriteria);
//		return this.accountInfoService.getAccountByAddress(new AccountDetailCriteriaDTO(address));
	}



	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_TRANSFERS)
	public ListResult<TransferModel, AccountCriteria> transfersOut(String address,Optional<Boolean> trx,Optional<Integer> limit,Optional<Integer> page, Optional<Integer> transType) {
		
		AccountCriteria criteria = new AccountCriteria();
		criteria.setAddress(address);
		criteria.setLimit(limit.orElse(20));
		criteria.setTrx(trx.orElse(false));
		criteria.setPage(page.orElse(1));
		criteria.setTransType(transType.orElse(null));
		
		return this.accountService.listTransfersOut(criteria);
	}


	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_UTXOS)
	public ListModel<TransferUtxoDTO,TransferUTXOCriteria> listUTXO(String address, Optional<Integer> limit, Optional<Integer> page){
		TransferUTXOCriteria transferUTXOCriteria = new TransferUTXOCriteria();
		transferUTXOCriteria.setLimit(limit.orElse(20));
		transferUTXOCriteria.setPage(page.orElse(1));
		transferUTXOCriteria.setAddress(address);
		transferUTXOCriteria.setStatus(1);
		return this.accountService.listUTXO(transferUTXOCriteria);
	}


	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_TRANS_LIMIT)
	public Double listTransLimit(String address,Integer limit){
		return this.accountService.listTransLimit(address,limit);
	}


	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_ERROR_UTXO)
	public Map<ULong,String> listErrorUtxo(String address){
		return this.transactionService.getErrorUtxo(address);
	}

//	@GET
//	@Path(ApiAppRoutePaths.V1.ACCOUNT_UTXOS_LIMIT)
//	public ListModel<TransferUtxoDTO,TransferUTXOCriteria>  listOffsetUtxo(String address,String uid, Optional<Integer> limit, Optional<Integer> page){
//		TransferUTXOCriteria transferUTXOCriteria = new TransferUTXOCriteria();
//		transferUTXOCriteria.setLimit(limit.orElse(20));
//		transferUTXOCriteria.setPage(page.orElse(1));
//		transferUTXOCriteria.setAddress(address);
//		transferUTXOCriteria.setUtxoId(ULong.valueOf(uid));
//		transferUTXOCriteria.setStatus(1);
//		return this.accountService.listUTXO(transferUTXOCriteria);
//	}


	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_UTXOS_LIMIT)
	public void   listOffsetUtxo(Request request, Response response, String address, String uid, Optional<Integer> limit, Optional<Integer> page){
		TransferUTXOCriteria transferUTXOCriteria = new TransferUTXOCriteria();
		transferUTXOCriteria.setLimit(limit.orElse(20));
		transferUTXOCriteria.setPage(page.orElse(1));
		transferUTXOCriteria.setAddress(address);
		transferUTXOCriteria.setUtxoId(ULong.valueOf(uid));
		transferUTXOCriteria.setStatus(1);
		try {
			CompressUtil.excute(response,accountService.listUTXO(transferUTXOCriteria).toString());
		} catch (ServletException e) {
			e.printStackTrace();
				} catch (IOException e) {
			e.printStackTrace();
		}

//		response.header("Content-Encoding", "gzip");
//		response.header("Transfer-Encoding", "chunked");
//		String str = accountService.listUTXO(transferUTXOCriteria).toString();
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		GZIPOutputStream gzip = null;
//		try {
//			gzip = new GZIPOutputStream(out);
//			gzip.write(str.getBytes());
//			gzip.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

//
//	@GET
//	@Path(ApiAppRoutePaths.V1.ACCOUNT_SEARCH_TRANSFERS_)
//	public ListModel<TransactionDTO, TransactionCriteria> listTransactionsWallet(Optional<String> address, Optional<Integer> page, Optional<Integer> limit) {
//
//		TransactionCriteria criteria = new TransactionCriteria();
//		criteria.setLimit(limit.orElse(20));
//		criteria.setPage(page.orElse(1));
//		criteria.setAddress(address.orElse(null));
//		return this.transactionService.getTransactionsForWallet(criteria);
//
//	}



	/* FIXME
	 * DISABLED
	@GET
	@Path(ApiAppRoutePaths.V1.ACCOUNT_FREEZE_ALL)	
	public ListResult<FrozenBalanceModel, AccountCriteria> freezeAll(String address,Optional<Integer> limit,Optional<Integer> page) {
		
		AccountCriteria criteria = new AccountCriteria();
		
		criteria.setAddress(address);
		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));
		
		return this.accountService.listFrozenBalance(criteria);
	}
	*/

	@GET
	@Path(ApiAppRoutePaths.V1.TRANSACTION_DH)
	public ListModel<DhVo, TransactionCriteria> transfersDh(Optional<Integer> page, Optional<Integer> limit) {
		TransactionCriteria criteria = new TransactionCriteria();
		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));
		accountService.getLatestAccounts(100);
		return this.accountService.transfersDh(criteria);
	}
}
