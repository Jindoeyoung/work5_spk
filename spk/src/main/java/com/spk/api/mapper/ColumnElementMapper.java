package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.ColumnElement;
import com.spk.api.entity.ColumnElementM;

@Mapper
public interface ColumnElementMapper {
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST (3개 테이블 전체 Join)
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
	List<ColumnElement> getColumnElementList(@Param("COL_ELEMENT_MST") ColumnElement columnElement);

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT - LIST
	//-------------------------------------------------------------------------------------------------------------------------------------	
	@Select("   SELECT "
		  + "      col_nm "
		  + "      ,col_desc "
		  + "      ,col_auth "
		  + "   FROM COL_ELEMENT_MST "		  
		  + "   WHERE tbl_nm = 'ST_HAKJEOK_M' ")
	@Results(id="ColElementMstMap", value={
		@Result(property="col_nm",         		 column="col_nm"),
		@Result(property="col_desc",             column="col_desc"),
		@Result(property="col_auth",          	 column="col_auth")
	})
	List<ColumnElementM> getColumnElementMstList(@Param("COL_ELEMENT_MST") ColumnElementM columnElementM);
	
}