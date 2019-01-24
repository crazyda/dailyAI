package com.axp.dao;

import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;

public interface AdminuserWithdrawalsDataLogDao extends IBaseDao<AdminuserWithdrawalsDataLog> {
	PageResult<AdminuserWithdrawalsDataLog> getReviewDataList(Integer currentPage, Integer pageSize);
	
	
}