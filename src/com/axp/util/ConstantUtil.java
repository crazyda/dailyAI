package com.axp.util;

//此类用来存放各种常量
public class ConstantUtil {

	// 存放在内存中的登陆用户；
	public static final String USER_IN_SESSION = "USER_IN_SESSION";
	public static final String SELLER_IN_SESSION = "SELLER_IN_SESSION";

	// 默认图片的地址前缀；
	public static final String PictureAddress = "";

	public static Integer AccountName;
	
	/**
	 * 基础网址；
	 */
	public static String BASE = "";// 这个值将在项目启动后的第一个访问者访问时被赋值；
	public static String BASE_PATH = "";

	/**
	 * 商家商城分佣比例常数；
	 */
	// =============================================================
	// 总部取得分佣比例；
	public static final Double adminCommission = 0.1;
	// 运营中西获得的分佣比例；
	public static final Double operationCenterCommission = 0.1;
	// 城市代理取得分佣比例；
	public static final Double cityCommission = 0.3;
	// 联盟组获得的分佣比例；
	public static final Double allianceCommission = 0.5;
	// =============================================================

	/**
	 * 金钱方面的精确度；
	 */
	// =============================================================
	// 保存精度；
	public static final Integer savePrecision = 2;
	// 计算精度；
	public static final Integer calculatePrecision = 4;

	// =============================================================

	/**
	 * 购物的超时时间；
	 */
	public static Long orderOverTime = 14 * 24 * 3600L;

}
