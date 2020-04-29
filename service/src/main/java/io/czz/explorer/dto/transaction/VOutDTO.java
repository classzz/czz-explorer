package io.czz.explorer.dto.transaction;

import java.math.BigDecimal;

public class VOutDTO {

    private BigDecimal value;
    private TransferOutDTO  scriptPubKey;
    private Integer n;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransferOutDTO getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(TransferOutDTO scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
}
