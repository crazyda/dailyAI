package com.axp.dao;


import org.hibernate.criterion.DetachedCriteria;

import com.axp.model.AdminuserTaokePid;

public interface TaokePidDao extends IBaseDao<AdminuserTaokePid> {

	void del(String ids);
	
	
}
