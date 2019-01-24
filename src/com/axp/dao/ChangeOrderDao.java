package com.axp.dao;

import java.util.List;
import com.axp.model.ChangeOrder;

public interface ChangeOrderDao extends IBaseDao<ChangeOrder> {

	/**
	 * 查询超时未处理订单
	 * @return
	 */
	public List<ChangeOrder> getChangeOrderOverdueUntreated();
		
	/**
	 * 换货订单自动收货
	 * @return
	 */
	public List<ChangeOrder> getChangeOrderAutomaticReceipt();
	
}
