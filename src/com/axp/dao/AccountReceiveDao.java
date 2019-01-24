package com.axp.dao;


import javax.servlet.http.HttpServletRequest;

import com.axp.model.AccountReceive;

public interface AccountReceiveDao extends IBaseDao<AccountReceive>{

	
   void	updateReceive(HttpServletRequest request);
}
