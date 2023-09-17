package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.SysMenuInfo;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface SysMenuInfoMapper {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	List<SysMenuInfo> getSysMenuInfoList(
		@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
	);
	
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	SysMenuInfo getSysMenuInfo(
		@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
	);	

    //============================================================
    //< INSERT - 시스템메뉴정보 저장
    //============================================================	
	int insertSysMenuInfo(
		@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
	);	
	
    //============================================================
    //< INSERT - 메뉴ID USE_YN 수정
    //============================================================	
	int updateUseYn(
			@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
			//@Param("parent_menu_id") String parent_menu_id
	);	
	
    //============================================================
    //< SELECT - 특정 상위메뉴코드를 함께 갖는, 동일 Level 메뉴들의 USE_YN 값들 조회
    //============================================================	
	List<SysMenuInfo> getSameLevelUseYn(
		@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo
	);
	
    //============================================================
    //< INSERT - 상위메뉴ID USE_YN 수정(N)
    //============================================================	
	int updateParentUseYn(
			@Param("SYS_MENU_INFO") SysMenuInfo sysMenuInfo,
			@Param("use_yn") String use_yn
			//@Param("parent_menu_id") String parent_menu_id
	);	
	
}
