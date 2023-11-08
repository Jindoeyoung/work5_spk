package com.spk.api.service.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.spk.api.entity.redis.busa_v4.BusA;
import com.spk.api.entity.redis.busa_v4.BusA_depth_1;
import com.spk.api.entity.redis.busa_v4.BusA_depth_2;
import com.spk.api.entity.redis.busa_v4.BusA_depth_3;
import com.spk.api.entity.sys.VMatrix;
import com.spk.api.mapper.sys.VMatrixMappers;
//import com.spk.api.entity.SysMenuUsr;
//import com.spk.api.mapper.SysMenuUsrMapper;

import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VMatrixServiceImpl implements VMatrixService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VMatrixMappers vMatrixMappers;
	
	AuthCheck authcheck = new AuthCheck();

	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>시스템메뉴정보 등록 리스트를 조회한다 </li>
	     * </ul>
	 * @param pSysMenuUsr 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */
	@Override
	public String getMatrixList(@Param("V_MATRIX") VMatrix vMatrix) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(vMatrix.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(vMatrix.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
//		JsonArray jsonArr = new JsonArray();
//		String Message = "SUCCESS";
//		String Success = "1";
		
		try {
			List<VMatrix> datas = vMatrixMappers.getMatrixList(vMatrix.getGubun());
//			
			
			
			BusA busA = new BusA();
			BusA_depth_1 busA_depth_1 = new BusA_depth_1();
			BusA_depth_2 busA_depth_2 = new BusA_depth_2();
//			BusA_depth_3 busA_depth_3 = new BusA_depth_3();
			
			if (datas.size() > 0) {
				
				for (VMatrix item : datas) {
					
					logger.info("getSpike_id==>"+item.getSpike_id());
					
					
					
					//methods 배열
					String method = item.getMethods();
					String[] arr_method = method.split("");
					
					busA.setUser_id(item.getSpike_id());
					busA_depth_1.setComponentId(item.getWidget_grp_id());
					busA_depth_1.setComponentName(item.getWidget_nm());
					busA_depth_1.setMethods(arr_method);
					
					busA_depth_2.setType(item.getType());
					busA_depth_2.setMethod(item.getMethod());
					busA_depth_2.setUri(item.getUri());
					
//					busA_depth_2.set
					
					
				}

				
			} else {

			}			
			
			
			
			
//			dataResult.addProperty("reason", Message);
//			dataResult.addProperty("result", Success);
//			
//			if (sysMenuUsr.size() > 0) {
//				for (SysMenuUsr item : sysMenuUsr) {
//					JSONObject result = new JSONObject(item.getJson_data());
//					jsonArr.add(item.getJson_data());	
//				}
//				dataResult.add("menu_info", jsonArr);
//				
//			} else {
//
//			}
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuUsrList2] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	

	
}
