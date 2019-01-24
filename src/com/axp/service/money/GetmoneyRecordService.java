package com.axp.service.money;

import java.util.Map;

import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

public interface GetmoneyRecordService {
	
	PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage, Integer pageSize);
	PageResult<GetmoneyRecord> getPaycompleteList(Integer currentPage, Integer pageSize);
	Map<String, Object> doPay(Integer recordId, Map<String, Object> returnMap);
	GetmoneyRecord findById(Integer Id);
	Map<String, Object>isPay(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
}
