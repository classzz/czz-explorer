/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.daos;


import io.czz.explorer.model.tables.Account;
import io.czz.explorer.model.tables.records.AccountRecord;

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
public class AccountDao extends DAOImpl<AccountRecord, io.czz.explorer.model.tables.pojos.Account, ULong> {

    /**
     * Create a new AccountDao without any configuration
     */
    public AccountDao() {
        super(Account.ACCOUNT, io.czz.explorer.model.tables.pojos.Account.class);
    }

    /**
     * Create a new AccountDao with an attached configuration
     */
    public AccountDao(Configuration configuration) {
        super(Account.ACCOUNT, io.czz.explorer.model.tables.pojos.Account.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ULong getId(io.czz.explorer.model.tables.pojos.Account object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchById(ULong... values) {
        return fetch(Account.ACCOUNT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public io.czz.explorer.model.tables.pojos.Account fetchOneById(ULong value) {
        return fetchOne(Account.ACCOUNT.ID, value);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByAddress(String... values) {
        return fetch(Account.ACCOUNT.ADDRESS, values);
    }

    /**
     * Fetch a unique record that has <code>address = value</code>
     */
    public io.czz.explorer.model.tables.pojos.Account fetchOneByAddress(String value) {
        return fetchOne(Account.ACCOUNT.ADDRESS, value);
    }

    /**
     * Fetch records that have <code>balance IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByBalance(Double... values) {
        return fetch(Account.ACCOUNT.BALANCE, values);
    }

    /**
     * Fetch records that have <code>created_time IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByCreatedTime(Timestamp... values) {
        return fetch(Account.ACCOUNT.CREATED_TIME, values);
    }

    /**
     * Fetch records that have <code>updated_time IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByUpdatedTime(Timestamp... values) {
        return fetch(Account.ACCOUNT.UPDATED_TIME, values);
    }

    /**
     * Fetch records that have <code>tx_count IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByTxCount(Integer... values) {
        return fetch(Account.ACCOUNT.TX_COUNT, values);
    }

    /**
     * Fetch records that have <code>total_input IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByTotalInput(Double... values) {
        return fetch(Account.ACCOUNT.TOTAL_INPUT, values);
    }

    /**
     * Fetch records that have <code>total_output IN (values)</code>
     */
    public List<io.czz.explorer.model.tables.pojos.Account> fetchByTotalOutput(Double... values) {
        return fetch(Account.ACCOUNT.TOTAL_OUTPUT, values);
    }
}
