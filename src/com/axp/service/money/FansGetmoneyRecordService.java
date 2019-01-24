package com.axp.service.money;

import java.util.List;
import java.util.Map;

import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

public interface FansGetmoneyRecordService {
	
	PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage, Integer pageSize);
	PageResult<GetmoneyRecord> payList(Integer currentPage, Integer pageSize,Integer timed);
	GetmoneyRecord findById(Integer Id);
	Map<String, Object>isPay(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
	
	
	Map<String, Object> fansPay(String ids);
	
	
	PageResult<GetmoneyRecord> getFansPayList(Integer currentPage, Integer pageSize);
	Map<String, Object> rejectPay(int id);
	
	/**
	 * 代付查询
	 * @param recordList
	 */
	void payQuery(List<GetmoneyRecord> recordList);
	/**
	 * 代付
	 * @param recordList
	 * @return
	 */
	Map<String, Object> pay(List<GetmoneyRecord> recordList);
	
	/**
	 * 人工支付
	 * @param ids
	 * @return
	 */
	Map<String, Object> artificialPay(String ids);
}
