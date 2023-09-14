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

		JsonArray jsonArr = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		JsonArray jsonArr3 = new JsonArray();
		JsonArray jsonArr4 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList(pSysMenuUsr);
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			
			JsonObject Obj1 = new JsonObject(); //ADD
			
			if (sysMenuUsr.size() > 0) {
				
				//==================================== FOR START
				for (SysMenuUsr item : sysMenuUsr) {
					
					JsonObject Obj2 = new JsonObject();		
					JsonObject Obj3 = new JsonObject();
					JsonObject Obj4 = new JsonObject();
					
					if ( item.getSeq().equals("1") && item.getLevel().equals("1") ) {
						
						Obj1.addProperty("menuCd", item.getMenu_id());
						Obj1.addProperty("menuNm", item.getMenu_nm());
					
					} else if (item.getLevel().equals("2") ) {
						
						Obj2.addProperty("menuCd", item.getMenu_id());
						Obj2.addProperty("menuNm", item.getMenu_nm());
						jsonArr.add(Obj2);
						
						Obj1.add("subMenu", jsonArr);
						
					} else if (item.getLevel().equals("3") ) {

						Obj3.addProperty("menuCd", item.getMenu_id());
						Obj3.addProperty("menuNm", item.getMenu_nm());
						jsonArr3.add(Obj3);
						
					} else if (item.getLevel().equals("4") ) {

						Obj4.addProperty("menuCd", item.getMenu_id());
						Obj4.addProperty("menuNm", item.getMenu_nm());
						jsonArr4.add(Obj4);
						
//						Obj3.add("subMenu", jsonArr4);
					}

					Obj3.add("subMenu", jsonArr4);
					Obj2.add("subMenu", jsonArr3);

				}
				//==================================== FOR END				
				
				jsonArr2.add(Obj1);
				dataResult.add("menu_info", jsonArr2);			

				
				
				
				
				
				
				
			} else {
//				dataResult.add("menu_info", jsonArr); // 임시 막음
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
		 * 	<li>시스템메뉴유저 상세정보를 조회한다 </li>
	     * </ul>
	 * @param pSysMenuUsr 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String getSysMenuUsrInfo(@Param("SYS_MENU_USR") SysMenuUsr pSysMenuUsr) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
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
			SysMenuUsr sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrInfo(pSysMenuUsr);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuUsr != null) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", sysMenuUsr.getSpike_id());
					Obj1.addProperty("division", sysMenuUsr.getDivision());
					Obj1.addProperty("menuCd", sysMenuUsr.getMenu_id());
					Obj1.addProperty("use_yn", sysMenuUsr.getUse_yn());
					Obj1.addProperty("createdAt", sysMenuUsr.getReg_dt());
					jsonArr1.add(Obj1);
					
					dataResult.add("flag_info", jsonArr1);
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
		} catch (Exception e) {
			logger.error("[SysMenuUsrServiceImpl.getSysMenuUsr] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	
	/**
	 * <p>UPDATE</p>
		 * <ul>
		 * 	<li>시스템메뉴 정보를 등록한다 </li>
	     * </ul>
	 * @param pSysMenuUsr 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String updateSysMenuUsr(@Param("SYS_MENU_USR") SysMenuUsr pSysMenuUsr) throws Exception {
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
			int results = sysMenuUsrMapper.updateSysMenuUsr(pSysMenuUsr);
			
			if (results == 1) {
				SysMenuUsr sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrInfo(pSysMenuUsr);
				dataResult.addProperty("reason", Message);
				dataResult.addProperty("result", Success);
				
				if (sysMenuUsr != null) {
						JsonObject Obj1 = new JsonObject();
						
						Obj1.addProperty("spike_id", sysMenuUsr.getSpike_id());
						Obj1.addProperty("division", sysMenuUsr.getDivision());
						Obj1.addProperty("menuCd", sysMenuUsr.getMenu_id());
						Obj1.addProperty("use_yn", sysMenuUsr.getUse_yn());						
						Obj1.addProperty("createdAt", sysMenuUsr.getReg_dt());
						
						jsonArr1.add(Obj1);
						
						dataResult.add("flag_info", jsonArr1);
				} else {
					JsonObject Obj3 = new JsonObject();
					Obj3.add("result", jsonArr1);
					dataResult.add("data", Obj3);
				}
			} // if (results == 1) {
		} catch (Exception e) {
			logger.error("[SysMenuUsrServiceImpl.updateSysMenuUsr] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}	
	
	
}
