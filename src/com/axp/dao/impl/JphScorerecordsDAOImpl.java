package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.axp.dao.JphScorerecordsDAO;
import com.axp.model.JphScorerecords;
import com.axp.model.Users;



@Repository
public class JphScorerecordsDAOImpl extends BaseDaoImpl<JphScorerecords> implements JphScorerecordsDAO {
	
	private static Map<Integer  , String > descripConfig; 
	private static Map<Integer  , String > titleDescripConfig; //标题
	private static Map<Integer  , Integer > realType; 
	
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
		if(realType == null){
			realType = new HashMap<Integer, Integer>();
			realType.put(BUY, 14);
			realType.put(BACK, 14);
			realType.put(CASH, 14);
		}
	}
	
	@Override
	public Object getLastRecordParameterInType(String parameter, Integer userId, Integer type){
		Query query = sessionFactory.getCurrentSession().createQuery("select "+parameter+" from Scorerecords where users.id = :userId and type = :type order by id desc");
		query.setParameter("userId", userId);
		query.setParameter("type", type);
		query.setMaxResults(1);
		query.setFirstResult(0);
		return query.uniqueResult();
	}
	
	@Override
	public JphScorerecords updateRecord(Users user,Integer updateValue,Integer type){
		if(updateValue==null||updateValue==0){
			return null;
		}
		JphScorerecords record = new JphScorerecords();
		record.setScore(updateValue);
		record.setAfterScore(user.getScore()+updateValue);
		record.setCreatetime(new Timestamp(System.currentTimeMillis()));
		record.setIsvalid(true);
		record.setRemark(descripConfig.get(type));
		record.setScoretype(titleDescripConfig.get(type));
		record.setType(realType.get(type));
		record.setUsers(user);
		record.setAdminuserId(1);
		super.save(record);
		return record;
	}
	
	@Override
	public JphScorerecords updateRecord(Users user,Integer updateValue){
		if(updateValue==null||updateValue==0){
			return null;
		}
		JphScorerecords record = new JphScorerecords();
		record.setScore(updateValue);
		record.setBeforeScore(user.getScore());
		record.setAfterScore(user.getScore()+updateValue);
		record.setCreatetime(new Timestamp(System.currentTimeMillis()));
		record.setIsvalid(true);
		record.setRemark("购买商品扣除积分");
		record.setScoretype("购物");
		record.setType(14);
		record.setUsers(user);
		record.setAdminuserId(1);
		super.save(record);
		return record;
	}

	
}