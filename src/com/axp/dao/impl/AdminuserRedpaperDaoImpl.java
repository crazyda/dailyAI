package com.axp.dao.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserRedpaperDao;
import com.axp.model.AdminuserRedpaper;
import com.axp.model.AdminuserWithdrawals;
import com.axp.query.PageResult;
@Repository
public class AdminuserRedpaperDaoImpl extends BaseDaoImpl<AdminuserRedpaper> implements AdminuserRedpaperDao{

	@Override
	public PageResult<AdminuserRedpaper> getRedpaperChangeRecord(Integer redId, Integer currentPage, Integer pageSize,
			HttpServletRequest request) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount;
	        String queryList;

	       
	            queryCount = "select count(r) from AdminuserRedpaper r where r.isValid=1 and r.account = :redId";
	            queryList = "from AdminuserRedpaper r where r.isValid=1 and r.account = :redId order by r.id desc";
	        

	        Long count;
	        List<AdminuserRedpaper> list;
	        
	            //先查询总条数；
	            count = (Long) session.createQuery(queryCount).setParameter("account", redId).uniqueResult();
	            //计算当前页的数据；
	            list = session.createQuery(queryList).setParameter("account", redId)
	                    .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                    .list();
	       

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

}
