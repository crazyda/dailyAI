package com.axp.dao;


import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminuserRedpaper;
import com.axp.query.PageResult;


public interface AdminuserRedpaperDao extends IBaseDao<AdminuserRedpaper> {
	PageResult<AdminuserRedpaper> getRedpaperChangeRecord(Integer redId, Integer currentPage, Integer pageSize,HttpServletRequest request);
}