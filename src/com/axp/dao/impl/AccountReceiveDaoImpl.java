package com.axp.dao.impl;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AccountReceiveDao;
import com.axp.model.AccountReceive;

@Repository
public class AccountReceiveDaoImpl extends BaseDaoImpl<AccountReceive> implements AccountReceiveDao{

	@Override
	public void updateReceive(HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("update AccountReceive set isValid=0").executeUpdate();
	}

}
