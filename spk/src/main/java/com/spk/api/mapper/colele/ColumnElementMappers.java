package com.spk.api.mapper.colele;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.colele.ColumnElementMasterEntity;
import com.spk.api.entity.colele.ColumnElementTypeEntity;
import com.spk.api.entity.colele.ColumnElementValueEntity;

@Mapper
public interface ColumnElementMappers {
    //============================================================
    //< SELECT (List)
    //============================================================	
	List<ColumnElementMasterEntity> getMasterList(
		@Param("table") String table
	);
	
	List<ColumnElementTypeEntity> getTypeList(
		 @Param("table") String table
		,@Param("column") String column
	);

	List<ColumnElementValueEntity> getValueList(
		 @Param("table") String table
		,@Param("column") String column
		,@Param("type") String type
	);
}
