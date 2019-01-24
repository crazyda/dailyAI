package com.axp.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.axp.model.AdminUser;
import com.axp.model.TkldPid;

public interface TkldPidDao  extends IBaseDao<TkldPid>{
		
	
		/**
		 * 判断pid是否唯一
		 * @param pid
		 * @return
		 */
	 boolean checkPid(String pid);
	 
	 /**
	  * 根据level查询上级TkldPid  1 代理商 2事业合伙人 3 普通合伙人 
	  * @param level
	  * @return
	  */
	 List<TkldPid> findParentTkldPid(Integer level);
	 
	 
	 List<TkldPid>  findPidByCondition(DetachedCriteria criteria,int start,int end);
	 
	 Long findCountByCondition(DetachedCriteria criteria);
	 
}
