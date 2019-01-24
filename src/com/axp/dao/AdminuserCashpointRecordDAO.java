package com.axp.dao;

import java.util.List;

import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.SellerMoneyRecord;
import com.axp.query.PageResult;




/**
 * A data access object (DAO) providing persistence and search support for
 * AdminuserCashpointRecord entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.com.wehua.db.model.AdminuserCashpointRecord
 * @author MyEclipse Persistence Tools
 */
public interface AdminuserCashpointRecordDAO extends IBaseDao<AdminuserCashpointRecord> {
	PageResult<AdminuserCashpointRecord> getMoneyChangeRecord(Integer Id, Integer currentPage, Integer pageSize);

	/**
	 * 获取收入以及支出金额
	 * @param adminUserId 		用户id值
	 * @param type				状态
	 * @return
	 */
	Object[] getTotalIncomeAndExpend(Integer adminUserId,Integer type);
	
	void delRecordByOrderItemId(ReGoodsorderItem item);
	
	
	/**
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageResult<AdminuserCashpointRecord> findReDepositList(Integer currentPage,
			Integer pageSize,String state,String sellerName);
	

	
}