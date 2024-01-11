package com.spk.api.service.pms.api;

import java.util.List;

//import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.spk.api.entity.pms.api.ApiMst;
import com.spk.api.entity.pms.api.ApiMstList;
import com.spk.api.mapper.pms.api.ApiMstInfoMapper;

import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiMstInfoServiceImpl implements ApiMstInfoService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApiMstInfoMapper apiMstMapper;	
	
	AuthCheck authcheck = new AuthCheck();
	
	/**
	 * <p>INSERT</p>
		 * <ul>
		 * 	<li>API 정보를 등록한다 </li>
	     * </ul>
	 * @param apiMst 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String insertApiMst(@Param("API_MST") ApiMstList apiMstList) throws Exception {	
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(apiMstList.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(apiMstList.getApikey());
		}		
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
//		JsonArray jsonArr1 = new JsonArray();
//		String Message = "SUCCESS";
//		String Success = "1";
		
		try {
			List<ApiMst> datas = apiMstList.getData();
			
			for (ApiMst item : datas) {
				int results = apiMstMapper.insertApiMstInfo(item);
			}
			
//			if (results == 1) {
//			} // if (results == 1) {
			
		} catch (Exception e) {
			logger.error("[ApiMstServiceImpl.insertApiMst] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	

	
}
