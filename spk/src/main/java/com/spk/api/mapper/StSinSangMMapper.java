package com.spk.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.StSinSangM;

@Mapper
public interface StSinSangMMapper {

	// select one
	@Select("SELECT * FROM ST_SINSANG_M WHERE hakbeon=#{hakbeon}")
	@Results(id="SinSangMap", value={
		@Result(property="hakbeon",          column="hakbeon"),
		@Result(property="curr_hakgwa",	     column="curr_hakgwa"),
		@Result(property="curr_haknyeon",    column="curr_haknyeon"),
		@Result(property="curr_juya",	     column="curr_juya"),
		@Result(property="curr_ban",	     column="curr_ban"),
		@Result(property="curr_hakgi",	     column="curr_hakgi"),
		@Result(property="jido_gyosu_1_1",   column="jido_gyosu_1_1"),
		@Result(property="jido_gyosu_1_2",   column="jido_gyosu_1_2"),
		@Result(property="jido_gyosu_2_1",   column="jido_gyosu_2_1"),
		@Result(property="jido_gyosu_2_2",   column="jido_gyosu_2_2"),
		@Result(property="jido_gyosu_3_1",   column="jido_gyosu_3_1"),
		@Result(property="jido_gyosu_3_2",   column="jido_gyosu_3_2"),
		@Result(property="sinjang",	     column="sinjang"),
		@Result(property="chejung",	     column="chejung"),
		@Result(property="blood_gb",	     column="blood_gb"),
		@Result(property="l_siryeok",	     column="l_siryeok"),
		@Result(property="r_siryeok",	     column="r_siryeok"),
		@Result(property="jonggyo",	     column="jonggyo"),
		@Result(property="teukgi",	     column="teukgi"),
		@Result(property="chwimi",	     column="chwimi"),
		@Result(property="byeolmyeong",	     column="byeolmyeong"),
		@Result(property="sukso",	     column="sukso"),
		@Result(property="tonghak",	     column="tonghak"),
		@Result(property="seonggyeok",	     column="seonggyeok"),
		@Result(property="jangaekind",	     column="jangaekind"),
		@Result(property="jangaedeunggeup",  column="jangaedeunggeup"),
		@Result(property="eng_hwalyong",     column="eng_hwalyong"),
		@Result(property="toeik_jumsu",	     column="toeik_jumsu"),
		@Result(property="jagayong_yn",	     column="jagayong_yn"),
		@Result(property="dongari",	     column="dongari"),
		@Result(property="danche_name",	     column="danche_name"),
		@Result(property="jinro",	     column="jinro"),
		@Result(property="huimang_college",  column="huimang_college"),
		@Result(property="huimang_hakgwa",   column="huimang_hakgwa"),
		@Result(property="tel_no",	     column="tel_no"),
		@Result(property="hp_no",	     column="hp_no"),
		@Result(property="email",	     column="email"),
		@Result(property="birthday",	     column="birthday"),
		@Result(property="eumyang_gb",	     column="eumyang_gb"),
		@Result(property="sex",		     column="sex")			
	})
	StSinSangM getByHakbeon(@Param("hakbeon") String hakbeon);
}
