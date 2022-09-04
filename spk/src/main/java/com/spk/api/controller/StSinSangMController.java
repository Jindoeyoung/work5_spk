package com.spk.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.StSinSangM;
import com.spk.api.mapper.StSinSangMMapper;

@RestController
@RequestMapping(value = "/ss", produces = "application/json; charset=utf8")
public class StSinSangMController {
	
	@Autowired
	private StSinSangMMapper stsinsangmmapper;

	// one
	@PostMapping("/dtl")
	public String getByHakbeon(@RequestBody StSinSangM _sinsangm) {
		
		StSinSangM sinsangm = stsinsangmmapper.getByHakbeon(_sinsangm.getHakbeon());
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		
		Obj1.addProperty("hakbeon",         sinsangm.getHakbeon());
		Obj1.addProperty("curr_hakgwa",	    sinsangm.getCurr_hakgwa());
		Obj1.addProperty("curr_haknyeon",   sinsangm.getCurr_haknyeon());
		Obj1.addProperty("curr_juya",	    sinsangm.getCurr_juya());
		Obj1.addProperty("curr_ban",	    sinsangm.getCurr_ban());
		Obj1.addProperty("curr_hakgi",	    sinsangm.getCurr_hakgi());
		Obj1.addProperty("jido_gyosu_1_1",  sinsangm.getJido_gyosu_1_1());
		Obj1.addProperty("jido_gyosu_1_2",  sinsangm.getJido_gyosu_1_2());
		Obj1.addProperty("jido_gyosu_2_1",  sinsangm.getJido_gyosu_2_1());
		Obj1.addProperty("jido_gyosu_2_2",  sinsangm.getJido_gyosu_2_2());
		Obj1.addProperty("jido_gyosu_3_1",  sinsangm.getJido_gyosu_3_1());
		Obj1.addProperty("jido_gyosu_3_2",  sinsangm.getJido_gyosu_3_2());
		Obj1.addProperty("sinjang",	    	sinsangm.getSinjang());
		Obj1.addProperty("chejung",	    	sinsangm.getChejung());
		Obj1.addProperty("blood_gb",	    sinsangm.getBlood_gb());
		Obj1.addProperty("l_siryeok",	    sinsangm.getL_siryeok());
		Obj1.addProperty("r_siryeok",	    sinsangm.getR_siryeok());
		Obj1.addProperty("jonggyo",	    	sinsangm.getJonggyo());
		Obj1.addProperty("teukgi",	    	sinsangm.getTeukgi());
		Obj1.addProperty("chwimi",	    	sinsangm.getChwimi());
		Obj1.addProperty("byeolmyeong",	    sinsangm.getByeolmyeong());
		Obj1.addProperty("sukso",	    	sinsangm.getSukso());
		Obj1.addProperty("tonghak",	    	sinsangm.getTonghak());
		Obj1.addProperty("seonggyeok",	    sinsangm.getSeonggyeok());
		Obj1.addProperty("jangaekind",	    sinsangm.getJangaekind());
		Obj1.addProperty("jangaedeunggeup", sinsangm.getJangaedeunggeup());
		Obj1.addProperty("eng_hwalyong",    sinsangm.getEng_hwalyong());
		Obj1.addProperty("toeik_jumsu",	    sinsangm.getToeik_jumsu());
		Obj1.addProperty("jagayong_yn",	    sinsangm.getJagayong_yn());
		Obj1.addProperty("dongar",	    	sinsangm.getDongari());
		Obj1.addProperty("danche",	    	sinsangm.getDanche_name());
		Obj1.addProperty("jinro",	    	sinsangm.getJinro());
		Obj1.addProperty("huimang_college", sinsangm.getHuimang_college());
		Obj1.addProperty("huimang_hakgwa",  sinsangm.getHuimang_hakgwa());
		Obj1.addProperty("tel_no",	    	sinsangm.getTel_no());
		Obj1.addProperty("hp_no",	    	sinsangm.getHp_no());
		Obj1.addProperty("email",	    	sinsangm.getEmail());
		Obj1.addProperty("birthday",	    sinsangm.getBirthday());
		Obj1.addProperty("eumyang_gb",	    sinsangm.getEumyang_gb());
		Obj1.addProperty("sex",		    	sinsangm.getSex());
		
		jsonArr1.add(Obj1);		
		
		Obj2.add("result", jsonArr1);
		dataResult.add("data", Obj2);		
				
		return dataResult.toString();
	}	
	
}
