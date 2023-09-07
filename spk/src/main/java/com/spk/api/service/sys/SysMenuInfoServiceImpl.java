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

	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>시스템메뉴정보 등록 리스트를 조회한다 </li>
	     * </ul>
	 * @param pSysMenuInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
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
			
			if (sysMenuInfo.size() > 0) {
				for (SysMenuInfo item : sysMenuInfo) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", item.getSpike_id());
					Obj1.addProperty("commenter", item.getCommenter());
					Obj1.addProperty("request", item.getRequest());
					Obj1.addProperty("use_yn", item.getUse_yn());
					Obj1.addProperty("createdAt", item.getReg_dt());
					jsonArr1.add(Obj1);
					
					dataResult.add("flag_info", jsonArr1);
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
	 * <p>SELECT (MAP)</p>
		 * <ul>
		 * 	<li>시스템메뉴정보 등록 상세정보를 조회한다 </li>
	     * </ul>
	 * @param pSysMenuInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
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
	
	/**
	 * <p>INSERT</p>
		 * <ul>
		 * 	<li>시스템메뉴 정보를 등록한다 </li>
	     * </ul>
	 * @param pSysMenuInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
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
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			int results = sysMenuInfoMapper.insertSysMenuInfo(pSysMenuInfo);
			
			if (results == 1) {
				SysMenuInfo sysMenuInfo = sysMenuInfoMapper.getSysMenuInfo(pSysMenuInfo);
				dataResult.addProperty("reason", Message);
				dataResult.addProperty("result", Success);
				
				if (sysMenuInfo != null) {
						JsonObject Obj1 = new JsonObject();
						
						Obj1.addProperty("spike_id", sysMenuInfo.getSpike_id());
						Obj1.addProperty("commenter", sysMenuInfo.getCommenter());
						Obj1.addProperty("createdAt", sysMenuInfo.getReg_dt());
						Obj1.addProperty("request", sysMenuInfo.getRequest());
						Obj1.addProperty("use_yn", sysMenuInfo.getUse_yn());
						
						jsonArr1.add(Obj1);
						
						dataResult.add("flag_info", jsonArr1);
				} else {
					JsonObject Obj3 = new JsonObject();
					Obj3.add("result", jsonArr1);
					dataResult.add("data", Obj3);
				}
			} // if (results == 1) {
		} catch (Exception e) {
			logger.error("[UHJ01ServiceImpl.insertSysMenuInfo] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
}