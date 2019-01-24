package com.axp.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utility {
	
	public static String req_uri = "";
	
	 public static int getMutiple(String content){
		    
			int len = content.length();
			if(len > 70){
				if(len % 70 ==0){
				  
					return len / 70;  
				}
				return len / 70 + 1;
			}
			return 1;
		}
	 
	 public static void setPageInfomation(PageInfo pageInfo, String pagestr,
	 			String pageSizeValue, int totalcount) {
	 		// String pagestr = (String) request.getParameter("page");
	 		if (pagestr == null || pagestr.trim().length() == 0) {
	 			pagestr = "0";
	 		}
	 		int pageindex = Integer.parseInt(pagestr);
	 		// String pageSizeValue = ServletActionContext.getServletContext()
	 		// .getInitParameter("houseDetailNewsPageSize");

	 		int pagesize = Integer.parseInt(pageSizeValue);
	 		int maxpagecount = totalcount / pagesize;
	 		if (totalcount % pagesize != 0) {
	 			maxpagecount++;
	 		}
	 		if (pageindex > maxpagecount) {
	 			pageindex = maxpagecount;
	 		}

	 		pageInfo.setCurrentPage(pageindex);
	 		pageInfo.setPageSize(pagesize);
	 		pageInfo.setTotalPage(maxpagecount);
	 	}
	 
	 
	 
	 public static void CopyFile(File   in,   File   out)   throws   Exception   { 
	 		BufferedInputStream   fis   =   new   BufferedInputStream(new   FileInputStream(in)); 
	 		BufferedOutputStream   fos   =   new   BufferedOutputStream(new   FileOutputStream(out)); 

	         byte[]   buf   =   new   byte[1024]; 
	         int   i   =   0; 
	         while((i=fis.read(buf))!=-1)   { 
	             fos.write(buf,   0,   i); 
	         } 
	         fis.close(); 
	         fos.close(); 
	 	}
	 
	 public static int getPagenumberCount(int count , int pagesize){
	 		
 		int pagenumcount = count/pagesize ;
     	if(count % (pagesize)!=0){
     		pagenumcount = pagenumcount +1;
     	}
     	if(pagenumcount==0){
     		pagenumcount=1;
     	}
     	return pagenumcount;
 	}
     
     public static String getMD5(byte[] source) {
 		String s = null;
 		char hexDigits[] = { // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
 		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
 				'e', 'f' };
 		try {
 			java.security.MessageDigest md = java.security.MessageDigest
 					.getInstance("MD5");
 			md.update(source);
 			byte tmp[] = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�����
 			// ���ֽڱ�ʾ���� 16 ���ֽ�
 			char str[] = new char[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ�
 			// ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
 			int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
 			for (int i = 0; i < 16; i++) { // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
 				// ת���� 16 �����ַ��ת��
 				byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
 				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��,
 				// >>> Ϊ�߼����ƣ������λһ������
 				str[k++] = hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
 			}
 			s = new String(str); // ����Ľ��ת��Ϊ�ַ�

 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return s;
 	}

 	/**
 	 * MD5����
 	 * 
 	 * @param sourceStr
 	 * @return
 	 */
 	public static String MD5(String sourceStr) {
 		if (sourceStr == null || sourceStr.length() == 0)
 			return null;
 		Utility utility = new Utility();
 		return utility.getMD5(sourceStr.getBytes());
 	}
 	
 	public static String generalInvitecode(int num, int fixdlenth) {
 	       Random r = new Random();
 	       int rn = r.nextInt(num);
 	       String rns = rn+"";
 	       int len = rns.length();
 	       if(len<fixdlenth){
 	    	   for(int i= len;i<fixdlenth;i++){
 	    		   rns = rns+ "1";
 	    	   }
 	    	   return rns;
 	       }else{
 	    	   return rns;
 	       }
 	}
 	 
 	 
    public static String formatDate(String format, Date d){
 		
 		
 		SimpleDateFormat sp = new SimpleDateFormat(format);
 		
 		return sp.format(d);
 		
 	}
    
    public static Date parseStr(String format, String datestr){
 		
 		try{
	 		SimpleDateFormat sp = new SimpleDateFormat(format);
	 		
	 		return sp.parse(datestr);
 		
 		}catch(Exception e){
 			
 		}
 		return null;
 	}
    

    public static String formatDate(Date d,String format){
    	
        
    	try{
    		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(format);
    		return sd.format(d);
    		
    	}catch(Exception e){
    		
    	}
    	return "";
    }
    
    public static int SendMessage(String phone,String content){
    	
    	 String url="http://www.doodsms.com:6630/WebAPI/doodsms.asmx/SendSmsExt";
    	 String charset="utf-8";//��վ���룬ʹ�ö��Ź�����վ�ı��룬����Ϊgb2312��gbk��utf-8
    	 String username = "shanger815"; // �ӿ��ʺ�
    	 String pwd = "shanger0625"; // �ӿ�����
    	 String gateId = "1"; // ͨ��ID��Ĭ��Ϊ1
    	 String tel = phone;
    	// String TelPhoneCode="java �������";
    	 //String TelContent="java����ֻ����Ϊ��good";
    	 //String content= charset=="utf-8"? TelContent : iconv(charset,"utf-8",TelContent);
    	 content = "���۱�����"+content;
    	 try{
    	// content = new String(content.getBytes(),"UTF-8");
    		 content = URLEncoder.encode(content, "UTF-8");
    	 }catch(Exception ee){}
    	 String SendTime = ""; // ��ʱ����ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS����������Ϊ�ռ���
    	 String surl = url+"?user="+username+"&pwd="+pwd+"&chid="+gateId+"&mobiles="+tel+"&contents="+content+"&sendtime="+SendTime;
    	 String result = "";
         BufferedReader in = null;
         try {
            URL realUrl = new URL(surl);
             // �򿪺�URL֮�������
             URLConnection connection = realUrl.openConnection();
             // ����ͨ�õ���������
             connection.setRequestProperty("accept", "*/*");
             connection.setRequestProperty("connection", "Keep-Alive");
             connection.setRequestProperty("user-agent",
                     "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
             // ����ʵ�ʵ�����
             connection.connect();
             // ��ȡ������Ӧͷ�ֶ�
             Map<String, List<String>> map = connection.getHeaderFields();
             // �������е���Ӧͷ�ֶ�
//             for (String key : map.keySet()) {
//                 System.out.println(key + "--->" + map.get(key));
//             }
             // ���� BufferedReader����������ȡURL����Ӧ
             in = new BufferedReader(new InputStreamReader(
                     connection.getInputStream(),"UTF-8"));
             String line;
//             while ((line = in.readLine()) != null) {
//            	 System.out.println("line======="+line);
//             }
             
         } catch (Exception e) {
//             System.out.println("����GET��������쳣��" + e);
             e.printStackTrace();
             return -1;
         }
         // ʹ��finally�����ر�������
         finally {
             try {
                 if (in != null) {
                     in.close();
                 }
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }
    	return 1;
    }
    
    /** 将map对象转成json字符串,转后格式为[{"status":"1"},{"key1"="value1"}]
     * 
     * ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("status", "1");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("key3", "value5");
		map2.put("key4", "value6");
		map2.put("key5", "value7");
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("key3", "value3");
		map3.put("key4", "value4");
		map3.put("key5", "value5");
		arrayList.add(map1);
		arrayList.add(map2);
		arrayList.add(map3);
		System.out.println(dataToJson(arrayList));
		
	 * @param mapArray HashMap<String, String>数组
	 * @return
	 */
	public static String dataToJson(ArrayList<HashMap<String, String>> mapArray){
		JSONArray jsonArray = new JSONArray();
		if(mapArray == null){
			JSONObject jsonObject1 = new JSONObject();
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("status", "-1");
			jsonObject1.accumulateAll(map1);
			jsonArray.add(jsonObject1);
			return jsonArray.toString();
		}
		for(HashMap<String, String> map:mapArray){
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulateAll(map);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}
	
	public static String imageToJson(ArrayList<String> imgList){
		JSONArray jsonArray = new JSONArray();
		if(imgList == null){
			return "";
		}
		for(String s:imgList){
			jsonArray.add(s);
		}
		return jsonArray.toString();
	}
	
}