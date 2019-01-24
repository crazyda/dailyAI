package com.axp.service.taoke;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.PartnerInform;
import com.axp.service.cashShop.IBaseService;

public interface PartnerInformService extends IBaseService{
	
	
	List<PartnerInform> findAll(HttpServletRequest request);
	
	
	/**
	 * 补充合伙人
	 * @param id
	 */
	boolean addPartner(Integer id);
	
}
