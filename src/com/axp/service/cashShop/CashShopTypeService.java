package com.axp.service.cashShop;

import java.util.List;

import com.axp.model.CashshopType;
import com.axp.model.ImageType;
import com.axp.service.system.IBaseService;

public interface CashShopTypeService extends IBaseService{

	public List<ImageType> getImageType(Integer current_user_id);
	public void saveCashShopType(CashshopType cashshopType);
	/**
	 * 游戏类型
	 * @return
	 */
	public List<CashshopType> getGameType();
}
