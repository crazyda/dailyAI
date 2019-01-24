package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.GetmoneyRecordDao;
import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;
@Repository
public class GetmoneyRecordDaoImpl extends BaseDaoImpl<GetmoneyRecord> implements GetmoneyRecordDao{
	 @Override
	    public PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage, Integer pageSize) {
	        Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount = "select count(r) from GetmoneyRecord r where r.isValid=1 and r.status=0";
	        String queryList = "from GetmoneyRecord r where r.isValid=1 and r.status=0" + " order by r.id desc";

	        //先查询总条数；
	        Long count = (Long) session.createQuery(queryCount).uniqueResult();

	        //计算当前页的数据；
	        List<GetmoneyRecord> list = session.createQuery(queryList)
	                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                .list();

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	    }

	@Override
	public PageResult<GetmoneyRecord> getPaycompleteList(Integer currentPage,
			Integer pageSize) {
		Session session = sessionFactory.getCurrentSession();
		
		//先获取查询条件；
		String queryCount = "select count(r) from GetmoneyRecord r where r.isValid=1 and r.status=10";
		String queryList = "from GetmoneyRecord r where r.isValid=1 and r.status=10" + " order by r.id desc";
		
		 //先查询总条数；
        Long count = (Long) session.createQuery(queryCount).uniqueResult();
        
      //计算当前页的数据；
        List<GetmoneyRecord> list = session.createQuery(queryList)
                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                .list();
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}
}
