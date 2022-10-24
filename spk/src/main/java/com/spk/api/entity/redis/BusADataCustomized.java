package com.spk.api.entity.redis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusADataCustomized implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7432942738743371823L;
	BusADataCustomizedColumn column;
}
