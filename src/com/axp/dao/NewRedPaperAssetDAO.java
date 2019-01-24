package com.axp.dao;

import com.axp.model.NewRedPaperAsset;

public interface NewRedPaperAssetDAO extends IBaseDao<NewRedPaperAsset>{
	
	public NewRedPaperAsset findValidAssetByAdminUserId(java.lang.Integer id);
	
	public Integer updateOutTimeAsset();
	
}
