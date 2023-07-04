package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

//import com.spk.api.entity.ColumnElementM;
//import com.spk.api.entity.ColumnElementTyp;
import com.spk.api.entity.ColumnElementVal;

@Mapper
public interface ColumnElementValMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "      element_val "
		  + "   FROM COL_ELEMENT_VAL "		  
		  + "    WHERE tbl_nm 	   = #{tbl_nm} "
		  + "      AND col_nm 	   = #{col_nm} "
		  + "      AND element_typ = #{element_typ} "
		  + " ORDER BY sort_seq ")
	@Results(id="ColElementTypMap", value={
		@Result(property="element_val",          column="element_val")
	})
	List<ColumnElementVal> getColumnElementValList(@Param("tbl_nm") String tbl_nm, @Param("col_nm") String col_nm, @Param("element_typ") String element_typ);		  
}