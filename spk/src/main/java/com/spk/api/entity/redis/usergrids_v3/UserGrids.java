package com.spk.api.entity.redis.usergrids_v3;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGrids implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1608647576965885507L;
	String user_id;
	UserGrids_depth_1 data;
}
