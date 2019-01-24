package com.axp.service.professional;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.service.system.IBaseService;

public interface MessageTypeService extends IBaseService{

	void list(HttpServletRequest request,HttpServletResponse response);

	void add(Integer id,HttpServletRequest request);

	void save(Integer id,Integer level,Integer isorder,String iconPic,HttpServletRequest request);
	
	void del(Integer id,HttpServletRequest request);


}
