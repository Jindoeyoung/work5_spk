package com.spk.api.service.sysmenu;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import com.spk.api.entity.SysMenuInfo;
import com.spk.api.entity.sysmenu.SysMenuMobile;
import com.spk.api.error.EResultCode;
//import com.spk.api.mapper.SysMenuInfoMapper;
import com.spk.api.mapper.sysmenu.SysMenuMobileMapper;
import com.spk.api.security.AuthCheck;
//import com.spk.api.service.sys.SysMenuInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysMenuMobileServiceImpl implements SysMenuMobileService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysMenuMobileMapper sysMenuMobileMapper;
//	private SysMenuInfoMapper sysMenuInfoMapper;
	
	AuthCheck authcheck = new AuthCheck();

	/**
	 * <p>SELECT (MAP)</p>
		 * <ul>
		 * 	<li>시스템메뉴모바일 정보 상세정보 조회 </li>
	     * </ul>
	 * @param pSysMenuMobile 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String getSysMenuMobileList(@Param("SYS_MENU_MOBILE") SysMenuMobile pSysMenuMobile) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
//		String Message = "SUCCESS";
//		String Success = "1";
		
		try {
			List<SysMenuMobile> sysMenuMobile = sysMenuMobileMapper.getSysMenuMobileList(pSysMenuMobile);
			dataResult.addProperty("reason", EResultCode.SUCCESS.getResultMessage());
			dataResult.addProperty("result", EResultCode.SUCCESS.getResultCode());
			
			if (sysMenuMobile.size() > 0) {
				for (SysMenuMobile item : sysMenuMobile) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", item.getSpike_id());
					Obj1.addProperty("division", item.getDivision());
					Obj1.addProperty("menu_id", item.getMenu_id());
					Obj1.addProperty("menu_nm", item.getMenu_nm());
					Obj1.addProperty("mob_icon_id", item.getMob_icon_id());
					jsonArr1.add(Obj1);
					
					dataResult.add("data", jsonArr1);
				}
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
		} catch (Exception e) {
			logger.error("[SysMenuMobileServiceImpl.getSysMenuMobileList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	/**
	 * <p>SELECT (MAP)</p>
		 * <ul>
		 * 	<li>시스템메뉴모바일 정보 상세정보 조회 </li>
	     * </ul>
	 * @param pSysMenuMobile 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String getSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile pSysMenuMobile) throws Exception {
		final Logger logger = LoggerFactory.getLogger(this.getClass());
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			SysMenuMobile sysMenuMobile = sysMenuMobileMapper.getSysMenuMobile(pSysMenuMobile);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuMobile != null) {
					JsonObject Obj1 = new JsonObject();
					
					Obj1.addProperty("spike_id", sysMenuMobile.getSpike_id());
					Obj1.addProperty("division", sysMenuMobile.getDivision());
					Obj1.addProperty("menu_id", sysMenuMobile.getMenu_id());
					Obj1.addProperty("menu_nm", sysMenuMobile.getMenu_nm());
					Obj1.addProperty("mob_icon_id", sysMenuMobile.getMob_icon_id());
					jsonArr1.add(Obj1);
					
					dataResult.add("data", jsonArr1);
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
		} catch (Exception e) {
			logger.error("[SysMenuMobileServiceImpl.getSysMenuMobile] ERROR : " + e);
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
	public String insertSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile pSysMenuMobile) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey());
		}		
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			int results = sysMenuMobileMapper.insertSysMenuMobile(pSysMenuMobile);
			
			if (results == 1) {
				SysMenuMobile sysMenuMobile = sysMenuMobileMapper.getSysMenuMobile(pSysMenuMobile);
				dataResult.addProperty("reason", Message);
				dataResult.addProperty("result", Success);
				
				if (sysMenuMobile != null) {
						JsonObject Obj1 = new JsonObject();
						
						Obj1.addProperty("spike_id", sysMenuMobile.getSpike_id());
						Obj1.addProperty("division", sysMenuMobile.getDivision());
						Obj1.addProperty("menu_id", sysMenuMobile.getMenu_id());
						Obj1.addProperty("menu_nm", sysMenuMobile.getMenu_nm());
						Obj1.addProperty("mob_icon_id", sysMenuMobile.getMob_icon_id());
						
						jsonArr1.add(Obj1);
						
						dataResult.add("flag_info", jsonArr1);
				} else {
					JsonObject Obj3 = new JsonObject();
					Obj3.add("result", jsonArr1);
					dataResult.add("data", Obj3);
				}
			} // if (results == 1) {
		} catch (Exception e) {
			logger.error("[SysMenuMobileServiceImpl.insertSysMenuMobile] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}
	
	/**
	 * <p>UPDATE</p>
		 * <ul>
		 * 	<li>시스템메뉴 정보를 등록한다 </li>
		 *  <li>상위메뉴 사용여부(USE_YN)을 수정한다 </li>
	     * </ul>
	 * @param pSysMenuInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class)	
	public String updateSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile pSysMenuMobile) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey());
		}		
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			int results = sysMenuMobileMapper.updateSysMenuMobile(pSysMenuMobile);
			
			if (results == 1) {
				SysMenuMobile sysMenuMobile = sysMenuMobileMapper.getSysMenuMobile(pSysMenuMobile);
				dataResult.addProperty("reason", Message);
				dataResult.addProperty("result", Success);
				
				if (sysMenuMobile != null) {
						JsonObject Obj1 = new JsonObject();
						
						Obj1.addProperty("spike_id", sysMenuMobile.getSpike_id());
						Obj1.addProperty("menu_id", sysMenuMobile.getMenu_id());
						Obj1.addProperty("menu_nm", sysMenuMobile.getMenu_nm());
						Obj1.addProperty("mob_icon_id", sysMenuMobile.getMob_icon_id());
						
						jsonArr1.add(Obj1);
						
						dataResult.add("flag_info", jsonArr1);
				} else {
					JsonObject Obj3 = new JsonObject();
					Obj3.add("result", jsonArr1);
					dataResult.add("data", Obj3);
				}
			} // if (results == 1) {
		} catch (Exception e) {
			logger.error("[SysMenuMobileServiceImpl.updateSysMenuMobile] ERROR : " + e);
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
	public String deleteSysMenuMobile(@Param("SYS_MENU_MOBILE") SysMenuMobile pSysMenuMobile) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuMobile.getApikey());
		}		
		
        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		try {
			int results = sysMenuMobileMapper.deleteSysMenuMobile(pSysMenuMobile);
			
			if (results == 1) {
				dataResult.addProperty("reason", EResultCode.SUCCESS.getResultMessage());
				dataResult.addProperty("result", EResultCode.SUCCESS.getResultCode());				
			} else { // if (results == 1) {
				dataResult.addProperty("reason", EResultCode.FAIL.getResultMessage());
				dataResult.addProperty("result", EResultCode.FAIL.getResultCode());				
			}
			JsonObject Obj1 = new JsonObject();
			jsonArr1.add(Obj1);
			dataResult.add("data", jsonArr1);
			
//			JsonObject Obj3 = new JsonObject();
//			Obj3.add("result", jsonArr1);
//			dataResult.add("data", Obj3);
			
		} catch (Exception e) {
			logger.error("[SysMenuMobileServiceImpl.deleteSysMenuMobile] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult.toString();
	}	
	
}
