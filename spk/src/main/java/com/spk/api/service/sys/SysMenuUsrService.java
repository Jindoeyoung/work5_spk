package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuUsrInfo;
import com.spk.api.entity.SysMenuSaveUsr;
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
	
    //============================================================
    //< INSERT/UPDATE - 시스템 메뉴 일괄 처리.20240314.PSD
    //============================================================
	public String saveSysMenuUsr(@Param("SYS_MENU_SAVE_USR") SysMenuSaveUsr pSysMenuSaveUsr) throws Exception;	
	
    //============================================================
    //< INSERT/UPDATE - 시스템 메뉴 중복 체크.20240318.PSD
    //============================================================
	public String checkSysMenuUsr(@Param("SYS_MENU_CHECK_USR") SysMenuUsrInfo pSysMenuUsrInfo) throws Exception;	
	
    //============================================================
    //< INSERT/UPDATE - 시스템 메뉴 삭제 기능 추가.20240318.PSD
    //============================================================
	public String deleteSysMenuUsr(@Param("SYS_MENU_USR_INFO") SysMenuUsrInfo pSysMenuUsrInfo) throws Exception;	
}
