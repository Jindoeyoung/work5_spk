package com.spk.api.entity;

import lombok.Data;

@Data
public class PjtCom {
	private String apikey;
	private String com_id;
	private String com_nm;
	private String dev_fr_dt;
	private String dev_to_dt;
	private String requester;
	private String owner;
	private String participant;
	private String scenario;
	private String scen_cnt;
	private String partici_cnt;
	private String proc_state;
	private Integer proc_rate;	
	
}
