package com.axp.service.money.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.axp.model.CashmoneyRecord;
import com.axp.query.PageResult;
import com.axp.service.money.CashmoneyRecordService;
import com.axp.service.system.impl.BaseServiceImpl;
@Service
public class CashmoneyRecordServiceImpl extends BaseServiceImpl implements CashmoneyRecordService{

	@Override
	public PageResult<CashmoneyRecord> getCashMoneyChangeRecord(Integer userId,
			Integer currentPage, Integer pageSize,HttpServletRequest request) {
		return iCashmoneyRecordDao.getCashMoneyChangeRecord(userId, currentPage, pageSize,request);
	}

	@Override
	public Object[] getTotalIncomeAndExpend(Integer userId, Integer type) {
		return iCashmoneyRecordDao.getTotalIncomeAndExpend(userId, type);
	}

	@Override
	public PageResult<CashmoneyRecord> getCashMoneyRecord(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return iCashmoneyRecordDao.getCashMoneyRecord(userId, currentPage, pageSize, request);
	}

	@Override
	public PageResult<CashmoneyRecord> getOneCashMoneyRecord(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		
		return iCashmoneyRecordDao.getOneCashMoneyRecord(userId, currentPage, pageSize, request);
	}


}
