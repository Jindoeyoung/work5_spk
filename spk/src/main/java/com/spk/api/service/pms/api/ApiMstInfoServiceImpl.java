package com.spk.api.service.pms.api;

import java.util.List;

//import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

//import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.spk.api.entity.pms.api.ApiMst;
import com.spk.api.entity.pms.api.ApiMstList;
import com.spk.api.entity.pms.api.ApiMstParam;
import com.spk.api.mapper.pms.api.ApiMstInfoMapper;
import com.spk.api.mapper.pms.api.ApiMstParamInfoMapper;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiMstInfoServiceImpl implements ApiMstInfoService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApiMstInfoMapper apiMstMapper;	
	
	@Autowired
	private ApiMstParamInfoMapper apiMstParamMapper;
	
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
	@Transactional
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
		int result = 0;
		int result2 = 0;
		
		try {
			List<ApiMst> datas = apiMstList.getData();
			
			for (ApiMst item : datas) {
				// API_MST 저장
				result = apiMstMapper.insertApiMstInfo(item);
				
				List<ApiMstParam> datas2 = item.getApi_param();
				for (ApiMstParam item2 : datas2) {
					// API_MST_PARAM 저장
					result2 = apiMstParamMapper.insertApiMstParamInfo(item2);
				}
			}
			
			if (result < 1 || result2 < 1) {
				throw new RuntimeException("API_MST Exception");
			}			
			
		} catch (Exception e) {
			logger.error("[ApiMstInfoServiceImpl.insertApiMst] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	

	
}
