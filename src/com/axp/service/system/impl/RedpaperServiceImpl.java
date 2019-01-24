package com.axp.service.system.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserRedpaper;
import com.axp.model.AdminuserRedpaperUsersReceive;
import com.axp.model.ProvinceEnum;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.RedpaperService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class RedpaperServiceImpl extends BaseServiceImpl implements RedpaperService {

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private DateBaseDAO dateBaseDAO;
	/**
	 * 获取当前用户的红包领取情况
	 */		
	public void redpaperInfo(HttpServletRequest request,
			HttpServletResponse response) {
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser=adminUserService.findById(current_user_id);
		if(adminUser.getProvinceEnum()!=null && adminUser.getProvinceEnum().getId() != null){
			Integer contryId = adminUser.getProvinceEnum().getId();
			QueryModel queryModel = new QueryModel();
			queryModel.combPreEquals("isvalid", true);
			//当前用户级别
			if(StringUtil.ADMIN > adminUser.getLevel()){
				Integer level = adminUser.getProvinceEnum().getLevel();
				StringBuffer sb = new StringBuffer();
				switch (level) {
				case 1:
					sb.append(" (provinceEnum.id = ").append(contryId)
					.append(" or provinceEnum.provinceEnum.id = ").append(contryId)
					.append(" or provinceEnum.provinceEnum.provinceEnum.id = ")
					.append(contryId).append(")");
					queryModel.combCondition(sb.toString());
					break;
				case 2:
					sb.append(" (provinceEnum.id = ").append(contryId)
					.append(" or provinceEnum.provinceEnum.id = ").append(contryId).append(")");
					break;
				default:
					sb.append(" provinceEnum.id = ").append(contryId);
					break;
				}
				queryModel.combCondition(sb.toString());
			}
			String pagestr = request.getParameter("page");
			int count = dateBaseDAO.findCount(AdminuserRedpaper.class, queryModel);
			PageInfo pageInfo = new PageInfo();
			Utility utility = new Utility();
			pageInfo.setParam("&page=");
			utility.setPageInfomation(pageInfo, pagestr, "10", count);
			int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
			int end = pageInfo.getPageSize();
			
			List<AdminuserRedpaper> findList = dateBaseDao.findPageList(AdminuserRedpaper.class, queryModel, start, end);
			
			request.setAttribute("redpaperList", findList);
			request.setAttribute("page", pagestr);
			request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
			request.setAttribute("count", count);
		}
	}
	/**
	 * 获取红包领取详情
	 */
	@Override
	public void getRedpaperDetail(HttpServletRequest request, Integer redpaperId) {
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("isvalid", true);
		queryModel.combPreEquals("adminuserRedpaper.id", redpaperId,"redpaperId");
		String pagestr = request.getParameter("page");
		int count = dateBaseDAO.findCount(AdminuserRedpaperUsersReceive.class, queryModel);
		PageInfo pageInfo = new PageInfo();
		Utility utility = new Utility();
		pageInfo.setParam("&redpaperId="+redpaperId+"&page=");
		utility.setPageInfomation(pageInfo, pagestr, "10", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		List<AdminuserRedpaperUsersReceive> findList = dateBaseDao.findPageList(AdminuserRedpaperUsersReceive.class, queryModel, start, end);
		request.setAttribute("redpaperList", findList);
		request.setAttribute("page", pagestr);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("count", count);
	}

}
