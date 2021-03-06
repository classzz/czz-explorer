/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.TransferOutRecord;

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
public class TransferOut extends TableImpl<TransferOutRecord> {

    private static final long serialVersionUID = -215055402;

    /**
     * The reference instance of <code>czztmp.transfer_out</code>
     */
    public static final TransferOut TRANSFER_OUT = new TransferOut();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransferOutRecord> getRecordType() {
        return TransferOutRecord.class;
    }

    /**
     * The column <code>czztmp.transfer_out.id</code>.
     */
    public final TableField<TransferOutRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.transfer_out.amount</code>.
     */
    public final TableField<TransferOutRecord, Double> AMOUNT = createField("amount", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.transfer_out.transaction_id</code>.
     */
    public final TableField<TransferOutRecord, ULong> TRANSACTION_ID = createField("transaction_id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.transfer_out.transaction_index</code>.
     */
    public final TableField<TransferOutRecord, Integer> TRANSACTION_INDEX = createField("transaction_index", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>czztmp.transfer_out.address</code>.
     */
    public final TableField<TransferOutRecord, String> ADDRESS = createField("address", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.transfer_out.asm</code>.
     */
    public final TableField<TransferOutRecord, String> ASM = createField("asm", org.jooq.impl.SQLDataType.VARCHAR.length(1000), this, "");

    /**
     * The column <code>czztmp.transfer_out.hex</code>.
     */
    public final TableField<TransferOutRecord, String> HEX = createField("hex", org.jooq.impl.SQLDataType.VARCHAR.length(1000), this, "");

    /**
     * The column <code>czztmp.transfer_out.script_pubkey_type</code>.
     */
    public final TableField<TransferOutRecord, String> SCRIPT_PUBKEY_TYPE = createField("script_pubkey_type", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.transfer_out.reqsigs</code>.
     */
    public final TableField<TransferOutRecord, Integer> REQSIGS = createField("reqsigs", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>czztmp.transfer_out</code> table reference
     */
    public TransferOut() {
        this("transfer_out", null);
    }

    /**
     * Create an aliased <code>czztmp.transfer_out</code> table reference
     */
    public TransferOut(String alias) {
        this(alias, TRANSFER_OUT);
    }

    private TransferOut(String alias, Table<TransferOutRecord> aliased) {
        this(alias, aliased, null);
    }

    private TransferOut(String alias, Table<TransferOutRecord> aliased, Field<?>[] parameters) {
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
    public Identity<TransferOutRecord, ULong> getIdentity() {
        return Keys.IDENTITY_TRANSFER_OUT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TransferOutRecord> getPrimaryKey() {
        return Keys.KEY_TRANSFER_OUT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TransferOutRecord>> getKeys() {
        return Arrays.<UniqueKey<TransferOutRecord>>asList(Keys.KEY_TRANSFER_OUT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransferOut as(String alias) {
        return new TransferOut(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TransferOut rename(String name) {
        return new TransferOut(name, null);
    }
}
