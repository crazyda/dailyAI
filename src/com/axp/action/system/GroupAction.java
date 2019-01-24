package com.axp.action.system;



import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.model.AdminUser;
import com.axp.model.ItalkGroup;
import com.axp.model.ItalkGroupMember;
import com.axp.model.TkldPid;
import com.rongcloud.models.CodeSuccessResult;


/*
 * 群管理功能
 * @author Administrator
 * */
@Controller
@RequestMapping("/system/group")
public class GroupAction extends BaseAction{
	
	
	
	@RequestMapping("/Group")
	public String GroupView(HttpServletRequest request,HttpServletResponse response){
		return "group/addGroup";
		
		
	}
	
	
	/**
	 * 创建群
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/createGroup")
	public String createGroup(HttpServletRequest request,HttpServletResponse response){
		
		String data = italkGroupService.creategroup(request);
		return  "redirect:groupList";
		
	}
	/**
	 * 群列表信息
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedPermission(permissionName="群管理",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="群管理")
	@RequestMapping("/groupList")
	public String groupList(HttpServletRequest request,HttpServletResponse response){
		
		
		List<ItalkGroup> groupList = italkGroupService.findByUserId(request);
		if(groupList.size()>0){//已经有群了
			request.setAttribute("groupList", groupList);
			return "group/groupDetail/groupList";
			
		}else{//没有群
			return "group/addGroup";
		}
	}
	
	
	/**
	 *  拉人入群  并且判断给用户是否已经在群内
	 * @param request
	 * @param response
	 * @param groupId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/addGroup")
	@ResponseBody
	public String addGroup(HttpServletRequest request,HttpServletResponse response,Integer groupId,String userphone){
		Map<String,Object> joinGroup = rongCloudService.joinGroupOne(request,groupId,userphone);
		
		return italkGroupMemberService.addGroup(groupId, userphone);
		
		
		
		
	}
	
	/**
	 * 群成员列表
	 */
	@RequestMapping("/groupEmp")
	public String groupEmp(HttpServletRequest request,HttpServletResponse response,Integer groupId){
		
		List<ItalkGroupMember> igMember = italkGroupMemberService.findByGroupId(groupId);
		
		request.setAttribute("groupId", groupId);
		request.setAttribute("igMember", igMember);
		return "group/groupDetail/groupEmp";
		
	}
	
	/**
	 * 自动把 相同等级的user 加入对应的群
	 */
	@RequestMapping("/selfAddGroup")
	public String selfAddGroup(HttpServletRequest request,HttpServletResponse response,Integer groupId){
		
		Map<String,Object> joinGroup = rongCloudService.joinGroup(request,groupId);
		return "redirect:groupList";
	}
	
	/**
	 * 单个禁言 
	 * @param request
	 * @param response
	 * @param userId  成员id
	 * @return
	 */
	@RequestMapping("/isForbid")
	@ResponseBody
	public String isForbid(HttpServletRequest request,HttpServletResponse response,Integer groupId,String userId,Integer forbid){
		
		boolean flag = italkGroupService.isForbid(groupId,Integer.parseInt(userId),forbid);
		if(flag){
			return "true";
			
		}else{
			return "false";
		}
	}
	
	/**
	 * 一键全体禁言
	 */
	@RequestMapping("/allIsForbid")
	public String allIsForbid(HttpServletRequest request,HttpServletResponse response,Integer groupId){
		
		italkGroupService.allIsForbid(groupId);
		
		return "redirect:groupEmp?groupId="+String.valueOf(groupId);
		
	}
	/**
	 * 一键全体解除禁言
	 */
	@RequestMapping("/allIsForbid2")
	public String allIsForbid2(HttpServletRequest request,HttpServletResponse response,Integer groupId){
		
		italkGroupService.allIsForbid2(groupId);
		
		return "redirect:groupEmp?groupId="+String.valueOf(groupId);
		
	}
	/**
	 * 退群
	 */
	@RequestMapping("/quitGroup")
	@ResponseBody
	public String quitGroup(Integer groupId,String userId){
		italkGroupService.quitGroup(userId,groupId);
		
		return "redirect:groupEmp?groupId="+String.valueOf(groupId);
		
	}
	/**
	 * 解散群
	 */
	@RequestMapping("/dismiss")
	@ResponseBody
	public String dissMissGroup(HttpServletRequest request,Integer groupId){
		Integer userId =  (Integer) request.getSession().getAttribute("currentUserId");
		String dissMiss = italkGroupService.disMiss(String.valueOf(userId),groupId);
		
		return "redirect:groupList";
	} 
	
}
