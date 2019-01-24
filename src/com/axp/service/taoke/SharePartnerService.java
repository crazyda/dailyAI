package com.axp.service.taoke;

import javax.servlet.http.HttpServletRequest;

import com.axp.service.system.IBaseService;

public interface SharePartnerService  extends IBaseService{

		
	
	/**
	 * 展示带审批的信息
	 */
	
	void findSharePartnerList(HttpServletRequest request);
	
	
}
