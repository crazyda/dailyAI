package com.axp.action.adver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.dao.AdvertDaysChangeLogDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdvertDaysChangeLog;
import com.axp.model.ProvinceEnum;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Controller
@RequestMapping("/adverLog")
public class AdverLogAction extends BaseAction{

	@Autowired
	private AdvertDaysChangeLogDAO advertDaysChangeLogDAO;
	
	@IsItem(firstItemName = "广告管理", secondItemName = "广告天数明细")
	@RequestMapping("list")
	public String list(HttpServletRequest request){
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		Integer current_level = (Integer)request.getSession().getAttribute("userLevel");
        String pagestr = request.getParameter("page");
	    String search_name = request.getParameter("search_name")==null?"":request.getParameter("search_name");
	    Integer currentLimit = (Integer)request.getSession().getAttribute("limit");
	    StringBuffer param = new StringBuffer();//页码条件
	     AdminUser adminUser = adminUserDao.findById(current_user_id);
	     ProvinceEnum enum1 = adminUser.getProvinceEnum();
        QueryModel queryModel = new QueryModel();
        if(current_level<StringUtil.ADMIN||currentLimit!=null){
        	if (enum1.getLevel()==3) {
        		queryModel.combPreEquals("adminUser.id", current_user_id, "adminUserId");
			}else if(enum1.getLevel()==2){
				param.append("( adminUser.provinceEnum.id="+enum1.getId()).
	        	append(" or adminUser.provinceEnum.provinceEnum.id="+enum1.getId()+"" +" and '区'=SUBSTRING(REVERSE(adminUser.provinceEnum.name),1,1)").
	        	append(")");
			}else{
				param.append("( adminUser.provinceEnum.id="+enum1.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id="+enum1.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.provinceEnum.id="+enum1.getId()).
				append(")");
			}
        	queryModel.combCondition(param.toString());
        }
 		if(StringUtils.isNotBlank(search_name)){
 			queryModel.combPreLike("userRealName", search_name);	
 		}
		queryModel.setOrder(" id desc ");
		PageInfo pageInfo = new PageInfo();
		int count = 0;
		count =dateBaseDao.findCount(AdvertDaysChangeLog.class, queryModel); 
	       
		
		Utility.setPageInfomation(pageInfo,pagestr, "10",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
			
		pageInfo.setParam("&search_name="+search_name+"&page=");
		List<AdvertDaysChangeLog> logList = dateBaseDao.findPageList(AdvertDaysChangeLog.class, queryModel, start, end);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("logList", logList);
		request.setAttribute("count", count);
		return "adverLog/list";
	}
}
