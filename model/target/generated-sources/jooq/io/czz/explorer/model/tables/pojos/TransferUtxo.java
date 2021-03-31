/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

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
public class TransferUtxo implements Serializable {

    private static final long serialVersionUID = -971131739;

    private ULong   id;
    private Double  amount;
    private String  txHash;
    private String  address;
    private ULong   transferOutId;
    private Integer vout;
    private String  scriptPubKey;
    private Integer coinBase;
    private Integer status;

    public TransferUtxo() {}

    public TransferUtxo(TransferUtxo value) {
        this.id = value.id;
        this.amount = value.amount;
        this.txHash = value.txHash;
        this.address = value.address;
        this.transferOutId = value.transferOutId;
        this.vout = value.vout;
        this.scriptPubKey = value.scriptPubKey;
        this.coinBase = value.coinBase;
        this.status = value.status;
    }

    public TransferUtxo(
        ULong   id,
        Double  amount,
        String  txHash,
        String  address,
        ULong   transferOutId,
        Integer vout,
        String  scriptPubKey,
        Integer coinBase,
        Integer status
    ) {
        this.id = id;
        this.amount = amount;
        this.txHash = txHash;
        this.address = address;
        this.transferOutId = transferOutId;
        this.vout = vout;
        this.scriptPubKey = scriptPubKey;
        this.coinBase = coinBase;
        this.status = status;
    }

    public ULong getId() {
        return this.id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTxHash() {
        return this.txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ULong getTransferOutId() {
        return this.transferOutId;
    }

    public void setTransferOutId(ULong transferOutId) {
        this.transferOutId = transferOutId;
    }

    public Integer getVout() {
        return this.vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public String getScriptPubKey() {
        return this.scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Integer getCoinBase() {
        return this.coinBase;
    }

    public void setCoinBase(Integer coinBase) {
        this.coinBase = coinBase;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TransferUtxo (");

        sb.append(id);
        sb.append(", ").append(amount);
        sb.append(", ").append(txHash);
        sb.append(", ").append(address);
        sb.append(", ").append(transferOutId);
        sb.append(", ").append(vout);
        sb.append(", ").append(scriptPubKey);
        sb.append(", ").append(coinBase);
        sb.append(", ").append(status);

        sb.append(")");
        return sb.toString();
    }
}