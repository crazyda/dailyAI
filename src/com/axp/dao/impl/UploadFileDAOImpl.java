package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.UploadFileDAO;
import com.axp.model.UploadFile;

@Repository("uploadFileDAO")
public  class UploadFileDAOImpl extends BaseDaoImpl<UploadFile> implements UploadFileDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getImgUrls(Integer id,String table) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "Select model.smallUrl from UploadFile as model" +
				" where model.relatedId=:relatedId and model.tableName=:tableName";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("relatedId", id);
		queryObject.setParameter("tableName", table);
		return queryObject.list();
	}
}