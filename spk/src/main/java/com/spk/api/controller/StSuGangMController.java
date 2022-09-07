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
import com.spk.api.mapper.StSuGangMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/hj", produces = "application/json; charset=utf8")
public class StSuGangMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StSuGangMMapper stsugangmmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@PostMapping("/sugang-seongjeok")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getSugangList(@RequestBody StSuGangM _sugang) throws Exception {

		if (!authcheck.getMetaAuthErrGenerator(_sugang.getApikey()).equals("{}")) {
			logger.info("[StSuGangMController][getSugangList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_sugang.getApikey());
		}		

		List<StSuGangM> datas = (List<StSuGangM>) stsugangmmapper.getSugangList(_sugang.getHakbeon());
//		List<StSuGangM> datas = (List<StSuGangM>) stsugangmmapper.getSugangList(_sugang);	// 조회조건 다수일때 사용할 것			
		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		if (datas.size() > 0) {
			
			for (StSuGangM item : datas) {
			System.out.println("item 1==>"+item);
			
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();
			
				Obj1.addProperty("year", item.getYear());
				Obj1.addProperty("hakgi", item.getHakgi());
				Obj1.addProperty("hakbeon", item.getHakbeon());
				Obj1.addProperty("isu_gb", item.getIsu_gb());
				Obj1.addProperty("chongjeom", item.getChongjeom());
				Obj1.addProperty("shinchung", item.getShinchung());
				Obj1.addProperty("chwideuk", item.getChwideuk());
				Obj1.addProperty("pyeongjeom_pyeonggyun", item.getPyeongjeom_pyeonggyun());
				Obj1.addProperty("chongjeom_pyeonggyun", item.getChongjeom_pyeonggyun());
				Obj1.addProperty("haksa_gyeonggo", item.getHaksa_gyeonggo());
				
				jsonArr1.add(Obj1);		
				
				Obj2.add("result", jsonArr1);
				dataResult.add("data", Obj2);				
			}
			
		} else {
			dataResult.addProperty("data", "");
		}
		return dataResult.toString();
	}	
	
}
