package com.axp.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AccountManage;

public interface AccountManageDao extends IBaseDao<AccountManage>{
	void updateManage(HttpServletRequest request,HttpServletResponse response);
}
