package com.axp.timedtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.UsersDAO;
import com.axp.service.system.SystemConfigService;
@Component
public class ScoreZeroJob extends QuartzJobBean{

	@Autowired SystemConfigService systemConfigService;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
			try {
				
			
		
		//int a =systemConfigService.updateUsersScore();
		//System.out.println("更新用户数量"+a);
		//usersDao.updateByHQL("update Users set score=0 where id=811");
			} catch (Exception e) {
				
			
			}
		
	}

}
