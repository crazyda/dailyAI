package com.axp.dao;

import com.axp.model.JphScorerecords;
import com.axp.model.Users;



public interface JphScorerecordsDAO extends IBaseDao<JphScorerecords> {

	public static final int BUY = 0x21;//购物
	public static final int BACK = 0x22;//退货
	public static final int CASH = 0x23;//提现
	
	Object getLastRecordParameterInType(String parameter, Integer userId,
			Integer type);

	JphScorerecords updateRecord(Users user, Integer updateMoney, Integer type);

	JphScorerecords updateRecord(Users user,Integer updateValue);
}