package com.axp.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.axp.dao.ImageTypeDAO;
import com.axp.model.ImageType;

@SuppressWarnings("unchecked")
@Repository("imageTypeDAO")
public class ImageTypeDAOImpl extends BaseDaoImpl<ImageType> implements ImageTypeDAO{

	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String IS_VALID = "isValid";

	public void save(ImageType transientInstance) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(ImageType persistentInstance) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}


	public ImageType findById(Integer id){
		Session session = sessionFactory.getCurrentSession();
		ImageType au =null;
		try {
			au  = (ImageType)session.get(ImageType.class,id);
			return au;
		} catch (RuntimeException re) {
			throw re;
		}
			
		
	}
	
	@Override
	public List<ImageType> findByType(Integer type){
		String queryString = "from ImageType as model where model.type = :type and model.isValid = true";
		Session session = sessionFactory.getCurrentSession();
		Query queryObject = session.createSQLQuery(queryString).setParameter("type", type);
		return queryObject.list();
	}

	public List findByExample(ImageType instance) {
		Session session = sessionFactory.getCurrentSession();
		try {
			List results = session
					.createCriteria("com.wehua.db.model.ImageType")
					.add(Example.create(instance)).list();
 
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String queryString = "from ImageType as model where model."
					+ propertyName + "= :"+propertyName+" and model.isValid=:isValid";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(propertyName, value);
			queryObject.setParameter("isValid", true);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findByIds(String propertyName, String ids) {
		Session session = sessionFactory.getCurrentSession();
		try {
			String queryString = " select * from Image_Type as model where model."
					+ propertyName + " in( ? ) and model.isValid = ?";
			Query queryObject = session.createSQLQuery(queryString);
			queryObject.setParameter(0, ids);
			queryObject.setParameter(1, true);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByIsValid(Object isValid) {
		return findByProperty(IS_VALID, isValid);
	}

	public List findAll() {
		Session session = sessionFactory.getCurrentSession();
		try {
			String queryString = "from ImageType where isValid= :isValid ";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter("isValid", true);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	

	public void attachDirty(ImageType instance) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(ImageType instance) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
