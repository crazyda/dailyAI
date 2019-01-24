package com.axp.service.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.ItalkGroup;


public interface ItalkGroupService  {
	/**
	 * 拉人入群
	 * @param request
	 */
	String creategroup(HttpServletRequest request) ;
	/**
	 * 通过userID
	 * 查找对应的群
	 * @param request
	 * @return 
	 */
	List<ItalkGroup> findByUserId(HttpServletRequest request);
	/**
	 * 把相同身份的人 加相同的群
	 * @param groupId
	 * @return
	 */
	String selfAddGroup(Integer groupId);
	
	boolean isForbid(Integer groupId,Integer userId,Integer isForbid);
	/**
	 * 一键禁言
	 * @param groupId
	 * @return
	 */
	boolean allIsForbid(Integer groupId);
	/**
	 * 一键解除禁言
	 * @param groupId
	 * @return
	 */
	boolean allIsForbid2(Integer groupId);
	/**
	 * 退出群
	 * @param userId
	 * @param groupId
	 * @return
	 */
	boolean quitGroup(String userId, Integer groupId);
	/**
	 * 解散群
	 * @param userId
	 * @param groupId
	 */
	String disMiss(String userId, Integer groupId);
	
}
