package com.push;

import java.util.ArrayList;
import java.util.List;

import com.axp.model.MessageCenter;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class PushMessageToApp  {


	/**
	 *  发送消息到手机APP
	 * @param messages
	 * @param appId
	 * @param appkey
	 * @param master
	 * @param host
	 * @return
	 */
	public static String  pushMessage(String messages,String appId,String appkey,String master,String host) {
		
		IGtPush push = new IGtPush(host,appkey,master);
		TransmissionTemplate template = TransmissionTemplateDemo(messages,appId,appkey);

		AppMessage message = new AppMessage();
		message.setData(template);

		message.setOffline(true);
		message.setOfflineExpireTime(24 * 1000 * 3600);

		List<String> appIdList = new ArrayList<String>();


		appIdList.add(appId);


		message.setAppIdList(appIdList);

		IPushResult ret=push.pushMessageToApp(message,"MessageCenter_toApp");
		String result=ret.getResponse().toString();
		if(ret.getResponse().get("result").toString().equals("ok")){
			result+="正常：";
		}else{
			result+="异常：";
		}
		return result;
	}
	
	
	private static TransmissionTemplate TransmissionTemplateDemo(String messages,String appId,String appkey) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionType(2);
		template.setTransmissionContent(messages);
//		template.setPushInfo("actionLocKey", 3, "message", "sound", "payload",
//				"locKey", "locArgs", "launchImage");
		return template;
	}
	

	public static String pushMessageSingle(String messages,String appId,String appkey,String master,String host,String cid){
        IGtPush push = new IGtPush(host, appkey, master);
         
        TransmissionTemplate template = TransmissionTemplateDemo(messages,appId,appkey);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
 
        target.setAppId(appId);
        target.setClientId(cid);
        //用户别名推送，cid和用户别名只能2者选其一
        //String alias = "个";
        //target.setAlias(alias);
        IPushResult ret = null;
        String result=null;
        try{
            ret = push.pushMessageToSingle(message, target);
            result="正常：";
        }catch(RequestException e){
        	String requstId = e.getRequestId();
			ret = push.pushMessageToSingle(message, target,
				  requstId);
			result="异常：";
        }
        
        //successed_online用户在线，消息在线下发  	successed_offline用户离线，消息存入离线系统
        result+=ret.getResponse().toString();
		return result;
	}
	
	
	public static String pushMessageSingleForUsers(String messages,String appId,String appkey,String master,String host,String cid,String content,String title){
        IGtPush push = new IGtPush(host, appkey, master);
         //wwqm
        TransmissionTemplate template = getTemplate(appId,appkey, content, title,messages);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
 
        target.setAppId(appId);
        target.setClientId(cid);
        //用户别名推送，cid和用户别名只能2者选其一
        //String alias = "个";
        //target.setAlias(alias);
        IPushResult ret = null;
        String result=null;
        try{
            ret = push.pushMessageToSingle(message, target);
            result="正常：";
        }catch(RequestException e){
        	String requstId = e.getRequestId();
			ret = push.pushMessageToSingle(message, target,
				  requstId);
			result="异常：";
        }
        
        //successed_online用户在线，消息在线下发  	successed_offline用户离线，消息存入离线系统
        result+=ret.getResponse().toString();
		return result;
	}
	
	
	public static String pushMessageSingleForUsersGame(String messages,String appId,String appkey,String master,String host,String cid,String content,String title){
        IGtPush push = new IGtPush(host, appkey, master);
         //wwqm
        TransmissionTemplate template = TransmissionTemplateDemo(messages,appId,appkey);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();
 
        target.setAppId(appId);
        target.setClientId(cid);
        //用户别名推送，cid和用户别名只能2者选其一
        //String alias = "个";
        //target.setAlias(alias);
        IPushResult ret = null;
        String result=null;
        try{
            ret = push.pushMessageToSingle(message, target);
            result="正常：";
        }catch(RequestException e){
        	String requstId = e.getRequestId();
			ret = push.pushMessageToSingle(message, target,
				  requstId);
			result="异常：";
        }
        
        //successed_online用户在线，消息在线下发  	successed_offline用户离线，消息存入离线系统
        result+=ret.getResponse().toString();
		return result;
	}
	
	public static TransmissionTemplate getTemplate(String appId,String appKey,String content,String title,String data) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent(data);
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    payload.setBadge(1);
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("");
	    payload.addCustomMsg("payload", data);
	    
	    APNPayload.DictionaryAlertMsg alertMsg = getDictionaryAlertMsg(content,title);
	    //简单模式APNPayload.SimpleMsg 
	    payload.setAlertMsg(alertMsg);
	    //字典模式使用下者
	    //payload.setAlertMsg(getDictionaryAlertMsg());
	    template.setAPNInfo(payload);
	    return template;
	}
	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String body,String title){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(body);
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    
	    // IOS8.2以上版本支持
	    alertMsg.setTitle(title);
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
	
	
	
	public static String pushMessageSingleToList(String messages,String appId,String appkey,String master,String host,List<String> cid){
        IGtPush push = new IGtPush(host, appkey, master);
         
        TransmissionTemplate template = TransmissionTemplateDemo(messages,appId,appkey);
        ListMessage message = new ListMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        String result=null;
        List<Target> list = new ArrayList<Target>();
        for(String cids:cid){
        	 Target target = new Target();
             target.setAppId(appId);
             target.setClientId(cids);
             //用户别名推送，cid和用户别名只能2者选其一
             //String alias = "个";
             //target.setAlias(alias);
             list.add(target);
          
        }
        String taskId = push.getContentId(message,"联盟组推送");
        //successed_online用户在线，消息在线下发  	successed_offline用户离线，消息存入离线系统
        IPushResult ret = null;
        try{
            ret = push.pushMessageToList(taskId, list);
            result="正常：";
        }catch(RequestException e){
			result="异常：";
        }
        result+=ret.getResponse().toString();
        return result; 
		
	}
	
	
	public static String pushMessageToList(String title,String text,String appId,String appKey,String master,String host,String cid){
		IGtPush push = new IGtPush(host, appKey, master);
		// 通知透传模板
		NotificationTemplate template = notificationTemplateDemo(appId,appKey,title,text,"","",1,"");
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
       	 Target target = new Target();
            target.setAppId(appId);
            target.setClientId(cid);
            targets.add(target);
         
       
		
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		return "";
		
	}
	
	public static NotificationTemplate notificationTemplateDemo(String appId,String appKey,String title,String text,String logoUrl,String logo,Integer type, String content) {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);

		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(title);
		style.setText(text);
		
		// 配置通知栏图标
		style.setLogo("");
		// 配置通知栏网络图标
		style.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);

		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(content);
		return template;
	}
	
	
	
	
	public static void pushMessageSingleIOS(String messages,String appId,String appkey,String masterSecret,String host,String cid){
		IGtPush push = new IGtPush(host, appkey, masterSecret);  
		TransmissionTemplate template = TransmissionTemplateDemo(messages,appId,appkey);
        APNPayload apnpayload = new APNPayload();
        apnpayload.setSound("");
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        apnpayload.setAlertMsg(alertMsg);

        template.setAPNInfo(apnpayload);
        SingleMessage sm = new SingleMessage();
        sm.setData(template);
        IPushResult ret = push.pushAPNMessageToSingle(appId, cid, sm);
	}
	
	public static void pushMessageSingleToListIOS(MessageCenter messages,String appId,String appkey,String master,String host,List<String> cid){
		IGtPush push = new IGtPush(host, appkey, master);
	       
	    APNTemplate t = new APNTemplate();
	    APNPayload apnpayload = new APNPayload();
	    apnpayload.setSound("");
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(messages.getRemark());
	    alertMsg.setTitleLocKey(messages.getTitle());
	    alertMsg.setActionLocKey("查看");
	    apnpayload.setAlertMsg(alertMsg);
        t.setAPNInfo(apnpayload);  
        ListMessage message = new ListMessage();
        message.setData(t);
        String contentId = push.getAPNContentId(appId, message);
        IPushResult ret = push.pushAPNMessageToList(appId, contentId, cid);
	}
	
	public static void pushMessageSingleToListIOSfortest(String content,String title,String appId,String appkey,String master,String host,List<String> cid){
		IGtPush push = new IGtPush(host, appkey, master);
	       
	    APNTemplate t = new APNTemplate();
	    APNPayload apnpayload = new APNPayload();
	    apnpayload.setSound("");
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(content);
	    alertMsg.setTitleLocKey(title);
	    alertMsg.setActionLocKey("查看");
	    apnpayload.setAlertMsg(alertMsg);
        t.setAPNInfo(apnpayload);  
        ListMessage message = new ListMessage();
        message.setData(t);
        String contentId = push.getAPNContentId(appId, message);
        IPushResult ret = push.pushAPNMessageToList(appId, contentId, cid);
	}

	public static void pushMessageSingleToListIOSForNew(String content,String title,String appId,String appkey,String master,String host,List<String> cid){

		IGtPush push = new IGtPush(host, appkey, master);
	       
	    APNTemplate t = new APNTemplate();
	    APNPayload apnpayload = new APNPayload();
	    apnpayload.setSound("");
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(content);
	    alertMsg.setTitleLocKey(title);
	    alertMsg.setActionLocKey("查看");
	    apnpayload.setAlertMsg(alertMsg);
        t.setAPNInfo(apnpayload);  
        ListMessage message = new ListMessage();
        message.setData(t);
        String contentId = push.getAPNContentId(appId, message);
        IPushResult ret = push.pushAPNMessageToList(appId, contentId, cid);
	}
	
	//==============================ZL=======================================//
		private static TransmissionTemplate TransmissionTemplateSystem(String content,String appId,String appkey) {
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(appId);
			template.setAppkey(appkey);
			template.setTransmissionType(2);
			template.setTransmissionContent(content);
			return template;
		}
		
		
		//系统消息推送
		public static void  pushSystemMessageToListAPP(String title,String content,String appId,String appkey,String master,String host,List<String> cid){
			IGtPush push = new IGtPush(host, appkey, master);
			APNTemplate t = new APNTemplate();
			APNPayload apnpayload = new APNPayload();
			apnpayload.setSound("");
			APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
			alertMsg.setBody(content);
			alertMsg.setActionLocKey(title);
			alertMsg.setActionLocKey("查看");
			apnpayload.setAlertMsg(alertMsg);
			t.setAPNInfo(apnpayload);
			ListMessage message = new ListMessage();
	        message.setData(t);
			String contentId = push.getAPNContentId(appId, message);
			IPushResult ret = push.pushAPNMessageToList(appId, contentId, cid);
			
		}
		
		/**
		 *  发送消息到手机APP
		 * @param content
		 * @param appId
		 * @param appkey
		 * @param master
		 * @param host
		 * @return
		 */
		public static String  pushSystemMessage(String content,String appId,String appkey,String master,String host) {
			
			IGtPush push = new IGtPush(host,appkey,master);
			TransmissionTemplate template = TransmissionTemplateSystem(content, appId, appkey);

			AppMessage message = new AppMessage();
			message.setData(template);

			message.setOffline(true);
			message.setOfflineExpireTime(24 * 1000 * 3600);

			List<String> appIdList = new ArrayList<String>();
			appIdList.add(appId);
			message.setAppIdList(appIdList);
			IPushResult ret=push.pushMessageToApp(message,"MessageCenter_toApp");
			String result=ret.getResponse().toString();
			if(ret.getResponse().get("result").toString().equals("ok")){
				result+="正常：";
			}else{
				result+="异常：";
			}
			return result;
		}
		//=================================END====================================//
}