/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.OrphanBlockRecord;

import java.sql.Timestamp;
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
public class OrphanBlock extends TableImpl<OrphanBlockRecord> {

    private static final long serialVersionUID = -997794177;

    /**
     * The reference instance of <code>czztmp.orphan_block</code>
     */
    public static final OrphanBlock ORPHAN_BLOCK = new OrphanBlock();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrphanBlockRecord> getRecordType() {
        return OrphanBlockRecord.class;
    }

    /**
     * The column <code>czztmp.orphan_block.id</code>.
     */
    public final TableField<OrphanBlockRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.orphan_block.created_time</code>.
     */
    public final TableField<OrphanBlockRecord, Timestamp> CREATED_TIME = createField("created_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>czztmp.orphan_block.height</code>.
     */
    public final TableField<OrphanBlockRecord, ULong> HEIGHT = createField("height", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.orphan_block.hash</code>.
     */
    public final TableField<OrphanBlockRecord, String> HASH = createField("hash", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>czztmp.orphan_block.merkle_root</code>.
     */
    public final TableField<OrphanBlockRecord, String> MERKLE_ROOT = createField("merkle_root", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>czztmp.orphan_block.miner_address</code>.
     */
    public final TableField<OrphanBlockRecord, String> MINER_ADDRESS = createField("miner_address", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.orphan_block.tx_count</code>.
     */
    public final TableField<OrphanBlockRecord, Integer> TX_COUNT = createField("tx_count", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>czztmp.orphan_block.difficulty</code>.
     */
    public final TableField<OrphanBlockRecord, Double> DIFFICULTY = createField("difficulty", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>czztmp.orphan_block.transaction_fees</code>.
     */
    public final TableField<OrphanBlockRecord, Double> TRANSACTION_FEES = createField("transaction_fees", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.orphan_block.size</code>.
     */
    public final TableField<OrphanBlockRecord, Long> SIZE = createField("size", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>czztmp.orphan_block.nonce</code>.
     */
    public final TableField<OrphanBlockRecord, ULong> NONCE = createField("nonce", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.orphan_block.bits</code>.
     */
    public final TableField<OrphanBlockRecord, ULong> BITS = createField("bits", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.orphan_block.confirmations</code>.
     */
    public final TableField<OrphanBlockRecord, Integer> CONFIRMATIONS = createField("confirmations", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>czztmp.orphan_block.reward</code>.
     */
    public final TableField<OrphanBlockRecord, Double> REWARD = createField("reward", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * Create a <code>czztmp.orphan_block</code> table reference
     */
    public OrphanBlock() {
        this("orphan_block", null);
    }

    /**
     * Create an aliased <code>czztmp.orphan_block</code> table reference
     */
    public OrphanBlock(String alias) {
        this(alias, ORPHAN_BLOCK);
    }

    private OrphanBlock(String alias, Table<OrphanBlockRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrphanBlock(String alias, Table<OrphanBlockRecord> aliased, Field<?>[] parameters) {
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
    public Identity<OrphanBlockRecord, ULong> getIdentity() {
        return Keys.IDENTITY_ORPHAN_BLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<OrphanBlockRecord> getPrimaryKey() {
        return Keys.KEY_ORPHAN_BLOCK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<OrphanBlockRecord>> getKeys() {
        return Arrays.<UniqueKey<OrphanBlockRecord>>asList(Keys.KEY_ORPHAN_BLOCK_PRIMARY, Keys.KEY_ORPHAN_BLOCK_HEIGHT_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrphanBlock as(String alias) {
        return new OrphanBlock(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrphanBlock rename(String name) {
        return new OrphanBlock(name, null);
    }
}
