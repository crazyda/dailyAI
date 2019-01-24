package com.axp.service.taoke.impl;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Service;

import com.axp.dao.CityPidDistributeDao;
import com.axp.dao.TaokePidDao;
import com.axp.model.AdminuserTaokePid;
import com.axp.model.CityAdminuserPidDistribute;
import com.axp.model.PartnerAdminuserPidDistribute;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.TaokePidService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class TaokePidServiceImpl extends BaseServiceImpl implements TaokePidService {
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private TaokePidDao taokePidDao;
	@Resource
	private CityPidDistributeDao cityPidDistributeDao;
	@Override
	public void getPidList(HttpServletRequest request,
			HttpServletResponse response) {
		String search_name = (String)request.getParameter("search_name");
		StringBuffer param = new StringBuffer();
		QueryModel model=new QueryModel();
		model.combPreEquals("isValid", true);
		
		if (!StringUtil.isEmpty(search_name)) {
			model.combCondition("pidname like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
		}
		
		 String pagestr = request.getParameter("page");
	     PageInfo pageInfo = new PageInfo();
	     int count = dateBaseDao.findCount(AdminuserTaokePid.class, model);
	     Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
	     int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
	     int end = pageInfo.getPageSize();
	     pageInfo.setParam(param + "&page=");
	     List<AdminuserTaokePid> pidlist=dateBaseDao.findPageList(AdminuserTaokePid.class, model, start, end);
	     List<AdminuserTaokePid> list=new ArrayList<AdminuserTaokePid>();
	     for(AdminuserTaokePid adminuserTaokePid:pidlist){
	    	 model.clearQuery();
	    	 model.combPreEquals("isValid", true);
	    	 model.combPreEquals("pidId", adminuserTaokePid.getId());
	    	 List<CityAdminuserPidDistribute> clist=dateBaseDao.findPageList(CityAdminuserPidDistribute.class, model, 0, 10);
  			 if (clist!=null&&clist.size()>0) {
					CityAdminuserPidDistribute cityAdminuserPidDistribute=clist.get(0);
					adminuserTaokePid.setFengpei(cityAdminuserPidDistribute.getAdminUser().getUsername());
					model.clearQuery();
     			    model.combPreEquals("isValid", true);
	     			model.combPreEquals("pidId", cityAdminuserPidDistribute.getId());
	     			List<PartnerAdminuserPidDistribute> plist = dateBaseDao.findPageList(PartnerAdminuserPidDistribute.class, model, 0, 10);
	     			if (plist!=null && plist.size()>0) {
	     					PartnerAdminuserPidDistribute partnerAdminuserPidDistribute = plist.get(0);
	     					if (partnerAdminuserPidDistribute!=null) {
		     					adminuserTaokePid.setFengpeis(partnerAdminuserPidDistribute.getUsers().getName());
							}
	     				
					}
				}
  			list.add(adminuserTaokePid);
	     }
	     
	    
	     request.setAttribute("count", count);
	     request.setAttribute("list", list);
	     request.setAttribute("page", pagestr);
	     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	    
	}
	@Override
	public void del(String ids) {
		taokePidDao.del(ids);
	}
	@Override
	public void add(HttpServletRequest request, Integer id) {
		AdminuserTaokePid adminuserTaokePid=null;
		if (id!=null) {
			adminuserTaokePid = taokePidDao.findById(id);
			request.setAttribute("adminuserTaokePid", adminuserTaokePid);
		}
	}
	@Override
	public void save(Integer id,String pid, String pidname, Timestamp createtime,
			HttpServletRequest request, HttpServletResponse response,
			Integer status,String tkLoginLoginname,String tkLoginPassword,String tkLoginUsername) {
		AdminuserTaokePid adminuserTaokePid=null;
		if (id!=null) {
			adminuserTaokePid=taokePidDao.findById(id);
		}else{
			adminuserTaokePid=new AdminuserTaokePid();
		}
		
		adminuserTaokePid.setPid(pid.trim());
		adminuserTaokePid.setPidname(pidname);
		adminuserTaokePid.setStatus(status);
		adminuserTaokePid.setIsValid(true);
		adminuserTaokePid.setStatus(0);
		adminuserTaokePid.setCreatetime(new java.sql.Timestamp(java.lang.System.currentTimeMillis()) );
		adminuserTaokePid.setTkLoginLoginname(tkLoginLoginname);
		adminuserTaokePid.setTkLoginPassword(tkLoginPassword);
		adminuserTaokePid.setTkLoginUsername(tkLoginUsername);
		
		taokePidDao.saveOrUpdate(adminuserTaokePid);
	}

}