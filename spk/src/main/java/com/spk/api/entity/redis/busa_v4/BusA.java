package com.spk.api.entity.redis.busa_v4;

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
	private static final long serialVersionUID = 7112271663410035184L;
	String user_id;
	List<BusA_depth_1> flag_info;
}
