package com.axp.service.system;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.Users;
import com.axp.service.system.IBaseService;

public interface UserSystemMessageService extends IBaseService{

	void list(HttpServletRequest request,HttpServletResponse response);
	
	void add(Integer id,HttpServletRequest request);

	void saveallmessage(Integer userId, Integer orderId, String typeId,
			String content, String title,HttpServletRequest request);
	
	
	void saveMessages(HttpServletRequest request,Integer orderId,
			Integer userId,String typeId,String content,String title,String province,String city,String county,String image,String icon);
	
	void savesysmessage( String typeId, String content,
			String title, HttpServletRequest request);
	
	void saveordmessage(Integer userId,Integer orderId, String typeId, String content,
			String title, HttpServletRequest request);
	
	void saveAssetsmessage(Integer userId, String typeId, String content,
			String title, HttpServletRequest request);
	
	void push(String title ,String content,String cid,HttpServletRequest request);
    
	public void push_message(String content,String title,String cid);
	Map<String, Object> saveMessage(String content ,Integer type ,String title,List<Users> ulist,String orderId,double money,Integer state);
	
	
	/**
	 * 最新系统消息 chy
	 */
	public void saveSystemMessage(String content, Integer type, String title,
			 double money,Integer state,List<String> gameBroadContents);
	
	public void saveActivityMessage(HttpServletRequest request,String content, Integer type, String title,
			 double money,Integer state,String province, String city, String county,String image,String icon);

	

	/**
	 * @param userId
	 * @param orderId
	 * @param typeId
	 * @param content
	 * @param title
	 * @param request
	 * @param gameBroadContents
	 */
	void saveallmessageGame(Integer userId, Integer orderId, String typeId,
			String content, String title, HttpServletRequest request,
			List<String> gameBroadContents);

	
}
