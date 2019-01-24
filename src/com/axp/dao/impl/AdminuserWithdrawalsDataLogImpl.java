package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserWithdrawalsDataLogDao;
import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;

@Repository
public class AdminuserWithdrawalsDataLogImpl extends BaseDaoImpl<AdminuserWithdrawalsDataLog> implements AdminuserWithdrawalsDataLogDao{
	@Override
	public PageResult<AdminuserWithdrawalsDataLog> getReviewDataList(
			Integer currentPage, Integer pageSize) {
		 Session session = sessionFactory.getCurrentSession();

	        //先查询总条数；
	        Query query1 = session.createQuery("select count(r) from AdminuserWithdrawalsDataLog r where isValid=1 and status=0");
	        Long count = (Long) query1.uniqueResult();

	        //计算当前页的数据；
	        Query query2 = session.createQuery("from AdminuserWithdrawalsDataLog where isValid=1 and status=0 ")
	                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);

	        //返回结果；
	        List<AdminuserWithdrawalsDataLog> list = query2.list();
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}
}
