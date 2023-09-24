package com.spk.api.service.udr;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.udr.UDR01Entity;
import com.spk.api.mapper.udr.UDR01Mapper;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UDR01ServiceImle implements UDR01Service {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UDR01Mapper udr01Mapper;	
	
	AuthCheck authcheck = new AuthCheck();
	
	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>등록금 내역을 조회한다 </li>
	     * </ul>
	 * @param pUdr01Entity 클라이언트에서 요청받은 학적정보
	 * @return String
	 */	
	@Override
	public String getRegistAmtList(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(pUdr01Entity.getApikey());
		}

        //============================================================
        //< json 포맷 데이터 생성
        //============================================================		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();
		String Message = "SUCCESS";
		String Success = "1";
		
		try {
			List<UDR01Entity> udr01Entity = udr01Mapper.getRegistAmtList(pUdr01Entity);
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", Success);
	
			if (udr01Entity.size() > 0) {
				for (UDR01Entity item : udr01Entity) {
					JsonObject Obj1 = new JsonObject();
					JsonObject Obj2 = new JsonObject();
	
					Obj1.addProperty("year", item.getYear());
					Obj1.addProperty("semester", item.getSemester());
					Obj1.addProperty("dept", item.getDept());
					Obj1.addProperty("grade", item.getGrade());
					Obj1.addProperty("deptname", item.getDeptname());
					Obj1.addProperty("entr_amt", item.getEntr_amt());
					Obj1.addProperty("edu_amt", item.getEdu_amt());
					Obj1.addProperty("std_amt1", item.getStd_amt1());
					Obj1.addProperty("std_amt2", item.getStd_amt2());
					Obj1.addProperty("std_amt3", item.getStd_amt3());
					Obj1.addProperty("std_amt1", item.getStd_amt4());
					Obj1.addProperty("std_amt1", item.getStd_amt5());				
					Obj1.addProperty("std_amt1", item.getStd_amt6());
							
					jsonArr1.add(Obj1);
	
					Obj2.add("result", jsonArr1);
					dataResult.add("data", Obj2);
				}
			} else {
				JsonObject Obj3 = new JsonObject();
				Obj3.add("result", jsonArr1);
				dataResult.add("data", Obj3);
			}
			
		} catch (Exception e) {
			logger.error("[UDR01ServiceImle.getRegistAmtList] ERROR : " + e);
			e.printStackTrace();
		}			
		return dataResult.toString();
	}	
	
}
