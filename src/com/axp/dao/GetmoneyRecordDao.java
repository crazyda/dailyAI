package com.axp.dao;

import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

public interface GetmoneyRecordDao extends IBaseDao<GetmoneyRecord>{
	 PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage, Integer pageSize);
	 PageResult<GetmoneyRecord> getPaycompleteList(Integer currentPage, Integer pageSize);
}
