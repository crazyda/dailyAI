package com.axp.action.database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.database.DataBaseService;
import com.axp.util.JDBCUtil;


@Controller
@RequestMapping("/db/")
public class DBAction extends BaseAction {

	
	@Autowired
	DataBaseService dataBaseService;
	
	 	@NeedPermission(permissionName = "同步数据库", classifyName = "系统管理")
	    @IsItem(firstItemName = "系统管理", secondItemName = "同步数据库")
	    @RequestMapping("list")
	    public String list(HttpServletRequest request,HttpServletResponse response) {
	 		return "database/database";
	 	}
	
	 	@RequestMapping("tabls")
	 	@ResponseBody
	 	public List<Map<String, String>> findAllTable(HttpServletRequest request,HttpServletResponse response){
	 		
	 		String dbName = request.getParameter("dbName");
	 		String userName = request.getParameter("userName");
	 		String pwd = request.getParameter("pwd");
	 		String url = request.getParameter("url");
	 		List<Map<String, String>> tabls = null;
	 		
	 	
	 		try {
	 			Properties properties=new Properties();
	 			properties.load(this.getClass().getResourceAsStream("/db2.properties"));
		 		properties.setProperty("url", url);
		 		properties.setProperty("userName", userName);
		 		properties.setProperty("pwd", pwd);
		 		FileOutputStream oFile = new FileOutputStream("db2.properties");
		 		properties.store(oFile, "Comment");
		 		oFile.close();
		 		
	 			//初始化数据源
	 			tabls = dataBaseService.findAllTableListMap(dbName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tabls;
	 	}
	
	 	/**
	 	 * 开始同步
	 	 * @param request
	 	 * @param response
	 	 */
	 	@RequestMapping("batchSynchro") 
	 	public void batchSynchro(HttpServletRequest request,HttpServletResponse response){
	 			
	 		dataBaseService.batchSynchro(request, request.getParameterValues("tableName"));
	 		
	 	}
	 	
	 	@RequestMapping("getTableStatus")
	 	@ResponseBody
	 	public Map<String, TablesStatus> getTableStatus(HttpServletRequest request,HttpServletResponse response){
	 		//简单一点 来一个遍历所有表的状态
	 		 Map<String, TablesStatus> tableStatus = dataBaseService.getTableStatus(request.getSession());
	 		 
	 		return tableStatus;
	 	}
	
	 	@RequestMapping("cleanTableStatus")
	 	public void cleanTableStatus(HttpServletRequest request,HttpServletResponse response){
	 		//简单一点 来一个遍历所有表的状态
	 		 	request.getSession().removeAttribute("tablesStatus");
	 	}
	
}

