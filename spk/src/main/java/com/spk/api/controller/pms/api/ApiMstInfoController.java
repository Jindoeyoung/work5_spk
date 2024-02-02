package com.spk.api.controller.pms.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spk.api.entity.pms.api.ApiMst;
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
    //< SELECT - API마스터 리스트 조회
    //============================================================	
	@PostMapping("/getApiMstList")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getMenuInfoList(@RequestBody ApiMst pApiMst) throws Exception {
		return apiMstService.getApiMstList(pApiMst);
	}
	
    //============================================================
    //< INSERT - API마스터 저장
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

    //============================================================
    //< INSERT - API마스터 삭제(PARAM, MATRIX 동시 삭제함)
    //============================================================	
	@DeleteMapping("/deleteApiMst_delete")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String deleteApiMst(@RequestBody ApiMstList apiMstList) throws Exception {
		try{
		return apiMstService.deleteApiMst(apiMstList);
		} catch (ReturnException e) {
			return (String)e.getValue();
		}	
	}
	
    //============================================================
    //< INSERT - API마스터 삭제(PARAM, MATRIX 동시 삭제함)
    //============================================================	
	@GetMapping("/deleteApiMst/{if_ids}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String deleteApiMst(@PathVariable String if_ids) throws Exception {	
		try{
			return apiMstService.deleteApiMstGet(if_ids);
//			return apiMstService.deleteApiMstGet(if_id1, if_id2);
		} catch (ReturnException e) {
			return (String)e.getValue();
		}	
	}	
	
//    //============================================================
//    //< INSERT - API마스터 삭제(PARAM, MATRIX 동시 삭제함)
//    //============================================================	
////	@GetMapping("/deleteApiMstGet/if_id1={if_id1}&if_id2={if_id2}")
////	@GetMapping("/deleteApiMst/{if_id1}/{if_id2}")
////	@GetMapping("/deleteApiMst/if_id1=if_id1&if_id2=if_id2")	
//	@GetMapping("/deleteApiMst/if_id1={if_id1}&if_id2={if_id2}")
//	@CrossOrigin(origins = "*", allowedHeaders = "*")
//	public String deleteApiMst(@PathVariable String if_id1, @PathVariable String if_id2) throws Exception {	
////	public String deleteApiMst(@RequestParam("if_id1") String if_id1, @RequestParam("if_id2") String if_id2) throws Exception {
//		try{
//			return apiMstService.deleteApiMstGet(if_id1, if_id2);
//		} catch (ReturnException e) {
//			return (String)e.getValue();
//		}	
//	}	
	
}
