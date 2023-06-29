package com.spk.api.entity;

import lombok.Data;

@Data
public class ColumnElement {
	private String apikey;
	private String tbl_nm;
	private String col_nm;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String col_desc;
	private String col_auth;
	private String element_typ;
	private String element_typ_nm;
	private String element_val;
	private String element_val_nm;
}