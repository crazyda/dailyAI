package com.axp.dao;

import java.util.List;

import com.axp.model.AdminUser;
import com.axp.model.ItalkGroup;
import com.axp.model.Users;

public interface ItalkGroupDao extends IBaseDao<ItalkGroup> {

	public List<ItalkGroup> findByGroupId(Integer groupId);

	public List<ItalkGroup> findByUserId(AdminUser user);

}
