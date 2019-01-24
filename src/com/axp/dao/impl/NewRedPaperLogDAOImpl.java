package com.axp.dao.impl;


import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.axp.dao.NewRedPaperLogDAO;
import com.axp.model.NewRedPaperLog;

@SuppressWarnings("unchecked")
@Repository("newRedPaperLogDAO")
public class NewRedPaperLogDAOImpl extends BaseDaoImpl<NewRedPaperLog> implements NewRedPaperLogDAO {
	
	public Object findMaxCount(){
		 StringBuffer sb = new StringBuffer();
		 sb.append("select count(*) from newRedPaperLog");
		 Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		 return query.uniqueResult();
		 
	}
	
	public List<NewRedPaperLog> getUserNewRedPaperLogList(Integer userId,Timestamp createTime){
		StringBuffer sb = new StringBuffer("SELECT * FROM new_red_paper_log WHERE userid = ? AND status != 1 AND endTime>=? ORDER BY endtime  ASC,money ASC");		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, userId);
		query.setParameter(1, createTime);
		try {
			query.addEntity(NewRedPaperLog.class);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return query.list();
	}
	
	public void updateLogAvailAndStatus(Integer[] ids){
		StringBuffer sb = new StringBuffer("UPDATE new_red_paper_log n SET n.avail = n.money,status = :status  where id in(:ids) ");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter("status", 0);
		query.setParameterList("ids", ids);
		query.executeUpdate();
	}
	
	public List<Integer> getAllAddendum(Integer[] ids){
		StringBuffer sb = new StringBuffer("select distinct addendumId from new_red_paper_log where id in(:ids)");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameterList("ids", ids);
		return query.list();
	}
	public Integer getAddendumId(Integer id){
		StringBuffer sb = new StringBuffer("select addendumId from new_red_paper_log where id =?");
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
		query.setParameter(0, id);
		return (Integer) query.list().get(0);
	}	
	
	public List<NewRedPaperLog> getMinNewRedPaperLog(Integer userId,Timestamp createTime){
		//StringBuffer sb = new StringBuffer("SELECT * FROM new_red_paper_log WHERE userid = ? AND status != 1 AND endTime>=? ORDER BY endtime  ASC,money ASC limit 1");		
		StringBuffer sb = new StringBuffer();
		sb.append("FROM NewRedPaperLog WHERE endTime >= ? AND userid = ? AND (status < 1 or status is null) AND avail > 0 ORDER BY endtime  ASC,avail ASC limit 0,1");
		Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter(0, createTime);
		query.setParameter(1, userId);
		return  query.list();
	}
}
