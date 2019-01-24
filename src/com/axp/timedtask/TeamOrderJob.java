package com.axp.timedtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.axp.service.goods.ReGoodsOfTeamMallService;
import com.axp.service.money.FansGetmoneyRecordService;
import com.axp.service.money.SellerWithdrawService;
import com.axp.service.money.impl.FansGetmoneyRecordServiceImpl;
import com.axp.service.money.impl.SellerWithdrawServiceImpl;

/**
 * 拼团定时查询
 * @author Administrator
 *
 */
@Component  
public class TeamOrderJob extends QuartzJobBean {

	@Autowired
	ReGoodsOfTeamMallService reGoodsOfTeamMallService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
		
			
		  System.out.println("拼团任务调度启动");
		  	reGoodsOfTeamMallService.teamOrderTimedtask();
		  	
		  System.out.println("拼团任务调度结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
