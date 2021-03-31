/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.daos;


import io.czz.explorer.model.tables.BlockChain;
import io.czz.explorer.model.tables.records.BlockChainRecord;

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
public class BlockChainDao extends DAOImpl<BlockChainRecord, io.czz.explorer.model.tables.pojos.BlockChain, ULong> {

    /**
     * Create a new BlockChainDao without any configuration
     */
    public BlockChainDao() {
        super(BlockChain.BLOCK_CHAIN, io.czz.explorer.model.tables.pojos.BlockChain.class);
    }

    /**
     * Create a new BlockChainDao with an attached configuration
     */
    public BlockChainDao(Configuration configuration) {
        super(BlockChain.BLOCK_CHAIN, io.czz.explorer.model.tables.pojos.BlockChain.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ULong getId(io.czz.explorer.model.tables.pojos.BlockChain object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.BlockChain> fetchById(ULong... values) {
        return fetch(BlockChain.BLOCK_CHAIN.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public io.czz.explorer.model.tables.pojos.BlockChain fetchOneById(ULong value) {
        return fetchOne(BlockChain.BLOCK_CHAIN.ID, value);
    }

    /**
     * Fetch records that have <code>created_time IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.BlockChain> fetchByCreatedTime(Timestamp... values) {
        return fetch(BlockChain.BLOCK_CHAIN.CREATED_TIME, values);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.BlockChain> fetchByName(String... values) {
        return fetch(BlockChain.BLOCK_CHAIN.NAME, values);
    }

    /**
     * Fetch records that have <code>type IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.BlockChain> fetchByType(Integer... values) {
        return fetch(BlockChain.BLOCK_CHAIN.TYPE, values);
    }
}