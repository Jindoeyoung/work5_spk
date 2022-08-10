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
import com.spk.api.entity.ComApiRel;
import com.spk.api.entity.ComScen;
import com.spk.api.mapper.ComApiRelMapper;

@RestController
@RequestMapping(value = "/comapiRel", produces = "application/json; charset=utf8")
public class ComApiRelController {

	@Autowired 
	private ComApiRelMapper comapirelMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("")
	public int post(
				   @RequestParam("com_id") 	  	 String com_id
				  ,@RequestParam("api_id")       String api_id
				  ,@RequestParam("reg_dt")       String reg_dt
				  ,@RequestParam("reg_id")       String reg_id
				  ,@RequestParam("upt_dt")       String upt_dt
				  ,@RequestParam("upt_id")       String upt_id
				  ,@RequestParam("api_src")      String api_src
				   ) {
		return comapirelMapper.insertParam(
				   com_id
				  ,api_id
				  ,reg_dt
				  ,reg_id
				  ,upt_dt
				  ,upt_id
				  ,api_src
				);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("")
//	@PostMapping("")
	public int post(@RequestBody ComApiRel comapirel) {
		return comapirelMapper.insertBody(comapirel);
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ComApiRel> getAll() {
		return comapirelMapper.getAll();
	}
	
	// list (특정 com_id 에 해당하는 api_id 여러 건)
	@GetMapping("/com_id={com_id}")
	public String getByComId(@PathVariable("com_id") String com_id) {

		// Return할 최종 결과값		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		List<ComApiRel> datas = (List<ComApiRel>) comapirelMapper.getByComId(com_id);
		
		for (ComApiRel item : datas) {
		System.out.println("[getByComId] item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
			
			jsonObj1.addProperty("com_id", item.getCom_id());
			jsonObj1.addProperty("api_id", item.getApi_id());
			jsonObj1.addProperty("api_src", item.getApi_src());
			jsonObj1.addProperty("reg_dt", item.getReg_dt());
			jsonObj1.addProperty("reg_id", item.getReg_id());
			jsonObj1.addProperty("upt_dt", item.getUpt_dt());
			jsonObj1.addProperty("upt_id", item.getUpt_id());
			jsonArr1.add(jsonObj1);		
	
			dataResult.add("data", jsonArr1);
		}
		
		return dataResult.toString();		
	}	
	
	
//  이하는 only one row 조회
//	// one
//	@GetMapping("/{com_id},{api_id}")
//	public ComApiRel getByComApiId(
//			@PathVariable("com_id") String com_id
//		   ,@PathVariable("api_id") String api_id
//			) {
//		return comapirelMapper.getByComApiId(com_id, api_id);
//	}	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{com_id}")	
//	@PutMapping("/{com_id},{api_id}")  	
	@PutMapping("/com_id={com_id},api_id={api_id}")
	public int put(
				   @PathVariable("com_id") 		 String com_id
				  ,@PathVariable("api_id") 		 String api_id
				  ,@RequestParam("upt_dt")       String upt_dt
				  ,@RequestParam("upt_id")       String upt_id
				  ,@RequestParam("api_src")      String api_src
		   ) {		
		return comapirelMapper.updateParam(
				  com_id
				  ,api_id
				  ,upt_dt
				  ,upt_id
				  ,api_src
		  );				
	}
	
	// Body
	@PostMapping("/{com_id},{api_id}")	
//	@PutMapping("/{com_id}")  
	public int put(@RequestBody ComApiRel comapirel) {
		return comapirelMapper.updateBody(comapirel);			
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/com_id={com_id},api_id={api_id}")	
	public int delete(
			@PathVariable("com_id") String com_id
		   ,@PathVariable("api_id") String api_id	
			) {
		return comapirelMapper.delete(com_id, api_id);
	}	
	
}
