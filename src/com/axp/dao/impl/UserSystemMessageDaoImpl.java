package com.axp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.axp.dao.UserSystemMessageDao;
import com.axp.model.AdminUser;
import com.axp.model.UserSystemMessage;

@Repository
public class UserSystemMessageDaoImpl extends BaseDaoImpl<UserSystemMessage> implements UserSystemMessageDao{

	@Override
	public void saveAllmsg(List<AdminUser> userlist,Integer msgId) {
        Session session = sessionFactory.getCurrentSession();
        
        StringBuilder sb = new StringBuilder();
        sb.append("insert into user_system_message(system_messageId,isRead,isValid,createtime,userId) values ");        
        for (AdminUser adminUser : userlist) {
        	  sb.append("("+msgId+","+0+","+1+","+"NOW()"+","+adminUser.getId()+")"+",");        	  
		}
        //session.createQuery(arg0)
        session.createSQLQuery(sb.substring(0, sb.length()-1)).executeUpdate();
	}


}
