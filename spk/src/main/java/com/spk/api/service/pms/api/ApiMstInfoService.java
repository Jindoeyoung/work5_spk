package com.spk.api.service.pms.api;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.pms.api.ApiMst;
//import com.spk.api.entity.SysMenuInfo;
import com.spk.api.entity.pms.api.ApiMstList;

/**
 * <p>시스템메뉴정보 Service</p>
 */
public interface ApiMstInfoService {
    //============================================================
    //< SELECT - API마스터 리스트 조회
    //============================================================	
	public String getApiMstList(@Param("API_MST") ApiMst apiMst) throws Exception;	
	
    //============================================================
    //< INSERT - API마스터 저장
    //============================================================
	public String insertApiMst(@Param("API_MST") ApiMstList apiMstList) throws Exception;
	
}
