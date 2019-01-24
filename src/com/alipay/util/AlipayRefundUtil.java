package com.alipay.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.bean.AlipayRefund;
import com.alipay.config.AlipayConfig;
import com.axp.util.DateUtil;

/* *
 *类名：AlipayNotify
 *功能：支付宝退款接口
 *详细：处理支付宝退款业务
 *版本：3.3
 *日期：2016-09-24
 *************************注意*************************
 *调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
public class AlipayRefundUtil {
	
	public static String refund(String orderCode,String alipayCode, String backCode, double fullPrice, double backPrice, String body) {
		StringBuffer result = new StringBuffer();
		try {
			BigDecimal b1 = new BigDecimal(Double.toString(fullPrice));
			BigDecimal b2 = new BigDecimal(Double.toString(backPrice));
			fullPrice =  b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			backPrice =  b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			AlipayRefund refund = new AlipayRefund();
			refund.setBiz_content(body);
			refund.setTimestamp(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", DateUtil.getNow()));
			refund.setOut_trade_no(orderCode);
			refund.setTrade_no(alipayCode);
			refund.setRefund_amount(backPrice+"");
			refund.setOut_request_no(backCode);
			refund.setRefund_reason("正常退单");
			
			String alipayUrl = "https://openapi.alipay.com/gateway.do";
			AlipayClient alipayClient = new DefaultAlipayClient(alipayUrl,AlipayConfig.appid,AlipayConfig.private_key,"json","uft-8",AlipayConfig.ali_public_key,AlipayConfig.sign_type);
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent(getInfo1(refund));
			
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				return "SUCCESS";
			} else {
				return response.getSubMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.append("未知错误");
		}
		return result.toString();
	}


	public static void main(String args[]){
		
	try{
	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.appid,AlipayConfig.private_key_pkcs8_new,"json","GBK",AlipayConfig.ali_public_key_new,"RSA");
	AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
	request.setBizContent("{" +
	"\"out_trade_no\":\"20150320010101001\"," +
	"\"trade_no\":\"2014112611001004680073956707\"," +
	"\"refund_amount\":200.12," +
	"\"refund_currency\":\"USD\"," +
	"\"refund_reason\":\"正常退款\"," +
	"\"out_request_no\":\"HZ01RF001\"," +
	"\"operator_id\":\"OP001\"," +
	"\"store_id\":\"NJ_S_001\"," +
	"\"terminal_id\":\"NJ_T_001\"," +
	"      \"goods_detail\":[{" +
	"        \"goods_id\":\"apple-01\"," +
	"\"alipay_goods_id\":\"20010001\"," +
	"\"goods_name\":\"ipad\"," +
	"\"quantity\":1," +
	"\"price\":2000," +
	"\"goods_category\":\"34543238\"," +
	"\"body\":\"特价手机\"," +
	"\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
	"        }]" +
	"  }");
	AlipayTradeRefundResponse response = alipayClient.execute(request);
//	System.out.println(response.getBody());
	if(response.isSuccess()){
	System.out.println("调用成功");
	} else {
	System.out.println("调用失败");
	}
	
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	
	
/*	
 *  这个方法可能是用不到的,
 * 	public static String sign(AlipayRefund refund,String paricv){
		// 订单
		String orderInfo = getInfo(refund);
		// 对订单做RSA 签名
		String sign = AlipaySignUtils.sign(orderInfo, AlipayConfig.private_key);

		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 完整的符合支付宝参数规范的订单信息
		return orderInfo + "&sign=\"" + sign + "\"&" + "sign_type=\"RSA\"";
	}*/
	
	public static String getInfo(Object parameter){
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(!StringUtils.isEmpty(value)&&!key.equals("sign")){
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
				buffer.append("\""+keyList.get(i)+"\""+"="+"\""+map.get(keyList.get(i))+"\"");
			}else{
				buffer.append("&"+"\""+keyList.get(i)+"\""+"="+"\""+map.get(keyList.get(i))+"\"");
			}
		}
		return buffer.toString();
	}
	
	public static String getInfo1(Object parameter){
		Map<String,String> map = new HashMap<>();
		try{
			Field[] fields = parameter.getClass().getDeclaredFields();
			for(Field field : fields){
				field.setAccessible(true);
				String key = field.getName();
				String value = field.get(parameter)==null?"":field.get(parameter).toString();
				if(!StringUtils.isEmpty(value)&&!key.equals("sign")){
					map.put(key, value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer("{");
		List<String> keyList = keyOrderByASCII(map);
		for(int i=0;i<keyList.size();i++){
			if(i==0){
				buffer.append("\""+keyList.get(i)+"\""+":"+"\""+map.get(keyList.get(i))+"\"");
			}else{
				buffer.append(","+"\""+keyList.get(i)+"\""+":"+"\""+map.get(keyList.get(i))+"\"");
			}
		}
		buffer.append("}");
		return buffer.toString();
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
	
}
