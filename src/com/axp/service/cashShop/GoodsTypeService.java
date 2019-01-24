package com.axp.service.cashShop;

import com.axp.model.CommodityType;

public interface GoodsTypeService extends ICashShopService{

	public void saveCommodityType(CommodityType commodityType);
	
	public void delCommodityType(Integer id);
}
