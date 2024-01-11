package com.spk.api.service.pms.api;

import java.util.List;

//import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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
		int result3 = 0;
		int result4 = 0;
		String if_id = null;
		String rel_if_id = null;
		int i = 0;
		
		try {
			List<ApiMst> datas = apiMstList.getData();
			
			for (ApiMst item : datas) {
				i++;
				// API_MST 저장
				result = apiMstMapper.insertApiMstInfo(item);
				
				List<ApiMstParam> datas2 = item.getApi_param();
				for (ApiMstParam item2 : datas2) {
					// API_MST_PARAM 저장
					result2 = apiMstParamMapper.insertApiMstParamInfo(item2);
				}
				
				if (i == 1)
					if_id = item.getApi_id();
				
				if (i == 2)
					rel_if_id = item.getApi_id();
			}
			
			
	        //============================================================
	        //< REL_API_ID 업데이트
	        //============================================================			
			
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
			
			
			result4 = apiMstMapper.insertMatrixInfo(if_id);
			
			
			
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

			final String apiUrl = "http://localhost:8443/routerspk/matrix/grids";			
			
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
			
			
			
			
			
			if (result < 1 || result2 < 1 || result3 < 1) {
				throw new RuntimeException("API_MST Exception");
			}
			
			
			
			
		} catch (Exception e) {
			logger.error("[ApiMstInfoServiceImpl.insertApiMst] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	

	
}
