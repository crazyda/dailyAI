package com.axp.dao;

import com.axp.model.Members;

public interface MembersDAO extends IBaseDao<Members>  {
	
    public Members findByUserId(Integer userId);
}