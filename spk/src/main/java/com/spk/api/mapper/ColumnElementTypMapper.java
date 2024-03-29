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
	// SELECT - LIST 단건(테스트) 용
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "        tbl_nm "	
		  + "       ,col_nm "
		  + "       ,element_typ "
		  + "     FROM COL_ELEMENT_TYP "		  
		  + "    WHERE tbl_nm = #{COL_ELEMENT_TYP.tbl_nm} "
		  + "      AND col_nm = #{COL_ELEMENT_TYP.col_nm} "
		  + " ORDER BY sort_seq ")
	@Results(id="OneColElementTypMap", value={
		@Result(property="tbl_nm",          column="tbl_nm"),	
		@Result(property="col_nm",          column="col_nm"),	
		@Result(property="element_typ",     column="element_typ")
	})
	List<ColumnElementTyp> getOneColumnElementTypList(@Param("COL_ELEMENT_TYP") ColumnElementM columnElementM);
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST (멀티용)
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "        tbl_nm "	
		  + "       ,col_nm "
		  + "       ,element_typ "
		  + "     FROM COL_ELEMENT_TYP "		  
		  + "    WHERE tbl_nm = #{tbl_nm} "
		  + "      AND col_nm = #{col_nm} "
		  + " ORDER BY sort_seq ")
	@Results(id="ColElementTypMap", value={
		@Result(property="tbl_nm",          column="tbl_nm"),	
		@Result(property="col_nm",          column="col_nm"),	
		@Result(property="element_typ",     column="element_typ")
	})
	List<ColumnElementTyp> getColumnElementTypList(@Param("tbl_nm") String tbl_nm, @Param("col_nm") String col_nm);	
}