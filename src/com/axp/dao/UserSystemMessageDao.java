package com.axp.dao;


import java.util.List;

import com.axp.model.AdminUser;
import com.axp.model.UserSystemMessage;



public interface UserSystemMessageDao extends IBaseDao<UserSystemMessage> {
	 /**
	  * 批量保存UserSystemMessage
	  * @param userlist
	  * @param msgId
	  */
	 void saveAllmsg(List<AdminUser> userlist,Integer msgId);

}
