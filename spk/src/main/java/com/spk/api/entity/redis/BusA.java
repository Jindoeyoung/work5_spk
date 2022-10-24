package com.spk.api.entity.redis;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusA implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -110116618420616571L;
	String user_id;
	List<BusADataDetail> flag_info;
}
