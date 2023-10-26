package com.spk.api.controller;

//import java.util.Arrays;
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
//import com.spk.api.entity.ColumnElement;
import com.spk.api.entity.ColumnElementM;
import com.spk.api.entity.ColumnElementTyp;
import com.spk.api.entity.ColumnElementVal;
import com.spk.api.mapper.ColumnElementMMapper;
import com.spk.api.mapper.ColumnElementTypMapper;
import com.spk.api.mapper.ColumnElementValMapper;
//import com.spk.api.mapper.ColumnElementMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/column-element-bak", produces = "application/json; charset=utf8")
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
	// SELECT - LIST (칼럼 1개)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/one-col")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getColumnElementList(@RequestBody ColumnElementM colElementM) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(colElementM.getApikey()).equals("{}")) {
			logger.info("[ColumnElementController][getColumnElementList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(colElementM.getApikey());
		}
		
		// Element 마스터
		ColumnElementM elementM = columnElementMMapper.getColumnElementMstList(colElementM);
		// Element 타입
		List<ColumnElementTyp> elementTyp = (List<ColumnElementTyp>) columnElementTypMapper.getOneColumnElementTypList(colElementM);
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();  // Element 타입 용
		JsonArray jsonArr2 = new JsonArray();  // Element 밸류 용
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");	
		
		JsonObject obj1 = new JsonObject();

		if (elementM != null) {
		
			obj1.addProperty("table", elementM.getTbl_nm());
			obj1.addProperty("key", elementM.getCol_nm());
			obj1.addProperty("value", elementM.getCol_desc());
		
			// 엘리먼트 타입/밸류 set [START]
			if (elementTyp.size() > 0) {
				for (ColumnElementTyp item : elementTyp) {
					// Element 타입 set
					JsonObject obj2 = new JsonObject();
					obj2.addProperty("elementType", item.getElement_typ());

					// Element 밸류 set
					List<ColumnElementVal> elementVal = (List<ColumnElementVal>) columnElementValMapper.getColumnElementValList(item.getTbl_nm(), item.getCol_nm(), item.getElement_typ());
					if (elementVal.size() > 0) {
						for (ColumnElementVal item2 : elementVal) {
							jsonArr2.add(item2.getElement_val());
						}
						obj2.add("elementValue", jsonArr2);
					} else {
						obj2.addProperty("elementValue", "");
					}

					// 타입과 밸류를 배열에 set
					jsonArr1.add(obj2);
				}
				obj1.add("elements", jsonArr1);
				dataResult.add("data", obj1);
			}
			// 엘리먼트 타입/밸류 set [END]
		
		obj1.addProperty("elementSelected", 0);
		
		// 권한
		boolean auth = elementM.getCol_auth().equals("Y") ? true : false;
		obj1.addProperty("permission", auth);
//		obj1.addProperty("permission", elementM.getCol_auth());
		
		dataResult.add("data", obj1);			
			
		} else {
			// 데이터 없을 시
			dataResult.addProperty("data", "");
		}
		logger.info("getColumnElementList=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST (칼럼 여러개)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMultiColumnElementList(@RequestBody ColumnElementM colElementM) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(colElementM.getApikey()).equals("{}")) {
			logger.info("[ColumnElementController][getMultiColumnElementList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(colElementM.getApikey());
		}
		
//		logger.info("colElementM.getTbl_nm()==@@@@@@==>"+colElementM.getTbl_nm());
		List<ColumnElementM> elementM = columnElementMMapper.getMultiColumnElementMstList(colElementM.getTbl_nm());
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();  // 반복 행 담기 용
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");	

		if (elementM.size() > 0) {		
		
			for (ColumnElementM item : elementM) {
				
				JsonObject obj1 = new JsonObject();     // result 배열 하위 요소들  
				JsonObject obj2 = new JsonObject();		// result 하위
				JsonArray jsonArr2 = new JsonArray(); 	// Element 타입 용
				JsonArray jsonArr3 = new JsonArray(); 	// Element 밸류 용
				
//				JsonArray jsonArr4 = new JsonArray(); 	// Element 밸류 용
				
//				JsonObject obj5 = new JsonObject();
				
				obj1.addProperty("table", item.getTbl_nm());
				obj1.addProperty("key", item.getCol_nm());  	
				obj1.addProperty("value", item.getCol_desc()); 		
		
				// Element 타입
				List<ColumnElementTyp> elementTyp = (List<ColumnElementTyp>) columnElementTypMapper.getColumnElementTypList(item.getTbl_nm(), item.getCol_nm());

				// 엘리먼트 타입/밸류 set [START]
				if (elementTyp.size() > 0) {
					for (ColumnElementTyp item2 : elementTyp) {
						// Element 타입 set
						JsonObject obj3 = new JsonObject();  // elements 배열 하위 Json 담기
						obj3.addProperty("elementType", item2.getElement_typ());

						// Element 밸류 set
						List<ColumnElementVal> elementVal = (List<ColumnElementVal>) columnElementValMapper.getColumnElementValList(item2.getTbl_nm(), item2.getCol_nm(), item2.getElement_typ());
						if (elementVal.size() > 0) {
							for (ColumnElementVal item3 : elementVal) {
								JsonObject obj4 = new JsonObject();  // elementValue 배열 하위 Json 담기
								
								obj4.addProperty("key", item3.getElement_val());
								obj4.addProperty("value", item3.getElement_val_nm());
								//jsonArr3.add(item3.getElement_val_nm());
								
								jsonArr3.add(obj4);
								
							}
							obj3.add("elementValue", jsonArr3);
						} else {
							
//							logger.info("여기 시작"+item.getCol_nm()+":"+item2.getElement_typ());
							
							JsonObject obj5 = new JsonObject();  // 위로
							JsonArray jsonArr4 = new JsonArray(); 	// 위로
							
							obj5.addProperty("key", "");
							jsonArr4.add(obj5);
							obj3.add("elementValue", jsonArr4);

							
//							obj3.addProperty("elementValue", "");
							
							
							
							
						}  // End of : if (elementVal.size() > 0) {
						
// 아래 3줄과 같이 하면, 값이 있는 경우도, 위에서 이미 담았던 ojb3 에 덮어 쓰므로, 안나오게 됨 						
//						obj5.addProperty("key", "");
//						jsonArr4.add(obj5);
//						obj3.add("elementValue", jsonArr4);						
						


						// 타입과 밸류를 elements 배열에 set
						jsonArr2.add(obj3);
					}  // End of : for (ColumnElementTyp item2 : elementTyp) {
					obj1.add("elements", jsonArr2);	
				}  // End of : if (elementTyp.size() > 0) {
				// 엘리먼트 타입/밸류 set [END]
			
				obj1.addProperty("elementSelected", 0);
				
				// 권한
				boolean auth = item.getCol_auth().equals("Y") ? true : false;
				obj1.addProperty("permission", auth);
				
				jsonArr1.add(obj1);
				obj2.add("result", jsonArr1);
				
				dataResult.add("data", obj2);
			}

		} else {
			// 데이터 없을 시
			dataResult.addProperty("data", "");
		}
		
		logger.info("getColumnElementList!!=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
}
