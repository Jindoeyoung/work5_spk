package com.spk.api.service.colele;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.entity.colele.ColumnElementMasterEntity;
import com.spk.api.entity.colele.ColumnElementTypeEntity;
import com.spk.api.entity.colele.ColumnElementValueEntity;
import com.spk.api.entity.colele.RequestTablesEntity;
import com.spk.api.mapper.colele.ColumnElementMappers;
import com.spk.api.security.AuthCheck;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColumnElementServiceImpl implements ColumnElementService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ColumnElementMappers columnElementMapper;	
	
	AuthCheck authcheck = new AuthCheck();
	
	/**
	 * <p>SELECT (List)</p>
		 * <ul>
		 * 	<li>칼럼 엘리리먼트 리스트를 조회한다 </li>
	     * </ul>
	 * @param colEleMaster 테이블 정보
	 * @return String
	 */	
	@Override
	public String getColumnElementList(@Param("COL_ELEMENT_MST") RequestTablesEntity reqTablesEntity) throws Exception {
	    //============================================================
	    //< api-key check
	    //============================================================
		if (!authcheck.getMetaAuthErrGenerator(reqTablesEntity.getApikey()).equals("{}")) {
			return authcheck.getMetaAuthErrGenerator(reqTablesEntity.getApikey());
		}

		String[] tables = reqTablesEntity.getTbl_nm();
		
		JsonObject dataResult = new JsonObject();
		JsonArray jsonArr1 = new JsonArray();  // 반복 행 담기 용
		
		for (int i = 0; i<tables.length; i++) {
			List<ColumnElementMasterEntity> elementMaster = columnElementMapper.getMasterList(tables[i]);
			
			String Message = "SUCCESS";
			dataResult.addProperty("reason", Message);
			dataResult.addProperty("result", "1");	
	
			if (elementMaster.size() > 0) {		
			
				for (ColumnElementMasterEntity item : elementMaster) {
					
					JsonObject obj1 = new JsonObject();     // result 배열 하위 요소들  
					JsonObject obj2 = new JsonObject();		// result 하위
					JsonArray jsonArr2 = new JsonArray(); 	// Element 타입 용
					JsonArray jsonArr3 = new JsonArray(); 	// Element 밸류 용
					
					obj1.addProperty("table", item.getTbl_nm());
					obj1.addProperty("key", item.getCol_nm());  	
					obj1.addProperty("value", item.getCol_desc()); 		
			
					// Element 타입
					List<ColumnElementTypeEntity> elementTyp = (List<ColumnElementTypeEntity>) columnElementMapper.getTypeList(item.getTbl_nm(), item.getCol_nm());
	
					// 엘리먼트 타입/밸류 set [START]
					if (elementTyp.size() > 0) {
						for (ColumnElementTypeEntity item2 : elementTyp) {
							// Element 타입 set
							JsonObject obj3 = new JsonObject();  // elements 배열 하위 Json 담기
							obj3.addProperty("elementType", item2.getElement_typ());
	
							// Element 밸류 set
							List<ColumnElementValueEntity> elementVal = (List<ColumnElementValueEntity>) columnElementMapper.getValueList(item2.getTbl_nm(), item2.getCol_nm(), item2.getElement_typ());
							if (elementVal.size() > 0) {
								for (ColumnElementValueEntity item3 : elementVal) {
									JsonObject obj4 = new JsonObject();  // elementValue 배열 하위 Json 담기
									
									obj4.addProperty("key", item3.getElement_val());
									obj4.addProperty("value", item3.getElement_val_nm());
									jsonArr3.add(obj4);
								}
								obj3.add("elementValue", jsonArr3);
							} else {
								JsonObject obj5 = new JsonObject();  // 위로
								JsonArray jsonArr4 = new JsonArray(); 	// 위로
								
								obj5.addProperty("key", "");
								jsonArr4.add(obj5);
								obj3.add("elementValue", jsonArr4);
							}  // End of : if (elementVal.size() > 0) {
							// 타입과 밸류를 elements 배열에 set
							jsonArr2.add(obj3);
						}  // End of : for (ColumnElementTyp item2 : elementTyp) {
						obj1.add("elements", jsonArr2);	
					}  // End of : if (elementTyp.size() > 0) {
					// 엘리먼트 타입/밸류 set [END]
				
					obj1.addProperty("elementSelected", 0);
					// 권한
					boolean auth = item.getCol_auth().equals("Y") ? true : false;
					obj1.addProperty("permission", auth);
					
					jsonArr1.add(obj1);
					obj2.add("result", jsonArr1);
					
					dataResult.add("data", obj2);
				}
				
			} else { // if (elementMaster.size() > 0) {	
				// 데이터 없을 시
				dataResult.addProperty("data", "");
			}
		} // for (int i = 0; i<tables.length; i++) {
		logger.info("getColumnElementList!!=>"+dataResult.toString());	
		return dataResult.toString();
	}
	
}
