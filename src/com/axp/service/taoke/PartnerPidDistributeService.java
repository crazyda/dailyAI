package com.axp.service.taoke;




import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.Users;
import com.axp.service.system.IBaseService;

public interface PartnerPidDistributeService extends IBaseService {
	void isDistribute(Integer ids,HttpServletRequest request,HttpServletResponse response);
	void ofDistribute(Integer id,HttpServletRequest request,HttpServletResponse response);
	void saveDistribute(Integer id,String pidId,String username,Integer adminuserId,Timestamp createtime,
			String name,HttpServletRequest request,HttpServletResponse response);
}