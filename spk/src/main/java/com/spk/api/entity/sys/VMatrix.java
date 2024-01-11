package com.spk.api.entity.sys;

import lombok.Data;
//23.01.08 timeout 추가

@Data
public class VMatrix {
	private String apikey;
	private String gubun;
	private String spike_id;
	private String widget_func_id;
//	private String widget_grp_id;
//	private String func_id;
	private String seq;
	private String widget_nm;
	private String methods;
	private String type;
	private String method;
	private String uri;
	private String default_param;
	private String default_param_value;
	private String type_a;
	private String method_a;
	private String uri_a;
	private String default_param_value_a;
	private String required_param;
	private String required_target;
	private String permission;
	private String timeout;
}