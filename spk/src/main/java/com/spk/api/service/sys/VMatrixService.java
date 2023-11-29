package com.spk.api.service.sys;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.sys.VMatrix;
/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface VMatrixService {
    //============================================================
    //< SELECT - 시스템메뉴유저 리스트 조회
    //============================================================	
	public String getMatrixList(@Param("V_MATRIX") VMatrix vMatrix) throws Exception;
	
    //============================================================
    //< SELECT - 매트릭스 조회 : Details
    //============================================================	
	public String getMatrixListDetails(@Param("V_MATRIX") VMatrix vMatrix) throws Exception;	
	
//    //============================================================
//    //< SELECT - 시스템메뉴유저 리스트 조회
//    //============================================================	
//	public String getSysMenuUsrList2(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;	
//	
//    //============================================================
//    //< SELECT - 시스템메뉴정보 상세 조회
//    //============================================================	
//	public String getSysMenuUsrInfo(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;	
//	
//    //============================================================
//    //< UPDATE - 시스템메뉴유저 수정
//    //============================================================
//	public String updateSysMenuUsr(@Param("SYS_MENU_USR") SysMenuUsr sysMenuUsr) throws Exception;	
}
