package com.axp.dao.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AccountManageDao;
import com.axp.model.AccountManage;

@Repository
public class AccountManageDaoImpl extends BaseDaoImpl<AccountManage> implements AccountManageDao{

	@Override
	public void updateManage(HttpServletRequest request, HttpServletResponse response) {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery("update AccountManage set isValid=0").executeUpdate();
	}

}
