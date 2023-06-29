package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.ColumnElementM;

@Mapper
public interface ColumnElementMMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - ONE
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "        tbl_nm "
	      + "       ,col_nm "
		  + "       ,col_desc "
		  + "       ,col_auth "
		  + "     FROM COL_ELEMENT_MST "		  
		  + "    WHERE tbl_nm = #{COL_ELEMENT_MST.tbl_nm} "
		  + "      AND col_nm = #{COL_ELEMENT_MST.col_nm} ")
	@Results(id="ColElementMstMap", value={
		@Result(property="tbl_nm",         		 column="tbl_nm"),
		@Result(property="col_nm",         		 column="col_nm"),
		@Result(property="col_desc",             column="col_desc"),
		@Result(property="col_auth",          	 column="col_auth")
	})
	ColumnElementM getColumnElementMstList(@Param("COL_ELEMENT_MST") ColumnElementM columnElementM);
	
}