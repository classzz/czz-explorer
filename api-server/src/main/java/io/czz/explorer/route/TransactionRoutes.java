package io.czz.explorer.route;

import com.google.inject.Inject;
import io.czz.explorer.dto.ListModel;
import io.czz.explorer.dto.transaction.TransactionCriteria;
import io.czz.explorer.dto.transaction.TransactionDTO;
import io.czz.explorer.dto.transaction.TransferUTXOCriteria;
import io.czz.explorer.dto.transaction.UTXODTO;
import io.czz.explorer.service.TransactionService;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;
import org.jooq.types.ULong;

import java.util.Map;
import java.util.Optional;


public class TransactionRoutes {

	private TransactionService txService;

	@Inject
	public TransactionRoutes(TransactionService txService) {
		this.txService= txService;
	}
	

	@GET
	@Path(ApiAppRoutePaths.V1.TRANSACTIONS)
	public ListModel<TransactionDTO, TransactionCriteria> listTransactions(Optional<Integer> blockHeight, Optional<Integer> page, Optional<Integer> limit, Optional<String> transHash) throws Throwable {

		TransactionCriteria criteria = new TransactionCriteria();
		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));
		criteria.setBlock(blockHeight.orElse(null));
		criteria.setHash(transHash.orElse(null));

		return this.txService.listTransactions(criteria);

	}

	@GET
	@Path(ApiAppRoutePaths.V1.TRANSACTION_UTXO)
	public UTXODTO listUTXO(Optional<String> transHash, String address){
		TransferUTXOCriteria transferUTXOCriteria = new TransferUTXOCriteria();
//		transferUTXOCriteria.setLimit(limit.orElse(20));
//		transferUTXOCriteria.setPage(page.orElse(1));
		transferUTXOCriteria.setTransHash(transHash.orElse(null));
		transferUTXOCriteria.setAddress(address);
		transferUTXOCriteria.setStatus(1);
		return this.txService.listUTXO(transferUTXOCriteria);
	}


//	@GET
//	@Path(ApiAppRoutePaths.V1.TRANSACTION_SEARCH)
//	public TransactionModel getTrxByHash(String hash) {
//
//		TransactionCriteria criteria = new TransactionCriteria();
//		criteria.setHash(hash);
//		return this.txService.getTxByHash(criteria.getHash());
//
//	}


	@GET
	@Path(ApiAppRoutePaths.V1.TRANSACTIONS_WALLET)
	public ListModel<TransactionDTO,TransactionCriteria> listTransactionsWallet(String address, Optional<Integer> page, Optional<Integer> limit) {

		TransactionCriteria criteria = new TransactionCriteria();
		criteria.setLimit(limit.orElse(20));
		criteria.setPage(page.orElse(1));
		criteria.setAddress(address);
		return this.txService.getTransactionsForWallet(criteria);

	}

	@GET
	@Path(ApiAppRoutePaths.V1.BLOCK_FIX)
	public void updateBlock(Integer start, Integer end){

		 this.txService.fixTransactionMinerAndRewards(start, end);
	}

//
//    @GET
//    @Path(ApiAppRoutePaths.V1.TRANSACTION_SEND)
//    public String sendTransaction(String toAddress) {
//
//         return this.txService.sendTransaction(toAddress);
//
//    }
}
