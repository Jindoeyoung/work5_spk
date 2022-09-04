package com.spk.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.StHakJeokM;

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
		@Result(property="hakwi_no",            column="hakwi_no"),
		@Result(property="imsi_hakjeok",        column="imsi_hakjeok"),
		@Result(property="bokhakyejeong_ilja",  column="bokhakyejeong_ilja")
	})
	StHakJeokM getByHakbeon(@Param("hakbeon") String hakbeon);		
}
