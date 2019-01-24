package com.axp.timedtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.axp.service.order.ChangeOrderService;
import com.axp.service.professional.ScoreMarkService;
import com.axp.service.professional.UserDarwService;

/**
 * 换货会自动取消及确认收货
 * @author Administrator
 *
 */
public class changeSurplusJob extends QuartzJobBean{

	
	@Autowired
	UserDarwService userDarwService;
	
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		System.out.println("恢复每天抽奖次数启动");
		userDarwService.changeSurplus();
		System.out.println("恢复每天抽奖次数结束");
		
		
		
	}

}
