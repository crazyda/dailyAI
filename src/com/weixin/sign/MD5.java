package com.weixin.sign;

import com.weixin.util.MD5Util;

public class MD5 {

	//获取md5加密的随机数，数据源为订单数据中的非空数据
	public static String getNonceStr(String xml){
		return MD5Util.GetMD5Code(xml);
	}
	
}
