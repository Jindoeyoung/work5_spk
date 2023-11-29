package com.spk.api.entity.redis.userdetails_v3;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails_depth_3 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1712636737503580465L;
	private String type;
	private String method;
	private String uri;
	
	private String requiredTarget;
	private int[] permission;
	
	private String[] requiredParameter;
	UserDetails_depth_4 defaultParameter;
}
