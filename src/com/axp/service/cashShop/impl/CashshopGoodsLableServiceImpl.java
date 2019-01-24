package com.axp.service.cashShop.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.ICashshopGoodsLableDao;
import com.axp.model.CashshopGoodsLable;
import com.axp.service.cashShop.CashshopGoodsLableService;
import com.axp.service.system.impl.BaseServiceImpl;
@Service("CashshopGoodsLableService")
public class CashshopGoodsLableServiceImpl extends BaseServiceImpl implements
		CashshopGoodsLableService {
	@Resource 
	private ICashshopGoodsLableDao cashshopGoodsLableDao;

	@Override
	public void saveCashshopGoodsLable(CashshopGoodsLable cashshopGoodsLable) {
		if (cashshopGoodsLable.getId() == null) {
			cashshopGoodsLableDao.save(cashshopGoodsLable);
		} else {
			cashshopGoodsLableDao.merge(cashshopGoodsLable);
		}
	}

	@Override
	public void delCashshopGoodsLable(Integer id) {
		cashshopGoodsLableDao.updatePropertyByIDs("isValid", false, String.valueOf(id), CashshopGoodsLable.class);
	}

	@Override
	public int getAllCountAndCon(Class<CashshopGoodsLable> CashshopGoodsLable,
			String name) {
		return 0;
	}

}
