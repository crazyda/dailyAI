package com.axp.service.cashShop;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NewsService extends IBaseService{
	public void getList (HttpServletRequest request,HttpServletResponse response);
	public void add(HttpServletRequest request,Integer id);
	public void save(HttpServletRequest request,Integer id,String title,String url,Timestamp createTime,Integer zoneId,Integer adminuserId);
	public void del(HttpServletRequest request,Integer id);
}
