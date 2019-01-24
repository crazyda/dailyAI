package com.axp.service.cashShop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.ICommodityTypeDao;
import com.axp.model.CommodityType;
import com.axp.service.cashShop.GoodsTypeService;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl extends CashShopServiceImpl implements GoodsTypeService {

	@Resource
	public ICommodityTypeDao commodityTypeDAO;

	@Override
	public void saveCommodityType(CommodityType commodityType) {
		if (commodityType.getId() != null) {
			commodityTypeDAO.save(commodityType);
		} else {
			commodityTypeDAO.merge(commodityType);
		}

	}

	@Override
	public void delCommodityType(Integer id) {
		commodityTypeDAO.updatePropertyByIDs("isValid", false, String.valueOf(id), CommodityType.class);

	}

}
