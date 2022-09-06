package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.StSuGangM;
import com.spk.api.mapper.StSuGangMMapper;

@RestController
@RequestMapping(value = "/hj", produces = "application/json; charset=utf8")
public class StSuGangMController {
	
	@Autowired
	private StSuGangMMapper stsugangmmapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@PostMapping("/suganginfo")
	public String getSugangList(@RequestBody StSuGangM _sugang) {

		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		List<StSuGangM> datas = (List<StSuGangM>) stsugangmmapper.getSugangList(_sugang);		
		
		for (StSuGangM item : datas) {
		System.out.println("item==>"+item);
		
			JsonObject Obj1 = new JsonObject();
			JsonObject Obj2 = new JsonObject();
		
			Obj1.addProperty("year", item.getYear());
			Obj1.addProperty("hakgi", item.getHakgi());
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
		return dataResult.toString();
	}	
	
}
