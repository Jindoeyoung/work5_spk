package com.spk.api.entity.redis.busa_v4;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusA_depth_1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3956940277654012034L;
	String componentId;
	String componentName;
	String[] methods;
	List<BusA_depth_2> behaviors;
}
