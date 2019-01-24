package com.axp.service.appAdver.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.Adverpool;
import com.axp.model.Advertype;
import com.axp.model.Coin;
import com.axp.model.Goods;
import com.axp.model.ProvinceEnum;
import com.axp.service.appAdver.IAdverDaysLogService;
import com.axp.service.appAdver.IAdverPoolService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.thread.AdvertDaysChangeLogThread;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class AdverPoolServiceImpl extends BaseServiceImpl implements IAdverPoolService{
	
	
	@Override
	public void playAdver(Integer id, Integer adver_id, HttpServletRequest request){
		int current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		AdminUser current_user = adminUserDAO.findById(current_user_id);
		request.setAttribute("quantity", current_user.getQuantity()==null?0:current_user.getQuantity());
		
		List<Coin> coinlist = coinDAO.findByCon(Coin.class, "isvalid =true");
		if(coinlist.size()>0){
			Coin coin = coinlist.get(0);
			request.setAttribute("coin", coin);
		}
		List<Advertype> atlist = advertypeDao.getListByType(5);
		if(atlist.size()>0){
			Advertype advertype = atlist.get(0);
			request.setAttribute("advertype", advertype);
		}
		Adverpool adverpool;
		if(id!=null){
			adverpool = adverpoolDao.findById(id);
		}else{
			adverpool = new Adverpool();
		}
		if(adver_id != null){
			Goods sc = goodsDao.findById(adver_id);
			adverpool.setGoods(sc);
		}
		
		List<Adverpool> alist = adverpoolDao.getHideAdverPool(current_user_id);
		if(alist!=null && alist.size()>0){
			request.setAttribute("hasHigel", "1");
		}else{
			request.setAttribute("hasHigel", "0");
		}
		
		request.setAttribute("adverpool", adverpool);
		
		getChildAreaSize(request, current_user);
		getParentAreaSize(request, current_user);
	}
	
	
	@Override
	public String savePlayAdver(Integer id, Integer adver_id, Integer playtotal,Integer ishige,
			String starttime,Integer isplay, HttpServletRequest request, HttpServletResponse response){
		Goods sc = null;
		Adverpool pool = null;
		Coin coin = null;
		int current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		String playday = request.getParameter("playday");
		String areaType = request.getParameter("areaType");
		AdminUser auser = adminUserDAO.findById(current_user_id);
		List<Coin> coinlist = coinDAO.findByCon(Coin.class, "isvalid =true");
		if(coinlist.size()>0){
			coin = coinlist.get(0);
			request.setAttribute("coin", coin);
		}
		int newquantity=0;
		
		//天数余额验证
		if (auser.getQuantity() < playtotal) {
			String errorstr="你的"+coin.getName()+"不足此次投放，充值后才能正常投放";
			request.setAttribute("errorstr", errorstr);
			return "redirect:/error/error";
		}
		newquantity = auser.getQuantity() - playtotal;
		if(adver_id !=null){
			sc = goodsDao.findById(adver_id);
		}
		if(id != null){
			pool = adverpoolDao.findById(id);
			//得到延迟天数
			Integer delayedDate = DateUtil.getDiffDays(DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",DateUtil.getNow()), "yyyy-MM-dd HH:mm:ss"), 
					DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",pool.getStarttime()), "yyyy-MM-dd HH:mm:ss"));
			//得到未使用完的天数
			Date enddate = DateUtil.addDay2Date(pool.getPlaytotal(), DateUtil.getDate(starttime));
			Integer differDate = DateUtil.getDiffDays(DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",DateUtil.getNow()), "yyyy-MM-dd HH:mm:ss"), 
					DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",enddate), "yyyy-MM-dd HH:mm:ss"));
			if(differDate>0){
				Integer backDate = differDate - (delayedDate>0?delayedDate:0);
				if(backDate>0){
					newquantity = newquantity + backDate;
					returnSurplusDaysLog(auser, backDate);
				}
			}
			
		}else{
			pool = new Adverpool();
		}
		
		pool.setAdminUser(auser);
		pool.setGoods(sc);
		pool.setBatch(0);
		pool.setIsvalid(true);
		Boolean higelevel;
//		if(ishige!=null&&ishige==1){
//			higelevel = true;
//		}else{
//			higelevel = false;
//		}
		if(!StringUtils.isEmpty(areaType)){
			higelevel = true;
		}else{
			higelevel = false;
		}
		pool.setPlaytotal(playtotal);
		pool.setHigelevel(higelevel);
		Timestamp startTime = new Timestamp(DateUtil.getDate(starttime).getTime());
		pool.setStarttime(startTime);
		Date enddate = DateUtil.addDay2Date(Integer.parseInt(playday), DateUtil.getDate(starttime));;
		pool.setEndtime(new java.sql.Timestamp(enddate.getTime()));
		pool.setIsplay(isplay);
		pool.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		pool.setPlayZone((int) CalcUtil.div(playtotal, Integer.valueOf(playday)));//投放的商圈数
		
		adverpoolDao.save(pool);
		if(auser.getLevel()<StringUtil.ADMIN){
			auser.setQuantity(newquantity);
			adminUserDAO.update(auser);
		}
		sc.setAdverStatus(2);
		goodsDao.update(sc);
		/**添加日志*/
		if(auser.getLevel()<StringUtil.ADMIN){
			Map<Integer , String > data = new HashMap<Integer, String>();
			data.put(IAdverDaysLogService.QUANTITY,playtotal.toString() );
			new Thread(new AdvertDaysChangeLogThread(auser.getId(),data,IAdverDaysLogService.COST)).start();
		}
		return "redirect:poolList";
	}
	
	@Override
	public void poolList(HttpServletRequest request){
		int currentUserId = (Integer)request.getSession().getAttribute("currentUserId");
		
        String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		QueryModel model = new QueryModel().setOrder("id desc");
		model.combPreEquals("adminUser.id", currentUserId, "adminUserId");
		model.combCondition("endtime >= current_timestamp()");
		model.combPreEquals("isvalid", true);
		int count = dateBaseDao.findCount(Adverpool.class, model);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		pageInfo.setParam("&page=");
		List<Adverpool> poollist = dateBaseDao.findPageList(Adverpool.class, model, start, end);
		request.setAttribute("pageFoot", pageInfo
				.getCommonDefaultPageFootView());
		request.setAttribute("poollist", poollist);
		request.setAttribute("outtime", "no");
	}
	
	@Override
	public void outtimeList(HttpServletRequest request){
         String pagestr = request.getParameter("page");
         int currentUserId = (Integer)request.getSession().getAttribute("currentUserId");
		PageInfo pageInfo = new PageInfo();
		QueryModel model = new QueryModel().setOrder("id desc");
		model.combPreEquals("adminUser.id", currentUserId, "adminUserId");
		model.combCondition("endtime < current_timestamp()");
		model.combPreEquals("isvalid", true);
		int count = dateBaseDao.findCount(Adverpool.class, model);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();		
		
		pageInfo.setParam("&page=");
		List<Adverpool> poollist = dateBaseDao.findPageList(Adverpool.class, model, start, end);
		request.setAttribute("pageFoot", pageInfo
				.getCommonDefaultPageFootView());
		request.setAttribute("poollist", poollist);
		request.setAttribute("outtime", "yes");
	}
	
	@Override
	public void delPool(Integer id, HttpServletRequest request){
		if(id!=null){
			Adverpool apool = adverpoolDao.findById(id);
			//等到未使用完的天数
			Integer differDate = DateUtil.getDiffDays(DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",DateUtil.getNow()), "yyyy-MM-dd HH:mm:ss"), 
					DateUtil.getDate(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss",apool.getEndtime()), "yyyy-MM-dd HH:mm:ss"));
			Integer areaSize = apool.getPlayZone();//商圈数
			Integer differTotal = 0;
			if(differDate > 0 )
			{
				AdminUser adminUser = apool.getAdminUser();
				Map<Integer , String > data = new HashMap<Integer, String>();
				Integer union = 0;
				//平台
				if((adminUser.getLevel()!=null&&adminUser.getLevel()<95)||
						(adminUser.getRePermissionRoleId()!=null&&adminUser.getRePermissionRoleId()>1)){
					if (areaSize!=null) {
						differTotal = (int) CalcUtil.mul(areaSize, differDate);
					}
					adminUser.setQuantity(adminUser.getQuantity()+differTotal);
					//data.put(IAdverDaysLogService.QUANTITY, differDate.toString());
					union = differTotal;
				}
				adminUserDAO.save(adminUser);
				data.put(IAdverDaysLogService.QUANTITY, union.toString());
				new Thread(new AdvertDaysChangeLogThread(adminUser.getId(),data,IAdverDaysLogService.DEL)).start();
			}
			
			apool.setIsvalid(false);
			adverpoolDao.save(apool);
			Goods adver = apool.getGoods();
			adver.setAdverStatus(1);
			goodsDao.update(adver);
		}
	}
	
	private void returnSurplusDaysLog(AdminUser auser,Integer days){
		if(auser.getLevel()>=StringUtil.ADMIN){
			return;
		}
		/**添加日志*/
		Map<Integer , String > data = new HashMap<Integer, String>();
			data.put(IAdverDaysLogService.QUANTITY,days*-1+"" );
		new Thread(new AdvertDaysChangeLogThread(auser.getId(),data,IAdverDaysLogService.POOLRETRUN)).start();
	}
	
	private int getChildAreaSize(HttpServletRequest request,AdminUser adminUser){
		Map<String,String> map = new HashMap<String, String>();
		QueryModel model = new QueryModel(); 
		int areaSize = 0;
		if(adminUser.getLevel().equals(StringUtil.ADVERCENTER)){
			model.combPreEquals("isvalid", true);
			model.combPreEquals("level", 65);
			model.combPreEquals("provinceEnum.provinceEnum.provinceEnum.id", adminUser.getProvinceEnum().getId(),"provinceEnumId");
			List<AdminUser> list = dateBaseDao.findLists(AdminUser.class, model);
			
			map.put(adminUser.getProvinceEnum().getName(), list.size()+"个商圈");
			areaSize += list.size();
			
			/*
			for(AdminUser center : list){
				model.clearQuery();
				model.combPreEquals("parentId", center.getId());
				model.combPreEquals("isvalid", true);
				model.combPreEquals("level", 65);
				int count = dateBaseDao.findCount(AdminUser.class, model);
				if(count>0){
					map.put(center.getProvinceEnum().getName(), count+"个商圈");
					areaSize += count;
				}
			}*/
		}else if(adminUser.getLevel().equals(StringUtil.ADVERONE)){
			if (adminUser.getProvinceEnum().getLevel()==3) {
				model.combPreEquals("id", adminUser.getProvinceEnum().getId(),"provinceEnumId");
			}else{
				model.combPreEquals("provinceEnum.id", adminUser.getProvinceEnum().getId(),"provinceEnumId");
			}
			
			model.combPreEquals("isValid", true);
			List<ProvinceEnum> list = dateBaseDao.findLists(ProvinceEnum.class, model);
			for(ProvinceEnum zone : list){
				model.clearQuery();
				model.combPreEquals("parentId", adminUser.getId());
				model.combPreEquals("level", 65);
				model.combPreEquals("provinceEnum.id", zone.getId(),"provinceEnumId");
				model.combPreEquals("isvalid", true);
				int count = dateBaseDao.findCount(AdminUser.class, model);
				if(count>0){
					map.put(zone.getName(), count+"个商圈");
					areaSize += count;
				}
			}
		}else if(adminUser.getLevel().equals(StringUtil.ADVERTWO)){
			model.clearQuery();
			model.combPreEquals("level", StringUtil.ADVERTWO);
			model.combPreEquals("provinceEnum.id", adminUser.getProvinceEnum().getId(),"provinceEnumId");
			model.combPreEquals("isvalid", true);
			model.combPreEquals("level", 65);
			int count = dateBaseDao.findCount(AdminUser.class, model);
			if(count>0){
				map.put(adminUser.getProvinceEnum().getName(), count+"个商圈");
				areaSize = +count;
			}
		}else if(adminUser.getLevel().equals(StringUtil.SUPPLIER)){
			map.put(adminUser.getProvinceEnum().getName(), "本商圈");
			areaSize =1;
		}
		
		if(map.size()==0&&areaSize==0){
			map.put("所选择区域内无商圈！", "");
		}
		request.setAttribute("childAreaMap", map);
		request.setAttribute("childAreaSize", areaSize);
		return areaSize;
	}
	
	private int getParentAreaSize(HttpServletRequest request,AdminUser adminUser){
		Map<String,String> map = new HashMap<String, String>();
		QueryModel model = new QueryModel(); 
		
		int areaSize = 0;
		if(adminUser.getLevel().equals(StringUtil.ADVERONE)){
			if (adminUser.getProvinceEnum().getLevel()==3) {
				model.combPreEquals("provinceEnum.provinceEnum.provinceEnum.id", adminUser.getProvinceEnum().getProvinceEnum().getProvinceEnum().getId(),"provinceEnumId");
			}else{
				model.combPreEquals("provinceEnum.provinceEnum.provinceEnum.id", adminUser.getProvinceEnum().getProvinceEnum().getId(),"provinceEnumId");
			}
			
			model.combPreEquals("isvalid", true);
			model.combPreEquals("level", 65);
			areaSize = dateBaseDao.findCount(AdminUser.class, model);
			map.put("全省共", areaSize+"个商圈");
		}else if(adminUser.getLevel().equals(StringUtil.ADVERTWO)){
			model.combPreEquals("provinceEnum.provinceEnum.id", adminUser.getProvinceEnum().getProvinceEnum().getId(),"provinceEnumId");
			model.combPreEquals("isvalid", true);
			model.combPreEquals("level", 65);
			areaSize = dateBaseDao.findCount(AdminUser.class, model);
			map.put("全市共", areaSize+"个商圈");
		}
		if(request!=null){
			request.setAttribute("parentAreaMap", map);
		}
		request.setAttribute("parentAreaSize", areaSize);
		return areaSize;
	}
	

}
 