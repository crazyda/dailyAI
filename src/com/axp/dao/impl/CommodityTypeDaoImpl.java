package com.axp.dao.impl;

import org.springframework.stereotype.Repository;

import com.axp.dao.ICommodityTypeDao;
import com.axp.model.CommodityType;

@Repository("commodityTypeDAO")
public class CommodityTypeDaoImpl extends BaseDaoImpl<CommodityType> implements ICommodityTypeDao{

}
