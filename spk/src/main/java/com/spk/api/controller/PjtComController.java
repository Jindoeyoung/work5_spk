package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.PjtCom;
import com.spk.api.mapper.PjtComMapper;

@RestController
@RequestMapping("/pjt")
public class PjtComController {

	@Autowired 
	private PjtComMapper pjtcomMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<PjtCom> getAll() {
		return pjtcomMapper.getAll();
	}	
}
