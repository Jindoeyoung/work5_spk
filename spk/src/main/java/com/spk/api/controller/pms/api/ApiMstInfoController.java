package com.spk.api.controller.pms.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.spk.api.entity.pms.api.ApiMst;
import com.spk.api.entity.pms.api.ApiMstList;
import com.spk.api.service.pms.api.ApiMstInfoService;
import com.spk.api.util.ReturnException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys", produces = "application/json; charset=utf8")
public class ApiMstInfoController {
	private final ApiMstInfoService apiMstService;

    //============================================================
    //< INSERT - 시스템메뉴정보 저장
    //============================================================	
	@PostMapping("/insertApiMst")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String insertApiMst(@RequestBody ApiMstList apiMstList) throws Exception {
		try{
		return apiMstService.insertApiMst(apiMstList);
		} catch (ReturnException e) {
			return (String)e.getValue();
		}	
	}
}
