/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.TransferUtxoRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TransferUtxo extends TableImpl<TransferUtxoRecord> {

    private static final long serialVersionUID = -335117062;

    /**
     * The reference instance of <code>czztmp.transfer_utxo</code>
     */
    public static final TransferUtxo TRANSFER_UTXO = new TransferUtxo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransferUtxoRecord> getRecordType() {
        return TransferUtxoRecord.class;
    }

    /**
     * The column <code>czztmp.transfer_utxo.id</code>.
     */
    public final TableField<TransferUtxoRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.amount</code>.
     */
    public final TableField<TransferUtxoRecord, Double> AMOUNT = createField("amount", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.tx_hash</code>.
     */
    public final TableField<TransferUtxoRecord, String> TX_HASH = createField("tx_hash", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.address</code>.
     */
    public final TableField<TransferUtxoRecord, String> ADDRESS = createField("address", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.transfer_out_id</code>.
     */
    public final TableField<TransferUtxoRecord, ULong> TRANSFER_OUT_ID = createField("transfer_out_id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.transfer_utxo.vout</code>.
     */
    public final TableField<TransferUtxoRecord, Integer> VOUT = createField("vout", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>czztmp.transfer_utxo.script_pub_key</code>.
     */
    public final TableField<TransferUtxoRecord, String> SCRIPT_PUB_KEY = createField("script_pub_key", org.jooq.impl.SQLDataType.VARCHAR.length(1000), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.coin_base</code>.
     */
    public final TableField<TransferUtxoRecord, Integer> COIN_BASE = createField("coin_base", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>czztmp.transfer_utxo.status</code>.
     */
    public final TableField<TransferUtxoRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>czztmp.transfer_utxo</code> table reference
     */
    public TransferUtxo() {
        this("transfer_utxo", null);
    }

    /**
     * Create an aliased <code>czztmp.transfer_utxo</code> table reference
     */
    public TransferUtxo(String alias) {
        this(alias, TRANSFER_UTXO);
    }

    private TransferUtxo(String alias, Table<TransferUtxoRecord> aliased) {
        this(alias, aliased, null);
    }

    private TransferUtxo(String alias, Table<TransferUtxoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Czztmp.CZZTMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TransferUtxoRecord, ULong> getIdentity() {
        return Keys.IDENTITY_TRANSFER_UTXO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TransferUtxoRecord> getPrimaryKey() {
        return Keys.KEY_TRANSFER_UTXO_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TransferUtxoRecord>> getKeys() {
        return Arrays.<UniqueKey<TransferUtxoRecord>>asList(Keys.KEY_TRANSFER_UTXO_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransferUtxo as(String alias) {
        return new TransferUtxo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TransferUtxo rename(String name) {
        return new TransferUtxo(name, null);
    }
}