package com.spk.api.entity;

import lombok.Data;

@Data
public class ApiScen {
	private String api_scen_id;
	private String api_id;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String func_nm;
	private String dev_fr_dt;
	private String dev_to_dt;
}
