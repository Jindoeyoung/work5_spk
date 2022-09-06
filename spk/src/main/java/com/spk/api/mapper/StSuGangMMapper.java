package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.StSuGangM;


@Mapper
public interface StSuGangMMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
//	@Select("SELECT * FROM ST_SUGANG_M WHERE year=#{ST_SUGANG_M.year} and hakgi=#{ST_SUGANG_M.hakgi} and hakbeon=#{ST_SUGANG_M.hakbeon}")
	@Select("SELECT * FROM ST_SUGANG_M WHERE year LIKE CONCAT(#{ST_SUGANG_M.year}, '%') and hakgi LIKE CONCAT(#{ST_SUGANG_M.hakgi}, '%') and hakbeon LIKE CONCAT(#{ST_SUGANG_M.hakbeon}, '%')")
	@Results(id="SuGangMap", value={
		@Result(property="year",                  	column="year"),
		@Result(property="hakgi",		 			column="hakgi"),
		@Result(property="haknyeon",		 		column="haknyeon"),
		@Result(property="hakbeon",		 			column="hakbeon"),
		@Result(property="isu_gb",		 			column="isu_gb"),
		@Result(property="chongjeom",		 		column="chongjeom"),
		@Result(property="shinchung",		 		column="shinchung"),
		@Result(property="chwideuk",		 		column="chwideuk"),
		@Result(property="pyeongjeom_pyeonggyun", 	column="pyeongjeom_pyeonggyun"),
		@Result(property="chongjeom_pyeonggyun",	column="chongjeom_pyeonggyun"),
		@Result(property="haksa_gyeonggo",	 		column="haksa_gyeonggo")
	})
	List<StSuGangM> getSugangList(@Param("ST_SUGANG_M") StSuGangM sugang);	
}
