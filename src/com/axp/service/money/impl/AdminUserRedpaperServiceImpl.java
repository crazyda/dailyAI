package com.axp.service.money.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminuserRedpaperDao;
import com.axp.model.AdminuserRedpaper;
import com.axp.query.PageResult;
import com.axp.service.money.AdminUserRedpaperService;
import com.axp.service.system.impl.BaseServiceImpl;

@Service
public class AdminUserRedpaperServiceImpl extends BaseServiceImpl implements AdminUserRedpaperService{

	@Autowired
	AdminuserRedpaperDao adminuserRedpaperDao;
	@Override
	public PageResult<AdminuserRedpaper> getRedpaperChangeRecord(Integer redId, Integer currentPage, Integer pageSize,
			HttpServletRequest request) {
		return adminuserRedpaperDao.getRedpaperChangeRecord(redId, currentPage, pageSize, request);
	}

}
