package com.axp.service.group;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rongcloud.models.CodeSuccessResult;
import com.rongcloud.models.GroupInfo;
import com.rongcloud.models.TokenResult;

public interface RongCloudService {

	TokenResult getToken(String userId, String name, String portraitUri);
	
	
	CodeSuccessResult getGroupToken(String[] userId,String groupId ,String groupName);
	
	CodeSuccessResult getSyncGroup(String userId,GroupInfo[] groupInfo);
	
	public Map<String,Object> joinGroup(HttpServletRequest request,Integer groupId);
	//禁言
	CodeSuccessResult getAddGagUser(String userId, String groupId, String minute);
	//一键禁言
	CodeSuccessResult getAllAddGagUser(String[] userId, String groupId, String minute);
	//一键移除禁言
	CodeSuccessResult rollBackGagUser(String[] userId, String groupId, String minute);
	//移除禁言 单个成员
	CodeSuccessResult rollBackGagUser(String userId, String groupId);
	//踢人
	CodeSuccessResult quit(String[] userId, String groupId);
	//踢人
	CodeSuccessResult quit(String userId, String groupId);
	//解散群
	CodeSuccessResult dissMiss(String userId, String groupId);
	//单个人加群
	Map<String,Object> joinGroupOne(HttpServletRequest request,Integer groupId,String phone);
	
}
