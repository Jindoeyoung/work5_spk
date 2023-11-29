package com.spk.api.entity.redis.usergrids_v3;

import java.io.Serializable;
//import java.util.List;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGrids_depth_2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1632422706685055031L;
	String componentId;
	String componentName;
	String[] methods;
	List<UserGrids_depth_3> behaviors;   
}
