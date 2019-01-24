package com.axp.service.appAdver.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.model.AdminUser;
import com.axp.model.AdvertDaysChangeLog;
import com.axp.service.appAdver.IAdverDaysLogService;
import com.axp.service.system.impl.BaseServiceImpl;

public class AdverDaysLogServiceImpl extends BaseServiceImpl implements IAdverDaysLogService {
	/**描述信息配置*/
	private static Map<Integer  , String > descripConfig;
	
	private static final AdverDaysLogServiceImpl singleton = new AdverDaysLogServiceImpl();
    public static AdverDaysLogServiceImpl getInstance()    
    {    
    	descripConfig = new HashMap<Integer, String>();
    	descripConfig.put(ADDUSER, "成为新用户获得广告天数");
    	descripConfig.put(NEWUSER, "新增用户减少广告天数");
    	descripConfig.put(BUY, "购买广告天数");
    	descripConfig.put(SELL, "出售广告天数");
    	descripConfig.put(COST, "广告池投放广告消费天数");
    	descripConfig.put(TWOA, "投放二维码广告消费天数");
    	descripConfig.put(IMG, "投放图片广告消费天数");
    	descripConfig.put(VIDEO, "投放视频广告消费天数");
    	descripConfig.put(SELLERPROMOTER, "商家成为推广员获得广告天数");
    	descripConfig.put(DEL, "删除广告返还天数");
    	descripConfig.put(POOLRETRUN, "修改广告池返还未使用广告天数");
    	return singleton;    
    }

	@Override
	public void addDaysChangeLog(Integer userId, Map<Integer, String> dayData,
			Integer type) {
		AdminUser user = adminUserDAO.findById(userId);

		AdvertDaysChangeLog logs = new AdvertDaysChangeLog();
		logs.setAdminUser(user);
		logs.setUserRealName(user.getUsername());
		logs.setParentId(user.getParentId());
		// 描述
		logs.setDiscript(descripConfig.get(type));
		try {
			// 执行减操作
			if (type >= COST) {
				logs = swichtData(dayData, logs, true);
			}
			// 执行加操作
			else {
				logs = swichtData(dayData, logs, false);
			}

			logs.setCreateTime(new java.sql.Timestamp(System
					.currentTimeMillis()));
			advertDaysLogDAO.save(logs);
			// 广告天数(新增用户、用户购买)，需要增加父级天数记录的情况
			if (type != COST && type != DEL) {
				AdminUser parentUser = user.getParentAdminUser();
				AdvertDaysChangeLog log2 = new AdvertDaysChangeLog();
				log2.setDiscript(descripConfig.get(type + 3));
				log2.setParentId(-1);
				// log2.setUserId(parentUser.getId());
				log2.setAdminUser(parentUser);
				log2.setUserRealName(parentUser.getUsername());
				log2.setCreateTime(new java.sql.Timestamp(System
						.currentTimeMillis()));
				log2 = swichtData(dayData, log2, true);
				advertDaysLogDAO.save(log2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    

		}
	}
	/**将天数放进去
	 * @param map
	 * @param logs
	 *@author zhangpeng
	 *flag = true .减。
	 *@time 2015-7-18
	 */
	private AdvertDaysChangeLog  swichtData(Map<Integer,String > map,AdvertDaysChangeLog logs ,boolean flag)
	{
		for(Integer key : map.keySet())
		{
			int advertType = key;
			int dayCount = flag ? -Integer.parseInt(map.get(key)) : Integer.parseInt(map.get(key));
			switch (advertType) {
			case QUANTITY:
				logs.setUnionDaycounts(dayCount);
				break;
			default:
				break;
			}
		}
		return logs;
	}
   
}
