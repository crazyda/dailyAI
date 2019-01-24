package com.axp.service.appAdver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface IAdverService extends IBaseService{

	void getPageList(HttpServletRequest request, HttpServletResponse response);

	void add(HttpServletRequest request, HttpServletResponse response);

	void save(Integer id, Integer type, String name, String description,
			Integer website_id, HttpServletRequest request,
			HttpServletResponse response);

	void del(String ids);

	void addAdverImg(Integer id, HttpServletRequest request);

	void saveAdverImg(Integer id, Integer playtotal, String adver_imageurls,
			String adver_imageurls_small, String adver_imgurl_size,
			HttpServletRequest request);

	void checkAdverList(HttpServletRequest request,Integer adminUserId);

	void checkAdver(Integer id, HttpServletRequest request);

	void saveCheck(Integer id, Integer adver_status, String checkstr,
			HttpServletRequest request);

}
