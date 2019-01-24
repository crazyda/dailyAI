package com.axp.service.appAdver;

import javax.servlet.http.HttpServletRequest;

import com.axp.service.system.IBaseService;

public interface IAdverCheckService extends IBaseService{

	void confirm(HttpServletRequest request);

	void del(HttpServletRequest request);

}
