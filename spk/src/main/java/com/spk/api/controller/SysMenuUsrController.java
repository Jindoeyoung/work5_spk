package com.spk.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.SysMenuUsr;
import com.spk.api.entity.SysMenuUsrSave;
import com.spk.api.service.sys.SysMenuUsrService;
import com.spk.api.util.ReturnException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys", produces = "application/json; charset=utf8")
public class SysMenuUsrController {
	private final SysMenuUsrService sysMenuUsrService;
	
    //============================================================
    //< SELECT - 시스템메뉴 리스트 조회
    //============================================================	
	@PostMapping("/getSysMenuUsrList-web")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuUsrList(@RequestBody SysMenuUsr pSysMenuUsr) throws Exception {
//		return sysMenuUsrService.getSysMenuUsrList_GR2(pSysMenuUsr);		
		return sysMenuUsrService.getSysMenuUsrList(pSysMenuUsr);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴정보 UseYn 업데이트
    //============================================================	
	@PutMapping("/updateSysMenuUseYn")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertSysMenuUsr(@RequestBody SysMenuUsrSave sysMenuUsrSave) throws Exception {
		try {
			return sysMenuUsrService.updateSysMenuUsr(sysMenuUsrSave);
		} catch (ReturnException e) {
			return (String)e.getValue();
		}
		
	}
}
