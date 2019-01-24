package com.axp.dao.impl;




import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.axp.dao.TaokePidDao;
import com.axp.model.AdminuserTaokePid;
import com.axp.model.TkldPid;

@Repository
public class TaokePidDaoImpl extends BaseDaoImpl<AdminuserTaokePid> implements TaokePidDao {

	@Override
	public void del(String ids) {
		updatePropertyByIDs("isValid", false, ids, AdminuserTaokePid.class);
	}

	

	
	
	
}
