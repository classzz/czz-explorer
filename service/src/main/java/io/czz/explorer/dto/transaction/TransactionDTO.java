package io.czz.explorer.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jooq.types.ULong;

import java.sql.Timestamp;
import java.util.List;

public class TransactionDTO {

    private ULong id;
    private String    txid;
    private Integer blockHeight;
    private String    blockhash;
    private String minerAddress;
    private double transFees;
    private Long blockCreatedTime;
    private Long createdTime;
    @JsonIgnore
    private Integer blocktime;
    @JsonIgnore
    private Integer time;
    private Integer locktime;
    private Long      size;
    private Integer   confirmations;
    private Integer   version;
    private String    hex;
    private List<VInDTO> vin;
    private List<VOutDTO> vout;
    private double totalInput;
    private double totalOutput;
    private boolean bStop;
    private Integer resTotal;
    private String input;

    public ULong getId() {
        return id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public double getTransFees() {
        return transFees;
    }

    public void setTransFees(double transFees) {
        this.transFees = transFees;
    }

    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Integer getBlockHeight() {
        return blockHeight;
    }

    public String getMinerAddress() {
        return minerAddress;
    }

    public void setMinerAddress(String minerAddress) {
        this.minerAddress = minerAddress;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Integer getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Integer blocktime) {
        this.blocktime = blocktime;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLocktime() {
        return locktime;
    }

    public void setLocktime(Integer locktime) {
        this.locktime = locktime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public List<VInDTO> getVin() {
        return vin;
    }

    public void setVin(List<VInDTO> vin) {
        this.vin = vin;
    }

    public List<VOutDTO> getVout() {
        return vout;
    }

    public void setVout(List<VOutDTO> vout) {
        this.vout = vout;
    }

    public Long getBlockCreatedTime() {
        return blockCreatedTime;
    }

    public void setBlockCreatedTime(Long blockCreatedTime) {
        this.blockCreatedTime = blockCreatedTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public double getTotalInput() {
        return totalInput;
    }

    public void setTotalInput(double totalInput) {
        this.totalInput = totalInput;
    }

    public double getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public boolean isbStop() {
        return bStop;
    }

    public void setbStop(boolean bStop) {
        this.bStop = bStop;
    }

    public Integer getResTotal() {
        return resTotal;
    }

    public void setResTotal(Integer resTotal) {
        this.resTotal = resTotal;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
