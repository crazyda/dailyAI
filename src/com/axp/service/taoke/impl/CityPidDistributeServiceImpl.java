package com.axp.service.taoke.impl;




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Service;

import com.axp.dao.CityPidDistributeDao;
import com.axp.dao.PartnerPidDistributeDao;
import com.axp.dao.TaokePidDao;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserTaokePid;
import com.axp.model.CityAdminuserPidDistribute;
import com.axp.model.PartnerAdminuserPidDistribute;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.CityPidDistributeService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service
public class CityPidDistributeServiceImpl extends BaseServiceImpl implements CityPidDistributeService {
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private TaokePidDao taokePidDao;
	@Resource
	private CityPidDistributeDao cityPidDistributeDao;
	@Resource
	private PartnerPidDistributeDao partnerPidDistributeDao;
	@Override
	public void isDistribute(Integer ids, HttpServletRequest request,
			HttpServletResponse response) {
		AdminUser adminUser=null;
		AdminuserTaokePid adminuserTaokePid=null;
		if (ids!=null) {
			adminuserTaokePid=taokePidDao.findById(ids);
		}
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		List<AdminUser> centerlist =null;
		List<AdminUser> agencylist =null;
		AdminUser  au= adminUserDAO.findById(current_user_id);
		if (au.getLevel()>=95) {
			centerlist=adminUserService.getLevel(85);
		}else if(au.getLevel()>=85){
			agencylist=adminUserDAO.findByPropertyWithValid("adminUser.id", au.getId());
		}
		request.setAttribute("centerlist", centerlist);
		request.setAttribute("agencylist", agencylist);
		request.setAttribute("adminUser", adminUser);
		request.setAttribute("adminuserTaokePid", adminuserTaokePid);
		}
	
	@Override
	public void ofDistribute(Integer ids, HttpServletRequest request,
			HttpServletResponse response){
		AdminuserTaokePid adminuserTaokePid=null;
		CityAdminuserPidDistribute adminuserPidDistribute=null;
		if (ids!=null) {
			adminuserTaokePid=taokePidDao.findById(ids);
			QueryModel model=new QueryModel();
			model.combPreEquals("isValid", true);
			model.combPreEquals("pidId", adminuserTaokePid.getId());
			List<CityAdminuserPidDistribute> clist=dateBaseDao.findPageList(CityAdminuserPidDistribute.class, model, 0, 10);
			if (clist!=null&& clist.size()>0) {
				adminuserPidDistribute=clist.get(0);
			}
		}
		if (adminuserTaokePid.getStatus()==1) {
			adminuserTaokePid.setStatus(0);
			adminuserTaokePid.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			adminuserPidDistribute.setIsValid(false);
			adminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		}
		taokePidDao.saveOrUpdate(adminuserTaokePid);
		cityPidDistributeDao.saveOrUpdate(adminuserPidDistribute);
	}
	
