package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ISecKillTimesDao;
import com.axp.model.CashshopTimes;

@SuppressWarnings("unchecked")
@Repository
public class SecKillTimesDaoImpl extends BaseDaoImpl<CashshopTimes> implements ISecKillTimesDao {
	
	@Override
	public List<CashshopTimes> getTimesList(Integer adminUserId){
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from CashshopTimes as model where model.isValid = true and model.user.id = :adminUserId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("adminUserId", adminUserId);
		return queryObject.list();
	}
	
	@Override
	public Integer getTimesCount(Integer adminUserId){
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select count(model.id) from CashshopTimes as model where model.isValid = true and model.user.id = :adminUserId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("adminUserId", adminUserId);
		return Integer.parseInt(queryObject.uniqueResult().toString());
	}
	
}