package com.axp.dao;

import com.axp.model.ProviderBonus;

public interface ProviderBonusDAO extends IBaseDao<ProviderBonus>{

	ProviderBonus getProviderBonus(Integer adminUserId);
}