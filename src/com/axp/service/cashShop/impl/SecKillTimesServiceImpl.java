package com.axp.service.cashShop.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.CashshopTimes;
import com.axp.service.cashShop.ISecKillTimesService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class SecKillTimesServiceImpl extends BaseServiceImpl implements ISecKillTimesService{
	@Override
	public void list(HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("currentUserId");
		Integer level = (Integer) request.getSession().getAttribute("userLevel");
		String page = request.getParameter("page");

		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		if (level.equals(StringUtil.ADMIN)) {
			model.combPreEquals("user.id", id, "userId");
		} else {
			AdminUser HQ = adminUserDAO.getHQ();
			model.combCondition("user.id in (" + HQ.getId() + "," + id + ")");
		}
		model.setOrder("id desc");
		PageInfo pageInfo = new PageInfo();
		int count = 0;

		count = dateBaseDao.findCount(CashshopTimes.class, model);
		Utility.setPageInfomation(pageInfo, page, "10", count);

		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();

		List<CashshopTimes> list = dateBaseDao.findPageList(
				CashshopTimes.class, model, start, end);
		
		request.setAttribute("timesList",list);
		request.setAttribute("count",count);
		request.setAttribute("pageFoot",
				pageInfo.getCommonDefaultPageFootView());
	}
	
	@Override
	public void add(HttpServletRequest request) {
		Integer id = (Integer) request.getSession().getAttribute("currentUserId");
		Integer count = cashshopTimesDao.getTimesCount(id);
		request.setAttribute("size", 6-count);
	}
	
	@Override
	public void edit(Integer id, HttpServletRequest request) {
		CashshopTimes editObject = cashshopTimesDao.findById(id);
		request.setAttribute("cs", editObject);
	}
	
	@Override
	public void save(HttpServletRequest request) {
		Integer currentUserId = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser currentUser = adminUserDAO.findById(currentUserId);
		
		String[] slist=request.getParameterValues("startTime");
		String[] elist=request.getParameterValues("endTime");
		if(slist!=null&&elist!=null){
			for(int i=0;i<slist.length;i++){
				if(StringUtils.isBlank(slist[i])){
					continue;
				}
				CashshopTimes editObject=new CashshopTimes();
				editObject.setUser(currentUser);
				Timestamp time = new Timestamp(System.currentTimeMillis());
				editObject.setCreateTime(time);
				editObject.setIsValid(true);
				editObject.setStartTime(slist[i]+":00");
				editObject.setEndTime(elist[i]+":00");
				
				cashshopTimesDao.save(editObject);
			}
		}
	}
	
	@Override
	public void update(Integer id, String endTime, String startTime, HttpServletRequest request) {
		Integer currentUserId = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser currentUser = adminUserDAO.findById(currentUserId);
		
		CashshopTimes ct = cashshopTimesDao.findById(id);
		
		ct.setEndTime(endTime.substring(0, 5)+":00");
		ct.setStartTime(startTime.substring(0, 5)+":00");
		ct.setUser(currentUser);
		cashshopTimesDao.update(ct);
	}
	
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		CashshopTimes cashshopTimes = cashshopTimesDao.findById(id);
		cashshopTimes.setIsValid(false);
		cashshopTimesDao.update(cashshopTimes);
	}
	
	@PostConstruct
	public void init() {}
	
}
