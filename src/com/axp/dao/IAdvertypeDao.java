package com.axp.dao;

import java.util.List;

import com.axp.model.Advertype;

public interface IAdvertypeDao extends IBaseDao<Advertype>{

	List<Advertype> getListByType(Integer type);
	
}
