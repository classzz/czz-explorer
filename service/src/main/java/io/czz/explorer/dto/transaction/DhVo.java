package io.czz.explorer.dto.transaction;

import java.io.Serializable;

/**
 * 兑换vo
 * @Author: 创建人
 * @CreateDate: 2021/3/24 19:27
 * @UpdateUser: 更新人
 * @UpdateDate: 2021/3/24 19:27
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class DhVo implements Serializable {
    private String mid;
    private String asset_type;
    private String convert_type;
    private String pub_key;
    private String ext_tx_hash;
    private String tx_hash;
    private String confirm_ext_tx_hash;
    private String amount;
    private String fee_amount;
    private String to_token;

    public String getTx_hash() {
        return tx_hash;
    }

    public void setTx_hash(String tx_hash) {
        this.tx_hash = tx_hash;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getConvert_type() {
        return convert_type;
    }

    public void setConvert_type(String convert_type) {
        this.convert_type = convert_type;
    }

    public String getPub_key() {
        return pub_key;
    }

    public void setPub_key(String pub_key) {
        this.pub_key = pub_key;
    }

    public String getExt_tx_hash() {
        return ext_tx_hash;
    }

    public void setExt_tx_hash(String ext_tx_hash) {
        this.ext_tx_hash = ext_tx_hash;
    }

    public String getConfirm_ext_tx_hash() {
        return confirm_ext_tx_hash;
    }

    public void setConfirm_ext_tx_hash(String confirm_ext_tx_hash) {
        this.confirm_ext_tx_hash = confirm_ext_tx_hash;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getTo_token() {
        return to_token;
    }

    public void setTo_token(String to_token) {
        this.to_token = to_token;
    }
}
