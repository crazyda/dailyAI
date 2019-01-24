package com.push;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.axp.model.MessageCenter;

@Service
public class ImpAppInformation implements AppInformation {

	@Override
	public String pushMessageCenterToApp(MessageCenter messages) {
		// TODO Auto-generated method stub
		return PushMessageToApp.pushMessage(MessageToJson(messages),appId,appkey,master,host);
	}

	@Override
	public String pushLoginMessageSingle(String user_id,String lastCid,String beforeCid) {
		// TODO Auto-generated method stub
		System.out.println("单点登录开始");
		String taskCode = "1000";
		String title = "单点登录";
		String message = "此账号在另一台设备中登录，被迫下线，如非本人操作，请尽快修改密码";
		String data = UserToJson(user_id, lastCid, message, title, taskCode);
		String result = PushMessageToApp.pushMessageSingle(data,appId,appkey,master,host, beforeCid);
		return result;
	}
	
	@Override
	public String pushLoginMessageSingle(String user_id,String cid) {
		// TODO Auto-generated method stub
		return PushMessageToApp.pushMessageSingle(UserToJson(user_id, cid),appId,appkey,master,host, cid);
	}
	
	
	@Override
	public String pushMessageSingleToList(MessageCenter messages,List<String> cid) {
		// TODO Auto-generated method stub
		return PushMessageToApp.pushMessageSingleToList(MessageToJson(messages),appId,appkey,master,host, cid);
	}


	@Override
	public String MessageToJson(MessageCenter messages) {
		// TODO Auto-generated method stub
		return MessageToJsonUtil.MessageToJson(messages);
	}

	@Override
	public String UserToJson(String user_id, String userid) {
		// TODO Auto-generated method stub
		return MessageToJsonUtil.UserToJson(user_id, userid);
	}
	
	@Override
	public String UserToJson(String userId,String channelId,String message,String title,String taskCode) {
		// TODO Auto-generated method stub
		return MessageToJsonUtil.UserToJson(userId, channelId, message, title, taskCode);
	}

	@Override
	public void pushLoginMessageSingleIOS(String user_id, String cid) {
		// TODO Auto-generated method stub
		PushMessageToApp.pushMessageSingleIOS(UserToJson(user_id, cid),appId,appkey,master,host, cid);
	}

	@Override
	public void pushMessageSingleToListIOS(MessageCenter messages,
			List<String> cid) {
		// TODO Auto-generated method stub
		PushMessageToApp.pushMessageSingleToListIOS(messages, appId, appkey, master, host, cid);
		
	}
	
	

	@Override
	public String pushMessage(String user_id, String lastCid, String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ) {
			System.out.println("-----------------start3000--------------");
			String taskCode = "3000";
			String data = MessageToJsonUtil.MessageToJson(user_id, lastCid, message, title, taskCode,msgId,typeId,msg_unread_count,sellermessage);	
			String result = PushMessageToApp.pushMessageSingleForUsers(data,appId,appkey,master,host, lastCid,title,sellermessage);
			System.out.println("-----------------end--------------");
			return result;
	}
	
	@Override
	public String pushMessageGame(String user_id, String lastCid, String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ) {
			System.out.println("-----------------start4000--------------");
			String taskCode = "4000";
			String data = MessageToJsonUtil.MessageToJson(user_id, lastCid, message, title, taskCode,msgId,typeId,msg_unread_count,sellermessage);	
			String result = PushMessageToApp.pushMessageSingleForUsers(data,appId,appkey,master,host, lastCid,title,sellermessage);
			System.out.println("-----------------end--------------");
			return result;
	}
	
	@Override
	public String pushMessageToAdminUser(String user_id, String lastCid, String title, String message,String msgId,String typeId,String msg_unread_count,String sellermessage ) {
			System.out.println("-----------------start 3000admin--------------");
			String taskCode = "3000";
			String data = MessageToJsonUtil.MessageToJson(user_id, lastCid, message, title, taskCode,msgId,typeId,msg_unread_count,sellermessage);	
			String result = PushMessageToApp.pushMessageSingleForUsers(data,s_appId,s_appkey,s_master,s_host, lastCid,title,sellermessage);
			System.out.println("-----------------end--------------");
			return result;
	}
	
	
	public  static void main(String [] args){
		ImpAppInformation ia = new ImpAppInformation();
		//ia.pushMessage("811", "902a68fca1f80b00991b3b3cf119d539", "显示标题", "测试内容", "1", "1", "1", "显示信息");
		ia.pushMessageToAdminUser("716", "f1cae0d7ea1239bb5ae16e496a648e3c", "响了说一声", "测试内容", "1", "1", "1", "显示信息");
	}
	
	
	
	//==================================ZL===============================================//
		//消息推送
		@Override
		public void pushSystemMessageToListAPP(String title ,String content,
				List<String> cid) {
			PushMessageToApp.pushSystemMessageToListAPP(title, content, appId, appkey, master,host, cid);
		}
		
		@Override
		public String pushSystemMessageCenterToApp(String title ,String content) {
			return PushMessageToApp.pushSystemMessage(content, appId, appkey, master,host);
		}

		@Override
		public String pushMessage(String user_id, String lastCid,
				String beforeCid, String title, String message) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String pushMessageToList(String user_id, String lastCid,
				String title, String message, String msgId, String typeId,
				String msg_unread_count, String sellermessage) {
			// TODO Auto-generated method stub
			return null;
		}


		//========================================end=======================================//

		@Override
		public String pushMessageForUsers(String user_id, String lastCid,
				String title, String message, String msgId, String typeId,
				String msg_unread_count, String sellermessage, String tokenId) {
			System.out.println("-------------------------s-----------"+lastCid);
			String taskCode = "3000";
			String data = MessageToJsonUtil.MessageToJson(user_id, lastCid, message, title, taskCode,msgId,typeId,msg_unread_count,sellermessage);	
			String result = PushMessageToApp.pushMessageSingleForUsers(data,appId,appkey,master,host, lastCid,title,sellermessage);
			System.out.println("-------------------------e-----------"+lastCid);
			return result;
			
		}
		@Override
		public String pushMessageForUsersGame(String user_id, String lastCid,
				String title, String message, String msgId, String typeId,
				String msg_unread_count, String sellermessage, String tokenId,List<String>gameBroadContents) {
			System.out.println("-------------------------sUsersGame-----------"+lastCid);
			String taskCode = "4000";
			String data = MessageToJsonUtil.MessageToJsonGame(user_id, lastCid, message, title, taskCode,msgId,typeId,msg_unread_count,sellermessage,gameBroadContents);	
			String result = PushMessageToApp.pushMessageSingleForUsers(data,appId,appkey,master,host, lastCid,title,sellermessage);
			System.out.println("-------------------------e-----------"+lastCid);
			return result;
			
		}

		/* (non-Javadoc)
		 * @see com.push.AppInformation#pushMessageForUsers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		
		@Override
		public String pushMessageForUsersByGame(String user_id, String lastCid,
				String title, String message, String msgId,
				String msg_unread_count, String sellermessage,String gameBroadContents, String tokenId) {
			System.out.println("-----------------start--------------");
			String taskCode = "3000";
			String data = MessageToJsonUtil.MessageToJson(user_id, lastCid, message, title, taskCode,msgId,null,msg_unread_count,sellermessage);
			String result = PushMessageToApp.pushMessageSingleForUsers(data,appId,appkey,master,host, lastCid,title,sellermessage);
			System.out.println("-----------------end--------------");
			return result;
		}

}
