package com.spk.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.spk.api.entity.BackgroundUser;

@Mapper
public interface BackgroundUserMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Update("UPDATE BACKGROUND_USER SET "
		+ "       background_id = #{BACKGROUND_USER.background_id} "
		+ "      ,upt_dt	    = now() "
		+ "      ,upt_id        = #{BACKGROUND_USER.upt_id} "
		+ "  WHERE spike_id = #{BACKGROUND_USER.spike_id}")
	int updateBody(@Param("BACKGROUND_USER") BackgroundUser backgroundUser);
}