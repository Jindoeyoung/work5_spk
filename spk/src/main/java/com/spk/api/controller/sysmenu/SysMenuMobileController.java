package com.spk.api.controller.sysmenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.sysmenu.SysMenuMobile;
import com.spk.api.service.sysmenu.SysMenuMobileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys", produces = "application/json; charset=utf8")
public class SysMenuMobileController {
	
	@Autowired
	private SysMenuMobileService sysMenuMobileService;	
//	private final SysMenuMobileService sysMenuMobileService;

    //============================================================
    //< SELECT - 시스템메뉴모바일 리스트 조회
    //============================================================	
	@PostMapping("/getSysMenuMobileList")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuInfoList(@RequestBody SysMenuMobile pSysMenuMobile) throws Exception {
		return sysMenuMobileService.getSysMenuMobileList(pSysMenuMobile);
	}	
	
    //============================================================
    //< SELECT - 시스템메뉴모바일 상세 조회
    //============================================================	
	@PostMapping("/getSysMenuMobile")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getSysMenuMobile(@RequestBody SysMenuMobile pSysMenuMobile) throws Exception {
		return sysMenuMobileService.getSysMenuMobile(pSysMenuMobile);
	}	
	
    //============================================================
    //< INSERT - 시스템메뉴모바일 저장
    //============================================================	
	@PostMapping("/insertSysMenuMobile")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertSysMenuMobile(@RequestBody SysMenuMobile pSysMenuMobile) throws Exception {
		return sysMenuMobileService.insertSysMenuMobile(pSysMenuMobile);
	}
	
    //============================================================
    //< UPDATE - 시스템메뉴모바일 수정
    //============================================================	
	@PutMapping("/updateSysMenuMobile")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String updateSysMenuMobile(@RequestBody SysMenuMobile pSysMenuMobile) throws Exception {
		return sysMenuMobileService.updateSysMenuMobile(pSysMenuMobile);
	}	
	
    //============================================================
    //< DELETE - 시스템메뉴모바일 수정
    //============================================================	
	@DeleteMapping("/deleteSysMenuMobile")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String deleteSysMenuMobile(@RequestBody SysMenuMobile pSysMenuMobile) throws Exception {
		return sysMenuMobileService.deleteSysMenuMobile(pSysMenuMobile);
	}	
}
