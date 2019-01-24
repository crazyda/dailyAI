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
import com.axp.model.AdminuserRedpaperUsersReceive;
import com.axp.query.PageResult;
import com.axp.service.newRedPaper.AdminuserRedpaperReceiveService;
import com.axp.service.newRedPaper.PayRedpaperService;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("AdminuserRedpaperReceiveService")
public class AdminuserRedpaperServiceImpl extends BaseServiceImpl implements AdminuserRedpaperReceiveService{
	@Resource
	private DateBaseDAO dateBaseDAO;
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private PayRedpaperService payRedpaperService;
	@Override
	public void getRedpaperDetailById(HttpServletRequest request, Integer redpId) {
		AdminuserRedpaper adminuserRedpaper=payRedpaperService.findById(redpId);
		StringBuffer param = new StringBuffer();
		QueryModel queryModel = new QueryModel();
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true);
		queryModel.combPreEquals("adminuserRedpaper.id", adminuserRedpaper.getId(),"redpId");
		String pagestr = request.getParameter("page");
		
		int count = dateBaseDAO.findCount(AdminuserRedpaperUsersReceive.class, queryModel);
		PageInfo pageInfo = new PageInfo();
		Utility utility = new Utility();
		utility.setPageInfomation(pageInfo, pagestr, "10", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		pageInfo.setParam(param + "&page=");
		List<AdminuserRedpaperUsersReceive> Userslist= dateBaseDAO.findPageList(AdminuserRedpaperUsersReceive.class, queryModel, start, end);
		double money =dateBaseDao.findSum(AdminuserRedpaperUsersReceive.class,"money", queryModel);
		request.setAttribute("adminuserRedpaper", adminuserRedpaper);
		request.setAttribute("count", count);
		request.setAttribute("money", money);
		request.setAttribute("Userslist", Userslist);
		request.setAttribute("page", pagestr);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	
	@Override
	public Integer countById(HttpServletRequest request,
			HttpServletResponse response, Integer redPaperId) {
		AdminuserRedpaper bean =adminuserRedpaperDao.findById(redPaperId);
		request.setAttribute("redPaperCount", bean.getSurplusQuantity()==null?0:bean.getSurplusQuantity());
		request.setAttribute("redPaperMoney", bean.getSurplusMoney()==null?0d:bean.getSurplusMoney());
		if(bean==null) return 0;
		return bean.getSurplusQuantity();
	}
}
