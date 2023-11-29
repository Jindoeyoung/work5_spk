package com.spk.api.entity.redis.userdetails_v3;

import java.io.Serializable;
//import java.util.List;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails_depth_2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5795832834861930747L;
	String componentId;
	String componentName;
	String[] methods;
	List<UserDetails_depth_3> behaviors;   
}
