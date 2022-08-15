package com.spk.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.spk.api.entity.PjtCom;

@Mapper
public interface PjtComMapper {

	//-------------------------------------------------------------------------------------------------------------------------------------
	// SELECT
	//-------------------------------------------------------------------------------------------------------------------------------------
	// list
	@Select("SELECT M.com_id"
			+ ",M.com_nm"
			+ ",M.dev_fr_dt"
			+ ",M.dev_to_dt"
			+ ",M.requester"
			+ ",M.owner"
			+ ",M.participant"
			+ ",M.scenario"
			+ ",(SELECT COUNT(1) FROM COM_SCEN S WHERE M.com_id=S.com_id) AS scen_cnt "
			+" ,(CASE when isnull(M.REQUESTER) then 0 else 1 end) + "
			+"  (CASE when isnull(M.OWNER) then 0 else 1 end) + "
			+"  (CASE when isnull(M.PARTICIPANT) then 0 else 1 end) + "
			+"  (CASE when isnull(M.SCENARIO) then 0 else 1 end) as partici_cnt "
			+ ",M.proc_state "
			+ ",M.proc_rate "			
			+ "FROM COM_MST M")
	@Results(id="PjtComMap", value={
		@Result(property="com_id", column="com_id"),
		@Result(property="com_nm", column="com_nm"),
		@Result(property="dev_fr_dt", column="dev_fr_dt"),
		@Result(property="dev_to_dt", column="dev_to_dt"),
		@Result(property="requester", column="requester"),
		@Result(property="owner", column="owner"),
		@Result(property="participant", column="participant"),
		@Result(property="scenario", column="scenario"),
		@Result(property="scen_cnt", column="scen_cnt"),
		@Result(property="partici_cnt", column="partici_cnt"),
		@Result(property="proc_state", column="proc_state"),
		@Result(property="proc_rate", column="proc_rate")
	})
	List<PjtCom> getAll();	
}
