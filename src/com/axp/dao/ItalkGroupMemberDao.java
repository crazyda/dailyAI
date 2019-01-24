package com.axp.dao;

import java.util.List;

import com.axp.model.ItalkGroupMember;

public interface ItalkGroupMemberDao extends IBaseDao<ItalkGroupMember>{
	List<ItalkGroupMember> findByGroupId(Integer groupId);
	
	List<ItalkGroupMember> findByGroup(Integer groupId,Integer userId);
	
}
