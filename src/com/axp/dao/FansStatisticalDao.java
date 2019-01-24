package com.axp.dao;




import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.UserLoginRecord;
import com.axp.model.UsersOperationRecord;

public interface FansStatisticalDao extends IBaseDao<UsersOperationRecord> {
	List<UsersOperationRecord> getOperation(HttpServletRequest request,Integer start,Integer end);
	
	Integer getCount (HttpServletRequest request);
	
	
	List<UserLoginRecord> getRecordList(HttpServletRequest request,Integer start,Integer end);
	
	Integer getSum (HttpServletRequest request);
}