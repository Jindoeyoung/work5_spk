package com.spk.api.mapper.udr;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.udr.UDR01Entity;

@Mapper
public interface UDR01Mapper {
    //============================================================
    //< SELECT (List)
    //============================================================	
	List<UDR01Entity> getRegistAmtList(
		@Param("ST_REGIST_AMT") UDR01Entity udr01Entity
	);
	
    //============================================================
    //< INSERT
    //============================================================	
	int insertRegistAmt(
		@Param("ST_REGIST_AMT") UDR01Entity udr01Entity
	);
	
}
