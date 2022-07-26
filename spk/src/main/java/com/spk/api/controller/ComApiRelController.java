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

import com.spk.api.entity.ComApiRel;
import com.spk.api.mapper.ComApiRelMapper;

@RestController
@RequestMapping("/comapiRel")
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
	// one
	@GetMapping("/{com_id}")
	public ComApiRel getByComApiId(
			@PathVariable("com_id") String com_id
		   ,@PathVariable("api_id") String api_id
			) {
		return comapirelMapper.getByComApiId(com_id, api_id);
	}	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{com_id}")	
	@PutMapping("/{com_id},{api_id}")  	
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
	@DeleteMapping("/{com_id},{api_id}")	
	public int delete(
			@PathVariable("com_id") String com_id
		   ,@PathVariable("api_id") String api_id	
			) {
		return comapirelMapper.delete(com_id, api_id);
	}	
	
}
