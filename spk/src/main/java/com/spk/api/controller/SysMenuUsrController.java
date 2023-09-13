package com.spk.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.SysMenuUsr;
import com.spk.api.service.sys.SysMenuUsrService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys", produces = "application/json; charset=utf8")
public class SysMenuUsrController {
	private final SysMenuUsrService sysMenuUsrService;

    //============================================================
    //< SELECT - 시스템메뉴사용자 리스트 조회
    //============================================================	
	@PostMapping("/getSysMenuUsrList")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuInfoList(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.getSysMenuUsrList(pSysMenuUsr);
	}	
}
