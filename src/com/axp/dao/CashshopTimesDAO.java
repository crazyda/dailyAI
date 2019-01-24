package com.axp.dao;

import java.util.List;

import com.axp.model.CashshopTimes;

public interface CashshopTimesDAO extends IBaseDao<CashshopTimes> {

	List<CashshopTimes> findTimesOfHQ();

	List<CashshopTimes> searchCashshopTimesByIds(List<Integer> timesList);
}
