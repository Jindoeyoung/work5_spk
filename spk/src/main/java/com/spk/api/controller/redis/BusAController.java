package com.spk.api.controller.redis;

import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
import com.spk.api.entity.redis.BusA;

//import com.spk.api.entity.FlagInfo;
//import com.spk.api.entity.FlagInfoList;
//import com.spk.api.entity.FlagInfoR;
//import com.spk.api.entity.FlagInfoRList;
import com.spk.api.service.redis.BusAService;

//import com.spk.api.service.FlagInfoService;
//import com.spk.api.service.FlagInfoRService;

@RestController
@RequestMapping(value = "/bus-avail", produces = "application/json; charset=utf8")
public class BusAController<RegisterUserRequest> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private BusAService busAService;
	
//	@Autowired
//    private FlagInfoRService flagInfoRService;
//	
//	@Autowired
//    private FlagInfoService flagInfoService;	

    public void RedisSampleController(BusAService busAService) {
        this.busAService = busAService;
    }

    // 전체 User 리스트 조회
    @PostMapping({"/user-list2"})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> getAllUsers() {
        List<String> users = busAService.getUsernameList();
        logger.info("[avail-user] new ResponseEntity<>(ImmutableMap.of(\"users\", users), HttpStatus.OK) : "+new ResponseEntity<>(ImmutableMap.of("users", users), HttpStatus.OK));
        return new ResponseEntity<>(ImmutableMap.of("users", users), HttpStatus.OK);
    }

    // User-BusAvail 플래그 정보 상세조회
    @PostMapping({"/"})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> getUser(
    	@RequestBody BusA request    		
    ) throws IOException {

    	BusA busA = busAService.getUser(request.getUser_id());

        if (busA == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        logger.info("[avail-list] request.getUser_id()==>"+request.getUser_id());
        logger.info("[avail-list] new ResponseEntity<>(busA, HttpStatus.OK) : "+new ResponseEntity<>(busA, HttpStatus.OK));
        
        return new ResponseEntity<>(busA, HttpStatus.OK);
    }

    // User-BusAvail 플래그정보 저장     
    @PostMapping({"/insert"})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> registerUser(
        @RequestBody BusA request
    ) throws Exception {
    	BusA busA = null;
    	busA = busAService.registerUser(request.getUser_id(), request.getFlag_info());

    	logger.info("[avail-insert] request.getUser_id()==>"+request.getUser_id());
    	logger.info("[avail-insert] request.getFlag_info()==>"+request.getFlag_info());
    	logger.info("new ResponseEntity<>(busA, HttpStatus.OK) : "+new ResponseEntity<>(busA, HttpStatus.OK));    	
    	
		return new ResponseEntity<>(busA, HttpStatus.OK);
    }

    
    // User-BusAvail 플래그정보 수정    
    @PutMapping({"/"})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> updateUser(
        @RequestBody BusA request
    ) throws Exception {
    	BusA busA = busAService.registerUser(request.getUser_id(), request.getFlag_info());

    	logger.info("[avail-update] request.getUser_id()==>"+request.getUser_id());
    	logger.info("[avail-update] request.getFlag_info()==>"+request.getFlag_info());
    	logger.info("new ResponseEntity<>(busA, HttpStatus.OK) : "+new ResponseEntity<>(busA, HttpStatus.OK));    	
    	
        return new ResponseEntity<>(busA, HttpStatus.OK);
    }    
    
    // User-BusAvail 플래그정보 삭제
    @DeleteMapping({"/"})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> deleteUser(
    	@RequestBody BusA request
    ) {

        busAService.deleteUser(request.getUser_id());

        logger.info("[avail-delete] request.getUser_id()==>"+request.getUser_id());
        logger.info("[avail-delete] new ResponseEntity<>(HttpStatus.NO_CONTENT) : "+new ResponseEntity<>(HttpStatus.NO_CONTENT));        
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }    
}