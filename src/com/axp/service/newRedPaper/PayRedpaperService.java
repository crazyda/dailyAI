package com.axp.service.newRedPaper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminuserRedpaper;
import com.axp.service.system.IBaseService;

public interface PayRedpaperService extends IBaseService{
AdminuserRedpaper findById(Integer redpId);
void getlist(HttpServletRequest request,HttpServletResponse response);
void isPay(HttpServletRequest request,Integer redpaperId);
}
