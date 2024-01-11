package com.spk.api.entity.pms.api;

import java.util.List;

import lombok.Data;

@Data
public class ApiMstList {
	private String apikey;
	private String spike_id;
	List<ApiMst> data;
}
