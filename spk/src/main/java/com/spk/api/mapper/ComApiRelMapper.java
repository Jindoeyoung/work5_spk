package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spk.api.entity.ComApiRel;

@Mapper
public interface ComApiRelMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Insert("INSERT INTO COM_API_REL("
				+ " com_id"			//컴포넌트 ID
				+ ",api_id"			//API ID
				+ ",reg_dt"			//등록일
				+ ",reg_id"			//등록자
				+ ",upt_dt"			//수정일
				+ ",upt_id"			//수정자
				+ ",api_src)"		//API 소스 경로				
				+ "   VALUES"
				+ " ("
				+ " #{com_id}"
				+ ",#{api_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{api_src}"				
				+ ")")
	int insertParam(
			   @Param("com_id") 	  String com_id
			  ,@Param("api_id")       String api_id
			  ,@Param("reg_dt")       String reg_dt
			  ,@Param("reg_id")       String reg_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("api_src")      String api_src			  
			  );			
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Insert("INSERT INTO COM_API_REL("
			+ " com_id"			//COM_ID
			+ ",api_id"			//API ID
			+ ",reg_dt"			//등록일
			+ ",reg_id"			//등록자
			+ ",upt_dt"			//수정일
			+ ",upt_id"			//수정자
			+ ",api_src)"		//API 소스 경로			
			+ "   VALUES"
			+ " ("
			+ " #{COM_MST.com_id}"
			+ ",#{COM_MST.api_id}"
			+ ",#{COM_MST.reg_dt}"
			+ ",#{COM_MST.reg_id}"
			+ ",#{COM_MST.upt_dt}"
			+ ",#{COM_MST.upt_id}"
			+ ",#{COM_MST.api_src}"
			+ ")")	
	int insertBody(@Param("COM_API_REL") ComApiRel comapirel);	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM COM_API_REL")
	@Results(id="ComApiRelMap", value={
		@Result(property="com_id", column="com_id"),
		@Result(property="api_id", column="api_id"),
		@Result(property="reg_dt", column="reg_dt"),
		@Result(property="reg_id", column="reg_id"),
		@Result(property="upt_dt", column="upt_dt"),
		@Result(property="upt_id", column="upt_id"),
		@Result(property="api_src",column="api_src")
	})
	List<ComApiRel> getAll();	
	
	// list (특정 com_id 에 해당하는 api_id 여러 건)
	@Select("SELECT * FROM COM_API_REL WHERE com_id=#{com_id}")
	@ResultMap("ComApiRelMap")
	List<ComApiRel> getByComId(
			@Param("com_id") String com_id
			);		
	
//  이하는 only one row 조회	
//	// one
//	@Select("SELECT * FROM COM_API_REL WHERE com_id=#{com_id} AND api_id=#{api_id}")
//	@ResultMap("ComApiRelMap")
//	ComApiRel getByComApiId(
//			@Param("com_id") String com_id
//		   ,@Param("api_id") String api_id
//			);	
	
	// only Api list (ComMstMapper 에서 사용)
	@Select("SELECT * FROM COM_API_REL WHERE com_id=#{com_id}")
	@Results(id="ComApiRelSrcMap", value= {
		@Result(property="api_src", column="api_src")
	})
	List<ComApiRel> getApiSrcByComId(
			@Param("com_id") String com_id
			);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// Params
	@Update("UPDATE COM_API_REL SET "
				+ "upt_dt=#{upt_dt}"
				+ ",upt_id=#{upt_id}"
				+ ",api_src=#{api_src}"
				+ "WHERE com_id=#{com_id} AND api_id=#{api_id}")
	int updateParam(
			   @Param("com_id") 	  String com_id
			  ,@Param("api_id")       String api_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("api_src")      String api_src
			  );				
	
	// Body
	@Update("UPDATE COM_API_REL SET "
			+ "upt_dt=#{COM_API_REL.upt_dt}"
			+ ",upt_id=#{COM_API_REL.upt_id}"
			+ ",api_src=#{COM_API_REL.api_src}"
			+ "WHERE com_id=#{COM_API_REL.com_id} AND api_id=#{COM_API_REL.api_id}")	
	int updateBody(@Param("COM_API_REL") ComApiRel comapirel);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM COM_API_REL WHERE com_id=#{com_id} AND api_id=#{api_id}")
	int delete(
			@Param("com_id") String com_id
		   ,@Param("api_id") String api_id
			);	
	
}