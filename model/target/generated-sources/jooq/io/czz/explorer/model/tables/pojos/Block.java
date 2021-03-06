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
public class Block implements Serializable {

    private static final long serialVersionUID = 815749402;

    private ULong     id;
    private Timestamp createdTime;
    private ULong     height;
    private String    hash;
    private String    prevBlockHash;
    private String    nextBlockHash;
    private String    merkleRoot;
    private String    minerAddress;
    private Integer   txCount;
    private Double    difficulty;
    private Double    sumDifficulty;
    private Double    hashRate;
    private Double    transactionFees;
    private Double    outputTotal;
    private Long      size;
    private Long      version;
    private String    versionHex;
    private ULong     nonce;
    private ULong     bits;
    private Integer   confirmations;
    private Double    reward;
    private Integer   isMain;

    public Block() {}

    public Block(Block value) {
        this.id = value.id;
        this.createdTime = value.createdTime;
        this.height = value.height;
        this.hash = value.hash;
        this.prevBlockHash = value.prevBlockHash;
        this.nextBlockHash = value.nextBlockHash;
        this.merkleRoot = value.merkleRoot;
        this.minerAddress = value.minerAddress;
        this.txCount = value.txCount;
        this.difficulty = value.difficulty;
        this.sumDifficulty = value.sumDifficulty;
        this.hashRate = value.hashRate;
        this.transactionFees = value.transactionFees;
        this.outputTotal = value.outputTotal;
        this.size = value.size;
        this.version = value.version;
        this.versionHex = value.versionHex;
        this.nonce = value.nonce;
        this.bits = value.bits;
        this.confirmations = value.confirmations;
        this.reward = value.reward;
        this.isMain = value.isMain;
    }

    public Block(
        ULong     id,
        Timestamp createdTime,
        ULong     height,
        String    hash,
        String    prevBlockHash,
        String    nextBlockHash,
        String    merkleRoot,
        String    minerAddress,
        Integer   txCount,
        Double    difficulty,
        Double    sumDifficulty,
        Double    hashRate,
        Double    transactionFees,
        Double    outputTotal,
        Long      size,
        Long      version,
        String    versionHex,
        ULong     nonce,
        ULong     bits,
        Integer   confirmations,
        Double    reward,
        Integer   isMain
    ) {
        this.id = id;
        this.createdTime = createdTime;
        this.height = height;
        this.hash = hash;
        this.prevBlockHash = prevBlockHash;
        this.nextBlockHash = nextBlockHash;
        this.merkleRoot = merkleRoot;
        this.minerAddress = minerAddress;
        this.txCount = txCount;
        this.difficulty = difficulty;
        this.sumDifficulty = sumDifficulty;
        this.hashRate = hashRate;
        this.transactionFees = transactionFees;
        this.outputTotal = outputTotal;
        this.size = size;
        this.version = version;
        this.versionHex = versionHex;
        this.nonce = nonce;
        this.bits = bits;
        this.confirmations = confirmations;
        this.reward = reward;
        this.isMain = isMain;
    }

    public ULong getId() {
        return this.id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public ULong getHeight() {
        return this.height;
    }

    public void setHeight(ULong height) {
        this.height = height;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPrevBlockHash() {
        return this.prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public String getNextBlockHash() {
        return this.nextBlockHash;
    }

    public void setNextBlockHash(String nextBlockHash) {
        this.nextBlockHash = nextBlockHash;
    }

    public String getMerkleRoot() {
        return this.merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getMinerAddress() {
        return this.minerAddress;
    }

    public void setMinerAddress(String minerAddress) {
        this.minerAddress = minerAddress;
    }

    public Integer getTxCount() {
        return this.txCount;
    }

    public void setTxCount(Integer txCount) {
        this.txCount = txCount;
    }

    public Double getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public Double getSumDifficulty() {
        return this.sumDifficulty;
    }

    public void setSumDifficulty(Double sumDifficulty) {
        this.sumDifficulty = sumDifficulty;
    }

    public Double getHashRate() {
        return this.hashRate;
    }

    public void setHashRate(Double hashRate) {
        this.hashRate = hashRate;
    }

    public Double getTransactionFees() {
        return this.transactionFees;
    }

    public void setTransactionFees(Double transactionFees) {
        this.transactionFees = transactionFees;
    }

    public Double getOutputTotal() {
        return this.outputTotal;
    }

    public void setOutputTotal(Double outputTotal) {
        this.outputTotal = outputTotal;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getVersionHex() {
        return this.versionHex;
    }

    public void setVersionHex(String versionHex) {
        this.versionHex = versionHex;
    }

    public ULong getNonce() {
        return this.nonce;
    }

    public void setNonce(ULong nonce) {
        this.nonce = nonce;
    }

    public ULong getBits() {
        return this.bits;
    }

    public void setBits(ULong bits) {
        this.bits = bits;
    }

    public Integer getConfirmations() {
        return this.confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Double getReward() {
        return this.reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Integer getIsMain() {
        return this.isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Block (");

        sb.append(id);
        sb.append(", ").append(createdTime);
        sb.append(", ").append(height);
        sb.append(", ").append(hash);
        sb.append(", ").append(prevBlockHash);
        sb.append(", ").append(nextBlockHash);
        sb.append(", ").append(merkleRoot);
        sb.append(", ").append(minerAddress);
        sb.append(", ").append(txCount);
        sb.append(", ").append(difficulty);
        sb.append(", ").append(sumDifficulty);
        sb.append(", ").append(hashRate);
        sb.append(", ").append(transactionFees);
        sb.append(", ").append(outputTotal);
        sb.append(", ").append(size);
        sb.append(", ").append(version);
        sb.append(", ").append(versionHex);
        sb.append(", ").append(nonce);
        sb.append(", ").append(bits);
        sb.append(", ").append(confirmations);
        sb.append(", ").append(reward);
        sb.append(", ").append(isMain);

        sb.append(")");
        return sb.toString();
    }
}
