package com.spk.api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spk.api.error.EResultCode;

public class Utils {

	public JsonObject getMetaErrGenerator(Integer responseCode, String type) throws Exception {

		JsonObject dataResult = new JsonObject();	
		String desc = "";
		String result = "-3";
		
		try {
			
			if (responseCode == 10000 ) {
				desc = "AUTHENTICATION RESTRICTIONS";
				result = "-3";				
			} else {
				desc = "ETC ERROR, PLEASE CHECK";
			}		

			dataResult.addProperty("reason", "[" + responseCode + " ERROR] " + desc);
			dataResult.addProperty("result", result);			
			dataResult.addProperty("data", "");
				
			return dataResult;
		} catch (Exception e) {
//			logger.error("[Utils.getMetaErrGenerator] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult;
	}	
	
	public JsonObject getMetaErrGenerator2(Integer responseCode) throws Exception {

		JsonObject dataResult = new JsonObject();
//		JsonArray jsonArr = new JsonArray();
		String desc = "";
		int result = 0;
		
		try {
			
			if (responseCode == 1000 ) {
				desc = "Exception";
				result = 0;
			} else if (responseCode == 2000 ) {
				desc = "DuplicateKeyException";
				result = -1;
			
			} else {
				desc = "ETC ERROR, PLEASE CHECK";
			}		

			dataResult.addProperty("reason", "[" + responseCode + " ERROR] " + desc);
			dataResult.addProperty("result", result);
			
			dataResult.addProperty("data", "");
//			dataResult.add("data", jsonArr);
	
			return dataResult;
		} catch (Exception e) {
//			logger.error("[Utils.getMetaErrGenerator] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult;
	}	
	
	public JsonObject getMetaErrGenerator3(EResultCode resultCode) throws Exception {

		JsonObject dataResult = new JsonObject();
		int result = -1;
		
		try {

			dataResult.addProperty("reason", "[" + resultCode.getResultCode() + " ERROR] " + resultCode.getResultMessage());
			dataResult.addProperty("result", result);
			
			dataResult.addProperty("data", "");
	
			return dataResult;
		} catch (Exception e) {
//			logger.error("[Utils.getMetaErrGenerator] ERROR : " + e);
			e.printStackTrace();
		}
		return dataResult;
	}	
	
	public JsonObject getOneGenerator(String data) throws Exception {
		
		
		System.out.println("1. data ====> " + data);
		
		JsonObject dataResult = new JsonObject();
		
		try {	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return dataResult;
	
	}
	
	
	public JsonObject getMultiGenerator(String datas) throws Exception {
		
		
		System.out.println("2. datas ====> " + datas);
		
		JsonObject dataResult = new JsonObject();
		
		try {	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return dataResult;
	
	}	
	
}
