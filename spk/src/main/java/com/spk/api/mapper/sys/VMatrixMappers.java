package com.spk.api.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.sys.VMatrix;

@Mapper
public interface VMatrixMappers {
    //============================================================
    //< SELECT (List)
    //============================================================	
	List<VMatrix> getMatrixList(
		@Param("gubun") String gubun
	);
	
//	List<ColumnElementTypeEntity> getTypeList(
//		 @Param("table") String table
//		,@Param("column") String column
//	);
//
//	List<ColumnElementValueEntity> getValueList(
//		 @Param("table") String table
//		,@Param("column") String column
//		,@Param("type") String type
//	);
}
