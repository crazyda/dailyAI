package com.axp.dao;


import com.axp.model.AdminuserWithdrawals;
import com.axp.query.PageResult;




public interface AdminuserWithdrawalsDao extends IBaseDao<AdminuserWithdrawals> {
	PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage, Integer pageSize,Integer state);
	
}