package com.spk.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.ComApiRel;
import com.spk.api.entity.ComApiRel2;
import com.spk.api.entity.ComMst;
import com.spk.api.mapper.ComApiRelMapper;
import com.spk.api.mapper.ComMstMapper;

@Service
public class ComMstService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private ComMstMapper commstMapper;
	
	@Autowired 
	private ComApiRelMapper comapirelMapper;
	
//	@PostMapping("/ins")
	@Transactional
	public int insertBody(@RequestBody ComMst commst) {
		
		int rslt1 = commstMapper.insertBody(commst);
		System.out.println("rslt1===@@@===>"+rslt1);
		int rslt2 = 0;
		
        //============================================================
        //< (2) COM_API_REL 분리
        //============================================================
		List<ComApiRel> datas = commst.getApi_src();
		
//		JsonArray jsonArr = new JsonArray();
		for (ComApiRel item : datas) {
			
			
			
			rslt2 = comapirelMapper.insertBody(item);
			
			logger.info("item===>"+item);
			System.out.println("rslt2===@@@===>"+rslt2);
			
			if (rslt1 < 1 || rslt2 < 1) {
//				if (rslt1 < 1) {
					throw new RuntimeException("COM_MST Exception");
				}			
			
			
//			logger.info("src===>"+item2.getApi_src());
//			JsonObject Obj = new JsonObject();
//			Obj.addProperty("api_src", item.getApi_src());
//			jsonArr.add(Obj);
		}
//		Obj1.add("api_src", jsonArr);	
		
		
		
		
		
		
		
//		int rslt2 = comapirelMapper.insertBody(comapirel);
		
		if (rslt1 < 1 || rslt2 < 1) {
//		if (rslt1 < 1) {
			throw new RuntimeException("COM_MST Exception 2...");
		}
		
		return rslt1;
	}
	
}
