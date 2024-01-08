package com.spk.api.entity.redis.busa_v5;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusA_depth_3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5964918111702555907L;
	private String user_id;
	private String if_id;
	private String api_if_id;
//	private String[] tbl_nm;	
	
//  [ORIGIN]	
//	private String componentId;
//    private int[] auths;
}
