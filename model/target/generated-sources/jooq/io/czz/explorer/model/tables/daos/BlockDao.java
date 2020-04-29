/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.daos;


import io.czz.explorer.model.tables.Block;
import io.czz.explorer.model.tables.records.BlockRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
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
public class BlockDao extends DAOImpl<BlockRecord, io.czz.explorer.model.tables.pojos.Block, ULong> {

    /**
     * Create a new BlockDao without any configuration
     */
    public BlockDao() {
        super(Block.BLOCK, io.czz.explorer.model.tables.pojos.Block.class);
    }

    /**
     * Create a new BlockDao with an attached configuration
     */
    public BlockDao(Configuration configuration) {
        super(Block.BLOCK, io.czz.explorer.model.tables.pojos.Block.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ULong getId(io.czz.explorer.model.tables.pojos.Block object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchById(ULong... values) {
        return fetch(Block.BLOCK.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public io.czz.explorer.model.tables.pojos.Block fetchOneById(ULong value) {
        return fetchOne(Block.BLOCK.ID, value);
    }

    /**
     * Fetch records that have <code>created_time IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByCreatedTime(Timestamp... values) {
        return fetch(Block.BLOCK.CREATED_TIME, values);
    }

    /**
     * Fetch records that have <code>height IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByHeight(ULong... values) {
        return fetch(Block.BLOCK.HEIGHT, values);
    }

    /**
     * Fetch a unique record that has <code>height = value</code>
     */
    public io.czz.explorer.model.tables.pojos.Block fetchOneByHeight(ULong value) {
        return fetchOne(Block.BLOCK.HEIGHT, value);
    }

    /**
     * Fetch records that have <code>hash IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByHash(String... values) {
        return fetch(Block.BLOCK.HASH, values);
    }

    /**
     * Fetch records that have <code>prev_block_hash IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByPrevBlockHash(String... values) {
        return fetch(Block.BLOCK.PREV_BLOCK_HASH, values);
    }

    /**
     * Fetch records that have <code>next_block_hash IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByNextBlockHash(String... values) {
        return fetch(Block.BLOCK.NEXT_BLOCK_HASH, values);
    }

    /**
     * Fetch records that have <code>merkle_root IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByMerkleRoot(String... values) {
        return fetch(Block.BLOCK.MERKLE_ROOT, values);
    }

    /**
     * Fetch records that have <code>miner_address IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByMinerAddress(String... values) {
        return fetch(Block.BLOCK.MINER_ADDRESS, values);
    }

    /**
     * Fetch records that have <code>tx_count IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByTxCount(Integer... values) {
        return fetch(Block.BLOCK.TX_COUNT, values);
    }

    /**
     * Fetch records that have <code>difficulty IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByDifficulty(Double... values) {
        return fetch(Block.BLOCK.DIFFICULTY, values);
    }

    /**
     * Fetch records that have <code>sum_difficulty IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchBySumDifficulty(Double... values) {
        return fetch(Block.BLOCK.SUM_DIFFICULTY, values);
    }

    /**
     * Fetch records that have <code>hash_rate IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByHashRate(Double... values) {
        return fetch(Block.BLOCK.HASH_RATE, values);
    }

    /**
     * Fetch records that have <code>transaction_fees IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByTransactionFees(Double... values) {
        return fetch(Block.BLOCK.TRANSACTION_FEES, values);
    }

    /**
     * Fetch records that have <code>output_total IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByOutputTotal(Double... values) {
        return fetch(Block.BLOCK.OUTPUT_TOTAL, values);
    }

    /**
     * Fetch records that have <code>size IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchBySize(Long... values) {
        return fetch(Block.BLOCK.SIZE, values);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByVersion(Long... values) {
        return fetch(Block.BLOCK.VERSION, values);
    }

    /**
     * Fetch records that have <code>version_hex IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByVersionHex(String... values) {
        return fetch(Block.BLOCK.VERSION_HEX, values);
    }

    /**
     * Fetch records that have <code>nonce IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByNonce(ULong... values) {
        return fetch(Block.BLOCK.NONCE, values);
    }

    /**
     * Fetch records that have <code>bits IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByBits(ULong... values) {
        return fetch(Block.BLOCK.BITS, values);
    }

    /**
     * Fetch records that have <code>confirmations IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByConfirmations(Integer... values) {
        return fetch(Block.BLOCK.CONFIRMATIONS, values);
    }

    /**
     * Fetch records that have <code>reward IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Block> fetchByReward(Double... values) {
        return fetch(Block.BLOCK.REWARD, values);
    }
}
