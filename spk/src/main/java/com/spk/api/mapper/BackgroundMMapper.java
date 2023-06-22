package com.spk.api.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
//import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import com.spk.api.entity.BackgroundM;

@Mapper
public interface BackgroundMMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("    SELECT "
		  + "      a.BACKGROUND_ID, "
		  + "      a.IMAGE_SRC, "
		  + "      a.THUMNAIL_SRC "
		  + "    FROM BACKGROUND_MST a "
		  + "   WHERE a.BACKGROUND_GBN = '1' "
		  + "ORDER BY a.BACKGROUND_ID")
	@Results(id="SpTempleteMap", value={
		@Result(property="background_id",         column="background_id"),
		@Result(property="image_src",             column="image_src"),
		@Result(property="thumnail_src",          column="thumnail_src")
	})
	List<BackgroundM> getBackgroundMasterList(@Param("BACKGROUND_MST") BackgroundM backgroundm);
	
}
