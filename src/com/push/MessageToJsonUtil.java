package com.push;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.model.MessageCenter;
import com.axp.util.StringUtil;


public class MessageToJsonUtil {
	
	public static final String SINGLE_ON = "1000"; 

	//[{"data":"[{}]"},{}] {date:aaa}
	public static String MessageToJson(MessageCenter messages) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray1=new JSONArray();
		JSONArray jsonArray=new JSONArray();
		HashMap<String, String> message=new HashMap<String, String>();
		message.put("taskCode", "2");
		HashMap<String, String> date=new HashMap<String, String>();
		//消息ID
		date.put("id", messages.getId().toString());
		//消息标题
		date.put("title", messages.getTitle()==null?"":messages.getTitle());
		//消息封面
		date.put("imageUrl", messages.getCover());
		//消息时间
		date.put("date",df.format(messages.getCreateTime()));
		//消息发布人
		date.put("author", messages.getAuthor());
		//消息说明
		date.put("remark", messages.getRemark()==null?"":messages.getRemark());
		jsonArray1.add(date);
		message.put("data", jsonArray1.toString());
		jsonArray.add(message);
//		System.out.println("发送信息为："+jsonArray.toString());
		return jsonArray.toString();
	}
	
	public static String UserToJson(String user_id,String userid) {
		// TODO Auto-generated method stub
		JSONArray jsonArray1=new JSONArray();
		JSONArray jsonArray=new JSONArray();
		JSONObject message=new JSONObject();
		message.put("taskCode", "0");
		JSONObject date=new JSONObject();
		message.put("user_id", user_id);
		message.put("userid", userid);
		jsonArray1.add(date);
		message.put("data", jsonArray1.toString());
		jsonArray.add(message);
//		System.out.println("发送信息为："+jsonArray.toString());
		return jsonArray.toString();
	}
	
	public static String UserToJson(String userId,String channelId,String message,String title,String taskCode) {
		JSONObject object = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("userId", userId);
		data.put("channelId", channelId);
		data.put("message", message);
		object.put("data",data);
		object.put("taskCode",taskCode);
		object.put("message",title);
		return object.toString();
	}

	public static String MessageToJson(String userId,String channelId,String message,String title,String taskCode,String msgId,String typeId,String msg_unread_count,String sellermessage) {
		JSONObject object = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("userId", userId);
		data.put("channelId", channelId);
		data.put("message", sellermessage);
		data.put("typeId", typeId);
		data.put("msgId", msgId);
		data.put("msg_unread_count", msg_unread_count);
		data.put("message", message);
		
		object.put("data",data);
		object.put("taskCode",taskCode);
		object.put("message",title);
		return object.toString();
	}
	
	public static String MessageToJsonGame(String userId,String channelId,String message,String title,String taskCode,String msgId,String typeId,String msg_unread_count,String sellermessage,List<String>gameBroadContents) {
		JSONObject object = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("userId", userId);
		data.put("channelId", channelId);
		data.put("message", sellermessage);
		data.put("typeId", typeId);
		data.put("msgId", msgId);
		data.put("msg_unread_count", msg_unread_count);
		data.put("message", message);
		data.put("gameBroadContents", gameBroadContents);
		object.put("data",data);
		object.put("taskCode",taskCode);
		object.put("message",title);
		return object.toString();
	}
	
	
	
	
	public static String MessageToJson(String userId,String channelId,String message,String title,String taskCode,String msgId,String msg_unread_count,String sellermessage) {
		JSONObject object = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("userId", userId);
		data.put("channelId", channelId);
		data.put("message", sellermessage);
		data.put("msgId", msgId);
		data.put("msg_unread_count", msg_unread_count);
		data.put("message", message);
		
		object.put("data",data);
		object.put("taskCode",taskCode);
		object.put("message",title);
		
		return object.toString();
	}
	
	//[{"data":"[{}]"},{}] {date:aaa}
		public static String MessageToJsonForNew(String content ,String title,String msgId,String imageUrl,String createtime,String author,String remark) {
			// TODO Auto-generated method stub
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JSONArray jsonArray1=new JSONArray();
			JSONArray jsonArray=new JSONArray();
			HashMap<String, String> message=new HashMap<String, String>();
			message.put("taskCode", "2");
			HashMap<String, String> date=new HashMap<String, String>();
			//消息ID
			date.put("id", msgId);
			//消息标题
			date.put("title", title);
			//消息封面
			date.put("imageUrl", imageUrl);
			//消息时间
			date.put("date",createtime);
			//消息发布人
			date.put("author",author);
			//消息说明
			date.put("remark", content);
			jsonArray1.add(date);
			message.put("data", jsonArray1.toString());
			jsonArray.add(message);
//			System.out.println("发送信息为："+jsonArray.toString());
			return jsonArray.toString();
		}
	
}
