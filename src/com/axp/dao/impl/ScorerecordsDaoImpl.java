package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ScorerecordsDAO;
import com.axp.model.Scorerecords;
import com.axp.model.Users;
import com.axp.query.PageResult;

@Repository
public class ScorerecordsDaoImpl extends BaseDaoImpl<Scorerecords> implements ScorerecordsDAO{
	private static Map<Integer  , String > descripConfig; 
	private static Map<Integer  , String > titleDescripConfig; //标题
	private static Map<Integer  , Integer > realType; 
	
	@PostConstruct
	private void init() {
		if(descripConfig==null){
			descripConfig = new HashMap<Integer, String>();
			descripConfig.put(BACK, "退货返还现金");
			descripConfig.put(BUY, "兑换商品扣除积分");
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
	public Scorerecords updateRecord(Users user,Integer updateValue,Integer type){
		if(updateValue==null||updateValue==0){
			return null;
		}
		Scorerecords record = new Scorerecords();
		record.setScore(updateValue);
		record.setAfterScore(user.getScore()+updateValue);
		record.setCreatetime(new Timestamp(System.currentTimeMillis()));
		record.setIsvalid(true);
		record.setRemark(descripConfig.get(type)+updateValue);
		record.setScoretype(titleDescripConfig.get(type));
		record.setType(realType.get(type));
		record.setUsers(user);
		record.setAdminuserId(1);
		super.save(record);
		return record;
	}
	
	@Override
	public Scorerecords updateRecord(Users user,Integer updateValue,Integer type,String remark){
		if(updateValue==null||updateValue==0){
			return null;
		}
		Scorerecords record = new Scorerecords();
		record.setScore(updateValue);
		record.setAfterScore(user.getScore()+updateValue);
		record.setCreatetime(new Timestamp(System.currentTimeMillis()));
		record.setIsvalid(true);
		record.setRemark(remark);
		record.setScoretype(titleDescripConfig.get(type));
		record.setType(realType.get(type));
		record.setUsers(user);
		record.setAdminuserId(1);
		super.save(record);
		return record;
	}
	/**
	 * 查询粉丝积分详情 da
	 */
	@Override
	public PageResult<Scorerecords> getScoreRecordsList(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		String type = request.getParameter("type");
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sb = new StringBuffer();
		String queryCount="";
		String queryList="";
		if(StringUtils.isNotBlank(type) && !type.equals("请选择")){
			if("1".equals(type)){
				sb.append(" and remark like'%"+"邀请"+"%'");
			}else if("2".equals(type)){
				sb.append(" and remark like'%"+"解锁"+"%'");
			}else if("3".equals(type)){
				sb.append(" and remark like'%"+"访问"+"%'");
			}else if("4".equals(type)){
				sb.append(" and remark like'%"+"注册"+"%'");
			}else if("5".equals(type)){
				sb.append(" and remark like'%"+"分享"+"%'");
			}else if("6".equals(type)){
				sb.append(" and remark like'%"+"补充"+"%'");
			}else if("7".equals(type)){
				sb.append(" and remark like'%"+"通话"+"%'");
			}else if("8".equals(type)){
				sb.append(" and remark like'%"+"广告"+"%'");
			}else if("9".equals(type)){
				sb.append(" and remark like'%"+"换货"+"%'");
			}
			else if("-1".equals(type)){
				sb.append(" ");
			}
			queryCount = "select count(r) from Scorerecords r where r.isvalid =1 and r.users.id="+userId+sb;
			queryList = "from Scorerecords r where r.isvalid =1 and r.users.id="+userId+sb+"order by r.id desc";
		}else{
			queryCount = "select count(r) from Scorerecords r where r.isvalid =1 and r.users.id="+userId;
			queryList = "from Scorerecords r where r.isvalid =1 and r.users.id="+userId+"order by r.id desc";
		
		}
		Long count = (Long) session.createQuery(queryCount).uniqueResult();
		List<Scorerecords> list = session.createQuery(queryList).setFirstResult((currentPage -1)* pageSize).setMaxResults(pageSize).list();
		
		return new PageResult<>(Integer.parseInt(count.toString()),pageSize,currentPage,list);
	}
}
