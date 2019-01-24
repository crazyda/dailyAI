package com.axp.service.cashShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface ShopCategoryService extends IBaseService{

	void list(HttpServletRequest request,HttpServletResponse response);

	void add(Integer id,HttpServletRequest request);

	void save(Integer id,Integer level,HttpServletRequest request);
	
	void del(Integer id,HttpServletRequest request);


}
