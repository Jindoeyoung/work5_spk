package com.spk.api.service.colele;

//import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.colele.ColumnElementMasterEntity;
//import com.spk.api.entity.colele.ColumnElementTypeEntity;
//import com.spk.api.entity.colele.ColumnElementValueEntity;

/**
 * <p>Service</p>
 */
public interface ColumnElementService {
    //============================================================
    //< SELECT (List)
    //============================================================
	public String getColumnElementList(@Param("COL_ELEMENT_MST") ColumnElementMasterEntity colEleMasterEntity) throws Exception;
//	public String getMasterList(@Param("COL_ELEMENT_MST") String tableName) throws Exception;
//	public String getTypeList(@Param("ColEleTypeEntity") ColEleTypeEntity colEleTypeEntity) throws Exception;
//	public String getValueList(@Param("COL_ELEMENT_MST") ColEleValueEntity ColEleValueEntity) throws Exception;
}
