package com.axp.service.professional;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TaokeService extends IProfessionalService{
	void getTaokeInfo(HttpServletRequest request,HttpServletResponse response);
	void del(String ids);
	void add(HttpServletRequest request,Integer id);
	void save(Integer id,Integer zoneid,Timestamp createtime,String bak_uri,Integer centerid,HttpServletRequest request);
}
