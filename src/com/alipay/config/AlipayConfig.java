package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088131665213756";//da
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;
	// 商户的私钥(RSA)da
	public static String private_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1wbXB9a/0/62UqANWbVg8BxdvP7j776cE5+de7mLT5ByE8gLFeGFU4KS4dN9jB+AcWVS1wzAl8aOU4ptmygKFW5o5O0kFS8D6PfoBLdC7fHNyFe2cJbO/kuGBrlvcJKYsAkMnz0fUVjpw5WthRmkxSaLyuJSTRBmN8wlyy4R0OGRF3zWqUr0ypgHSmbB0wSVFcFbYi7cVTuHUirAEi7vPEi9TS+PrlMj+n4aeCRJ2w5XLvPqAFTFHiUMA17/V8d4f4FH+lI2K17VDwPsJETcBK1vIQI+EMb/yNs9KEYimy7JIx4QAbok+rTUQAl29E7335fzbxi+9Bgh5nMDW77QiQIDAQAB";
	public static String private_key_new="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAslISTnGyT5/2H1KYanPq5B9z73cktc4MAlP1+TV6t87If9dpaySA6r3yJ2eoqvpuyULc9QOJuBr0pFYuvFoyyyGA4gS7JNpNUIhEIgiQlBSHJocWBSzqvWzZET+yk7X+J1tSmMML4dBvV+p5wfgM1pRm2nSfAIlJZGs9HMq/rdguILVH19nU3E4L1UNL6vfq+cZrcYcZh7yQWH1+aVkOM3KPs/FYNoVWw3idvierJ/abrTNj+eLncam00ZLf4QtG7ZZqxyubl0BtJi3kQcxcF7NrTj3D+YwLXOWVSzG8lrulo/h5eAAB0E2iHg9Y8ieRJQhvHAEnZNUWU2t3u+I/zQIDAQAB";
	
	public static String appid = "2018072060699374";
									
	// 商户的私钥(RSA,pkcs8格式)
	public static String private_key_pkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDXBtcH1r/T/rZSoA1ZtWDwHF28/uPvvpwTn517uYtPkHITyAsV4YVTgpLh032MH4BxZVLXDMCXxo5Tim2bKAoVbmjk7SQVLwPo9+gEt0Lt8c3IV7Zwls7+S4YGuW9wkpiwCQyfPR9RWOnDla2FGaTFJovK4lJNEGY3zCXLLhHQ4ZEXfNapSvTKmAdKZsHTBJUVwVtiLtxVO4dSKsASLu88SL1NL4+uUyP6fhp4JEnbDlcu8+oAVMUeJQwDXv9Xx3h/gUf6UjYrXtUPA+wkRNwErW8hAj4Qxv/I2z0oRiKbLskjHhABuiT6tNRACXb0Tvffl/NvGL70GCHmcwNbvtCJAgMBAAECggEAROuuVJbimYKU0dr3LH/UeGG/MerJCHkh8bBBe/y2yGPWDL0bwIGuiFvb1IJDRXx7YN1HNiKiQIOl7uT5yLeGs+PVvzq7TAbry+pZ9dj/nODaQpLKqT/NTPjMFq+tJnn2csYTw56FwT8sfkIlgfn8NCzstbThCE5vVQccPgmgMEDmIsu/S3m2+I3az3aM4dNR04W3DQjayL1ldk8OlN27rrI/rp213D2qne0S4UTR9XsmXwPHmCHPVxxgCW8eYBkbX6jZV+hy+TzurrFdgI4EjKz+4B7Ql6ligSIE+DkNklBOVPMOqk64j5WuQ0c4xmTWbZA3nPB5epGwLpUxu1UfAQKBgQDuKTLOZO0OG8UW+eLjj4Do0q122n2QxM6LGquUktZcIYlMIOc7s1HzUwNRxaRTl/WEKbYtZtXf1UFVKVvBaFUjOGJ1aguLeaVAf71Z1d8mqiyPHt7D4g/BC6M+mSHvBGcC9fJ7S4szczsnuvGsFAwzqKxbQKSULc7wjhZuZFelsQKBgQDnIgld83tKawFnPlcEEC4mGOqSYW0ySxRb42Zxn1IiGff8MRE2cJctWCVkXqgwPoBLLqaWEL2RThvoHR/iOb6ty3fr7XwVdMRIDeXgpSyvJ3IJZ/oU4N2VVWdNk6A+RcNBz3WuufgErvVM9lD/YdytnW3upXJPP+wZblSI3tkWWQKBgQCZtA0OFyTGzjVMtskvhBX2LMG2yxvuqpWCQRwrLvkZTKuHblTNutVQ7GpgA+Yh7RiOyzy/4HZ3lvIrPB3znJoVuPYSQPZidYepMMfLirQSCBHp7EposSTpudEJPVshHv48cu6jlAWqltu+sG/4VpfpXxqdagLNfyLnLUxcwnWugQKBgQDJeuEhdgLujzLM9gnSb+xap3OroKQYqFwZ8bO2rkRhl7OVsownoaQXEfSeduk0DPe3EfaDcBpf1O/8uY62nNG8HFbwYbe92GjVys+tsS6vjLoauvnEQ3TFHYqMlHimcBfSgW87nb83DfClYz6eMIe3DDntQ2nO7ZD5ZjTojyI8GQKBgF6nKWA0YrPENTxtfHKvLlTP8GPIJglirQ4B7DNCCUAeobwUNkMSI2buuovoielfDm62+06ymAhtB1WNz9IjFjawigMB28lSubyC+J6PWUUs5SfPF++F2QyH2EcCZpcl4w523Hbh5kNavnXGtQocOVh/MW0LSV9Jhr4AVl114WD4";
	
	public static String private_key_pkcs8_new="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyUhJOcbJPn/Yf"+
												"Uphqc+rkH3PvdyS1zgwCU/X5NXq3zsh/12lrJIDqvfInZ6iq+m7JQtz1A4m4GvSk"+
												"Vi68WjLLIYDiBLsk2k1QiEQiCJCUFIcmhxYFLOq9bNkRP7KTtf4nW1KYwwvh0G9X"+
												"6nnB+AzWlGbadJ8AiUlkaz0cyr+t2C4gtUfX2dTcTgvVQ0vq9+r5xmtxhxmHvJBY"+
												"fX5pWQ4zco+z8Vg2hVbDeJ2+J6sn9putM2P54udxqbTRkt/hC0btlmrHK5uXQG0m"+
												"LeRBzFwXs2tOPcP5jAtc5ZVLMbyWu6Wj+Hl4AAHQTaIeD1jyJ5ElCG8cASdk1RZT"+
												"a3e74j/NAgMBAAECggEBAIqntGJYl+zAFfGvGKKVtcxiBJTsEJhpOf9JZ9a6Vcy8"+
												"tjxptCfCf5eH0NYBDS69gg1utz6w3JJSK23pMXf1rs8yIvQb96SlNj7rZgcMn9b0"+
												"y04S9fVaQsC99V8rJ2Ehvxi9MtFjwEZ6+0jiIoDHrrvfMsakrcoN9gpmNGPB5x91"+
												"ziDeF30QhYYWYK3cjehvFv68x0HpM8ieg7i5oyMl7sw28ipKvJbG4Kn+LbEvASWU"+
												"uHVc+HkWNPld2wO6b+skb+nopMo9fQYXSiOJlSbBr5IrrYl/30QCcGL3ZTmUks0o"+
												"vrA5ZsqtFAnhmXYpFbgGlWTlsg6H1kmPcYZXlUVQJsECgYEA7CUmCgue06x0rcH/"+
												"omhZ1tMzIRbezIf3ahaeSpOdoXbz3UVLwFRKcniO5pMF3SUOW03FzkMSqc2FH/Wq"+
												"9eVYrvBOKR30li9k6ogx5xMzCVpDA19HfmFABvrEFdpkIoviytDWC5KRJ/ukbcCW"+
												"//XuwoquqL+bsPDZmuJFRvcr3TECgYEAwVBK0pPKLj2t/+Aq6WvOHNeriiQUBjym"+
												"cr8Dnp0TOSmuHQCuAi0s7H80cufFgOzzFQ2uXt7kyYWQl84Riq07REnmbXFSCC0D"+
												"kAau/PRKT0T183gn3dEn80u91ncjkxImEvL2v6P+4qMqL1kC6lqcE8Qd6IVE7jRq"+
												"G4Q7xN2z9V0CgYAxUUTTfj/KZ07q5wQzb+WrVuprs7IONys1zcl7O78E9KnTgWBj"+
												"p3bw1zrI7tWfrNiFuwVfoWRpcBGobg8pUZ+m0WsRbT8iin1h+dWqM7lJ777lGmX8"+
												"/+R2ULLY+YZGLL7NqrW5Lv//wK9DeKBQjpUxrfLyLSof1v0rOqxLON3BUQKBgBKe"+
												"URaG9WoOtrt2M6vXnjpY60SaxPL01HMowoh8SGWxBfv1HM1I3JLKjZqjh/oq2B6m"+
												"+qxYe2CNZKe6SSY6GQMyuua/i08ORtGQUzo5rQ6FbcrNP8m1Y0ujd904DE0V6mTY"+
												"0bsTaHEbleyJfiZkI7Qjp13FuCXZFn8f9UG4uKRhAoGAH7ojuogLdE6sbYGwnj3i"+
												"rIfUECQyLsEd6FEspi+yOfAp3YuncO+RbYb8DBKxq5/wDgMnNbQhh9XII36wVjif"+
												"rSphAfPiektl5mw96oK/FQsM6u8HHjyjCGDt2FVkI7YWdaOpbRQmza6a39OlrBGd"+
												"QpHDNLQdMz18d5Y77Xr9Eng=";
	
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1wbXB9a/0/62UqANWbVg8BxdvP7j776cE5+de7mLT5ByE8gLFeGFU4KS4dN9jB+AcWVS1wzAl8aOU4ptmygKFW5o5O0kFS8D6PfoBLdC7fHNyFe2cJbO/kuGBrlvcJKYsAkMnz0fUVjpw5WthRmkxSaLyuJSTRBmN8wlyy4R0OGRF3zWqUr0ypgHSmbB0wSVFcFbYi7cVTuHUirAEi7vPEi9TS+PrlMj+n4aeCRJ2w5XLvPqAFTFHiUMA17/V8d4f4FH+lI2K17VDwPsJETcBK1vIQI+EMb/yNs9KEYimy7JIx4QAbok+rTUQAl29E7335fzbxi+9Bgh5nMDW77QiQIDAQAB";

	
	public static String ali_public_key_new="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAslISTnGyT5/2H1KYanPq5B9z73cktc4MAlP1+TV6t87If9dpaySA6r3yJ2eoqvpuyULc9QOJuBr0pFYuvFoyyyGA4gS7JNpNUIhEIgiQlBSHJocWBSzqvWzZET+yk7X+J1tSmMML4dBvV+p5wfgM1pRm2nSfAIlJZGs9HMq/rdguILVH19nU3E4L1UNL6vfq+cZrcYcZh7yQWH1+aVkOM3KPs/FYNoVWw3idvierJ/abrTNj+eLncam00ZLf4QtG7ZZqxyubl0BtJi3kQcxcF7NrTj3D+YwLXOWVSzG8lrulo/h5eAAB0E2iHg9Y8ieRJQhvHAEnZNUWU2t3u+I/zQIDAQAB";
	
	
	// 商户私钥(MD5)
	public static String key = "6f5sktkbb0wgm2kctvumn1jre9gnyfwl";

	// 商户绑定支付宝账号
	public static String seller_email = "2535044006@qq.com";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/2t/jboss-as/standalone/log/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 即时到帐签名方式 不需修改
	public static String sign_type = "RSA";

	// 即时到帐签名方式 不需修改
		public static String sign_type2 = "RSA2";
	// MD5签名方式
	public static String sign_type_MD5 = "MD5";

	// ////////////////////////////////////////
	// service
	public static String service = "alipay.wap.create.direct.pay.by.user";

	// 异步通知的地址
	public static String notify_url = "http://jifen.aixiaoping.cn:8080/AI/sellerMallNotify";

	// 返回跳转界面
	public static String return_url = "http://jifen.aixiaoping.com:8080/aixiaoping/successPay.jsp";
	

	// 支付类型，1：表示商品购买；
	public static String payment_type = "1";

	// 超时时间
	public static String it_b_pay = "1h";
}
