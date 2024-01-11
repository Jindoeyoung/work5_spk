package com.spk.api.mapper.pms.api;

//import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.pms.api.ApiMst;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface ApiMstInfoMapper {
    //============================================================
    //< INSERT - 시스템메뉴정보 저장
    //============================================================	
	int insertApiMstInfo(
		@Param("API_MST") ApiMst apiMst
	);	
	

}
