/**
 * 2018年9月5日
 * Administrator
 */
package com.axp.service.professional.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.axp.dao.DataBaseDao;
import com.axp.dao.ScoreMarkDAO;
import com.axp.model.ScoreMark;
import com.axp.service.professional.ScoreMarkService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;


public class ScoreMarkServiceImpl extends BaseServiceImpl implements ScoreMarkService {
	@Resource
	private ScoreMarkDAO scoreMarkDAO;
	
	@Override
	public void OverdueIntegral() {
		
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.orCondition("validityTime>"+createTime);
		List<ScoreMark> scoreMarks = dateBaseDao.findLists(ScoreMark.class, model);
		for(ScoreMark s : scoreMarks){
			String a = s.getRemark();
			s.setRemark(a.substring(0, a.indexOf("h")-1));
			s.setRefreshTime(createTime);
			scoreMarkDAO.saveOrUpdate(s);
		}
		
	}
	
	
}
