package com.axp.service.cashShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface LocalIstopService extends IBaseService{
	public void getList (HttpServletRequest request,HttpServletResponse response);
	void ajaxTop(Integer sid,boolean istop);
}
