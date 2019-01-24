package com.axp.util;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;

public class SellerMallUtil {

	// 基础网址；形式为：http://ip:端口/上下文路径/;
	public static String BASE = "http://jifen.aixiaoping.cn:8080/jupinhuiAI/";

	/**
	 * 因为这个系统中使用了非此系统中的图片，所以需要知道图片的服务器路径；
	 */
	public static String BASE_PIC_PATH = "http://jifen.aixiaoping.cn:8081/dailyRes/";
	

	// 常量定义；
	public static final String service = "service";// 接口名称
	public static final String partner = "partner";// 合作者id，即签约支付宝时对应的唯一用户号；
	public static final String _input_charset = "utf-8";// 参数编码字符集；
	public static final String sign_type = "RSA";// 签名方式；
	public static final String sign = "sign";// 签名；
	public static final String notify_url = "notify_url";// 异步通知页面路径；
	public static final String seller_id = "seller_id";// 卖家支付宝用户号；
	public static final String payment_type = "1";// 支付类型；
	public static final String it_b_pay = "3837m";// 支付超时时间；

	// 判断两个String的Set是否是相等的；
	public static Boolean isSetEqual(Set<String> set1, Set<String> set2) {
		if (set1.size() == set2.size()) {
			if (set1.toString().equals(set2.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 发送请求
	 * 调用该方法时，直接请求支付宝的 手机网站支付，仅此一个功能；
	 */
	public static String sendURL(PrintWriter writer, String out_trade_no, String subject, String total_fee, String body, String show_url,
			Integer sellerId) {
		// out_trade_no 唯一的订单号；
		// subject 商品名称；
		// total_fee 交易金额；
		// body 商品描述（可不写）；
		// show_url 在收银台页面展示的商品连接（可不写）；

		// 把请求参数打包成数组
		// Map<String, String> sParaTemp = new HashMap<String, String>();
		// sParaTemp.put("service", AlipayConfig.service);
		// sParaTemp.put("partner", AlipayConfig.partner);
		// sParaTemp.put("seller_id", AlipayConfig.seller_id);
		// sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		// sParaTemp.put("payment_type", AlipayConfig.payment_type);
		// sParaTemp.put("notify_url", AlipayConfig.notify_url);
		// sParaTemp.put("return_url", AlipayConfig.return_url);
		// sParaTemp.put("out_trade_no", out_trade_no);
		// sParaTemp.put("subject", subject);
		// sParaTemp.put("total_fee", total_fee);
		// sParaTemp.put("show_url", show_url);
		// sParaTemp.put("body", body);
		// sParaTemp.put("it_b_pay", AlipayConfig.it_b_pay);
		//
		// // 建立请求
		// String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		// writer.write(sHtmlText);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url + sellerId);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);

		// 建立请求
		// String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		// writer.write(sHtmlText);
		return AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
	}

	/**
	 * 生成订单号
	 * 订单号规则为 YY-MM-DD-hh-mm-ss + uuid
	 */
	public static String getOrderNumber() {
		return new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()).toString()
				+ (((Math.random() * 10000 + 10000) + "").substring(0, 5));
	}

	public static void main(String[] args) {
		
	}

}
