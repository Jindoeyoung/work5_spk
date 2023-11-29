package com.spk.api.controller.sys;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.sys.VMatrix;
import com.spk.api.service.sys.VMatrixService;

//import com.spk.api.entity.colele.RequestTablesEntity;
//import com.spk.api.service.colele.ColumnElementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/matrix", produces = "application/json; charset=utf8")
public class VMatrixController {
	private final VMatrixService vMatrixService;

    //============================================================
    //< SELECT - 시스템메뉴사용자 리스트 조회
    //============================================================	
	@PostMapping("")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMatrixList(@RequestBody VMatrix vMatrix) throws Exception {
		return vMatrixService.getMatrixList(vMatrix);
	}
	
    //============================================================
    //< SELECT - Matrix : UserDetails 조회(mysql)-저장(redis) 처리  
    //============================================================	
	@PostMapping("/details")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMatrixListDetails(@RequestBody VMatrix vMatrix) throws Exception {
		return vMatrixService.getMatrixListDetails(vMatrix);
	}	
}
