package com.spk.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spk.api.entity.ApiMst;
import com.spk.api.entity.PjtCom;
import com.spk.api.mapper.PjtComMapper;
import com.google.gson.JsonArray;
import org.apache.commons.lang3.StringUtils;

@RestController
//@RequestMapping("/pjt")
@RequestMapping(value = "/pjt", produces = "application/json; charset=utf8")
public class PjtComController {

	@Autowired 
	private PjtComMapper pjtcomMapper;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
//	@GetMapping("")	
	@PostMapping("")	
	public String getAll() {

		JsonObject dataResult = new JsonObject();
//		JsonObject jsonObj1 = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		
		
		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();		
		
		for (PjtCom item : datas) {
//		System.out.println("item==>"+item);
//		System.out.println("item.getCom_id()==>"+item.getCom_id());
//		
//		
//			String com_id = null;  
//			com_id = item.getCom_id();
		
			JsonObject jsonObj1 = new JsonObject();
//			JsonArray jsonArr1 = new JsonArray();				
			
			jsonObj1.addProperty("com_id", item.getCom_id());
			jsonObj1.addProperty("com_nm", item.getCom_nm());
			jsonObj1.addProperty("dev_fr_dt", item.getDev_fr_dt());
			jsonObj1.addProperty("dev_to_dt", item.getDev_to_dt());
			jsonObj1.addProperty("requester", item.getRequester());
			jsonObj1.addProperty("owner", item.getOwner());
			jsonObj1.addProperty("participant", item.getParticipant());
			jsonObj1.addProperty("scenario", item.getScenario());
			jsonObj1.addProperty("scen_cnt", item.getScen_cnt());		
			jsonArr1.add(jsonObj1);		
			
			dataResult.add("data", jsonArr1);
			
			
		}		
		
//		try {
//			result = utils.getMultiGenerator(apimstMapper.getAll().toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
				
		return dataResult.toString();		
		
	}
	
	
//	// list
////	@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public List<PjtCom> getAll() {
//		return pjtcomMapper.getAll();
//	}
	
//	// 잘되는 버전
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public List<PjtCom> getAll() {
//		System.out.println("@@@=>"+pjtcomMapper.getAll());
//		return pjtcomMapper.getAll();
//	}
		
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll() {
//		System.out.println("111=>"+pjtcomMapper.getAll());
//		System.out.println("222=>"+pjtcomMapper.getAll().toString());
//		
//		String oring_str = pjtcomMapper.getAll().toString();
//		String first_repl = oring_str.substring(1);
//		String second_repl = StringUtils.removeEnd(first_repl, "]"); 
//		
//		System.out.println("333=>"+second_repl);
//		
//		return second_repl;
////		return pjtcomMapper.getAll().toString();
//	}	
	
	
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll() {
//		System.out.println("111=>"+pjtcomMapper.getAll());
//		System.out.println("222=>"+pjtcomMapper.getAll().toString());
//
//		
//		<PjtCom> datas = pjtcomMapper.getAll();
//		
//		return second_repl;
////		return pjtcomMapper.getAll().toString();
//	}	
	
	
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public PjtCom getAll() {
//		System.out.println("111=>"+pjtcomMapper.getAll());
//		System.out.println("222=>"+pjtcomMapper.getAll().toString());
//		
//		String oring_str = pjtcomMapper.getAll().toString();
//		String first_repl = oring_str.substring(1);
//		String second_repl = StringUtils.removeEnd(first_repl, "]"); 
//		
//		System.out.println("333=>"+second_repl);
//		
//		return <PjtCom> second_repl;
////		return pjtcomMapper.getAll().toString();
//	}	
	
	
	
	
	

//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public List<PjtCom> getAll() {
//		System.out.println("@@@=>"+pjtcomMapper.getAll());
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
//		for (PjtCom item : datas) {
//			System.out.println("item==>"+item);
//		}
//		
//		return pjtcomMapper.getAll();
//	}	
//	// 위 COPY
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public List<PjtCom> getAll() {
//		System.out.println("@@@=>"+pjtcomMapper.getAll());
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
//		for (PjtCom item : datas) {
//			System.out.println("item==>"+item);
//			
//			
//			String ab = item.getCom_id();
//		}
//		
//		return pjtcomMapper.getAll();
//	}	
	
	
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public List<PjtCom> getAll() {
//
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
////		String arrayList = new JsonArray();		
//		String arrayList = null;
//		Gson gson = new Gson();
//		
//		for (PjtCom item : datas) {
//			System.out.println("item==>"+item);
//			
//			//arrayList.add(getJsonStringFromMap(item));
//			
//			arrayList = gson.toJson(item, JsonArray.class);
//		}
//		
//		return pjtcomMapper.getAll();
//	}	
	
	
	
	
	
	// HERE
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll() {
//
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
////		String arrayList = new JsonArray();		
//		String arrayList = null;
//		Gson gson = new Gson();
//		
//		for (PjtCom item : datas) {
//			System.out.println("item==>"+item);
//			
//			//arrayList.add(getJsonStringFromMap(item));
//			
//			arrayList = gson.toJson(item, JsonArray.class);
//		}
//		
//		return arrayList.toString();
//	}		
	
	
	
	
	
	
	
	
	
//	// list
////	@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll2() {
//		String listAll = new Gson().toJson(pjtcomMapper.getAll());
//		return listAll;
//	}	
	
	
//	// list
////	@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public JsonObject getAll3() {
//		Gson gson = new Gson();
//		JsonObject result = (JsonObject) gson.toJsonTree(pjtcomMapper.getAll()); 
//		return result;
//	}	

	
//	// list
////	@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll3() {
//		JsonArray json_array = new JsonArray();
//		return json_array.toString();
//	}		
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll3() {
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
//		System.out.println("@datas=>"+datas);
//		
////		String[] datas2 = (String[]) datas.toArray();
////		
////		System.out.println("@datas=>"+datas2);
//		
////		for (int i = 0; i < datas.size(); i++) {
////		
////			System.out.println("@@datas=>"+datas[i]);
////		}	
//			
////		JsonArray arrayList = new JsonArray();
////		Gson gson = new Gson();
////		arrayList = gson.fromJson(pjtcomMapper.getAll().toString(),  <PjtCom> );
//		return datas.toString();
//	}	
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public String getAll3() {
//		
//		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
//		
//		JsonArray json_array = new JsonArray();
//		
//		System.out.println("@datas=>"+datas);
//		
//		
//		for (int i = 0; i < datas.size(); i++) {		
//		
//			JsonObject dataResult = new JsonObject();
//			dataResult.add("com_id", datas.get(i));
//		
//		
////		String[] datas2 = (String[]) datas.toArray();
////		
////		System.out.println("@datas=>"+datas2);
//		
////		for (int i = 0; i < datas.size(); i++) {
////		
////			System.out.println("@@datas=>"+datas[i]);
////		}	
//			
////		JsonArray arrayList = new JsonArray();
////		Gson gson = new Gson();
////		arrayList = gson.fromJson(pjtcomMapper.getAll().toString(),  <PjtCom> );
//		return datas.toString();
//	}	
	
	
//	// list
//	//@GetMapping("")
//	@GetMapping(value = "", produces = "application/json; charset=utf8")	
//	public JsonObject getAll3() {
//		
//		JsonArray json_array = new JsonArray();		
//		
//		List<PjtCom>[] list;
//		for(List<PjtCom> list: list) {
//			json_array.add(convertMapToJson());
//		}
//		
////		List<PjtCom> datas = (List<PjtCom>) pjtcomMapper.getAll();
////		System.out.println("@datas=>"+datas);
//		
////		String[] datas2 = (String[]) datas.toArray();
////		
////		System.out.println("@datas=>"+datas2);
//		
////		for (int i = 0; i < datas.size(); i++) {
////		
////			System.out.println("@@datas=>"+datas[i]);
////		}	
//			
////		JsonArray arrayList = new JsonArray();
////		Gson gson = new Gson();
////		arrayList = gson.fromJson(pjtcomMapper.getAll().toString(),  <PjtCom> );
//		return datas.toString();
//	}	
	
	
//	public static String getJsonStringFromList(Map<String, Object> map2) {
//
//		JsonArray arrayList = new JsonArray();
//
//		for (Map<String, Object> map : map2) {
//			JsonArray jsonArray = new JsonArray();
//			jsonArray.add(getJsonStringFromList(map));
//		}
//
//		return arrayList.toString();
//	}	
	
	
}
