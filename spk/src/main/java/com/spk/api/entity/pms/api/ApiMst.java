package com.spk.api.entity.pms.api;

import java.util.List;

import lombok.Data;

@Data
public class ApiMst {
	private String apikey;
	private String api_id;
	private String api_nm;
	private String method;
	private String uri;
	private String func_cd;
	private String rel_api_id;
	private String timeout;
	private List<ApiMstParam> api_param;
}
