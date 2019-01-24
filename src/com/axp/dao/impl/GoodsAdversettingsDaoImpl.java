package com.axp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.IGoodsAdversettingsDao;
import com.axp.model.GoodsAdversettings;


@SuppressWarnings("unchecked")
@Repository
public class GoodsAdversettingsDaoImpl extends BaseDaoImpl<GoodsAdversettings> implements IGoodsAdversettingsDao {
	
	@Override
	public void updateStatus(Integer goodId, Integer status){
		Session session = sessionFactory.getCurrentSession();
		String queryString = "update GoodsAdversettings set status = :status where goods.id = :goodId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("goodId", goodId);
		queryObject.setParameter("status", status);
		queryObject.executeUpdate();
	}
	
}
