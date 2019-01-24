package com.axp.service.taoke.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.CityPidDistributeDao;
import com.axp.dao.PartnerPidDistributeDao;
import com.axp.model.AdminUser;
import com.axp.model.CityAdminuserPidDistribute;
import com.axp.model.PartnerAdminuserPidDistribute;
import com.axp.model.Users;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.PartnerPidDistributeService;
import com.axp.util.QueryModel;

@Service
public class PartnerPidDistributeServiceImpl extends BaseServiceImpl implements PartnerPidDistributeService {
	@Resource
	private CityPidDistributeDao cityPidDistributeDao;
	@Resource
	private AdminUserDAO adminUserDAO;
	@Resource
	private PartnerPidDistributeDao partnerPidDistributeDao;
	@Override
	public void isDistribute(Integer ids, HttpServletRequest request,
			HttpServletResponse response) {
		CityAdminuserPidDistribute cityAdminuserPidDistribute=null;
		if (ids!=null) {
			cityAdminuserPidDistribute=cityPidDistributeDao.findById(ids);
		}
		request.setAttribute("cityAdminuserPidDistribute", cityAdminuserPidDistribute);
		
	}
	
	@Override
	public void ofDistribute(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		CityAdminuserPidDistribute cityAdminuserPidDistribute=null;
		PartnerAdminuserPidDistribute partnerAdminuserPidDistribute=null;
		if (id!=null) {
			cityAdminuserPidDistribute=cityPidDistributeDao.findById(id);
			QueryModel model=new QueryModel();
			model.combPreEquals("isValid", true);
			model.combPreEquals("pidId", cityAdminuserPidDistribute.getId());
			List<PartnerAdminuserPidDistribute> plist=dateBaseDao.findPageList(PartnerAdminuserPidDistribute.class, model, 0, 10);
			if (plist!=null&& plist.size()>0) {
				partnerAdminuserPidDistribute=plist.get(0);
			}
		}
		if (cityAdminuserPidDistribute.getStatus()==1) {
			cityAdminuserPidDistribute.setStatus(0);
			cityAdminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			partnerAdminuserPidDistribute.setIsValid(false);
			partnerAdminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			partnerAdminuserPidDistribute.setUsername("");
		}
		cityPidDistributeDao.saveOrUpdate(cityAdminuserPidDistribute);
		partnerPidDistributeDao.saveOrUpdate(partnerAdminuserPidDistribute);
	}
	@Override
	public void saveDistribute(Integer id, String pidId,String username,
			Integer adminuserId, Timestamp createtime, String name,
			HttpServletRequest request, HttpServletResponse response) {
		CityAdminuserPidDistribute adminuserPidDistribute=null;
		PartnerAdminuserPidDistribute partnerAdminuserPidDistribute=null;
		if (id!=null) {
			adminuserPidDistribute=cityPidDistributeDao.findById(id);
			partnerAdminuserPidDistribute=new PartnerAdminuserPidDistribute();
		}
		QueryModel queryModel=new QueryModel();
		
		queryModel.combPreEquals("isvalid", true);
		queryModel.combPreEquals("id", adminuserPidDistribute.getAdminUser().getId());
		AdminUser adminUser=(AdminUser) dateBaseDao.findOne(AdminUser.class, queryModel);
		queryModel.clearQuery();
		queryModel.combPreEquals("isvalid", true);
		queryModel.combPreEquals("name", name);
		Users users=(Users) dateBaseDao.findOne(Users.class, queryModel);
		if (adminuserPidDistribute.getStatus()==0 && users!=null) {
			adminuserPidDistribute.setStatus(1);
			partnerAdminuserPidDistribute.setCityAdminuserPidDistribute(adminuserPidDistribute);
			partnerAdminuserPidDistribute.setAdminUser(adminUser);
			partnerAdminuserPidDistribute.setIsValid(true);
			partnerAdminuserPidDistribute.setUsers(users);
			partnerAdminuserPidDistribute.setUsername(username);
			partnerAdminuserPidDistribute.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			cityPidDistributeDao.saveOrUpdate(adminuserPidDistribute);
			partnerPidDistributeDao.saveOrUpdate(partnerAdminuserPidDistribute);
		}
		
	}
	

	
}