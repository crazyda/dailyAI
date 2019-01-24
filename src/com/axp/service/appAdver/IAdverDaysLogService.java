package com.axp.service.appAdver;

import java.util.Map;

public interface IAdverDaysLogService{
	/**广告天数*/
	public static final int QUANTITY = 65;
	
	/**描述*/
	public static final int DEL = 0 ;//删除广告池
	
	public static final int ADDUSER = 1;//新增用户
	
	public static final int NEWUSER = 4;//被新增用户
	/**用户购买*/
	public static final int BUY = 2;
	/**用户销售*/
	public static final int SELL = 5;
	/**广告机投放广告*/
	public static final int COST = 3;
	/**商家成为代言人获得天数*/
	public static final int SELLERPROMOTER = 10;
	
	/**广告池更新返还天数*/
	public static final int POOLRETRUN = 102;
	/**二维码广告*/
	public static final int TWOA = 102;
	/**二维码广告*/
	public static final int IMG = 104;
	/**视频广告*/
	public static final int VIDEO = 103;
	
	
	/**添加广告天数变化日志
	 * @param userId 用户id
	 * @param dayCount 数量
	 * advertType : 购买，或者消费广告类型
	 *@author zhangpeng
	 *@time 2015-7-18
	 */
	public void addDaysChangeLog(Integer userId ,Map<Integer , String > dayData,Integer type);
}
