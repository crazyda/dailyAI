package com.axp.service.professional.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.axp.dao.DateBaseDAO;
import com.axp.model.ProvinceEnum;
import com.axp.model.Shoptypes;
import com.axp.service.professional.IProfessionalService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;

public abstract class ProfessionalServiceImpl extends BaseServiceImpl implements IProfessionalService{

	@Resource
	public DateBaseDAO dateBaseDAO;
	@Override
	public List<Shoptypes> getShopTypeList() {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid",true );
		List<Shoptypes> ShoptypesList = dateBaseDAO.findLists(Shoptypes.class, queryModel);
		return ShoptypesList;
	}
	
	@Override
	public Integer getZoneId(String province, String city, String county) {
		Integer zoneId;
		if(StringUtils.isNotBlank(county)){
			zoneId = Integer.parseInt(county);
		}else if(StringUtils.isNotBlank(city)){
			zoneId = Integer.parseInt(city);
		}else{
			zoneId = Integer.parseInt(province);
		}
		return zoneId;
	}
	
	@Override
	public List<ProvinceEnum> getZone(Integer level) {
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("level",level);
		queryModel.combPreEquals("isValid", true);
		List<ProvinceEnum> provinceEnum = dateBaseDAO.findLists(ProvinceEnum.class, queryModel);
		return provinceEnum;
	}
	
}
