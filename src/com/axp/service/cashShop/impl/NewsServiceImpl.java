package com.axp.service.cashShop.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.NewsDao;
import com.axp.model.AdminUser;
import com.axp.model.News;
import com.axp.model.ProvinceEnum;
import com.axp.service.cashShop.NewsService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsService{
	@Resource
	private NewsDao newsDao;
	
	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		Integer current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		String search_name = request.getParameter("search_name");
		StringBuffer param = new StringBuffer();
		QueryModel model = new QueryModel().setOrder("id desc");
		model.combEquals("status", 1);
		model.combEquals("adminuserId", current_user_id);
		
		//搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("title like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(News.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<News> newslist = dateBaseDao.findPageList(News.class, model, start, end);
        request.setAttribute("newslist", newslist);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}

	@Override
	public void add(HttpServletRequest request, Integer id) {
		News news =null;
		if (id!=null) {
			news = newsDao.findById(id);
		}else{
			news = new News();
		}
		request.setAttribute("news", news);
	}

	@Override
	public void save(HttpServletRequest request, Integer id,String title, String url,
			Timestamp createTime, Integer zoneId, Integer adminuserId) {
		Integer current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		AdminUser au = adminUserDAO.findById(current_user_id);
		ProvinceEnum provinceEnum = au.getProvinceEnum();
		News news =null;
		if (id!=null) {
			news = newsDao.findById(id);
		}else{
			news = new News();
		}
		
		news.setAdminuserId(au==null?0:au.getId());
		news.setZoneId(provinceEnum==null?0:provinceEnum.getId());
		news.setTitle(title);
		news.setUrl(url);
		news.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		news.setStatus(Short.valueOf("1"));
		newsDao.saveOrUpdate(news);
		
	}

	@Override
	public void del(HttpServletRequest request, Integer id) {
		News news = null;
		if (id!=null) {
			news = newsDao.findById(id);
			news.setStatus(Short.valueOf("0"));
			news.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		}
		newsDao.saveOrUpdate(news);
	}

}
