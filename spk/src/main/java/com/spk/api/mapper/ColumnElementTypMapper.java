package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.ColumnElementM;
import com.spk.api.entity.ColumnElementTyp;

@Mapper
public interface ColumnElementTypMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "       element_typ "
		  + "     FROM COL_ELEMENT_TYP "		  
		  + "    WHERE tbl_nm = #{COL_ELEMENT_TYP.tbl_nm} "
		  + "      AND col_nm = #{COL_ELEMENT_TYP.col_nm} "
		  + " ORDER BY sort_seq ")
	@Results(id="ColElementTypMap", value={
		@Result(property="element_typ",          column="element_typ")
	})
	List<ColumnElementTyp> getColumnElementTypList(@Param("COL_ELEMENT_TYP") ColumnElementM columnElementM);	
}