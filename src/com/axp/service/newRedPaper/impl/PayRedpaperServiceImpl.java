package com.axp.service.newRedPaper.impl;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserRedpaper;
import com.axp.service.newRedPaper.PayRedpaperService;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("PayRedpaperService")
public class PayRedpaperServiceImpl extends BaseServiceImpl implements PayRedpaperService{
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private AdminUserService adminUserService;
	@Override
	public AdminuserRedpaper findById(Integer redpId) {
		return adminuserRedpaperDao.findById(redpId);
	}
	@Override
	public void getlist(HttpServletRequest request, HttpServletResponse response) {
		String id=(String)request.getParameter("redpaper_id");
		String search_name = (String)request.getParameter("search_name");
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser=adminUserService.findById(current_user_id);
		Integer current_level= adminUser.getLevel();
		StringBuffer param = new StringBuffer();
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true);
		queryModel.combPreEquals("adminUser.id", adminUser.getId(),"adminUserId");
		queryModel.combCompare("adminUser.level", current_level, 4);
		queryModel.setOrder("id desc");
		//id
	    if(!StringUtils.isEmpty(id )){
	    	queryModel.combPreEquals("id", Integer.parseInt(id ));
	    	param.append("&redpaper_id="+id );
	    }
	    //搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	queryModel.combCondition("name like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
		String pagestr = request.getParameter("page");
		int count = dateBaseDAO.findCount(AdminuserRedpaper.class, queryModel);
		PageInfo pageInfo = new PageInfo();
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		List<AdminuserRedpaper> rlist= dateBaseDAO.findPageList(AdminuserRedpaper.class, queryModel, start, end);
		request.setAttribute("count", count);
		request.setAttribute("rlist", rlist);
		request.setAttribute("page", pagestr);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	@Override
	public void isPay(HttpServletRequest request, Integer redpaperId) {
		AdminuserRedpaper adminuserRedpaper=null;
		if (redpaperId!=null) {
			adminuserRedpaper=adminuserRedpaperDao.findById(redpaperId);
		}
		if (adminuserRedpaper.getSurplusQuantity()!=0) {
			adminuserRedpaper.setStatus(10);
		}else {
			adminuserRedpaper.setStatus(0);
		}
		adminuserRedpaperDao.saveOrUpdate(adminuserRedpaper);
	}

}
