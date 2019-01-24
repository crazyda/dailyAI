package com.axp.service.cashShop;

import javax.servlet.http.HttpServletRequest;

import com.axp.service.system.IBaseService;

public interface ISecKillTimesService extends IBaseService{

	void list(HttpServletRequest request);

	void delete(Integer id, HttpServletRequest request);

	void add(HttpServletRequest request);

	void save(HttpServletRequest request);

	void update(Integer id, String endTime, String startTime,
			HttpServletRequest request);

	void edit(Integer id, HttpServletRequest request);

}
