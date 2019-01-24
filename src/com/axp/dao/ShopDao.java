package com.axp.dao;

import com.axp.model.Seller;

public interface ShopDao extends IBaseDao<Seller>{
	void del(Integer id);
}
