package com.axp.service.money;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.Scorerecords;
import com.axp.query.PageResult;

public interface FansGetScoreRecordService {
	/**
	 * da  粉丝积分列表
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<Scorerecords> getScoreChangeRecord(Integer userId, Integer currentPage, Integer pageSize,HttpServletRequest request);
}
