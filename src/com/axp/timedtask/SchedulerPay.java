package com.axp.timedtask;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerPay {

	
	
	public static void main(String[] args) {
		
		SchedulerFactory schedulerfactory=new StdSchedulerFactory();  
		
		Scheduler scheduler=null;  
		
		try {
			
			 scheduler=schedulerfactory.getScheduler();  
			 JobDetail jobDetail=new JobDetail();
			 jobDetail.setName("test_Job");
			 jobDetail.setGroup("group1");
			 jobDetail.setJobClass(TimedQueryPay.class);
			 
			/* SimpleTrigger simpleTrigger=new SimpleTrigger();
			 simpleTrigger.setName("simpleTrigger");
			 simpleTrigger.setGroup(Scheduler.DEFAULT_GROUP);
			 simpleTrigger.setStartTime(new Date(System.currentTimeMillis()));
			 simpleTrigger.setRepeatInterval(3000);
			 simpleTrigger.setRepeatCount(3);
			 scheduler.scheduleJob(jobDetail,simpleTrigger); */
			 
			 
			 CronTrigger cronTrigger=new CronTrigger();
			 cronTrigger.setName("cronTrigger");
			 cronTrigger.setGroup(Scheduler.DEFAULT_GROUP);
			 /**
			  * 1 秒 0~59 2 分 0~59 3 小时 0~24 4日期  1~31 5月 1~12 6星期  1~7 7 年 (可选 1970~2099)
			  */
			 String expression="0/3 * * * * ?";
			 cronTrigger.setCronExpression(expression);
			 scheduler.scheduleJob(jobDetail,cronTrigger);
			 scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	


	
	
}
