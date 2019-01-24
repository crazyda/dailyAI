package com.axp.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RedpaperService extends IBaseService{

	void redpaperInfo(HttpServletRequest request, HttpServletResponse response);

	void getRedpaperDetail(HttpServletRequest request, Integer redpaperId);

}
