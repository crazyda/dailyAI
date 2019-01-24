package com.axp.service.newRedPaper;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.NewRedPaperSetting;
import com.axp.service.system.IBaseService;

public interface IBaseNewRedPaperService extends IBaseService {

	@SuppressWarnings("rawtypes")
	public Object findById(Class clazz, Integer id);

	public NewRedPaperAsset getAsset(String sellerId, String adminUserId);

	NewRedPaperAsset getAssetDetail(HttpServletRequest request, Integer id, Integer adminUserId, Integer sellerId);

	public void updateSetting(NewRedPaperSetting setting);

	public void mergeSetting(NewRedPaperSetting setting);

	public void updateAsset(NewRedPaperAsset asset);

	public void saveLog(NewRedPaperAsset asset, AdminUser remitter, Double addValue, String remark);
}
