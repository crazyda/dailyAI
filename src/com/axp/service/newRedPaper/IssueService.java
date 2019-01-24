package com.axp.service.newRedPaper;

import com.axp.model.AdminUser;

public interface IssueService extends IBaseNewRedPaperService {

	public void addAssetSurplus(AdminUser adminUser, Double addValue, String remark);

}
