package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuInfo;

/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface SysMenuInfoService {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	public String getSysMenuInfoList(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;
	
    //============================================================
    //< INSERT
    //============================================================
	public String insertSysMenuInfo(@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo) throws Exception;	
}
