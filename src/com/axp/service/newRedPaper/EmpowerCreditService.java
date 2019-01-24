package com.axp.service.newRedPaper;

import com.axp.model.NewRedPaperAsset;

public interface EmpowerCreditService extends IBaseNewRedPaperService {
	public String getChild(Integer id, Integer level);

	public String getSeller(String parentIds);

	public NewRedPaperAsset findValidAssetByAdminUserId(java.lang.Integer id);

	String saveAssetDetail(Integer current_user_id, NewRedPaperAsset asset);

	@SuppressWarnings("rawtypes")
	public void updatePropertyByID(String propertyName, Object value, int id, Class model);
}
