package com.spk.api.entity.redis.usergrids_v5;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGrids_depth_4 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8951224740310813436L;
	private String if_id;
	private String api_if_id;
//	private String[] tbl_nm;	
}
