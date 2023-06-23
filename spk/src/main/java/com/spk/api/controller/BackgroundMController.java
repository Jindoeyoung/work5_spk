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
import com.spk.api.entity.BackgroundM;
import com.spk.api.mapper.BackgroundMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/background-mst", produces = "application/json; charset=utf8")
public class BackgroundMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BackgroundMMapper backgroundmmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getBackgroundMasterList(@RequestBody BackgroundM _backgroundm) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(_backgroundm.getApikey()).equals("{}")) {
			logger.info("[BackgroundMController][getBackgroundMasterList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_backgroundm.getApikey());		
		}
		
		List<BackgroundM> datas = (List<BackgroundM>) backgroundmmapper.getBackgroundMasterList(_backgroundm);				
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		

		if (datas.size() > 0) {
		
			for (BackgroundM item : datas) {
//			System.out.println("item==>"+item);
			
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();
			
				Obj1.addProperty("background_id", item.getBackground_id());
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
		logger.info("getBackgroundMasterList=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
	
}
