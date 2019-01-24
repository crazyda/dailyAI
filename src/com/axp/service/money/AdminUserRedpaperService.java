package com.axp.service.money;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminuserRedpaper;
import com.axp.query.PageResult;

public interface AdminUserRedpaperService {

	PageResult<AdminuserRedpaper> getRedpaperChangeRecord(Integer redId, Integer currentPage, Integer pageSize,HttpServletRequest request);

}
