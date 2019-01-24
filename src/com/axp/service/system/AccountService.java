package com.axp.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService extends IBaseService{

	void accountManage(HttpServletRequest request,HttpServletResponse response);
	
	void add(HttpServletRequest request,HttpServletResponse response,Integer id);
	
	void save(HttpServletRequest request,HttpServletResponse response,String name);
	
	void saveAccount(HttpServletRequest request,HttpServletResponse response,Integer id,String firstselect,String secondselect,
			String appid,String secret,String mchid,String apikey,String aliappid,String priKey,String priKey2,String pubKey,String alisellerid,String aliapikey);
}
