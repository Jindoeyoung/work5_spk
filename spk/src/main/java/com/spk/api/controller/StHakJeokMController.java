package com.spk.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.StHakJeokM;
import com.spk.api.entity.VHakJeokSeongJeok;
import com.spk.api.mapper.StHakJeokMMapper;
import com.spk.api.security.AuthCheck;

@RestController
@RequestMapping(value = "/hj", produces = "application/json; charset=utf8")
public class StHakJeokMController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StHakJeokMMapper sthakjeokmmapper;
	
	AuthCheck authcheck = new AuthCheck();
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/ins")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int post(@RequestBody StHakJeokM hakjeokm) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(hakjeokm.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][insert] AUTHENTICATION RESTRICTIONS");
		} else {		
			return sthakjeokmmapper.insertBody(hakjeokm);
		}
		return 0;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/student-list")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getStudentList(@RequestBody StHakJeokM _hakjeokm) throws Exception  {

		if (!authcheck.getMetaAuthErrGenerator(_hakjeokm.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][getStudentList] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_hakjeokm.getApikey());		
		}
		
		List<StHakJeokM> datas = (List<StHakJeokM>) sthakjeokmmapper.getSudentList(_hakjeokm);				
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();		
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");		

		if (datas.size() > 0) {
		
			for (StHakJeokM item : datas) {
//			System.out.println("item==>"+item);
			
				JsonObject Obj1 = new JsonObject();
				JsonObject Obj2 = new JsonObject();
			
				Obj1.addProperty("hakbeon", item.getHakbeon());
				Obj1.addProperty("haknyeon", item.getHaknyeon());	
				Obj1.addProperty("h_name", item.getH_name());
				Obj1.addProperty("hakgwa", item.getHakgwa());
				Obj1.addProperty("ban", item.getBan());
				Obj1.addProperty("sex", item.getSex());
				Obj1.addProperty("sangtae", item.getSangtae());
				Obj1.addProperty("bigo", item.getBigo());
				
				jsonArr1.add(Obj1);		
				
				Obj2.add("result", jsonArr1);
				dataResult.add("data", Obj2);				
			}
			
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getStudentList=>"+dataResult.toString());	
		return dataResult.toString();
	}	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - ONE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/student-info")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getByHakbeon(@RequestBody StHakJeokM _hakjeokm) throws Exception {
		
		if (!authcheck.getMetaAuthErrGenerator(_hakjeokm.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][getByHakbeon] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_hakjeokm.getApikey());
		}		

		StHakJeokM hakjeokm = sthakjeokmmapper.getByHakbeon(_hakjeokm.getHakbeon());

		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();

		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			

		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();

		if (hakjeokm != null) {
			Obj1.addProperty("hakbeon", hakjeokm.getHakbeon());
			Obj1.addProperty("profile", hakjeokm.getProfile());
			Obj1.addProperty("jumin_no", hakjeokm.getJumin_no());
			Obj1.addProperty("birthday", hakjeokm.getBirthday());
			Obj1.addProperty("h_name", hakjeokm.getH_name());
			Obj1.addProperty("c_nane", hakjeokm.getC_nane());
			Obj1.addProperty("gukga", hakjeokm.getGukga());
			Obj1.addProperty("e_name_last", hakjeokm.getE_name_last());
			Obj1.addProperty("e_name", hakjeokm.getE_name());
			Obj1.addProperty("sangtae", hakjeokm.getSangtae());
			Obj1.addProperty("byeondongsayu", hakjeokm.getByeondongsayu());
			Obj1.addProperty("byeondong_ilja", hakjeokm.getByeondong_ilja());
			Obj1.addProperty("gwajeong_gb", hakjeokm.getGwajeong_gb());
			Obj1.addProperty("juya_gb", hakjeokm.getJuya_gb());
			Obj1.addProperty("hakgwa", hakjeokm.getHakgwa());
			Obj1.addProperty("jeongong", hakjeokm.getJeongong());
			Obj1.addProperty("haknyeon", hakjeokm.getHaknyeon());
			Obj1.addProperty("ban", hakjeokm.getBan());
			Obj1.addProperty("hakgi", hakjeokm.getHakgi());
			Obj1.addProperty("isuhakgi", hakjeokm.getIsuhakgi());
			Obj1.addProperty("iphak_hakgwa", hakjeokm.getIphak_hakgwa());
			Obj1.addProperty("jeonhyeong_gb", hakjeokm.getJeonhyeong_gb());
			Obj1.addProperty("wetak_company", hakjeokm.getWetak_company());
			Obj1.addProperty("iphak_ilja", hakjeokm.getIphak_ilja());
			Obj1.addProperty("joleop_haknyeon", hakjeokm.getJoleop_haknyeon());
			Obj1.addProperty("joleop_hakgwa", hakjeokm.getJoleop_hakgwa());
			Obj1.addProperty("iphak_juya", hakjeokm.getIphak_juya());
			Obj1.addProperty("iphak_gb", hakjeokm.getIphak_gb());
			Obj1.addProperty("byunguk_gb", hakjeokm.getByunguk_gb());
			Obj1.addProperty("joleop_jeungseo_no", hakjeokm.getJoleop_jeungseo_no());
			Obj1.addProperty("hakwi_no", hakjeokm.getHakwi_no());
			Obj1.addProperty("bigo", hakjeokm.getBigo());
			
			jsonArr1.add(Obj1);		
			
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);		
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getByHakbeon=>"+dataResult.toString());		
		return dataResult.toString();
	}	
	
	
