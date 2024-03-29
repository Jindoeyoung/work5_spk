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
		@Param("API_MST") ApiMst apiMst,
		@Param("spike_id") String spike_id
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
	
    //============================================================
    //< DELETE - API_MST 삭제
    //============================================================	
	int deleteApiMstInfo(
		@Param("API_MST") ApiMst apiMst
	);
	
    //============================================================
    //< DELETE - API_MST_PARAM 삭제
    //============================================================	
	int deleteApiMstParamInfo(
		@Param("API_MST") ApiMst apiMst
	);
	
    //============================================================
    //< DELETE - A_MATRIX 삭제
    //============================================================	
	int deleteMatrixInfo(
		@Param("api_id") String api_id
	);
	
	
    //============================================================
    //< DELETE - API_MST 삭제
    //============================================================	
	int deleteApiMstInfoGet(
		@Param("if_id") String if_id
	);
	
    //============================================================
    //< DELETE - API_MST_PARAM 삭제
    //============================================================	
	int deleteApiMstParamInfoGet(
		@Param("if_id") String if_id
	);
	
    //============================================================
    //< DELETE - A_MATRIX 삭제
    //============================================================	
	int deleteMatrixInfoGet(
		@Param("if_id") String if_id
	);	
}
