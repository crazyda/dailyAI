package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.SellerAccountNumberDAO;
import com.axp.model.SellerAccountNumber;

@Repository("sellerAccountNumberDAO")
public class SellerAccountNumberDAOImpl extends BaseDaoImpl<SellerAccountNumber> implements SellerAccountNumberDAO{

	@Override
	public void deleteSellerAccountNumber(Integer id) {
		getSessionFactory().getCurrentSession().createQuery("delete from  SellerAccountNumber where id = ?")
		.setParameter(0, id).executeUpdate();
	}
	
	@Override
	public void deleteSellerAccNumById(Integer id){
		getSessionFactory().getCurrentSession().createQuery("update  SellerAccountNumber set isValid =false where id = ? ")
		.setParameter(0, id).executeUpdate();
	}
}
