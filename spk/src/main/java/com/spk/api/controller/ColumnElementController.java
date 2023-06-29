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
import com.spk.api.entity.ColumnElement;
import com.spk.api.mapper.ColumnElementMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/column-element", produces = "application/json; charset=utf8")
public class ColumnElementController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ColumnElementMapper columnElementMapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getColumnElementList(@RequestBody ColumnElement colElement) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(colElement.getApikey()).equals("{}")) {
			logger.info("[BackgroundMController][getBackgroundMasterList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(colElement.getApikey());
		}
		
		List<ColumnElement> datas = (List<ColumnElement>) columnElementMapper.getColumnElementList(colElement);
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		JsonArray jsonArr2 = new JsonArray();
		String result = "result";
		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty(result, "1");		

		if (datas.size() > 0) {
		
			for (ColumnElement item : datas) {
				
			System.out.println("item==>"+item);
			
			
			
			JsonObject obj1 = new JsonObject();
		
			obj1.addProperty("key", item.getCol_nm());
			obj1.addProperty("value", item.getCol_desc());
			obj1.addProperty("elementType", item.getElement_typ());
			obj1.addProperty("elementValue", item.getElement_val());
				
			
			
			obj1.addProperty("elementSelected", "0");
			obj1.addProperty("permission", item.getCol_auth());
			
			
			dataResult.add("data", obj1);
			
			//Obj2.add("background_size", jsonArr1);
//			dataResult.add("data", obj1);			
			
			
			
			
			
			
			
			
			
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@			
//				JsonObject obj1 = new JsonObject();
//				JsonObject obj2 = new JsonObject();
//				JsonObject obj3 = new JsonObject();
//			
//				obj1.addProperty("key", item.getCol_nm());
//				obj1.addProperty("value", item.getCol_desc());
//				
//				
////				for (ColumnElement item_2 : datas) {
////					System.out.println("item_2 [S] @@@ ==>"+item);
//					
//				obj2.addProperty("elementType", item.getElement_typ());
//				
//				
////				if (item.getElement_val() != "") { 
////				
////				
////					jsonArr2.add(obj2);					
////					obj2.add("elementValue", jsonArr2);
////				} else {
////					obj2.add("elementValue", jsonArr2);
////				}
//				
//				
//				
//
//					
//					jsonArr1.add(obj2);
//					
//					obj1.add("elements", jsonArr1);
//					dataResult.add("data", obj1);						
//					
////					System.out.println("item_2 [E] @@@ ==>"+item);
////				}
//				
//				
//				obj1.addProperty("elementSelected", "0");
//				obj1.addProperty("permission", item.getCol_auth());
//				
//				
//		
//				
//				//Obj2.add("background_size", jsonArr1);
////				dataResult.add("data", obj1);
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@				
				
				
			}
			
		} else {
			
			JsonObject Obj3 = new JsonObject();			
			
			Obj3.add("background_size", jsonArr1);
			dataResult.add("data", Obj3);			
			
//			dataResult.addProperty("data", "");
		}
		logger.info("getColumnElementList=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
	
}
