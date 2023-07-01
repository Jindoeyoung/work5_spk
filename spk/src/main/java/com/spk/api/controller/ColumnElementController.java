package com.spk.api.controller;

import java.util.Arrays;
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
import com.spk.api.entity.BackgroundM;
import com.spk.api.entity.ColumnElement;
import com.spk.api.entity.ColumnElementM;
import com.spk.api.entity.ColumnElementTyp;
import com.spk.api.entity.ColumnElementVal;
import com.spk.api.mapper.ColumnElementMMapper;
import com.spk.api.mapper.ColumnElementTypMapper;
import com.spk.api.mapper.ColumnElementValMapper;
//import com.spk.api.mapper.ColumnElementMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/column-element", produces = "application/json; charset=utf8")
public class ColumnElementController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private ColumnElementMapper columnElementMapper;
	
	@Autowired
	private ColumnElementMMapper columnElementMMapper;	
	
	@Autowired
	private ColumnElementTypMapper columnElementTypMapper;	
	
	@Autowired
	private ColumnElementValMapper columnElementValMapper;	
	
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getColumnElementList(@RequestBody ColumnElementM colElementM) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(colElementM.getApikey()).equals("{}")) {
			logger.info("[ColumnElementController][getColumnElementList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(colElementM.getApikey());
		}
		
		
		// Element 마스터
		ColumnElementM elementM = columnElementMMapper.getColumnElementMstList(colElementM);
		// Element 타입
		List<ColumnElementTyp> elementTyp = (List<ColumnElementTyp>) columnElementTypMapper.getColumnElementTypList(colElementM);
		

		
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();			
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");	
		
		JsonObject obj1 = new JsonObject();
//		JsonObject obj2 = new JsonObject();
		JsonObject obj3 = new JsonObject();			
			
			

		if (elementM != null) {
		
			obj1.addProperty("key", elementM.getCol_nm());
			obj1.addProperty("value", elementM.getCol_desc());
		
			// 엘리먼트 TYPE
			if (elementTyp.size() > 0) {
				
				String[] arrElementTyp = new String[elementTyp.size()];
				int i = 0;
				
				for (ColumnElementTyp item : elementTyp) {
				JsonObject obj2 = new JsonObject();
				logger.info("=== START ========");	
//					logger.info("item  #####>"+item);
//					logger.info("elementTyp.size()===>"+elementTyp.size());
					logger.info("item.getElement_typ()  #####>"+item.getElement_typ());

					logger.info(" [i]   #####>"+i);
					arrElementTyp[i] = item.getElement_typ();
					
					logger.info("arrElementTyp[i]   #####>"+arrElementTyp[i]);
					
					obj2.addProperty("elementType", item.getElement_typ());
					

					i++;
					
				logger.info("============= END =======  ");
				jsonArr1.add(obj2);
				}
				
					
				obj1.add("elements", jsonArr1);
				dataResult.add("data", obj1);
					
				
			}
		
		obj1.addProperty("elementSelected", "0");
		obj1.addProperty("permission", elementM.getCol_auth());
		
		dataResult.add("data", obj1);			

		
		//Obj2.add("background_size", jsonArr1);
//		dataResult.add("data", obj1);			
		
		
			
			
			
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
				
				
//			}
			
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
