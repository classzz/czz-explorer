/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables;


import io.czz.explorer.model.Czztmp;
import io.czz.explorer.model.Keys;
import io.czz.explorer.model.tables.records.SyncNodeRecord;

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
import org.jooq.types.UInteger;


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
public class SyncNode extends TableImpl<SyncNodeRecord> {

    private static final long serialVersionUID = -234513063;

    /**
     * The reference instance of <code>czztmp.sync_node</code>
     */
    public static final SyncNode SYNC_NODE = new SyncNode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SyncNodeRecord> getRecordType() {
        return SyncNodeRecord.class;
    }

    /**
     * The column <code>czztmp.sync_node.id</code>.
     */
    public final TableField<SyncNodeRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>czztmp.sync_node.node_id</code>.
     */
    public final TableField<SyncNodeRecord, Integer> NODE_ID = createField("node_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>czztmp.sync_node.is_validating</code>.
     */
    public final TableField<SyncNodeRecord, String> IS_VALIDATING = createField("is_validating", org.jooq.impl.SQLDataType.VARCHAR.length(45).nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>czztmp.sync_node.ping</code>.
     */
    public final TableField<SyncNodeRecord, Timestamp> PING = createField("ping", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>czztmp.sync_node.sync_start_full</code>.
     */
    public final TableField<SyncNodeRecord, Long> SYNC_START_FULL = createField("sync_start_full", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>czztmp.sync_node.sync_end_full</code>.
     */
    public final TableField<SyncNodeRecord, Long> SYNC_END_FULL = createField("sync_end_full", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>czztmp.sync_node.start_full_date</code>.
     */
    public final TableField<SyncNodeRecord, Timestamp> START_FULL_DATE = createField("start_full_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>czztmp.sync_node.end_full_date</code>.
     */
    public final TableField<SyncNodeRecord, Timestamp> END_FULL_DATE = createField("end_full_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>czztmp.sync_node.sync_start_solidity</code>.
     */
    public final TableField<SyncNodeRecord, Long> SYNC_START_SOLIDITY = createField("sync_start_solidity", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>czztmp.sync_node.sync_end_solidity</code>.
     */
    public final TableField<SyncNodeRecord, Long> SYNC_END_SOLIDITY = createField("sync_end_solidity", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>czztmp.sync_node.start_solidity_date</code>.
     */
    public final TableField<SyncNodeRecord, Timestamp> START_SOLIDITY_DATE = createField("start_solidity_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>czztmp.sync_node.end_solidity_date</code>.
     */
    public final TableField<SyncNodeRecord, Timestamp> END_SOLIDITY_DATE = createField("end_solidity_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>czztmp.sync_node</code> table reference
     */
    public SyncNode() {
        this("sync_node", null);
    }

    /**
     * Create an aliased <code>czztmp.sync_node</code> table reference
     */
    public SyncNode(String alias) {
        this(alias, SYNC_NODE);
    }

    private SyncNode(String alias, Table<SyncNodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private SyncNode(String alias, Table<SyncNodeRecord> aliased, Field<?>[] parameters) {
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
    public Identity<SyncNodeRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_SYNC_NODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SyncNodeRecord> getPrimaryKey() {
        return Keys.KEY_SYNC_NODE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SyncNodeRecord>> getKeys() {
        return Arrays.<UniqueKey<SyncNodeRecord>>asList(Keys.KEY_SYNC_NODE_PRIMARY, Keys.KEY_SYNC_NODE_NODE_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncNode as(String alias) {
        return new SyncNode(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SyncNode rename(String name) {
        return new SyncNode(name, null);
    }
}