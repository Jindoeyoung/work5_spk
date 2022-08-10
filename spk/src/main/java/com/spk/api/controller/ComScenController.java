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
import com.spk.api.entity.ComMst;
import com.spk.api.entity.ComScen;
import com.spk.api.mapper.ComScenMapper;

@RestController
//@RequestMapping("/comscen")
@RequestMapping(value = "/comscen", produces = "application/json; charset=utf8")
public class ComScenController {

	@Autowired 
	private ComScenMapper comscenMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("")
	public int post(@RequestParam("com_scen_id")  String com_scen_id
				   ,@RequestParam("com_id")       String com_id
				   ,@RequestParam("reg_dt")       String reg_dt
				   ,@RequestParam("reg_id")       String reg_id
				   ,@RequestParam("upt_dt")       String upt_dt
				   ,@RequestParam("upt_id")       String upt_id
				   ,@RequestParam("func_nm")      String func_nm
				   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				   ,@RequestParam("dev_to_dt")    String dev_to_dt) {
		return comscenMapper.insertParam(
					com_scen_id
					,com_id
					,reg_dt
					,reg_id
					,upt_dt
					,upt_id
					,func_nm
					,dev_fr_dt
					,dev_to_dt
					);
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("")
//	@PostMapping("")
	public int post(@RequestBody ComScen comscen) {
		return comscenMapper.insertBody(comscen);
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ComScen> getAll() {
		return comscenMapper.getAll();
	}

	// list (특정 com_id 에 해당하는 시나리오 여러 건)
	@GetMapping("/com_id={com_id}")
	public String getByComId(@PathVariable("com_id") String com_id) {

		// Return할 최종 결과값		
		JsonObject dataResult = new JsonObject();
		
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		List<ComScen> datas = (List<ComScen>) comscenMapper.getByComId(com_id);
		
		for (ComScen item : datas) {
		System.out.println("[getByComScenId] item==>"+item);
		
			JsonObject jsonObj1 = new JsonObject();
			
			jsonObj1.addProperty("com_scen_id", item.getCom_scen_id());
			jsonObj1.addProperty("com_id", item.getCom_id());
			jsonObj1.addProperty("reg_dt", item.getReg_dt());
			jsonObj1.addProperty("reg_id", item.getReg_id());
			jsonObj1.addProperty("upt_dt", item.getUpt_dt());
			jsonObj1.addProperty("upt_id", item.getUpt_id());
			jsonObj1.addProperty("func_nm", item.getFunc_nm());
			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
			jsonArr1.add(jsonObj1);		
	
			dataResult.add("data", jsonArr1);
		}
		
		return dataResult.toString();
	}	
	
//  이하는 only one row 조회	
//	// one
//	@GetMapping("/{com_scen_id},{com_id}")
//	public ComScen getByComScenId(
//			@PathVariable("com_scen_id") String com_scen_id
//		   ,@PathVariable("com_id") String com_id
//			) {
//		return comscenMapper.getByComScenId(com_scen_id, com_id);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{com_scen_id},{com_id}")	
	@PutMapping("/com_scen_id={com_scen_id},com_id={com_id}")  	
	public int put(
			@PathVariable("com_scen_id")  String com_scen_id
		   ,@PathVariable("com_id")       String com_id	
		   ,@RequestParam("upt_dt")       String upt_dt
		   ,@RequestParam("upt_id")       String upt_id
		   ,@RequestParam("func_nm")       String func_nm
		   ,@RequestParam("dev_fr_dt")     String dev_fr_dt
		   ,@RequestParam("dev_to_dt")     String dev_to_dt) {		
		return comscenMapper.updateParam(
				com_scen_id
				,com_id
				,upt_dt
				,upt_id
				,func_nm
				,dev_fr_dt
				,dev_to_dt
				);				
	}
	
	// Body
	@PostMapping("/{com_scen_id},{com_id}")	
//	@PutMapping("/{com_scen_id},{com_id}")  
	public int put(@RequestBody ComScen comscen) {
		return comscenMapper.updateBody(comscen);			
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/com_scen_id={com_scen_id},com_id={com_id}")	
	public int delete(
			@PathVariable("com_scen_id") String com_scen_id
		   ,@PathVariable("com_id") String com_id
			) {
		return comscenMapper.delete(com_scen_id, com_id);
	}	
}
