package com.weixin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.axp.util.StringUtil;
import com.weixin.bean.PayParameter;
import com.weixin.config.WeixinConfig;
import com.weixin.sign.MD5;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class WeixinUtil{
	
	
	public static Map<String, String> getParams(HttpServletRequest request,HttpServletResponse respone) {
		try {
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuilder result = new StringBuilder();
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result.append(line);
			}
			return xmlElements(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将所有参数键按ASCII排序
	 * @param map
	 * @return
	 */
	public static List<String> keyOrderByASCII(Map<String,String> map){
		Collection<String> keyset= map.keySet(); 
		List<String> list=new ArrayList<String>(keyset);
		Collections.sort(list);
		return list;
	}
	
	public static String notifySign(Map<String,String> map){
		StringBuffer signSB = new StringBuffer();
		List<String> keys = WeixinUtil.keyOrderByASCII(map);
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals("sign")||key.equals("body")){
				continue;
			}
			signSB.append(key+"="+map.get(key));
			if(it.hasNext()){
				signSB.append("&");
			}
		}
		signSB.append("&key="+WeixinConfig.api_key);
		return MD5Util.GetMD5Code(signSB.toString()).toUpperCase();
	}
	
	/**
	 * 编辑预付信息xml
	 * @param attach
	 * @param body
	 * @param totalPrice
	 * @param orderCode
	 * @param baseIp
	 * @param notifyUrl
	 * @param nonce_str
	 * @return
	 */
	public static String setXmlStr(PayParameter parameter) {
		String signChar = getSign(parameter);
		Document dom = DocumentHelper.createDocument();//创建xml文件
		Element xml = dom.addElement("xml");
		xml.addElement("appid").addText(WeixinConfig.appid);
		xml.addElement("attach").addCDATA(parameter.getAttach());
		xml.addElement("body").addCDATA(parameter.getBody());
		xml.addElement("mch_id").addText(WeixinConfig.mch_id);
		xml.addElement("nonce_str").addText(parameter.getNonce_str());
		xml.addElement("notify_url").addCDATA(parameter.getNotifyUrl());
		xml.addElement("out_trade_no").addCDATA(parameter.getOrderCode());
		xml.addElement("spbill_create_ip").addCDATA(parameter.getBaseIp());
		xml.addElement("total_fee").addCDATA(parameter.getTotalPrice());
		xml.addElement("trade_type").addText("APP");
		xml.addElement("sign").addText(signChar);
		String output = dom.asXML();
		return output;
	}
	
	public static String setCloseXmlStr(PayParameter parameter) {
		
		String sign = getCloseSign(parameter);
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("<xml>\n");
		sbf.append("<appid>"+WeixinConfig.appid+"</appid>\n");
		sbf.append("<mch_id>"+WeixinConfig.mch_id+"</mch_id>\n");
		sbf.append("<nonce_str>"+parameter.getNonce_str()+"</nonce_str>\n");//随机字符串，不大于32位
		sbf.append("<out_trade_no>"+parameter.getOrderCode()+"</out_trade_no>\n");//商户内部订单号，32位内
		sbf.append("<sign>"+sign+"</sign>\n");
		sbf.append("</xml>");
		
		return sbf.toString();
	}
	
	public static String getCloseSign(PayParameter parameter){
		StringBuffer signSB = new StringBuffer();
		signSB.append("appid="+WeixinConfig.appid);
		signSB.append("&mch_id="+WeixinConfig.mch_id);
		signSB.append("&nonce_str="+getNonceStr(parameter.getAttach(),parameter.getBody()));
		signSB.append("&out_trade_no="+parameter.getOrderCode());
		signSB.append("&key="+WeixinConfig.api_key);
		return MD5Util.GetMD5Code(signSB.toString()).toUpperCase();
	}
	
	public static String getPaySign(PayParameter parameter, String prepayId,long timeStamp){
		StringBuffer signSB = new StringBuffer();
		signSB.append("appid="+WeixinConfig.appid);
		signSB.append("&noncestr="+parameter.getNonce_str());
		signSB.append("&package=Sign=WXPay");
		signSB.append("&partnerid="+WeixinConfig.mch_id);
		signSB.append("&prepayid="+prepayId);
		signSB.append("&timestamp="+timeStamp);
		signSB.append("&key="+WeixinConfig.api_key);
		return MD5Util.GetMD5Code(signSB.toString()).toUpperCase();
	}
	
	public static String getPaySignNoAppId(PayParameter parameter, String prepayId,long timeStamp){
		StringBuffer signSB = new StringBuffer();
		signSB.append("noncestr="+parameter.getNonce_str());
		signSB.append("&package=Sign=WXPay");
		signSB.append("&partnerid="+WeixinConfig.mch_id);
		signSB.append("&prepayid="+prepayId);
		signSB.append("&timestamp="+timeStamp);
		signSB.append("&key="+WeixinConfig.api_key);
		return MD5Util.GetMD5Code(signSB.toString()).toUpperCase();
	}
	
	public static String getSign(PayParameter parameter){
		StringBuffer signSB = new StringBuffer();
		signSB.append("appid="+WeixinConfig.appid);
		signSB.append("&attach="+parameter.getAttach());
		signSB.append("&body="+parameter.getBody());
		signSB.append("&mch_id="+WeixinConfig.mch_id);
		signSB.append("&nonce_str="+getNonceStr(parameter.getAttach(),parameter.getBody()));
		signSB.append("&notify_url="+parameter.getNotifyUrl());
		signSB.append("&out_trade_no="+parameter.getOrderCode());
		signSB.append("&spbill_create_ip="+parameter.getBaseIp());
		signSB.append("&total_fee="+parameter.getTotalPrice());
		signSB.append("&trade_type="+"APP");
		signSB.append("&key="+WeixinConfig.api_key);
		return MD5Util.GetMD5Code(signSB.toString()).toUpperCase();
	}
	
	/**
	 * 发送预付信息
	 * @param urlStr 微信API地址
	 * @param xml 预付信息Xml
	 * @return
	 */
	public static String sendXml(String urlStr, String xml) {
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(
					con.getOutputStream());
			out.write(new String(xml.getBytes("UTF-8")));
			out.flush();
			out.close();
			InputStreamReader in = new InputStreamReader(con.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(in);
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result.append(line);
			}
			in.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * 获得预付订单id
	 * @param attach 附加信息
	 * @param body 主体信息
	 * @param totalPrice 总价
	 * @param orderCode 内部订单序号
	 * @param baseIp 本地服务ip
	 * @param basePath 本地服务根目录
	 * @return
	 */
	public static String getPrepayId(PayParameter parameter){
		String prepayId = "";
		// 回调接口url
		String urlStr = WeixinConfig.HTTPS_VERIFY_URL;
		String xml = setXmlStr(parameter);
		String responseResult = sendXml(urlStr,xml);
		if(!StringUtil.hasLength(responseResult)){
			return prepayId;
		}
		Map<String, String> responseMap = xmlElements(responseResult);
		if(responseMap.get("result_code").equals("SUCCESS")){
//			String nonceStr = responseMap.get("nonce_str");
			prepayId = responseMap.get("prepay_id");
		}
//		else{//关闭订单
//			try{
//				PayParameter payParameter = new PayParameter();
//				payParameter.setAttach(parameter.getOrderCode());
//				payParameter.setBaseIp(parameter.getBaseIp());
//				payParameter.setBody(parameter.getOrderCode());
//				payParameter.setNotifyUrl("");
//				payParameter.setOrderCode(parameter.getOrderCode());
//				payParameter.setTotalPrice(parameter.getTotalPrice());
//				WeixinUtil.closeOrder(payParameter);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
		return prepayId;
	}
	
	/**
	 * 关闭订单
	 * @param parameter
	 * @return
	 */
	public static String closeOrder(PayParameter parameter){
		String prepayId = "";
		// 回调接口url
		String urlStr = WeixinConfig.HTTPS_CLOSE_URL;
		String xml = setCloseXmlStr(parameter);
		String responseResult = sendXml(urlStr,xml);
		if(!StringUtil.hasLength(responseResult)){
			return prepayId;
		}
		Map<String, String> responseMap = xmlElements(responseResult);
		if(responseMap.get("return_code").equals("SUCCESS")){
			prepayId = responseMap.get("prepay_id");
		}
		return prepayId;
	}
	
	public static String getNonceStr(String attach,String body){
		return MD5.getNonceStr("attach="+attach+"&body="+body+"&device_info="+WeixinConfig.device_info+"&mch_id="+WeixinConfig.mch_id+"&key="+WeixinConfig.api_key);
	}
	
	
	/**
	 * 解析服务器返回的数据
	 * @param xmlDoc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlElements(String xmlDoc) {
		Map<String, String> map = new HashMap<>();
		try {
			Document document = DocumentHelper.parseText(xmlDoc);
			Element nodeElement = document.getRootElement();
			List<Element> node = nodeElement.elements();
			for (Iterator<Element> it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				map.put(elm.getName(), elm.getText());
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String getNonceStr(Object parameter){
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(StringUtils.isNotEmpty(value)&&!key.equals("sign")){
					map.put(key, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		List<String> keyList = keyOrderByASCII(map);
		for(int i=0;i<keyList.size();i++){
			if(i==0){
				buffer.append(keyList.get(i)+"="+map.get(keyList.get(i)));
			}else{
				buffer.append("&"+keyList.get(i)+"="+map.get(keyList.get(i)));
			}
		}
		return MD5.getNonceStr(buffer.toString());
	}
	
	public static String getParameterSign(Object parameter,String apiKey){
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(StringUtils.isNotEmpty(value)&&!key.equals("sign")){
					map.put(key, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		List<String> keyList = keyOrderByASCII(map);
		for(int i=0;i<keyList.size();i++){
			if(i==0){
				buffer.append(keyList.get(i)+"="+map.get(keyList.get(i)));
			}else{
				buffer.append("&"+keyList.get(i)+"="+map.get(keyList.get(i)));
			}
		}
		buffer.append("&key=" + apiKey);
		return MD5Util.GetMD5Code(buffer.toString()).toUpperCase();
	}
	
	
	public static String getParameterSign(Object parameter){
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(StringUtils.isNotEmpty(value)&&!key.equals("sign")){
					map.put(key, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		List<String> keyList = keyOrderByASCII(map);
		for(int i=0;i<keyList.size();i++){
			if(i==0){
				buffer.append(keyList.get(i)+"="+map.get(keyList.get(i)));
			}else{
				buffer.append("&"+keyList.get(i)+"="+map.get(keyList.get(i)));
			}
		}
		buffer.append("&key=" + WeixinConfig.api_key);
		return MD5Util.GetMD5Code(buffer.toString()).toUpperCase();
	}
	
	public static String getXmlStr(Object parameter) {
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(StringUtils.isNotEmpty(value)){
					map.put(key, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Document dom = DocumentHelper.createDocument();//创建xml文件
		Element xml = dom.addElement("xml");
		List<String> keyList = keyOrderByASCII(map);
		for(int i=0;i<keyList.size();i++){
			xml.addElement(keyList.get(i)).addCDATA(map.get(keyList.get(i)));
		}
		return dom.asXML();
	}
	
	/**
	 * 发送预付信息（需要双向证书）
	 * @param urlStr 微信API地址
	 * @param xml 预付信息Xml
	 * @return
	 */
	public static String sendXmlWithSSL(String urlStr, String xml) {
		System.out.println("发送预付信息的xml:"+xml);
		
		
		StringBuffer result = new StringBuffer();

		try{
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		    FileInputStream instream = new FileInputStream(new File(WeixinConfig.SSLPath));
		    try {
	            keyStore.load(instream, WeixinConfig.mch_id.toCharArray());
	        } finally {
	            instream.close();
	        }
	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, WeixinConfig.mch_id.toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
		    
			try {
				
				HttpPost httpost = new HttpPost(urlStr); // 设置响应头信息  
				httpost.addHeader("Connection", "keep-alive");  
	            httpost.addHeader("Accept", "*/*");  
	            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
	            httpost.addHeader("Host", "api.mch.weixin.qq.com");  
	            httpost.addHeader("X-Requested-With", "XMLHttpRequest");  
	            httpost.addHeader("Cache-Control", "max-age=0");  
	            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
	            httpost.setEntity(new StringEntity(xml, "UTF-8"));  
	            CloseableHttpResponse response = httpclient.execute(httpost);  
				
	            try {
		            HttpEntity entity = response.getEntity();
		            if (entity != null) {
		            	InputStreamReader in = new InputStreamReader(entity.getContent(), "UTF-8");
		    			BufferedReader br = new BufferedReader(in);
		    			for (String line = br.readLine(); line != null; line = br.readLine()) {
		    				result.append(line);
		    			}
		            }
		            EntityUtils.consume(entity);
	            }catch(Exception e){
	            	e.printStackTrace();
	            }finally{
	            	response.close();
	            }
			} catch (Exception e) {
				e.printStackTrace();
			} finally { 
				httpclient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * 发送预付信息（需要双向证书）
	 * @param urlStr 微信API地址
	 * @param xml 预付信息Xml
	 * @return
	 */
	public static String sendXmlWithSSL(String urlStr, String xml,String path,String mchId) {
		StringBuffer result = new StringBuffer();

		try{
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		    FileInputStream instream = new FileInputStream(new File(path));
		    try {
	            keyStore.load(instream, mchId.toCharArray());
	        } finally {
	            instream.close();
	        }
	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, mchId.toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
		    
			try {
				
				HttpPost httpost = new HttpPost(urlStr); // 设置响应头信息  
				httpost.addHeader("Connection", "keep-alive");  
	            httpost.addHeader("Accept", "*/*");  
	            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
	            httpost.addHeader("Host", "api.mch.weixin.qq.com");  
	            httpost.addHeader("X-Requested-With", "XMLHttpRequest");  
	            httpost.addHeader("Cache-Control", "max-age=0");  
	            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
	            httpost.setEntity(new StringEntity(xml, "UTF-8"));  
	            CloseableHttpResponse response = httpclient.execute(httpost);  
				
	            try {
		            HttpEntity entity = response.getEntity();
		            if (entity != null) {
		            	InputStreamReader in = new InputStreamReader(entity.getContent(), "UTF-8");
		    			BufferedReader br = new BufferedReader(in);
		    			for (String line = br.readLine(); line != null; line = br.readLine()) {
		    				result.append(line);
		    			}
		            }
		            EntityUtils.consume(entity);
	            }catch(Exception e){
	            	e.printStackTrace();
	            }finally{
	            	response.close();
	            }
			} catch (Exception e) {
				e.printStackTrace();
			} finally { 
				httpclient.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
}