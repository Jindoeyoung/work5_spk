package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuUsr;

/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface SysMenuUsrService {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	public String getSysMenuUsrList(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;
}
