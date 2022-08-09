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

import com.spk.api.entity.ComScen;

@Mapper
public interface ComScenMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (get)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Insert("INSERT INTO COM_SCEN("
				+ " com_scen_id"	//컴포넌트 시나리오 ID
				+ ",com_id"			//컴포넌트 ID
				+ ",reg_dt"			//등록일
				+ ",reg_id"			//등록자
				+ ",upt_dt"			//수정일
				+ ",upt_id"			//수정자
				+ ",func_nm"		//시나리오 기능명
				+ ",dev_fr_dt"		//개발기간 시작일시
				+ ",dev_to_dt)"		//개발기간 종료일시
				+ "  VALUES"
				+ " ("
				+ " #{com_scen_id}"
				+ ",#{com_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{func_nm}"
				+ ",#{dev_fr_dt}"
				+ ",#{dev_to_dt}"
				+ ")")
	int insertParam(
			   @Param("com_scen_id")  String com_scen_id
			  ,@Param("com_id") 	  String com_id
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
	@Insert("INSERT INTO COM_SCEN("
			+ " com_scen_id"	//컴포넌트 시나리오 ID
			+ ",com_id"			//컴포넌트 ID
			+ ",reg_dt"			//등록일
			+ ",reg_id"			//등록자
			+ ",upt_dt"			//수정일
			+ ",upt_id"			//수정자
			+ ",func_nm"		//시나리오 기능명
			+ ",dev_fr_dt"		//개발기간 시작일시
			+ ",dev_to_dt)"		//개발기간 종료일시
			+ "  VALUES"
			+ " ("
			+ " #{COM_SCEN.com_scen_id}"
			+ ",#{COM_SCEN.com_id}"
			+ ",#{COM_SCEN.reg_dt}"
			+ ",#{COM_SCEN.reg_id}"
			+ ",#{COM_SCEN.upt_dt}"
			+ ",#{COM_SCEN.upt_id}"
			+ ",#{COM_SCEN.func_nm}"
			+ ",#{COM_SCEN.dev_fr_dt}"
			+ ",#{COM_SCEN.dev_to_dt}"
			+ ")")	
	int insertBody(@Param("COM_SCEN") ComScen comscen);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM COM_SCEN")
	@Results(id="ComScenMap", value={
		@Result(property="com_scen_id", column="com_scen_id"),
		@Result(property="com_id", column="com_id"),
		@Result(property="reg_dt", column="reg_dt"),
		@Result(property="reg_id", column="reg_id"),
		@Result(property="upt_dt", column="upt_dt"),
		@Result(property="upt_id", column="upt_id"),
		@Result(property="func_nm", column="func_nm"),
		@Result(property="dev_fr_dt", column="dev_fr_dt"),
		@Result(property="dev_to_dt", column="dev_to_dt")
	})
	List<ComScen> getAll();

	
	// one (com_id 에 해당하는 시나리오 여러 건)
	@Select("SELECT * FROM COM_SCEN WHERE com_id=#{com_id}")
	@ResultMap("ComScenMap")
	List<ComScen> getByComId(
			@Param("com_id") String com_id
			);	

//  이하는 only one row 조회
//	// one
//	@Select("SELECT * FROM COM_SCEN WHERE com_scen_id=#{com_scen_id} AND com_id=#{com_id}")
//	@ResultMap("ComScenMap")
//	ComScen getByComScenId(
//			 @Param("com_scen_id") String com_scen_id
//			,@Param("com_id") String com_id
//			);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// Params
	@Update("UPDATE COM_SCEN SET "
				+ "upt_dt=#{upt_dt}"
				+ ",upt_id=#{upt_id}"
				+ ",func_nm=#{func_nm}"
				+ ",dev_fr_dt=#{dev_fr_dt}"
				+ ",dev_to_dt=#{dev_to_dt}"
				+ "WHERE com_scen_id=#{com_scen_id}"
				+ " AND com_id=#{com_id}")
	int updateParam(
			   @Param("com_scen_id")  String com_scen_id
			  ,@Param("com_id")       String com_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("func_nm")      String func_nm
			  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
			  );				
	
	// Body
	@Update("UPDATE COM_SCEN SET "
			+ "upt_dt=#{COM_SCEN.upt_dt}"
			+ ",upt_id=#{COM_SCEN.upt_id}"
			+ ",func_nm=#{COM_SCEN.func_nm}"
			+ ",dev_fr_dt=#{COM_SCEN.dev_fr_dt}"
			+ ",dev_to_dt=#{COM_SCEN.dev_to_dt}"			
			+ "WHERE com_scen_id=#{com_scen_id}"
			+ "  AND com_id=#{com_id}")
	int updateBody(@Param("COM_SCEN") ComScen comscen);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM COM_SCEN "
			+"WHERE com_scen_id=#{com_scen_id}"
			+ " AND com_id=#{com_id}")
	int delete(
			@Param("com_scen_id") String com_scen_id
		   ,@Param("com_id") String com_id
			);	
	
}
