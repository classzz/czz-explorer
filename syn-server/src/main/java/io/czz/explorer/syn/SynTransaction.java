package io.czz.explorer.syn;

import com.google.inject.Inject;
import io.czz.explorer.SynServerConfig;
import io.czz.explorer.chain.CzzChainService;
import io.czz.explorer.dto.transaction.*;
import io.czz.explorer.model.tables.Transaction;
import io.czz.explorer.model.tables.records.TransferInRecord;
import io.czz.explorer.service.AccountService;
import io.czz.explorer.service.TransactionService;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import static io.czz.explorer.model.tables.Account.ACCOUNT;
import static io.czz.explorer.model.tables.TransferIn.TRANSFER_IN;
import static io.czz.explorer.model.tables.TransferUtxo.TRANSFER_UTXO;

public class SynTransaction {


    private DSLContext dslContext;
    private AccountService accountService;
    private TransactionService transactionService;
    private CzzChainService czzChainService;
    private SynServerConfig config;

    private static final Logger logger = LoggerFactory.getLogger(SynAccount.class);

    @Inject
    public SynTransaction(DSLContext dslContext,AccountService accountService,TransactionService transactionService,CzzChainService czzChainService,SynServerConfig config) {
        this.dslContext = dslContext;
        this.accountService = accountService;
        this.transactionService=transactionService;
        this.czzChainService= czzChainService;
        this.config = config;
    }

    public void checkAccountUtxo(){

        List<String> address = this.dslContext.select(ACCOUNT.ADDRESS).from(ACCOUNT).fetchInto(String.class);
        for (String s : address) {
            TransferUTXOCriteria transferUTXOCriteria = new TransferUTXOCriteria();
            transferUTXOCriteria.setAddress(s);
            transferUTXOCriteria.setStatus(1);
            UTXODTO utxodto = transactionService.listUTXO(transferUTXOCriteria);
            List<TransferUtxoDTO> transferUtxos = utxodto.getTransferUtxos();
            for (TransferUtxoDTO transferUtxo : transferUtxos) {

                List<TransferInRecord> transferInRecords = this.dslContext.select().from(TRANSFER_IN).where(TRANSFER_IN.TRANSACTION_HASH.equals(transferUtxo.getTxHash())).fetchInto(TransferInRecord.class);
                if(!transferInRecords.isEmpty()){
                    this.dslContext.update(TRANSFER_UTXO)
                            .set(TRANSFER_UTXO.STATUS, 2)
                            .where(TRANSFER_UTXO.ID.eq(transferUtxo.getId())).execute();
                }
//                TransactionDTO transactionDTO = czzChainService.getTransaction(transferUtxo.getTxHash());
//                List<VInDTO> vInDTOS = transactionDTO.getVin();
//                for (VInDTO vInDTO : vInDTOS) {
//                    if(vInDTO.getTxid().equals()){
//
//                    }
//                }
            }
        }
    }
}
