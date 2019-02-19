package com.weixin.config;

public class WeixinConfig {
	
	public static String HTTPS_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";//订单退款接口

	public static String HTTPS_VERIFY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//预付订单接口
	
	public static String HTTPS_CLOSE_URL = "https://api.mch.weixin.qq.com/pay/closeorder";//关闭订单接口

	public static String appid = "wx466dd364a7b82daa";
	
	public static String gzh_appid = "wxbfe1aaa96fff38ad";//公众号
	
	public static String s_appid = "wxf1aebc3b62b679e3";//商家版
	
	
	public static String app_secret = "18d894d1a2111fc300fb42cb0f30db86";
	
	public static String s_app_secret="9894265621dc2642672ba8701c61df76";
	
	public static String gzh_app_secret="65ad4a104ab59902ac9885a1ce9f467a";//公众号
	
	
	public static String mch_id = "1514762571"; 
	
	public static String s_mch_id = "1514793711";
	
	public static String gzh_mch_id = "1344459601";
	
	public static String api_key = "AXPQQWWEERRTTYYMMNNBBVVCLLKKJAXP";
	
	public static String s_api_key = "AXPSJBYYQQWWEERRTTYYUUIIOOPPMMNN";
	
	public static String gzh_api_key = "a963da52e6695e41b1a914b0db50bgzh";
	
	public static String device_info = "WEB";
	
	public static String SSLPath = "E:/xampp/tomcat/apiclient_cert.p12";
	
	public static String gzh_SSLPath = "/2t/file/apiclient_cert.p12";
	
	//小程序那边的证书 地址在数据库拿
	public static String SSLPath_Prefix="/2t/zhengshu/axp-shh";
}
