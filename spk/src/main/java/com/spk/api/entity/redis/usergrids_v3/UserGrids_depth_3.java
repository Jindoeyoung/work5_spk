package com.spk.api.entity.redis.usergrids_v3;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGrids_depth_3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -238453474161417170L;
	private String type;
	private String method;
	private String uri;
	
	private String requiredTarget;
	private int[] permission;
	
	private String[] requiredParameter;
	UserGrids_depth_4 defaultParameter;
}
