package com.axp.service.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.ItalkGroup;
import com.axp.model.ItalkGroupMember;
import com.axp.model.Users;


public interface ItalkGroupMemberService  {
	/**
	 * 拉人入群
	 * @param request
	 */
	String addGroup(Integer groupId,String userphone) ;
	
	List<ItalkGroupMember> findByGroupId(Integer groupId);
	
	String selfGroup(Integer groupId,List<Users> users);
	
}
