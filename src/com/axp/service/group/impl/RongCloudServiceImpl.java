package com.axp.service.group.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.DataBaseDao;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ItalkGroupDao;
import com.axp.dao.TkldPidDao;
import com.axp.model.ItalkGroup;
import com.axp.model.Parameter;
import com.axp.model.TkldPid;
import com.axp.model.Users;
import com.axp.service.group.ItalkGroupMemberService;
import com.axp.service.group.RongCloudService;
import com.axp.service.invoke.impl.BaseServiceImpl;
import com.axp.util.QueryModel;
import com.rongcloud.RongCloud;
import com.rongcloud.models.CodeSuccessResult;
import com.rongcloud.models.GroupInfo;
import com.rongcloud.models.TokenResult;
@Service
public class RongCloudServiceImpl  implements RongCloudService{
	@Autowired
	private ItalkGroupDao italkGroupDao;
	
	@Autowired
	private TkldPidDao tkldPidDao;
	@Autowired
	private ItalkGroupMemberService italkGroupMemberService;
	
	@Autowired
	 private DateBaseDAO dateBaseDAO;
	


	private final String appKey = "0vnjpoad062fz";//替换成您的appkey
	private final String appSecret = "QyQI79nMxYcMHq";//替换成匹配上面key的secret da
	
	Reader reader = null ;
	RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
	
	@Override
	public TokenResult getToken(String userId, String name, String portraitUri) {
		 TokenResult userGetTokenResult=null;
		try {
    	  // 获取 Token 方法 
    	   userGetTokenResult = rongCloud.user.getToken(userId, name, portraitUri);
    	  return userGetTokenResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGetTokenResult;
	}

	@Override
	public CodeSuccessResult getGroupToken(String[] userId, String groupId,
			String groupName) {
		CodeSuccessResult getGroupToken = null;
		try {
			getGroupToken = rongCloud.group.create(userId, groupId, groupName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getGroupToken;
	}

	@Override
	public CodeSuccessResult getSyncGroup(String userId, GroupInfo[] groupInfo) {
		CodeSuccessResult getSyncGroup = null;
		try {
			getSyncGroup = rongCloud.group.sync(userId, groupInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getSyncGroup;
	}

	@Override
	public Map<String, Object> joinGroup(HttpServletRequest request,Integer groupId) {
		
		ItalkGroup group = italkGroupDao.findByGroupId(groupId).get(0);
		String groupName = group.getName();//群名字
		Integer level = group.getGroupType();//群类别
		
		List<TkldPid> tkldPidList =  tkldPidDao.findParentTkldPid(level);
		List<String> tkList = new ArrayList<String>();
		List<Users> userList = new ArrayList<Users>();
		//取对应的level的 user
		for(TkldPid t :tkldPidList){
			if(t.getUsers() == null){
				continue;
			}else{
				
			userList.add(t.getUsers());
			
			tkList.add(String.valueOf(t.getUsers().getId()));
			}
		}
		
		
		String[] userIds = (String[])tkList.toArray(new String[tkList.size()]);
		
		Map<String,Object> getJoinCode = new HashMap<String,Object>();
    	CodeSuccessResult joinCode = null;
		try {
			joinCode = rongCloud.group.join(userIds,String.valueOf(groupId) , groupName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(joinCode.getCode()==200){
			//保存群成员信息 可以返回保存信息
    		String joinString = null;
    		joinString = italkGroupMemberService.selfGroup(groupId, userList);
    		
			getJoinCode.put("code", joinCode.getCode());
		}else{
			getJoinCode.put("code", joinCode.getCode());
    		getJoinCode.put("message", joinCode.getErrorMessage());
		}
		
		return getJoinCode;
	}

	@Override
	public CodeSuccessResult getAddGagUser(String userId, String groupId,
			String minute) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.addGagUser(userId, groupId, minute);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public CodeSuccessResult rollBackGagUser(String userId, String groupId) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.rollBackGagUser(userId, groupId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public CodeSuccessResult getAllAddGagUser(String[] userIds, String groupId,
			String minute) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.addGagUser(userIds, groupId, minute);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public CodeSuccessResult rollBackGagUser(String[] userId, String groupId,
			String minute) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.rollBackGagUser(userId, groupId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public CodeSuccessResult quit(String userId, String groupId) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.quit(userId, groupId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public CodeSuccessResult quit(String[] userId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeSuccessResult dissMiss(String userId, String groupId) {
		CodeSuccessResult code = null;
		try {
			code = rongCloud.group.dismiss(userId, groupId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public Map<String,Object> joinGroupOne(HttpServletRequest request,
			Integer groupId,String phone) {
		
		
		ItalkGroup group = italkGroupDao.findByGroupId(groupId).get(0);
		String groupName = group.getName();//群名字
		
		QueryModel query = new QueryModel();
		query.clearQuery();
		query.combPreEquals("phone", phone);
		query.combPreEquals("isValid",true );
		List<Users> user =  dateBaseDAO.findLists(Users.class, query);//可能存在的是一个手机号码绑定了多个用户 存在多个id
		
		
		String [] users = new String[1];
		users[0] = user.get(0).getId().toString();
		Map<String,Object> getJoinCode = new HashMap<String,Object>();
    	CodeSuccessResult joinCode = null;
		try {
			joinCode = rongCloud.group.join(users,String.valueOf(groupId) , groupName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(joinCode.getCode()==200){
			getJoinCode.put("code", joinCode.getCode());
		}else{
			getJoinCode.put("code", joinCode.getCode());
    		getJoinCode.put("message", joinCode.getErrorMessage());
		}
		
		return getJoinCode;
		
	}
	
	
	

	

	
	
}
