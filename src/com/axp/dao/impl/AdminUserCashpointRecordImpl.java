package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.AdminuserCashpointRecordDAO;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;

@Repository
public class AdminUserCashpointRecordImpl extends BaseDaoImpl<AdminuserCashpointRecord> implements AdminuserCashpointRecordDAO{

	@Override
	public PageResult<AdminuserCashpointRecord> getMoneyChangeRecord(
			Integer adminuserId, Integer currentPage, Integer pageSize) {
		 Session session = sessionFactory.getCurrentSession();

	        //先获取查询条件；
	        String queryCount = "select count(r) from AdminuserCashpointRecord r where r.type=1 and  r.isValid=1 and r.adminUser.id=" + adminuserId;
	        String queryList = "from AdminuserCashpointRecord r where r.isValid=1 and r.type=1 and r.adminUser.id=" + adminuserId + " order by r.id desc";
	        //先查询总条数；
	        Long count = (Long) session.createQuery(queryCount).uniqueResult();

	        //计算当前页的数据；
	        List<AdminuserCashpointRecord> list = session.createQuery(queryList)
	                .setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize)
	                .list();

	        //返回结果；
	        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}

	@Override
	public Object[] getTotalIncomeAndExpend(Integer adminUserId,Integer type) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT SUM(case when cashpoint > 0 then cashpoint else 0 end),SUM(case when cashpoint < 0 then cashpoint else 0 end) from AdminuserCashpointRecord where adminuserId=:adminuserId AND type=:type AND isValid=true";
		Object[] obj = (Object[]) session.createQuery(hql).setParameter("adminuserId", adminUserId).setParameter("type", type).uniqueResult();
	
		if(obj[0]==null){
			obj[0]=0.0;
		}
		if(obj[1]==null){
			obj[1]=0.0;
		}
		return obj;
	}
	/**
	 * 删除商家资金记录
	 * @param item
	 */
	@Override
	public void delRecordByOrderItemId(ReGoodsorderItem item){
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session.createSQLQuery("update adminuser_cashpoint_record set isvalid=0 where isvalid=1 and orderitemid="+item.getId());
		createSQLQuery.executeUpdate();
		
	}
	

	
	@Override
	public PageResult<AdminuserCashpointRecord> findReDepositList(
			Integer currentPage, Integer pageSize ,String type,String sellerName) {
		
		Session session = sessionFactory.getCurrentSession();

		Long count;
		List<AdminuserCashpointRecord> list;
        //先获取查询条件；
        String queryCount;
        String queryList;
        queryCount = "select count(r) from AdminuserCashpointRecord r where r.isValid=1  and r.isDeposit=1";
        queryList = "from AdminuserCashpointRecord r where r.isValid=1 and r.isDeposit=1  ";
        
        if(!"0".equals(type)){
        	queryCount += " and r.type = :type"; 
        	queryList += " and r.type = :type";
        	
        }
        if(!"".equals(sellerName)){
        	queryCount += " and r.adminUser.username = :sellerName";
        	queryList += " and r.adminUser.username  = :sellerName";
        	
        }
        queryList += " order by r.id desc";
        Query sql = session.createQuery(queryCount);
        Query sql2 = session.createQuery(queryList);
        
        if(!"0".equals(type)){
        	sql.setParameter("type", Integer.valueOf(type));
        	sql2.setParameter("type", Integer.valueOf(type));
        }
        if(!"".equals(sellerName)){
        	sql.setParameter("sellerName", sellerName);
        	sql2.setParameter("sellerName", sellerName);
        }
        
        //先查询总条数；
        count = (Long) sql.uniqueResult();
        //计算当前页的数据；
        list = sql2.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        //返回结果；
        return new PageResult<>(Integer.parseInt(count.toString()), pageSize, currentPage, list);
	}
	

}
