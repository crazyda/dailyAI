package com.axp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.axp.model.Adverpool;


public interface IAdverpoolDao extends IBaseDao<Adverpool>{
	
	
	public List<Adverpool> getHideAdverPool(Integer zonrId);
}
