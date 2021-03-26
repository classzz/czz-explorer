package io.czz.explorer.dto.transaction;

import org.jooq.types.ULong;

/**
 * @author xingkong
 * @date 19-9-23 下午5:46
 */
public class TransferUtxoDTO {


    private ULong id;
    private Double  amount;
    private String  txHash;
    private String  address;
    private ULong   transferOutId;
    private Integer vout;
    private String  scriptPubKey;
    private Integer status;
    private Long blockHeight;
    private Integer coinBase;

    public ULong getId() {
        return id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ULong getTransferOutId() {
        return transferOutId;
    }

    public void setTransferOutId(ULong transferOutId) {
        this.transferOutId = transferOutId;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Integer getCoinBase() {
        return coinBase;
    }

    public void setCoinBase(Integer coinBase) {
        this.coinBase = coinBase;
    }
}
