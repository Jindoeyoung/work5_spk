package com.spk.api.service.colele;

//import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spk.api.entity.colele.RequestTablesEntity;

/**
 * <p>Service</p>
 */
public interface ColumnElementService {
    //============================================================
    //< SELECT (List)
    //============================================================
	public String getColumnElementList(@Param("COL_ELEMENT_MST") RequestTablesEntity requestTablesEntity) throws Exception;
}
