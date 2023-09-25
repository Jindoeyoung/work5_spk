package com.spk.api.service.udr;

//import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.udr.UDR01Entity;
import com.spk.api.entity.udr.UDR01EntityResult;

/**
 * <p>Service</p>
 */
public interface UDR01Service {
    //============================================================
    //< SELECT (List)
    //============================================================	
	public String getRegistAmtList(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception;
	
    //============================================================
    //< INSERT
    //============================================================
	public String insertRegistAmt(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception;
	
    //============================================================
    //< UPDATE
    //============================================================	
	public String updateRegistAmt(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception;
	
    //============================================================
    //< DELETE
    //============================================================	
	public String deleteRegistAmt(@Param("ST_REGIST_AMT") UDR01Entity pUdr01Entity) throws Exception;
	
    //============================================================
    //< SAVE
    //============================================================
	public String saveRegistAmt(@Param("ST_REGIST_AMT") UDR01EntityResult pUdr01Entity) throws Exception;
//	public String saveRegistAmt(@Param("ST_REGIST_AMT") List<UDR01Entity> pUdr01Entity) throws Exception;	
}
