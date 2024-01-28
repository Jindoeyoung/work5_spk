package com.spk.api.service.pms.api;

import java.util.List;

//import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.spk.api.entity.pms.api.ApiMst;
import com.spk.api.entity.pms.api.ApiMstList;
import com.spk.api.entity.pms.api.ApiMstParam;
import com.spk.api.error.EResultCode;
import com.spk.api.mapper.pms.api.ApiMstInfoMapper;
import com.spk.api.mapper.pms.api.ApiMstParamInfoMapper;
import com.spk.api.security.AuthCheck;
import com.spk.api.util.ReturnException;
import com.spk.api.util.Utils;

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
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>시스템메뉴정보 등록 리스트를 조회한다 </li>
	     * </ul>
	 * @param pSysMenuInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String getApiMstList(@Param("SYS_MENU_INFO") ApiMst pApiMst) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pApiMst.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pApiMst.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<ApiMst> apiMst = apiMstMapper.getApiMstList(pApiMst);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
//			dataResult.addProperty("menu_id", pApiMst.getMenu_id());
			
			if (apiMst.size() > 0) {
				for (ApiMst item : apiMst) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("api_id", item.getApi_id());
					Obj1.addProperty("api_nm", item.getApi_nm());
					Obj1.addProperty("method", item.getMethod());
					Obj1.addProperty("uri", item.getUri());
					Obj1.addProperty("func_cd", item.getFunc_cd());
					Obj1.addProperty("rel_api_id", item.getRel_api_id());
					Obj1.addProperty("timeout", item.getTimeout());
					
					jsonArr1.add(Obj1);
					
					dataResult.add("data", jsonArr1);
				}
			} else {
				dataResult.add("flag_info", jsonArr1);
			}
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuInfoList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	/**
	 * <p>INSERT</p>
		 * <ul>
		 * 	<li>API 정보를 등록한다 </li>
	     * </ul>
	 * @param apiMst 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class)
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
		JsonArray jsonArr1 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		Utils utils = new Utils();
		
		String Message = "SUCCESS";
		String Success = "1";
		int result = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		String if_id = null;
		String rel_if_id = null;
		String func_cd = null;
		int i = 0;
		
		try {
			List<ApiMst> datas = apiMstList.getData();
			
			for (ApiMst item : datas) {
				i++;
				// API_MST 저장
				result = apiMstMapper.insertApiMstInfo(item, apiMstList.getSpike_id());
				
				List<ApiMstParam> datas2 = item.getApi_param();
				for (ApiMstParam item2 : datas2) {
					// API_MST_PARAM 저장
					result2 = apiMstParamMapper.insertApiMstParamInfo(item2, apiMstList.getSpike_id());
				}
				
				if (i == 1) {
					if_id = item.getApi_id();
					func_cd = item.getFunc_cd();
				}
				
				if (i == 2)
					rel_if_id = item.getApi_id();
			}
			
			
	        //============================================================
	        //< REL_API_ID 업데이트
	        //============================================================			
			if ( rel_if_id != null && rel_if_id.length() > 0)
				result3 = apiMstMapper.updateApiMstRelApiId(if_id, rel_if_id);
			
	        //============================================================
	        //< MATRIX insert
	        //============================================================			
			
			// 박부장 SQL 로 select insert 함
			// 여기서 2가지 타입
			
			// 1) GRID  
			// 1)-> search type 에 api_Id where 조건에 던지고
			// 2)   update type 에 1=2 를 던진다 
			
			// 2) GRID-SAVE
			// 1)-> search type 에 api_Id where 조건에 던지고
			// 2)   update type 에 rel_api_id 던짐 
			
			// 그냥, GRID 에 대한 api_id 를 search type 에 넣는다
			
			// procedure 수행
			// 파라미터로 받은 spike_id는 owner 로서, SUPER 12명과 더불어 추가로 매트릭스 부여받게 됨
			// 정리) API 를 추가하면, SUPER12명 + 파라미터로 받은 owner (spike_id)
			result4 = apiMstMapper.insertMatrixInfo(if_id, apiMstList.getSpike_id());
			
			
			
//			logger.info("============ START ==================");
//			try {
//			  // 5초 동안 현재 스레드 정지
//			  Thread.sleep(5000);
//			} catch (InterruptedException e) {
//			  e.printStackTrace();
//			}
//			logger.info("============ END ==================");
			
			
	        //============================================================
	        //< MATRIX(MySql) -> FLAG(Redis) 전송
	        //============================================================			

			String apiUrl = null;
			
			if (("GRID".equals(func_cd)) || ("PRINT".equals(func_cd)) ) {
//				apiUrl = "http://210.123.228.102:8445/routerspk/matrix/grids";  // 남부대 개발
//				apiUrl = "http://localhost:8443/routerspk/matrix/grids";		// 로컬
//				apiUrl = "http://10.1.193.1:8443/routerspk/matrix/grids"; 		// 본사 개발(내부) 
				apiUrl = "http://221.133.61.193:8443/routerspk/matrix/grids";   // 본사 개발(외부)
			} else if ("DETAIL".equals(func_cd)) {
//				apiUrl = "http://210.123.228.102:8445/routerspk/matrix/details"; // 남부대 개발
//				apiUrl = "http://localhost:8443/routerspk/matrix/details";		 // 로컬
//				apiUrl = "http://10.1.193.1:8443/routerspk/matrix/details";		 // 본사 개발(내부)
				apiUrl = "http://221.133.61.193:8443/routerspk/matrix/details";  // 본사 개발(외부)
			} else if ("REPORT".equals(func_cd)) {
//				apiUrl = "http://210.123.228.102:8445/routerspk/matrix/prints";  // 남부대 개발
//				apiUrl = "http://localhost:8443/routerspk/matrix/prints";		 // 로컬
//				apiUrl = "http://10.1.193.1:8443/routerspk/matrix/prints";		 // 본사 개발(내부)
				apiUrl = "http://221.133.61.193:8443/routerspk/matrix/prints";	 // 본사 개발(외부)			
			}
			
	        // RestTemplate 객체 생성
	        RestTemplate restTemplate = new RestTemplate();

	        // Request Body에 담을 데이터
//	        String requestBody = "{\"spike_id\": \"%\"}";
	        String requestBody = "{\"apikey\": \"BJDGye3B/cyODiJhIbNWNh6j6K3lHtqLAtTdjORKQ6E=\", \"spike_id\": \"%\"}";

	        // HTTP 요청 헤더 설정
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // HttpEntity 생성
	        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

	        // API 호출 및 응답 받기
	        String result_param = restTemplate.postForObject(apiUrl, requestEntity, String.class);			
			
//			final String apiUrl = "http://localhost:8443/routerspk/matrix/grids";
//			
//	        // WebClient 객체 생성
//	        WebClient webClient = WebClient.create();
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//	        	        
//	        
//	        // Request Body에 담을 데이터
//	        String requestBody = "{\"spike_id\": \"%\"}";
//	        
//	        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//	        // API 호출 및 응답 받기
//	        String result_api = webClient.post()
//	                .uri(apiUrl)
//	                .body(BodyInserters.fromValue(requestBody))
//	                .retrieve()
//	                .bodyToMono(String.class)
//	                .block();
			
			
//			logger.info("result@@@@@@@@@>"+result);
//			logger.info("result2########>"+result2);
			
			
//			if (result < 1 || result2 < 1) {
//				throw new RuntimeException("API_MST Exception");
//			}
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			jsonArr1.add(Obj1);
			dataResult.add("flag_info", jsonArr1);
			
		} catch (DuplicateKeyException e){
			logger.error("[ApiMstInfoServiceImpl.insertApiMst] ERROR : DuplicateKeyException : " + e);

			JsonObject results = new JsonObject();
			results = utils.getMetaErrGenerator2(2000);
			e.printStackTrace();
			throw new ReturnException(results, "DuplicateKeyException 발생");			
			
		} catch (Exception e) {
			logger.error("[ApiMstInfoServiceImpl.insertApiMst] ERROR : " + e);
			
			JsonObject results = new JsonObject();
			results = utils.getMetaErrGenerator2(1000);			
			e.printStackTrace();
			throw new ReturnException(results, "Exception 발생");
		}
		return dataResult.toString();
	}
	
	/**
	 * <p>DELETE</p>
		 * <ul>
		 * 	<li>API 마스터를 삭제한다. </li>
		 * 	<li>API_MST, API_MST_PARAM, A_MATRIX 테이블 동시에 삭제한다. </li> 
	     * </ul>
	 * @param apiMstList 클라이언트에서 요청받은 API마스터 정보
	 * @return String
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String deleteApiMst(@Param("API_MST") ApiMstList apiMstList) throws Exception {
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
		JsonArray jsonArr1 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		Utils utils = new Utils();
		String matrix_api_id = "";
		int results = 0;
		int results2 = 0;
		int results3 = 0;
		
		try {
			
			List<ApiMst> datas = apiMstList.getData();
			
			for (ApiMst item : datas) {
	            //============================================================
	            //< API_MST 삭제
	            //============================================================				
				results = apiMstMapper.deleteApiMstInfo(item);
	            //============================================================
	            //< API_MST_PARAM 삭제
	            //============================================================				
				results2 = apiMstMapper.deleteApiMstParamInfo(item);
				//============================================================
	            //< A_MATRIX 삭제를 위한 API 묶음 생성 (,(콤마) 구분자)
	            //============================================================				
				matrix_api_id += item.getApi_id();
				matrix_api_id += ",";
			}
            //============================================================
            //< api_id 마지막 콤마(,) 제거
            //============================================================
			matrix_api_id = matrix_api_id.substring(0, matrix_api_id.length()-1);
//			logger.info("matrix_api_id==>"+matrix_api_id);
			
			results3 = apiMstMapper.deleteMatrixInfo(matrix_api_id);

			if ( results >= 0 && results2 >= 0 && results3 >= 0) {
				dataResult.addProperty("reason", EResultCode.SUCCESS.getResultMessage());
				dataResult.addProperty("result", EResultCode.SUCCESS.getResultCode());
			} else {
				dataResult.addProperty("reason", EResultCode.FAIL.getResultMessage());
				dataResult.addProperty("result", EResultCode.FAIL.getResultCode());
			}
			jsonArr1.add(Obj1);
			dataResult.add("data", jsonArr1);			
			
			
		} catch (Exception e) {
			logger.error("[ApiMstInfoServiceImpl.deleteApiMst] ERROR : " + e);
			
			JsonObject result = new JsonObject();
			result = utils.getMetaErrGenerator3(EResultCode.FAILED_DELETE);		
			e.printStackTrace();
			throw new ReturnException(result, EResultCode.FAILED_DELETE.getResultMessage());				
		}
		return dataResult.toString();
	}
	
}
