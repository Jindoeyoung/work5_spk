package com.spk.api.entity.redis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusADataCustomizedColumn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3331138720925468909L;
	private String hakbeon;
	private String profile;
	private String jumin_no;
	private String birthday;
	private String h_name;
	private String c_nane;
	private String gukga;
	private String e_name_last;
	private String e_name;
	private String sangtae;
	private String byeondongsayu;
	private String byeondong_ilja;
	private String gwajeong_gb;
	private String juya_gb;
	private String hakgwa;
	private String jeongong;
	private String haknyeon;
	private String ban;
	private String hakgi;
	private String isuhakgi;
	private String iphak_hakgwa;
	private String jeonhyeong_gb;
	private String wetak_company;
	private String iphak_ilja;
	private String joleop_haknyeon;
	private String joleop_hakgwa;
	private String iphak_juya;
	private String iphak_gb;
	private String byunguk_gb;
	private String joleop_jeungseo_no;
	private String hakwi_no;
	private String imsi_hakjeok;
	private String bokhakyejeong_ilja;
	private String sex;
	private String bigo;
	private String year;
}