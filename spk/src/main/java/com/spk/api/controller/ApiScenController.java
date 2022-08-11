package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.ApiScen;
import com.spk.api.entity.ComScen;
import com.spk.api.mapper.ApiScenMapper;

@RestController
@RequestMapping(value = "/apiscen", produces = "application/json; charset=utf8")
public class ApiScenController {

	@Autowired 
	private ApiScenMapper apiscenMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
////	@PutMapping("")
//	@PostMapping("")
//	public int post(@RequestParam("api_scen_id")  String api_scen_id
//				   ,@RequestParam("api_id")       String api_id
//				   ,@RequestParam("reg_dt")       String reg_dt
//				   ,@RequestParam("reg_id")       String reg_id
//				   ,@RequestParam("upt_dt")       String upt_dt
//				   ,@RequestParam("upt_id")       String upt_id
//				   ,@RequestParam("func_nm")      String func_nm
//				   ,@RequestParam("proc_state")   String proc_state
//				   ,@RequestParam("use_yn")    	  String use_yn) {				   
////				   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
////				   ,@RequestParam("dev_to_dt")    String dev_to_dt) {
//		return apiscenMapper.insertParam(
//					api_scen_id
//					,api_id
//					,reg_dt
//					,reg_id
//					,upt_dt
//					,upt_id
//					,func_nm
//					,proc_state
//					,use_yn					
////					,dev_fr_dt
////					,dev_to_dt
//					);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("/ins")
	public int post(@RequestBody ApiScen apiscen) {
		return apiscenMapper.insertBody(apiscen);
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ApiScen> getAll() {
		return apiscenMapper.getAll();
	}
	
	
	// list (특정 api_id 에 해당하는 시나리오 여러 건)
//	@GetMapping("/api_id={api_id}")
//	public String getByApiId(@PathVariable("api_id") String api_id) {
	@PostMapping("/lst")
	public String getByApiId(@RequestBody ApiScen _apiscen) {
	
		// Return할 최종 결과값		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		List<ApiScen> datas = (List<ApiScen>) apiscenMapper.getByApiId(_apiscen.getApi_id());
		
		for (ApiScen item : datas) {
			System.out.println("[ApiScenController][getByApiId] item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
			
			jsonObj1.addProperty("api_scen_id", item.getApi_scen_id());
			jsonObj1.addProperty("api_id", item.getApi_id());
			jsonObj1.addProperty("reg_dt", item.getReg_dt());
			jsonObj1.addProperty("reg_id", item.getReg_id());
			jsonObj1.addProperty("upt_dt", item.getUpt_dt());
			jsonObj1.addProperty("upt_id", item.getUpt_id());
			jsonObj1.addProperty("func_nm", item.getFunc_nm());
			jsonObj1.addProperty("proc_state", item.getProc_state());
			jsonObj1.addProperty("use_yn", item.getUse_yn());
			jsonArr1.add(jsonObj1);		
	
			dataResult.add("data", jsonArr1);
		}
		
		return dataResult.toString();
	}
	
//	이하는 only one row 조회	
//	// one
//	@GetMapping("/{api_scen_id},{api_id}")
//	public ApiScen getByScenApiId(
//			@PathVariable("api_scen_id") String api_scen_id
//		   ,@PathVariable("api_id") String api_id
//			) {
//		return apiscenMapper.getByApiScenId(api_scen_id, api_id);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
//	// Params
////	@PostMapping("/{api_scen_id},{api_id}")	
//	@PutMapping("/api_scen_id={api_scen_id},api_id={api_id}")  	
//	public int put(
//			@PathVariable("api_scen_id")  String api_scen_id
//		   ,@PathVariable("api_id")       String api_id	
//		   ,@RequestParam("upt_dt")       String upt_dt
//		   ,@RequestParam("upt_id")       String upt_id
//		   ,@RequestParam("func_nm")      String func_nm
//		   ,@RequestParam("proc_state")   String proc_state
//		   ,@RequestParam("use_yn")       String use_yn) {		   
////		   ,@RequestParam("dev_fr_dt")     String dev_fr_dt
////		   ,@RequestParam("dev_to_dt")     String dev_to_dt) {		
//		return apiscenMapper.updateParam(
//				api_scen_id
//				,api_id
//				,upt_dt
//				,upt_id
//				,func_nm
//				,proc_state
//				,use_yn				
////				,dev_fr_dt
////				,dev_to_dt
//				);				
//	}
	
	// Body
//	@PostMapping("/{api_scen_id},{api_id}")	
	@PutMapping("/upt")  
	public int put(@RequestBody ApiScen apiscen) {
		return apiscenMapper.updateBody(apiscen);			
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@DeleteMapping("/api_scen_id={api_scen_id},api_id={api_id}")	
//	public int delete(
//			@PathVariable("api_scen_id") String api_scen_id
//		   ,@PathVariable("api_id") String api_id
//			) {
//		return apiscenMapper.delete(api_scen_id, api_id);
//	}
	
	// 삭제 - 단건
	@DeleteMapping("/del")	
	public int delete(@RequestBody ApiScen apiscen) {
		return apiscenMapper.delete(apiscen);
	}	
	
	// 삭제 - 여러행
	@DeleteMapping("/dels")
	public int deleteMulti(@RequestBody ApiScen apiscen) {
		return apiscenMapper.deleteMulti(apiscen);
	}	
	
}
