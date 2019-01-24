package com.axp.util;

import javax.servlet.http.HttpSession;

public class ActionContext {
	private static ThreadLocal<HttpSession> local = new ThreadLocal<HttpSession>();
	public static void set(HttpSession session){
		local.set(session);
	}
	public static HttpSession get(){
		return local.get();
	}
}
