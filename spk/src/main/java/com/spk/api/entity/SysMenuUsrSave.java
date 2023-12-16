package com.spk.api.entity;

import java.util.List;

import lombok.Data;

@Data
public class SysMenuUsrSave {
	private String apikey;
	private String spike_id;
	List<SysMenuUsr> data;
}
