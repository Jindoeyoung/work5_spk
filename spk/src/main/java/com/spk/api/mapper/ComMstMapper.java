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

import com.spk.api.entity.ComMst;

@Mapper
public interface ComMstMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Params)
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Insert("INSERT INTO COM_MST("
				+ " com_id"			//컴포넌트 ID
				+ ",reg_dt"			//등록일
				+ ",reg_id"			//등록자
				+ ",upt_dt"			//수정일
				+ ",upt_id"			//수정자
				+ ",com_nm"			//컴포넌트 명
				+ ",com_cate"		//카테고리
				+ ",com_attr"		//속성
				+ ",com_form"		//형태
				+ ",com_src"		//소스경로
				+ ",dev_fr_dt"		//개발기간(시작)
				+ ",dev_to_dt"		//개발기간(종료)
				+ ",use_fr_dt"		//사용기간(시작)
				+ ",use_to_dt"		//사용기간(종료)				
				+ ",requester"		//사용자(요청자)
				+ ",owner"			//소유자(관리자)
				+ ",developer"		//개발자
				+ ",participant"	//참여자
				+ ",scenario)"		//시나리오
				+ "   VALUES"
				+ " ("
				+ " #{com_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{com_nm}"
				+ ",#{com_cate}"
				+ ",#{com_attr}"
				+ ",#{com_form}"
				+ ",#{com_src}"
				+ ",#{dev_fr_dt}"
				+ ",#{dev_to_dt}"
				+ ",#{use_fr_dt}"
				+ ",#{use_to_dt}"
				+ ",#{requester}"
				+ ",#{owner}"
				+ ",#{developer}"
				+ ",#{participant}"
				+ ",#{scenario}"
				+ ")")
	int insertParam(
			   @Param("com_id") 	  String com_id
			  ,@Param("reg_dt")       String reg_dt
			  ,@Param("reg_id")       String reg_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("com_nm")       String com_nm
			  ,@Param("com_cate")     String com_cate
			  ,@Param("com_attr")     String com_attr
			  ,@Param("com_form")	  String com_form
			  ,@Param("com_src")      String com_src
		 	  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
			  ,@Param("use_fr_dt")	  String use_fr_dt
			  ,@Param("use_to_dt")    String use_to_dt
			  ,@Param("requester")    String requester
			  ,@Param("owner")	      String owner
			  ,@Param("developer")    String developer
			  ,@Param("participant")  String participant
			  ,@Param("scenario")     String scenario
			  );			
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Insert("INSERT INTO API_MST("
			+ " com_id"			//COM_ID
			+ ",reg_dt"			//등록일
			+ ",reg_id"			//등록자
			+ ",upt_dt"			//수정일
			+ ",upt_id"			//수정자
			+ ",com_nm"			//컴포넌트명
			+ ",com_cate"		//카테고리
			+ ",com_attr"		//속성
			+ ",com_form"		//형태
			+ ",com_src"		//소스경로		
			+ ",dev_fr_dt"		//개발기간(시작)
			+ ",dev_to_dt"		//개발기간(종료)
			+ ",use_fr_dt"		//사용기간(시작)
			+ ",use_to_dt"		//사용기간(종료)
			+ ",requester"		//사용자(요청자)
			+ ",owner"			//소유자(관리자)
			+ ",developer"		//개발자
			+ ",participant"	//참여자
			+ ",scenario)"		//시나리오
			+ "   VALUES"
			+ " ("
			+ " #{COM_MST.api_id}"
			+ ",#{COM_MST.reg_dt}"
			+ ",#{COM_MST.reg_id}"
			+ ",#{COM_MST.upt_dt}"
			+ ",#{COM_MST.upt_id}"
			+ ",#{COM_MST.com_nm}"
			+ ",#{COM_MST.com_cate}"
			+ ",#{COM_MST.com_attr}"
			+ ",#{COM_MST.com_form}"
			+ ",#{COM_MST.com_src}"
			+ ",#{COM_MST.dev_fr_dt}"
			+ ",#{COM_MST.dev_to_dt}"
			+ ",#{COM_MST.use_fr_dt}"
			+ ",#{COM_MST.use_to_dt}"
			+ ",#{COM_MST.requester}"
			+ ",#{COM_MST.owner}"
			+ ",#{COM_MST.developer}"
			+ ",#{COM_MST.participant}"
			+ ",#{COM_MST.scenario}"
			+ ")")	
	int insertBody(@Param("COM_MST") ComMst commst);	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM COM_MST")
	@Results(id="ComMstMap", value={
		@Result(property="api_id", column="api_id"),
		@Result(property="reg_dt", column="reg_dt"),
		@Result(property="reg_id", column="reg_id"),
		@Result(property="upt_dt", column="upt_dt"),
		@Result(property="upt_id", column="upt_id"),
		@Result(property="com_nm", column="com_nm"),
		@Result(property="com_cate", column="com_cate"),
		@Result(property="com_attr", column="com_attr"),
		@Result(property="com_form", column="com_form"),
		@Result(property="com_src", column="com_src"),
		@Result(property="dev_fr_dt", column="dev_fr_dt"),
		@Result(property="dev_to_dt", column="dev_to_dt"),
		@Result(property="use_fr_dt", column="use_fr_dt"),
		@Result(property="use_to_dt", column="use_to_dt"),
		@Result(property="requester", column="requester"),
		@Result(property="owner", column="owner"),
		@Result(property="developer", column="developer"),
		@Result(property="participant", column="participant"),
		@Result(property="scenario", column="scenario")
	})
	List<ComMst> getAll();
	
	// one
	@Select("SELECT * FROM COM_MST WHERE com_id=#{com_id}")
	@ResultMap("ComMstMap")
	ComMst getByComId(@Param("com_id") String com_id);	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// Params
	@Update("UPDATE COM_MST SET "
				+ "upt_dt=#{upt_dt}"
				+ ",upt_id=#{upt_id}"
				+ ",com_nm=#{com_nm}"
				+ ",com_cate=#{com_cate}"
				+ ",com_attr=#{com_attr}"
				+ ",com_form=#{com_form}"
				+ ",com_src=#{com_src}"
				+ ",dev_fr_dt=#{dev_fr_dt}"
				+ ",dev_to_dt=#{dev_to_dt}"
				+ ",use_fr_dt=#{use_fr_dt}"
				+ ",use_to_dt=#{use_to_dt}"
				+ ",requester=#{requester}"
				+ ",owner=#{owner}"
				+ ",developer=#{developer}"
				+ ",participant=#{participant}"
				+ ",scenario=#{scenario}"
				+ "WHERE com_id=#{com_id}")
	int updateParam(
			   @Param("com_id") 	  String com_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("com_nm")       String com_nm
			  ,@Param("com_cate")     String com_cate
			  ,@Param("com_attr")     String com_attr
			  ,@Param("com_form")	  String com_form
			  ,@Param("com_src")      String com_src
		 	  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
			  ,@Param("use_fr_dt")	  String use_fr_dt
			  ,@Param("use_to_dt")    String use_to_dt
			  ,@Param("requester")    String requester
			  ,@Param("owner")	      String owner
			  ,@Param("developer")    String developer
			  ,@Param("participant")  String participant
			  ,@Param("scenario")     String scenario
			  );				
	
	// Body
	@Update("UPDATE COM_MST SET "
			+ "upt_dt=#{COM_MST.upt_dt}"
			+ ",upt_id=#{COM_MST.upt_id}"
			+ ",com_nm=#{COM_MST.com_nm}"
			+ ",com_cate=#{COM_MST.com_cate}"
			+ ",com_attr=#{COM_MST.com_attr}"
			+ ",com_form=#{COM_MST.com_form}"
			+ ",res_form=#{COM_MST.res_form}"
			+ ",com_src=#{COM_MST.com_src}"
			+ ",dev_fr_dt=#{COM_MST.dev_fr_dt}"
			+ ",dev_to_dt=#{COM_MST.dev_to_dt}"
			+ ",use_fr_dt=#{COM_MST.use_fr_dt}"
			+ ",use_to_dt=#{COM_MST.use_to_dt}"
			+ ",requester=#{COM_MST.requester}"
			+ ",owner=#{COM_MST.owner}"
			+ ",developer=#{COM_MST.developer}"
			+ ",participant=#{COM_MST.participant}"
			+ ",scenario=#{COM_MST.scenario}"
			+ "WHERE com_id=#{COM_MST.com_id}")	
	int updateBody(@Param("COM_MST") ComMst commst);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM COM_MST WHERE com_id=#{com_id}")
	int delete(@Param("com_id") String com_id);	
	
}