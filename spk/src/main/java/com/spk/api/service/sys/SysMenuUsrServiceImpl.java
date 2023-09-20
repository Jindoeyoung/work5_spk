package com.spk.api.service.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
	public String getSysMenuUsrList2(@Param("SYS_MENU_USR") SysMenuUsr pSysMenuUsr) throws Exception {
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
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList2(pSysMenuUsr);
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuUsr.size() > 0) {
				for (SysMenuUsr item : sysMenuUsr) {
					JSONObject result = new JSONObject(item.getJson_data());
					jsonArr.add(item.getJson_data());	
				}
				dataResult.add("menu_info", jsonArr);
				
			} else {

			}
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuUsrList2] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
	
	
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
//		JsonArray jsonArr4 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList(pSysMenuUsr);
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			
			JsonObject Obj1 = new JsonObject(); //ADD
			
			if (sysMenuUsr.size() > 0) {

//				JsonArray jsonArr4 = new JsonArray();			
//				String beforeLevel;
//				//==================================== FOR START
//				for (SysMenuUsr item : sysMenuUsr) {
//					JsonObject Obj2 = new JsonObject();		
//					JsonObject Obj3 = new JsonObject();
//					
//					if ( item.getSeq().equals("1") && item.getLevel().equals("1") ) {
//						
//						Obj1.addProperty("menuCd", item.getMenu_id());
//						Obj1.addProperty("menuNm", item.getMenu_nm());
//					
//					} else if (item.getLevel().equals("2") ) {
//						
//						Obj2.addProperty("menuCd", item.getMenu_id());
//						Obj2.addProperty("menuNm", item.getMenu_nm());
//						jsonArr.add(Obj2);
//						
//						Obj1.add("subMenu", jsonArr);
//						
//					} else if (item.getLevel().equals("3") ) {
//
//						Obj3.addProperty("menuCd", item.getMenu_id());
//						Obj3.addProperty("menuNm", item.getMenu_nm());
//						jsonArr3.add(Obj3);
//
//					} else if (item.getLevel().equals("4") ) {
//						JsonObject Obj4 = new JsonObject();
//						Obj4.addProperty("menuCd", item.getMenu_id());
//						Obj4.addProperty("menuNm", item.getMenu_nm());
//						jsonArr4.add(Obj4);
//						
////						Obj3.add("subMenu", jsonArr4);
//					}
//					Obj3.add("subMenu", jsonArr4);
//					Obj2.add("subMenu", jsonArr3);
//					beforeLevel=item.getLevel();
//				}
//				//==================================== FOR END				
//				
//				jsonArr2.add(Obj1);
//				dataResult.add("menu_info", jsonArr2);			
				// ----------------------------
				JsonObject result = new JsonObject();
				result.addProperty("menuCd", "NAMBU");
				result.add("subMenu", new JsonArray());
								
				for (SysMenuUsr item : sysMenuUsr) {
					String parentId = item.getParent_menu_id();
					JsonObject parent = getParent(result, parentId);
					JsonObject current = new JsonObject();
					current.addProperty("menuCd", item.getMenu_id());
					current.addProperty("menuNm", item.getMenu_nm());
					current.addProperty("use_yn", item.getUse_yn());
					if(item.getIsDirectory() != null && item.getIsDirectory().equals("1")) {
						current.add("subMenu", new JsonArray());
					}
					if(parent == null) {
//						System.out.println("not found parent:"+parentId+",current:"+current);
						result.get("subMenu").getAsJsonArray().add(current);
					}else {
						JsonElement children = parent.get("subMenu");
						if(children == null) {
							parent.add("subMenu", new JsonArray());
							children = parent.get("subMenu").getAsJsonArray();
						}
						children.getAsJsonArray().add(current);
						boolean isEveryN = true;
						for(JsonElement child : children.getAsJsonArray()) {
							String yn=child.getAsJsonObject().get("use_yn").getAsString();
//							logger.info(yn);
							if(yn.equals("Y")) {								
								isEveryN=false;
								break;
							}
						}
						if(isEveryN) {
							parent.addProperty("use_yn", "N");
						}else {
							parent.addProperty("use_yn", "Y");
						}
					}
				}
				dataResult.add("menu_info", result.get("subMenu").getAsJsonArray());
			} else {
				
			}
		} catch (Exception e) {
			logger.error("[SysMenuInfoServiceImpl.getSysMenuInfoList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}

	public JsonObject getParent(JsonObject current, String parentId) {		
		if(current != null && current.get("menuCd") != null && current.get("menuCd").getAsString().equals(parentId)) {
			return current;
		}
		if(current.get("subMenu") != null && current.get("subMenu").isJsonArray()) {
			for(JsonElement next:current.get("subMenu").getAsJsonArray()) {
				if(next.getAsJsonObject() != null) {
					JsonObject result = getParent(next.getAsJsonObject(), parentId);
					if(result != null) {
						return result;
					}
				}
			}
		}
		return null;
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
