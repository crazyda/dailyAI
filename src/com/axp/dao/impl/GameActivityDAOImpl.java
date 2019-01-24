package com.axp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.GameActivityDAO;
import com.axp.model.AdminUser;
import com.axp.model.GameActivity;
import com.axp.model.ReGoodsOfBase;
import com.axp.query.PageResult;
import com.axp.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class GameActivityDAOImpl extends BaseDaoImpl<GameActivity> implements GameActivityDAO {

	
	@Override
	public PageResult<GameActivity> getAvtivityForList(Integer currentPage,
			Integer pageSize, String searchWord) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount = "";
	        String queryList = "";

	        if (StringUtil.hasLength(searchWord)) {
	            searchWord = " and name like '%" + searchWord + "%' ";
	        } else {
	            searchWord = "";
	        }
	        queryCount = "select count(*) from GameActivity g where g.isValid=1 "+searchWord;
            queryList = "from  GameActivity g where g.isValid=1 "+ searchWord +"order by g.id desc";
	       

	        //先查询总条数；
	        Query query1 = session.createQuery(queryCount);
	        Long count = (Long) query1.uniqueResult();

	        //计算当前页的数据；
	        Query query2 = session.createQuery(queryList).setFirstResult((currentPage - 1) * pageSize)
	                .setMaxResults(pageSize);
	        List<GameActivity> list = query2.list();

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
		
	}
	
	
	
	
	
	
}
