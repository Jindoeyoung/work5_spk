package com.spk.api.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.system.UserInfo;

@Mapper
public interface DataLoadMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - Login 사용자
	//-------------------------------------------------------------------------------------------------------------------------------------

	@Select("SELECT "
			+ "       user_id "
			+ "      ,user_nm "
			+ "      ,sabun "
			+ "  FROM USER_MST "
			+ " WHERE sabun BETWEEN #{from_sabun} and #{to_sabun}")
	@Results(id="UserIdMap", value={
			@Result(property="user_id", column="user_id"),
			@Result(property="user_nm", column="user_nm"),
			@Result(property="sabun", column="sabun"),
		})
	List<UserInfo> getBySabunRange(
			 @Param("from_sabun") String from_sabun
			,@Param("to_sabun") String to_sabun
			);
}
