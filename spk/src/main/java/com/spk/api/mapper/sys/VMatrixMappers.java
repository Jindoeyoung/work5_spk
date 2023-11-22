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
	
    //============================================================
    //< SELECT (List) - 전체 spike_id 리스트 조회
    //============================================================	
	List<VMatrix> getAllSpikeIdList(
	);

    //============================================================
    //< SELECT (List) - spike_id 별 처리할 위젯 리스트 조회
    //============================================================	
	List<VMatrix> getSpikeIdMatrixList(
		 @Param("gubun") String gubun
		,@Param("spike_id") String spike_id
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
