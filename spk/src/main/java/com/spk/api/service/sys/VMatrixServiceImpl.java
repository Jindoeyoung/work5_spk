package com.spk.api.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.spk.api.service.redis.BusAService_v4;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VMatrixServiceImpl implements VMatrixService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BusAService_v4 busAService_v4;
	
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
			BusA_depth_3 busA_depth_3 = new BusA_depth_3();
			
			if (datas.size() > 0) {
				
				for (VMatrix item : datas) {
					
					logger.info("getSpike_id==>"+item.getSpike_id());
					
					
					
					//methods 배열
					String method = item.getMethods();
					String[] arr_method = method.split("");

					//default_param_value
					String[] arr_default_param_value = item.getDefault_param_value().split(",");
					
					logger.info("arr_default_param_value==>"+Arrays.toString(arr_default_param_value));
					logger.info("arr_default_param_value.length==>"+arr_default_param_value.length);
					
//					String default_param_value = item.getDefault_param_value();
//					String[] arr_default_param_value = default_param_value.split("^");					

					
					String user_id = (arr_default_param_value[0] == null ? "" : arr_default_param_value[0]);
					String if_id = (arr_default_param_value[1] == null ? "" : arr_default_param_value[1]);
					String tbl_nm = (arr_default_param_value[2] == null ? "" : arr_default_param_value[2]);
					String[] arr_tbl_nm = tbl_nm.split(",");
					
					//================================================================
					busA.setUser_id(item.getSpike_id()); 					//spike_id
					//================================================================
					
					
					//================================================================
					busA_depth_1.setComponentId(item.getWidget_grp_id()+"^"+item.getFunc_id()); // 위젯ID^기능ID 
					busA_depth_1.setComponentName(item.getWidget_nm());   	// 위젯명
					busA_depth_1.setMethods(arr_method); 					// methods - 권한(CRUD)
					//================================================================
					
					// [behaviors] - 1
					//================================================================
					List<BusA_depth_2> behaviors = new ArrayList<BusA_depth_2>();
					busA_depth_2.setType(item.getType());					// type (self)
					busA_depth_2.setMethod(item.getMethod());				// method (POST/GET/PUT/DELETE/PATCH)
					busA_depth_2.setUri(item.getUri());						// uri
			
									
					busA_depth_3.setUser_id(user_id);
					busA_depth_3.setIf_id(if_id);
					busA_depth_3.setTbl_nm(arr_tbl_nm);

					behaviors.add(busA_depth_2);
					//================================================================
					

					// [behaviors] - 2
					//================================================================
					busA_depth_2.setType(item.getType_a());					// type (self)
					busA_depth_2.setMethod(item.getMethod_a());				// method (POST/GET/PUT/DELETE/PATCH)
					busA_depth_2.setUri(item.getUri_a());					// uri
					
					String[] arr_setRequiredParameter = item.getRequired_param().split("^");	// requiredParameter
					busA_depth_2.setRequiredParameter(arr_setRequiredParameter);
					busA_depth_2.setRequiredTarget(item.getRequired_target());
					
					String[] Permission = item.getPermission().split("");
					int[] intArray = new int[Permission.length];
					for(int i = 0; i < Permission.length; i++){
					    intArray[i] = Integer.parseInt(Permission[i]);
					}
					busA_depth_2.setPermission(intArray);
					
					behaviors.add(busA_depth_2);
					//================================================================
					
					List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
					flag_info.add(busA_depth_1);
					
					BusA insertBusA = null;
					busAService_v4.getUser(item.getSpike_id());
					insertBusA = busAService_v4.registerUser(item.getSpike_id(), flag_info);
					
//					return new ResponseEntity<>(insertBusA, HttpStatus.OK);
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
			logger.error("[VMatrixServiceImpl.getMatrixList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	

	
}
