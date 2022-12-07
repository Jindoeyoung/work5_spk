package com.spk.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.StSuGangM;
import com.spk.api.entity.StSugangSincheongM;
import com.spk.api.entity.VHakJeokSeongJeok;
import com.spk.api.mapper.StSuGangMMapper;
import com.spk.api.mapper.StSugangSincheongMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/su", produces = "application/json; charset=utf8")
public class StSugangSincheongMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
//	private StSuGangMMapper stsugangmmapper;
	private StSugangSincheongMMapper stsugangsincheongmmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@PostMapping("/sugang-sincheong")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getSugangSincheongList(@RequestBody StSugangSincheongM _sugangsincheong) throws Exception {

		if (!authcheck.getMetaAuthErrGenerator(_sugangsincheong.getApikey()).equals("{}")) {
			logger.info("[StSuGangMController][getSugangList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_sugangsincheong.getApikey());
		}		

		List<StSugangSincheongM> datas = (List<StSugangSincheongM>) stsugangsincheongmmapper.getSugangSincheongList(_sugangsincheong.getHakbeon());
//		List<StSugangSincheongM> datas = (List<StSugangSincheongM>) stsugangsincheongmmapper.getSugangSincheongList(_sugangsincheong);	// 조회조건 다수일때 사용할 것			
		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		if (datas.size() > 0) {
			
			for (StSugangSincheongM item : datas) {
			
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();
			
				Obj1.addProperty("year", item.getYear());
				Obj1.addProperty("hakgi", item.getHakgi());
				Obj1.addProperty("hakbeon", item.getHakbeon());
				Obj1.addProperty("gwamok_cd", item.getGwamok_cd());
				Obj1.addProperty("gwamok_nm", item.getGwamok_nm());
				Obj1.addProperty("isu_gb", item.getIsu_gb());
				Obj1.addProperty("ban", item.getBan());
				Obj1.addProperty("hakjeom", item.getHakjeom());
				Obj1.addProperty("gyosu_user_id", item.getGyosu_user_id());
				Obj1.addProperty("gyosu_nm", item.getGyosu_nm());
				Obj1.addProperty("gwamok_sigan", item.getGwamok_sigan());
				Obj1.addProperty("jaeisu_gb", item.getJaeisu_gb());
				Obj1.addProperty("deuggup", item.getDeuggup());
				
				jsonArr1.add(Obj1);		
				
				Obj2.add("result", jsonArr1);
				dataResult.add("data", Obj2);				
			}
			
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getSugangList=>"+dataResult.toString());
		return dataResult.toString();
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT 칼럼 헤더명
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/sugang-sincheong-column-name")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getKorColumnNameSugangSincheong(@RequestBody StSugangSincheongM _StSugangSincheongM) throws Exception {
		
		if (!authcheck.getMetaAuthErrGenerator(_StSugangSincheongM.getApikey()).equals("{}")) {
			logger.info("[StSugangSincheongMController][getKorColumnNameSugangSincheong] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_StSugangSincheongM.getApikey());
		}		

		StSugangSincheongM stSugangSincheongM = stsugangsincheongmmapper.getKorColumnName();
//		VHakJeokSeongJeok vHakjeokSeongjeok = stsugangsincheongmmapper.getKorColumnName(_StSugangSincheongM.getHakbeon());

		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();

		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			

		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();

		if (stSugangSincheongM != null) {
			Obj1.addProperty("hakbeon", 		stSugangSincheongM.getHakbeon());
			Obj1.addProperty("isu_gb", 			stSugangSincheongM.getIsu_gb());
			Obj1.addProperty("gwamok_cd",		stSugangSincheongM.getGwamok_cd());
			Obj1.addProperty("ban", 			stSugangSincheongM.getBan());
			Obj1.addProperty("gwamok_nm",		stSugangSincheongM.getGwamok_nm());
			Obj1.addProperty("hakjeom",			stSugangSincheongM.getHakjeom());
			Obj1.addProperty("gyosu_nm",		stSugangSincheongM.getGyosu_nm());
			Obj1.addProperty("gwamok_sigan",	stSugangSincheongM.getGwamok_sigan());
			Obj1.addProperty("jaeisu_gb",		stSugangSincheongM.getJaeisu_gb());
			Obj1.addProperty("deuggup",			stSugangSincheongM.getDeuggup());
			jsonArr1.add(Obj1);		
			
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);		
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getKorColumnNameSugangSincheong=>"+dataResult.toString());		
		return dataResult.toString();
	}	
	
}
