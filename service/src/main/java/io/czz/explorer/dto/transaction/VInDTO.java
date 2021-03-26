package io.czz.explorer.dto.transaction;

import java.math.BigDecimal;

public class VInDTO {

    private String coinbase;

    private String txid;

    private Integer vout;

    private TransferInDTO scriptSig;

    private Long sequence;

    private BigDecimal value;

    private String address;


    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public TransferInDTO getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(TransferInDTO scriptSig) {
        this.scriptSig = scriptSig;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
