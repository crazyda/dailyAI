package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ReGoodsorderItemDAO;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;
import com.axp.util.StringUtil;
@Repository
public class ReGoodsorderItemDAOImpl extends BaseDaoImpl<ReGoodsorderItem> implements ReGoodsorderItemDAO{

	@Override
	public void updateStatusByParent(Integer status, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query createQuery = session.createQuery("update ReGoodsorderItem set status = "+status +" where order.id = "+id);
		createQuery.executeUpdate();
		
	}

	
	@Override
	public PageResult<ReGoodsorderItem> getGoodsForList(Integer currentPage,
			int pageSize, String typeId) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount = "";
	        String queryList = "";
	        if("3".equals(typeId)){
	        	
	        	queryCount = "select count(r) from ReGoodsorderItem r where r.isValid=1 and r.isLock=1 and r.status>0 and r.mallClass='ldm'" ;
	        	queryList = "from ReGoodsorderItem r where  r.isValid=1 and r.isLock=1  and r.status>0 and r.mallClass='ldm' order by id desc";
	        	
	        }else if("4".equals(typeId)){
	        	queryCount = "select count(r) from ReGoodsorderItem r where r.isValid=1 and r.isLock=1 and r.isGame=1 and r.status>0" ;
	        	queryList = "from ReGoodsorderItem r where  r.isValid=1 and r.isLock=1 and r.isGame=1 and r.status>0  order by id desc";
	        }
	       


	        //先查询总条数；
	        Query query1 = session.createQuery(queryCount);
	        Long count = (Long) query1.uniqueResult();

	        //计算当前页的数据；
	        Query query2 = session.createQuery(queryList).setFirstResult((currentPage - 1) * pageSize)
	                .setMaxResults(pageSize);
	        List<ReGoodsorderItem> list = query2.list();

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}


}
