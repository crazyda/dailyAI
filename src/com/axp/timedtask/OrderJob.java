package com.axp.timedtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.axp.service.order.ReGoodsorderService;

/**
 * 换货会自动取消及确认收货
 * @author Administrator
 *
 */
public class OrderJob extends QuartzJobBean{

	
	@Autowired
	
	ReGoodsorderService rgservice;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		System.out.println("自动确认收货定时任务");
		rgservice.changeOrder();
		
		
	}

}
