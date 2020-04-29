/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.records;


import io.czz.explorer.model.tables.SyncBlock;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
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
public class SyncBlockRecord extends UpdatableRecordImpl<SyncBlockRecord> implements Record6<ULong, ULong, Timestamp, Timestamp, Timestamp, Integer> {

    private static final long serialVersionUID = 767926461;

    /**
     * Setter for <code>czztmp.sync_block.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>czztmp.sync_block.block_num</code>.
     */
    public void setBlockNum(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.block_num</code>.
     */
    public ULong getBlockNum() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>czztmp.sync_block.date_start</code>.
     */
    public void setDateStart(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.date_start</code>.
     */
    public Timestamp getDateStart() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>czztmp.sync_block.date_end</code>.
     */
    public void setDateEnd(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.date_end</code>.
     */
    public Timestamp getDateEnd() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>czztmp.sync_block.date_locked</code>.
     */
    public void setDateLocked(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.date_locked</code>.
     */
    public Timestamp getDateLocked() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>czztmp.sync_block.node_id</code>.
     */
    public void setNodeId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>czztmp.sync_block.node_id</code>.
     */
    public Integer getNodeId() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<ULong, ULong, Timestamp, Timestamp, Timestamp, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<ULong, ULong, Timestamp, Timestamp, Timestamp, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return SyncBlock.SYNC_BLOCK.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field2() {
        return SyncBlock.SYNC_BLOCK.BLOCK_NUM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return SyncBlock.SYNC_BLOCK.DATE_START;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return SyncBlock.SYNC_BLOCK.DATE_END;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return SyncBlock.SYNC_BLOCK.DATE_LOCKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return SyncBlock.SYNC_BLOCK.NODE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value2() {
        return getBlockNum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getDateStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getDateEnd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getDateLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getNodeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value2(ULong value) {
        setBlockNum(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value3(Timestamp value) {
        setDateStart(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value4(Timestamp value) {
        setDateEnd(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value5(Timestamp value) {
        setDateLocked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord value6(Integer value) {
        setNodeId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SyncBlockRecord values(ULong value1, ULong value2, Timestamp value3, Timestamp value4, Timestamp value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SyncBlockRecord
     */
    public SyncBlockRecord() {
        super(SyncBlock.SYNC_BLOCK);
    }

    /**
     * Create a detached, initialised SyncBlockRecord
     */
    public SyncBlockRecord(ULong id, ULong blockNum, Timestamp dateStart, Timestamp dateEnd, Timestamp dateLocked, Integer nodeId) {
        super(SyncBlock.SYNC_BLOCK);

        set(0, id);
        set(1, blockNum);
        set(2, dateStart);
        set(3, dateEnd);
        set(4, dateLocked);
        set(5, nodeId);
    }
}
