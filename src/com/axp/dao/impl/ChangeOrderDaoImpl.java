package com.axp.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.ChangeOrderDao;
import com.axp.model.ChangeOrder;
@Repository
public class ChangeOrderDaoImpl extends BaseDaoImpl<ChangeOrder> implements ChangeOrderDao {

	
	/**
	 * 查询超时未处理订单
	 * @return
	 */
	public List<ChangeOrder> getChangeOrderOverdueUntreated(){
		// 72 hour 1 MINUTE
		Session session = sessionFactory.getCurrentSession();
		
		String sql=" select * from h_change_order where isvalid=1 "
				+ "   and  date_add(sponsor_time, INTERVAL "+ChangeOrder.HOUR+" hour)<now() and status="+ChangeOrder.UNACCEPTED;
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(ChangeOrder.class);
		return sqlQuery.list();
	}

	
	/**
	 * 换货订单自动收货
	 * @return
	 */
	public List<ChangeOrder> getChangeOrderAutomaticReceipt(){
		// 72 hour 1 MINUTE -- 甲方发货时间超过1天 或 乙方发货时间超过1天
		
		//查询条件 status为已接受 且 ( 甲方发货时间超时 或乙方发货时间超时 )
		Session session = sessionFactory.getCurrentSession();
		String sql="select * from h_change_order where isvalid=1  and status="+ChangeOrder.ACCEPTED
				+ " and (  date_add(invite_sendGoods_time, INTERVAL "+ChangeOrder.HOUR+" hour)<now()  "
				+ " or date_add(accept_sendGoods_time, INTERVAL "+ChangeOrder.HOUR+" hour)<now() )";
				SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(ChangeOrder.class);
		
		return sqlQuery.list();
	}
	
	
}
