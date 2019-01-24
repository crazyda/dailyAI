package com.axp.service.cashShop;

import java.util.List;

import com.axp.model.CommodityType;

public interface CommodityTypeService extends IBaseService{
	public List<CommodityType> getType(Integer level);
}
