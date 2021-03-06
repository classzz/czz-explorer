/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.records;


import io.czz.explorer.model.tables.BlockChain;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class BlockChainRecord extends UpdatableRecordImpl<BlockChainRecord> implements Record4<ULong, Timestamp, String, Integer> {

    private static final long serialVersionUID = 343606821;

    /**
     * Setter for <code>czztmp.block_chain.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>czztmp.block_chain.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>czztmp.block_chain.created_time</code>.
     */
    public void setCreatedTime(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>czztmp.block_chain.created_time</code>.
     */
    public Timestamp getCreatedTime() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>czztmp.block_chain.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>czztmp.block_chain.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>czztmp.block_chain.type</code>.
     */
    public void setType(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>czztmp.block_chain.type</code>.
     */
    public Integer getType() {
        return (Integer) get(3);
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
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<ULong, Timestamp, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<ULong, Timestamp, String, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return BlockChain.BLOCK_CHAIN.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return BlockChain.BLOCK_CHAIN.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return BlockChain.BLOCK_CHAIN.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return BlockChain.BLOCK_CHAIN.TYPE;
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
    public Timestamp value2() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChainRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChainRecord value2(Timestamp value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChainRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChainRecord value4(Integer value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockChainRecord values(ULong value1, Timestamp value2, String value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BlockChainRecord
     */
    public BlockChainRecord() {
        super(BlockChain.BLOCK_CHAIN);
    }

    /**
     * Create a detached, initialised BlockChainRecord
     */
    public BlockChainRecord(ULong id, Timestamp createdTime, String name, Integer type) {
        super(BlockChain.BLOCK_CHAIN);

        set(0, id);
        set(1, createdTime);
        set(2, name);
        set(3, type);
    }
}
