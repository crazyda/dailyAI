package com.axp.service.money.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.axp.model.Scorerecords;
import com.axp.query.PageResult;
import com.axp.service.money.FansGetScoreRecordService;
import com.axp.service.system.impl.BaseServiceImpl;
@Service
public class FansGetScoreRecordServiceImpl extends BaseServiceImpl implements FansGetScoreRecordService {

	@Override
	public PageResult<Scorerecords> getScoreChangeRecord(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return scoreRecordsDao.getScoreRecordsList(userId, currentPage, pageSize, request);
	}

	

}
