package com.spk.api.service.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.SysMenuInfo;
import com.spk.api.mapper.SysMenuInfoMapper;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysMenuInfoServiceImpl implements SysMenuInfoService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysMenuInfoMapper sysMenuInfoMapper;
	
	AuthCheck authcheck = new AuthCheck();

	@Override
	public String getSysMenuInfoList(@Param("SYS_MENU_INFO") SysMenuInfo pSysMenuInfo) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<SysMenuInfo> sysMenuInfo = sysMenuInfoMapper.getSysMenuInfoList(pSysMenuInfo);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			dataResult.addProperty("menu_id", pSysMenuInfo.getMenu_id());
			
			logger.info("size=>"+sysMenuInfo.size());
			
			if (sysMenuInfo.size() > 0) {
				
				for (SysMenuInfo item : sysMenuInfo) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", item.getSpike_id());
					Obj1.addProperty("commenter", item.getCommenter());
					Obj1.addProperty("requirement", item.getRequest());
					Obj1.addProperty("useFlag", item.getUse_yn());
					jsonArr1.add(Obj1);
					
					dataResult.add("flag_info", jsonArr1);
				
//				for (SysMenuInfo item : sysMenuInfo) {
//					JsonObject Obj1 = new JsonObject();
//					JsonObject Obj2 = new JsonObject();
//	
//					Obj1.addProperty("spike_id", item.getSpike_id());
//					Obj1.addProperty("menuCd", item.getMenuCd());
//					Obj1.addProperty("commenter", item.getUser_nm());
//					Obj1.addProperty("requirement", item.getRequest());
//					Obj1.addProperty("useFlag", item.getUse_yn());
//					jsonArr1.add(Obj1);
//	
//					Obj2.add("result", jsonArr1);
//					dataResult.add("data", Obj2);
				}
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
			
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuInfoList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}
	
	
	
	@Override
	public String getSysMenuInfo(@Param("SYS_MENU_INFO") SysMenuInfo pSysMenuInfo) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			SysMenuInfo sysMenuInfo = sysMenuInfoMapper.getSysMenuInfo(pSysMenuInfo);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
//			dataResult.addProperty("menu_id", pSysMenuInfo.getMenu_id());
			
			if (sysMenuInfo != null) {
				
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", sysMenuInfo.getSpike_id());
					Obj1.addProperty("commenter", sysMenuInfo.getCommenter());
					Obj1.addProperty("requirement", sysMenuInfo.getRequest());
					Obj1.addProperty("useFlag", sysMenuInfo.getUse_yn());
					Obj1.addProperty("createdAt", sysMenuInfo.getReg_dt());
					jsonArr1.add(Obj1);
					
					dataResult.add("flag_info", jsonArr1);
				


			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
			
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuInfo] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	
	@Override
	public String insertSysMenuInfo(@Param("SYS_MENU_INFO") SysMenuInfo pSysMenuInfo) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuInfo.getApikey());
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
			int results = sysMenuInfoMapper.insertSysMenuInfo(pSysMenuInfo);

//	        //============================================================
//	        //< Respnse Data (Sample)
//			//< API 호출 시 공통으로 리턴받는 Response Data
//	        //============================================================			
//			String if_id = "IF-001-001";				// API id
//			String version = "v1.0";					// API version
//			String m_code = "HAK000";					// Menu Code
//			String s_code = "HAK001";					// Sub Menu Code
//			String response_format = "json";			// 반환 data 타입
//			String result = "";							// 응답코드 4자리
//			String reason = "";							// 응답 결과 메세지 ("성공", "필수항목 누락" 등)
//			String server_time = "2021-04-13 15:40:40";	//응답 시 서버 시간
//			String response_time = "132";				// 서버에서 처리한 경과 시간(milliseconds)
//			// 인터페이스별로 반환되는 데이터가 있을 경우 "datas" Object 하위로 반환
//			// 추가 반환할 데이터가 없을 경우 null 또는 ""(빈스트링) 반환
//			
//			if (results == 1) {
//				dataResult.addProperty("reason", OK_MESSAGE);
//				result = "0000";
//				reason = "성공";
//			} else {
//				dataResult.addProperty("reason", NOT_OK_MESSAGE);
//				result = "0001";	
//				reason = "필수항목누락";				
//			}
//			dataResult.addProperty("result", results);
//			
//			Obj1.addProperty("if_id", if_id);
//			Obj1.addProperty("version", version);
//			Obj1.addProperty("m_code", m_code);
//			Obj1.addProperty("s_code", s_code);
//			Obj1.addProperty("response_format", response_format);
//			Obj1.addProperty("result", result);
//			Obj1.addProperty("reason", reason);
//			Obj1.addProperty("server_time", server_time);
//			Obj1.addProperty("response_time", response_time);
//
//			Obj2.addProperty("hakbeon", pUhj01Entity.getHakbeon());
//			jsonArr2.add(Obj2);
//			Obj1.add("datas", jsonArr2);			
//			
//			jsonArr1.add(Obj1);
//			Obj3.add("result", jsonArr1);
//			
//			dataResult.add("data", Obj3);
		} catch (Exception e) {
			logger.error("[UHJ01ServiceImpl.insertStudent] ERROR : " + e);
			// TODO
			e.printStackTrace();
		}
		return dataResult.toString();
	}	
	
}
