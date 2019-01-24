package com.axp.service.professional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ShopService extends IProfessionalService{
	void getSellerList(HttpServletRequest request,HttpServletResponse response);
	boolean delete(HttpServletRequest request,Integer id);
}
