package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; // Body 방식
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.ApiMst;
import com.spk.api.mapper.ApiMstMapper;
import com.spk.api.util.Utils;
//import com.spk.api.security.SignVerifier;

@RestController
//@RequestMapping("/api")
@RequestMapping(value = "/api", produces = "application/json; charset=utf8")
public class ApiMstController {
	
	@Autowired 
	private ApiMstMapper apimstMapper;

//	SignVerifier verifier = new SignVerifier();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("")
	public int post(@RequestParam("api_id") 	  String api_id
				   ,@RequestParam("reg_dt")       String reg_dt
				   ,@RequestParam("reg_id")       String reg_id
				   ,@RequestParam("upt_dt")       String upt_dt
				   ,@RequestParam("upt_id")       String upt_id
				   ,@RequestParam("api_nm")       String api_nm
				   ,@RequestParam("api_cate")     String api_cate
				   ,@RequestParam("version")      String version
				   ,@RequestParam("param")	      String param
				   ,@RequestParam("res_form")     String res_form
				   ,@RequestParam("rtn_type")     String rtn_type
				   ,@RequestParam("method")       String method
				   ,@RequestParam("url")	      String url
				   ,@RequestParam("proc_state")   String proc_state
				   ,@RequestParam("proc_rate")    String proc_rate
				   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				   ,@RequestParam("dev_to_dt")    String dev_to_dt
				   ,@RequestParam("requester")    String requester
				   ,@RequestParam("owner")	      String owner
				   ,@RequestParam("developer")    String developer
				   ,@RequestParam("participant")  String participant
				   ,@RequestParam("scenario")     String scenario) {
		return apimstMapper.insertParam(
					api_id
					,reg_dt
					,reg_id
					,upt_dt
					,upt_id
					,api_nm
					,api_cate
					,version
					,param
					,res_form
					,rtn_type
					,method
					,url
					,proc_state
					,proc_rate
					,dev_fr_dt
					,dev_to_dt
					,requester
					,owner
					,developer
					,participant
					,scenario
					);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("")
//	@PostMapping("")
	public int post(@RequestBody ApiMst apimst) {
		return apimstMapper.insertBody(apimst);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public String getAll() {



//		Utils utils = new Utils();
		
		// Return할 최종 결과값		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		List<ApiMst> datas = (List<ApiMst>) apimstMapper.getAll();		
		
		for (ApiMst item : datas) {
		System.out.println("item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
		
			jsonObj1.addProperty("api_id", item.getApi_id());
			jsonObj1.addProperty("reg_dt", item.getReg_dt());
			jsonObj1.addProperty("reg_id", item.getReg_id());
			jsonObj1.addProperty("upt_dt", item.getUpt_dt());
			jsonObj1.addProperty("upt_id", item.getUpt_id());
			jsonObj1.addProperty("api_nm", item.getApi_nm());
			jsonObj1.addProperty("api_cate", item.getApi_cate());
			jsonObj1.addProperty("version", item.getVersion());
			jsonObj1.addProperty("param", item.getParam());
			jsonObj1.addProperty("res_form", item.getRes_form());		
			jsonObj1.addProperty("rtn_type", item.getRtn_type());
			jsonObj1.addProperty("method", item.getMethod());
			jsonObj1.addProperty("url", item.getUrl());
			jsonObj1.addProperty("proc_state", item.getProc_state());
			jsonObj1.addProperty("proc_rate", item.getProc_rate());
			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
			jsonObj1.addProperty("requester", item.getRequester());
			jsonObj1.addProperty("owner", item.getOwner());
			jsonObj1.addProperty("developer", item.getDeveloper());
			jsonObj1.addProperty("participant", item.getParticipant());
			jsonObj1.addProperty("scenario", item.getScenario());
			jsonArr1.add(jsonObj1);		
			
	//		JsonObject lineData = new JsonObject();
	//		lineData.add("result", jsonArr1);
			dataResult.add("data", jsonArr1);		

		}		
		
//		try {
//			result = utils.getMultiGenerator(apimstMapper.getAll().toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
				
		return dataResult.toString();
//		return apimstMapper.getByApiId(api_id).toString();
	}
	
//	// list
//	@GetMapping("")
//	public List<ApiMst> getAll() {
//
////	public List<ApiMst> getAll(@PathVariable("api_key") String api_key) {		
////		SignVerifier verifier = new SignVerifier();
////		
////		  if (!verifier.verifySignature(api_key)) {
////		//		response.sendError(HttpServletResponse.SC_NOT_FOUND, "주어진 키에 해당하는 파일 정보가 없습니다.");
////				return null;
////		  }		
//		
//		return apimstMapper.getAll();
//	}
	
//	//================TRY(1)===================================
//	// one
//	@GetMapping(value = "/{api_id}")
//	public String getByApiId(@PathVariable("api_id") String api_id) {
//		
//		// Return할 최종 결과값
//		JsonObject result = new JsonObject();
//		Utils utils = new Utils();
//		
//		System.out.println("123@@=>"+apimstMapper.getByApiId(api_id));
//		
//		try {
//			result = utils.getOneGenerator(apimstMapper.getByApiId(api_id).toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//				
//		return result.toString();
////		return apimstMapper.getByApiId(api_id).toString();
//	}	
//	//================TRY(1)===================================
	
	//================TRY(2)===================================
	// one
	@GetMapping(value = "/{api_id}")
	public String getByApiId(@PathVariable("api_id") String api_id) {
		
		
		System.out.println("[ApiMstController]getByApiId=>"+apimstMapper.getByApiId(api_id));
		
		ApiMst apimst = apimstMapper.getByApiId(api_id);
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		JsonObject jsonObj1 = new JsonObject();
		
		jsonObj1.addProperty("api_id", apimst.getApi_id());
		jsonObj1.addProperty("reg_dt", apimst.getReg_dt());
		jsonObj1.addProperty("reg_id", apimst.getReg_id());
		jsonObj1.addProperty("upt_dt", apimst.getUpt_dt());
		jsonObj1.addProperty("upt_id", apimst.getUpt_id());
		jsonObj1.addProperty("api_nm", apimst.getApi_nm());
		jsonObj1.addProperty("api_cate", apimst.getApi_cate());
		jsonObj1.addProperty("version", apimst.getVersion());
		jsonObj1.addProperty("param", apimst.getParam());
		jsonObj1.addProperty("res_form", apimst.getRes_form());		
		jsonObj1.addProperty("rtn_type", apimst.getRtn_type());
		jsonObj1.addProperty("method", apimst.getMethod());
		jsonObj1.addProperty("url", apimst.getUrl());
		jsonObj1.addProperty("proc_state", apimst.getProc_state());
		jsonObj1.addProperty("proc_rate", apimst.getProc_rate());
		jsonObj1.addProperty("dev_fr_dt", apimst.getDev_fr_dt());
		jsonObj1.addProperty("dev_to_dt", apimst.getDev_to_dt());
		jsonObj1.addProperty("requester", apimst.getRequester());
		jsonObj1.addProperty("owner", apimst.getOwner());
		jsonObj1.addProperty("developer", apimst.getDeveloper());
		jsonObj1.addProperty("participant", apimst.getParticipant());
		jsonObj1.addProperty("scenario", apimst.getScenario());
		jsonArr1.add(jsonObj1);		
		
//		JsonObject lineData = new JsonObject();
//		lineData.add("result", jsonArr1);
		dataResult.add("data", jsonArr1);		
		
//		String[] arrApiMst = new String[apimst. ];
		
		
				
		return dataResult.toString();
//		return apimstMapper.getByApiId(api_id).toString();
	}	
	//================TRY(2)===================================	
	
	
	
//	// one
//	@GetMapping("/{api_id}")
//	public ApiMst getByApiId(@PathVariable("api_id") String api_id) {
//		System.out.println("123=>"+apimstMapper.getByApiId(api_id));
//		return apimstMapper.getByApiId(api_id);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{api_id}")	
	@PutMapping("/{api_id}")  	
	public int put(@PathVariable("api_id") String api_id
		   ,@RequestParam("reg_dt")       String reg_dt
		   ,@RequestParam("reg_id")       String reg_id
		   ,@RequestParam("upt_dt")       String upt_dt
		   ,@RequestParam("upt_id")       String upt_id
		   ,@RequestParam("api_nm")       String api_nm
		   ,@RequestParam("api_cate")     String api_cate
		   ,@RequestParam("version")      String version
		   ,@RequestParam("param")	      String param
		   ,@RequestParam("res_form")     String res_form
		   ,@RequestParam("rtn_type")     String rtn_type
		   ,@RequestParam("method")       String method
		   ,@RequestParam("url")	      String url
		   ,@RequestParam("proc_state")   String proc_state
		   ,@RequestParam("proc_rate")    String proc_rate
		   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
		   ,@RequestParam("dev_to_dt")    String dev_to_dt
		   ,@RequestParam("requester")    String requester
		   ,@RequestParam("owner")	      String owner
		   ,@RequestParam("developer")    String developer
		   ,@RequestParam("participant")  String participant
		   ,@RequestParam("scenario")     String scenario) {		
		return apimstMapper.updateParam(
				api_id
				,reg_dt
				,reg_id
				,upt_dt
				,upt_id
				,api_nm
				,api_cate
				,version
				,param
				,res_form
				,rtn_type
				,method
				,url
				,proc_state
				,proc_rate
				,dev_fr_dt
				,dev_to_dt
				,requester
				,owner
				,developer
				,participant
				,scenario
				);				
	}
	
	// Body
	@PostMapping("/{api_id}")	
//	@PutMapping("/{api_id}")  
	public int put(@RequestBody ApiMst apimst) {
		return apimstMapper.updateBody(apimst);			
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/{api_id}")	
	public int delete(@PathVariable("api_id") String api_id) {
		return apimstMapper.delete(api_id);
	}
	
}