package io.czz.explorer.dto.transaction;

import io.czz.explorer.dto.CommonCriteriaDTO;
import org.jooq.types.ULong;

import java.util.HashMap;
import java.util.Map;

public class TransferUTXOCriteria extends CommonCriteriaDTO {

	private Integer block;

	private String transHash;

	private String address;

	private Integer status;

	private ULong utxoId;

	@Override
	public Map<String, String> params() {

		HashMap<String, String> map = new HashMap<>();

		if (block!=null) {
			map.put("block", String.valueOf(block));
		}

		return map;
	}


	public Integer getBlock() {
		return block;
	}


	public void setBlock(Integer block) {
		this.block = block;
	}

	public String getTransHash() {
		return transHash;
	}

	public void setTransHash(String transHash) {
		this.transHash = transHash;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ULong getUtxoId() {
		return utxoId;
	}

	public void setUtxoId(ULong utxoId) {
		this.utxoId = utxoId;
	}
}
