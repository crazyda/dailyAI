package com.axp.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.axp.dao.TkldPidOneDao;
import com.axp.model.TkldPidOne;
@Repository("TkldPidOneDao")
public class TkldPidOneDaoImpl extends BaseDaoImpl<TkldPidOne> implements TkldPidOneDao {

	@Override
	public boolean checkPid(String pid) {
		 Session session = sessionFactory.getCurrentSession();
		 
		Query query=	session.createQuery("from TkldPidOne where isValid=1 and pid=:pid");
		query.setString("pid", pid);
			 List list = query.list();
		return list.size()>0?true:false;
	}

	@Override
	public List<TkldPidOne> findParentTkldPid(Integer level) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from TkldPidOne t where t.isValid=1 and t.level=:level");
		query.setParameter("level", level);
		return query.list();
	}


	@Override
	public List<TkldPidOne> findPidByCondition(DetachedCriteria criteria,int start,int end ) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		return criteria.getExecutableCriteria(session).setFirstResult(start).setMaxResults(10).list();
		
	}
	@Override
	public Long findCountByCondition(DetachedCriteria criteria){
		
		return  (Long) criteria.getExecutableCriteria(this.getSessionFactory().getCurrentSession()).uniqueResult();
		
	}
	
}
