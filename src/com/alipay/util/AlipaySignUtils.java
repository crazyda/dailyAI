package com.alipay.util;


import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.config.AlipayConfig;
import com.alipay.sign.Base64;


public class AlipaySignUtils {

	private static final String ALGORITHM = "RSA2";

	private static final String SIGN_ALGORITHMS = "SASHA256WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String sign(String subject, String body, String price, String callbackUrl) {
    	String	sign = "";
		try {
			
			String out_trade_no=new SimpleDateFormat("yyyymmddmmhhss").format(new Date());//产生一组随机的订单号
			//实例化客户端
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.appid,AlipayConfig.private_key_pkcs8,"json","UTF-8",AlipayConfig.ali_public_key,"RSA2");
			//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody(body);
			model.setSubject(subject);
			model.setOutTradeNo(out_trade_no);
			model.setTimeoutExpress("30m");
			model.setTotalAmount(price);
			model.setProductCode("QUICK_MSECURITY_PAY");  
			request.setBizModel(model);
			request.setNotifyUrl(callbackUrl);//商户外网可以访问的异步地址，不能重定向
			try {
			        //这里和普通的接口调用不同，使用的是sdkExecute
			        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			        //就是orderString 可以直接给客户端请求，无需再做处理。
			    	sign = response.getBody();
			    } catch (AlipayApiException e) {
			        e.printStackTrace();
			}
			
			return sign;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param ali_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verifyss(String content, String sign, String ali_public_key, String input_charset)
	{
		
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(ali_public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
			signature.initVerify(pubKey);
			//signature.update( content.getBytes(input_charset) );
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("验证出错:"+e);
		}
		return false;
	}
	

}
