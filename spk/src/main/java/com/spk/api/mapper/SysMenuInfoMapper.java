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

}
