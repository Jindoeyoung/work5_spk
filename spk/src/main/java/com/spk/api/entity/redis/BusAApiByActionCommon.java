package com.spk.api.entity.redis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusAApiByActionCommon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3917763572388694639L;
	private String componentId;
    private int[] auths;
}
