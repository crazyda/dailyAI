package com.axp.service.group.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




































import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.ItalkGroupDao;
import com.axp.dao.ItalkGroupMemberDao;
import com.axp.dao.ItalkGroupNoticeDao;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.ItalkGroup;
import com.axp.model.ItalkGroupMember;
import com.axp.model.ItalkGroupNotice;
import com.axp.model.Users;
import com.axp.service.group.ItalkGroupNoticeService;
import com.axp.service.group.ItalkGroupService;
import com.axp.service.group.RongCloudService;
import com.axp.service.invoke.impl.BaseServiceImpl;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.rongcloud.models.CodeSuccessResult;


@Service
public class ItalkGroupServiceImpl extends BaseServiceImpl implements ItalkGroupService {
	
	@Autowired
	public UsersDAO userDao;
	@Autowired
	public ItalkGroupNoticeService italkGroupNoticeService;
	
	@Override
	public String creategroup(HttpServletRequest request) {
		Integer userId = (Integer) request.getSession().getAttribute("currentUserId");
		
		String groupInfo = request.getParameter("groupInfo");
//		String groupNotice = request.getParameter("groupNotice");
		String level = request.getParameter("level");
		String img = request.getParameter("img");
		String name = request.getParameter("groupName");
		String groupNotice = request.getParameter("groupNotice");
		String url = request.getParameter("url");
		
		
		
		String groupName = null;
		
		//根据级别 区分群id  事业合伙人, 合伙人 后台admin 直接创建 两个群
		String groupId = null;
		boolean flag = true;
		do {
			if(Integer.parseInt(level)==2){//事业合伙人
				groupId="222";
				groupName = name;
			}else if(Integer.parseInt(level)==3){//合伙人
				groupId="333";
				groupName = name;
			}
			groupId += StringUtil.randomString6();
			List<ItalkGroup> italk = italkGroupDao.findByGroupId(Integer.parseInt(groupId));
			if(italk.size()>0){
				flag = false;
			}
			
		} while (!flag);
		//Users user = userDao.findById(userId);
		AdminUser adminUser = adminUserDao.findById(userId);
		String[] userIds = new String[1];   //暂时只能数组 1 
		userIds[0] = String.valueOf(userId);
		
		  Map<String,Object> groupCode = new HashMap<String,Object>();
	     
		  //向融云提交建群
		  CodeSuccessResult groupToken = rongCloudService.getGroupToken(userIds, groupId, groupName);
		  if(groupToken.getCode() == 200){//添加成功
			  ItalkGroup italkGroup = new ItalkGroup();
			  italkGroup.setCreateTime(new Timestamp(System.currentTimeMillis()));
			  italkGroup.setGroupId(Integer.parseInt(groupId));
			  italkGroup.setName(groupName);
			  italkGroup.setUser(adminUser);
			  italkGroup.setIsValid(true);
			  italkGroup.setGroupType(Integer.parseInt(level));
			  italkGroup.setImgUrl(img);
			  ItalkGroupNotice italkGroupNotice = new ItalkGroupNotice();
			  italkGroupNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
			  italkGroupNotice.setIsValid(true);
			  italkGroupNotice.setName(groupNotice);
			  italkGroupNotice.setContent(groupInfo);
			  italkGroupNotice.setItalkGroup(italkGroup);
			  italkGroupNoticeDao.save(italkGroupNotice);
			  italkGroupDao.save(italkGroup);
	       	 groupCode.put("code",groupToken.getCode()); 
	        }else{
	        	 groupCode.put("code", groupToken.getCode());
	        	 groupCode.put("message", groupToken.getErrorMessage());
	         }
		return (groupToken.getErrorMessage());
			//return groupId;

	}

	@Override
	public List<ItalkGroup> findByUserId(HttpServletRequest request) {
		Integer userId =  (Integer) request.getSession().getAttribute("currentUserId");
		//Users user = userDao.findById(userId);
		
		AdminUser adminUser = adminUserDao.findById(userId);
		
		List<ItalkGroup> groups = italkGroupDao.findByUserId(adminUser);
		
		
		
		return groups;
		
	}

	@Override
	public String selfAddGroup(Integer groupId) {
		
		return null;
	}

