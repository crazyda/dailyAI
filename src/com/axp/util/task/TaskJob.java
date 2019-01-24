package com.axp.util.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axp.service.order.ReGoodsorderService;

@Component("TaskJob")
public class TaskJob {
	@Autowired
	ReGoodsorderService rgservice;
	public void doTask() {//定时任务
//		//确认订单
//		System.out.println("开始");
//		rgservice.changeOrder();
//		System.out.println("结束");
	}
	
	
}
