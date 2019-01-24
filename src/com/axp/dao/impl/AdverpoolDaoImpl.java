package com.axp.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.IAdverpoolDao;
import com.axp.model.Adverpool;



@SuppressWarnings("unchecked")
@Repository
public class AdverpoolDaoImpl extends BaseDaoImpl<Adverpool> implements IAdverpoolDao {
	
	
	@Override
	public List<Adverpool> getHideAdverPool(Integer adminUserId){
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Adverpool as model where model.isvalid=true " +
				"and model.higelevel = true and  model.isplay =2 " +
				"and model.adminUser.id="+adminUserId;
		Query queryObject = session.createQuery(queryString);
		
		return queryObject.list();
	}
}
