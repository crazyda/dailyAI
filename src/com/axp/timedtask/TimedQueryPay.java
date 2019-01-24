package com.axp.timedtask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TimedQueryPay implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		System.out.println("测试定时任务开始");
		
		
		System.out.println("测试定时任务结束");
	}

	
}
