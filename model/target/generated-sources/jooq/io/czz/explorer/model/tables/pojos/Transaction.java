/*
 * This file is generated by jOOQ.
*/
package io.czz.explorer.model.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

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
public class Transaction implements Serializable {

    private static final long serialVersionUID = -1823559384;

    private ULong     id;
    private String    hash;
    private String    blockHash;
    private Timestamp blockTime;
    private Timestamp createdTime;
    private Long      size;
    private Long      weight;
    private Double    fees;
    private Double    totalInput;
    private Double    totalOutput;
    private Integer   type;
    private Integer   confirmations;
    private Integer   version;
    private String    hex;

    public Transaction() {}

    public Transaction(Transaction value) {
        this.id = value.id;
        this.hash = value.hash;
        this.blockHash = value.blockHash;
        this.blockTime = value.blockTime;
        this.createdTime = value.createdTime;
        this.size = value.size;
        this.weight = value.weight;
        this.fees = value.fees;
        this.totalInput = value.totalInput;
        this.totalOutput = value.totalOutput;
        this.type = value.type;
        this.confirmations = value.confirmations;
        this.version = value.version;
        this.hex = value.hex;
    }

    public Transaction(
        ULong     id,
        String    hash,
        String    blockHash,
        Timestamp blockTime,
        Timestamp createdTime,
        Long      size,
        Long      weight,
        Double    fees,
        Double    totalInput,
        Double    totalOutput,
        Integer   type,
        Integer   confirmations,
        Integer   version,
        String    hex
    ) {
        this.id = id;
        this.hash = hash;
        this.blockHash = blockHash;
        this.blockTime = blockTime;
        this.createdTime = createdTime;
        this.size = size;
        this.weight = weight;
        this.fees = fees;
        this.totalInput = totalInput;
        this.totalOutput = totalOutput;
        this.type = type;
        this.confirmations = confirmations;
        this.version = version;
        this.hex = hex;
    }

    public ULong getId() {
        return this.id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBlockHash() {
        return this.blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public Timestamp getBlockTime() {
        return this.blockTime;
    }

    public void setBlockTime(Timestamp blockTime) {
        this.blockTime = blockTime;
    }

    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getWeight() {
        return this.weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Double getFees() {
        return this.fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Double getTotalInput() {
        return this.totalInput;
    }

    public void setTotalInput(Double totalInput) {
        this.totalInput = totalInput;
    }

    public Double getTotalOutput() {
        return this.totalOutput;
    }

    public void setTotalOutput(Double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getConfirmations() {
        return this.confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getHex() {
        return this.hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transaction (");

        sb.append(id);
        sb.append(", ").append(hash);
        sb.append(", ").append(blockHash);
        sb.append(", ").append(blockTime);
        sb.append(", ").append(createdTime);
        sb.append(", ").append(size);
        sb.append(", ").append(weight);
        sb.append(", ").append(fees);
        sb.append(", ").append(totalInput);
        sb.append(", ").append(totalOutput);
        sb.append(", ").append(type);
        sb.append(", ").append(confirmations);
        sb.append(", ").append(version);
        sb.append(", ").append(hex);

        sb.append(")");
        return sb.toString();
    }
}