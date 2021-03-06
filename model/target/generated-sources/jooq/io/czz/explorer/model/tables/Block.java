/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.BlockRecord;

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
public class Block extends TableImpl<BlockRecord> {

    private static final long serialVersionUID = -928691097;

    /**
     * The reference instance of <code>czztmp.block</code>
     */
    public static final Block BLOCK = new Block();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BlockRecord> getRecordType() {
        return BlockRecord.class;
    }

    /**
     * The column <code>czztmp.block.id</code>.
     */
    public final TableField<BlockRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.block.created_time</code>.
     */
    public final TableField<BlockRecord, Timestamp> CREATED_TIME = createField("created_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>czztmp.block.height</code>.
     */
    public final TableField<BlockRecord, ULong> HEIGHT = createField("height", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.block.hash</code>.
     */
    public final TableField<BlockRecord, String> HASH = createField("hash", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>czztmp.block.prev_block_hash</code>.
     */
    public final TableField<BlockRecord, String> PREV_BLOCK_HASH = createField("prev_block_hash", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>czztmp.block.next_block_hash</code>.
     */
    public final TableField<BlockRecord, String> NEXT_BLOCK_HASH = createField("next_block_hash", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.block.merkle_root</code>.
     */
    public final TableField<BlockRecord, String> MERKLE_ROOT = createField("merkle_root", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

    /**
     * The column <code>czztmp.block.miner_address</code>.
     */
    public final TableField<BlockRecord, String> MINER_ADDRESS = createField("miner_address", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.block.tx_count</code>.
     */
    public final TableField<BlockRecord, Integer> TX_COUNT = createField("tx_count", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>czztmp.block.difficulty</code>.
     */
    public final TableField<BlockRecord, Double> DIFFICULTY = createField("difficulty", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>czztmp.block.sum_difficulty</code>.
     */
    public final TableField<BlockRecord, Double> SUM_DIFFICULTY = createField("sum_difficulty", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>czztmp.block.hash_rate</code>.
     */
    public final TableField<BlockRecord, Double> HASH_RATE = createField("hash_rate", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>czztmp.block.transaction_fees</code>.
     */
    public final TableField<BlockRecord, Double> TRANSACTION_FEES = createField("transaction_fees", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.block.output_total</code>.
     */
    public final TableField<BlockRecord, Double> OUTPUT_TOTAL = createField("output_total", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.block.size</code>.
     */
    public final TableField<BlockRecord, Long> SIZE = createField("size", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>czztmp.block.version</code>.
     */
    public final TableField<BlockRecord, Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>czztmp.block.version_hex</code>.
     */
    public final TableField<BlockRecord, String> VERSION_HEX = createField("version_hex", org.jooq.impl.SQLDataType.VARCHAR.length(1000), this, "");

    /**
     * The column <code>czztmp.block.nonce</code>.
     */
    public final TableField<BlockRecord, ULong> NONCE = createField("nonce", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.block.bits</code>.
     */
    public final TableField<BlockRecord, ULong> BITS = createField("bits", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>czztmp.block.confirmations</code>.
     */
    public final TableField<BlockRecord, Integer> CONFIRMATIONS = createField("confirmations", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>czztmp.block.reward</code>.
     */
    public final TableField<BlockRecord, Double> REWARD = createField("reward", org.jooq.impl.SQLDataType.DOUBLE.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.DOUBLE)), this, "");

    /**
     * The column <code>czztmp.block.is_main</code>.
     */
    public final TableField<BlockRecord, Integer> IS_MAIN = createField("is_main", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>czztmp.block</code> table reference
     */
    public Block() {
        this("block", null);
    }

    /**
     * Create an aliased <code>czztmp.block</code> table reference
     */
    public Block(String alias) {
        this(alias, BLOCK);
    }

    private Block(String alias, Table<BlockRecord> aliased) {
        this(alias, aliased, null);
    }

    private Block(String alias, Table<BlockRecord> aliased, Field<?>[] parameters) {
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
    public Identity<BlockRecord, ULong> getIdentity() {
        return Keys.IDENTITY_BLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BlockRecord> getPrimaryKey() {
        return Keys.KEY_BLOCK_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BlockRecord>> getKeys() {
        return Arrays.<UniqueKey<BlockRecord>>asList(Keys.KEY_BLOCK_PRIMARY, Keys.KEY_BLOCK_HEIGHT_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Block as(String alias) {
        return new Block(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Block rename(String name) {
        return new Block(name, null);
    }
}
