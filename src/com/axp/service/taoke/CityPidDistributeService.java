package com.axp.service.taoke;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface CityPidDistributeService extends IBaseService {
	void isDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response);
	void allDistribute(String ids,HttpServletRequest request,HttpServletResponse response);
	void ofDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response);
	void recycleDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response);
	void saveDistribute(Integer id,Integer pid,String pidId,Integer adminuserId,Timestamp createtime,HttpServletRequest request,HttpServletResponse response);
	void saveAll(String ids,Integer pid,String pidId,Integer adminuserId,Timestamp createtime,HttpServletRequest request,HttpServletResponse response);
	void getList(HttpServletRequest request,HttpServletResponse response);
}