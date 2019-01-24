package com.axp.service.system;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserInfoService extends IBaseService{
	void getUserInfo(HttpServletRequest request,HttpServletResponse response,String mixM,String maxM);
	void del(String ids);
}
