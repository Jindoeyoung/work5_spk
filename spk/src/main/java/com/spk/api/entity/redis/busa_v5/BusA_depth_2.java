package com.spk.api.entity.redis.busa_v5;

import java.io.Serializable;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusA_depth_2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4082590613712694293L;
	private String type;
	private String method;
	private String uri;
	
	private String requiredTarget;
	private int[] permission;
	
	private String[] requiredParameter;
	BusA_depth_3 defaultParameter;
	private String timeout;
    
//  [ORIGIN]    
//	private String api;
//	private String apiMethod;
//    private String[] params;
//    BusA_depth_3 common;    
}
