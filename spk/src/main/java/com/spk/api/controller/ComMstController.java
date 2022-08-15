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
import com.spk.api.entity.ApiMst;
import com.spk.api.entity.ComMst;
import com.spk.api.mapper.ComMstMapper;

@RestController
//@RequestMapping("/com")
@RequestMapping(value = "/com", produces = "application/json; charset=utf8")
public class ComMstController {

	@Autowired 
	private ComMstMapper commstMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
////	@PutMapping("")
//	@PostMapping("")
//	public int post(
//				   @RequestParam("com_id") 	  	 String com_id
//				  ,@RequestParam("reg_dt")       String reg_dt
//				  ,@RequestParam("reg_id")       String reg_id
//				  ,@RequestParam("upt_dt")       String upt_dt
//				  ,@RequestParam("upt_id")       String upt_id
//				  ,@RequestParam("com_nm")       String com_nm
//				  ,@RequestParam("com_cate")     String com_cate
//				  ,@RequestParam("com_attr")     String com_attr
//				  ,@RequestParam("com_form")	 String com_form
//				  ,@RequestParam("com_src")      String com_src
//			 	  ,@RequestParam("dev_fr_dt")    String dev_fr_dt
//				  ,@RequestParam("dev_to_dt")    String dev_to_dt
//				  ,@RequestParam("use_fr_dt")	 String use_fr_dt
//				  ,@RequestParam("use_to_dt")    String use_to_dt
//				  ,@RequestParam("requester")    String requester
//				  ,@RequestParam("owner")	     String owner
//				  ,@RequestParam("developer")    String developer
//				  ,@RequestParam("participant")  String participant
//				  ,@RequestParam("scenario")     String scenario
//				   ) {
//		return commstMapper.insertParam(
//				   com_id
//				  ,reg_dt
//				  ,reg_id
//				  ,upt_dt
//				  ,upt_id
//				  ,com_nm
//				  ,com_cate
//				  ,com_attr
//				  ,com_form
//				  ,com_src
//			 	  ,dev_fr_dt
//				  ,dev_to_dt
//				  ,use_fr_dt
//				  ,use_to_dt
//				  ,requester
//				  ,owner
//				  ,developer
//				  ,participant
//				  ,scenario
//				);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("/ins")
	public int post(@RequestBody ComMst commst) {
		return commstMapper.insertBody(commst);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
//	@GetMapping("/lst")
	@PostMapping("/lst")
	public String getAll() {
		// Return할 최종 결과값		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		List<ComMst> datas = (List<ComMst>) commstMapper.getAll();		
		
		for (ComMst item : datas) {
		System.out.println("item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
		
			jsonObj1.addProperty("com_id", item.getCom_id());
			jsonObj1.addProperty("reg_dt", item.getReg_dt());
			jsonObj1.addProperty("reg_id", item.getReg_id());
			jsonObj1.addProperty("upt_dt", item.getUpt_dt());
			jsonObj1.addProperty("upt_id", item.getUpt_id());
			jsonObj1.addProperty("com_nm", item.getCom_nm());
			jsonObj1.addProperty("com_cate", item.getCom_cate());
			jsonObj1.addProperty("com_attr", item.getCom_attr());
			jsonObj1.addProperty("com_form", item.getCom_form());
			jsonObj1.addProperty("com_src", item.getCom_src());
			//jsonObj1.addProperty("api_id", item);
			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
			jsonObj1.addProperty("use_fr_dt", item.getUse_fr_dt());
			jsonObj1.addProperty("use_to_dt", item.getUse_to_dt());
			jsonObj1.addProperty("requester", item.getRequester());
			jsonObj1.addProperty("owner", item.getOwner());
			jsonObj1.addProperty("developer", item.getDeveloper());
			jsonObj1.addProperty("participant", item.getParticipant());
			jsonObj1.addProperty("scenario", item.getScenario());
			jsonArr1.add(jsonObj1);		
			
			JsonObject lineData = new JsonObject();
			lineData.add("result", jsonArr1);
			dataResult.add("data", lineData);			
			
//			dataResult.add("data", jsonArr1);		
		}		
				
		return dataResult.toString();
	}
	
//	// list
//	@GetMapping("")
//	public List<ComMst> getAll() {
//		return commstMapper.getAll();
//	}
	
	// one
//	@GetMapping("/com_id={com_id}")
//	public String getByComId(@PathVariable("com_id") String com_id) {
	@PostMapping("/dtl")
	public String getByComId(@RequestBody ComMst _commst) {

//		String com_id = _commst.getCom_id();
//		ComMst commst = commstMapper.getByComId(com_id);
		
		ComMst commst = commstMapper.getByComId(_commst.getCom_id());
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		JsonObject jsonObj1 = new JsonObject();
		
		jsonObj1.addProperty("com_id", commst.getCom_id());
		jsonObj1.addProperty("reg_dt", commst.getReg_dt());
		jsonObj1.addProperty("reg_id", commst.getReg_id());
		jsonObj1.addProperty("upt_dt", commst.getUpt_dt());
		jsonObj1.addProperty("upt_id", commst.getUpt_id());
		jsonObj1.addProperty("com_nm", commst.getCom_nm());
		jsonObj1.addProperty("com_cate", commst.getCom_cate());
		jsonObj1.addProperty("com_attr", commst.getCom_attr());
		jsonObj1.addProperty("com_form", commst.getCom_form());
		jsonObj1.addProperty("com_src", commst.getCom_src());
		//jsonObj1.addProperty("api_id", commst);
		jsonObj1.addProperty("dev_fr_dt", commst.getDev_fr_dt());
		jsonObj1.addProperty("dev_to_dt", commst.getDev_to_dt());
		jsonObj1.addProperty("use_fr_dt", commst.getUse_fr_dt());
		jsonObj1.addProperty("use_to_dt", commst.getUse_to_dt());
		jsonObj1.addProperty("requester", commst.getRequester());
		jsonObj1.addProperty("owner", commst.getOwner());
		jsonObj1.addProperty("developer", commst.getDeveloper());
		jsonObj1.addProperty("participant", commst.getParticipant());
		jsonObj1.addProperty("scenario", commst.getScenario());
		jsonArr1.add(jsonObj1);		
		
		JsonObject lineData = new JsonObject();
		lineData.add("result", jsonArr1);
		dataResult.add("data", lineData);
		
//		dataResult.add("data", jsonArr1);
				
		return dataResult.toString();		
	}	
	
	
//	// one
//	@GetMapping("/{com_id}")
//	public ComMst getByComId(@PathVariable("com_id") String com_id) {
//		return commstMapper.getByComId(com_id);
//	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
//	// Params
////	@PostMapping("/{com_id}")	
//	@PutMapping("/com_id={com_id}")  	
//	public int put(
//				   @PathVariable("com_id") 		 String com_id
//				  ,@RequestParam("upt_dt")       String upt_dt
//				  ,@RequestParam("upt_id")       String upt_id
//				  ,@RequestParam("com_nm")       String com_nm
//				  ,@RequestParam("com_cate")     String com_cate
//				  ,@RequestParam("com_attr")     String com_attr
//				  ,@RequestParam("com_form")	 String com_form
//				  ,@RequestParam("com_src")      String com_src
//			 	  ,@RequestParam("dev_fr_dt")    String dev_fr_dt
//				  ,@RequestParam("dev_to_dt")    String dev_to_dt
//				  ,@RequestParam("use_fr_dt")	 String use_fr_dt
//				  ,@RequestParam("use_to_dt")    String use_to_dt
//				  ,@RequestParam("requester")    String requester
//				  ,@RequestParam("owner")	     String owner
//				  ,@RequestParam("developer")    String developer
//				  ,@RequestParam("participant")  String participant
//				  ,@RequestParam("scenario")     String scenario
//		   ) {		
//		return commstMapper.updateParam(
//				  com_id
//				  ,upt_dt
//				  ,upt_id
//				  ,com_nm
//				  ,com_cate
//				  ,com_attr
//				  ,com_form
//				  ,com_src
//			 	  ,dev_fr_dt
//				  ,dev_to_dt
//				  ,use_fr_dt
//				  ,use_to_dt
//				  ,requester
//				  ,owner
//				  ,developer
//				  ,participant
//				  ,scenario
//		  );				
//	}
	
	// Body
//	@PostMapping("/{com_id}")	
	@PutMapping("/upt")  
	public int put(@RequestBody ComMst commst) {
		return commstMapper.updateBody(commst);			
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@DeleteMapping("/com_id={com_id}")	
//	public int delete(@PathVariable("com_id") String com_id) {
//		return commstMapper.delete(com_id);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/del")	
	public int delete(@RequestBody ComMst commst) {
		return commstMapper.delete(commst);
	}	
	
	
}