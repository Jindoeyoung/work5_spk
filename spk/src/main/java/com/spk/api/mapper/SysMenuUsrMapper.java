package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuUsr;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface SysMenuUsrMapper {
    //============================================================
    //< SELECT - 시스템메뉴유저 리스트 조회
    //============================================================	
	List<SysMenuUsr> getSysMenuUsrList_GR2(
		@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr
	);	

    //============================================================
    //< SELECT - 시스템메뉴유저 리스트 조회
    //============================================================	
	List<SysMenuUsr> getSysMenuUsrList(
		@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr
	);
	
    //============================================================
    //< UPDATE - 시스템메뉴유저 UseYn 수정
    //============================================================	
	int updateUseYn(
		@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr
	);	
}
