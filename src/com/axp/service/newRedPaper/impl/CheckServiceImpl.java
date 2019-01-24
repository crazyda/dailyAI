package com.axp.service.newRedPaper.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperSetting;
import com.axp.service.newRedPaper.CheckService;

@Service("checkService")
public class CheckServiceImpl extends BaseNewRedPaperServiceImpl implements CheckService{

	public List<NewRedPaperSetting> getCheckListByAdmin(AdminUser adminUser){
		return newRedPaperSettingDAO.getCheckListByAdmin(adminUser);
	}
}