	//强制回收
	@Override
	public void recycleDistribute(Integer ids, HttpServletRequest request,
			HttpServletResponse response) {
		AdminuserTaokePid adminuserTaokePid= null;
		CityAdminuserPidDistribute cityAdminuserPidDistribute = null;
		if (ids!=null) {
			adminuserTaokePid = taokePidDao.findById(ids);
			QueryModel queryModel = new QueryModel();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("pidId", adminuserTaokePid.getId());
			List<CityAdminuserPidDistribute> clist = dateBaseDao.findPageList(CityAdminuserPidDistribute.class, queryModel, 0, 10);
			if (clist!=null && clist.size()>0) {
				cityAdminuserPidDistribute = clist.get(0);
			}
		
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("pidId", cityAdminuserPidDistribute.getId());
			List<PartnerAdminuserPidDistribute> plist = dateBaseDao.findPageList(PartnerAdminuserPidDistribute.class, queryModel, 0, 10);
			if (plist!=null && plist.size()>0) {
				for (PartnerAdminuserPidDistribute partnerAdminuserPidDistribute : plist) {
					if (cityAdminuserPidDistribute.getStatus()==1) {
						cityAdminuserPidDistribute.setStatus(0);
						partnerAdminuserPidDistribute.setIsValid(false);
						partnerAdminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
						partnerAdminuserPidDistribute.setUsername("");
					}
					partnerPidDistributeDao.saveOrUpdate(partnerAdminuserPidDistribute);
				}
			}
			
			
			if (adminuserTaokePid.getStatus()==1) {
				adminuserTaokePid.setStatus(0);
				adminuserTaokePid.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				cityAdminuserPidDistribute.setIsValid(false);
				cityAdminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
		}
		taokePidDao.saveOrUpdate(adminuserTaokePid);
		cityPidDistributeDao.saveOrUpdate(cityAdminuserPidDistribute);
	}
	
	
	@Override
	public void saveDistribute(Integer id,Integer pid, String pidId, Integer adminuserId,
			Timestamp createtime, HttpServletRequest request,
			HttpServletResponse response) {
		AdminuserTaokePid adminuserTaokePid=null;
		CityAdminuserPidDistribute adminuserPidDistribute=null;
		if (id!=null) {
			adminuserTaokePid=taokePidDao.findById(id);
			adminuserPidDistribute=new CityAdminuserPidDistribute();
		}
		
		AdminUser adminUser=null;
		List<AdminUser> alist=adminUserDAO.findByCon(AdminUser.class, "id="+adminuserId);
		if (alist!=null&&alist.size()>0) {
			adminUser=alist.get(0);
		}
			if (adminuserTaokePid.getStatus()==0) {
				adminuserTaokePid.setStatus(1);
				adminuserPidDistribute.setAdminuserTaokePid(adminuserTaokePid);
				adminuserPidDistribute.setAdminUser(adminUser);
				adminuserPidDistribute.setStatus(0);
				adminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				adminuserPidDistribute.setIsValid(true);
				
			}
			taokePidDao.saveOrUpdate(adminuserTaokePid);
			cityPidDistributeDao.saveOrUpdate(adminuserPidDistribute);
		}
	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		 StringBuffer param = new StringBuffer();
		 Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		 QueryModel model=new QueryModel();
		 model.clearQuery();
		 model.combPreEquals("isValid", true);
		 model.combPreEquals("adminUser.id", current_user_id,"adminuserId");
		 String pagestr = request.getParameter("page");
	     PageInfo pageInfo = new PageInfo();
	     int count = dateBaseDao.findCount(CityAdminuserPidDistribute.class, model);
	     Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
	     int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
	     int end = pageInfo.getPageSize();
	     pageInfo.setParam(param + "&page=");
	     List<CityAdminuserPidDistribute> clist=dateBaseDao.findPageList(CityAdminuserPidDistribute.class, model, start, end);
	     List<CityAdminuserPidDistribute> list=new ArrayList<CityAdminuserPidDistribute>();
	     		for(CityAdminuserPidDistribute cityAdminuserPidDistribute:clist){
	     			
	     			if(cityAdminuserPidDistribute.getStatus()!=null&&cityAdminuserPidDistribute.getStatus()==1){
	     			    model.clearQuery();
	     			    model.combPreEquals("isValid", true);
		     			model.combPreEquals("pidId", cityAdminuserPidDistribute.getId());
		     			List<PartnerAdminuserPidDistribute> plist=dateBaseDao.findPageList(PartnerAdminuserPidDistribute.class, model, 0, 0);
		     			if (plist!=null&&plist.size()>0) {
							PartnerAdminuserPidDistribute partnerAdminuserPidDistribute=plist.get(0);
							cityAdminuserPidDistribute.setFengpei(partnerAdminuserPidDistribute.getUsers().getName());
							cityAdminuserPidDistribute.setUsername(partnerAdminuserPidDistribute.getUsername());
					}
	     			}
	     			list.add(cityAdminuserPidDistribute);
	     	}
	     request.setAttribute("count", count);
	     request.setAttribute("clist", clist);
	     request.setAttribute("list", list);
	     request.setAttribute("page", pagestr);
	     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	@Override
	public void allDistribute(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		AdminUser adminUser=null;
		AdminuserTaokePid adminuserTaokePid=null;
		List<AdminuserTaokePid>list=new ArrayList<AdminuserTaokePid>();
		if (ids!=null) {
			String[]tempIds=ids.split(",");
			for(int i=0;i<tempIds.length;i++){
				QueryModel queryModel=new QueryModel();
				queryModel.combPreEquals("isValid", true);
				queryModel.combPreEquals("id", Integer.parseInt(tempIds[i]));
				List<AdminuserTaokePid> alist=dateBaseDao.findPageList(AdminuserTaokePid.class, queryModel, 0, 0);
				if (alist!=null&&alist.size()>0) {
					adminuserTaokePid=alist.get(0);
					list.add(alist.get(0));
				}
			}
			request.setAttribute("alist", list);
		}
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		List<AdminUser> centerlist =null;
		List<AdminUser> agencylist =null;
		AdminUser  au= adminUserDAO.findById(current_user_id);
		if (au.getLevel()>=95) {
			centerlist=adminUserService.getLevel(85);
		}else if(au.getLevel()>=85){
			agencylist=adminUserDAO.findByPropertyWithValid("adminUser.id", au.getId());
		}
		request.setAttribute("centerlist", centerlist);
		request.setAttribute("agencylist", agencylist);
		request.setAttribute("ids", ids);
		request.setAttribute("adminUser", adminUser);
		request.setAttribute("adminuserTaokePid", adminuserTaokePid);
		}
	@Override
	public void saveAll(String ids, Integer pid, String pidId,
			Integer adminuserId, Timestamp createtime,
			HttpServletRequest request, HttpServletResponse response) {
		AdminuserTaokePid adminuserTaokePid=null;
		CityAdminuserPidDistribute adminuserPidDistribute=null;
		if (ids!=null) {
			String[]tempIds=ids.split(",");
			for (int i = 0; i < tempIds.length; i++) {
				if (tempIds[i]!=null) {
					adminuserTaokePid=taokePidDao.findById(Integer.parseInt(tempIds[i]));
					adminuserPidDistribute=new CityAdminuserPidDistribute();
				}
				
				AdminUser adminUser=null;
				List<AdminUser> alist=adminUserDAO.findByCon(AdminUser.class, "id="+adminuserId);
				if (alist!=null&&alist.size()>0) {
					adminUser=alist.get(0);
				}
				if (adminuserTaokePid.getStatus()==0) {
					adminuserTaokePid.setStatus(1);
					adminuserPidDistribute.setAdminuserTaokePid(adminuserTaokePid);
					adminuserPidDistribute.setAdminUser(adminUser);
					adminuserPidDistribute.setStatus(0);
					adminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					adminuserPidDistribute.setIsValid(true);
					
				}
				taokePidDao.saveOrUpdate(adminuserTaokePid);
				cityPidDistributeDao.saveOrUpdate(adminuserPidDistribute);
			}
		}
	}
	
}
	

