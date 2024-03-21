package com.spk.api.entity;

import java.util.List;

import lombok.Data;

@Data
public class SysMenuSaveUsr {
	private String apikey;
	private List<SysMenuSaveDetailUsr> data;
}
