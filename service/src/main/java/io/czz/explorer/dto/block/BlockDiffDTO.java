package io.czz.explorer.dto.block;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

public class BlockDiffDTO {

    private Long createdTime;
    private Integer height;
    private String hash;
    private Double difficulty;
    private Double hashRate;


    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public Double getHashRate() {
        return hashRate;
    }

    public void setHashRate(Double hashRate) {
        this.hashRate = hashRate;
    }
}
