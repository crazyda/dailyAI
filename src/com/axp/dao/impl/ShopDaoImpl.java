package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.ShopDao;
import com.axp.model.Seller;

@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl<Seller> implements ShopDao{

	@Override
	public void del(Integer id) {
		
		updatePropertyByID("isvalid", false, id, Seller.class);
	}

}
