package com.axp.action.tag;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.dao.DateBaseDAO;


@Controller
@RequestMapping("/tag/selectTag")
public class SelectTagAction {

	@Resource
	private DateBaseDAO dateBaseDAO;
	
	
	@ResponseBody
	@RequestMapping("/tag")
	public Object tag(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
		 * 传入值
		 */
		String tableName =  request.getParameter("tableName"); // 表名
		String parentIdVal = request.getParameter("parentIdVal"); // 父级值
		String key =  request.getParameter("key");// vaule 值
		String showProperty = request.getParameter("showProperty");// 显示文本内容
		String parentProperty =  request.getParameter("parentProperty");// 父级id名
		String where =  request.getParameter("where");// 父级id名
		String defaultOptionText =  request
				.getParameter("defaultOptionText");// 默认文本
		String defalutValue =  request
				.getParameter("defalutValue");// 默认文本
		if(defalutValue == null)
			defalutValue = "";
		StringBuffer sb = new StringBuffer(100);
		if(StringUtils.isNotEmpty(defaultOptionText))
			sb.append("<option value='"+defalutValue+"' >"+defaultOptionText+"</option>");
		/*
		 * 拼接option
		 */
		String sql = "select " + key + "," + showProperty + " from " + tableName;
		sql += " where 1=1 and  isvalid = 1 ";//条件
		if(StringUtils.isNotEmpty(where) && !where.equals("null"))
			sql += " and "+where;
		if(!StringUtils.isEmpty(parentIdVal))
		sql += " and "+parentProperty+" = "+parentIdVal;
		
		List<Object> lists = dateBaseDAO.findBySql(sql);
		for (Object object : lists) {
			Object[] obj = (Object[]) object;
			String keyString = String.valueOf(obj[0]);
			String textString =String.valueOf(obj[1]);
			//添加下拉标签
			sb.append("<option value='"+keyString+"' >"+textString+"</option>");
		}
		//传出out
		out.print(sb.toString());
		return null;
	}
	
}
