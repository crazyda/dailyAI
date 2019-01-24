package com.axp.dao;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.axp.model.AdminuserWithdrawals;
import com.axp.model.GetmoneyRecord;
import com.axp.model.UserLoginRecord;
import com.axp.query.PageResult;




public interface SellerWithdrawalsDao extends IBaseDao<AdminuserWithdrawals> {
	PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage, Integer pageSize,Integer state);
	
	
	/**
	 * 商家待支付列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	 PageResult<AdminuserWithdrawals> getSellerPayList(Integer currentPage,
			Integer pageSize) ;
	
	 
	 /**
	  * 查询商家提现已支付的列表
	  * @return
	  */
	 List<AdminuserWithdrawals> getSellerPayListByStatus();
			
		
			/**
			 * 查询所有未审核的提现
			 * @param currentPage
			 * @param pageSize
			 * @param state
			 * @return
			 */
	 PageResult<AdminuserWithdrawals> getCheckListByStatus(Integer currentPage,
				Integer pageSize,Integer state);
	
	 
	 
	 
	 Integer getSum (HttpServletRequest request);
	 
	 List<AdminuserWithdrawals> getSellerWithdrawalList(HttpServletRequest request,Integer start,Integer end);
	 
	 List<GetmoneyRecord> getUserWithdrawalList(HttpServletRequest request,Integer start,Integer end);
		
}