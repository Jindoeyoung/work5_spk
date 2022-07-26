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

import com.spk.api.entity.ComMst;
import com.spk.api.mapper.ComMstMapper;

@RestController
@RequestMapping("/com")
public class ComMstController {

	@Autowired 
	private ComMstMapper commstMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@PutMapping("")
	@PostMapping("")
	public int post(
				   @RequestParam("com_id") 	  	 String com_id
				  ,@RequestParam("reg_dt")       String reg_dt
				  ,@RequestParam("reg_id")       String reg_id
				  ,@RequestParam("upt_dt")       String upt_dt
				  ,@RequestParam("upt_id")       String upt_id
				  ,@RequestParam("com_nm")       String com_nm
				  ,@RequestParam("com_cate")     String com_cate
				  ,@RequestParam("com_attr")     String com_attr
				  ,@RequestParam("com_form")	 String com_form
				  ,@RequestParam("com_src")      String com_src
			 	  ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				  ,@RequestParam("dev_to_dt")    String dev_to_dt
				  ,@RequestParam("use_fr_dt")	 String use_fr_dt
				  ,@RequestParam("use_to_dt")    String use_to_dt
				  ,@RequestParam("requester")    String requester
				  ,@RequestParam("owner")	     String owner
				  ,@RequestParam("developer")    String developer
				  ,@RequestParam("participant")  String participant
				  ,@RequestParam("scenario")     String scenario
				   ) {
		return commstMapper.insertParam(
				   com_id
				  ,reg_dt
				  ,reg_id
				  ,upt_dt
				  ,upt_id
				  ,com_nm
				  ,com_cate
				  ,com_attr
				  ,com_form
				  ,com_src
			 	  ,dev_fr_dt
				  ,dev_to_dt
				  ,use_fr_dt
				  ,use_to_dt
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
	public int post(@RequestBody ComMst commst) {
		return commstMapper.insertBody(commst);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ComMst> getAll() {
		return commstMapper.getAll();
	}
	// one
	@GetMapping("/{com_id}")
	public ComMst getByComId(@PathVariable("com_id") String com_id) {
		return commstMapper.getByComId(com_id);
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// Params
//	@PostMapping("/{com_id}")	
	@PutMapping("/{com_id}")  	
	public int put(
				   @PathVariable("com_id") 		 String com_id
				  ,@RequestParam("upt_dt")       String upt_dt
				  ,@RequestParam("upt_id")       String upt_id
				  ,@RequestParam("com_nm")       String com_nm
				  ,@RequestParam("com_cate")     String com_cate
				  ,@RequestParam("com_attr")     String com_attr
				  ,@RequestParam("com_form")	 String com_form
				  ,@RequestParam("com_src")      String com_src
			 	  ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				  ,@RequestParam("dev_to_dt")    String dev_to_dt
				  ,@RequestParam("use_fr_dt")	 String use_fr_dt
				  ,@RequestParam("use_to_dt")    String use_to_dt
				  ,@RequestParam("requester")    String requester
				  ,@RequestParam("owner")	     String owner
				  ,@RequestParam("developer")    String developer
				  ,@RequestParam("participant")  String participant
				  ,@RequestParam("scenario")     String scenario
		   ) {		
		return commstMapper.updateParam(
				  com_id
				  ,upt_dt
				  ,upt_id
				  ,com_nm
				  ,com_cate
				  ,com_attr
				  ,com_form
				  ,com_src
			 	  ,dev_fr_dt
				  ,dev_to_dt
				  ,use_fr_dt
				  ,use_to_dt
				  ,requester
				  ,owner
				  ,developer
				  ,participant
				  ,scenario
		  );				
	}
	
	// Body
	@PostMapping("/{com_id}")	
//	@PutMapping("/{com_id}")  
	public int put(@RequestBody ComMst commst) {
		return commstMapper.updateBody(commst);			
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/{com_id}")	
	public int delete(@PathVariable("com_id") String com_id) {
		return commstMapper.delete(com_id);
	}	
	
}