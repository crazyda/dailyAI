package com.axp.service.cashShop;

import com.axp.model.CashshopGoodsLable;
import com.axp.service.system.IBaseService;

public interface CashshopGoodsLableService extends IBaseService {

	public void saveCashshopGoodsLable(CashshopGoodsLable cashshopGoodsLable);
	
	public void delCashshopGoodsLable(Integer id);
	/**
	 * 获取页面总数
	 * @param CashshopGoodsLable
	 * @param name
	 * @return
	 */
	int getAllCountAndCon(Class<CashshopGoodsLable> CashshopGoodsLable, String name);
}
