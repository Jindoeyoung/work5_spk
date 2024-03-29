package com.spk.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.SysMenuInfo;
import com.spk.api.entity.SysMenuSaveUsr;
import com.spk.api.service.sys.SysMenuInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys", produces = "application/json; charset=utf8")
public class SysMenuInfoController {
	private final SysMenuInfoService sysMenuInfoService;

    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	@PostMapping("/getSysMenuInfoList")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuInfoList(@RequestBody SysMenuInfo pSysMenuInfo) throws Exception {
		return sysMenuInfoService.getSysMenuInfoList(pSysMenuInfo);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴정보 상세 조회
    //============================================================	
	@PostMapping("/getSysMenuInfo")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuInfo(@RequestBody SysMenuInfo pSysMenuInfo) throws Exception {
		return sysMenuInfoService.getSysMenuInfo(pSysMenuInfo);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴정보 저장
    //============================================================	
	@PostMapping("/insertMenuInfo")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertMenuInfo(@RequestBody SysMenuInfo pSysMenuInfo) throws Exception {
		return sysMenuInfoService.insertSysMenuInfo(pSysMenuInfo);
	}
	
    //============================================================
    //< SELECT - 시스템메뉴정보 저장/상위메뉴 USE_YN 업데이트
    //============================================================
	@PostMapping("/insertMenuInfo-v2")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertSysMenuInfoUpdate(@RequestBody SysMenuInfo pSysMenuInfo) throws Exception {
		return sysMenuInfoService.insertSysMenuInfoUpdate(pSysMenuInfo);
	}	

}
