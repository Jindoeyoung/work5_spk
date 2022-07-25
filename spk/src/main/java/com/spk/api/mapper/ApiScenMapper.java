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
				+ ",dev_fr_dt"		//개발기간 시작일시
				+ ",dev_to_dt)"		//개발기간 종료일시
				+ "  VALUES"
				+ " ("
				+ " #{api_scen_id}"
				+ ",#{api_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{func_nm}"
				+ ",#{dev_fr_dt}"
				+ ",#{dev_to_dt}"
				+ ")")
	int insertParam(
			   @Param("api_scen_id")  String api_scen_id
			  ,@Param("api_id") 	  String api_id
			  ,@Param("reg_dt")       String reg_dt
			  ,@Param("reg_id")       String reg_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("func_nm")      String func_nm
			  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
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
			+ ",dev_fr_dt"		//개발기간 시작일시
			+ ",dev_to_dt)"		//개발기간 종료일시
			+ "  VALUES"
			+ " ("
			+ " #{API_SCEN.api_scen_id}"
			+ ",#{API_SCEN.api_id}"
			+ ",#{API_SCEN.reg_dt}"
			+ ",#{API_SCEN.reg_id}"
			+ ",#{API_SCEN.upt_dt}"
			+ ",#{API_SCEN.upt_id}"
			+ ",#{API_SCEN.func_nm}"
			+ ",#{API_SCEN.dev_fr_dt}"
			+ ",#{API_SCEN.dev_to_dt}"
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
		@Result(property="dev_fr_dt", column="dev_fr_dt"),
		@Result(property="dev_to_dt", column="dev_to_dt")
	})
	List<ApiScen> getAll();
	
	// one
	@Select("SELECT * FROM API_SCEN WHERE api_scen_id=#{api_scen_id} AND api_id=#{api_id}")
	@ResultMap("ApiScenMap")
	ApiScen getByApiScenId(
			 @Param("api_scen_id") String api_scen_id
			,@Param("api_id") String api_id
			);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// Params
	@Update("UPDATE API_SCEN SET "
				+ "upt_dt=#{upt_dt}"
				+ ",upt_id=#{upt_id}"
				+ ",func_nm=#{func_nm}"
				+ ",dev_fr_dt=#{dev_fr_dt}"
				+ ",dev_to_dt=#{dev_to_dt}"
				+ "WHERE api_scen_id=#{api_scen_id}"
				+ " AND api_id=#{api_id}")
	int updateParam(
			   @Param("api_scen_id")  String api_scen_id
			  ,@Param("api_id")       String api_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("func_nm")      String func_nm
			  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
			  );				
	
	// Body
	@Update("UPDATE API_SCEN SET "
			+ "upt_dt=#{API_SCEN.upt_dt}"
			+ ",upt_id=#{API_SCEN.upt_id}"
			+ ",func_nm=#{API_SCEN.func_nm}"
			+ ",dev_fr_dt=#{API_SCEN.dev_fr_dt}"
			+ ",dev_to_dt=#{API_SCEN.dev_to_dt}"			
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
