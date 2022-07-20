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
	
//	@PostMapping("/{api_id}")	
	@PutMapping("/{api_id}")  // good
//	@PutMapping("")
//	public int put(@RequestBody ApiMst apimst) { // good
	public int put(@PathVariable("api_id") String api_id, @RequestParam("api_nm") String api_nm, @RequestParam("param") String param) {
//	public int put(@PathVariable("api_nm") String api_nm, @PathVariable("param") String param) {
		
//		ApiMst apimst = apimstMapper.getByApiId(api_id);
//		apimst.setApi_nm(api_nm);
//		apimst.setParam(param);
		
		
//		return apimstMapper.update(apimst);
//		return apimstMapper.update(api_nm, param);
		return apimstMapper.update(api_id, api_nm, param);
//		return apimstMapper.update(apimst);  // good
		
	}
	
	
}
