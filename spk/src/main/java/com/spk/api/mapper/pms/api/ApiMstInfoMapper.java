package com.spk.api.mapper.pms.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.pms.api.ApiMst;
//import com.spk.api.entity.pms.api.ApiMstList;

/**
 * <p>Mapper</p>
 */
@Mapper
public interface ApiMstInfoMapper {
    //============================================================
    //< SELECT - 시스템메뉴정보 리스트 조회
    //============================================================	
	List<ApiMst> getApiMstList(
		@Param("API_MST") ApiMst apiMst
	);	
	
    //============================================================
    //< INSERT - API마스터 저장
    //============================================================	
	int insertApiMstInfo(
		@Param("API_MST") ApiMst apiMst
	);	
	
    //============================================================
    //< INSERT - 매트릭스 정보 저장 (procedure 호출)
    //============================================================	
	int insertMatrixInfo(
		@Param("api_id") String api_id,
		@Param("spike_id") String spike_id
//		@Param("API_MST") ApiMstList apiMstList
	);
	
    //============================================================
    //< UPDATE - REL_API_ID update
    //============================================================	
	int updateApiMstRelApiId(
		@Param("api_id") String api_id,
		@Param("rel_api_id") String rel_api_id
	);	
	
}
