package com.axp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserWithdrawalsBankDao;
import com.axp.model.AdminuserWithdrawalsBank;



@Repository
public class AdminuserWithdrawalsBankImpl extends BaseDaoImpl<AdminuserWithdrawalsBank> implements AdminuserWithdrawalsBankDao{



	@Override
	public void updateIsDefueltByAdminuserId(Integer adminuserId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "update AdminuserWithdrawalsBank set isValid=fasle where adminUser.id= "+adminuserId;
		Query querySeller = session.createQuery(queryString);
		querySeller.executeUpdate();
	
		
	}
	
	@Override
	public void updateIsDefueltByAdminuserId(Integer adminuserId,Integer bankId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "update AdminuserWithdrawalsBank set isValid=fasle where adminUser.id= "+adminuserId+"and id <>"+bankId;
		Query querySeller = session.createQuery(queryString);
		querySeller.executeUpdate();
	
		
	}
	
	
	


}
