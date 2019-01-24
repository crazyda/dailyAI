package com.axp.dao;

import java.util.List;

import com.axp.model.MembersBonus;

public interface MembersBonusDAO extends IBaseDao<MembersBonus> {

	List<MembersBonus> getMembersBonusByVipType(Integer adminUserId,
			Integer membersTypeIncomeId);
}