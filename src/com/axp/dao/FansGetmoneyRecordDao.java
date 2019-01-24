package com.axp.dao;

import java.util.List;

import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

public interface FansGetmoneyRecordDao extends IBaseDao<GetmoneyRecord>{
	 PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage, Integer pageSize);
	 PageResult<GetmoneyRecord> getPaycompleteList(Integer currentPage, Integer pageSize);
	 PageResult<GetmoneyRecord> getFansPayList(Integer currentPage, Integer pageSize);
	 
	 List<GetmoneyRecord> getMoenyRecordListByStatus();
	 
}
