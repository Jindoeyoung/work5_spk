package com.spk.api.controller.system;

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
import com.spk.api.entity.system.UserInfo;
import com.spk.api.mapper.system.UserLoginMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/background-info", produces = "application/json; charset=utf8")
//@RequestMapping(value = "/login-info", produces = "application/json; charset=utf8")
public class UserLoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private UserLoginMapper userLoginMapper;
	
	AuthCheck authcheck = new AuthCheck();

	@PostMapping("/")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getBySabun(@RequestBody UserInfo _userInfo) throws Exception {
		
		if (!authcheck.getMetaAuthErrGenerator(_userInfo.getApikey()).equals("{}")) {
			logger.info("[userLoginController][getBySabun] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_userInfo.getApikey());
		}		

		UserInfo userInfo = userLoginMapper.getBySabun(_userInfo.getSpike_id());
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
//		String Message = "SUCCESS";
//		dataResult.addProperty("reason", Message);
//		dataResult.addProperty("result", "1");			

		if (userInfo != null) {	
			logger.info("[userLoginController][getBySabun] item==>"+userInfo);

			String Message = "SUCCESS";
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", "1");			
			
			JsonObject Obj1 = new JsonObject();
			JsonObject Obj2 = new JsonObject();
			
//			Obj1.addProperty("user_id", userInfo.getSpike_id());
//			Obj1.addProperty("user_nm", userInfo.getName());
//			Obj1.addProperty("user_grp_cd", userInfo.getUser_grp_cd());
			Obj1.addProperty("background_id", userInfo.getBackground_id());
			Obj1.addProperty("image_src", userInfo.getImage_src());
			
			jsonArr1.add(Obj1);		
	
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);			
		} else {
			
			String Message = "Data Not Found";
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", "-1");
			dataResult.addProperty("data", "");
		}
		
		return dataResult.toString();
	}	
	
}
