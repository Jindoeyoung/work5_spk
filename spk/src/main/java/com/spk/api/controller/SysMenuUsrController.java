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
	public String getMenuUsrList(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.getSysMenuUsrList(pSysMenuUsr);
	}
	
    //============================================================
    //< SELECT - 시스템메뉴사용자 리스트 조회 (부서별 메뉴조회 적용)
    //============================================================	
	@PostMapping("/getSysMenuUsrList-v2")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuUsrList2(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.getSysMenuUsrList2(pSysMenuUsr);
	}
	
    //============================================================
    //< SELECT - 시스템메뉴사용자 리스트 조회 (OWNER 추가)
    //============================================================	
	@PostMapping("/getSysMenuUsrList-v3")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuUsrList3(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.getSysMenuUsrList3(pSysMenuUsr);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴정보 상세 조회
    //============================================================	
	@PostMapping("/getSysMenuUsr")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getSysMenuUsr(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.getSysMenuUsrInfo(pSysMenuUsr);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴정보 상세 조회
    //============================================================	
	@PostMapping("/updateSysMenuUsr")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertSysMenuUsr(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
		return sysMenuUsrService.updateSysMenuUsr(pSysMenuUsr);
	}	
	
}
