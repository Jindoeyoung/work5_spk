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

import com.spk.api.entity.SpTempleteM;

@Mapper
public interface SpTempleteMMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("    SELECT "
		  + "      a.TEMPLETE_ID, "
		  + "      a.IMAGE_SRC, "
		  + "      a.THUMNAIL_SRC "
		  + "    FROM SP_TEMPLETE_MST a "
		  + "   WHERE a.TEMPLETE_GBN = '1' "
		  + "ORDER BY a.TEMPLETE_ID")
	@Results(id="SpTempleteMap", value={
		@Result(property="templete_id",           column="templete_id"),
		@Result(property="image_src",             column="image_src"),
		@Result(property="thumnail_src",          column="thumnail_src")
	})
	List<SpTempleteM> getTempleteMasterList(@Param("SP_TEMPLETE_MST") SpTempleteM templetem);
	
}
