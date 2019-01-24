package com.axp.dao;

import java.util.List;

import com.axp.model.CashshopTimes;

public interface ISecKillTimesDao extends IBaseDao<CashshopTimes> {

	List<CashshopTimes> getTimesList(Integer adminUserId);

	Integer getTimesCount(Integer adminUserId);}