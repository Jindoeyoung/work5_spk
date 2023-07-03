package com.spk.api.entity;

import lombok.Data;

@Data
public class ColumnElementM {
	private String apikey;
	private String tbl_nm;
	private String col_nm;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String col_desc;
	private String col_auth;
//	private String[] cols_nm;
}