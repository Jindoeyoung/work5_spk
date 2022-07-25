package com.spk.api.entity;

//import java.util.Date;
//import org.springframework.format.annotation.DateTimeFormat; // DateTimeFormat 적용 시

import lombok.Data;

@Data
public class ApiMst {
	private String api_id;
	
//	private DateTimeFormat reg_dt;
//	private Date reg_dt;
	private String reg_dt;
	
	private String reg_id;
	
//	private DateTimeFormat upt_dt;
//	private Date upt_dt;
	private String upt_dt;
	
	private String upt_id;
	private String api_nm;
	private String api_cate;
	private String version;
	private String param;
	private String res_form;
	private String rtn_type;
	private String method;
	private String url;
	private String proc_state;
	private Integer proc_rate;
	private String dev_fr_dt;
	private String dev_to_dt;
	private String requester;
	private String owner;
	private String developer;
	private String participant;
	private String scenario;
}
