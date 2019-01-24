package com.axp.service.cashShop.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdvertDaysChangeLogDAO;
import com.axp.dao.CoinDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ISecKillTimesDao;
import com.axp.dao.MembersBonusDAO;
import com.axp.dao.MembersTypeIncomeDAO;
import com.axp.dao.ProviderBonusDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.ShoptypesDAO;
import com.axp.dao.UploadFileDAO;
import com.axp.dao.UsersDAO;
import com.axp.dao.ZonesDAO;
import com.axp.service.system.IBaseService;

public class BaseServiceImpl implements IBaseService {
	@Autowired
	AdminUserDAO adminUserDAO; 
	@Autowired
	CoinDAO coinDAO;
	@Autowired
	DateBaseDAO dateBaseDao;
	@Autowired
	MembersTypeIncomeDAO membersTypeIncomeDAO;
	@Autowired
	MembersBonusDAO membersBonusDAO;
	@Autowired
	ProviderBonusDAO providerBonusDAO;
	@Autowired
	ProvinceEnumDAO provinceEnumDAO;
	@Autowired
	SellerDAO sellerDAO;
	@Autowired
	UploadFileDAO uploadFileDAO;
	@Autowired
	ZonesDAO zonesDAO;
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	ShoptypesDAO shoptypesDAO;
	@Autowired
	AdvertDaysChangeLogDAO advertDaysLogDAO;
	@Autowired
	ISecKillTimesDao cashshopTimesDao;
	
	
	

}
