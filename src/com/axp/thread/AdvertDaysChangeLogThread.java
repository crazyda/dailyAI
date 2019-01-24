package com.axp.thread;

import java.util.Map;

import com.axp.service.system.AdverDaysLogService;
import com.axp.util.ToolSpring;

public class AdvertDaysChangeLogThread implements Runnable {
	
	private Integer userId;
	private Integer type;
	private Map<Integer, String > map;
	
	public AdvertDaysChangeLogThread()
	{
		
	}
	
	public AdvertDaysChangeLogThread(Integer userId, Map<Integer, String > map, Integer type)
	{
		this.userId = userId;
		this.type = type;
		this.map = map;
	}
	
	@Override
	public void run() {
		AdverDaysLogService adverDaysLogService = (AdverDaysLogService) ToolSpring.getBean("adverDaysLogService");
		adverDaysLogService.addDaysChangeLog(userId, map, type);
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Map<Integer, String> getMap() {
		return map;
	}
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

}
