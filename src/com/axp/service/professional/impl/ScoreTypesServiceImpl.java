/**
 * 2018年9月5日
 * Administrator
 */
package com.axp.service.professional.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axp.model.Scoretypes;
import com.axp.service.professional.ScoreTypesService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.QueryModel;

@Service("ScoreTypesService")
public class ScoreTypesServiceImpl extends BaseServiceImpl implements ScoreTypesService {

	@Override
	public List<Scoretypes> getActivity() {
		QueryModel model = new QueryModel();
		model.clearQuery();
		model.combPreEquals("isValid", true);
		model.combPreEquals("type", 1);
		List<Scoretypes> getActivity = dateBaseDao.findLists(Scoretypes.class, model);
		return getActivity;
	}
	
	
	
}
