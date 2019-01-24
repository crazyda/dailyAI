package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.MembersBonusDAO;
import com.axp.model.MembersBonus;

@Repository
public class MembersBonusDAOImpl extends BaseDaoImpl<MembersBonus> implements MembersBonusDAO {
	
	//根据会员类型获得当前供应商的会员分佣设置集合
	@SuppressWarnings("unchecked")
	@Override
	public List<MembersBonus> getMembersBonusByVipType(Integer adminUserId, Integer membersTypeIncomeId){
		Session session = sessionFactory.getCurrentSession();
		String queryChar = "from MembersBonus where provider.id = :providerId" +
				" and membersTypeIncome.id = :membersTypeIncomeId order by id desc";
		Query queryObject = session.createQuery(queryChar);
		queryObject.setParameter("providerId", adminUserId);
		queryObject.setParameter("membersTypeIncomeId", membersTypeIncomeId);
		return queryObject.list();
	}
}