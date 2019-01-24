package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserWithdrawalsDao;
import com.axp.model.AdminuserWithdrawals;
import com.axp.query.PageResult;


@Repository
public class AdminuserWithdrawalsImpl extends BaseDaoImpl<AdminuserWithdrawals> implements AdminuserWithdrawalsDao{

	@Override
	public PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage,
			Integer pageSize,Integer state) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount;
	        String queryList;

	       
	            queryCount = "select count(r) from AdminuserWithdrawals r where r.isValid=1 and r.state = :state";
	            queryList = "from AdminuserWithdrawals r where r.isValid=1 and r.state = :state order by r.id desc";
	        

	        Long count;
	        List<AdminuserWithdrawals> list;
	        
	            //先查询总条数；
	            count = (Long) session.createQuery(queryCount).setParameter("state", state).uniqueResult();
	            //计算当前页的数据；
	            list = session.createQuery(queryList).setParameter("state", state)
	                    .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                    .list();
	       

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	    }

	
	}


