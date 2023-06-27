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

//	@Select("SELECT "
//			+ "       user_id "
//			+ "      ,user_nm "
//			+ "      ,user_grp_cd "
//			+ "  FROM USER_MST "
//			+ " WHERE sabun=#{sabun} "
//			+ "   AND router_id=#{router_id} ")
//	@Results(id="ComScenMap", value={
//			@Result(property="user_id", column="user_id"),
//			@Result(property="user_nm", column="user_nm"),
//			@Result(property="user_grp_cd", column="user_grp_cd"),
//		})
	@Select("SELECT "
			+ "       UM.user_id "
			+ "      ,UM.user_nm "
			+ "      ,UM.user_grp_cd "
			+ "      ,BU.background_id "
			+ "      ,BM.image_src "
			+ "  FROM USER_MST UM LEFT OUTER JOIN BACKGROUND_USER BU ON (UM.USER_ID = BU.SPIKE_ID) "
			+ "                   LEFT OUTER JOIN BACKGROUND_MST BM ON (BU.BACKGROUND_ID = BM.BACKGROUND_ID) "
			+ " WHERE UM.SABUN=#{sabun} "
			+ "   AND UM.ROUTER_ID=#{router_id} ")
	@Results(id="UserInfoMap", value={
			@Result(property="user_id", 		column="user_id"),
			@Result(property="user_nm", 		column="user_nm"),
			@Result(property="user_grp_cd", 	column="user_grp_cd"),
			@Result(property="background_id", 	column="background_id"),
			@Result(property="image_src", 		column="image_src")
		})	
	UserInfo getBySabun(
			 @Param("router_id") String router_id,
			 @Param("sabun") String sabun
			);
}
