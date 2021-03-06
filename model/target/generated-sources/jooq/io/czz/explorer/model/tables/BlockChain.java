/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.BlockChainRecord;

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
public class BlockChain extends TableImpl<BlockChainRecord> {

    private static final long serialVersionUID = -158718707;

    /**
     * The reference instance of <code>czztmp.block_chain</code>
     */
    public static final BlockChain BLOCK_CHAIN = new BlockChain();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BlockChainRecord> getRecordType() {
        return BlockChainRecord.class;
    }

    /**
     * The column <code>czztmp.block_chain.id</code>.
     */
    public final TableField<BlockChainRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.block_chain.created_time</code>.
     */
    public final TableField<BlockChainRecord, Timestamp> CREATED_TIME = createField("created_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>czztmp.block_chain.name</code>.
     */
    public final TableField<BlockChainRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

    /**
     * The column <code>czztmp.block_chain.type</code>.
     */
    public final TableField<BlockChainRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>czztmp.block_chain</code> table reference
     */
    public BlockChain() {
        this("block_chain", null);
    }

    /**
     * Create an aliased <code>czztmp.block_chain</code> table reference
     */
    public BlockChain(String alias) {
        this(alias, BLOCK_CHAIN);
    }

    private BlockChain(String alias, Table<BlockChainRecord> aliased) {
        this(alias, aliased, null);
    }

    private BlockChain(String alias, Table<BlockChainRecord> aliased, Field<?>[] parameters) {
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
    public Identity<BlockChainRecord, ULong> getIdentity() {
        return Keys.IDENTITY_BLOCK_CHAIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BlockChainRecord> getPrimaryKey() {
        return Keys.KEY_BLOCK_CHAIN_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BlockChainRecord>> getKeys() {
        return Arrays.<UniqueKey<BlockChainRecord>>asList(Keys.KEY_BLOCK_CHAIN_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChain as(String alias) {
        return new BlockChain(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BlockChain rename(String name) {
        return new BlockChain(name, null);
    }
}
