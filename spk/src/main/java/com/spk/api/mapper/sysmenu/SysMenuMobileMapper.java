package com.spk.api.mapper.sysmenu;

//import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.sysmenu.SysMenuMobile;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface SysMenuMobileMapper {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
//	List<SysMenuInfo> getSysMenuInfoList(
//		@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
//	);
	
    //============================================================
    //< SELECT - 시스템메뉴 모바일 정보 조회
    //============================================================	
	SysMenuMobile getSysMenuMobile(
		@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile
	);

    //============================================================
    //< INSERT - 시스템메뉴 모바일 정보 저장
    //============================================================	
	int insertSysMenuMobile(
		@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile
	);	
	
    //============================================================
    //< UPDATE - 시스템메뉴 모바일 정보 수정
    //============================================================	
	int updateSysMenuMobile(
		@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile
	);	
 
    //============================================================
    //< DELETE - 시스템메뉴 모바일 정보 삭제
    //============================================================	
	int deleteSysMenuMobile(
		@Param("SYS_MENU_MOBILE") SysMenuMobile sysMenuMobile
	);	
	
}
