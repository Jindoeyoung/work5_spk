package com.spk.api.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.system.UserInfo;

@Mapper
public interface UserLoginMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - Login 사용자
	//-------------------------------------------------------------------------------------------------------------------------------------

	@Select("SELECT "
			+ "       user_id "
			+ "      ,user_nm "
			+ "      ,user_grp_cd "
			+ "  FROM USER_MST "
			+ " WHERE sabun=#{sabun} "
			+ "   AND router_id=#{router_id} ")
	@Results(id="ComScenMap", value={
			@Result(property="user_id", column="user_id"),
			@Result(property="user_nm", column="user_nm"),
			@Result(property="user_grp_cd", column="user_grp_cd"),
		})
	UserInfo getBySabun(
			 @Param("router_id") String router_id,
			 @Param("sabun") String sabun
			);
}
