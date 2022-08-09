package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.spk.api.entity.ApiScen;

@Mapper
public interface ApiScenMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (get)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Insert("INSERT INTO API_SCEN("
				+ " api_scen_id"	//API 시나리오 ID
				+ ",api_id"			//API_ID
				+ ",reg_dt"			//등록일
				+ ",reg_id"			//등록자
				+ ",upt_dt"			//수정일
				+ ",upt_id"			//수정자
				+ ",func_nm"		//시나리오 기능명
				+ ",proc_state"		//진행상태
				+ ",use_yn)"		//사용여부				
//				+ ",dev_fr_dt"		//개발기간 시작일시
//				+ ",dev_to_dt)"		//개발기간 종료일시
				+ "  VALUES"
				+ " ("
				+ " #{api_scen_id}"
				+ ",#{api_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{func_nm}"
				+ ",#{proc_state}"
				+ ",#{use_yn}"				
//				+ ",#{dev_fr_dt}"
//				+ ",#{dev_to_dt}"
				+ ")")
	int insertParam(
			   @Param("api_scen_id")  String api_scen_id
			  ,@Param("api_id") 	  String api_id
			  ,@Param("reg_dt")       String reg_dt
			  ,@Param("reg_id")       String reg_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("func_nm")      String func_nm
			  ,@Param("proc_state")   String proc_state
			  ,@Param("use_yn")    	  String use_yn			  
//			  ,@Param("dev_fr_dt")    String dev_fr_dt
//			  ,@Param("dev_to_dt")    String dev_to_dt
			  );
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Insert("INSERT INTO API_SCEN("
			+ " api_scen_id"	//API 시나리오 ID
			+ ",api_id"			//API_ID
			+ ",reg_dt"			//등록일
			+ ",reg_id"			//등록자
			+ ",upt_dt"			//수정일
			+ ",upt_id"			//수정자
			+ ",func_nm"		//시나리오 기능명
			+ ",proc_state"		//진행상태
			+ ",use_yn)"		//사용여부			
//			+ ",dev_fr_dt"		//개발기간 시작일시
//			+ ",dev_to_dt)"		//개발기간 종료일시
			+ "  VALUES"
			+ " ("
			+ " #{API_SCEN.api_scen_id}"
			+ ",#{API_SCEN.api_id}"
			+ ",#{API_SCEN.reg_dt}"
			+ ",#{API_SCEN.reg_id}"
			+ ",#{API_SCEN.upt_dt}"
			+ ",#{API_SCEN.upt_id}"
			+ ",#{API_SCEN.func_nm}"
			+ ",#{API_SCEN.proc_state}"
			+ ",#{API_SCEN.use_yn}"			
//			+ ",#{API_SCEN.dev_fr_dt}"
//			+ ",#{API_SCEN.dev_to_dt}"
			+ ")")	
	int insertBody(@Param("API_SCEN") ApiScen apiscen);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM API_SCEN")
	@Results(id="ApiScenMap", value={
		@Result(property="api_scen_id", column="api_scen_id"),
		@Result(property="api_id", column="api_id"),
		@Result(property="reg_dt", column="reg_dt"),
		@Result(property="reg_id", column="reg_id"),
		@Result(property="upt_dt", column="upt_dt"),
		@Result(property="upt_id", column="upt_id"),
		@Result(property="func_nm", column="func_nm"),
		@Result(property="proc_state", column="proc_state"),
		@Result(property="use_yn", column="use_yn")		
//		@Result(property="dev_fr_dt", column="dev_fr_dt"),
//		@Result(property="dev_to_dt", column="dev_to_dt")
	})
	List<ApiScen> getAll();
	
	
	// one (api_id 에 해당하는 시나리오 여러 건)
	@Select("SELECT * FROM API_SCEN WHERE api_id=#{api_id}")
	@ResultMap("ApiScenMap")
	List<ApiScen> getByApiId(
			@Param("api_id") String api_id
			);	
	
//	이하는 only one row 조회
//	// one
//	@Select("SELECT * FROM API_SCEN WHERE api_scen_id=#{api_scen_id} AND api_id=#{api_id}")
//	@ResultMap("ApiScenMap")
//	ApiScen getByApiScenId(
//			 @Param("api_scen_id") String api_scen_id
//			,@Param("api_id") String api_id
//			);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// Params
	@Update("UPDATE API_SCEN SET "
				+ "upt_dt=#{upt_dt}"
				+ ",upt_id=#{upt_id}"
				+ ",func_nm=#{func_nm}"
				+ ",proc_state=#{proc_state}"
				+ ",use_yn=#{use_yn}"				
//				+ ",dev_fr_dt=#{dev_fr_dt}"
//				+ ",dev_to_dt=#{dev_to_dt}"
				+ "WHERE api_scen_id=#{api_scen_id}"
				+ " AND api_id=#{api_id}")
	int updateParam(
			   @Param("api_scen_id")  String api_scen_id
			  ,@Param("api_id")       String api_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("func_nm")      String func_nm
			  ,@Param("proc_state")   String proc_state
			  ,@Param("use_yn")    	  String use_yn			  
//			  ,@Param("dev_fr_dt")    String dev_fr_dt
//			  ,@Param("dev_to_dt")    String dev_to_dt
			  );				
	
	// Body
	@Update("UPDATE API_SCEN SET "
			+ "upt_dt=#{API_SCEN.upt_dt}"
			+ ",upt_id=#{API_SCEN.upt_id}"
			+ ",func_nm=#{API_SCEN.func_nm}"
			+ ",proc_state=#{API_SCEN.proc_state}"
			+ ",use_yn=#{API_SCEN.use_yn}"			
//			+ ",dev_fr_dt=#{API_SCEN.dev_fr_dt}"
//			+ ",dev_to_dt=#{API_SCEN.dev_to_dt}"			
			+ "WHERE api_scen_id=#{api_scen_id}"
			+ "  AND api_id=#{api_id}")
	int updateBody(@Param("API_SCEN") ApiScen apiscen);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM API_SCEN "
			+"WHERE api_scen_id=#{api_scen_id}"
			+ " AND api_id=#{api_id}")
	int delete(
			@Param("api_scen_id") String api_scen_id
		   ,@Param("api_id") String api_id
			);	

}
