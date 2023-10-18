package com.spk.api.entity.colele;

import lombok.Data;

@Data
public class ColumnElementTypeEntity {
	private String apikey;
	private String tbl_nm;
	private String col_nm;
	private String element_typ;	
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String element_typ_nm;
	private String sort_seq;
}