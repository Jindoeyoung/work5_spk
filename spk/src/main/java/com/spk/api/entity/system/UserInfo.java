package com.spk.api.entity.system;

import lombok.Data;

@Data
public class UserInfo {
	private String apikey;
	private String user_id;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String user_nm;
	private String div_cd;
	private String user_grp_cd;
	private String depart_cd;
	private String rank_cd;
	private String charge_cd;
	private String sabun;	
}