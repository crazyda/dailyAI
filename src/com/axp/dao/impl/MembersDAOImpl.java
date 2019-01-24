package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.MembersDAO;
import com.axp.model.Members;

@Repository
public class MembersDAOImpl extends BaseDaoImpl<Members> implements MembersDAO {
	@SuppressWarnings("unchecked")
	@Override
    public Members findByUserId(Integer userId) {
		Members au =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Members where isValid=true and users.id = :userId");
		List<Members> aulist = query.setParameter("userId", userId).list();
		if (aulist.size() > 0) {
			au = aulist.get(0);
		}
		return au;
	}
}