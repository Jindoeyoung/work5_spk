package com.spk.api.entity;

import lombok.Data;

@Data
public class ComMst {
	private String com_id;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String com_nm;
	private String com_cate;
	private String com_attr;
	private String com_form;
	private String com_src;
	private String dev_fr_dt;
	private String dev_to_dt;
	private String use_fr_dt;
	private String use_to_dt;
	private String requester;
	private String owner;
	private String developer;
	private String participant;
	private String scenario;	
}