/**
 * 2018-11-14
 * Administrator
 */
package com.axp.service.money.impl;

import org.springframework.stereotype.Service;

import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.query.PageResult;
import com.axp.service.money.AdminUserRedpaperService;
import com.axp.service.money.AdminuserCashpointRecordService;
import com.axp.service.system.impl.BaseServiceImpl;

/**
 * @author da
 * @data 2018-11-14下午6:02:44
 */
@Service
public class AdminuserCashpointRecordServiceImpl extends BaseServiceImpl implements AdminuserCashpointRecordService{

	@Override
	public PageResult<AdminuserCashpointRecord> reDepositList(Integer currentPage,
			Integer pageSize,String state,String sellerName) {
		
		return adminuserCashpointRecordDAO.findReDepositList(currentPage,pageSize,state,sellerName);
	}
	


}
