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

//import com.spk.api.controller.string;
import com.spk.api.entity.ApiMst;

@Mapper
public interface ApiMstMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (get)
	//-------------------------------------------------------------------------------------------------------------------------------------
//	@Insert("INSERT INTO API_MST(api_id, api_nm, param) VALUES(#{api_id}, #{api_nm}, #{param})")
	@Insert("INSERT INTO API_MST("
				+ " api_id"			//API_ID
				+ ",reg_dt"			//등록일
				+ ",reg_id"			//등록자
				+ ",upt_dt"			//수정일
				+ ",upt_id"			//수정자
				+ ",api_nm"			//API 명
				+ ",api_cate"		//카테고리
				+ ",version"		//버전
				+ ",param"			//파라미터
				+ ",res_form"		//응답포맷		
				+ ",rtn_type"		//응답타입
				+ ",method"			//메소드
				+ ",url"			//URL
				+ ",proc_state"		//진행상태
				+ ",proc_rate"		//진행률
				+ ",dev_fr_dt"		//개발기간(시작)
				+ ",dev_to_dt"		//개발기간(종료)
				+ ",requester"		//사용자(요청자)
				+ ",owner"			//소유자(관리자)
				+ ",developer"		//개발자
				+ ",participants"	//참여자
				+ ",scenario)"		//시나리오
				+ "   VALUES"
				+ " ("
				+ " #{api_id}"
				+ ",#{reg_dt}"
				+ ",#{reg_id}"
				+ ",#{upt_dt}"
				+ ",#{upt_id}"
				+ ",#{api_nm}"
				+ ",#{api_cate}"
				+ ",#{version}"
				+ ",#{param}"
				+ ",#{res_form}"
				+ ",#{rtn_type}"
				+ ",#{method}"
				+ ",#{url}"
				+ ",#{proc_state}"
				+ ",#{proc_rate}"
				+ ",#{dev_fr_dt}"
				+ ",#{dev_to_dt}"
				+ ",#{requester}"
				+ ",#{owner}"
				+ ",#{developer}"
				+ ",#{participants}"
				+ ",#{scenario}"
				+ ")")
	int insertParam(@Param("api_id") 	  String api_id
			  ,@Param("reg_dt")       String reg_dt
			  ,@Param("reg_id")       String reg_id
			  ,@Param("upt_dt")       String upt_dt
			  ,@Param("upt_id")       String upt_id
			  ,@Param("api_nm")       String api_nm
			  ,@Param("api_cate")     String api_cate
			  ,@Param("version")      String version
			  ,@Param("param")	      String param
			  ,@Param("res_form")     String res_form
		 	  ,@Param("rtn_type")     String rtn_type
			  ,@Param("method")       String method
			  ,@Param("url")	      String url
			  ,@Param("proc_state")   String proc_state
			  ,@Param("proc_rate")    String proc_rate
			  ,@Param("dev_fr_dt")    String dev_fr_dt
			  ,@Param("dev_to_dt")    String dev_to_dt
			  ,@Param("requester")    String requester
			  ,@Param("owner")	      String owner
			  ,@Param("developer")    String developer
			  ,@Param("participants") String participants
			  ,@Param("scenario")     String scenario);			
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// INSERT (Body)
	//-------------------------------------------------------------------------------------------------------------------------------------	
//	@Insert("INSERT INTO API_MST(api_id, api_nm, param) VALUES(#{API_MST.api_id}, #{API_MST.api_nm}, #{API_MST.param})")
//	int insert(@Param("API_MST") ApiMst apimst);
	
	@Insert("INSERT INTO API_MST("
			+ " api_id"			//API_ID
			+ ",reg_dt"			//등록일
			+ ",reg_id"			//등록자
			+ ",upt_dt"			//수정일
			+ ",upt_id"			//수정자
			+ ",api_nm"			//API 명
			+ ",api_cate"		//카테고리
			+ ",version"		//버전
			+ ",param"			//파라미터
			+ ",res_form"		//응답포맷		
			+ ",rtn_type"		//응답타입
			+ ",method"			//메소드
			+ ",url"			//URL
			+ ",proc_state"		//진행상태
			+ ",proc_rate"		//진행률
			+ ",dev_fr_dt"		//개발기간(시작)
			+ ",dev_to_dt"		//개발기간(종료)
			+ ",requester"		//사용자(요청자)
			+ ",owner"			//소유자(관리자)
			+ ",developer"		//개발자
			+ ",participants"	//참여자
			+ ",scenario)"		//시나리오
			+ "   VALUES"
			+ " ("
			+ " #{API_MST.api_id}"
			+ ",#{API_MST.reg_dt}"
			+ ",#{API_MST.reg_id}"
			+ ",#{API_MST.upt_dt}"
			+ ",#{API_MST.upt_id}"
			+ ",#{API_MST.api_nm}"
			+ ",#{API_MST.api_cate}"
			+ ",#{API_MST.version}"
			+ ",#{API_MST.param}"
			+ ",#{API_MST.res_form}"
			+ ",#{API_MST.rtn_type}"
			+ ",#{API_MST.method}"
			+ ",#{API_MST.url}"
			+ ",#{API_MST.proc_state}"
			+ ",#{API_MST.proc_rate}"
			+ ",#{API_MST.dev_fr_dt}"
			+ ",#{API_MST.dev_to_dt}"
			+ ",#{API_MST.requester}"
			+ ",#{API_MST.owner}"
			+ ",#{API_MST.developer}"
			+ ",#{API_MST.participants}"
			+ ",#{API_MST.scenario}"
			+ ")")	
	int insertBody(@Param("API_MST") ApiMst apimst);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT * FROM API_MST")
	@Results(id="ApiMstMap", value={
		@Result(property="api_id", column="api_id"),
		@Result(property="api_nm", column="api_nm"),
		@Result(property="param", column="param"),
	})
	List<ApiMst> getAll();
	
	// one
	@Select("SELECT * FROM API_MST WHERE api_id=#{api_id}")
	@ResultMap("ApiMstMap")
	ApiMst getByApiId(@Param("api_id") String api_id);
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// UPDATE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	// get
	@Update("UPDATE API_MST SET api_nm=#{api_nm}, param=#{param} WHERE api_id=#{api_id}")
	int update(@Param("api_id") String api_id, @Param("api_nm") String api_nm, @Param("param") String param);
	
//	// Body
//	@Update("UPDATE API_MST SET api_nm=#{API_MST.api_nm}, param=#{API_MST.param} WHERE api_id=#{API_MST.api_id}")
//	int update(@Param("API_MST") ApiMst apimst);

	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// DELETE
	//-------------------------------------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM API_MST WHERE api_id=#{api_id}")
	int delete(@Param("api_id") String api_id);
	
}
