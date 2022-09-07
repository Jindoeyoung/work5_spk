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
import com.spk.api.entity.StSeongJeokM;
import com.spk.api.mapper.StSeongJeokMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/hj", produces = "application/json; charset=utf8")
public class StSeongJeokMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StSeongJeokMMapper stseongjeokmmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@PostMapping("/gwamok-seongjeok")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getSeongJeokList(@RequestBody StSeongJeokM _seongjeok) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(_seongjeok.getApikey()).equals("{}")) {
			logger.info("[StSeongJeokMController][getSeongJeokList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_seongjeok.getApikey());		
		}
		
		List<StSeongJeokM> datas = (List<StSeongJeokM>) stseongjeokmmapper.getSeongJeokList(_seongjeok.getHakbeon());		
//		List<StSeongJeokM> datas = (List<StSeongJeokM>) stseongjeokmmapper.getSeongJeokList(_seongjeok);	// 조회조건 다수일때 사용할 것		
		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		for (StSeongJeokM item : datas) {
		System.out.println("item==>"+item);
		
			JsonObject Obj1 = new JsonObject();
			JsonObject Obj2 = new JsonObject();
		
			Obj1.addProperty("year", item.getYear());
			Obj1.addProperty("hakgi", item.getHakgi());	
			Obj1.addProperty("hakbeon", item.getHakbeon());
			Obj1.addProperty("gwamok", item.getGwamok());
			Obj1.addProperty("gubun", item.getGubun());
			Obj1.addProperty("hakjeom", item.getHakjeom());
			Obj1.addProperty("chongjeom", item.getChongjeom());
			Obj1.addProperty("deuggup", item.getDeuggup());
			Obj1.addProperty("jaesugang_year", item.getJaesugang_year());
			Obj1.addProperty("yeongae", item.getYeongae());
			Obj1.addProperty("injung_hakjeom", item.getInjung_hakjeom());
			
			jsonArr1.add(Obj1);		
			
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);				

		}		
		return dataResult.toString();
	}	
}
