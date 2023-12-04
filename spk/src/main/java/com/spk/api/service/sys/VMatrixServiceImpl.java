package com.spk.api.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.tomcat.util.json.JSONParser;
//import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
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

import com.spk.api.entity.redis.usergrids_v3.*;
import com.spk.api.service.redis.UserGridsService_v3;

import com.spk.api.entity.redis.userdetails_v3.*;
import com.spk.api.service.redis.UserDetailsService_v3;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VMatrixServiceImpl implements VMatrixService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BusAService_v4 busAService_v4;

	@Autowired
	private UserGridsService_v3 gridsService_v3;	
	
	@Autowired
	private UserDetailsService_v3 detailsService_v3;
	
	@Autowired
	private VMatrixMappers vMatrixMappers;
	
	AuthCheck authcheck = new AuthCheck();

	/**
	 * <p>SELECT - INSERT (Matrix - BUS Avail)</p>
		 * <ul>
		 * 	<li>매트릭스 데이터에서 BUS Avail을 조회하고, Json 데이터를 생성한다.  </li>
	     * </ul>
	 * @param vMatrix 클라이언트에서 요청받은 메뉴정보
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
		
		try {
			List<VMatrix> users = vMatrixMappers.getAllSpikeIdList();

			for (VMatrix user : users) {
			
				List<VMatrix> datas = vMatrixMappers.getSpikeIdMatrixList(vMatrix.getGubun(), user.getSpike_id()); 
			
				List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
//				int rowCount = 0;
				String spike_id = "";
				
				if (datas.size() > 0) {
					
					for (VMatrix item : datas) {
						
//						rowCount++;
						spike_id = item.getSpike_id();
//						logger.info("rowCount#################==>"+rowCount);
						
						BusA busA = new BusA();
						BusA_depth_1 busA_depth_1 = new BusA_depth_1();
						BusA_depth_2 busA_depth_2 = new BusA_depth_2();
						BusA_depth_3 busA_depth_3 = new BusA_depth_3();
						
						//methods 배열
						String[] arr_method = item.getMethods().split("");
						String user_id = "";
						
			            //============================================================
			            //< defaultParameter : user_id 또는 tbl_nm
						//< user_id : (공통)_그리드, (공통)_디테일
						//< tbl_mm : 그 외 전부
			            //============================================================
						// 콤마로 총 테이블 갯수 구하기 (콤마개수 + 1 해준다) : (공통)_그리드, (공통)_디테일 이 아닌 경우만 해당
						String getDefault_param_value = item.getDefault_param_value();
						
						int tableCount = 0;
						if ("tbl_nm".equals(item.getDefault_param()) && item.getDefault_param_value() != null && item.getDefault_param_value().length() > 0) {
							tableCount = getDefault_param_value.length() - getDefault_param_value.replace(String.valueOf(","), "").length()+1;
						}
						
//						logger.info("tableCount====="+tableCount);
						
						String[] arr_tbl_nm = new String[tableCount];
						
						if ( 
							( item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-001-01") ||
							  item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-002-01") ) && 
							  item.getGubun().equals("0") ) { // BUS AVAIL 경우 
							user_id = item.getDefault_param_value();
						} else {

							if ( tableCount > 0 ) {
//								logger.info("tableCount > 0 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
								if (item.getDefault_param_value() != null && item.getDefault_param_value().contains(",")) {
									arr_tbl_nm = item.getDefault_param_value().split(",");
								} else {
									arr_tbl_nm[0] = item.getDefault_param_value();
									
									// 배열 나머지 요소는 null 로 채움
//									for(int j = 1; j < tableCount; j++){
//										arr_tbl_nm[j] = null;
//									}								
								}
								
							}
								
						}
						
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
						
						List<BusA_depth_2> behaviors = new ArrayList<BusA_depth_2>();
						
			            //============================================================
			            //< self API - TYPE 유무 여부로 [TYPE]~[DEFAULT_PARAM_VALUE] 까지의 셋팅 여부 결정 
			            //============================================================ 
						if (item.getType() != null && item.getType().length()>0) {
						
				            //============================================================
				            //< API - self 영역 
							//< : 위젯 자체 API 호출. 예)칼럼 헤더
							//< : [behaviors] - 1
				            //============================================================ 
				            //============================================================
				            //< type (self)
				            //============================================================
							if (item.getType() != null && item.getType().length()>0)
								busA_depth_2.setType(item.getType());
							
				            //============================================================
				            //< method (POST/GET/PUT/DELETE/PATCH)
				            //============================================================
							if (item.getMethod() != null && item.getMethod().length()>0)
								busA_depth_2.setMethod(item.getMethod());
							
				            //============================================================
				            //< uri
				            //============================================================
							if (item.getUri() != null && item.getUri().length()>0)
								busA_depth_2.setUri(item.getUri());
		
				            //============================================================
				            //< defaultParameter (user_id 또는 tbl_nm)
				            //============================================================					
							if (user_id != null && user_id.length()>0)				
								busA_depth_3.setUser_id(user_id);
							
							if (arr_tbl_nm != null && arr_tbl_nm.length > 0) {
								busA_depth_3.setTbl_nm(arr_tbl_nm);
							}
							
							if (busA_depth_3 != null)
								busA_depth_2.setDefaultParameter(busA_depth_3);
							if (busA_depth_2 != null)
								behaviors.add(busA_depth_2);
						}	
							
			            //============================================================
			            //< API - search 영역
						//< : API 업무 영역 (데이터 조회)
						//< : [behaviors] - 2
						//< : 콤마(,)로 구분된 type 갯수만큼 loop
			            //============================================================					
						
						// 콤마로 총 api 갯수 구하기 (콤마개수 + 1 해준다)
						String getType_a = item.getType_a();
						int apiCount = 0;
						if (item.getType_a() != null && item.getType_a().length() > 0) {
							apiCount = getType_a.length() - getType_a.replace(String.valueOf(","), "").length()+1;
						}
						
						String[] arr_type_a = new String[apiCount];
						String[] arr_method_a = new String[apiCount];
						String[] arr_uri_a = new String[apiCount];
						String[] arr_requiredTarget = new String[apiCount];
						String[] arr_defaultParamValue_a = new String[apiCount];
						String[] arr_requiredParam = new String[apiCount];
						String[] arr_permission = new String[apiCount];
						
						if (apiCount > 0) {
							
							// TYPE
							if (item.getType_a() != null && item.getType_a().contains(",")) {
								arr_type_a = item.getType_a().split(",");
							} else {
								arr_type_a[0] = item.getType_a();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_type_a[j] = null;
//								}							
							}
							
							// METHOD
							if (item.getMethod_a() != null && item.getMethod_a().contains(",")) {
								arr_method_a = item.getMethod_a().split(",");
							} else {
								arr_method_a[0] = item.getMethod_a();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_method_a[j] = null;
//								}							
							}
							
							// URI
							if (item.getUri_a() != null && item.getUri_a().contains(",")) {
								arr_uri_a = item.getUri_a().split(",");
							} else {
								arr_uri_a[0] = item.getUri_a();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_uri_a[j] = null;
//								}							
							}
							
							// REQUIRED TARGET
							if (item.getRequired_target() != null && item.getRequired_target().contains(",")) {
								arr_requiredTarget = item.getRequired_target().split(",");
							} else {
								arr_requiredTarget[0] = item.getRequired_target();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_requiredTarget[j] = null;
//								}						
							}
							
							// DEFAULT PARAM VALUE
							if (item.getDefault_param_value_a() != null && item.getDefault_param_value_a().contains(",")) {
								arr_defaultParamValue_a = item.getDefault_param_value_a().split(",");
							} else {
								arr_defaultParamValue_a[0] = item.getDefault_param_value_a();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_defaultParamValue_a[j] = null;
//								}							
							}
							
							// REQURED PARAM
							if (item.getRequired_param() != null && item.getRequired_param().contains("`")) {
								arr_requiredParam = item.getRequired_param().split("`");
							} else {
								arr_requiredParam[0] = item.getRequired_param();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_requiredParam[j] = null;
//								}						
							}
							
							// PERMISSION
							if (item.getPermission() != null && item.getPermission().contains("`")) {
								arr_permission = item.getPermission().split("`");
							} else {
								arr_permission[0] = item.getPermission();
								
								// 배열 나머지 요소는 null 로 채움
//								for(int j = 1; j < apiCount; j++){
//									arr_permission[j] = null;
//								}						
							}					
						
						} // end of if (apiCount > 0)

						// loop 시작 
						for(int i = 0; i < apiCount; i++) {
//							logger.info("@@@@@iiiii@@@@@@===>"+i);
							BusA_depth_2 busA_depth_2_A = new BusA_depth_2();
							BusA_depth_3 busA_depth_3_A = new BusA_depth_3();
							List<BusA_depth_2> behaviors_A = new ArrayList<BusA_depth_2>();
							
				            //============================================================
				            //< type
				            //============================================================
							if (arr_type_a[i] != null && arr_type_a[i].length() > 0)
								busA_depth_2_A.setType(arr_type_a[i]);
							
				            //============================================================
				            //< method (POST/GET/PUT/DELETE/PATCH)
				            //============================================================
							if (arr_method_a[i] != null && arr_method_a[i].length() > 0)
								busA_depth_2_A.setMethod(arr_method_a[i]);
							
							//============================================================
				            //< uri
				            //============================================================
							if (arr_uri_a[i] != null && arr_uri_a[i].length() > 0)
								busA_depth_2_A.setUri(arr_uri_a[i]);
	
							//============================================================
				            //< requiredTarget
				            //============================================================
							if (arr_requiredTarget[i] != null && arr_requiredTarget[i].length() > 0)
								busA_depth_2_A.setRequiredTarget(arr_requiredTarget[i]);						
	
							//============================================================
				            //< defaultParameter (IF_ID)
				            //============================================================
							if (arr_defaultParamValue_a[i] != null && arr_defaultParamValue_a[i].length() > 0)
								busA_depth_3_A.setIf_id(arr_defaultParamValue_a[i]);
	
							if (busA_depth_3_A != null)
								busA_depth_2_A.setDefaultParameter(busA_depth_3_A);
							
							//============================================================
				            //< requiredParameter
				            //============================================================						
							if (arr_requiredParam[i] != null && arr_requiredParam[i].length() > 0) {
								String[] each_arr_requiredParam = new String[1];
								if (arr_requiredParam[i].toString() != null && arr_requiredParam[i].toString().contains(",")) {
									each_arr_requiredParam = arr_requiredParam[i].toString().split(",");
								} else {
									each_arr_requiredParam[0] = arr_requiredParam[i];
//JDY 아래 4줄은 의심									
//									// 배열 나머지 요소는 null 로 채움
//									for(int j = 1; j < apiCount; j++){
//										each_arr_requiredParam[j] = null;
//									}									
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
							
							//if (apiCount > 0) {
							
								if (busA_depth_2_A != null)
									behaviors_A.add(busA_depth_2_A);
								
								if (behaviors_A != null)
									behaviors.addAll(behaviors_A);
							//}

						} // end of for(int i = 0; i < apiCount; i++)
						
						if (behaviors != null && !behaviors.isEmpty())
							busA_depth_1.setBehaviors(behaviors);
						
						if (busA_depth_1 != null)
							flag_info.add(busA_depth_1);
						
//						if ( rowCount == datas.size()) {
//							logger.info("INSERT!!!>");
//							// REDIS Insert
//							BusA insertBusA = null;
//							busAService_v4.getUser(item.getSpike_id());
//							insertBusA = busAService_v4.registerUser(item.getSpike_id(), flag_info);
//						}
						
					}  // end of for (VMatrix item : datas)
					
					logger.info("INSERT!!!>");
					// REDIS Insert
					BusA insertBusA = null;
					busAService_v4.getUser(spike_id);
					insertBusA = busAService_v4.registerUser(spike_id, flag_info);					
					
				} else {
				} // end of if (datas.size() > 0)			
			
			}
		} catch (Exception e) {
			logger.error("[VMatrixServiceImpl.getMatrixList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	
	/**
	 * <p>SELECT - INSERT (Matrix - UserGrids)</p>
		 * <ul>
		 * 	<li>매트릭스 데이터에서 Grdis 조회하고, UserGrids Json 데이터를 생성한다.  </li>
	     * </ul>
	 * @param vMatrix 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */
	@Override
	public String getMatrixListGrids(@Param("V_MATRIX") VMatrix vMatrix) throws Exception {
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
			List<VMatrix> users = vMatrixMappers.getAllSpikeIdList();
//			List<VMatrix> datas = vMatrixMappers.getMatrixList(vMatrix.getGubun());

			for (VMatrix user : users) {
				logger.info("user.getSpike_id()==>"+user.getSpike_id());	
			
				List<VMatrix> datas = vMatrixMappers.getSpikeIdMatrixList(vMatrix.getGubun(), user.getSpike_id()); 
				
				
				UserGrids_depth_1 data = new UserGrids_depth_1();
	// [POINT]  아래 선언을, loop 시작 전에 선언
				List<UserGrids_depth_2> result = new ArrayList<UserGrids_depth_2>();
//				List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
				
				int rowCount = 0;
				
				if (datas.size() > 0) {
					
					for (VMatrix item : datas) {
						
						rowCount++;
						
						UserGrids grids = new UserGrids();
						UserGrids_depth_1 depth_1 = new UserGrids_depth_1();
						UserGrids_depth_2 depth_2 = new UserGrids_depth_2();
						UserGrids_depth_3 depth_3 = new UserGrids_depth_3();
						UserGrids_depth_4 depth_4 = new UserGrids_depth_4();						
						
//						UserDetails details = new UserDetails();
//						UserDetails_depth_1 depth_1 = new UserDetails_depth_1();
//						UserDetails_depth_2 depth_2 = new UserDetails_depth_2();
//						UserDetails_depth_3 depth_3 = new UserDetails_depth_3();
//						UserDetails_depth_4 depth_4 = new UserDetails_depth_4();
						
						
						logger.info("item.getSpike_id()==>"+item.getSpike_id());
						
						//methods 배열
						String[] arr_method = item.getMethods().split("");
						String user_id = "";
						
						// Default_param_value 에서 콤마(,) 개수 구하기
	//					String paramValue = item.getDefault_param_value();					
	//					int commaCount = paramValue.length() - paramValue.replace(String.valueOf(","), "").length();
	//					logger.info("commaCount=====#@@##==>"+commaCount);
	//					String[] arr_tbl_nm = new String[commaCount-1];
						
						// 굳이 위 처럼 전체 배열 크리를 먼저 구하지 않고, 아래에서 콤마(,) 갯수에 의해 크기가 할당 되니까, 우선 1사이즈로 초기화 하자
//						String[] arr_tbl_nm = new String[1];
						
	//					logger.info("1. item.getDefault_param_value()====>"+item.getDefault_param_value());
						
			            //============================================================
			            //< defaultParameter : user_id 또는 tbl_nm
						//< user_id : (공통)_그리드, (공통)_디테일
						//< tbl_mm : 그 외 전부
			            //============================================================
						// 콤마로 총 테이블 갯수 구하기 (콤마개수 + 1 해준다) : (공통)_그리드, (공통)_디테일 이 아닌 경우만 해당
						String getDefault_param_value = item.getDefault_param_value();
						
						int tableCount = 0;
//						if (item.getDefault_param_value() != null && item.getDefault_param_value().contains(",")) {
							tableCount = getDefault_param_value.length() - getDefault_param_value.replace(String.valueOf(","), "").length()+1;
//						}
						
						logger.info("tableCount====="+tableCount);
						
						String[] arr_tbl_nm = new String[tableCount];
						
						if ( 
							( item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-001-01") ||
							  item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-002-01") ) && 
							  item.getGubun().equals("0") ) { // BUS AVAIL 경우 
							user_id = item.getDefault_param_value();
						} else {

							if ( tableCount > 0 ) {
								
								if (item.getDefault_param_value() != null && item.getDefault_param_value().contains(",")) {
									arr_tbl_nm = item.getDefault_param_value().split(",");
								} else {
									arr_tbl_nm[0] = item.getDefault_param_value();
									
									// 배열 나머지 요소는 null 로 채움
									for(int j = 1; j < tableCount; j++){
										arr_tbl_nm[j] = null;
									}								
								}
								
							}
								
						}
						
	//					logger.info("2. arr_tbl_nm====>"+Arrays.toString(arr_tbl_nm));
						
			            //============================================================
			            //< spike_id
			            //============================================================					
						grids.setUser_id(item.getSpike_id());
						
			            //============================================================
			            //< 위젯ID^기능ID
			            //============================================================					
						depth_2.setComponentId(item.getWidget_grp_id()+"^"+item.getFunc_id()); // 위젯ID^기능ID
						
			            //============================================================
			            //< componentName (위젯명)
			            //============================================================										
						depth_2.setComponentName(item.getWidget_nm());
	
			            //============================================================
			            //< methods (권한)
			            //============================================================					
						depth_2.setMethods(arr_method);
	
						
			            //============================================================
			            //< API - self 영역 
						//< : 위젯 자체 API 호출. 예)칼럼 헤더
						//< : [behaviors] - 1
			            //============================================================ 
	//					BusA_depth_2 busA_depth_2 = new BusA_depth_2();
						List<UserGrids_depth_3> behaviors = new ArrayList<UserGrids_depth_3>();
						
			            //============================================================
			            //< type (self)
			            //============================================================
						if (item.getType().length()>0)
							depth_3.setType(item.getType());
						
			            //============================================================
			            //< method (POST/GET/PUT/DELETE/PATCH)
			            //============================================================
						if (item.getMethod().length()>0)
							depth_3.setMethod(item.getMethod());
						
			            //============================================================
			            //< uri
			            //============================================================
						if (item.getUri().length()>0)
							depth_3.setUri(item.getUri());
	
			            //============================================================
			            //< defaultParameter (user_id 또는 tbl_nm)
			            //============================================================					
//						if (user_id.length()>0)				
//							depth_4.setUser_id(user_id);
						
//						logger.info("Arrays.toString(arr_tbl_nm)===>"+Arrays.toString(arr_tbl_nm));
//						logger.info("arr_tbl_nm.length====="+arr_tbl_nm.length);
						
						if (arr_tbl_nm != null && arr_tbl_nm.length > 0) 
							depth_4.setTbl_nm(arr_tbl_nm);
						
						if (depth_4 != null)
							depth_3.setDefaultParameter(depth_4);
						if (depth_3 != null)
							behaviors.add(depth_3);
	
			            //============================================================
			            //< API - search 영역
						//< : API 업무 영역 (데이터 조회)
						//< : [behaviors] - 2
						//< : 콤마(,)로 구분된 type 갯수만큼 loop
			            //============================================================					
						
						// 콤마로 총 api 갯수 구하기 (콤마개수 + 1 해준다)
						String getType_a = item.getType_a();					
						int apiCount = getType_a.length() - getType_a.replace(String.valueOf(","), "").length()+1;
//						logger.info("apiCount==######################################===#@@##==>"+apiCount);				
						
						// TYPE
						String[] arr_type_a = new String[apiCount];
						if (item.getType_a() != null && item.getType_a().contains(",")) {
							arr_type_a = item.getType_a().split(",");
						} else {
							arr_type_a[0] = item.getType_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_type_a[j] = null;
							}							
						}
	
	//					logger.info("arr_type_a============>"+Arrays.toString(arr_type_a));
						
						// METHOD
						String[] arr_method_a = new String[apiCount];
						if (item.getMethod_a() != null && item.getMethod_a().contains(",")) {
							arr_method_a = item.getMethod_a().split(",");
						} else {
							arr_method_a[0] = item.getMethod_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_method_a[j] = null;
							}							
						}
						
						// URI
						String[] arr_uri_a = new String[apiCount];
						if (item.getUri_a() != null && item.getUri_a().contains(",")) {
							arr_uri_a = item.getUri_a().split(",");
						} else {
							arr_uri_a[0] = item.getUri_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_uri_a[j] = null;
							}							
						}
						
						// REQUIRED TARGET
						String[] arr_requiredTarget = new String[apiCount];
						if (item.getRequired_target() != null && item.getRequired_target().contains(",")) {
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
						if (item.getDefault_param_value_a() != null && item.getDefault_param_value_a().contains(",")) {
							arr_defaultParamValue_a = item.getDefault_param_value_a().split(",");
						} else {
							arr_defaultParamValue_a[0] = item.getDefault_param_value_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_defaultParamValue_a[j] = null;
							}							
						}
						
	//					logger.info("arr_defaultParamValue============>"+Arrays.toString(arr_defaultParamValue_a));
						
						// REQURED PARAM
						String[] arr_requiredParam = new String[apiCount];
						if (item.getRequired_param() != null && item.getRequired_param().contains("`")) {
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
						if (item.getPermission() != null && item.getPermission().contains("`")) {
							arr_permission = item.getPermission().split("`");
						} else {
							arr_permission[0] = item.getPermission();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_permission[j] = null;
							}						
						}					
						
						
	//					logger.info("@@@@@arr_type_a.length@@@@@@===>"+arr_type_a.length);
						// loop 시작 
						for(int i = 0; i < apiCount; i++){
	//					logger.info("@@@@@iiiii@@@@@@===>"+i);
							UserGrids_depth_3 depth_3_A = new UserGrids_depth_3();
							UserGrids_depth_4 depth_4_A = new UserGrids_depth_4();
							List<UserGrids_depth_3> behaviors_A = new ArrayList<UserGrids_depth_3>();
							
				            //============================================================
				            //< type
				            //============================================================
							if (arr_type_a[i] != null && arr_type_a[i].length() > 0)
								depth_3_A.setType(arr_type_a[i]);
							
				            //============================================================
				            //< method (POST/GET/PUT/DELETE/PATCH)
				            //============================================================
							if (arr_method_a[i] != null && arr_method_a[i].length() > 0)
								depth_3_A.setMethod(arr_method_a[i]);
							
							//============================================================
				            //< uri
				            //============================================================
							if (arr_uri_a[i] != null && arr_uri_a[i].length() > 0)
								depth_3_A.setUri(arr_uri_a[i]);
	
							//============================================================
				            //< requiredTarget
				            //============================================================
							if (arr_requiredTarget[i] != null && arr_requiredTarget[i].length() > 0)
								depth_3_A.setRequiredTarget(arr_requiredTarget[i]);						
	
							//============================================================
				            //< defaultParameter (IF_ID)
				            //============================================================
							
							logger.info("arr_defaultParamValue_a[i]===>"+arr_defaultParamValue_a[i]);
							logger.info("arr_defaultParamValue_a[i].length()====="+arr_defaultParamValue_a[i].length());							
							
							
							if (arr_defaultParamValue_a[i] != null && arr_defaultParamValue_a[i].length() > 0)
								depth_4_A.setIf_id(arr_defaultParamValue_a[i]);
	
							if (depth_4_A != null)
								depth_3_A.setDefaultParameter(depth_4_A);
							
							//============================================================
				            //< requiredParameter
				            //============================================================						
							if (arr_requiredParam[i] != null && arr_requiredParam[i].length() > 0) {
								String[] each_arr_requiredParam = new String[1];
								if (arr_requiredParam[i].toString() != null && arr_requiredParam[i].toString().contains(",")) {
									each_arr_requiredParam = arr_requiredParam[i].toString().split(",");
								} else {
									each_arr_requiredParam[0] = arr_requiredParam[i];
//JDY 아래 4줄은 의심									
//									// 배열 나머지 요소는 null 로 채움
//									for(int j = 1; j < apiCount; j++){
//										each_arr_requiredParam[j] = null;
//									}									
								}
								depth_3_A.setRequiredParameter(each_arr_requiredParam);
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
								depth_3_A.setPermission(intArray);						
							}
							if (depth_3_A != null)
								behaviors_A.add(depth_3_A);
							
							if (behaviors_A != null)
								behaviors.addAll(behaviors_A);
	
						// loop 끝
						}
						if (behaviors != null)
							depth_2.setBehaviors(behaviors);
						
	// [POINT]          아래 선언을, loop 시작 전에 선언					
	//					List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
						if (depth_2 != null)
							result.add(depth_2);
						
	//					logger.info("rowCount =##################>"+rowCount);					
	//					logger.info("size =##################>"+datas.size());
	
						logger.info("flag_info =>"+result);
						
						data.setResult(result);
						
						if ( rowCount == datas.size()) {
							logger.info("INSERT!!!>");
							// REDIS Insert
							UserGrids insertGrids = null;
							gridsService_v3.getUser(item.getSpike_id());
							insertGrids = gridsService_v3.registerUser(item.getSpike_id(), data);							
//							BusA insertBusA = null;
//							busAService_v4.getUser(item.getSpike_id());
//							insertBusA = busAService_v4.registerUser(item.getSpike_id(), flag_info);
						}
						
	//					return new ResponseEntity<>(insertBusA, HttpStatus.OK);
					}  // end of for (VMatrix item : datas)
	
					
					
				} else {
				} // end of if (datas.size() > 0)			
			
			}
			

		} catch (Exception e) {
			logger.error("[VMatrixServiceImpl.getMatrixList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	
	
	
	/**
	 * <p>SELECT - INSERT (Matrix - UserDetails)</p>
		 * <ul>
		 * 	<li>매트릭스 데이터에서 Details를 조회하고, UserDetails Json 데이터를 생성한다.  </li>
	     * </ul>
	 * @param vMatrix 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */
	@Override
	public String getMatrixListDetails(@Param("V_MATRIX") VMatrix vMatrix) throws Exception {
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
			List<VMatrix> users = vMatrixMappers.getAllSpikeIdList();
//			List<VMatrix> datas = vMatrixMappers.getMatrixList(vMatrix.getGubun());

			for (VMatrix user : users) {
				logger.info("user.getSpike_id()==>"+user.getSpike_id());	
			
				List<VMatrix> datas = vMatrixMappers.getSpikeIdMatrixList(vMatrix.getGubun(), user.getSpike_id()); 
				
				
				UserDetails_depth_1 data = new UserDetails_depth_1();
	// [POINT]  아래 선언을, loop 시작 전에 선언
				List<UserDetails_depth_2> result = new ArrayList<UserDetails_depth_2>();
//				List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
				
				int rowCount = 0;
				
				if (datas.size() > 0) {
					
					for (VMatrix item : datas) {
						
						rowCount++;
						
						UserDetails details = new UserDetails();
						UserDetails_depth_1 depth_1 = new UserDetails_depth_1();
						UserDetails_depth_2 depth_2 = new UserDetails_depth_2();
						UserDetails_depth_3 depth_3 = new UserDetails_depth_3();
						UserDetails_depth_4 depth_4 = new UserDetails_depth_4();
						
//						BusA busA = new BusA();
//						BusA_depth_1 busA_depth_1 = new BusA_depth_1();
//						BusA_depth_2 busA_depth_2 = new BusA_depth_2();
//						BusA_depth_3 busA_depth_3 = new BusA_depth_3();
						
						
						
						logger.info("item.getSpike_id()==>"+item.getSpike_id());
						
						//methods 배열
						String[] arr_method = item.getMethods().split("");
						String user_id = "";
						
						// Default_param_value 에서 콤마(,) 개수 구하기
	//					String paramValue = item.getDefault_param_value();					
	//					int commaCount = paramValue.length() - paramValue.replace(String.valueOf(","), "").length();
	//					logger.info("commaCount=====#@@##==>"+commaCount);
	//					String[] arr_tbl_nm = new String[commaCount-1];
						
						// 굳이 위 처럼 전체 배열 크리를 먼저 구하지 않고, 아래에서 콤마(,) 갯수에 의해 크기가 할당 되니까, 우선 1사이즈로 초기화 하자
//						String[] arr_tbl_nm = new String[1];
						
	//					logger.info("1. item.getDefault_param_value()====>"+item.getDefault_param_value());
						
			            //============================================================
			            //< defaultParameter : user_id 또는 tbl_nm
						//< user_id : (공통)_그리드, (공통)_디테일
						//< tbl_mm : 그 외 전부
			            //============================================================
						// 콤마로 총 테이블 갯수 구하기 (콤마개수 + 1 해준다) : (공통)_그리드, (공통)_디테일 이 아닌 경우만 해당
						String getDefault_param_value = item.getDefault_param_value();
						
						int tableCount = 0;
//						if (item.getDefault_param_value() != null && item.getDefault_param_value().contains(",")) {
							tableCount = getDefault_param_value.length() - getDefault_param_value.replace(String.valueOf(","), "").length()+1;
//						}
						
						logger.info("tableCount====="+tableCount);
						
						String[] arr_tbl_nm = new String[tableCount];
						
						if ( 
							( item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-001-01") ||
							  item.getWidget_grp_id().equals("CMC-003") &&  item.getFunc_id().equals("FU-002-01") ) && 
							  item.getGubun().equals("0") ) { // BUS AVAIL 경우 
							user_id = item.getDefault_param_value();
						} else {

							if ( tableCount > 0 ) {
								
								if (item.getDefault_param_value() != null && item.getDefault_param_value().contains(",")) {
									arr_tbl_nm = item.getDefault_param_value().split(",");
								} else {
									arr_tbl_nm[0] = item.getDefault_param_value();
									
									// 배열 나머지 요소는 null 로 채움
									for(int j = 1; j < tableCount; j++){
										arr_tbl_nm[j] = null;
									}								
								}
								
							}
								
						}
						
	//					logger.info("2. arr_tbl_nm====>"+Arrays.toString(arr_tbl_nm));
						
			            //============================================================
			            //< spike_id
			            //============================================================					
						details.setUser_id(item.getSpike_id());
						
			            //============================================================
			            //< 위젯ID^기능ID
			            //============================================================					
						depth_2.setComponentId(item.getWidget_grp_id()+"^"+item.getFunc_id()); // 위젯ID^기능ID
						
			            //============================================================
			            //< componentName (위젯명)
			            //============================================================										
						depth_2.setComponentName(item.getWidget_nm());
	
			            //============================================================
			            //< methods (권한)
			            //============================================================					
						depth_2.setMethods(arr_method);
	
						
			            //============================================================
			            //< API - self 영역 
						//< : 위젯 자체 API 호출. 예)칼럼 헤더
						//< : [behaviors] - 1
			            //============================================================ 
	//					BusA_depth_2 busA_depth_2 = new BusA_depth_2();
						List<UserDetails_depth_3> behaviors = new ArrayList<UserDetails_depth_3>();
						
			            //============================================================
			            //< type (self)
			            //============================================================
						if (item.getType().length()>0)
							depth_3.setType(item.getType());
						
			            //============================================================
			            //< method (POST/GET/PUT/DELETE/PATCH)
			            //============================================================
						if (item.getMethod().length()>0)
							depth_3.setMethod(item.getMethod());
						
			            //============================================================
			            //< uri
			            //============================================================
						if (item.getUri().length()>0)
							depth_3.setUri(item.getUri());
	
			            //============================================================
			            //< defaultParameter (user_id 또는 tbl_nm)
			            //============================================================					
						if (user_id.length()>0)				
							depth_4.setUser_id(user_id);
						
//						logger.info("Arrays.toString(arr_tbl_nm)===>"+Arrays.toString(arr_tbl_nm));
//						logger.info("arr_tbl_nm.length====="+arr_tbl_nm.length);
						
						if (arr_tbl_nm != null && arr_tbl_nm.length > 0) 
							depth_4.setTbl_nm(arr_tbl_nm);
						
						if (depth_4 != null)
							depth_3.setDefaultParameter(depth_4);
						if (depth_3 != null)
							behaviors.add(depth_3);
	
			            //============================================================
			            //< API - search 영역
						//< : API 업무 영역 (데이터 조회)
						//< : [behaviors] - 2
						//< : 콤마(,)로 구분된 type 갯수만큼 loop
			            //============================================================					
						
						// 콤마로 총 api 갯수 구하기 (콤마개수 + 1 해준다)
						String getType_a = item.getType_a();					
						int apiCount = getType_a.length() - getType_a.replace(String.valueOf(","), "").length()+1;
//						logger.info("apiCount==######################################===#@@##==>"+apiCount);				
						
						// TYPE
						String[] arr_type_a = new String[apiCount];
						if (item.getType_a() != null && item.getType_a().contains(",")) {
							arr_type_a = item.getType_a().split(",");
						} else {
							arr_type_a[0] = item.getType_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_type_a[j] = null;
							}							
						}
	
	//					logger.info("arr_type_a============>"+Arrays.toString(arr_type_a));
						
						// METHOD
						String[] arr_method_a = new String[apiCount];
						if (item.getMethod_a() != null && item.getMethod_a().contains(",")) {
							arr_method_a = item.getMethod_a().split(",");
						} else {
							arr_method_a[0] = item.getMethod_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_method_a[j] = null;
							}							
						}
						
						// URI
						String[] arr_uri_a = new String[apiCount];
						if (item.getUri_a() != null && item.getUri_a().contains(",")) {
							arr_uri_a = item.getUri_a().split(",");
						} else {
							arr_uri_a[0] = item.getUri_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_uri_a[j] = null;
							}							
						}
						
						// REQUIRED TARGET
						String[] arr_requiredTarget = new String[apiCount];
						if (item.getRequired_target() != null && item.getRequired_target().contains(",")) {
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
						if (item.getDefault_param_value_a() != null && item.getDefault_param_value_a().contains(",")) {
							arr_defaultParamValue_a = item.getDefault_param_value_a().split(",");
						} else {
							arr_defaultParamValue_a[0] = item.getDefault_param_value_a();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_defaultParamValue_a[j] = null;
							}							
						}
						
	//					logger.info("arr_defaultParamValue============>"+Arrays.toString(arr_defaultParamValue_a));
						
						// REQURED PARAM
						String[] arr_requiredParam = new String[apiCount];
						if (item.getRequired_param() != null && item.getRequired_param().contains("`")) {
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
						if (item.getPermission() != null && item.getPermission().contains("`")) {
							arr_permission = item.getPermission().split("`");
						} else {
							arr_permission[0] = item.getPermission();
							
							// 배열 나머지 요소는 null 로 채움
							for(int j = 1; j < apiCount; j++){
								arr_permission[j] = null;
							}						
						}					
						
						
	//					logger.info("@@@@@arr_type_a.length@@@@@@===>"+arr_type_a.length);
						// loop 시작 
						for(int i = 0; i < apiCount; i++){
	//					logger.info("@@@@@iiiii@@@@@@===>"+i);
							UserDetails_depth_3 depth_3_A = new UserDetails_depth_3();
							UserDetails_depth_4 depth_4_A = new UserDetails_depth_4();
							List<UserDetails_depth_3> behaviors_A = new ArrayList<UserDetails_depth_3>();
//							BusA_depth_2 busA_depth_2_A = new BusA_depth_2();
//							BusA_depth_3 busA_depth_3_A = new BusA_depth_3();
//							List<BusA_depth_2> behaviors_A = new ArrayList<BusA_depth_2>();
							
				            //============================================================
				            //< type
				            //============================================================
							if (arr_type_a[i] != null && arr_type_a[i].length() > 0)
								depth_3_A.setType(arr_type_a[i]);
							
				            //============================================================
				            //< method (POST/GET/PUT/DELETE/PATCH)
				            //============================================================
							if (arr_method_a[i] != null && arr_method_a[i].length() > 0)
								depth_3_A.setMethod(arr_method_a[i]);
							
							//============================================================
				            //< uri
				            //============================================================
							if (arr_uri_a[i] != null && arr_uri_a[i].length() > 0)
								depth_3_A.setUri(arr_uri_a[i]);
	
							//============================================================
				            //< requiredTarget
				            //============================================================
							if (arr_requiredTarget[i] != null && arr_requiredTarget[i].length() > 0)
								depth_3_A.setRequiredTarget(arr_requiredTarget[i]);						
	
							//============================================================
				            //< defaultParameter (IF_ID)
				            //============================================================
							
							logger.info("arr_defaultParamValue_a[i]===>"+arr_defaultParamValue_a[i]);
							logger.info("arr_defaultParamValue_a[i].length()====="+arr_defaultParamValue_a[i].length());							
							
							
							if (arr_defaultParamValue_a[i] != null && arr_defaultParamValue_a[i].length() > 0)
								depth_4_A.setIf_id(arr_defaultParamValue_a[i]);
	
							if (depth_4_A != null)
								depth_3_A.setDefaultParameter(depth_4_A);
							
							//============================================================
				            //< requiredParameter
				            //============================================================						
							if (arr_requiredParam[i] != null && arr_requiredParam[i].length() > 0) {
								String[] each_arr_requiredParam = new String[1];
								if (arr_requiredParam[i].toString() != null && arr_requiredParam[i].toString().contains(",")) {
									each_arr_requiredParam = arr_requiredParam[i].toString().split(",");
								} else {
									each_arr_requiredParam[0] = arr_requiredParam[i];
//JDY 아래 4줄은 의심									
//									// 배열 나머지 요소는 null 로 채움
//									for(int j = 1; j < apiCount; j++){
//										each_arr_requiredParam[j] = null;
//									}									
								}
								depth_3_A.setRequiredParameter(each_arr_requiredParam);
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
								depth_3_A.setPermission(intArray);						
							}
							if (depth_3_A != null)
								behaviors_A.add(depth_3_A);
							
							if (behaviors_A != null)
								behaviors.addAll(behaviors_A);
	
						// loop 끝
						}
						if (behaviors != null)
							depth_2.setBehaviors(behaviors);
						
	// [POINT]          아래 선언을, loop 시작 전에 선언					
	//					List<BusA_depth_1> flag_info = new ArrayList<BusA_depth_1>();
						if (depth_2 != null)
							result.add(depth_2);
						
	//					logger.info("rowCount =##################>"+rowCount);					
	//					logger.info("size =##################>"+datas.size());
	
						logger.info("flag_info =>"+result);
						
						data.setResult(result);
						
						if ( rowCount == datas.size()) {
							logger.info("INSERT!!!>");
							// REDIS Insert
							UserDetails insertDetails = null;
							detailsService_v3.getUser(item.getSpike_id());
							insertDetails = detailsService_v3.registerUser(item.getSpike_id(), data);							
//							BusA insertBusA = null;
//							busAService_v4.getUser(item.getSpike_id());
//							insertBusA = busAService_v4.registerUser(item.getSpike_id(), flag_info);
						}
						
	//					return new ResponseEntity<>(insertBusA, HttpStatus.OK);
					}  // end of for (VMatrix item : datas)
	
					
					
				} else {
				} // end of if (datas.size() > 0)			
			
			}
			

		} catch (Exception e) {
			logger.error("[VMatrixServiceImpl.getMatrixList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	

	
}
