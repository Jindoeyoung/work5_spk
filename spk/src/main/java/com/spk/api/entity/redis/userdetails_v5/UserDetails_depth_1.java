package com.spk.api.entity.redis.userdetails_v5;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails_depth_1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5938728700277392388L;
	List<UserDetails_depth_2> result;
}
