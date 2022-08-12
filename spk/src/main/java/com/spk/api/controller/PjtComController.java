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
import com.spk.api.security.SignVerifier;
import com.spk.api.util.Utils;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import com.google.gson.JsonArray;
import org.apache.commons.lang3.StringUtils;

@RestController
//@RequestMapping("/pjt")
@RequestMapping(value = "/pjt", produces = "application/json; charset=utf8")
public class PjtComController {

	@Autowired 
	private PjtComMapper pjtcomMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
//	@GetMapping("")	
	@PostMapping("/lst")	
	public String getAll(@RequestBody PjtCom _pjtcom) throws Exception {

		
        //============================================================
        //< 인증키
        //============================================================			
		String apikey = _pjtcom.getApikey();
		System.out.println("apikey:"+apikey);

		
		Utils utils = new Utils();
		SignVerifier verifier = new SignVerifier();
		JsonObject result = new JsonObject();

		String signature = apikey;
		
        //============================================================
        //< 서버인증 처리
        //============================================================			
        if (!verifier.verifySignature(signature)) {
//        	logger.info("[DonwloadMetaController.hpMetaDataInfo] AUTHENTICATION RESTRICTIONS");
        	
        	System.out.println("[DonwloadMetaController.hpMetaDataInfo] AUTHENTICATION RESTRICTIONS");
        	
            result = utils.getMetaErrGenerator(10000, "AUTH");
            return result.toString();
        }		
		
		
		JsonObject dataResult = new JsonObject();
//		JsonObject jsonObj1 = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();		
		
		for (PjtCom item : datas) {
			System.out.println("[PjtComController][getAll] item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
			JsonObject lineData = new JsonObject();
			
			jsonObj1.addProperty("com_id", item.getCom_id());
			jsonObj1.addProperty("com_nm", item.getCom_nm());
			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
			jsonObj1.addProperty("requester", item.getRequester());
			jsonObj1.addProperty("owner", item.getOwner());
			jsonObj1.addProperty("participant", item.getParticipant());
			jsonObj1.addProperty("scenario", item.getScenario());
			jsonObj1.addProperty("scen_cnt", item.getScen_cnt());		
			jsonArr1.add(jsonObj1);
			
			lineData.add("result", jsonArr1);
			dataResult.add("data", lineData);
			
		}		
				
		return dataResult.toString();		
		
	}
	

	
	
	
// 원본	
	
//	//-------------------------------------------------------------------------------------------------------------------------------------
//	// SELECT
//	//-------------------------------------------------------------------------------------------------------------------------------------
//	// list
////	@GetMapping("")	
//	@PostMapping("/lst")	
//	public String getAll(@RequestBody PjtCom _pjtcom) throws Exception {
//
//		
//        //============================================================
//        //< 인증키
//        //============================================================			
//		String apikey = _pjtcom.getApikey();
//		System.out.println("apikey:"+apikey);
//
//		
//		Utils utils = new Utils();
//		SignVerifier verifier = new SignVerifier();
//		JsonObject result = new JsonObject();
//
//		String signature = apikey;
//		
//        //============================================================
//        //< 서버인증 처리
//        //============================================================			
//        if (!verifier.verifySignature(signature)) {
////        	logger.info("[DonwloadMetaController.hpMetaDataInfo] AUTHENTICATION RESTRICTIONS");
//        	
//        	System.out.println("[DonwloadMetaController.hpMetaDataInfo] AUTHENTICATION RESTRICTIONS");
//        	
//            result = utils.getMetaErrGenerator(10000, "AUTH");
//            return result.toString();
//        }		
//		
//		
//		JsonObject dataResult = new JsonObject();
////		JsonObject jsonObj1 = new JsonObject();
//		JsonArray jsonArr1 = new JsonArray();		
//		
//		String Message = "SUCCESS";
//		dataResult.addProperty("reason", Message);
//		dataResult.addProperty("result", "1");		
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();		
//		
//		for (PjtCom item : datas) {
////		System.out.println("item==>"+item);
////		System.out.println("item.getCom_id()==>"+item.getCom_id());
////		
////		
////			String com_id = null;  
////			com_id = item.getCom_id();
//		
//			JsonObject jsonObj1 = new JsonObject();
////			JsonArray jsonArr1 = new JsonArray();				
//			
//			jsonObj1.addProperty("com_id", item.getCom_id());
//			jsonObj1.addProperty("com_nm", item.getCom_nm());
//			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
//			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
//			jsonObj1.addProperty("requester", item.getRequester());
//			jsonObj1.addProperty("owner", item.getOwner());
//			jsonObj1.addProperty("participant", item.getParticipant());
//			jsonObj1.addProperty("scenario", item.getScenario());
//			jsonObj1.addProperty("scen_cnt", item.getScen_cnt());		
//			jsonArr1.add(jsonObj1);		
//			
//			dataResult.add("data", jsonArr1);
//			
//			
//		}		
//		
////		try {
////			result = utils.getMultiGenerator(apimstMapper.getAll().toString());
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//				
//		return dataResult.toString();		
//		
//	}	
	
	
	
	
	
}
