package com.axp.service.invoke;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.Users;
import com.axp.service.system.IBaseService;

public interface UsersInvokeService extends IBaseService{

	String login(String name, String pwd, String inviteCode, String latitude,
			String longitude, HttpServletRequest request);

	List<Users> saveUserGoldList(HttpServletRequest request,String phone, Integer gold);
	
	
}