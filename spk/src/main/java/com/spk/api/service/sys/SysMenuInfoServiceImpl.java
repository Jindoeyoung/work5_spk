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
					
					Obj1.addProperty("commenter", item.getUser_nm());
					Obj1.addProperty("spike_id", item.getSpike_id());
					Obj1.addProperty("commenter", item.getUser_nm());
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
	
}
