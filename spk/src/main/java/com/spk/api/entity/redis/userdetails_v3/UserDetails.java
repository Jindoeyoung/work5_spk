package com.spk.api.entity.redis.userdetails_v3;

import java.io.Serializable;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2012098288516685284L;
	String user_id;
	UserDetails_depth_1 data;
}
