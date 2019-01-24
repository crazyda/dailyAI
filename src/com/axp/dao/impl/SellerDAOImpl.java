package com.axp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.SellerDAO;
import com.axp.model.AdminUser;
import com.axp.model.Seller;
@Repository("sellerDAO")
public class SellerDAOImpl extends BaseDaoImpl<Seller> implements SellerDAO{
	@Override
	public Seller findById(java.lang.Integer id) {
		Seller au = super.findById(id);
		if (au != null) {
			float avg = 0;
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("select count(*) from SellerRating where seller.id =:sellerId ");
			query.setParameter("sellerId", au.getId());
			int count = Integer.parseInt(query.uniqueResult().toString());
			if (count > 0) {
				query = session.createQuery("select avg(score) from SellerRating where seller.id =:sellerId ");
				query.setParameter("sellerId", au.getId());
				avg = Float.parseFloat(query.uniqueResult().toString());
			}
			au.setSellerRating(avg);
			au.setCount(count);
		}
		return au;
	}
	@Override
	public Seller findByUserId(java.lang.Integer userId) {
		Seller au = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Seller> aulist = session.createQuery("from Seller where isvalid=true and users.id = ?0")
					.setParameter("0", userId).list();
			if (aulist.size() > 0) {
				au = aulist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return au;
	}
	@Override
	public Seller findByName(String name) {
		Seller au = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Seller> aulist = session.createQuery("from Seller where name = :name and isvalid = true ")
					.setParameter("name", name).list();
			if (aulist.size() > 0) {
				au = aulist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return au;
	}
	@Override
	public List<Seller> getSellerListByAdminId(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<AdminUser> list =(List<AdminUser>) session.createQuery("from AdminUser where id = :id").setParameter("id", userId).list();
		if(list!=null&&list.size()>0){
			List<Seller> aulist = session.createQuery("from Seller where adminUser = :adminUser and isvalid = true ")
					.setParameter("adminUser", list.get(0)).list();
			return aulist;	
		}else{
			return new ArrayList<Seller>();
		}
	}
	
	@Override
	public Seller getSellerMax(){
		Session session = sessionFactory.getCurrentSession();
		Seller seller = new Seller();
		try {
			@SuppressWarnings("unchecked")
			List<Seller> sellers = session.createQuery("select max(id) from Seller where isvalid=true ").list();
			if(sellers != null && sellers.size()>0){
				seller = sellers.get(0);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
		
		
		
	}
	
	

}