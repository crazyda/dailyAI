package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ProviderBonusDAO;
import com.axp.model.ProviderBonus;

@Repository
public class ProviderBonusDAOImpl extends BaseDaoImpl<ProviderBonus> implements ProviderBonusDAO {
	@SuppressWarnings("unchecked")
	@Override
	public ProviderBonus getProviderBonus(Integer adminUserId) {
		ProviderBonus adminScale = null;
		Session session = sessionFactory.getCurrentSession();
		String queryCahr = "from ProviderBonus where adminUser.id =:adminUserId and isValid=:isValid";
		Query queryObject = session.createQuery(queryCahr);
		queryObject.setParameter("adminUserId", adminUserId);
		queryObject.setParameter("isValid", true);
		List<ProviderBonus> adminScaleList = queryObject.list();
		if(adminScaleList.size()>0){
			adminScale = adminScaleList.get(0);
		}
		return adminScale;
	}
}