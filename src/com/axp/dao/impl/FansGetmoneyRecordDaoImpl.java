package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.FansGetmoneyRecordDao;
import com.axp.dao.GetmoneyRecordDao;
import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;
@Repository("fansGetmoneyRecordDao")
public class FansGetmoneyRecordDaoImpl extends BaseDaoImpl<GetmoneyRecord> implements FansGetmoneyRecordDao{
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
		String queryCount = "select count(r) from GetmoneyRecord r where r.isValid=1  and ( r.status="+GetmoneyRecord.yi_zhi_fu+" or r.status="+GetmoneyRecord.zhi_fu_cheng_gong+" or r.status="+GetmoneyRecord.zhi_fu_shi_bai+" )" ;
		String queryList = "from GetmoneyRecord r where r.isValid=1 and ( r.status="+GetmoneyRecord.yi_zhi_fu+" or r.status="+GetmoneyRecord.zhi_fu_cheng_gong+" or r.status="+GetmoneyRecord.zhi_fu_shi_bai+"  ) order by r.id desc";
		
		 //先查询总条数；
        Long count = (Long) session.createQuery(queryCount).uniqueResult();
        
      //计算当前页的数据；
        List<GetmoneyRecord> list = session.createQuery(queryList)
                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
                .list();
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

	
	@Override
	public List<GetmoneyRecord> getMoenyRecordListByStatus() {
		
		Session session = sessionFactory.getCurrentSession();
		String queryList = "from GetmoneyRecord r where r.isValid=1 and  r.status="+GetmoneyRecord.zhi_fu_cheng_gong;
		
		return session.createQuery(queryList).list();
		
	}
	
	
	
	
	@Override
	public PageResult<GetmoneyRecord> getFansPayList(Integer currentPage,
			Integer pageSize) {
	
		Session session = sessionFactory.getCurrentSession();
		//先获取查询条件；
				String queryCount = "select count(r) from GetmoneyRecord r where r.isValid=1 and r.status="+GetmoneyRecord.yi_shen_he+"";
				String queryList = "from GetmoneyRecord r where r.isValid=1 and r.status="+GetmoneyRecord.yi_shen_he+"" + " order by r.id desc";
		

				 //先查询总条数；
		        Long count = (Long) session.createQuery(queryCount).uniqueResult();
		        
		      //计算当前页的数据；
		        List<GetmoneyRecord> list = session.createQuery(queryList)
		                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
		                .list();
		        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}


}
