package com.axp.dao;

import com.axp.model.FreeVoucherRecord;

public interface FreeVoucherRecordDAO extends IBaseDao<FreeVoucherRecord> {

	Integer getUsersValidFVCount(Integer userId);
	
}