package com.axp.dao;

import com.axp.model.GoodsAdversettings;

public interface IGoodsAdversettingsDao extends IBaseDao<GoodsAdversettings> {

	void updateStatus(Integer goodId, Integer status);

}
