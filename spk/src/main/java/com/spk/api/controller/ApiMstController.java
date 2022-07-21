package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody; // Body 방식
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.ApiMst;
import com.spk.api.mapper.ApiMstMapper;

@RestController
@RequestMapping("/api")
public class ApiMstController {
	
	@Autowired 
	private ApiMstMapper apimstMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// get
	@PostMapping("")
	public int post(@RequestParam("api_id") String api_id, @RequestParam("api_nm") String api_nm, @RequestParam("param") String param) {
		return apimstMapper.insert(api_id, api_nm, param);
	}
//	// Body
//	@PostMapping("")
//	public int post(@RequestBody ApiMst apimst) {
//		return apimstMapper.insert(apimst);
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ApiMst> getAll() {
		return apimstMapper.getAll();
	}
	// one
	@GetMapping("/{api_id}")
	public ApiMst getByApiId(@PathVariable("api_id") String api_id) {
		return apimstMapper.getByApiId(api_id);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// get
	@PutMapping("/{api_id}")
	public int put(@PathVariable("api_id") String api_id, @RequestParam("api_nm") String api_nm, @RequestParam("param") String param) {
		return apimstMapper.update(api_id, api_nm, param);
	}
//	// Body
//	@PutMapping("/{api_id}")
//	public int put(@RequestBody ApiMst apimst) {
//		return apimstMapper.update(apimst);			
//	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/{api_id}")	
	public int delete(@PathVariable("api_id") String api_id) {
		return apimstMapper.delete(api_id);
	}
	
}