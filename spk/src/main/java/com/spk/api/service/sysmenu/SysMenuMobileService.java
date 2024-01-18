package com.spk.api.service.sysmenu;

import org.apache.ibatis.annotations.Param;

//import com.spk.api.entity.SysMenuInfo;
import com.spk.api.entity.sysmenu.SysMenuMobile;

/**
 * <p>시스템메뉴 모바일정보 Service</p>
 */
public interface SysMenuMobileService {
    //============================================================
    //< SELECT - 시스템메뉴모바일 리스트 조회
    //============================================================	
	public String getSysMenuMobileList(@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile) throws Exception;
	
    //============================================================
    //< SELECT - 시스템메뉴모바일 상세조회
    //============================================================	
	public String getSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile) throws Exception;	
	
    //============================================================
    //< INSERT - 시스템메뉴모바일 저장
    //============================================================
	public String insertSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile) throws Exception;
	
    //============================================================
    //< UPDATE - 시스템메뉴모바일 수정
    //============================================================
	public String updateSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile) throws Exception;
	
    //============================================================
    //< DELETE - 시스템메뉴모바일 삭제
    //============================================================
	public String deleteSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile) throws Exception;		
}
