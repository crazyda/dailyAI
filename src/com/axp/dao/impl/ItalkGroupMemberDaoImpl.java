package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ItalkGroupMemberDao;
import com.axp.model.ItalkGroupMember;
@Repository
public class ItalkGroupMemberDaoImpl extends BaseDaoImpl<ItalkGroupMember> implements
		ItalkGroupMemberDao {

	@Override
	public List<ItalkGroupMember> findByGroupId(Integer groupId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ItalkGroupMember i where i.italkGroup.groupId="+groupId+"and isValid =true order by createTime desc");
		
		return query.list();
	}

	@Override
	public List<ItalkGroupMember> findByGroup(Integer groupId, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ItalkGroupMember i where i.italkGroup.groupId="+groupId+"and i.users.id="+userId +"and isValid=true");
		
		return query.list();
	}

	

}
