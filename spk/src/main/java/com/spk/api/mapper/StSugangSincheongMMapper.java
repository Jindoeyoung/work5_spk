package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.StSugangSincheongM;

@Mapper
public interface StSugangSincheongMMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
//	@Select("SELECT * FROM ST_SUGANGSINCHEONG_M WHERE year LIKE CONCAT(#{ST_SUGANG_M.year}, '%') and hakgi LIKE CONCAT(#{ST_SUGANG_M.hakgi}, '%') and hakbeon LIKE CONCAT(#{ST_SUGANG_M.hakbeon}, '%')")
	@Select("SELECT * FROM ST_SUGANGSINCHEONG_M WHERE hakbeon=#{hakbeon}")
	@Results(id="SugangSincheong", value={			
		@Result(property="year",          	column="year"),
		@Result(property="hakgi",	 		column="hakgi"),
		@Result(property="hakbeon",	 		column="hakbeon"),
		@Result(property="gwamok_cd",	 	column="gwamok_cd"),
		@Result(property="gwamok_nm",	 	column="gwamok_nm"),
		@Result(property="isu_gb",	 		column="isu_gb"),
		@Result(property="ban",		 		column="ban"),
		@Result(property="hakjeom",	 		column="hakjeom"),
		@Result(property="gyosu_user_id", 	column="gyosu_user_id"),
		@Result(property="gyosu_nm",	 	column="gyosu_nm"),
		@Result(property="gwamok_sigan",	column="gwamok_sigan"),
		@Result(property="jaeisu_gb",	 	column="jaeisu_gb"),
		@Result(property="deuggup",	 		column="deuggup")
	})
	List<StSugangSincheongM> getSugangSincheongList(@Param("hakbeon") String hakbeon);
//	List<StSugangSincheongM> getSeongJeokList(@Param("ST_SUGANGSINCHEONG_M") StSugangSincheongM sugangsincheong);
}
