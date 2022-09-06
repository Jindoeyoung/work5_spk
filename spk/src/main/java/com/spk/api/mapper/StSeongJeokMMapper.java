package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.StSeongJeokM;

@Mapper
public interface StSeongJeokMMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM ST_SEONGJEOK_M WHERE year=#{ST_SUGANG_M.year} and hakgi=#{ST_SUGANG_M.hakgi} and hakbeon=#{ST_SUGANG_M.hakbeon}")
	@Results(id="SeongJeokMap", value={
		@Result(property="year",           	column="year"),
		@Result(property="hakgi",	  		column="hakgi"),
		@Result(property="haknyeon",	  	column="haknyeon"),
		@Result(property="hakbeon",	  		column="hakbeon"),
		@Result(property="gwamok",	  		column="gwamok"),
		@Result(property="gubun",	  		column="gubun"),
		@Result(property="hakjeom",	  		column="hakjeom"),
		@Result(property="chongjeom",	  	column="chongjeom"),
		@Result(property="deuggup",	  		column="deuggup"),
		@Result(property="jaesugang_year", 	column="jaesugang_year"),
		@Result(property="yeongae",	  		column="yeongae"),
		@Result(property="injung_hakjeom", 	column="injung_hakjeom")	
	})
	List<StSeongJeokM> getSeongJeokList(@Param("ST_SUGANG_M") StSeongJeokM seongjeok);
}
