package io.czz.explorer.dto.block;

import io.czz.explorer.dto.CommonCriteriaDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BlockCriteriaDTO extends CommonCriteriaDTO {
	
	private String producedBy;

	private Integer height;

	private String hash;
	
	@Override
	public Map<String, String> params() {
		
		HashMap<String, String> map = new HashMap<>();
		
		if (StringUtils.isNotBlank(this.producedBy)) {
			map.put("producedBy", this.producedBy);
		}
		
		return map;
	}
	
	public String getProducedBy() {
		return producedBy;
	}
	
	public void setProducedBy(String producedBy) {
		this.producedBy = producedBy;
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
}
