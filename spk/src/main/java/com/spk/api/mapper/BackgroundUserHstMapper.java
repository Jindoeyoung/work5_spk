package com.spk.api.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.BackgroundUser;
//import com.spk.api.entity.BackgroundUserHst;

@Mapper
public interface BackgroundUserHstMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Insert("INSERT INTO BACKGROUND_USER_HST ("
			+ " spike_id "
			+ " ,background_id "
			+ " ,seq "
			+ ",reg_dt"
			+ ",reg_id"
			+ ",image_src"
			+ ",thumnail_src) "
			+ "   SELECT "
			+ " #{BACKGROUND_USER.spike_id} "
			+ ",background_id "
			+ ",ifnull((select max(seq) + 1 from BACKGROUND_USER_HST where spike_id = #{BACKGROUND_USER.spike_id} ), 1) "
//			+ ",ifnull((select max(seq) + 1 from BACKGROUND_USER_HST where spike_id = ' "+ "#{BACKGROUND_USER_HST.spike_id}" + "' ), 1) "
			+ ",now() "
			+ ",#{BACKGROUND_USER.spike_id}"
			+ ",image_src "
			+ ",thumnail_src "
			+ " FROM BACKGROUND_MST "
			+ "WHERE background_id = #{BACKGROUND_USER.background_id_before} ")
//			+ ")")	
	int insertBody(@Param("BACKGROUND_USER") BackgroundUser backgroundUser);	
	
//	@Insert("INSERT INTO BACKGROUND_USER_HST ("
//			+ " spike_id "
//			+ " background_id "
//			+ " seq "
//			+ ",reg_dt"
//			+ ",reg_id"
//			+ ",image_src"
//			+ ",thumnail_src) "
//			+ "   VALUES"
//			+ " ("
//			+ " #{BACKGROUND_USER_HST.spike_id} "
//			+ ",#{BACKGROUND_USER_HST.background_id} "
//			+ ",#{BACKGROUND_USER_HST.seq} "
//			+ ",now() "
//			+ ",#{BACKGROUND_USER_HST.reg_id}"
//			+ ",#{BACKGROUND_USER_HST.image_src}"
//			+ ",#{BACKGROUND_USER_HST.thumnail_src}"
//			+ ")")	
//	int insertBody(@Param("BACKGROUND_USER_HST") BackgroundUserHst backgroundUserHst);	
}