package com.axp.service.system.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.Users;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.UserInfoService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl implements UserInfoService{
	@Resource
	private AdminUserService adminUserService;
	
	@Override
	public void getUserInfo(HttpServletRequest request,
			HttpServletResponse response,String mixM,String maxM) {
		String id=(String)request.getParameter("users_id");
		String search_name = (String)request.getParameter("search_name");
		String code = request.getParameter("code");
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Integer current_level= adminUser.getLevel();
        StringBuffer param = new StringBuffer();//页码条件
	    QueryModel model = new QueryModel();
		model.combPreEquals("id", id );
		model.combPreEquals("isvalid", true);
		model.combPreEquals("adminUser.id", current_user_id, "adminUserId");
		model.combCompare("adminUser.level", current_level, 2);
		//id
	    if(!StringUtils.isEmpty(id )){
	    	model.combPreEquals("id", Integer.parseInt(id ));
	    	param.append("&users_id="+id );
	    }
	    //搜索
		if (!StringUtils.isEmpty(code)) {
			model.combPreEquals("mycode", code);
			model.orEquals("invitecode", code);
		}
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("name like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    Double mixMoney=null;
	    Double maxMoney=null;
	    if (!StringUtil.isEmpty(mixM)) {
	    	mixMoney=Double.valueOf(mixM);
		}
		if (!StringUtil.isEmpty(maxM)) {
			maxMoney=Double.valueOf(maxM);
		}
		if (mixM==null) {
			mixM="";
			maxM="";
		}
	    if(null==maxMoney){
			 model.combPreCompare("money",mixMoney,QueryModel.GREATER_EQUALS); 
		 }
		 if(null==mixMoney){
			 model.combPreCompare("money",maxMoney,QueryModel.LESS_EQUALS);
		 }
		 if(mixMoney!=null&&maxMoney!=null){
			 model.getSb().append(" AND (money >=:mixMoney");
			 model.getSb().append(" AND money <=:maxMoney)");
			 model.getPreConditionMap().put("mixMoney", mixMoney);
			 model.getPreConditionMap().put("maxMoney", maxMoney);
		 }
		 model.setOrder("id desc");
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(Users.class, model);
        Double totalMoney = dateBaseDao.findSum(Users.class, "money", model);
        Double totalScore = dateBaseDao.findSum(Users.class, "score", model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        List<Users> userslist=dateBaseDao.findPageList(Users.class, model, start, end);
        pageInfo.setParam("&mixM="+mixM+"&maxM="+maxM+"&page=");
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("totalScore", totalScore);
        request.setAttribute("mixM", mixM);
        request.setAttribute("maxM", maxM);
        request.setAttribute("userslist", userslist);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	@Override
	public void del(String ids) {
		usersDAO.del(ids);
	}

}
