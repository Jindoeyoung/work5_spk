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

import com.spk.api.entity.ComScen;
import com.spk.api.mapper.ComScenMapper;

@RestController
@RequestMapping("/comscen")
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
	// one
	@GetMapping("/{com_scen_id},{com_id}")
	public ComScen getByComScenId(
			@PathVariable("com_scen_id") String com_scen_id
		   ,@PathVariable("com_id") String com_id
			) {
		return comscenMapper.getByComScenId(com_scen_id, com_id);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{com_scen_id},{com_id}")	
	@PutMapping("/{com_scen_id},{com_id}")  	
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
	@DeleteMapping("/{com_scen_id},{com_id}")	
	public int delete(
			@PathVariable("com_scen_id") String com_scen_id
		   ,@PathVariable("com_id") String com_id
			) {
		return comscenMapper.delete(com_scen_id, com_id);
	}	
}
