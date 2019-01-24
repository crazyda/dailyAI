package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.axp.dao.ICashpointsRecordDao;
import com.axp.model.CashpointsRecord;
import com.axp.model.Users;
@Repository
public class CashpointsRecordDaoImpl extends BaseDaoImpl<CashpointsRecord> implements ICashpointsRecordDao{
	private static Map<Integer  , String > descripConfig; 
	private static Map<Integer  , String > titleDescripConfig; //标题
	
	@PostConstruct
	private void init() {
		if(descripConfig==null){
			descripConfig = new HashMap<Integer, String>();
			descripConfig.put(BACK, "退货返还现金");
			descripConfig.put(BUY, "购买商品扣除钱包余额");
			descripConfig.put(CASH, "提现扣除钱包余额");
		}
		if(titleDescripConfig == null){
			titleDescripConfig = new HashMap<Integer, String>();
			titleDescripConfig.put(BUY, "购物");
			titleDescripConfig.put(BACK, "退货");
			titleDescripConfig.put(CASH, "提现");
		}
	}
	@Override
	public CashpointsRecord updateRecord(Users user,Double updateValue,Integer type){
		if(updateValue==null||updateValue==0){
			return null;
		}
		CashpointsRecord record = new CashpointsRecord();
		record.setScore(updateValue);
		record.setAfterScore(user.getCashPoints()+updateValue);
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		record.setIsValid(true);
		record.setRemark(descripConfig.get(type));
		record.setType(type);
		record.setUsers(user);
		super.save(record);
		return record;
	}

}
