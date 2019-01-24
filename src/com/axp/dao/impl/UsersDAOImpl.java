package com.axp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Repository;

import com.axp.dao.UsersDAO;
import com.axp.model.Seller;
import com.axp.model.Users;

@Repository
public class UsersDAOImpl extends BaseDaoImpl<Users> implements UsersDAO {
	
	@Override
	public Users findByLoginName(String loginName) {
		Users user = null;
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Users as model where model.isvalid = true and model.name = :loginName";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("loginName", loginName);
		@SuppressWarnings("unchecked")
		List<Users> usersList = queryObject.list();
		if(usersList.size()>0){
			user = usersList.get(0);
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findFans(Users user) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Users as model where model.isvalid = true and model.invitecode = :invitecode";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("invitecode", user.getMycode());
		return queryObject.list();
	}
	
	@Override
	public void delete(Users persistentInstance) {
		persistentInstance.setIsvalid(false);
		super.saveOrUpdate(persistentInstance);
	}
	@Override
	public Users findById(java.lang.Integer id) {
		Users au = super.findById(id);
		if(!au.getIsvalid()){
			au = null;
		}
		return au;
	}
	@Override
	public Users findByIdIgnoreValid(java.lang.Integer id) {
		return super.findById(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findInIds(String propertyName,String ids) {
		if(ids==null||ids.length()==0)
		{
			ids = "-1";
		}
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Users as model where model."+ propertyName + " in ("+ids+")";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	@Override
	public List<String> findAllUsersid() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select userid from Users where isvalid = true and userid is not null and length(userid) = 64";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	@Override
	public void del(String ids) {
		updatePropertyByIDs("isvalid", false, ids, Users.class);
	}

	
	public List<String> findInId(String propertyName,String ids){
		if(ids==null||ids.length()==0)
		{
			ids = "-1";
		}
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from Users as model where model."+ propertyName + " in ("+ids+")";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	@Override
	public List<Users> findAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = " from Users where isvalid = true and userid is not null and length(userid) = 64";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}

	
	@Override
	public List<Users> findAllIsValidUsers() {
		Session session = sessionFactory.getCurrentSession();
		String queryString = " from Users where isvalid = true and userid is not null and length(userid) = 32 ";
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}


	//=============================ZL================================//
	@Override
	public Users findByPhone(String phone) {
		Users users = null;
		try {
			Session session=sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Users> aulist = session.createQuery("from Users where phone = :phone and isValid = true ")
			.setParameter("phone", phone).list();
			if (aulist.size() > 0) {
				users = aulist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return users;
	}

	@Override
	public Integer updateUsersScore() {
		
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session.createSQLQuery("update users set score=0 ");
		return createSQLQuery.executeUpdate();
		
	}
	
	
	
	
	
}