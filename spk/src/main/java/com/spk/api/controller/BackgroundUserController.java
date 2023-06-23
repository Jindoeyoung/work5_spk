package com.spk.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.BackgroundUser;
import com.spk.api.mapper.BackgroundUserMapper;
import com.spk.api.security.AuthCheck;
import com.spk.api.service.BackgroundUserService;
import com.spk.api.service.ComMstService;

@RestController
@RequestMapping(value = "/background", produces = "application/json; charset=utf8")
public class BackgroundUserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BackgroundUserMapper backgroundUserMapper;
	
	@Autowired
	private BackgroundUserService backgroundUserService;	
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// update
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@PutMapping("/")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int put(@RequestBody BackgroundUser backgroundUser) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(backgroundUser.getApikey()).equals("{}")) {
			logger.info("[BackgroundUserController][update] AUTHENTICATION RESTRICTIONS");
		} else {
			return backgroundUserService.insertBody(backgroundUser);
//			return backgroundUserMapper.updateBody(backgroundUser);
		}
		return 0;		
	}
	
}
