package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuInfo;
import com.spk.api.entity.SysMenuSaveUsr;

/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface SysMenuInfoService {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	public String getSysMenuInfoList(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;
	
    //============================================================
    //< SELECT - 시스템메뉴정보 상세 조회
    //============================================================	
	public String getSysMenuInfo(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;	
	
    //============================================================
    //< INSERT - 시스템메뉴정보 저장
    //============================================================
	public String insertSysMenuInfo(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;
	
    //============================================================
    //< INSERT/UPDATE - 시스템메뉴정보 저장/상위메뉴 USE_YN 수정
    //============================================================
	public String insertSysMenuInfoUpdate(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;	
}
