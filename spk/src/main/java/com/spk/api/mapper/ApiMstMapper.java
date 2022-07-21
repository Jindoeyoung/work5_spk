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
	// get
	@Insert("INSERT INTO API_MST(api_id, api_nm, param) VALUES(#{api_id}, #{api_nm}, #{param})")
	int insert(@Param("api_id") String api_id, @Param("api_nm") String api_nm, @Param("param") String param);	
	
//	// Body
//	@Insert("INSERT INTO API_MST(api_id, api_nm, param) VALUES(#{API_MST.api_id}, #{API_MST.api_nm}, #{API_MST.param})")
//	int insert(@Param("API_MST") ApiMst apimst);
	
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
