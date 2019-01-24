package com.axp.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class ResponseResult {

	
	private Boolean success = true;// 返回的信息性质；
	private String msg="操作成功";// 返回的信息内容
	private Object obj; //主体信息
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * 返回json  
	 * @param response
	 * @param flag  
	 * @param map
	 */
	public static void printResult(HttpServletResponse response,boolean flag,Map<String, Object> map) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw=response.getWriter();
			Map<String, Object> maps=new HashMap<String, Object>();
			if(map!=null){
				pw.print( JSONObject.toJSONString(map));
			}else{
				if(flag){
					
					maps.put("flag", "1");
					maps.put("msg", "操作成功");
				}else{
					maps.put("msg", "操作失败");
					
				}
				pw.print( JSONObject.toJSONString(maps));
			}
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回json  
	 * @param response
	 * @param flag  
	 * @param map
	 */
	public static void printResult(HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");  
			PrintWriter pw=response.getWriter();
			Map<String, Object> maps=new HashMap<String, Object>();
					maps.put("flag", "1");
					maps.put("msg", "操作成功");
				pw.print( JSONObject.toJSONString(maps));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
