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
		JsonArray jsonArr1 = new JsonArray();  // Element 타입 용
		JsonArray jsonArr2 = new JsonArray();  // Element 밸류 용
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");	
		
		JsonObject obj1 = new JsonObject();

		if (elementM != null) {
		
			obj1.addProperty("key", elementM.getCol_nm());
			obj1.addProperty("value", elementM.getCol_desc());
		
			// 엘리먼트 타입/밸류 set
			if (elementTyp.size() > 0) {
				
				for (ColumnElementTyp item : elementTyp) {
					// Element 타입 set
					JsonObject obj2 = new JsonObject();
					obj2.addProperty("elementType", item.getElement_typ());

					// Element 밸류 set
					List<ColumnElementVal> elementVal = (List<ColumnElementVal>) columnElementValMapper.getColumnElementValList(item.getTbl_nm(), item.getCol_nm(), item.getElement_typ());
					if (elementVal.size() > 0) {
						for (ColumnElementVal item2 : elementVal) {
//							jsonArr2.add(item2.getElement_val());
							jsonArr2.add(Integer.parseInt(item2.getElement_val()));
						}
						obj2.add("elementValue", jsonArr2);
					} else {

						obj2.addProperty("elementValue", " ");
					}
					
					jsonArr1.add(obj2);
				}
				
					
				obj1.add("elements", jsonArr1);
				dataResult.add("data", obj1);
					
				
			}
		
		obj1.addProperty("elementSelected", 0);
		obj1.addProperty("permission", elementM.getCol_auth());
		
		dataResult.add("data", obj1);			

			
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
