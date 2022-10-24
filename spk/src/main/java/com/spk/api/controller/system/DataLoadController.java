package com.spk.api.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.redis.BusA;
import com.spk.api.entity.redis.BusADataDetail;
import com.spk.api.entity.system.UserInfo;
import com.spk.api.mapper.system.DataLoadMapper;
//import com.spk.api.security.AuthCheck;
import com.spk.api.service.redis.BusAService;

@RestController
@RequestMapping(value = "/data-load", produces = "application/json; charset=utf8")
public class DataLoadController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private DataLoadMapper dataLoadMapper;

	@Autowired
    private BusAService busAService;	
	
	@PostMapping("/{from_sabun},{to_sabun}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getBySabun(@PathVariable("from_sabun") String from_sabun
			                ,@PathVariable("to_sabun") String to_sabun) throws Exception {
		
//		logger.info("from sabun =>"+from_sabun);
//		logger.info("to sabun =>"+to_sabun);
		
		List<UserInfo> datas = dataLoadMapper.getBySabunRange(from_sabun, to_sabun);
		
//		JsonObject dataResult = new JsonObject();
//		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "";
		Message = "SUCCESS";
//		dataResult.addProperty("reason", Message);
//		dataResult.addProperty("result", "1");			
			
		
//		JsonArray jsonArrMethods = new JsonArray();
//		String[] methods = new String[1];
//		methods[0] = "R";
		
		if (datas.size() > 0) {
				
			for (UserInfo item : datas) {
//				logger.info("item.getUser_id() @@@ =>"+item.getUser_id());
				
//				JsonObject Obj1 = new JsonObject();
//				JsonObject Obj2 = new JsonObject();				
//				
//				Obj1.addProperty("user_id", item.getUser_id());
//				Obj1.addProperty("user_nm", item.getUser_nm());
//				Obj1.addProperty("sabun", item.getSabun());
//				
//				jsonArr1.add(Obj1);		
//	
//				Obj2.add("result", jsonArr1);
//				dataResult.add("data", Obj2);
				
				JsonObject dataResult = new JsonObject();
				JsonArray jsonArr1 = new JsonArray();					
				
//				dataResult.addProperty("user_id", item.getUser_id());	
				
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();				
				
				JsonArray jsonArrMethods = new JsonArray();
				String[] methods = new String[1];
				methods[0] = "R";				
				
				Obj1.addProperty("componentId", "CMC-002^FU-002-01");
				Obj1.addProperty("componentName", "(공통)_조회조건");
				
//				jsonArrMethods.add(methods[0]);
//				Obj1.add("methods", jsonArrMethods);
				
//				Obj1.addProperty("methods", Arrays.toString(methods));
				Obj1.addProperty("methods", "R");
				
				jsonArr1.add(Obj1);		
	
				
				
				dataResult.add("flag_info", jsonArr1);
				
				
				
				
				
				
				
				
//				JsonObject dataResult = new JsonObject();
//				JsonArray jsonArr1 = new JsonArray();					
				
//				dataResult.addProperty("user_id", item.getUser_id());	
				
				JsonObject Obj3 = new JsonObject();
				JsonObject Obj4 = new JsonObject();				
				
				JsonArray jsonArr2 = new JsonArray();
				
				Obj3.addProperty("componentId", "CMC-003^FU-001-01");
				Obj3.addProperty("componentName", "(공통)_그리드");
				Obj3.addProperty("apiBySelf", "/spk/hj/hakjeok-column-name");
				Obj3.addProperty("apiBySelfMethod", "POST");
				
				jsonArr2.add(Obj3);		
				
				jsonArr1.addAll(jsonArr2);				
				
				
				
				
				
				
				
				

				
//				logger.info("from sabun =>"+from_sabun);
//				logger.info("to sabun =>"+to_sabun);
				
				List<BusADataDetail> empList = new ArrayList<BusADataDetail>(); // 데이터를 저장할 List
				for(Object arr : jsonArr1) {
					
					methods = null;
					
					JsonObject obj = (JsonObject) arr; // JSONArray 데이터를 하나씩 가져와 JSONObject로 변환해준다.
				    
				    // 값을 VO에 넣어준다.
					BusADataDetail busADataDetail = new BusADataDetail();
					
					if ( obj.get("componentId") != null )
					busADataDetail.setComponentId(obj.get("componentId").toString());
					
					if ( obj.get("componentName") != null )
					busADataDetail.setComponentName(obj.get("componentName").toString());
					
					if ( obj.get("apiBySelf") != null )
					busADataDetail.setApiBySelf(obj.get("apiBySelf").toString()  );
					
					if ( obj.get("apiBySelfMethod") != null )
					busADataDetail.setApiBySelfMethod(obj.get("apiBySelfMethod").toString());
					
					String[] methods_value = new String[1];
					if ( obj.get("methods") != null )
						methods_value[0] = obj.get("methods").toString();							
					
					
					if ( methods_value[0] != null )
					busADataDetail.setMethods(methods_value);                //    methods[0].toString());
					
					
//					busADataDetail.setComponentId(obj.get("componentId").toString());
//					busADataDetail.setComponentName(obj.get("componentName").toString());					
//					busADataDetail.setApiBySelf(obj.get("apiBySelf").toString());
//					busADataDetail.setApiBySelfMethod(obj.get("apiBySelfMethod").toString());
//					busADataDetail.setMethods(methods);                //    methods[0].toString());

					empList.add(busADataDetail); // list에 추가해준다.
				}
				
				
				
				BusA busA = null;
				busA = busAService.registerUser(item.getUser_id(), empList );
//				busA = busAService.registerUser(item.getUser_id(), List<BusADataDetail> Obj1);
				
				logger.info("dataResult.toString()====>"+dataResult.toString());
			}
			
		} else {
			
//			Message = "Data Not Found";
//			dataResult.addProperty("reason", Message);
//			dataResult.addProperty("result", "-1");
//			dataResult.addProperty("data", "");
		}
		
//		return dataResult.toString();
		return "Success";
	}	
	
}
