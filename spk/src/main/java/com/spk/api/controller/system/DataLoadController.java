package com.spk.api.controller.system;

import java.util.List;

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
import com.spk.api.entity.StHakJeokM;
import com.spk.api.entity.system.UserInfo;
import com.spk.api.mapper.system.DataLoadMapper;
import com.spk.api.mapper.system.UserLoginMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/data-load", produces = "application/json; charset=utf8")
public class DataLoadController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private DataLoadMapper dataLoadMapper;
//	private UserLoginMapper userLoginMapper;

	@PostMapping("/{from_sabun},{to_sabun}")
//	@GetMapping("/{from_sabun},{to_sabun}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getBySabun(@PathVariable("from_sabun") String from_sabun
			                ,@PathVariable("to_sabun") String to_sabun) throws Exception {
//	public String getBySabun(@RequestBody UserInfo _userInfo) throws Exception {		
		
		logger.info("from sabun =>"+from_sabun);
		logger.info("to sabun =>"+to_sabun);
		
		List<UserInfo> datas = dataLoadMapper.getBySabunRange(from_sabun, to_sabun);
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "";
//		dataResult.addProperty("reason", Message);
//		dataResult.addProperty("result", "1");			

//		if (userInfo != null) {	
//			logger.info("[userLoginController][getBySabun] item==>"+userInfo);

			Message = "SUCCESS";
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", "1");			
			

			
		if (datas.size() > 0) {
				
			for (UserInfo item : datas) {
				logger.info("item.getUser_id() @@@ =>"+item.getUser_id());
				
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();				
				
				Obj1.addProperty("user_id", item.getUser_id());
				Obj1.addProperty("user_nm", item.getUser_nm());
				Obj1.addProperty("sabun", item.getSabun());
				
				jsonArr1.add(Obj1);		
	
				Obj2.add("result", jsonArr1);
				dataResult.add("data", Obj2);
			}
			
		} else {
			
			Message = "Data Not Found";
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", "-1");
			dataResult.addProperty("data", "");
		}
		
		return dataResult.toString();
	}	
	
}
