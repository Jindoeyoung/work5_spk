package com.spk.api.service.udr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.udr.UDR01Entity;
import com.spk.api.entity.udr.UDR01EntityResult;
import com.spk.api.mapper.udr.UDR01Mapper;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UDR01ServiceImle implements UDR01Service {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UDR01Mapper udr01Mapper;	
	
	AuthCheck authcheck = new AuthCheck();
	
	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>등록금 내역을 조회한다 </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 학적정보
	 * @return String
	 */	
	@Override
	public String getRegistAmtList(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<UDR01Entity> udr01Entity = udr01Mapper.getRegistAmtList(pUdr01Entity);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
	
			if (udr01Entity.size() > 0) {
				for (UDR01Entity item : udr01Entity) {
					JsonObject Obj1 = new JsonObject();
					JsonObject Obj2 = new JsonObject();
	
					Obj1.addProperty("year", item.getYear());
					Obj1.addProperty("semester", item.getSemester());
					Obj1.addProperty("dept", item.getDept());
					Obj1.addProperty("grade", item.getGrade());
//					Obj1.addProperty("deptname", item.getDeptname());
					Obj1.addProperty("entr_amt", item.getEntr_amt());
					Obj1.addProperty("edu_amt", item.getEdu_amt());
					Obj1.addProperty("std_amt1", item.getStd_amt1());
					Obj1.addProperty("std_amt2", item.getStd_amt2());
					Obj1.addProperty("std_amt3", item.getStd_amt3());
					Obj1.addProperty("std_amt1", item.getStd_amt4());
					Obj1.addProperty("std_amt1", item.getStd_amt5());				
					Obj1.addProperty("std_amt1", item.getStd_amt6());
							
					jsonArr1.add(Obj1);
	
					Obj2.add("result", jsonArr1);
					dataResult.add("data", Obj2);
				}
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
			
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.getRegistAmtList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	/**
	 * <p>INSERT</p>
		 * <ul>
		 * 	<li>등록금 내역을 저장한다. </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 등록정보
	 * @return String
	 */	
	@Override
	public String insertRegistAmt(@Param("ST_HAKJEOK_M") UDR01Entity pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
		}	
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		JsonObject Obj3 = new JsonObject();	
		
		String OK_MESSAGE = "SUCCESS";
		String NOT_OK_MESSAGE = "FAIL";
		
		try {
			int results = udr01Mapper.insertRegistAmt(pUdr01Entity);

	        //============================================================
	        //< Respnse Data (Sample)
			//< API 호출 시 공통으로 리턴받는 Response Data
	        //============================================================			
			String if_id = "IF-001-001";				// API id
			String version = "v1.0";					// API version
			String m_code = "HAK000";					// Menu Code
			String s_code = "HAK001";					// Sub Menu Code
			String response_format = "json";			// 반환 data 타입
			String result = "";							// 응답코드 4자리
			String reason = "";							// 응답 결과 메세지 ("성공", "필수항목 누락" 등)
			String server_time = "2021-04-13 15:40:40";	//응답 시 서버 시간
			String response_time = "132";				// 서버에서 처리한 경과 시간(milliseconds)
			// 인터페이스별로 반환되는 데이터가 있을 경우 "datas" Object 하위로 반환
			// 추가 반환할 데이터가 없을 경우 null 또는 ""(빈스트링) 반환
			
			if (results == 1) {
				dataResult.addProperty("reason", OK_MESSAGE);
				result = "0000";
				reason = "성공";
			} else {
				dataResult.addProperty("reason", NOT_OK_MESSAGE);
				result = "0001";	
				reason = "필수항목누락";				
			}
			dataResult.addProperty("result", results);
			
			Obj1.addProperty("if_id", if_id);
			Obj1.addProperty("version", version);
			Obj1.addProperty("m_code", m_code);
			Obj1.addProperty("s_code", s_code);
			Obj1.addProperty("response_format", response_format);
			Obj1.addProperty("result", result);
			Obj1.addProperty("reason", reason);
			Obj1.addProperty("server_time", server_time);
			Obj1.addProperty("response_time", response_time);

			jsonArr2.add(Obj2);
			Obj1.add("datas", jsonArr2);			
			
			jsonArr1.add(Obj1);
			Obj3.add("result", jsonArr1);
			
			dataResult.add("data", Obj3);
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.insertRegistAmt] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	
	/**
	 * <p>UPDATE</p>
		 * <ul>
		 * 	<li>등록금 내역을 수정한다. </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 등록정보
	 * @return String
	 */	
	@Override
	public String updateRegistAmt(@Param("ST_HAKJEOK_M") UDR01Entity pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
		}	
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		JsonObject Obj3 = new JsonObject();	
		
		String OK_MESSAGE = "SUCCESS";
		String NOT_OK_MESSAGE = "FAIL";
		
		try {
			int results = udr01Mapper.updateRegistAmt(pUdr01Entity);

	        //============================================================
	        //< Respnse Data (Sample)
			//< API 호출 시 공통으로 리턴받는 Response Data
	        //============================================================			
			String if_id = "IF-001-001";				// API id
			String version = "v1.0";					// API version
			String m_code = "HAK000";					// Menu Code
			String s_code = "HAK001";					// Sub Menu Code
			String response_format = "json";			// 반환 data 타입
			String result = "";							// 응답코드 4자리
			String reason = "";							// 응답 결과 메세지 ("성공", "필수항목 누락" 등)
			String server_time = "2021-04-13 15:40:40";	//응답 시 서버 시간
			String response_time = "132";				// 서버에서 처리한 경과 시간(milliseconds)
			// 인터페이스별로 반환되는 데이터가 있을 경우 "datas" Object 하위로 반환
			// 추가 반환할 데이터가 없을 경우 null 또는 ""(빈스트링) 반환
			
			if (results == 1) {
				dataResult.addProperty("reason", OK_MESSAGE);
				result = "0000";
				reason = "성공";
			} else {
				dataResult.addProperty("reason", NOT_OK_MESSAGE);
				result = "0001";	
				reason = "필수항목누락";				
			}
			dataResult.addProperty("result", results);
			
			Obj1.addProperty("if_id", if_id);
			Obj1.addProperty("version", version);
			Obj1.addProperty("m_code", m_code);
			Obj1.addProperty("s_code", s_code);
			Obj1.addProperty("response_format", response_format);
			Obj1.addProperty("result", result);
			Obj1.addProperty("reason", reason);
			Obj1.addProperty("server_time", server_time);
			Obj1.addProperty("response_time", response_time);

			jsonArr2.add(Obj2);
			Obj1.add("datas", jsonArr2);			
			
			jsonArr1.add(Obj1);
			Obj3.add("result", jsonArr1);
			
			dataResult.add("data", Obj3);
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.updateRegistAmt] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	
	/**
	 * <p>DELETE</p>
		 * <ul>
		 * 	<li>등록금 내역을 삭제한다. </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 등록정보
	 * @return String
	 */	
	@Override
	public String deleteRegistAmt(@Param("ST_HAKJEOK_M") UDR01Entity pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
		}	
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		JsonObject Obj3 = new JsonObject();	
		
		String OK_MESSAGE = "SUCCESS";
		String NOT_OK_MESSAGE = "FAIL";
		
		try {
			int results = udr01Mapper.deleteRegistAmt(pUdr01Entity);

	        //============================================================
	        //< Respnse Data (Sample)
			//< API 호출 시 공통으로 리턴받는 Response Data
	        //============================================================			
			String if_id = "IF-001-001";				// API id
			String version = "v1.0";					// API version
			String m_code = "HAK000";					// Menu Code
			String s_code = "HAK001";					// Sub Menu Code
			String response_format = "json";			// 반환 data 타입
			String result = "";							// 응답코드 4자리
			String reason = "";							// 응답 결과 메세지 ("성공", "필수항목 누락" 등)
			String server_time = "2021-04-13 15:40:40";	//응답 시 서버 시간
			String response_time = "132";				// 서버에서 처리한 경과 시간(milliseconds)
			// 인터페이스별로 반환되는 데이터가 있을 경우 "datas" Object 하위로 반환
			// 추가 반환할 데이터가 없을 경우 null 또는 ""(빈스트링) 반환
			
			if (results == 1) {
				dataResult.addProperty("reason", OK_MESSAGE);
				result = "0000";
				reason = "성공";
			} else {
				dataResult.addProperty("reason", NOT_OK_MESSAGE);
				result = "0001";	
				reason = "필수항목누락";				
			}
			dataResult.addProperty("result", results);
			
			Obj1.addProperty("if_id", if_id);
			Obj1.addProperty("version", version);
			Obj1.addProperty("m_code", m_code);
			Obj1.addProperty("s_code", s_code);
			Obj1.addProperty("response_format", response_format);
			Obj1.addProperty("result", result);
			Obj1.addProperty("reason", reason);
			Obj1.addProperty("server_time", server_time);
			Obj1.addProperty("response_time", response_time);

			jsonArr2.add(Obj2);
			Obj1.add("datas", jsonArr2);			
			
			jsonArr1.add(Obj1);
			Obj3.add("result", jsonArr1);
			
			dataResult.add("data", Obj3);
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.updateRegistAmt] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}	
	
	/**
	 * <p>SAVE</p>
		 * <ul>
		 * 	<li>Multi-row 등록금 데이터를 CUD 플래그에 맞게 처리한다. </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 등록정보
	 * @return String
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveRegistAmt(@Param("ST_HAKJEOK_M") UDR01EntityResult pUdr01EntityResult) throws Exception {
//	public String saveRegistAmt(@Param("ST_HAKJEOK_M") List<UDR01Entity> pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
//		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
//			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
//		}	
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		JsonObject Obj3 = new JsonObject();	
		
		String OK_MESSAGE = "SUCCESS";
		String NOT_OK_MESSAGE = "FAIL";
		String crud_gb = "";
		int results = 0;
		
		try {
//			int results = udr01Mapper.saveRegistAmt(pUdr01Entity);
			
			List<UDR01Entity> datas = pUdr01EntityResult.getData();
//			List<UDR01Entity> datas = pUdr01Entity;
			
			for (UDR01Entity item : datas) {
//			for (UDR01Entity item : datas) {
				crud_gb = item.getCrud_gb();
			
		        //============================================================
		        //< SAVE
		        //============================================================				
				if (crud_gb.equals("C")) {
					results = udr01Mapper.insertRegistAmt(item);
				} else if (crud_gb.equals("U")) {
					results = udr01Mapper.updateRegistAmt(item);
				} else if (crud_gb.equals("D")) {
					results = udr01Mapper.deleteRegistAmt(item);
				}			
			
			}
			

	        //============================================================
	        //< Respnse Data (Sample)
			//< API 호출 시 공통으로 리턴받는 Response Data
	        //============================================================			
			String if_id = "IF-001-001";				// API id
			String version = "v1.0";					// API version
			String m_code = "HAK000";					// Menu Code
			String s_code = "HAK001";					// Sub Menu Code
			String response_format = "json";			// 반환 data 타입
			String result = "";							// 응답코드 4자리
			String reason = "";							// 응답 결과 메세지 ("성공", "필수항목 누락" 등)
			String server_time = "2021-04-13 15:40:40";	//응답 시 서버 시간
			String response_time = "132";				// 서버에서 처리한 경과 시간(milliseconds)
			// 인터페이스별로 반환되는 데이터가 있을 경우 "datas" Object 하위로 반환
			// 추가 반환할 데이터가 없을 경우 null 또는 ""(빈스트링) 반환
			
			if (results == 1) {
				dataResult.addProperty("reason", OK_MESSAGE);
				result = "0000";
				reason = "성공";
			} else {
				dataResult.addProperty("reason", NOT_OK_MESSAGE);
				result = "0001";	
//				reason = "필수항목누락";
				reason = "실패";
			}
			dataResult.addProperty("result", results);
			
			Obj1.addProperty("if_id", if_id);
			Obj1.addProperty("version", version);
			Obj1.addProperty("m_code", m_code);
			Obj1.addProperty("s_code", s_code);
			Obj1.addProperty("response_format", response_format);
			Obj1.addProperty("result", result);
			Obj1.addProperty("reason", reason);
			Obj1.addProperty("server_time", server_time);
			Obj1.addProperty("response_time", response_time);

			jsonArr2.add(Obj2);
			Obj1.add("datas", jsonArr2);			
			
			jsonArr1.add(Obj1);
			Obj3.add("result", jsonArr1);
			
			dataResult.add("data", Obj3);
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.insertRegistAmt] ERROR : " + e);
			
//			dataResult.addProperty("reason", NOT_OK_MESSAGE);
//			dataResult.addProperty("result", results);
//
//			jsonArr2.add(Obj2);
//			Obj1.add("datas", jsonArr2);			
//			
//			jsonArr1.add(Obj1);
//			Obj3.add("result", jsonArr1);
//			
//			dataResult.add("data", Obj3);
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return dataResult.toString();
	}	
}
