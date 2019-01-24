package com.axp.service.newRedPaper;

import java.util.List;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperSetting;

public interface CheckService extends IBaseNewRedPaperService {

	public List<NewRedPaperSetting> getCheckListByAdmin(AdminUser adminUser);

}
