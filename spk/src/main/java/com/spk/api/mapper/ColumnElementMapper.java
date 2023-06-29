package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.ColumnElement;

@Mapper
public interface ColumnElementMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "      em.col_nm "
		  + "      ,em.col_desc "
		  + "      ,et.element_typ "
		  + "      ,ev.element_val "
		  + "      ,em.col_auth "
		  + "   FROM COL_ELEMENT_MST em LEFT OUTER JOIN COL_ELEMENT_TYP et ON (em.tbl_nm = et.tbl_nm AND em.col_nm = et.col_nm) "
		  + "                            LEFT OUTER JOIN COL_ELEMENT_VAL ev ON (et.tbl_nm = ev.tbl_nm AND et.col_nm = ev.col_nm AND et.element_typ = ev.element_typ) "		  
		  + "   WHERE em.tbl_nm = 'ST_HAKJEOK_M' ")
	@Results(id="ColElementMap", value={
		@Result(property="col_nm",         		 column="col_nm"),
		@Result(property="col_desc",             column="col_desc"),
		@Result(property="element_typ",          column="element_typ"),
		@Result(property="element_val",          column="element_val"),
		@Result(property="col_auth",          	 column="col_auth")
	})
	List<ColumnElement> getColumnElementList(@Param("BACKGROUND_MST") ColumnElement columnElement);
}
