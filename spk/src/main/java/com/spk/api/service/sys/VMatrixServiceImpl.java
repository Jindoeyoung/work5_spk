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
//			BusA_depth_2 busA_depth_2 = new BusA_depth_2();
			BusA_depth_3 busA_depth_3 = new BusA_depth_3();
			
			if (datas.size() > 0) {
				
				for (VMatrix item : datas) {
					
					logger.info("getSpike_id==>"+item.getSpike_id());
					
					//methods 배열
					String[] arr_method = item.getMethods().split("");
					String user_id = "";
					
					// Default_param_value 에서 콤마(,) 개수 구하기
//					String paramValue = item.getDefault_param_value();					
//					int commaCount = paramValue.length() - paramValue.replace(String.valueOf(","), "").length();
//					logger.info("commaCount=====#@@##==>"+commaCount);
//					String[] arr_tbl_nm = new String[commaCount-1];
					
					// 굳이 위 처럼 전체 배열 크리를 먼저 구하지 않고, 아래에서 콤마(,) 갯수에 의해 크기가 할당 되니까, 우선 1사이즈로 초기화 하자
					String[] arr_tbl_nm = new String[1];
					
//					logger.info("1. item.getDefault_param_value()====>"+item.getDefault_param_value());
					
		            //============================================================
		            //< defaultParameter : user_id 또는 tbl_nm
					//< user_id : (공통)_그리드, (공통)_디테일
					//< tbl_mm : 그 외 전부
		            //============================================================
					if ( 
						( item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-001-01") ||
						  item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-002-01") ) && 
						  item.getGubun().equals("0") ) { // BUS AVAIL 경우 
						user_id = item.getDefault_param_value();
					} else {
						if (item.getDefault_param_value().contains(",")) {
							arr_tbl_nm = item.getDefault_param_value().split(",");
						} else {
							arr_tbl_nm[0] = item.getDefault_param_value();
						}
					}
					
//					logger.info("2. arr_tbl_nm====>"+Arrays.toString(arr_tbl_nm));
					
		            //============================================================
		            //< spike_id
		            //============================================================					
					busA.setUser_id(item.getSpike_id());
					
		            //============================================================
		            //< 위젯ID^기능ID
		            //============================================================					
					busA_depth_1.setComponentId(item.getWidget_grp_id()+"^"+item.getFunc_id()); // 위젯ID^기능ID
					
		            //============================================================
		            //< componentName (위젯명)
		            //============================================================										
					busA_depth_1.setComponentName(item.getWidget_nm());

		            //============================================================
		            //< methods (권한)
		            //============================================================					
					busA_depth_1.setMethods(arr_method);

					
		            //============================================================
		            //< API - self 영역 
					//< : 위젯 자체 API 호출. 예)칼럼 헤더
					//< : [behaviors] - 1
		            //============================================================ 
					BusA_depth_2 busA_depth_2 = new BusA_depth_2();
					List<BusA_depth_2> behaviors = new ArrayList<BusA_depth_2>();
					
		            //============================================================
		            //< type (self)
		            //============================================================					
					busA_depth_2.setType(item.getType());
					
		            //============================================================
		            //< method (POST/GET/PUT/DELETE/PATCH)
		            //============================================================					
					busA_depth_2.setMethod(item.getMethod());
					
		            //============================================================
		            //< uri
		            //============================================================
					busA_depth_2.setUri(item.getUri());

		            //============================================================
		            //< defaultParameter (user_id 또는 tbl_nm)
		            //============================================================					
					if (user_id.length()>0)				
						busA_depth_3.setUser_id(user_id);
					if (arr_tbl_nm.length > 0) 
						busA_depth_3.setTbl_nm(arr_tbl_nm);
					
					busA_depth_2.setDefaultParameter(busA_depth_3);
					behaviors.add(busA_depth_2);

		            //============================================================
		            //< API - search 영역
					//< : API 업무 영역 (데이터 조회)
					//< : [behaviors] - 2
					//< : 콤마(,)로 구분된 type 갯수만큼 loop
		            //============================================================					
					
					// 콤마로 총 api 갯수 구하기 (콤마개수 + 1 해준다)
					String getType_a = item.getType_a();					
					int apiCount = getType_a.length() - getType_a.replace(String.valueOf(","), "").length()+1;
					logger.info("apiCount=====#@@##==>"+apiCount);				
					
					// TYPE
					String[] arr_type_a = new String[apiCount];
					if (item.getType_a().contains(",")) {
						arr_type_a = item.getType_a().split(",");
					} else {
						arr_type_a[0] = item.getType_a();
//						String type_a = arr_type_a[0].toString();
					}

					logger.info("arr_type_a============>"+Arrays.toString(arr_type_a));
					
					// TYPE
					String[] arr_method_a = new String[apiCount];
					if (item.getMethod_a().contains(",")) {
						arr_method_a = item.getMethod_a().split(",");
					} else {
						arr_method_a[0] = item.getMethod_a();
					}
					
					// URI
					String[] arr_uri_a = new String[apiCount];
					if (item.getUri_a().contains(",")) {
						arr_uri_a = item.getUri_a().split(",");
					} else {
						arr_uri_a[0] = item.getUri_a();
					}
					
					// REQUIRED TARGET
					String[] arr_requiredTarget = new String[apiCount];
					if (item.getRequired_target().contains(",")) {
						arr_requiredTarget = item.getRequired_target().split(",");
					} else {
						arr_requiredTarget[0] = item.getRequired_target();
						
						// 배열 나머지 요소는 null 로 채움
						for(int j = 1; j < apiCount; j++){
							arr_requiredTarget[j] = null;
						}						
					}
					
//					logger.info("arr_requiredTarget============>"+Arrays.toString(arr_requiredTarget));
					
					// DEFAULT PARAM VALUE
					String[] arr_defaultParamValue_a = new String[apiCount];
					if (item.getDefault_param_value_a().contains(",")) {
						arr_defaultParamValue_a = item.getDefault_param_value_a().split(",");
					} else {
						arr_defaultParamValue_a[0] = item.getDefault_param_value_a();
					}
					
					logger.info("arr_defaultParamValue============>"+Arrays.toString(arr_defaultParamValue_a));
					
					// REQURED PARAM
					String[] arr_requiredParam = new String[apiCount];
					if (item.getRequired_param().contains("`")) {
						arr_requiredParam = item.getRequired_param().split("`");
					} else {
						arr_requiredParam[0] = item.getRequired_param();
						
						// 배열 나머지 요소는 null 로 채움
						for(int j = 1; j < apiCount; j++){
							arr_requiredParam[j] = null;
						}						
					}
					
					// PERMISSION
					String[] arr_permission = new String[apiCount];
					if (item.getPermission().contains("`")) {
						arr_permission = item.getPermission().split("`");
					} else {
						arr_permission[0] = item.getPermission();
						
						// 배열 나머지 요소는 null 로 채움
						for(int j = 1; j < apiCount; j++){
							arr_permission[j] = null;
						}						
					}					
					
					
					logger.info("@@@@@arr_type_a.length@@@@@@===>"+arr_type_a.length);
					// loop 시작 
					for(int i = 0; i < apiCount; i++){
					logger.info("@@@@@iiiii@@@@@@===>"+i);
						BusA_depth_2 busA_depth_2_A = new BusA_depth_2();
						BusA_depth_3 busA_depth_3_A = new BusA_depth_3();
						List<BusA_depth_2> behaviors_A = new ArrayList<BusA_depth_2>();
						
			            //============================================================
			            //< type
			            //============================================================
						busA_depth_2_A.setType(arr_type_a[i]);
						
			            //============================================================
			            //< method (POST/GET/PUT/DELETE/PATCH)
			            //============================================================						
						busA_depth_2_A.setMethod(arr_method_a[i]);
						
						//============================================================
			            //< uri
			            //============================================================						
						busA_depth_2_A.setUri(arr_uri_a[i]);

						//============================================================
			            //< requiredTarget
			            //============================================================
//						logger.info("arr_requiredTarget[i]=######################>"+arr_requiredTarget[i]);
//						logger.info("arr_requiredTarget=######################>"+arr_requiredTarget);
//						logger.info("arr_requiredTarget.toString=>"+Arrays.toString(arr_requiredTarget));
						
//						if (arr_requiredTarget != null && arr_requiredTarget.length > 0)
						if (arr_requiredTarget[i] != null && arr_requiredTarget[i].length() > 0)
							busA_depth_2_A.setRequiredTarget(arr_requiredTarget[i]);						

						//============================================================
			            //< defaultParameter (IF_ID)
			            //============================================================						
						busA_depth_3_A.setIf_id(arr_defaultParamValue_a[i]);

						busA_depth_2_A.setDefaultParameter(busA_depth_3_A);
						
						//============================================================
			            //< requiredParameter
			            //============================================================						
						if (arr_requiredParam[i].length() > 0) {
							String[] each_arr_requiredParam = new String[1];
							if (arr_requiredParam[i].toString().contains(",")) {
								each_arr_requiredParam = arr_requiredParam[i].toString().split(",");
							} else {
								each_arr_requiredParam[0] = arr_requiredParam[i];
							}
							busA_depth_2_A.setRequiredParameter(each_arr_requiredParam);
						}
						
						//============================================================
			            //< permission
			            //============================================================
						if (arr_permission[i] != null && arr_permission[i].length() > 0) {
							String[] Permission = arr_permission[i].split("");
							int[] intArray = new int[Permission.length];
							for(int j = 0; j < Permission.length; j++){
							    intArray[j] = Integer.parseInt(Permission[j]);
							}
							busA_depth_2_A.setPermission(intArray);						
						}
							
							
//						String[] Permission = item.getPermission().split("");
//						int[] intArray = new int[Permission.length];
//						for(int j = 0; j < Permission.length; j++){
//						    intArray[j] = Integer.parseInt(Permission[j]);
//						}
//						busA_depth_2_A.setPermission(intArray);						
						
						
						
						
						
						//behaviors.add(busA_depth_2_A);
						//busA_depth_1.setBehaviors(behaviors);
						
						
						behaviors_A.add(busA_depth_2_A);
						
						
						
						
						
						

						behaviors.addAll(behaviors_A);
												

					// loop 끝
					}
					busA_depth_1.setBehaviors(behaviors);
					
					
					List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
					flag_info.add(busA_depth_1);
					
					logger.info("flag_info =>"+flag_info);					
					
					// REDIS Insert
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
