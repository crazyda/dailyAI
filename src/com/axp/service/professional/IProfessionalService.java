package com.axp.service.professional;

import java.util.List;

import com.axp.model.ProvinceEnum;
import com.axp.model.Shoptypes;
import com.axp.service.system.IBaseService;

public interface IProfessionalService extends IBaseService{

	 //获取所有商品类型
	 public List<Shoptypes> getShopTypeList();
	
	 public Integer getZoneId(String province,String city,String county);
	 //获取城市省份
	 public List<ProvinceEnum> getZone(Integer level);
	 public List<ProvinceEnum> getZoneById(Integer id,Integer level);
	 
}
