package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuUsr;
import com.spk.api.entity.SysMenuUsrSave;

/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface SysMenuUsrService {
    //============================================================
    //< SELECT - 시스템메뉴유저 리스트 조회
    //============================================================	
	public String getSysMenuUsrList_GR2(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;	
	
	//============================================================
    //< SELECT - 시스템메뉴유저 리스트 조회
    //============================================================	
	public String getSysMenuUsrList(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;
	
    //============================================================
    //< UPDATE - 시스템메뉴유저 수정
    //============================================================
	public String updateSysMenuUsr(@Param("SYS_MENU_USR") SysMenuUsrSave sysMenuUsrSave) throws Exception;
}
