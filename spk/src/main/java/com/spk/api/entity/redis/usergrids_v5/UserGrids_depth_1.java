package com.spk.api.entity.redis.usergrids_v5;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGrids_depth_1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2707065220607539626L;
	List<UserGrids_depth_2> result;
}
