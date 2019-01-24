package com.axp.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.axp.dao.NewRedPaperAddendumDAO;
import com.axp.model.NewRedPaperAddendum;

@Repository("newRedPaperAddendumDAO")
@SuppressWarnings("unchecked")
public class NewRedPaperAddendumDAOImpl extends BaseDaoImpl<NewRedPaperAddendum> implements NewRedPaperAddendumDAO{

	
	public List<NewRedPaperAddendum> findByUserIdAndEndTime(String userId,
			String settingId, Timestamp endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("From NewRedPaperAddendum where users.id=:usersId AND setting.id =:settingId AND "
				+ "endTime >='" + endTime + "' AND endTime <= '" + endTime+"'");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter("usersId",Integer.valueOf(userId));
		query.setParameter("settingId", Integer.valueOf(settingId));
		return query.list();

	}
	
	public void updateAvail(Integer id,double money){
		StringBuffer sb = new StringBuffer("update new_red_paper_addendum set avail = avail+?  where id =?");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, money);
		query.setParameter(1, id);
		query.executeUpdate();
	}
	
	public List<Object[]> getTimeAndAvail(Integer id ,Timestamp nextTime){
		StringBuffer sb = new StringBuffer("select avail,endTime from new_red_paper_addendum where userId = ? and endTime>=? and endTime<=? and avail > ?");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, id);
		query.setParameter(1, nextTime);
		query.setParameter(2, nextTime);
		query.setParameter(3, 0);
		return query.list();
	}
	
	public  Object[] getMinTimeAndAvail(Integer id){
		StringBuffer sb =new StringBuffer("SELECT avail,min(endTime) FROM new_red_paper_addendum WHERE userId = ? and avail >  ?");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, id);
		query.setParameter(1, 0.00);
		return (Object[]) query.uniqueResult();

	}
	
	public void updateOneAvail(Integer id,double userMoney){
		StringBuffer sb = new StringBuffer("update new_red_paper_addendum set avail = avail-? where id = ?");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, userMoney);
		query.setParameter(1, id);
		query.executeUpdate();
	}
}