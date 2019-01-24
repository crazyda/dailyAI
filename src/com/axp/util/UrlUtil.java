package com.axp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axp.dao.CaptchaDAO;
import com.axp.dao.CautionDAO;
import com.axp.model.Captcha;



@Component
public class UrlUtil {
    @Autowired
    CaptchaDAO captchaDao;
    @Autowired
    CautionDAO cautionDao;



	
	 /**
	 * @param moneySum 输入总金额
	 * @param redNum 输入红包数量
	 */
	 private static void wxAlgorithm(double moneySum, int redNum) {
	 // 设置最小的金额
	 double moneyMin = 0.01;
	 Random random = new Random();
	 //精确小数点2位
	 NumberFormat formatter = new DecimalFormat("#.##");
	 for (int i=1;i<redNum;i++)
	 {
	   //随机一个数，数值范围在最小值与余额之间
	   String money = formatter.format(random.nextDouble() * (moneySum - moneyMin) + moneyMin);
	   //数值转换
	       moneySum = Double.valueOf(formatter.format(moneySum - Double.valueOf(money)));
	   
	   System.out.println("第"+i+"个红包：" + money + "元 ,余额：" + moneySum);
	 }
	  
	 System.out.println("最后个红包：" + moneySum + "元 ,余额：" + (moneySum - moneySum));
	 }
	  
	 
	
		 void hb(double total,int num,double min){
		 for(int i=1;i<num;i++){
		  double safe_total=(total-(num-i)*min)/(num-i);
		  double money=Math.random()*(safe_total-min)+min;
		  BigDecimal money_bd=new BigDecimal(money);
		  money=money_bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		  total=total-money;
		  BigDecimal total_bd=new BigDecimal(total);
		  total=total_bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		  System.out.println("第"+i+"个红包："+money+",余额为:"+total+"元");
		 }
		 System.out.println("第"+num+"个红包："+total+",余额为:0元");
		 }

	
	
	public static List<Map<String, String>> getJson(int userid) {
		String url = "http://www.app258.com/weixinpl/interface/userdetail_utf8.php?user_id=" + userid;
		URL yahoo;
		List<Map<String, String>> map = null;
		try {
			yahoo = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));

