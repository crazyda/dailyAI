package com.axp.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.ReFeedback;
import com.axp.model.Users;
import com.axp.service.system.FeedbackService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl implements FeedbackService{

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response) {
	    QueryModel queryModel = new QueryModel();
	    queryModel.combPreEquals("isValid", true);
		
		StringBuffer param = new StringBuffer();
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		int count = dateBaseDao.findCount(ReFeedback.class, queryModel);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
	    int end = pageInfo.getPageSize();
	    pageInfo.setParam(param + "&page=");
		ReFeedback feedback = null;
		Users users = null;
	    List<ReFeedback> list = dateBaseDao.findPageList(ReFeedback.class, queryModel, start, end);
		if (list.size()>0) {
			feedback = list.get(0);
			users = feedback.getUser();
			if (users!=null) {
				request.setAttribute("users", users);
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

}
