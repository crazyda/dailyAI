package com.axp.service.cashShop.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.model.CommodityType;
import com.axp.service.cashShop.CommodityTypeService;
import com.axp.util.QueryModel;

@Service("commodityTypeService")
public class CommodityTypeServiceImpl extends BaseServiceImpl implements CommodityTypeService{
	@Resource
	public DateBaseDAO dateBaseDAO;
	@Override
	public List<CommodityType> getType(Integer level) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",level);
		queryModel.combPreEquals("isValid", true);
		List<CommodityType> commodityType = dateBaseDAO.findLists(CommodityType.class, queryModel);
		return commodityType;
	}

}
