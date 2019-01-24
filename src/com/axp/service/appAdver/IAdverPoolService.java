package com.axp.service.appAdver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface IAdverPoolService extends IBaseService{


	void playAdver(Integer id, Integer adver_id, HttpServletRequest request);

	String savePlayAdver(Integer id, Integer adver_id, Integer playtotal,
			Integer ishige, String starttime, Integer isplay,
			HttpServletRequest request, HttpServletResponse response);

	void poolList(HttpServletRequest request);

	void outtimeList(HttpServletRequest request);

	void delPool(Integer id, HttpServletRequest request);


}
