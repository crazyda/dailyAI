package com.axp.dao;

import java.sql.Timestamp;
import java.util.List;

import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperSetting;

public interface NewRedPaperSettingDAO extends IBaseDao<NewRedPaperSetting>{
	
	public List<NewRedPaperSetting> getCheckListByAdmin(AdminUser adminUser);
	
	public List<NewRedPaperSetting> findTotalSetting(StringBuffer str,List<Object> objects,Timestamp beginTime,Timestamp endTime);
	
	public Integer updateDaySurplus();

	public void updateAllNunUsed(Integer key, Integer value, String string);
}
