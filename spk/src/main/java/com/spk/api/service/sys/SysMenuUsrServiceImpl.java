package com.spk.api.service.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.SysMenuUsr;
import com.spk.api.mapper.SysMenuUsrMapper;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysMenuUsrServiceImpl implements SysMenuUsrService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysMenuUsrMapper sysMenuUsrMapper;
//	private SysMenuInfoMapper sysMenuInfoMapper;
	
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
	public String getSysMenuUsrList(@Param("SYS_MENU_USR") SysMenuUsr pSysMenuUsr) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuUsr.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuUsr.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList(pSysMenuUsr);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuUsr.size() > 0) {
				for (SysMenuUsr item : sysMenuUsr) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", item.getSpike_id());
					Obj1.addProperty("division", item.getDivision());
					Obj1.addProperty("menu_cd", item.getMenu_cd());
					Obj1.addProperty("seq", item.getSeq());
					Obj1.addProperty("parent_menu_cd", item.getParent_menu_cd());
					Obj1.addProperty("menu_nm", item.getMenu_nm());
					Obj1.addProperty("level", item.getLevel());
					
					jsonArr1.add(Obj1);
					
					dataResult.add("menu_info", jsonArr1);
				}
			} else {
				dataResult.add("menu_info", jsonArr1);
			}
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuInfoList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}

}
