package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.axp.dao.TkldPidDao;
import com.axp.model.AdminUser;
import com.axp.model.TkldPid;
@Repository("TkldPidDao")
public class TkldPidDaoImpl extends BaseDaoImpl<TkldPid> implements TkldPidDao {

	@Override
	public boolean checkPid(String pid) {
		 Session session = sessionFactory.getCurrentSession();
		 
		Query query=	session.createQuery("from TkldPid where isValid=1 and pid=:pid");
		query.setString("pid", pid);
			 List list = query.list();
		return list.size()>0?true:false;
	}

	@Override
	public List<TkldPid> findParentTkldPid(Integer level) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from TkldPid t where t.isValid=1 and t.level=:level");
		query.setParameter("level", level);
		return query.list();
	}


	@Override
	public List<TkldPid> findPidByCondition(DetachedCriteria criteria,int start,int end ) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		return criteria.getExecutableCriteria(session).setFirstResult(start).setMaxResults(10).list();
		
	}
	@Override
	public Long findCountByCondition(DetachedCriteria criteria){
		
		return  (Long) criteria.getExecutableCriteria(this.getSessionFactory().getCurrentSession()).uniqueResult();
		
	}
	
}
