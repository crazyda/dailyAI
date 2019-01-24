package com.axp.service.system;

import javax.servlet.http.HttpServletRequest;

public interface LoginService extends IBaseService{
	
	String userLogin(String loginname, String password,
			HttpServletRequest request);

	String userLogout(HttpServletRequest request);
			
}