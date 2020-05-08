/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model;


import io.czz.explorer.model.tables.Account;
import io.czz.explorer.model.tables.Block;
import io.czz.explorer.model.tables.BlockChain;
import io.czz.explorer.model.tables.FlywaySchemaHistory;
import io.czz.explorer.model.tables.OrphanBlock;
import io.czz.explorer.model.tables.SyncAccount;
import io.czz.explorer.model.tables.SyncBlock;
import io.czz.explorer.model.tables.SyncNode;
import io.czz.explorer.model.tables.Transaction;
import io.czz.explorer.model.tables.TransferIn;
import io.czz.explorer.model.tables.TransferOut;
import io.czz.explorer.model.tables.TransferUtxo;
import io.czz.explorer.model.tables.records.AccountRecord;
import io.czz.explorer.model.tables.records.BlockChainRecord;
import io.czz.explorer.model.tables.records.BlockRecord;
import io.czz.explorer.model.tables.records.FlywaySchemaHistoryRecord;
import io.czz.explorer.model.tables.records.OrphanBlockRecord;
import io.czz.explorer.model.tables.records.SyncAccountRecord;
import io.czz.explorer.model.tables.records.SyncBlockRecord;
import io.czz.explorer.model.tables.records.SyncNodeRecord;
import io.czz.explorer.model.tables.records.TransactionRecord;
import io.czz.explorer.model.tables.records.TransferInRecord;
import io.czz.explorer.model.tables.records.TransferOutRecord;
import io.czz.explorer.model.tables.records.TransferUtxoRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;