//	// Body
//	@PutMapping("/upt")
//	@CrossOrigin(origins = "*", allowedHeaders = "*")
//	public int put(@RequestBody StHakJeokM hakjeokm) {
//		return sthakjeokmmapper.updateBody(hakjeokm);			
//	}
	
	
	// Body
	@PutMapping("/upt")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int put(@RequestBody StHakJeokM hakjeokm) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(hakjeokm.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][update] AUTHENTICATION RESTRICTIONS");
		} else {
			return sthakjeokmmapper.updateBody(hakjeokm);
		}
		return 0;		
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@DeleteMapping("/del")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public int delete(@RequestBody StHakJeokM hakjeokm) throws Exception {
		if (!authcheck.getMetaAuthErrGenerator(hakjeokm.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][delete] AUTHENTICATION RESTRICTIONS");
		} else {
			return sthakjeokmmapper.delete(hakjeokm);
		}
		return 0;
	}	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT 칼럼 헤더명
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/hakjeok-column-name")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getKorColumnNameHakjeok(@RequestBody VHakJeokSeongJeok _vHakjeokSeongjeok) throws Exception {
		
		if (!authcheck.getMetaAuthErrGenerator(_vHakjeokSeongjeok.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][getByHakbeon] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_vHakjeokSeongjeok.getApikey());
		}		

		VHakJeokSeongJeok vHakjeokSeongjeok = sthakjeokmmapper.getKorColumnName(_vHakjeokSeongjeok.getHakbeon());

		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();

		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			

		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();

		if (vHakjeokSeongjeok != null) {
				Obj1.addProperty("hakbeon", 			vHakjeokSeongjeok.getHakbeon());
				Obj1.addProperty("profile", 			vHakjeokSeongjeok.getProfile());
				Obj1.addProperty("jumin_no", 			vHakjeokSeongjeok.getJumin_no());
				Obj1.addProperty("birthday", 			vHakjeokSeongjeok.getBirthday());
				Obj1.addProperty("h_name", 				vHakjeokSeongjeok.getH_name());
				Obj1.addProperty("c_nane", 				vHakjeokSeongjeok.getC_nane());
				Obj1.addProperty("gukga", 				vHakjeokSeongjeok.getGukga());
				Obj1.addProperty("e_name_last", 		vHakjeokSeongjeok.getE_name_last());
				Obj1.addProperty("e_name", 				vHakjeokSeongjeok.getE_name());
				Obj1.addProperty("sangtae", 			vHakjeokSeongjeok.getSangtae());
				Obj1.addProperty("byeondongsayu", 		vHakjeokSeongjeok.getByeondongsayu());
				Obj1.addProperty("byeondong_ilja", 		vHakjeokSeongjeok.getByeondong_ilja());
				Obj1.addProperty("gwajeong_gb", 		vHakjeokSeongjeok.getGwajeong_gb());
				Obj1.addProperty("juya_gb", 			vHakjeokSeongjeok.getJuya_gb());
				Obj1.addProperty("hakgwa", 				vHakjeokSeongjeok.getHakgwa());
				Obj1.addProperty("jeongong", 			vHakjeokSeongjeok.getJeongong());
				Obj1.addProperty("haknyeon", 			vHakjeokSeongjeok.getHaknyeon());
				Obj1.addProperty("ban", 				vHakjeokSeongjeok.getBan());
				Obj1.addProperty("hakgi", 				vHakjeokSeongjeok.getHakgi());
				Obj1.addProperty("isuhakgi", 			vHakjeokSeongjeok.getIsuhakgi());
				Obj1.addProperty("iphak_hakgwa", 		vHakjeokSeongjeok.getIphak_hakgwa());
				Obj1.addProperty("jeonhyeong_gb", 		vHakjeokSeongjeok.getJeonhyeong_gb());
				Obj1.addProperty("wetak_company", 		vHakjeokSeongjeok.getWetak_company());
				Obj1.addProperty("iphak_ilja", 			vHakjeokSeongjeok.getIphak_ilja());
				Obj1.addProperty("joleop_haknyeon", 	vHakjeokSeongjeok.getJoleop_haknyeon());
				Obj1.addProperty("joleop_hakgwa", 		vHakjeokSeongjeok.getJoleop_hakgwa());
				Obj1.addProperty("iphak_juya", 			vHakjeokSeongjeok.getIphak_juya());
				Obj1.addProperty("iphak_gb", 			vHakjeokSeongjeok.getIphak_gb());
				Obj1.addProperty("byunguk_gb", 			vHakjeokSeongjeok.getByunguk_gb());
				Obj1.addProperty("joleop_jeungseo_no", 	vHakjeokSeongjeok.getJoleop_jeungseo_no());
				Obj1.addProperty("hakwi_no", 			vHakjeokSeongjeok.getHakwi_no());
				Obj1.addProperty("bigo", 				vHakjeokSeongjeok.getBigo());
			jsonArr1.add(Obj1);		
			
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);		
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getKorColumnNameHakjeok=>"+dataResult.toString());		
		return dataResult.toString();
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT 칼럼 헤더명
	//-------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping("/seongjeok-column-name")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getKorColumnNameSeongjeok(@RequestBody VHakJeokSeongJeok _vHakjeokSeongjeok) throws Exception {
		
		if (!authcheck.getMetaAuthErrGenerator(_vHakjeokSeongjeok.getApikey()).equals("{}")) {
			logger.info("[StHakJeokMController][getByHakbeon] AUTHENTICATION RESTRICTIONS");
			return authcheck.getMetaAuthErrGenerator(_vHakjeokSeongjeok.getApikey());
		}		

		VHakJeokSeongJeok vHakjeokSeongjeok = sthakjeokmmapper.getKorColumnName(_vHakjeokSeongjeok.getHakbeon());

		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();

		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			

		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();

		if (vHakjeokSeongjeok != null) {
			Obj1.addProperty("sg_year", 			vHakjeokSeongjeok.getSg_year());
			Obj1.addProperty("sg_hakgi",			vHakjeokSeongjeok.getSg_hakgi());
			Obj1.addProperty("sg_haknyeon", 		vHakjeokSeongjeok.getSg_haknyeon());
			Obj1.addProperty("sg_hakbeon",			vHakjeokSeongjeok.getSg_hakbeon());
			Obj1.addProperty("isu_gb",				vHakjeokSeongjeok.getIsu_gb());
			Obj1.addProperty("chongjeom",			vHakjeokSeongjeok.getChongjeom());
			Obj1.addProperty("shinchung",			vHakjeokSeongjeok.getShinchung());
			Obj1.addProperty("chwideuk",			vHakjeokSeongjeok.getChwideuk());
			Obj1.addProperty("pyeongjeom_pyeonggyun",	vHakjeokSeongjeok.getPyeongjeom_pyeonggyun());
			Obj1.addProperty("chongjeom_pyeonggyun",	vHakjeokSeongjeok.getChongjeom_pyeonggyun());
			Obj1.addProperty("haksa_gyeonggo",		vHakjeokSeongjeok.getHaksa_gyeonggo());
			jsonArr1.add(Obj1);		
			
			Obj2.add("result", jsonArr1);
			dataResult.add("data", Obj2);		
		} else {
			dataResult.addProperty("data", "");
		}
		logger.info("getKorColumnNameSeongjeok=>"+dataResult.toString());		
		return dataResult.toString();
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - ONE (GET 방식)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@GetMapping("/studentinfo_get/{apikey},{hakbeon}")
//	public String getByHakbeonGet(@PathVariable String apikey, @PathVariable String hakbeon) throws Exception {
	@GetMapping("/studentinfo_get/apikey={apikey}&hakbeon={hakbeon}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public String getByHakbeonGet(@PathVariable String apikey, @PathVariable String hakbeon) throws Exception {	
		System.out.println("@@@@@@@@@@@@@@@@@@@=>"+hakbeon);
//		if (!authcheck.getMetaAuthErrGenerator(apikey).equals("{}")) {
//			logger.info("[StHakJeokMController][getByHakbeon] AUTHENTICATION RESTRICTIONS");
//			return authcheck.getMetaAuthErrGenerator(apikey);
//		}		
		
		StHakJeokM hakjeokm = sthakjeokmmapper.getByHakbeon(hakbeon);
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		
		String Message = "SUCCESS";
		dataResult.addProperty("reason", Message);
		dataResult.addProperty("result", "1");			
		
		JsonObject Obj1 = new JsonObject();
		JsonObject Obj2 = new JsonObject();
		
		Obj1.addProperty("hakbeon", hakjeokm.getHakbeon());
		Obj1.addProperty("jumin_no", hakjeokm.getJumin_no());
		Obj1.addProperty("birthday", hakjeokm.getBirthday());
		Obj1.addProperty("h_name", hakjeokm.getH_name());
		Obj1.addProperty("c_nane", hakjeokm.getC_nane());
		Obj1.addProperty("gukga", hakjeokm.getGukga());
		Obj1.addProperty("e_name_last", hakjeokm.getE_name_last());
		Obj1.addProperty("e_name", hakjeokm.getE_name());
		Obj1.addProperty("sangtae", hakjeokm.getSangtae());
		Obj1.addProperty("byeondongsayu", hakjeokm.getByeondongsayu());
		Obj1.addProperty("byeondong_ilja", hakjeokm.getByeondong_ilja());
		Obj1.addProperty("gwajeong_gb", hakjeokm.getGwajeong_gb());
		Obj1.addProperty("juya_gb", hakjeokm.getJuya_gb());
		Obj1.addProperty("hakgwa", hakjeokm.getHakgwa());
		Obj1.addProperty("jeongong", hakjeokm.getJeongong());
		Obj1.addProperty("haknyeon", hakjeokm.getHaknyeon());
		Obj1.addProperty("ban", hakjeokm.getBan());
		Obj1.addProperty("hakgi", hakjeokm.getHakgi());
		Obj1.addProperty("isuhakgi", hakjeokm.getIsuhakgi());
		Obj1.addProperty("iphak_hakgwa", hakjeokm.getIphak_hakgwa());
		Obj1.addProperty("jeonhyeong_gb", hakjeokm.getJeonhyeong_gb());
		Obj1.addProperty("wetak_company", hakjeokm.getWetak_company());
		Obj1.addProperty("iphak_ilja", hakjeokm.getIphak_ilja());
		Obj1.addProperty("joleop_haknyeon", hakjeokm.getJoleop_haknyeon());
		Obj1.addProperty("joleop_hakgwa", hakjeokm.getJoleop_hakgwa());
		Obj1.addProperty("iphak_juya", hakjeokm.getIphak_juya());
		Obj1.addProperty("iphak_gb", hakjeokm.getIphak_gb());
		Obj1.addProperty("byunguk_gb", hakjeokm.getByunguk_gb());
		Obj1.addProperty("joleop_jeungseo_no", hakjeokm.getJoleop_jeungseo_no());
		Obj1.addProperty("hakwi_no", hakjeokm.getHakwi_no());
		
		jsonArr1.add(Obj1);		
		
		Obj2.add("result", jsonArr1);
		dataResult.add("data", Obj2);		
				
		return dataResult.toString();
	}	
	
}
