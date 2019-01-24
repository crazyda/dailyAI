package com.axp.dao.impl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.SeliveDao;
import com.axp.model.AdminUser;
import com.axp.model.Goods;
import com.axp.model.SeLive;


@Repository("seliveDao")
public class SeliveDaoImpl extends BaseDaoImpl<SeLive> implements SeliveDao{
	@Override
	public SeLive findById(Integer id){
		SeLive selive=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist=session.createQuery("from SeLive where id=:id and isvalid=true")
					.setParameter("id", id).list();
			if (relivelist.size()>0) {
				selive=relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		
		return selive;
	}
	@Override
	public SeLive findByAdminuserId(Integer adminuserId) {
		SeLive selive = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist = session.createQuery("from SeLive where isvalid=true and adminuser.id = ?0")
					.setParameter("0", adminuserId).list();
			if (relivelist.size() > 0) {
				selive = relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return selive;
	}

	@Override
	public SeLive findBySellerId(Integer sellerId) {
		SeLive seLive=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist =session.createQuery("from SeLive where isvalid=true and seller.id=?0").setParameter("0", sellerId).list();
			if (relivelist.size()>0) {
				seLive=relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return seLive;
	}
	
	@Override
	public SeLive findByLiveName(String livename) {
		SeLive selive = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist = session.createQuery("from SeLive where livename = :livename and isvalid = true ")
					.setParameter("livename", livename).list();
			if (relivelist.size() > 0) {
				selive = relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return selive;
	}
	@Override
	public void del(String ids) {
		updatePropertyByIDs("isvalid", false, ids, SeLive.class);
	}
	@Override
	public SeLive findByAdminuser(AdminUser adminUser) {
		SeLive seLive=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist=session.createQuery("from SeLive where adminUser= :adminUser and isvalid = true")
			.setParameter("adminUser", adminUser).list();
			if (relivelist.size() > 0) {
				seLive = relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
	
		return seLive;
	}
	@Override
	public void findByistop(boolean istop) {
		try {
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<SeLive> relivelist = session.createQuery("from SeLive where istop = 1 and isvalid = true ")
					.setParameter("istop", istop).list();
			if (relivelist.size() > 0) {
				SeLive seLive = relivelist.get(0);
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}
	@Override
	public void ajaxTop(Integer sid,boolean istop) {
		
		
		SeLive selive =this.findById(sid);
		
		selive.setIstop(istop);
		this.update(selive);
		
	}
	
	

}
