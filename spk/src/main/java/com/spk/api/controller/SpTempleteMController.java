package com.spk.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.SpTempleteM;
import com.spk.api.mapper.SpTempleteMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/set", produces = "application/json; charset=utf8")
public class SpTempleteMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SpTempleteMMapper sptempletemmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/templete")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getTempleteMasterList(@RequestBody SpTempleteM _templetem) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(_templetem.getApikey()).equals("{}")) {
			logger.info("[SpTempleteMController][getTempleteMasterList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_templetem.getApikey());		
		}
		
		List<SpTempleteM> datas = (List<SpTempleteM>) sptempletemmapper.getTempleteMasterList(_templetem);				
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		

		if (datas.size() > 0) {
		
			for (SpTempleteM item : datas) {
//			System.out.println("item==>"+item);
			
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();
			
				Obj1.addProperty("thumbnails", item.getThumnail_src());
				Obj1.addProperty("originals", item.getImage_src());
				
				jsonArr1.add(Obj1);		
				
				Obj2.add("background_size", jsonArr1);
				dataResult.add("data", Obj2);				
			}
			
		} else {
			
			JsonObject Obj3 = new JsonObject();			
			
			Obj3.add("background_size", jsonArr1);
			dataResult.add("data", Obj3);			
			
//			dataResult.addProperty("data", "");
		}
		logger.info("getTempleteMasterList=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
	
}
