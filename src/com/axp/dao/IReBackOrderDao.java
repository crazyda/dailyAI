package com.axp.dao;

import java.util.List;

import com.axp.model.ReBackOrder;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.Users;


public interface IReBackOrderDao extends IBaseDao<ReBackOrder>{

	List<ReBackOrder> getBackListBySeller(Integer sellerId, Integer backState);

	 ReBackOrder saveBackOrder(ReGoodsorderItem item, Users user, String reason, Integer type, String imageJson,Integer backState );
}
