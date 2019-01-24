package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.TaokeDao;
import com.axp.model.AdminuserZoneidTaoke;

@Repository("taokeDao")
public class TaokeDaoImpl extends BaseDaoImpl<AdminuserZoneidTaoke> implements TaokeDao{

	@Override
	public void del(String ids) {
		updatePropertyByIDs("isvalid", false, ids, AdminuserZoneidTaoke.class);
	}
	
}
