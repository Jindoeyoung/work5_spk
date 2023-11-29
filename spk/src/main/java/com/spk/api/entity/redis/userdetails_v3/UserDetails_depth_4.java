package com.spk.api.entity.redis.userdetails_v3;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails_depth_4 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6363146853612155133L;
	private String user_id;
	private String if_id;
	private String[] tbl_nm;	
}
