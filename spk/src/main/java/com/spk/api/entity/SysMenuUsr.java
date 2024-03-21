package com.spk.api.entity;

import lombok.Data;

@Data
public class SysMenuUsr {
	private String apikey;
	private String spike_id;
	private String division;
	private String menu_id;
	private String seq;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String parent_menu_id;
	private String menu_nm;
	private String menu_desc;
	private String level;
	private String order_no;
	private String use_yn;
	private String json_data;
	private String isDirectory;
	private String dept;
	private String owner;
	
	// 시스템 메뉴 관리 기능 추가.20240314.PSD
	private String gubun;
	private String isdirectory;
	private String order_by;
}
