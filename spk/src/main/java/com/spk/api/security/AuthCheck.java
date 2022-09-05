package com.spk.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.spk.api.util.Utils;

public class AuthCheck {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	private SignVerifier signverifier;
	
	public String getMetaAuthErrGenerator(String apikey) throws Exception {
	
	  //============================================================
	  //< 인증키
	  //============================================================			
	//	String apikey = _pjtcom.getApikey();
	//	//logger.info("apikey:"+apikey);
		
		Utils utils = new Utils();
		SignVerifier verifier = new SignVerifier();
		JsonObject result = new JsonObject();
		
	  //============================================================
	  //< 서버인증 처리
	  //============================================================			
	    if (!verifier.verifySignature(apikey)) {
	    	logger.info("AUTH CHECKING");
	        result = utils.getMetaErrGenerator(10000, "AUTH");
//	        System.out.println("result 1) ===>"+result.toString());
	        return result.toString();
	    }
//	    System.out.println("result 2) ===>"+result.toString());
	    return result.toString();
	    
//	    return null;
	}
}