package com.spk.api.entity;

import lombok.Data;

@Data
public class ColumnElementVal {
	private String apikey;
	private String tbl_nm;
	private String col_nm;
	private String element_typ;
	private String element_val;	
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String element_val_nm;
}