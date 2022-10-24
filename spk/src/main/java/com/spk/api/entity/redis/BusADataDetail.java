package com.spk.api.entity.redis;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusADataDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7699296956137911676L;
	String componentId;
	String componentName;
	String[] methods;
	BusADataCustomized customized;
	String apiBySelf;
	String apiBySelfMethod;
	List<BusAApiByAction> apiByActions;
}