/**
 * A class modelling foreign key relationships between tables of the <code>czztmp</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountRecord, ULong> IDENTITY_ACCOUNT = Identities0.IDENTITY_ACCOUNT;
    public static final Identity<BlockRecord, ULong> IDENTITY_BLOCK = Identities0.IDENTITY_BLOCK;
    public static final Identity<BlockChainRecord, ULong> IDENTITY_BLOCK_CHAIN = Identities0.IDENTITY_BLOCK_CHAIN;
    public static final Identity<OrphanBlockRecord, ULong> IDENTITY_ORPHAN_BLOCK = Identities0.IDENTITY_ORPHAN_BLOCK;
    public static final Identity<SyncAccountRecord, ULong> IDENTITY_SYNC_ACCOUNT = Identities0.IDENTITY_SYNC_ACCOUNT;
    public static final Identity<SyncBlockRecord, ULong> IDENTITY_SYNC_BLOCK = Identities0.IDENTITY_SYNC_BLOCK;
    public static final Identity<SyncNodeRecord, UInteger> IDENTITY_SYNC_NODE = Identities0.IDENTITY_SYNC_NODE;
    public static final Identity<TransactionRecord, ULong> IDENTITY_TRANSACTION = Identities0.IDENTITY_TRANSACTION;
    public static final Identity<TransferInRecord, ULong> IDENTITY_TRANSFER_IN = Identities0.IDENTITY_TRANSFER_IN;
    public static final Identity<TransferOutRecord, ULong> IDENTITY_TRANSFER_OUT = Identities0.IDENTITY_TRANSFER_OUT;
    public static final Identity<TransferUtxoRecord, ULong> IDENTITY_TRANSFER_UTXO = Identities0.IDENTITY_TRANSFER_UTXO;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_PRIMARY = UniqueKeys0.KEY_ACCOUNT_PRIMARY;
    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_ACCOUNT_ADDRESS_UNIQUE = UniqueKeys0.KEY_ACCOUNT_ACCOUNT_ADDRESS_UNIQUE;
    public static final UniqueKey<BlockRecord> KEY_BLOCK_PRIMARY = UniqueKeys0.KEY_BLOCK_PRIMARY;
    public static final UniqueKey<BlockRecord> KEY_BLOCK_HEIGHT_UNIQUE = UniqueKeys0.KEY_BLOCK_HEIGHT_UNIQUE;
    public static final UniqueKey<BlockChainRecord> KEY_BLOCK_CHAIN_PRIMARY = UniqueKeys0.KEY_BLOCK_CHAIN_PRIMARY;
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = UniqueKeys0.KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final UniqueKey<OrphanBlockRecord> KEY_ORPHAN_BLOCK_PRIMARY = UniqueKeys0.KEY_ORPHAN_BLOCK_PRIMARY;
    public static final UniqueKey<OrphanBlockRecord> KEY_ORPHAN_BLOCK_HEIGHT_UNIQUE = UniqueKeys0.KEY_ORPHAN_BLOCK_HEIGHT_UNIQUE;
    public static final UniqueKey<SyncAccountRecord> KEY_SYNC_ACCOUNT_PRIMARY = UniqueKeys0.KEY_SYNC_ACCOUNT_PRIMARY;
    public static final UniqueKey<SyncAccountRecord> KEY_SYNC_ACCOUNT_ADDRESS_UNIQUE = UniqueKeys0.KEY_SYNC_ACCOUNT_ADDRESS_UNIQUE;
    public static final UniqueKey<SyncBlockRecord> KEY_SYNC_BLOCK_PRIMARY = UniqueKeys0.KEY_SYNC_BLOCK_PRIMARY;
    public static final UniqueKey<SyncBlockRecord> KEY_SYNC_BLOCK_BLOCK_NUM_UNIQUE = UniqueKeys0.KEY_SYNC_BLOCK_BLOCK_NUM_UNIQUE;
    public static final UniqueKey<SyncNodeRecord> KEY_SYNC_NODE_PRIMARY = UniqueKeys0.KEY_SYNC_NODE_PRIMARY;
    public static final UniqueKey<SyncNodeRecord> KEY_SYNC_NODE_NODE_ID_UNIQUE = UniqueKeys0.KEY_SYNC_NODE_NODE_ID_UNIQUE;
    public static final UniqueKey<TransactionRecord> KEY_TRANSACTION_PRIMARY = UniqueKeys0.KEY_TRANSACTION_PRIMARY;
    public static final UniqueKey<TransactionRecord> KEY_TRANSACTION_HASH_UNIQUE = UniqueKeys0.KEY_TRANSACTION_HASH_UNIQUE;
    public static final UniqueKey<TransferInRecord> KEY_TRANSFER_IN_PRIMARY = UniqueKeys0.KEY_TRANSFER_IN_PRIMARY;
    public static final UniqueKey<TransferOutRecord> KEY_TRANSFER_OUT_PRIMARY = UniqueKeys0.KEY_TRANSFER_OUT_PRIMARY;
    public static final UniqueKey<TransferUtxoRecord> KEY_TRANSFER_UTXO_PRIMARY = UniqueKeys0.KEY_TRANSFER_UTXO_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<AccountRecord, ULong> IDENTITY_ACCOUNT = createIdentity(Account.ACCOUNT, Account.ACCOUNT.ID);
        public static Identity<BlockRecord, ULong> IDENTITY_BLOCK = createIdentity(Block.BLOCK, Block.BLOCK.ID);
        public static Identity<BlockChainRecord, ULong> IDENTITY_BLOCK_CHAIN = createIdentity(BlockChain.BLOCK_CHAIN, BlockChain.BLOCK_CHAIN.ID);
        public static Identity<OrphanBlockRecord, ULong> IDENTITY_ORPHAN_BLOCK = createIdentity(OrphanBlock.ORPHAN_BLOCK, OrphanBlock.ORPHAN_BLOCK.ID);
        public static Identity<SyncAccountRecord, ULong> IDENTITY_SYNC_ACCOUNT = createIdentity(SyncAccount.SYNC_ACCOUNT, SyncAccount.SYNC_ACCOUNT.ID);
        public static Identity<SyncBlockRecord, ULong> IDENTITY_SYNC_BLOCK = createIdentity(SyncBlock.SYNC_BLOCK, SyncBlock.SYNC_BLOCK.ID);
        public static Identity<SyncNodeRecord, UInteger> IDENTITY_SYNC_NODE = createIdentity(SyncNode.SYNC_NODE, SyncNode.SYNC_NODE.ID);
        public static Identity<TransactionRecord, ULong> IDENTITY_TRANSACTION = createIdentity(Transaction.TRANSACTION, Transaction.TRANSACTION.ID);
        public static Identity<TransferInRecord, ULong> IDENTITY_TRANSFER_IN = createIdentity(TransferIn.TRANSFER_IN, TransferIn.TRANSFER_IN.ID);
        public static Identity<TransferOutRecord, ULong> IDENTITY_TRANSFER_OUT = createIdentity(TransferOut.TRANSFER_OUT, TransferOut.TRANSFER_OUT.ID);
        public static Identity<TransferUtxoRecord, ULong> IDENTITY_TRANSFER_UTXO = createIdentity(TransferUtxo.TRANSFER_UTXO, TransferUtxo.TRANSFER_UTXO.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<AccountRecord> KEY_ACCOUNT_PRIMARY = createUniqueKey(Account.ACCOUNT, "KEY_account_PRIMARY", Account.ACCOUNT.ID);
        public static final UniqueKey<AccountRecord> KEY_ACCOUNT_ACCOUNT_ADDRESS_UNIQUE = createUniqueKey(Account.ACCOUNT, "KEY_account_account_address_unique", Account.ACCOUNT.ADDRESS);
        public static final UniqueKey<BlockRecord> KEY_BLOCK_PRIMARY = createUniqueKey(Block.BLOCK, "KEY_block_PRIMARY", Block.BLOCK.ID);
        public static final UniqueKey<BlockRecord> KEY_BLOCK_HEIGHT_UNIQUE = createUniqueKey(Block.BLOCK, "KEY_block_height_UNIQUE", Block.BLOCK.HEIGHT);
        public static final UniqueKey<BlockChainRecord> KEY_BLOCK_CHAIN_PRIMARY = createUniqueKey(BlockChain.BLOCK_CHAIN, "KEY_block_chain_PRIMARY", BlockChain.BLOCK_CHAIN.ID);
        public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "KEY_flyway_schema_history_PRIMARY", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<OrphanBlockRecord> KEY_ORPHAN_BLOCK_PRIMARY = createUniqueKey(OrphanBlock.ORPHAN_BLOCK, "KEY_orphan_block_PRIMARY", OrphanBlock.ORPHAN_BLOCK.ID);
        public static final UniqueKey<OrphanBlockRecord> KEY_ORPHAN_BLOCK_HEIGHT_UNIQUE = createUniqueKey(OrphanBlock.ORPHAN_BLOCK, "KEY_orphan_block_height_UNIQUE", OrphanBlock.ORPHAN_BLOCK.HEIGHT);
        public static final UniqueKey<SyncAccountRecord> KEY_SYNC_ACCOUNT_PRIMARY = createUniqueKey(SyncAccount.SYNC_ACCOUNT, "KEY_sync_account_PRIMARY", SyncAccount.SYNC_ACCOUNT.ID);
        public static final UniqueKey<SyncAccountRecord> KEY_SYNC_ACCOUNT_ADDRESS_UNIQUE = createUniqueKey(SyncAccount.SYNC_ACCOUNT, "KEY_sync_account_address_unique", SyncAccount.SYNC_ACCOUNT.ADDRESS, SyncAccount.SYNC_ACCOUNT.ORIGIN);
        public static final UniqueKey<SyncBlockRecord> KEY_SYNC_BLOCK_PRIMARY = createUniqueKey(SyncBlock.SYNC_BLOCK, "KEY_sync_block_PRIMARY", SyncBlock.SYNC_BLOCK.ID);
        public static final UniqueKey<SyncBlockRecord> KEY_SYNC_BLOCK_BLOCK_NUM_UNIQUE = createUniqueKey(SyncBlock.SYNC_BLOCK, "KEY_sync_block_block_num_UNIQUE", SyncBlock.SYNC_BLOCK.BLOCK_NUM);
        public static final UniqueKey<SyncNodeRecord> KEY_SYNC_NODE_PRIMARY = createUniqueKey(SyncNode.SYNC_NODE, "KEY_sync_node_PRIMARY", SyncNode.SYNC_NODE.ID);
        public static final UniqueKey<SyncNodeRecord> KEY_SYNC_NODE_NODE_ID_UNIQUE = createUniqueKey(SyncNode.SYNC_NODE, "KEY_sync_node_node_id_UNIQUE", SyncNode.SYNC_NODE.NODE_ID);
        public static final UniqueKey<TransactionRecord> KEY_TRANSACTION_PRIMARY = createUniqueKey(Transaction.TRANSACTION, "KEY_transaction_PRIMARY", Transaction.TRANSACTION.ID);
        public static final UniqueKey<TransactionRecord> KEY_TRANSACTION_HASH_UNIQUE = createUniqueKey(Transaction.TRANSACTION, "KEY_transaction_hash_UNIQUE", Transaction.TRANSACTION.HASH);
        public static final UniqueKey<TransferInRecord> KEY_TRANSFER_IN_PRIMARY = createUniqueKey(TransferIn.TRANSFER_IN, "KEY_transfer_in_PRIMARY", TransferIn.TRANSFER_IN.ID);
        public static final UniqueKey<TransferOutRecord> KEY_TRANSFER_OUT_PRIMARY = createUniqueKey(TransferOut.TRANSFER_OUT, "KEY_transfer_out_PRIMARY", TransferOut.TRANSFER_OUT.ID);
        public static final UniqueKey<TransferUtxoRecord> KEY_TRANSFER_UTXO_PRIMARY = createUniqueKey(TransferUtxo.TRANSFER_UTXO, "KEY_transfer_utxo_PRIMARY", TransferUtxo.TRANSFER_UTXO.ID);
    }
}
