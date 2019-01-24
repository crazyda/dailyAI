package com.axp.dao;

import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;

public interface ReGoodsorderItemDAO extends IBaseDao<ReGoodsorderItem>{

	/**
	 * 根据主订单的id修改其所有子订单的Id状态
	 * @param status
	 * @param id
	 */
	void updateStatusByParent(Integer status,Integer id);

	/** 查询积分夺宝中奖用户
	 * @param currentPage
	 * @param pagesize
	 * @param searchWord 
	 * @return
	 */
	PageResult<ReGoodsorderItem> getGoodsForList(Integer currentPage,
			int pagesize, String searchWord);

}