	@Override
	public boolean isForbid(Integer groupId, Integer userId, Integer isForbid) {
		
		if(isForbid==0){//页面显示已禁言, 执行是取消禁言
			ItalkGroupMember groupMember = italkGroupMemberDao.findByGroup(groupId, userId).get(0);
			groupMember.setIsForbid(false);
			italkGroupMemberDao.update(groupMember);
			//请求融云移除用户禁言服务
			CodeSuccessResult backUser = rongCloudService.rollBackGagUser(String.valueOf(userId), String.valueOf(groupId));
			if(backUser.getCode()==200){//云禁言 请求成功
				return true;
			}else{
				
				return false;
			}
			
			
		}else if(isForbid == 1 || isForbid == null){//执行是 禁言用户,不让其发言
			String minute = "5";//禁言时间
			//跟新禁言字段
			ItalkGroupMember groupMember = italkGroupMemberDao.findByGroup(groupId, userId).get(0);
			groupMember.setIsForbid(true);
			
			italkGroupMemberDao.update(groupMember);
			//请求融云禁言服务
			CodeSuccessResult addGagUser = rongCloudService.getAddGagUser(String.valueOf(userId), String.valueOf(groupId), minute);
			if(addGagUser.getCode()==200){//云禁言 请求成功
				return true;
			}else{
				
				return false;
			}
		
		}
		
		return false;
	}
	//设置 禁言 把 isForbid 改成禁言状态 就是 0  true
	@Override
	public boolean allIsForbid(Integer groupId) {
		List<ItalkGroupMember> groupList = italkGroupMemberDao.findByGroupId(groupId);
		List<String> userIdList = new ArrayList<String>();
		for(ItalkGroupMember g :groupList){
			if(g.getIsForbid()==false || g.getIsForbid()== null){
				g.setIsForbid(true);
				italkGroupMemberDao.update(g);
			}
			userIdList.add(String.valueOf(g.getUsers().getId()));
		}
		//当提交多个 userId 参数时，表示将群组中多个用户禁言，每次最多设置 20 个用户；
		String[] userIds = (String[])userIdList.toArray(new String[userIdList.size()]);
		CodeSuccessResult addGagUser = rongCloudService.getAllAddGagUser(userIds,String.valueOf(groupId),"5");
		
		
		if(addGagUser.getCode()!=200){//云禁言 请求成功
			return false;
		}
		
		return true;
	}
	//设置 禁言 把 isForbid 改成不禁言状态 就是1 false
	@Override
	public boolean allIsForbid2(Integer groupId) {
		List<ItalkGroupMember> groupList = italkGroupMemberDao.findByGroupId(groupId);
		List<String> userIdList = new ArrayList<String>();
		for(ItalkGroupMember g :groupList){
			if(g.getIsForbid() || g.getIsForbid()== null){//true 已禁言
				g.setIsForbid(false);
				italkGroupMemberDao.update(g);
			}
			userIdList.add(String.valueOf(g.getUsers().getId()));
		}
		//当提交多个 userId 参数时，表示将群组中多个用户禁言，每次最多设置 20 个用户；
		String[] userIds = (String[])userIdList.toArray(new String[userIdList.size()]);
		CodeSuccessResult addGagUser = rongCloudService.getAllAddGagUser(userIds,String.valueOf(groupId),"5");
		
		
		if(addGagUser.getCode()!=200){//云禁言 请求成功
			return false;
		}
		
		return true;
	}

	@Override
	public boolean quitGroup(String userId, Integer groupId) {
		ItalkGroupMember groupMember = italkGroupMemberDao.findByGroup(groupId, Integer.parseInt(userId)).get(0);
		groupMember.setIsValid(false);//isValid 判断是否在群
		italkGroupMemberDao.update(groupMember);
		CodeSuccessResult quitCode = rongCloudService.quit(userId, String.valueOf(groupId));
		if(quitCode.getCode()==200){//云禁言 请求成功
			return true;
		}else{
			
			return false;
		}
		
	}

	@Override
	public String disMiss(String userId, Integer groupId) {
		//先删除群公告
		QueryModel query = new QueryModel();
		query.clearQuery();
		query.combPreEquals("italkGroup.groupId", groupId,"groupId");
		query.combPreEquals("isValid", true);
		List<ItalkGroupNotice> groupNoticeList = dateBaseDAO.findLists(ItalkGroupNotice.class, query);
		for(ItalkGroupNotice n:groupNoticeList){
			italkGroupNoticeService.delete(n);
		}
		//再删群信息和成员
		
		List<ItalkGroupMember> groupMemberList = italkGroupMemberDao.findByGroupId(groupId);
		for(ItalkGroupMember n:groupMemberList){
			italkGroupMemberDao.delete(n);
		}
		//最后删群
		query.clearQuery();
		query.combPreEquals("isValid", true);
		query.combPreEquals("groupId", groupId);
		ItalkGroup group = (ItalkGroup) dateBaseDAO.findList(ItalkGroup.class, query).get(0);
		italkGroupDao.delete(group);
		
		
		CodeSuccessResult dissMissCode = rongCloudService.dissMiss(userId, String.valueOf(groupId));
		if(dissMissCode.getCode()==200){
			return "success";
		}else{
			
			return "file";
		}
	}

}
