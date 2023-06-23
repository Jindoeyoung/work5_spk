package com.spk.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spk.api.entity.BackgroundUser;
import com.spk.api.mapper.BackgroundUserHstMapper;
import com.spk.api.mapper.BackgroundUserMapper;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.spk.api.entity.ComApiRel;
//import com.spk.api.entity.ComApiRel2;
//import com.spk.api.entity.ComMst;
//import com.spk.api.mapper.ComApiRelMapper;
//import com.spk.api.mapper.ComMstMapper;

@Service
public class BackgroundUserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private BackgroundUserMapper backgroundUserMapper;
	
	@Autowired 
	private BackgroundUserHstMapper backgroundUserHstMapper;
	
//	@PostMapping("/ins")
	@Transactional
	public int insertBody(@RequestBody BackgroundUser backgroundUser) {
		
		int rslt1 = 0;
		
		rslt1 = backgroundUserMapper.updateBody(backgroundUser);
		logger.info("rslt1===@@@===>"+rslt1);
		
		int rslt2 = 0;
			
		rslt2 = backgroundUserHstMapper.insertBody(backgroundUser);
		logger.info("rslt2===@@@===>"+rslt2);
		
		if (rslt1 < 1 || rslt2 < 1) {
			throw new RuntimeException("COM_MST Exception 2...");
		}
		
		return rslt1;
	}
	
}
