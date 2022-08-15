package com.spk.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spk.api.entity.ApiMst;
import com.spk.api.entity.PjtCom;
import com.spk.api.mapper.PjtComMapper;
import com.spk.api.security.AuthCheck;
import com.spk.api.security.SignVerifier;
import com.spk.api.util.Utils;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import com.google.gson.JsonArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/pjt", produces = "application/json; charset=utf8")
public class PjtComController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private PjtComMapper pjtcomMapper;
		
//	AuthCheck authcheck = new AuthCheck();
//	
//	public String PjtComController(@RequestBody PjtCom _pjtcom) throws Exception {
//
//		if (!authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey()).equals("{}")) {
//			System.out.println("123===");
//			return authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey());
//			}
//		return null;		
//		
//	}
	

	
	
//	public PjtComController(@RequestBody PjtCom _pjtcom) throws Exception {
//		logger.info("====================1");
//		
//		
//        //============================================================
//        //< 인증키
//        //============================================================			
//		String apikey = _pjtcom.getApikey();
//		//logger.info("apikey:"+apikey);
//		
//		Utils utils = new Utils();
//		SignVerifier verifier = new SignVerifier();
//		JsonObject result = new JsonObject();
//		
//        //============================================================
//        //< 서버인증 처리
//        //============================================================			
//        if (!verifier.verifySignature(apikey)) {
//        	logger.info("[PjtComController][getAll] AUTHENTICATION RESTRICTIONS");
//            result = utils.getMetaErrGenerator(10000, "AUTH");
//            authCheck(result);
////            return result.toString();
//        }		
//		
////        return result.toString();
//	}
//	
//	
//	public String authCheck(JsonObject result) throws Exception {
//		return result.toString();
//	}
	
	
	AuthCheck authcheck = new AuthCheck();

	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@PostMapping("/lst")	
	public String getAll(@RequestBody PjtCom _pjtcom) throws Exception {
		
//		AuthCheck cuthcheck = new AuthCheck();
		
//		authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey());
		
//		String chk = authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey());
//		return chk;
		
		if (!authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey()).equals("{}")) {
			System.out.println("123===");
			return authcheck.getMetaAuthErrGenerator(_pjtcom.getApikey());
		}
		
		
//        //============================================================
//        //< 인증키
//        //============================================================			
//		String apikey = _pjtcom.getApikey();
//		//logger.info("apikey:"+apikey);
//		
//		Utils utils = new Utils();
//		SignVerifier verifier = new SignVerifier();
//		JsonObject result = new JsonObject();
//		
//        //============================================================
//        //< 서버인증 처리
//        //============================================================			
//        if (!verifier.verifySignature(apikey)) {
//        	logger.info("[PjtComController][getAll] AUTHENTICATION RESTRICTIONS");
//            result = utils.getMetaErrGenerator(10000, "AUTH");
//            return result.toString();
//        }		
		
        //============================================================
        //< Json 포맷 생성
        //============================================================        
		JsonObject dataResult = new JsonObject();
		JsonArray lineArr = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");
		
		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
		
		for (PjtCom item : datas) {
			System.out.println("[PjtComController][getAll] item==>"+item);
		
			JsonObject Obj1 = new JsonObject();
			JsonObject Obj2 = new JsonObject();
			
			Obj1.addProperty("com_id", item.getCom_id());
			Obj1.addProperty("com_nm", item.getCom_nm());
			Obj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			Obj1.addProperty("dev_to_dt", item.getDev_to_dt());
			Obj1.addProperty("requester", item.getRequester());
			Obj1.addProperty("owner", item.getOwner());
			Obj1.addProperty("participant", item.getParticipant());
			Obj1.addProperty("scenario", item.getScenario());
			Obj1.addProperty("scen_cnt", item.getScen_cnt());
			Obj1.addProperty("partici_cnt", item.getPartici_cnt());
			Obj1.addProperty("proc_state", item.getProc_state());
			Obj1.addProperty("proc_rate", item.getProc_rate());
			
			lineArr.add(Obj1);
			
			Obj2.add("result", lineArr);
			dataResult.add("data", Obj2);
		}		
				
		return dataResult.toString();		
		
	}
	
}
