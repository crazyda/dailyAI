package com.axp.service.professional.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserZonesDAO;
import com.axp.dao.TaokeDao;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserZoneidTaoke;
import com.axp.model.ProvinceEnum;
import com.axp.model.RePermissionRole;
import com.axp.model.Shoptypes;
import com.axp.service.professional.TaokeService;
import com.axp.service.professional.UserService;
import com.axp.service.system.AdminUserService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class TaokeServiceImpl extends ProfessionalServiceImpl implements TaokeService{

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private TaokeDao taokeDao;
	@Resource
	private AdminUserZonesDAO adminUserZonesDAO;
	@Resource
	private UserService userService;
	@Override
	public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
		return null;
	}

	@Override
	public void getTaokeInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String id=(String)request.getParameter("taoke_id");
		String search_name = (String)request.getParameter("search_name");
        StringBuffer param = new StringBuffer();
	    QueryModel model = new QueryModel();
		model.combPreEquals("id", id );
		model.combPreEquals("isvalid", true);
	    if(!StringUtils.isEmpty(id )){
	    	model.combPreEquals("id", Integer.parseInt(id ));
	    	param.append("&taoke_id="+id );
	    }
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("zoneid like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    List<ProvinceEnum> plist=dateBaseDao.findPageList(ProvinceEnum.class, model, 0, pageSize);
	    ProvinceEnum provinceEnum=null;
	    if (plist!=null&&plist.size()>0) {
			provinceEnum=plist.get(0);
		}
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(AdminuserZoneidTaoke.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<AdminuserZoneidTaoke> zonelist=dateBaseDao.findPageList(AdminuserZoneidTaoke.class, model, start, end);
    	request.setAttribute("provinceEnum", provinceEnum);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("zonelist", zonelist);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		
	}

	@Override
	public void del(String ids) {
		taokeDao.del(ids);
	}

	@Override
	public void add(HttpServletRequest request, Integer id) {
		ProvinceEnum provinceEnum=null;
		AdminUser adminUser =null;
		if (id!=null) {
			AdminuserZoneidTaoke zone=taokeDao.findById(id);
			request.setAttribute("AdminuserZoneidTaoke", zone);
		}
		AdminuserZoneidTaoke zone=null;
		if(id==null){
			zone =new AdminuserZoneidTaoke();
		}else{
			zone=taokeDao.findById(id);
		}
		Integer level =  (Integer) request.getSession().getAttribute("userLevel");
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		List<ProvinceEnum> zoneList=null;
		List<ProvinceEnum> distrtList=null;
		AdminUser au = adminUserDAO.findById(current_user_id);
		if (level >= 95) {
			// 获取所有省份
			zoneList = userService.getZone(1);
		} else if (level == 75) {
			distrtList = provinceEnumDAO.findByPropertyWithValid("provinceEnum.id", au.getProvinceEnum().getId());
			request.setAttribute("iscity", "yes");
		} else {
			zoneList = userService.getZoneById(au.getProvinceEnum().getId(),level);
		}
		
		List<AdminUser> centerlist =null;
		List<AdminUser> agencylist =null;
		if (au.getLevel()>=95) {
			centerlist=adminUserService.getLevel(85);
		}else if(au.getLevel()>=85){
			agencylist=adminUserDAO.findByPropertyWithValid("adminUser.id", au.getId());
		}
		request.setAttribute("centerlist", centerlist);
		request.setAttribute("agencylist", agencylist);
		request.setAttribute("adminUser", adminUser);
		
		request.setAttribute("zoneList", zoneList);
		request.setAttribute("distrtList", distrtList);
		request.setAttribute("zonetaoke", zone);
		request.setAttribute("provinceEnum", provinceEnum);
		
	}

	@Override
	public void save(Integer id, Integer zoneid, Timestamp createtime,
			String bak_uri,Integer centerid,HttpServletRequest request) {
		AdminuserZoneidTaoke zone=null;
		if (id!=null) {
			zone=taokeDao.findById(id);
		}else{
			zone=new AdminuserZoneidTaoke();
		}
		ProvinceEnum provinceEnum=provinceEnumDAO.findById(zoneid);
		AdminUser adminUser = adminUserDAO.findById(centerid);
		zone.setId(id);
		zone.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		zone.setBak_uri(bak_uri);
		zone.setZoneid(provinceEnum==null?0:provinceEnum.getId());
		zone.setProvinceEnum(provinceEnum);
		zone.setAdminuserId(adminUser==null?0:adminUser.getId());
		zone.setAdminUser(adminUser);
		zone.setIsValid(true);
		taokeDao.saveOrUpdate(zone);
	}



}
