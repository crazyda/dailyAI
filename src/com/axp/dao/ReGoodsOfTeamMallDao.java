package com.axp.dao;

import java.util.List;

import com.axp.model.ReGoodsOfTeamMall;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;

public interface ReGoodsOfTeamMallDao extends IBaseDao<ReGoodsOfTeamMall>{


	 /**
    * 获取所有的待审核的拼团的商品；
    *
    * @param currentPage 当前页；
    * @param pageSize    每页条数；
    * @return
    */
   PageResult<ReGoodsOfTeamMall> getCheckPageresult(Integer currentPage, Integer pageSize,Integer adminUserId);
   
   /**
	 * 查询超过拼团时间的订单
	 */
	public List<ReGoodsorderItem> getTeamOrderOverdue();
}

