package com.axp.dao;

import com.axp.model.AdminuserWithdrawalsBank;





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
public interface AdminuserWithdrawalsBankDao extends IBaseDao<AdminuserWithdrawalsBank> {
	
	void updateIsDefueltByAdminuserId(Integer adminuserId);
	void updateIsDefueltByAdminuserId(Integer adminuserId ,Integer bankId);
	
}