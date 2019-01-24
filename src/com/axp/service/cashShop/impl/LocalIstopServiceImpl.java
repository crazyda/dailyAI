package com.axp.service.cashShop.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.ReGoodsOfLocalSpecialtyMall;
import com.axp.service.cashShop.LocalIstopService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("localIstopService")
public class LocalIstopServiceImpl extends BaseServiceImpl implements LocalIstopService{

	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		String search_name = (String)request.getParameter("search_name");
		StringBuffer param = new StringBuffer();
		QueryModel model = new QueryModel().setOrder("istop desc,id desc");
		model.combPreEquals("isValid", true);
		model.combCondition("shelvesTime >= sysdate()");
		//搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("snapshotGoods.name like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(ReGoodsOfLocalSpecialtyMall.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<ReGoodsOfLocalSpecialtyMall> rlslist = dateBaseDao.findPageList(ReGoodsOfLocalSpecialtyMall.class, model, start, end);
        request.setAttribute("rlslist", rlslist);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public void ajaxTop(Integer sid, boolean istop) {
		reGoodsOfLocalSpecialtyMallDAO.ajaxTop(sid, istop);
	}

}
