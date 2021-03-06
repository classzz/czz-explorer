/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.daos;


import io.czz.explorer.model.tables.TransferIn;
import io.czz.explorer.model.tables.records.TransferInRecord;

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
public class TransferInDao extends DAOImpl<TransferInRecord, io.czz.explorer.model.tables.pojos.TransferIn, ULong> {

    /**
     * Create a new TransferInDao without any configuration
     */
    public TransferInDao() {
        super(TransferIn.TRANSFER_IN, io.czz.explorer.model.tables.pojos.TransferIn.class);
    }

    /**
     * Create a new TransferInDao with an attached configuration
     */
    public TransferInDao(Configuration configuration) {
        super(TransferIn.TRANSFER_IN, io.czz.explorer.model.tables.pojos.TransferIn.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ULong getId(io.czz.explorer.model.tables.pojos.TransferIn object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchById(ULong... values) {
        return fetch(TransferIn.TRANSFER_IN.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public io.czz.explorer.model.tables.pojos.TransferIn fetchOneById(ULong value) {
        return fetchOne(TransferIn.TRANSFER_IN.ID, value);
    }

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByAmount(Double... values) {
        return fetch(TransferIn.TRANSFER_IN.AMOUNT, values);
    }

    /**
     * Fetch records that have <code>transaction_id IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByTransactionId(ULong... values) {
        return fetch(TransferIn.TRANSFER_IN.TRANSACTION_ID, values);
    }

    /**
     * Fetch records that have <code>transaction_hash IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByTransactionHash(String... values) {
        return fetch(TransferIn.TRANSFER_IN.TRANSACTION_HASH, values);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByAddress(String... values) {
        return fetch(TransferIn.TRANSFER_IN.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>sequence IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchBySequence(Long... values) {
        return fetch(TransferIn.TRANSFER_IN.SEQUENCE, values);
    }

    /**
     * Fetch records that have <code>asm IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByAsm(String... values) {
        return fetch(TransferIn.TRANSFER_IN.ASM, values);
    }

    /**
     * Fetch records that have <code>hex IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByHex(String... values) {
        return fetch(TransferIn.TRANSFER_IN.HEX, values);
    }

    /**
     * Fetch records that have <code>coin_base IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByCoinBase(String... values) {
        return fetch(TransferIn.TRANSFER_IN.COIN_BASE, values);
    }

    /**
     * Fetch records that have <code>vout IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.TransferIn> fetchByVout(Integer... values) {
        return fetch(TransferIn.TRANSFER_IN.VOUT, values);
    }
}
