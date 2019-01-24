package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.IAdvertypeDao;
import com.axp.model.Advertype;

@SuppressWarnings("unchecked")
@Repository
public class AdvertypeDaoImpl extends BaseDaoImpl<Advertype> implements IAdvertypeDao {
	
	@Override
	public List<Advertype> getListByType(Integer type){
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Advertype where type = :type and isvalid = true order by id desc";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("type", type);
		return queryObject.list();
	}
	
}
