package com.spk.api.mapper.pms.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.pms.api.ApiMstParam;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface ApiMstParamInfoMapper {
    //============================================================
    //< INSERT - 시스템메뉴정보 저장
    //============================================================	
	int insertApiMstParamInfo(
		@Param("API_MST_PARAM") ApiMstParam apiMstParam,
		@Param("spike_id") String spike_id
	);	
	

}
