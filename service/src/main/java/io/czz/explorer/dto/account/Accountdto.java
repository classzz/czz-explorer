package io.czz.explorer.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Accountdto {


    @JsonIgnore
    private Long id;

    /**
     * 账户余额
     */
    private double balance;

    private double totalInput;

    private double totalOutput;

    private Integer txCount;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public Integer getTxCount() {
        return txCount;
    }

    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }
}
