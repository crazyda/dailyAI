package com.axp.service.money;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;

public interface WithdrawDatalogService {
	PageResult<AdminuserWithdrawalsDataLog> getCheckDataList(Integer currentPage, Integer pageSize);
	AdminuserWithdrawalsDataLog findById(Integer LogId);
	Map<String, Object> doReview(Map<String, Object> returnMap, Integer id, Integer status,HttpServletRequest request) throws Exception;
	
	Map<String, Object> reviewPage (Map<String, Object> returnMap, Integer id, Integer status,HttpServletRequest request);
	
}
