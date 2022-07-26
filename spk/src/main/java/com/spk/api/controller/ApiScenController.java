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

import com.spk.api.entity.ApiScen;
import com.spk.api.mapper.ApiScenMapper;

@RestController
@RequestMapping("/apiscen")
public class ApiScenController {

	@Autowired 
	private ApiScenMapper apiscenMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("")
	public int post(@RequestParam("api_scen_id")  String api_scen_id
				   ,@RequestParam("api_id")       String api_id
				   ,@RequestParam("reg_dt")       String reg_dt
				   ,@RequestParam("reg_id")       String reg_id
				   ,@RequestParam("upt_dt")       String upt_dt
				   ,@RequestParam("upt_id")       String upt_id
				   ,@RequestParam("func_nm")      String func_nm
				   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				   ,@RequestParam("dev_to_dt")    String dev_to_dt) {
		return apiscenMapper.insertParam(
					api_scen_id
					,api_id
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
	// one
	@GetMapping("/{api_scen_id},{api_id}")
	public ApiScen getByScenApiId(
			@PathVariable("api_scen_id") String api_scen_id
		   ,@PathVariable("api_id") String api_id
			) {
		return apiscenMapper.getByApiScenId(api_scen_id, api_id);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{api_scen_id},{api_id}")	
	@PutMapping("/{api_scen_id},{api_id}")  	
	public int put(
			@PathVariable("api_scen_id")  String api_scen_id
		   ,@PathVariable("api_id")       String api_id	
		   ,@RequestParam("upt_dt")       String upt_dt
		   ,@RequestParam("upt_id")       String upt_id
		   ,@RequestParam("func_nm")       String func_nm
		   ,@RequestParam("dev_fr_dt")     String dev_fr_dt
		   ,@RequestParam("dev_to_dt")     String dev_to_dt) {		
		return apiscenMapper.updateParam(
				api_scen_id
				,api_id
				,upt_dt
				,upt_id
				,func_nm
				,dev_fr_dt
				,dev_to_dt
				);				
	}
	
	// Body
	@PostMapping("/{api_scen_id},{api_id}")	
//	@PutMapping("/{api_scen_id},{api_id}")  
	public int put(@RequestBody ApiScen apiscen) {
		return apiscenMapper.updateBody(apiscen);			
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/{api_scen_id},{api_id}")	
	public int delete(
			@PathVariable("api_scen_id") String api_scen_id
		   ,@PathVariable("api_id") String api_id
			) {
		return apiscenMapper.delete(api_scen_id, api_id);
	}	
	
}
