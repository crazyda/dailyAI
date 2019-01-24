package com.axp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.IGoodsDao;
import com.axp.model.Goods;


@SuppressWarnings("unchecked")
@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao {
	
	@Override
	public void del(String ids){
		updatePropertyByIDs("isvalid", false, ids, Goods.class);
	}
	
	@Override
	public void saveAdverImgs(Integer id,Integer playtotal,String adver_imageurls,
			String adver_imageurls_small, String adver_imgurl_size){
		Session session = getSessionFactory().getCurrentSession();
		String queryString = "update Goods set playtotal = :playtotal ," +
				" status = 0 , adver_status = 0 , " +
				" adver_imgurls = :adver_imgurls, " +
				" adver_imgurls_small = :adver_imgurls_small, " +
				" adver_imgurl_size = :adver_imgurl_size " +
				" where id = :id ";
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter("playtotal", playtotal);
		queryObject.setParameter("adver_imgurls", adver_imageurls);
		queryObject.setParameter("adver_imgurls_small", adver_imageurls_small);
		queryObject.setParameter("adver_imgurl_size", adver_imgurl_size);
		queryObject.setParameter("id", id);
		queryObject.executeUpdate();
	}
	
}
