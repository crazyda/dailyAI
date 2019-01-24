package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminuserWithdrawalsDataDao;
import com.axp.model.AdminuserWithdrawalsData;
import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;
import com.axp.service.money.WithdrawDatalogService;
import com.axp.service.system.AdminUserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;

@Service
public class WithdrawDatalogServiceImpl extends BaseServiceImpl implements WithdrawDatalogService{

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private AdminuserWithdrawalsDataDao adminuserWithdrawalsDataDao;
	@Override
	public PageResult<AdminuserWithdrawalsDataLog> getCheckDataList(
			Integer currentPage, Integer pageSize) {
		return adminuserWithdrawalsDataLogDao.getReviewDataList(currentPage, pageSize);
	}

	@Override
	public AdminuserWithdrawalsDataLog findById(Integer LogId) {
		return adminuserWithdrawalsDataLogDao.findById(LogId);
	}

	@Override
	public Map<String, Object> doReview(Map<String, Object> returnMap,
			Integer id,Integer status,HttpServletRequest request) throws Exception {
		AdminuserWithdrawalsDataLog log = adminuserWithdrawalsDataLogDao.findById(id);
        if (log == null) {
            throw new Exception("参数错误：t找不到id值为" + id + "的AdminuserWithdrawalsDataLog对象；");
        }
        log.setStatus(status);
        AdminuserWithdrawalsData adminuserWithdrawalsData=null;
        List <AdminuserWithdrawalsData > awd= 	adminuserWithdrawalsDataDao.findByPropertyWithValid("adminUser.id", log.getAdminUser().getId());
        if (awd!=null&&awd.size()>0) {
        	adminuserWithdrawalsData=awd.get(0);
        	 if (status == AdminuserWithdrawalsDataLog.pass) {
             	adminuserWithdrawalsData.setImage(log.getImage());
             	adminuserWithdrawalsData.setImage2(log.getImage2());
             	adminuserWithdrawalsData.setName(log.getName());
             	adminuserWithdrawalsData.setPhone(log.getPhone());
             	adminuserWithdrawalsData.setCode(log.getCode());
             	adminuserWithdrawalsData.setCretatetime(log.getCretatetime());
             	adminuserWithdrawalsDataDao.update(adminuserWithdrawalsData);
             } else if (status == AdminuserWithdrawalsDataLog.unPass) {
             	log.setStatus(status);
             }
		}else{
			adminuserWithdrawalsData=new AdminuserWithdrawalsData();
			if (status == AdminuserWithdrawalsDataLog.pass) {
				adminuserWithdrawalsData.setAdminUser(log.getAdminUser());
             	adminuserWithdrawalsData.setImage(log.getImage());
             	adminuserWithdrawalsData.setImage2(log.getImage2());
             	adminuserWithdrawalsData.setName(log.getName());
             	adminuserWithdrawalsData.setPhone(log.getPhone());
             	adminuserWithdrawalsData.setCode(log.getCode());
             	adminuserWithdrawalsData.setCretatetime(log.getCretatetime());
             	adminuserWithdrawalsData.setIsValid(true);
             	adminuserWithdrawalsDataDao.save(adminuserWithdrawalsData);
             } else if (status == AdminuserWithdrawalsDataLog.unPass) {
             	log.setStatus(status);
             }
		}

        returnMap.put("message", "审核操作完成；");
        return returnMap;
    
	}

	@Override
	public Map<String, Object> reviewPage(Map<String, Object> returnMap,
			Integer id, Integer status, HttpServletRequest request) {
		
		
		AdminuserWithdrawalsDataLog data = adminuserWithdrawalsDataLogDao.findById(id);
		request.setAttribute("log", data);
		
		List <AdminuserWithdrawalsData > awd= 	adminuserWithdrawalsDataDao.findByPropertyWithValid("adminUser.id", data.getAdminUser().getId());
		if(awd !=null && awd.size()>0){
			request.setAttribute("data", awd.get(0));
		}
		return returnMap;
	}

}
