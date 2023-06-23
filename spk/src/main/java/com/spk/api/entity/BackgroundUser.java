package com.spk.api.entity;

import lombok.Data;

@Data
public class BackgroundUser {
	private String apikey;
	private String spike_id;
	private String background_id;
	private String reg_dt;
	private String reg_id;
	private String upt_dt;
	private String upt_id;
	private String background_id_before; // 이력에 insert 할, 이전 배경화면 id
}