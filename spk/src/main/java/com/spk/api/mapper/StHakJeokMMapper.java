package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spk.api.entity.StHakJeokM;

@Mapper
public interface StHakJeokMMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Insert("INSERT INTO ST_HAKJEOK_M ("
			+ " hakbeon"
			+ ",reg_id"
			+ ",reg_dt"
			+ ",jumin_no"
			+ ",birthday"
			+ ",h_name"
			+ ",c_nane"
			+ ",gukga"
			+ ",e_name_last"
			+ ",e_name"
			+ ",sangtae"
			+ ",byeondongsayu"
			+ ",byeondong_ilja"
			+ ",gwajeong_gb"
			+ ",juya_gb"
			+ ",hakgwa"
			+ ",jeongong"
			+ ",haknyeon"
			+ ",ban"
			+ ",hakgi"
			+ ",isuhakgi"
			+ ",iphak_hakgwa"
			+ ",jeonhyeong_gb"
			+ ",wetak_company"
			+ ",iphak_ilja"
			+ ",joleop_haknyeon"
			+ ",joleop_hakgwa"
			+ ",iphak_juya"
			+ ",iphak_gb"
			+ ",byunguk_gb"
			+ ",joleop_jeungseo_no"
			+ ",hakwi_no"
			+ ",bigo) "
			+ "   VALUES"
			+ " ("
			+ " #{ST_HAKJEOK_M.hakbeon}"
			+ ",#{ST_HAKJEOK_M.reg_id}"
			+ ",#{ST_HAKJEOK_M.reg_dt}"
			+ ",#{ST_HAKJEOK_M.jumin_no}"
			+ ",#{ST_HAKJEOK_M.birthday}"
			+ ",#{ST_HAKJEOK_M.h_name}"
			+ ",#{ST_HAKJEOK_M.c_nane}"
			+ ",#{ST_HAKJEOK_M.gukga}"
			+ ",#{ST_HAKJEOK_M.e_name_last}"
			+ ",#{ST_HAKJEOK_M.e_name}"
			+ ",#{ST_HAKJEOK_M.sangtae}"
			+ ",#{ST_HAKJEOK_M.byeondongsayu}"
			+ ",#{ST_HAKJEOK_M.byeondong_ilja}"
			+ ",#{ST_HAKJEOK_M.gwajeong_gb}"
			+ ",#{ST_HAKJEOK_M.juya_gb}"
			+ ",#{ST_HAKJEOK_M.hakgwa}"
			+ ",#{ST_HAKJEOK_M.jeongong}"
			+ ",#{ST_HAKJEOK_M.haknyeon}"
			+ ",#{ST_HAKJEOK_M.ban}"
			+ ",#{ST_HAKJEOK_M.hakgi}"
			+ ",#{ST_HAKJEOK_M.isuhakgi}"
			+ ",#{ST_HAKJEOK_M.iphak_hakgwa}"
			+ ",#{ST_HAKJEOK_M.jeonhyeong_gb}"
			+ ",#{ST_HAKJEOK_M.wetak_company}"
			+ ",#{ST_HAKJEOK_M.iphak_ilja}"
			+ ",#{ST_HAKJEOK_M.joleop_haknyeon}"
			+ ",#{ST_HAKJEOK_M.joleop_hakgwa}"
			+ ",#{ST_HAKJEOK_M.iphak_juya}"
			+ ",#{ST_HAKJEOK_M.iphak_gb}"
			+ ",#{ST_HAKJEOK_M.byunguk_gb}"
			+ ",#{ST_HAKJEOK_M.joleop_jeungseo_no}"
			+ ",#{ST_HAKJEOK_M.hakwi_no}"
			+ ",#{ST_HAKJEOK_M.bigo}"
			+ ")")	
	int insertBody(@Param("ST_HAKJEOK_M") StHakJeokM hakjeokm);	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - ONE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("SELECT * FROM ST_HAKJEOK_M WHERE hakbeon=#{hakbeon}")
	@Results(id="HakJeokMap", value={
		@Result(property="hakbeon",             column="hakbeon"),
		@Result(property="profile",             column="profile"),
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
		@Result(property="bigo",            	column="bigo")
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
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
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
			+ "       ifnull(TB.year,' ') LIKE CONCAT('%', #{ST_HAKJEOK_M.year}, '%') "
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
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Update("UPDATE ST_HAKJEOK_M SET "
			+ " upt_id             = #{ST_HAKJEOK_M.upt_id}"               
			+ ",upt_dt	       	   = #{ST_HAKJEOK_M.upt_dt}"            
			+ ",jumin_no	       = #{ST_HAKJEOK_M.jumin_no}"          
			+ ",birthday	       = #{ST_HAKJEOK_M.birthday}"          
			+ ",h_name	           = #{ST_HAKJEOK_M.h_name}"            
			+ ",c_nane	           = #{ST_HAKJEOK_M.c_nane}"            
			+ ",gukga	           = #{ST_HAKJEOK_M.gukga}"             
			+ ",e_name_last	       = #{ST_HAKJEOK_M.e_name_last}"       
			+ ",e_name	           = #{ST_HAKJEOK_M.e_name}"            
			+ ",sangtae	           = #{ST_HAKJEOK_M.sangtae}"           
			+ ",byeondongsayu      = #{ST_HAKJEOK_M.byeondongsayu}"     
			+ ",byeondong_ilja     = #{ST_HAKJEOK_M.byeondong_ilja}"    
			+ ",gwajeong_gb	       = #{ST_HAKJEOK_M.gwajeong_gb}"       
			+ ",juya_gb	           = #{ST_HAKJEOK_M.juya_gb}"           
			+ ",hakgwa	           = #{ST_HAKJEOK_M.hakgwa}"            
			+ ",jeongong	       = #{ST_HAKJEOK_M.jeongong}"          
			+ ",haknyeon	       = #{ST_HAKJEOK_M.haknyeon}"          
			+ ",ban		           = #{ST_HAKJEOK_M.ban}"               
			+ ",hakgi	       	   = #{ST_HAKJEOK_M.hakgi}"             
			+ ",isuhakgi	       = #{ST_HAKJEOK_M.isuhakgi}"          
			+ ",iphak_hakgwa       = #{ST_HAKJEOK_M.iphak_hakgwa}"      
			+ ",jeonhyeong_gb      = #{ST_HAKJEOK_M.jeonhyeong_gb}"     
			+ ",wetak_company      = #{ST_HAKJEOK_M.wetak_company}"     
			+ ",iphak_ilja	       = #{ST_HAKJEOK_M.iphak_ilja}"        
			+ ",joleop_haknyeon    = #{ST_HAKJEOK_M.joleop_haknyeon}"   
			+ ",joleop_hakgwa      = #{ST_HAKJEOK_M.joleop_hakgwa}"     
			+ ",iphak_juya	       = #{ST_HAKJEOK_M.iphak_juya}"        
			+ ",iphak_gb	       = #{ST_HAKJEOK_M.iphak_gb}"          
			+ ",byunguk_gb	       = #{ST_HAKJEOK_M.byunguk_gb}"        
			+ ",joleop_jeungseo_no = #{ST_HAKJEOK_M.joleop_jeungseo_no}"
			+ ",hakwi_no	       = #{ST_HAKJEOK_M.hakwi_no}"          
			+ ",bigo 	           = #{ST_HAKJEOK_M.bigo}"              
			+ "WHERE hakbeon = #{ST_HAKJEOK_M.hakbeon}")	
	int updateBody(@Param("ST_HAKJEOK_M") StHakJeokM hakjeokm);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM ST_HAKJEOK_M WHERE hakbeon=#{ST_HAKJEOK_M.hakbeon}")
	int delete(@Param("ST_HAKJEOK_M") StHakJeokM hakjeokm);
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT 칼럼 헤더명
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// select 
	@Select("SELECT  "
			+ "     max(case COLUMN_NAME "
			+ "		    when 'HAKBEON' then '학번' end) as HAKBEON "
			+ "    ,max(case COLUMN_NAME "
			+ "	    	when 'PROFILE' then '프로필' end) as PROFILE "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JUMIN_NO' then '주민번호' end) as JUMIN_NO "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BIRTHDAY' then '생년월일' end) as BIRTHDAY "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'H_NAME' then '성명' end) as H_NAME "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'C_NANE' then '한문성명' end) as C_NANE "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'GUKGA' then '국가' end) as GUKGA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'E_NAME_LAST' then '영문성' end) as E_NAME_LAST "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'E_NAME' then '영문이름' end) as E_NAME "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'SANGTAE' then '학생상태' end) as SANGTAE "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BYEONDONGSAYU' then '변동사유' end) as BYEONDONGSAYU "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BYEONDONG_ILJA' then '변동일자' end) as BYEONDONG_ILJA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'GWAJEONG_GB' then '과정구분' end) as GWAJEONG_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JUYA_GB' then '주야구분' end) as JUYA_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'HAKGWA' then '학과' end) as HAKGWA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JEONGONG' then '전공' end) as JEONGONG "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'HAKNYEON' then '학년' end) as HAKNYEON "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BAN' then '학반' end) as BAN "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'HAKGI' then '학기' end) as HAKGI "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'ISUHAKGI' then '이수학기' end) as ISUHAKGI "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'IPHAK_HAKGWA' then '입학학과' end) as IPHAK_HAKGWA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JEONHYEONG_GB' then '전공구분' end) as JEONHYEONG_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'WETAK_COMPANY' then '위탁회사' end) as WETAK_COMPANY "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'IPHAK_ILJA' then '입학일자' end) as IPHAK_ILJA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JOLEOP_HAKNYEON' then '졸업학년' end) as JOLEOP_HAKNYEON "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JOLEOP_HAKGWA' then '졸업학과' end) as JOLEOP_HAKGWA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'IPHAK_JUYA' then '입학주야' end) as IPHAK_JUYA "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'IPHAK_GB' then '입학구분' end) as IPHAK_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BYUNGUK_GB' then '병역사항' end) as BYUNGUK_GB "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'JOLEOP_JEUNGSEO_NO' then '졸업증서번호' end) as JOLEOP_JEUNGSEO_NO "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'HAKWI_NO' then '학위번호' end) as HAKWI_NO "
			+ "    ,max(case COLUMN_NAME "
			+ "			when 'BIGO' then '비고' end) as BIGO "
			+ " FROM INFORMATION_SCHEMA.COLUMNS "
			+ "WHERE TABLE_NAME = 'V_HAKJEOK_SEONGJEOK' "
			+ "  AND TABLE_SCHEMA = 'spike' ")
	@ResultMap("HakJeokMap")
	StHakJeokM getKorColumnName(@Param("hakbeon") String hakbeon);	
}
