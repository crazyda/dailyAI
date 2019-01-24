package com.axp.timedtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.axp.service.money.FansGetmoneyRecordService;
import com.axp.service.money.SellerWithdrawService;

@Component  
public class QueryPayTimedJob extends QuartzJobBean {

	@Autowired
	private FansGetmoneyRecordService fansService;
	
	@Autowired
	private SellerWithdrawService sellerService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			
		  System.out.println("任务调度启动");
		  System.out.println("粉丝定时查询易联代付----开始");
		  fansService.payList(1, 10,-1);
		  System.out.println("粉丝定时查询易联代付----结束");
		  
		  
		  System.out.println("商家定时查询易联代付----开始");
		  sellerService.payList(1,10,null,-1);
		  System.out.println("商家定时查询易联代付----结束");
		  System.out.println("任务调度结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
