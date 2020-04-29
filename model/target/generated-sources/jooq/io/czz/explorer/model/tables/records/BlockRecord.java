/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.records;


import io.czz.explorer.model.tables.Block;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record21;
import org.jooq.Row21;
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
public class BlockRecord extends UpdatableRecordImpl<BlockRecord> implements Record21<ULong, Timestamp, ULong, String, String, String, String, String, Integer, Double, Double, Double, Double, Double, Long, Long, String, ULong, ULong, Integer, Double> {

    private static final long serialVersionUID = 1791682053;

    /**
     * Setter for <code>czztmp.block.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>czztmp.block.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>czztmp.block.created_time</code>.
     */
    public void setCreatedTime(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>czztmp.block.created_time</code>.
     */
    public Timestamp getCreatedTime() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>czztmp.block.height</code>.
     */
    public void setHeight(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>czztmp.block.height</code>.
     */
    public ULong getHeight() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>czztmp.block.hash</code>.
     */
    public void setHash(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>czztmp.block.hash</code>.
     */
    public String getHash() {
        return (String) get(3);
    }

    /**
     * Setter for <code>czztmp.block.prev_block_hash</code>.
     */
    public void setPrevBlockHash(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>czztmp.block.prev_block_hash</code>.
     */
    public String getPrevBlockHash() {
        return (String) get(4);
    }

    /**
     * Setter for <code>czztmp.block.next_block_hash</code>.
     */
    public void setNextBlockHash(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>czztmp.block.next_block_hash</code>.
     */
    public String getNextBlockHash() {
        return (String) get(5);
    }

    /**
     * Setter for <code>czztmp.block.merkle_root</code>.
     */
    public void setMerkleRoot(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>czztmp.block.merkle_root</code>.
     */
    public String getMerkleRoot() {
        return (String) get(6);
    }

    /**
     * Setter for <code>czztmp.block.miner_address</code>.
     */
    public void setMinerAddress(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>czztmp.block.miner_address</code>.
     */
    public String getMinerAddress() {
        return (String) get(7);
    }

    /**
     * Setter for <code>czztmp.block.tx_count</code>.
     */
    public void setTxCount(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>czztmp.block.tx_count</code>.
     */
    public Integer getTxCount() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>czztmp.block.difficulty</code>.
     */
    public void setDifficulty(Double value) {
        set(9, value);
    }

    /**
     * Getter for <code>czztmp.block.difficulty</code>.
     */
    public Double getDifficulty() {
        return (Double) get(9);
    }

    /**
     * Setter for <code>czztmp.block.sum_difficulty</code>.
     */
    public void setSumDifficulty(Double value) {
        set(10, value);
    }

    /**
     * Getter for <code>czztmp.block.sum_difficulty</code>.
     */
    public Double getSumDifficulty() {
        return (Double) get(10);
    }

    /**
     * Setter for <code>czztmp.block.hash_rate</code>.
     */
    public void setHashRate(Double value) {
        set(11, value);
    }

    /**
     * Getter for <code>czztmp.block.hash_rate</code>.
     */
    public Double getHashRate() {
        return (Double) get(11);
    }

    /**
     * Setter for <code>czztmp.block.transaction_fees</code>.
     */
    public void setTransactionFees(Double value) {
        set(12, value);
    }

    /**
     * Getter for <code>czztmp.block.transaction_fees</code>.
     */
    public Double getTransactionFees() {
        return (Double) get(12);
    }

    /**
     * Setter for <code>czztmp.block.output_total</code>.
     */
    public void setOutputTotal(Double value) {
        set(13, value);
    }

    /**
     * Getter for <code>czztmp.block.output_total</code>.
     */
    public Double getOutputTotal() {
        return (Double) get(13);
    }

    /**
     * Setter for <code>czztmp.block.size</code>.
     */
    public void setSize(Long value) {
        set(14, value);
    }

    /**
     * Getter for <code>czztmp.block.size</code>.
     */
    public Long getSize() {
        return (Long) get(14);
    }

    /**
     * Setter for <code>czztmp.block.version</code>.
     */
    public void setVersion(Long value) {
        set(15, value);
    }

    /**
     * Getter for <code>czztmp.block.version</code>.
     */
    public Long getVersion() {
        return (Long) get(15);
    }

    /**
     * Setter for <code>czztmp.block.version_hex</code>.
     */
    public void setVersionHex(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>czztmp.block.version_hex</code>.
     */
    public String getVersionHex() {
        return (String) get(16);
    }

    /**
     * Setter for <code>czztmp.block.nonce</code>.
     */
    public void setNonce(ULong value) {
        set(17, value);
    }

    /**
     * Getter for <code>czztmp.block.nonce</code>.
     */
    public ULong getNonce() {
        return (ULong) get(17);
    }

    /**
     * Setter for <code>czztmp.block.bits</code>.
     */
    public void setBits(ULong value) {
        set(18, value);
    }

    /**
     * Getter for <code>czztmp.block.bits</code>.
     */
    public ULong getBits() {
        return (ULong) get(18);
    }

    /**
     * Setter for <code>czztmp.block.confirmations</code>.
     */
    public void setConfirmations(Integer value) {
        set(19, value);
    }

    /**
     * Getter for <code>czztmp.block.confirmations</code>.
     */
    public Integer getConfirmations() {
        return (Integer) get(19);
    }

    /**
     * Setter for <code>czztmp.block.reward</code>.
     */
    public void setReward(Double value) {
        set(20, value);
    }

    /**
     * Getter for <code>czztmp.block.reward</code>.
     */
    public Double getReward() {
        return (Double) get(20);
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
    // Record21 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row21<ULong, Timestamp, ULong, String, String, String, String, String, Integer, Double, Double, Double, Double, Double, Long, Long, String, ULong, ULong, Integer, Double> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row21<ULong, Timestamp, ULong, String, String, String, String, String, Integer, Double, Double, Double, Double, Double, Long, Long, String, ULong, ULong, Integer, Double> valuesRow() {
        return (Row21) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return Block.BLOCK.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Block.BLOCK.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field3() {
        return Block.BLOCK.HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Block.BLOCK.HASH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Block.BLOCK.PREV_BLOCK_HASH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Block.BLOCK.NEXT_BLOCK_HASH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Block.BLOCK.MERKLE_ROOT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Block.BLOCK.MINER_ADDRESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return Block.BLOCK.TX_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field10() {
        return Block.BLOCK.DIFFICULTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field11() {
        return Block.BLOCK.SUM_DIFFICULTY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field12() {
        return Block.BLOCK.HASH_RATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field13() {
        return Block.BLOCK.TRANSACTION_FEES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field14() {
        return Block.BLOCK.OUTPUT_TOTAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field15() {
        return Block.BLOCK.SIZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field16() {
        return Block.BLOCK.VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return Block.BLOCK.VERSION_HEX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field18() {
        return Block.BLOCK.NONCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field19() {
        return Block.BLOCK.BITS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field20() {
        return Block.BLOCK.CONFIRMATIONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field21() {
        return Block.BLOCK.REWARD;
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
    public ULong value3() {
        return getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getPrevBlockHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getNextBlockHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getMerkleRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getMinerAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getTxCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value10() {
        return getDifficulty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value11() {
        return getSumDifficulty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value12() {
        return getHashRate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value13() {
        return getTransactionFees();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value14() {
        return getOutputTotal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value15() {
        return getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value16() {
        return getVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getVersionHex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value18() {
        return getNonce();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value19() {
        return getBits();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value20() {
        return getConfirmations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value21() {
        return getReward();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value2(Timestamp value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value3(ULong value) {
        setHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value4(String value) {
        setHash(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value5(String value) {
        setPrevBlockHash(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value6(String value) {
        setNextBlockHash(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value7(String value) {
        setMerkleRoot(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value8(String value) {
        setMinerAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value9(Integer value) {
        setTxCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value10(Double value) {
        setDifficulty(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value11(Double value) {
        setSumDifficulty(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value12(Double value) {
        setHashRate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value13(Double value) {
        setTransactionFees(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value14(Double value) {
        setOutputTotal(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value15(Long value) {
        setSize(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value16(Long value) {
        setVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value17(String value) {
        setVersionHex(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value18(ULong value) {
        setNonce(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value19(ULong value) {
        setBits(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value20(Integer value) {
        setConfirmations(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord value21(Double value) {
        setReward(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockRecord values(ULong value1, Timestamp value2, ULong value3, String value4, String value5, String value6, String value7, String value8, Integer value9, Double value10, Double value11, Double value12, Double value13, Double value14, Long value15, Long value16, String value17, ULong value18, ULong value19, Integer value20, Double value21) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BlockRecord
     */
    public BlockRecord() {
        super(Block.BLOCK);
    }

    /**
     * Create a detached, initialised BlockRecord
     */
    public BlockRecord(ULong id, Timestamp createdTime, ULong height, String hash, String prevBlockHash, String nextBlockHash, String merkleRoot, String minerAddress, Integer txCount, Double difficulty, Double sumDifficulty, Double hashRate, Double transactionFees, Double outputTotal, Long size, Long version, String versionHex, ULong nonce, ULong bits, Integer confirmations, Double reward) {
        super(Block.BLOCK);

        set(0, id);
        set(1, createdTime);
        set(2, height);
        set(3, hash);
        set(4, prevBlockHash);
        set(5, nextBlockHash);
        set(6, merkleRoot);
        set(7, minerAddress);
        set(8, txCount);
        set(9, difficulty);
        set(10, sumDifficulty);
        set(11, hashRate);
        set(12, transactionFees);
        set(13, outputTotal);
        set(14, size);
        set(15, version);
        set(16, versionHex);
        set(17, nonce);
        set(18, bits);
        set(19, confirmations);
        set(20, reward);
    }
}
