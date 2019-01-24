package com.push;

import java.util.List;

import com.axp.model.MessageCenter;




public interface AppInformation {

	//应用ID
	static final String appId = "Cl6ogDYaP26181vk65zKR3";
	//应用验证码
	static final String appkey = "bXf4zxUior9s9CMXNkHpJ9";
	//个推集成鉴权码
	static final String master = "xtg8UuseFtAnnE0tWqlOk9";
	//个推服务器地址
	static final String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
	//应用ID
	static final String s_appId = "6NRip6zMGq72dc4av0WOw6";
	//应用验证码
	static final String s_appkey = "6Zc4FkVBoQ63fzDWURoFq7";
	//个推集成鉴权码
	static final String s_master = "1A3xIZooqB9NRHCpm61Gy6";
	//个推服务器地址
	static final String s_host = "http://sdk.open.api.igexin.com/apiex.htm";
	
/*	//测试key
	//应用ID
	static final String appId = "yKtvNsP2nO6F0SyUtpef03";
	//应用验证码
	static final String appkey = "JMnkcgHkp991reFCQv5nQ4";
	//个推集成鉴权码
	static final String master = "HH16n5mWoz9pCpTmA2RIh8";
	//个推服务器地址
	static final String host = "http://sdk.open.api.igexin.com/apiex.htm";
	*/
	/**
	 * 将总部发出的消息发送到APP上
	 * @param messages 发送消息的内容
	 * @return 发送的结果
	 */
	public abstract  String  pushMessageCenterToApp(MessageCenter messages);
	
	/**
	 * 讲登录验证的消息发送到指定APP上
	 * @param messages 发送消息的内容
	 * @return 发送的结果
	 */
	public abstract  String  pushLoginMessageSingle(String user_id,String cid);
	public abstract  String  pushLoginMessageSingle(String user_id, String lastCid, String beforeCid);
	public String pushMessage(String user_id, String lastCid,String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ) ;
	
	public String pushMessageToList(String user_id, String lastCid,String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ) ;
		
	/* *
	 * 发送消息给指定用户
	 * 
	 * */
	public abstract String pushMessage(String user_id, String lastCid, String beforeCid,String title,String message);
	
	

	/**
	 * 讲登录验证的消息发送到指定APP上
	 * @param messages 发送消息的内容
	 * @return 发送的结果
	 */
	public abstract  void  pushLoginMessageSingleIOS(String user_id,String cid);
	
	/**
	 * 讲登录验证的消息发送到指定APP集合上
	 * @param messages 发送消息的内容
	 * @param cid APP集合
	 * @param date 发送时间
	 * @return 发送的结果
	 */
	public abstract  String  pushMessageSingleToList(MessageCenter messages,List<String> cid);
	
	/**
	 * 讲登录验证的消息发送到指定APP集合上
	 * @param messages 发送消息的内容
	 * @param cid APP集合
	 * @param date 发送时间
	 * @return 发送的结果
	 */
	public abstract  void  pushMessageSingleToListIOS(MessageCenter messages,List<String> cid);
	

	/**
	 * 将要发送的消息转成JsonArray字符串
	 * @param messages		需要转的消息
	 * @return 				消息 的JsonArray字符串
	 */
	public abstract String MessageToJson(MessageCenter messages);
	
	/**
	 *将要发送的消息转成JsonArray字符串
	 * @param userid    
	 * @param user_id   
	 * @return
	 */
	public abstract String UserToJson(String userid,String user_id);
	
	

	public abstract String UserToJson(String userId, String channelId, String message,
			String title, String taskCode);
	
	
	/**
	 * 将系统消息发送到指定App集合上
	 * @param messages 发送消息的内容
	 * @param cid APP集合
	 * @return 发送的结果
	 * */
	
	public abstract void pushSystemMessageToListAPP(String title ,String content,List<String> cid);
	
	/**
	 * 将总部发出的消息发送到APP上
	 * @param messages 发送消息的内容
	 * @return 发送的结果
	 */
	public abstract  String  pushSystemMessageCenterToApp(String title,String content);
	

	
	public abstract String pushMessageForUsers(String user_id, String lastCid, String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ,String tokenId );
		
	String pushMessageToAdminUser(String user_id, String lastCid, String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage );

	public abstract String pushMessageForUsersByGame(String user_id, String lastCid, String title, String message, String msgId, String msg_unread_count, String sellermessage ,String gameBroadContents,String tokenId);

	/**
	 * @param user_id
	 * @param lastCid
	 * @param title
	 * @param message
	 * @param msgId
	 * @param typeId
	 * @param msg_unread_count
	 * @param sellermessage
	 * @return
	 */
	String pushMessageGame(String user_id, String lastCid, String title,
			String message, String msgId, String typeId,
			String msg_unread_count, String sellermessage);

	/**
	 * @param user_id
	 * @param lastCid
	 * @param title
	 * @param message
	 * @param msgId
	 * @param typeId
	 * @param msg_unread_count
	 * @param sellermessage
	 * @param tokenId
	 * @return
	 */
	String pushMessageForUsersGame(String user_id, String lastCid,
			String title, String message, String msgId, String typeId,
			String msg_unread_count, String sellermessage, String tokenId,List<String>gameBroadContents);		
}
