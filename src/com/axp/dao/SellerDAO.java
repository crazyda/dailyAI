package com.axp.dao;


import java.util.List;

import com.axp.model.Seller;

public interface SellerDAO extends IBaseDao<Seller> {

	Seller findByUserId(Integer userId);

	Seller findByName(String name);
	
	List<Seller> getSellerListByAdminId(Integer userId);

	/**
	 * 查找id最大的seller
	 * @return
	 */
	Seller getSellerMax();
}