			String inputLine;
			String json = "";
			while ((inputLine = in.readLine()) != null) {
				json += inputLine;
			}
			if ("".equals(json)) {
				return null;
			}
			map = jsonStringToList(URLDecoder.decode(json, "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
	
	
	public static Map<String, String> getTaoKeJson(String taoke) {
		String url = taoke+"/index.php?s=/Service/getData";
		URL yahoo;
		Map<String, String> map = null;
		try {
			yahoo = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));

			String inputLine;
			String json = "[";
			while ((inputLine = in.readLine()) != null) {
				json += inputLine;
			}
			json+="]";
			if ("".equals(json)) {
				return null;
			}
			map = jsonStringToMap(URLDecoder.decode(json, "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
	
	
	public static Map<String, Object> getTaoKeToMap(String url) {
		URL yahoo;
		Map<String, Object> map = null;
		try {
			yahoo = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream(),"utf-8"));

			String inputLine;
			String json = "";
			while ((inputLine = in.readLine()) != null) {
				json += inputLine;
			}
			json=json.trim();
			if(!json.startsWith("{")){
				json=json.substring(1);
			}
			
			
			if ("".equals(json)) {
				return null;
			}
			map = jsonObjectToMap(URLDecoder.decode("["+json+"]", "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	public static Map<String, Object> getTaoKeToMapForSet(String url) {
		URL yahoo;
		Map<String, Object> map = null;
		try {
			yahoo = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yahoo.openStream()));

			String inputLine;
			String json = "[";
			while ((inputLine = in.readLine()) != null) {
				json += inputLine;
			}
			json+="]";
			if ("".equals(json)) {
				return null;
			}
			
			map = jsonObjectToMap2(URLDecoder.decode(json, "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	

	public static void main(String [] args){
		UrlUtil uu =new  UrlUtil();
		//uu.getTaoKeJson("");
		 uu.hb(1000, 5000, 0.08);
		
	}
	
	
	public static void getCode(String str) {
		try {
			byte[] temp = str.getBytes("utf-8");
			byte[] newtemp = new String(temp, "utf-8").getBytes("gbk");
			String newStr = new String(newtemp, "gbk");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String htmlEncode(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int c = (int) str.charAt(i);
			if (c > 127 && c != 160) {
				sb.append("&#").append(c).append(";");
			} else {
				sb.append((char) c);
			}
		}
		return sb.toString();
	}

	public static List<Map<String, String>> jsonStringToList(String rsContent)
			throws Exception {

		JSONArray arry = JSONArray.fromObject(rsContent);
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {

				String key = (String) iter.next();

				String value = jsonObject.get(key).toString();

				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	public static List<Map<String, Object>> jsonObjectToList(String rsContent)
			throws Exception {
		rsContent=rsContent.replaceAll("null", "0");
		
		JSONArray arry = JSONArray.fromObject(rsContent);
		List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				
				String key = (String) iter.next();
				Object str ="";
				try{
				str =  jsonObject.get(key);
				map.put(key, str);
				}catch(Exception e){
					
					e.printStackTrace();
					 str ="";
					 map.put(key, str);
				}
			
				
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	public static List<Map<String, String>> jsonStringToListByTaoke(String rsContent,String taoke)
			throws Exception {

		JSONArray arry = JSONArray.fromObject(rsContent);
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {

				String key = (String) iter.next();
				
				String value = jsonObject.get(key).toString();
				if("uri".equals(key)){
					value=taoke.concat(value);
				}
				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	public static List<Map<String, String>> jsonStringToListByTaokeForPid(String rsContent,String taoke,String pid)
			throws Exception {

		JSONArray arry = JSONArray.fromObject(rsContent);
		List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			Map<String, String> map = new HashMap<String, String>();
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {

				String key = (String) iter.next();
				
				String value = jsonObject.get(key).toString();
				if("uri".equals(key)){
					value=taoke.concat(value).concat("&pid=").concat(pid);
				}

				
				map.put(key, value);
			}
			rsList.add(map);
		}
		return rsList;
	}
	
	public static Map<String, String> jsonStringToMap(String rsContent)
			throws Exception {

		JSONArray arry = JSONArray.fromObject(rsContent);
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = jsonObject.get(key).toString();
				map.put(key, value);
			}
			
		}
		return map;
	}
	
	public static Map<String, Object> jsonObjectToMap(String rsContent)
			throws Exception {
		
		JSONArray arry = JSONArray.fromObject(rsContent);
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				if("data".equals(key)){
					String datavalue= "["+jsonObject.get(key).toString()+"]";
					Map<String, Object> datamap = new HashMap<String, Object>();
					datamap=	 jsonObjectToMap2(datavalue);
					map.put(key, datamap);
					
				}else{
					Object value = jsonObject.get(key)==null?"":jsonObject.get(key);
					map.put(key, value);
				}
				
				
			}
			
		}
		return map;
	}
	
	public static Map<String, Object> jsonObjectToMap2(String rsContent)
			throws Exception {
		JSONArray arry = JSONArray.fromObject(rsContent);
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < arry.size(); i++) {
			JSONObject jsonObject = arry.getJSONObject(i);
			
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				if("orders".equals(key)||"partners".equals(key) ||"summary".equals(key) ){
					String datavalue= jsonObject.get(key).toString();
					
					List<Map<String, Object>> datamap1 = new ArrayList<Map<String, Object>>();
					if(StringUtils.isNotBlank(datavalue)){
					
					datamap1=	 jsonObjectToList(datavalue);
					map.put(key, datamap1);
					}else{
						map.put(key, "");
					}
					
				}else{
					Object value = jsonObject.get(key);
					map.put(key, value);
				}
				
				
			}
			
		}
		return map;
	}
	
	
	private String sendMessage2(String phone,String content){//移动平台
    	String status ="";
    	content = content.substring(5);//去掉“【每天积分】”的签名
    	String url ="http://jifen.aixiaoping.cn:8080/sendMessage/servlet/Send?phone="+phone+"&content="+content;
    	
    	List<Map<String, String>> map=null;
		try {
			url=URLDecoder.decode(url,"UTF-8");
	    	URL yahoo;
	    	
			yahoo = new URL(url);
			 BufferedReader in = new BufferedReader(  
		                new InputStreamReader(  
		                yahoo.openStream()));  
		  
		    String inputLine;  
		    
		    String json="";
		    while ((inputLine = in.readLine()) != null)  {
		    	json+=inputLine;
		    }
		    
		    map = jsonStringToList(URLDecoder.decode(json,"UTF-8"));
		    in.close();  
		}catch(Exception e){
		    	e.printStackTrace();
		    }
    	
		if(map!=null){
			if("0".equals(map.get(0).get("status"))){
				status="Success";
			}
		}
		
		
    	return status;
    }
	
	public String pushMessage(String title,String text,String logoUrl,String logo,String content,String cids){//移动平台
    	String status="";
    	String url ="http://jifen.aixiaoping.cn:8080/sendMessage/servlet/push?p_title="+title+"&p_text="+text+"&p_content="+content+"&p_cids="+cids;
    	
    	List<Map<String, String>> map=null;
		try {
			url=URLDecoder.decode(url,"UTF-8");
	    	URL yahoo;
	    	
			yahoo = new URL(url);
			 BufferedReader in = new BufferedReader(  
		                new InputStreamReader(  
		                yahoo.openStream()));  
		  
		    String inputLine;  
		    
		    String json="";
		    while ((inputLine = in.readLine()) != null)  {
		    	json+=inputLine;
		    }
		    
		    map = jsonStringToList(URLDecoder.decode(json,"UTF-8"));
		    in.close();  
		}catch(Exception e){
		    	e.printStackTrace();
		    }
    	
		if(map!=null){
			if("1".equals(map.get(0).get("status"))){
				status="Success";
			}
		}
		
		
    	return status;
    }
	
    
    
    private  String sendMessage(String phone,String content){//短信平台
    	String status ="";
    	String url ="http://114.215.136.60:8888/sms.aspx?action=send&userid=152&account=JCWW&password=a654321&mobile="+phone+"&content="+content+"&sendTime=&extno=";
    	
    	try {
    		url=URLDecoder.decode(url,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	URL yahoo;
    	
		try {
			yahoo = new URL(url);
			 BufferedReader in = new BufferedReader(  
		                new InputStreamReader(  
		                yahoo.openStream()));  
		  
		    String inputLine;  
		    
		    while ((inputLine = in.readLine()) != null)  {
			    if(inputLine.replace(" ","").startsWith("<returnstatus>")){
			    	 status = inputLine.replace("<returnstatus>", "");
			    	 status = status.replace("</returnstatus>", "").replace(" ", "");
			    	break;
			    }
		    }
		    in.close();  
		} catch(Exception e){
			e.printStackTrace();
		}
			
		    return status;
    }
	
    
    public   String sendMessagedd(String phone,String content){//短信平台
    	String status ="";
    	String url ="http://114.215.136.60:8888/sms.aspx?action=send&userid=152&account=JCWW&password=a654321&mobile="+phone+"&content="+content+"&sendTime=&extno=";
		Captcha captcha = new Captcha();
		captcha.setPhone(phone);
		captcha.setIsvalid(true);
		captcha.setCreatetime(new java.sql.Timestamp(System
				.currentTimeMillis()));
		captcha.setCode("123456");
		captchaDao.save(captcha);
		    return status;
    }
    
    
    public void save(Captcha captcha){
    	
    }

	public String send(String phone, String content) {//发送验证码

		String status ="";
    	String cm = "^((13[4-9])|(147)|(178)|(15[0-2,7-9])|(18[2-3,7-8]))\\d{8}$"; 
    	if(phone.matches(cm)){
    		status=this.sendMessage2(phone, content);
    		if("".equals(status)){
    			System.out.println("移动服务器发送失败");
    			status=this.sendMessage(phone, content);
    		}
    	}else{
    		status=this.sendMessage(phone, content);
    	}

			if ("Success".equals(status)) {
				if (content.contains("验证码")) {
					// 验证码的短信
					Captcha captcha = new Captcha();
					captcha.setPhone(phone);
					captcha.setIsvalid(true);
					captcha.setCreatetime(new java.sql.Timestamp(System
							.currentTimeMillis()));
					captcha.setCode(content.substring(9, 15) + "");
					captchaDao.save(captcha);
				} else {
				}
			}
		

		return status;
	}
	
	
	public String sendM(String phone, String content) {//发送消息

		String status ="";
    	String cm = "^((13[4-9])|(147)|(178)|(15[0-2,7-9])|(18[2-3,7-8]))\\d{8}$"; 
    	if(phone.matches(cm)){
    		status=this.sendMessage2(phone, content);
    		if("".equals(status)){
    			System.out.println("移动服务器发送失败");
    			status=this.sendMessage(phone, content);
    		}
    	}else{
    		status=this.sendMessage(phone, content);
    	}

			
		

		return status;
	}
	
	

	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		ip = "115.28.42.111";
		return ip;
	}

	
	/**
     * 发送公众号信息
     * @param urlParam
     * @param param  参数
     * @return
     */
    public static Map<String,Object> sendGzhMsg(Integer urlId,String param){
		String url = "https://jifen.518wk.com/mrjf/index.php/Vip/Vip/"+getUrlParam(urlId)+"?"+param;
    	return getTaoKeToMap(url);
    	
    }
    private static String getUrlParam(Integer urlId){
		String urlParam = "";
		switch (urlId) {
		case 1:
			//互动
			urlParam = "InteractionMessage";
			break;
		case 2:
			//订单
			urlParam = "orderMessage";
			break;
		case 3:
			//商品
			urlParam = "goodsMessage";
			break;
		case 4:
			//其他
			urlParam = "otherMessage";
			break;
		case 6:
			//积分
			urlParam = "integralMessage";
			break;
		case 7:
			//自定义
			urlParam = "customMessage";
			break;
		case 8:
			//中奖信息
			urlParam = "prizeMessage";
			break;	
		case 9:
			//开店 分佣
			urlParam = "shopMessage";
			break;	
		
		
		}
		return urlParam;
    }
	
}
