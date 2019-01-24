package com.axp.service.newRedPaper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.form.newRedPaper.NewRedPaperTotal;


public interface StatisticsService extends IBaseNewRedPaperService{
	public List<NewRedPaperTotal> newRedPaperCountList(String search_id,String search_name,String sellerId,String allianceId,String cityId,String centerId);

	public List<NewRedPaperTotal> newRedPaperCountList(HttpServletRequest request);
}
