package com.axp.service.group.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.ItalkGroupDao;
import com.axp.dao.ItalkGroupMemberDao;
import com.axp.dao.UsersDAO;
import com.axp.model.ItalkGroup;
import com.axp.model.ItalkGroupMember;
import com.axp.model.Users;
import com.axp.service.group.ItalkGroupMemberService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.rongcloud.models.CodeSuccessResult;


@Service
public class ItalkGroupMemberServiceImpl extends BaseServiceImpl implements ItalkGroupMemberService {
	
	
	@Override
	public String addGroup(Integer groupId, String userphone) {
		ItalkGroup italk = italkGroupDao.findByGroupId(groupId).get(0);
		Users user = userDao.findByPhone(userphone)	;
		if(user==null){
			return "noUser";
		}
		
		if(italkGroupMemberDao.findByGroup(groupId, user.getId()).size()>0){
			return "false";
		}else{
		
	    	
			
		ItalkGroupMember igMember = new ItalkGroupMember();
		igMember.setIsValid(true);
		igMember.setItalkGroup(italk);
		igMember.setUsers(user);
		igMember.setCreateTime(new Timestamp(System.currentTimeMillis()));
		italkGroupMemberDao.save(igMember);
		
		return "true";
		}
	}

	@Override
	public List<ItalkGroupMember> findByGroupId(Integer groupId) {
		return italkGroupMemberDao.findByGroupId(groupId);
	}
	/**
	 * 自动加人
	 */
	@Override
	public String selfGroup(Integer groupId, List<Users> users) {
		
		List<ItalkGroup> italk = italkGroupDao.findByGroupId(groupId);
		for(Users user : users){
			if(italkGroupMemberDao.findByGroup(groupId, user.getId()).size()>0){//判断是否已经在群了,
				continue;
			}else{
			ItalkGroupMember igMember = new ItalkGroupMember();
			igMember.setIsValid(true);
			igMember.setItalkGroup(italk.get(0));
			igMember.setUsers(user);
			igMember.setIsForbid(true);
			igMember.setCreateTime(new Timestamp(System.currentTimeMillis()));
			italkGroupMemberDao.save(igMember);
			
			}
		}
		return "true";
	}

	
	

	
	
		




}
