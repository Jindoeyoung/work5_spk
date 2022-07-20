package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("")
	public int post(@RequestBody ApiMst apimst) {
		return apimstMapper.insert(apimst);
	}
	
	@GetMapping("")
	public List<ApiMst> getAll() {
		return apimstMapper.getAll();
	}
	
	@GetMapping("/{api_id}")
	public ApiMst getByApiId(@PathVariable("api_id") String api_id) {
		return apimstMapper.getByApiId(api_id);
	}
	
	// get방식
	@PutMapping("/{api_id}")  // good
	public int put(@PathVariable("api_id") String api_id, @RequestParam("api_nm") String api_nm, @RequestParam("param") String param) {
		return apimstMapper.update(api_id, api_nm, param);
		
//	// Body 방식
//	@PutMapping("/{api_id}")  // good
//	public int put(@RequestBody ApiMst apimst) { // good
//		return apimstMapper.update(apimst);  // good			
		
		
	}
	
	
}
