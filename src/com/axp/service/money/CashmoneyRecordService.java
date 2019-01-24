package com.axp.service.money;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.CashmoneyRecord;
import com.axp.query.PageResult;

public interface CashmoneyRecordService {
	
	PageResult<CashmoneyRecord> getCashMoneyChangeRecord(Integer userId, Integer currentPage, Integer pageSize,HttpServletRequest request);

	/**
	 * 获取收入以及支出金额
	 * @param userId 		用户id值
	 * @param type				状态
	 * @return
	 */
	Object[] getTotalIncomeAndExpend(Integer userId,Integer type);
	
	
	
	/**
	 * 统计每个用户的消费金额,
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<CashmoneyRecord> getCashMoneyRecord(Integer userId,Integer currentPage,Integer pageSize,HttpServletRequest request);
	/**
	 * 查询详细的用的现金记录
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<CashmoneyRecord> getOneCashMoneyRecord(Integer userId,Integer currentPage,Integer pageSize,HttpServletRequest request);
	
}
