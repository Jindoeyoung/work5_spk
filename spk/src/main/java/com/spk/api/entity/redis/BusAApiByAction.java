package com.spk.api.entity.redis;

import java.io.Serializable;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusAApiByAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679302573629872397L;
	private String api;
	private String apiMethod;
  private String[] params;
  BusAApiByActionCommon common;
//  List<BusAApiByActionCommon> common;
}
