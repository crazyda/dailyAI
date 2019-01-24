package com.axp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public final class ToolSpring extends ApplicationObjectSupport{
	private static ApplicationContext applicationContext = null;
	@Override
	protected void initApplicationContext(ApplicationContext context)throws BeansException {
	// TODO Auto-generated method stub
		super.initApplicationContext(context);
	 		if(ToolSpring.applicationContext == null){
	 			ToolSpring.applicationContext = context;
	 		}
		}
	
	public static ApplicationContext getAppContext() {
		return applicationContext;
	}
	
	public static Object getBean(String name){
		return getAppContext().getBean(name);
	}
}
