package com.axp.service.newRedPaper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminUser;
import com.axp.model.AdminuserRedpaper;
import com.axp.model.AdminuserRedpaperUsersReceive;
import com.axp.model.SellerMoneyRecord;
import com.axp.query.PageResult;
import com.axp.service.system.IBaseService;

public interface AdminuserRedpaperReceiveService extends IBaseService{
void getRedpaperDetailById(HttpServletRequest request,Integer redpId);
Integer countById(HttpServletRequest request, HttpServletResponse response,
		Integer redPaperId);
}
