package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.ComMst;
import com.spk.api.entity.StHakJeokM;
import com.spk.api.entity.StSeongJeokM;

@Mapper
public interface StHakJeokMMapper {

	// select one
	@Select("SELECT * FROM ST_HAKJEOK_M WHERE hakbeon=#{hakbeon}")
	@Results(id="HakJeokMap", value={
		@Result(property="hakbeon",             column="hakbeon"),
		@Result(property="jumin_no",            column="jumin_no"),
		@Result(property="birthday",            column="birthday"),
		@Result(property="h_name",              column="h_name"),
		@Result(property="c_nane",              column="c_nane"),
		@Result(property="gukga",               column="gukga"),
		@Result(property="e_name_last",         column="e_name_last"),
		@Result(property="e_name",              column="e_name"),
		@Result(property="sangtae",             column="sangtae"),
		@Result(property="byeondongsayu",       column="byeondongsayu"),
		@Result(property="byeondong_ilja",      column="byeondong_ilja"),
		@Result(property="gwajeong_gb",         column="gwajeong_gb"),
		@Result(property="juya_gb",             column="juya_gb"),
		@Result(property="hakgwa",              column="hakgwa"),
		@Result(property="jeongong",            column="jeongong"),
		@Result(property="haknyeon",            column="haknyeon"),
		@Result(property="ban",                 column="ban"),
		@Result(property="hakgi",               column="hakgi"),
		@Result(property="isuhakgi",            column="isuhakgi"),
		@Result(property="iphak_hakgwa",        column="iphak_hakgwa"),
		@Result(property="jeonhyeong_gb",       column="jeonhyeong_gb"),
		@Result(property="wetak_company",       column="wetak_company"),
		@Result(property="iphak_ilja",          column="iphak_ilja"),
		@Result(property="joleop_haknyeon",     column="joleop_haknyeon"),
		@Result(property="joleop_hakgwa",       column="joleop_hakgwa"),
		@Result(property="iphak_juya",          column="iphak_juya"),
		@Result(property="iphak_gb",            column="iphak_gb"),
		@Result(property="byunguk_gb",          column="byunguk_gb"),
		@Result(property="joleop_jeungseo_no",  column="joleop_jeungseo_no"),
		@Result(property="hakwi_no",            column="hakwi_no")
	})
	StHakJeokM getByHakbeon(@Param("hakbeon") String hakbeon);
	
//	@Select("SELECT "
//			+ "     hakbeon"
//			+ "    ,haknyeon"
//			+ "    ,h_name"
//			+ "    ,hakgwa"
//			+ "    ,ban"
//			+ "    ,case when substr(jumin_no,7,1) = 1 or substr(jumin_no,7,1) = 3 then '남자'"
//			+ "          when substr(jumin_no,7,1) = 2 or substr(jumin_no,7,1) = 3 then '여자' end as sex"
//			+ "    ,sangtae"
//			+ "    ,bigo"
//			+ " FROM "
//			+ "    ST_HAKJEOK_M "
//			+ " WHERE "
////			+ "   and year LIKE CONCAT(#{ST_HAKJEOK_M.haknyeon}, '%') "
//			+ "       haknyeon LIKE CONCAT('%', #{ST_HAKJEOK_M.haknyeon}, '%') "
//			+ "   and hakgwa LIKE CONCAT('%', #{ST_HAKJEOK_M.hakgwa}, '%') "
//			+ "   and h_name LIKE CONCAT('%', #{ST_HAKJEOK_M.h_name}, '%') "
//			+ "   and hakbeon LIKE CONCAT('%', #{ST_HAKJEOK_M.hakbeon}, '%')")			
////			+ " WHERE hakbeon LIKE CONCAT(#{ST_HAKJEOK_M.hakbeon}, '%')")
	
	@Select("SELECT "
			+ "     HJ.hakbeon"
			+ "    ,HJ.haknyeon"
			+ "    ,HJ.h_name"
			+ "    ,HJ.hakgwa"
			+ "    ,HJ.ban"
			+ "    ,case when substr(HJ.jumin_no,7,1) = 1 or substr(HJ.jumin_no,7,1) = 3 then '남자'"
			+ "          when substr(HJ.jumin_no,7,1) = 2 or substr(HJ.jumin_no,7,1) = 4 then '여자' end as sex"
			+ "    ,HJ.sangtae"
			+ "    ,HJ.bigo"
			+ " FROM "
			+ "    ST_BUSEOTEAMBAN_H TB INNER JOIN ST_HAKJEOK_M HJ ON (TB.hakbeon = HJ.hakbeon) "
			+ " WHERE "
			+ "       TB.year LIKE CONCAT('%', #{ST_HAKJEOK_M.year}, '%') "
			+ "   and ifnull(HJ.hakgi,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.hakgi}, '%') "
			+ "   and ifnull(HJ.hakgwa,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.hakgwa}, '%') "
			+ "   and ifnull(HJ.haknyeon,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.haknyeon}, '%') "
			+ "   and ifnull(HJ.ban,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.ban}, '%') "
			+ "   and ifnull(HJ.h_name,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.h_name}, '%') "
			+ "   and ifnull(HJ.hakbeon,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.hakbeon}, '%')")
	@Results(id="SeongJeokMap", value={
		@Result(property="hakbeon",           	column="hakbeon"),
		@Result(property="haknyeon",	  		column="haknyeon"),
		@Result(property="h_name",	  			column="h_name"),
		@Result(property="hakgwa",	  			column="hakgwa"),
		@Result(property="ban",	  				column="ban"),
		@Result(property="sex",	  				column="sex"),
		@Result(property="sangtae",	  			column="sangtae"),
		@Result(property="bigo",	  			column="bigo")	
	})
	List<StHakJeokM> getSudentList(@Param("ST_HAKJEOK_M") StHakJeokM hakjeokm);
//	List<StHakJeokM> getSudentList(@Param("hakbeon") String hakbeon);
	
}
