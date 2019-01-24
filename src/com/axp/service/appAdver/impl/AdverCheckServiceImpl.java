package com.axp.service.appAdver.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.IBuyDao;
import com.axp.model.AdminUser;
import com.axp.model.Buy;
import com.axp.service.appAdver.IAdverCheckService;
import com.axp.service.appAdver.IAdverDaysLogService;
import com.axp.service.invoke.impl.BaseServiceImpl;
import com.axp.thread.AdvertDaysChangeLogThread;

@Service("adverCheckService")
public class AdverCheckServiceImpl extends BaseServiceImpl implements IAdverCheckService{
	@Autowired
	private IBuyDao buyDao;
	@Autowired
	private AdminUserDAO adminUserDao;
	
	
	@Override
	public void confirm(HttpServletRequest request) {
		int quantity = 0;			//变动的联盟组广告天数	
		String id = request.getParameter("id");
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		AdminUser current_user = adminUserDao.findById(current_user_id);
		
		Buy sc = buyDao.findById(Integer.parseInt(id));
		AdminUser au = sc.getAdminUser();
				

		sc.setStatus(2);
		sc.setChecker(current_user.getId());
		sc.setCheckstr(current_user.getUsername());
		sc.setChecktime(new Timestamp(System.currentTimeMillis()));
		
		quantity = sc.getQuantity();
		au.setQuantity(au.getQuantity()==null?quantity:au.getQuantity()+quantity);
		
		adminUserDao.merge(au);
		buyDao.merge(sc);
		/**添加日志*/
		Map<Integer , String > data = new HashMap<Integer, String>();
		data.put(IAdverDaysLogService.QUANTITY,quantity+"");
		new Thread(new AdvertDaysChangeLogThread(au.getId(),data,IAdverDaysLogService.BUY)).start();
	}


	@Override
	public void del(HttpServletRequest request) {
		String id = request.getParameter("id");
		Buy buy = buyDao.findById(Integer.parseInt(id));
		buy.setIsvalid(false);
		buyDao.merge(buy);
	}

}
