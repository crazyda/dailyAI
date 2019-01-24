package com.axp.service.newRedPaper.impl;

import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperAsset;
import com.axp.service.newRedPaper.IssueService;
import com.axp.util.CalcUtil;

@Service("issueService")
public class IssueServiceImpl extends BaseNewRedPaperServiceImpl implements IssueService {

	/**
	 * 修改额度并保存记录
	 * @param adminUser
	 * @param addValue
	 * @param remark
	 */
	@Override
	public void addAssetSurplus(AdminUser adminUser, Double addValue, String remark) {
		Integer id = adminUser.getId();
		NewRedPaperAsset asset = getAsset(null, id + "");
		double finalValue = CalcUtil.add(asset.getPositionSurplus(), addValue);
		//更新额度
		asset.setPositionSurplus(finalValue);
		updateAsset(asset);
		saveLog(asset, adminUser, addValue, remark);
	}

}
