package com.axp.service.money;

import java.util.Map;


import com.axp.model.AdminuserWithdrawals;
import com.axp.query.PageResult;

public interface WithdrawReviewService {
	PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage, Integer pageSize, Integer state);
	
	AdminuserWithdrawals findById(Integer Id);
	
	Map<String, Object> doCheck(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
	
	
	/*
	 * 对商家提现进行是否支付操作
	 * */
	 Map<String, Object> isPay(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
	
}
