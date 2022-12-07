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
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT 칼럼 헤더명
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// select 
	@Select("SELECT  "
			+ "     max(case COLUMN_NAME "
			+ "		    when 'HAKBEON' then '학번' end) as HAKBEON "			
			+ "    ,max(case COLUMN_NAME "
			+ "		    when 'ISU_GB' then '이수구분' end) as ISU_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "	    	when 'GWAMOK_CD' then '교과목코드' end) as GWAMOK_CD "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BAN' then '분반' end) as BAN "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'GWAMOK_NM' then '교과목명' end) as GWAMOK_NM "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'HAKJEOM' then '학점' end) as HAKJEOM "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'GYOSU_NM' then '담당교수' end) as GYOSU_NM "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'GWAMOK_SIGAN' then '과목시간' end) as GWAMOK_SIGAN "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JAEISU_GB' then '재이수구분' end) as JAEISU_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "    		when 'DEUGGUP' then '등급' end) as DEUGGUP "
			+ "  FROM INFORMATION_SCHEMA.COLUMNS "
			+ " WHERE TABLE_NAME = 'ST_SUGANGSINCHEONG_M' "
			+ "   AND TABLE_SCHEMA = 'spike' ")
//	@ResultMap("HakJeokMap")
	@Results(id="SugangSincheongMap", value={
			@Result(property="hakbeon",             column="hakbeon"),
			@Result(property="isu_gb",              column="isu_gb"),
			@Result(property="gwamok_cd",           column="gwamok_cd"),
			@Result(property="ban",            		column="ban"),
			@Result(property="gwamok_nm",           column="gwamok_nm"),
			@Result(property="hakjeom",             column="hakjeom"),
			@Result(property="gyosu_nm",            column="gyosu_nm"),
			@Result(property="gwamok_sigan",        column="gwamok_sigan"),
			@Result(property="jaeisu_gb",         	column="jaeisu_gb"),
			@Result(property="deuggup",             column="deuggup")
	})
	StSugangSincheongM getKorColumnName();
//	StSugangSincheongM getKorColumnName(@Param("hakbeon") String hakbeon);	
}
