package com.spk.api.entity.redis.busa_v4;

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
	private static final long serialVersionUID = 4859107020066141216L;
	private String user_id;
	private String if_id;
	private String[] tbl_nm;	
}
