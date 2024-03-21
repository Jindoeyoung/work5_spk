package com.spk.api.entity;

import java.util.List;

import lombok.Data;

@Data
public class SysMenuSaveDetailUsr {
	private String flag;
	private List<SysMenuUsr> data;
}
