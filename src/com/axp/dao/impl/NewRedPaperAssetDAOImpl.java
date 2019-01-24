package com.axp.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.NewRedPaperAssetDAO;
import com.axp.model.NewRedPaperAsset;
import com.axp.util.QueryModel;

@Repository("newRedPaperAssetDAO")
public class NewRedPaperAssetDAOImpl extends BaseDaoImpl<NewRedPaperAsset> implements NewRedPaperAssetDAO{
	@Resource
	private DateBaseDAO dateBaseDAO;
	
	public NewRedPaperAsset findValidAssetByAdminUserId(java.lang.Integer id) {
		try {
			NewRedPaperAsset instance = null;
			QueryModel model = new QueryModel();
			/*model.combPreCompare("endTime",
					new java.sql.Timestamp(System.currentTimeMillis()),
					QueryModel.GREATER);*/
			model.combPreEquals("adminUser.id", id, "adminUserId");
			model.combPreEquals("status", 1);
			model.combPreEquals("isValid", true);
			List<NewRedPaperAsset> instances = dateBaseDAO.findPageList(NewRedPaperAsset.class, model, 0, 1);
			if(instances.size()>0){
				instance = instances.get(0);
			}
			return instance;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public Integer updateOutTimeAsset() {
		StringBuffer assetHql = new StringBuffer();
		assetHql.append("update NewRedPaperAsset ");
		assetHql.append("set status = 5 where endTime <= now() and status = 1");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(assetHql.toString());
		return query.executeUpdate();
	}
		
	
}