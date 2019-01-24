package com.axp.service.taoke;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface TaokePidService extends IBaseService {
	void getPidList(HttpServletRequest request,HttpServletResponse response);
	void del(String ids);
	void add(HttpServletRequest request,Integer id);
	void save(Integer id,String pid,String pidname,Timestamp createtime,
			HttpServletRequest request,HttpServletResponse response,Integer status,
			String tkLoginLoginname,String tkLoginPassword,String tkLoginUsername);
	
	
}