package com.axp.dao;

import com.axp.model.SellerAccountNumber;

public interface SellerAccountNumberDAO extends IBaseDao<SellerAccountNumber>{

	 public void deleteSellerAccountNumber(Integer id);
	 
	 public void deleteSellerAccNumById(Integer id);
}
