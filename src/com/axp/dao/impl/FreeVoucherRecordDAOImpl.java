package com.axp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.FreeVoucherRecordDAO;
import com.axp.model.FreeVoucherRecord;

@Repository
public class FreeVoucherRecordDAOImpl extends BaseDaoImpl<FreeVoucherRecord> implements FreeVoucherRecordDAO {
	
	/**
	 * 获取粉丝有效免单券总数
	 * @param userId
	 * @return
	 */
	@Override
	public Integer getUsersValidFVCount(Integer userId){
		Session session = sessionFactory.getCurrentSession();
		String queryChar = "select count(id) from FreeVoucherRecord where " +
				"users.id = :userId and isValid = true and status = 0 and endTime > current_timestamp()";
		Query query = session.createQuery(queryChar);
		query.setParameter("userId", userId);
		return Integer.parseInt(query.uniqueResult().toString());
	}
}