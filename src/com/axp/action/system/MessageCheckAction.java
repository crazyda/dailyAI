package com.axp.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.util.httpClient.HttpResponse;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.MessageCenterDAO;
import com.axp.model.AdminUser;
import com.axp.model.MessageCenter;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Controller
@RequestMapping("/messageCheck")
public class MessageCheckAction extends BaseAction{

	@Autowired
	private MessageCenterDAO centerDAO;
	@Autowired
	private DateBaseDAO dateBaseDAO;
	
	
	
	@IsItem(firstItemName = "业务管理", secondItemName = "推送消息审核列表")
	@NeedPermission(permissionName = "推送消息审核列表", classifyName = "业务管理")
	@RequestMapping("list")
	public String list(HttpServletRequest request,HttpResponse response){
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
	
		AdminUser user = adminUserDao.findById(current_user_id);
		if(user.getLevel()>=StringUtil.ADMIN){
			QueryModel queryModel = new QueryModel();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("checkStatus", 0);
			//查询符合条件的总记录数
			int count = dateBaseDao.findCount(MessageCenter.class, queryModel);
			//---------分页----------
			 String pagestr = request.getParameter("page");
		     PageInfo pageInfo = new PageInfo();
		     Utility.setPageInfomation(pageInfo, pagestr, "20",count);
		     int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		     int end = pageInfo.getPageSize();
		     pageInfo.setParam("&page=");
		     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		     
		     List<MessageCenter> messagelist = dateBaseDao.findPageList(MessageCenter.class, queryModel, start, end);
		     request.setAttribute("messagelist", messagelist);
		}

		return "messageCheck/checkList";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,HttpServletResponse response){
		
		String id = request.getParameter("id");
		MessageCenter message = null;
		if (id != null && id.length() > 0) {
			message = centerDAO.findById(Integer.parseInt(id));
			request.setAttribute("message", message);
		}
		
		return "messageCheck/checkCityUeditor";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String checkStatus = request.getParameter("checkStatus");
		MessageCenter message = centerDAO.findById(Integer.parseInt(id));
		message.setCheckStatus(Integer.parseInt(checkStatus));
		centerDAO.update(message);
		return "redirect:list";
	}
}
