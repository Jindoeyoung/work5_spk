package com.spk.api.service.sys;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.spk.api.entity.SysMenuUsrInfo;
import com.spk.api.entity.SysMenuSaveDetailUsr;
import com.spk.api.entity.SysMenuSaveUsr;
import com.spk.api.entity.SysMenuUsr;
import com.spk.api.entity.SysMenuUsrSave;
import com.spk.api.error.EResultCode;
import com.spk.api.mapper.SysMenuUsrMapper;
import com.spk.api.security.AuthCheck;
import com.spk.api.util.ReturnException;
import com.spk.api.util.Utils;

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
	public String getSysMenuUsrList_GR2(@Param("SYS_MENU_USR") SysMenuUsr pSysMenuUsr) throws Exception {
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

		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList_GR2(pSysMenuUsr);
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuUsr.size() > 0) {

				JsonObject result = new JsonObject();
				result.addProperty("menuCd", "NAMBU");
				result.add("subMenu", new JsonArray());
								
				for (SysMenuUsr item : sysMenuUsr) {
					String parentId = item.getParent_menu_id();
					JsonObject parent = getParent(result, parentId);
					JsonObject current = new JsonObject();
					current.addProperty("division", item.getDivision());
					current.addProperty("menuCd", item.getMenu_id());
					current.addProperty("menuNm", item.getMenu_nm());
					current.addProperty("use_yn", item.getUse_yn());
					current.addProperty("owner", item.getOwner());
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
	
	
	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>시스템메뉴 리스트를 조회한다 </li>
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

		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			
			List<SysMenuUsr> sysMenuUsr = sysMenuUsrMapper.getSysMenuUsrList(pSysMenuUsr);
			
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
			
			if (sysMenuUsr.size() > 0) {

				JsonObject result = new JsonObject();
				result.addProperty("menuCd", "NAMBU");
				result.add("subMenu", new JsonArray());
								
				for (SysMenuUsr item : sysMenuUsr) {
					String parentId = item.getParent_menu_id();
					JsonObject parent = getParent(result, parentId);
					JsonObject current = new JsonObject();
					current.addProperty("division", item.getDivision());
					current.addProperty("menuCd", item.getMenu_id());
					current.addProperty("menuNm", item.getMenu_nm());
					current.addProperty("use_yn", item.getUse_yn());
					current.addProperty("owner", item.getOwner());
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
//				System.out.println("===== FOR END =====");	
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
	
	
	/**
	 * <p>getParent</p>
		 * <ul>
		 * 	<li>상위 메뉴 찾기 </li>
		* </ul>
	 * @param current JsonObject
	 * @param parentId 부모 메뉴 id
	 * @return String
	 */	
	public JsonObject getParent(JsonObject current, String parentId) {		
		if(current != null && current.get("menuCd") != null && current.get("menuCd").getAsString().equals(parentId)) {
//			System.out.println("1111 current ["+current+"]");
			return current;
		}
		if(current.get("subMenu") != null && current.get("subMenu").isJsonArray()) {
			for(JsonElement next:current.get("subMenu").getAsJsonArray()) {
				if(next.getAsJsonObject() != null) {
					JsonObject result = getParent(next.getAsJsonObject(), parentId);
					if(result != null) {
//						System.out.println("2222 result ["+result+"]");
						return result;
					}
				}
			}
		}
		return null;
	}	
	
	/**
	 * <p>UPDATE</p>
		 * <ul>
		 * 	<li>시스템메뉴 사용여부(UseYn)을 update 한다. </li>
		* </ul>
	 * @param SysMenuUsrSave 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */
	@Override
	public String updateSysMenuUsr(@Param("SYS_MENU_USR") SysMenuUsrSave SysMenuUsrSave) throws Exception {
		//============================================================
		//< api-key check
		//============================================================
		if (!authcheck.getMetaAuthErrGenerator(SysMenuUsrSave.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(SysMenuUsrSave.getApikey());
		}		
		
		//============================================================
		//< json 포맷 데이터 생성
		//============================================================		
		int results = 0;
		JsonObject dataResult = new JsonObject();
		JsonObject Obj3 = new JsonObject();
		Utils utils = new Utils();
		
		String OK_MESSAGE = "SUCCESS";
		String NOT_OK_MESSAGE = "FAIL";
		
		try {
			
			List<SysMenuUsr> datas = SysMenuUsrSave.getData();
			
			for (SysMenuUsr item : datas) {
				item.setSpike_id(SysMenuUsrSave.getSpike_id());
			//============================================================
			//< USE_YN 업데이트
			//============================================================
				results = sysMenuUsrMapper.updateUseYn(item);
			}

			if (results == 1) {
				dataResult.addProperty("reason", OK_MESSAGE);
			} else {
				results = -1;
				dataResult.addProperty("reason", NOT_OK_MESSAGE);
			}
			
			dataResult.addProperty("result", Integer.toString(results));
			dataResult.add("data", Obj3);			
			
		} catch (Exception e) {
			logger.error("[SysMenuUsrServiceImpl.updateSysMenuUsr] ERROR : Exception " + e);
			
			JsonObject result = new JsonObject();
			result = utils.getMetaErrGenerator3(EResultCode.FAILED_UPDATE);		
			e.printStackTrace();
			throw new ReturnException(result, EResultCode.FAILED_UPDATE.getResultMessage());			
			
		}
		return dataResult.toString();
	}	
	
	/**
	 * <p>INSERT</p>
		* <ul>
		* 	<li>시스템메뉴 정보를 저장한다 </li>
		*  <li>메뉴 숨김, 메뉴 정렬, 메뉴명 수정, 메뉴 등록 </li>
		* </ul>
	 * @param pSysMenuSaveUsr 클라이언트에서 요청받은 메뉴처리정보
	 * @return String
	 */	
	@Override
	@Transactional(rollbackFor = Exception.class)	
	public String saveSysMenuUsr(@Param("SYS_MENU_SAVE_USR") SysMenuSaveUsr pSysMenuSaveUsr) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuSaveUsr.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuSaveUsr.getApikey());
		}
		
		String Message = "SUCCESS";
		String Success = "1";

		try {
			List<SysMenuSaveDetailUsr> datas = pSysMenuSaveUsr.getData();
			for (SysMenuSaveDetailUsr item : datas) {
				String flag = item.getFlag();
				List<SysMenuUsr> data = item.getData();

				if ("H".equals(flag)) {
					for (SysMenuUsr menuItem : data) {
						String division = menuItem.getDivision();
						String menuId = menuItem.getMenu_id();
						String useYn = menuItem.getUse_yn();

						// 메뉴 사용자
						// updateSysMenuUseYn(division, menuId, useYn);
					}
				} else if ("U".equals(flag)) {
					for (SysMenuUsr menuItem : data) {
						String division = menuItem.getDivision();
						String menuId = menuItem.getMenu_id();
						String menuNm = menuItem.getMenu_nm();

						// 메뉴 마스터
						// updateSysMenuInfo(division, menuId, menuNm);
					}
				} else if ("I".equals(flag)) {
					for (SysMenuUsr menuItem : data) {
						String division = menuItem.getDivision();
						String menuId = menuItem.getMenu_id();
						String gubun = menuItem.getGubun();
						String parentMenuId = menuItem.getParent_menu_id();
						String menuNm = menuItem.getMenu_nm();
						String isdirectory = menuItem.getIsDirectory();

						// 메뉴 마스터
						// insertSysMenuInfo(division, menuId, gubun, parentMenuId, menuNm, isdirectory);

						// 메뉴 사용자
						// user = getUserGroupCd();
						// for (SysMenuUsr usr : usrInfo) {
						// 	insertSysMenuInfoUsr(usrInfo.spikeId, division, menuId, useYn);
						// }

						// 메뉴 부서
						// user = getUserDeptCd();
						// for (SysMenuUsr usr : usrInfo) {
						// 	insertSysMenuInfoDept(division, menuId, departCd, useYn);
						// }
					}
				} else if ("S".equals(flag)) {
					// menuId 기준으로 정렬하기 위한 Comparator
					Comparator<SysMenuUsr> byMenuId = new Comparator<SysMenuUsr>() {
						@Override
						public int compare(SysMenuUsr o1, SysMenuUsr o2) {
							return o1.getMenu_id().compareTo(o2.getMenu_id());
						}
					};

					// menuId로 정렬
					Collections.sort(data, byMenuId);

					// seq 값을 재설정
					int newSeq = 1;
					for (SysMenuUsr menuItem : data) {
						String division = menuItem.getDivision();
						String menuId = menuItem.getMenu_id();
						String seq = menuItem.getSeq();
						String orderNo = menuItem.getOrder_by();

						// 메뉴 마스터
						// updateSysMenuSort(division, menuId, Integer.toString(newSeq), orderNo);
						newSeq++;
					}
				}
			}

			return "SUCCESS";

		} catch (Exception e) {
			return e.toString();
		}
	}

	/**
	 * <p>DUPL CHECK</p>
		 * <ul>
		 * 	<li>시스템메뉴 정보를 저장한다 </li>
		 *  <li>메뉴 중복 체크</li>
		* </ul>
	 * @param pSysMenuUsrInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String checkSysMenuUsr(@Param("SYS_MENU_USR_INFO") SysMenuUsrInfo pSysMenuUsrInfo) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuUsrInfo.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuUsrInfo.getApikey());
		}
		
		String Message = "SUCCESS";
		String Success = "1";
		try {
			SysMenuUsr data = pSysMenuUsrInfo.getData();

			String division = data.getDivision();
			String menuId = data.getMenu_id();

			// 중복 체크
			// dupSysMenuInfo(division, menuId);

			return "SUCCESS";

		} catch (Exception e) {
			return e.toString();
		}
	}

	/**
	 * <p>DELETE</p>
		 * <ul>
		 * 	<li>시스템메뉴 정보를 저장한다 </li>
		 *  <li>메뉴 삭제</li>
		* </ul>
	 * @param pSysMenuUsrInfo 클라이언트에서 요청받은 메뉴정보
	 * @return String
	 */	
	@Override
	public String deleteSysMenuUsr(@Param("SYS_MENU_USR_INFO") SysMenuUsrInfo pSysMenuUsrInfo) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(pSysMenuUsrInfo.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pSysMenuUsrInfo.getApikey());
		}
		
		String Message = "SUCCESS";
		String Success = "1";
		try {
			SysMenuUsr data = pSysMenuUsrInfo.getData();

			String division = data.getDivision();
			String menuId = data.getMenu_id();

			// 메뉴 삭제
			// deleteSysMenuInfo(division, menuId);

			return "SUCCESS";

		} catch (Exception e) {
			return e.toString();
		}
	}
}
