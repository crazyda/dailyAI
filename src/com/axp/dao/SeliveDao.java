package com.axp.dao;

import com.axp.model.AdminUser;
import com.axp.model.SeLive;

public interface SeliveDao extends IBaseDao<SeLive>{
	 
	SeLive findById(Integer id);
	
	SeLive findByAdminuserId(Integer adminuserId);
	
	SeLive findBySellerId(Integer sellerId);

	SeLive findByLiveName(String livename);
	
	
	void del(String ids);
	
	SeLive findByAdminuser(AdminUser adminUser);
	
	void findByistop(boolean istop);
	
	void ajaxTop(Integer sid,boolean istop);
}
