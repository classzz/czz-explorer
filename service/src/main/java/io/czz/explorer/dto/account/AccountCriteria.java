package io.czz.explorer.dto.account;


import io.czz.explorer.dto.CommonCriteria;

public class AccountCriteria extends CommonCriteria {
	
	private String address;

	//trx only
	private boolean trx;

	private Integer transType;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isTrx() {
		return trx;
	}

	public void setTrx(boolean trx) {
		this.trx = trx;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}
}
