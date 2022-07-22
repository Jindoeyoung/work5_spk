package com.spk.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; // Body 방식
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
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (get)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PutMapping("")  // 포스트맨에서, 아래의 @PutMapping - PUT 으로 테스트 성공
//	@PostMapping("") // 원래 PostMapping 으로 테스트 했었음. 아래 Body 의 PostMapping 과 공존시키기 위해, 위의 PutPapping 으로 우선 둠 (Postman 테스트 시에도 PUT으로 해야함)
	public int post(@RequestParam("api_id") 	  String api_id
				   ,@RequestParam("reg_dt")       String reg_dt
				   ,@RequestParam("reg_id")       String reg_id
				   ,@RequestParam("upt_dt")       String upt_dt
				   ,@RequestParam("upt_id")       String upt_id
				   ,@RequestParam("api_nm")       String api_nm
				   ,@RequestParam("api_cate")     String api_cate
				   ,@RequestParam("version")      String version
				   ,@RequestParam("param")	      String param
				   ,@RequestParam("res_form")     String res_form
				   ,@RequestParam("rtn_type")     String rtn_type
				   ,@RequestParam("method")       String method
				   ,@RequestParam("url")	      String url
				   ,@RequestParam("proc_state")   String proc_state
				   ,@RequestParam("proc_rate")    String proc_rate
				   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
				   ,@RequestParam("dev_to_dt")    String dev_to_dt
				   ,@RequestParam("requester")    String requester
				   ,@RequestParam("owner")	      String owner
				   ,@RequestParam("developer")    String developer
				   ,@RequestParam("participants") String participants
				   ,@RequestParam("scenario")     String scenario) {
		return apimstMapper.insertParam(
					api_id
					,reg_dt
					,reg_id
					,upt_dt
					,upt_id
					,api_nm
					,api_cate
					,version
					,param
					,res_form
					,rtn_type
					,method
					,url
					,proc_state
					,proc_rate
					,dev_fr_dt
					,dev_to_dt
					,requester
					,owner
					,developer
					,participants
					,scenario
					);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@PostMapping("")
	public int post(@RequestBody ApiMst apimst) {
		return apimstMapper.insertBody(apimst);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@GetMapping("")
	public List<ApiMst> getAll() {
		return apimstMapper.getAll();
	}
	// one
	@GetMapping("/{api_id}")
	public ApiMst getByApiId(@PathVariable("api_id") String api_id) {
		return apimstMapper.getByApiId(api_id);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------
	// get
	@PutMapping("/{api_id}")
	public int put(@PathVariable("api_id") String api_id
		   ,@RequestParam("reg_dt")       String reg_dt
		   ,@RequestParam("reg_id")       String reg_id
		   ,@RequestParam("upt_dt")       String upt_dt
		   ,@RequestParam("upt_id")       String upt_id
		   ,@RequestParam("api_nm")       String api_nm
		   ,@RequestParam("api_cate")     String api_cate
		   ,@RequestParam("version")      String version
		   ,@RequestParam("param")	      String param
		   ,@RequestParam("res_form")     String res_form
		   ,@RequestParam("rtn_type")     String rtn_type
		   ,@RequestParam("method")       String method
		   ,@RequestParam("url")	      String url
		   ,@RequestParam("proc_state")   String proc_state
		   ,@RequestParam("proc_rate")    String proc_rate
		   ,@RequestParam("dev_fr_dt")    String dev_fr_dt
		   ,@RequestParam("dev_to_dt")    String dev_to_dt
		   ,@RequestParam("requester")    String requester
		   ,@RequestParam("owner")	      String owner
		   ,@RequestParam("developer")    String developer
		   ,@RequestParam("participants") String participants
		   ,@RequestParam("scenario")     String scenario) {		
		return apimstMapper.updateParam(
				api_id
				,reg_dt
				,reg_id
				,upt_dt
				,upt_id
				,api_nm
				,api_cate
				,version
				,param
				,res_form
				,rtn_type
				,method
				,url
				,proc_state
				,proc_rate
				,dev_fr_dt
				,dev_to_dt
				,requester
				,owner
				,developer
				,participants
				,scenario
				);				
	}
	
	// Body
//	@PutMapping("/{api_id}")   // PutMapping 으로 포스트맨에서 테스트 성공  
	@PostMapping("/{api_id}")  // 원래 PutMapping 으로 테스트 했었음. 위 get PutMapping 과 공존시키기 위해, PostMapping 으로 우선 둠 (Postman 테스트 시에도 POST 으로 해야함)
	public int put(@RequestBody ApiMst apimst) {
		return apimstMapper.updateBody(apimst);			
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/{api_id}")	
	public int delete(@PathVariable("api_id") String api_id) {
		return apimstMapper.delete(api_id);
	}
	
}