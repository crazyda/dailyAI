package com.axp.service.invoke.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.FreeVoucherRecordDAO;
import com.axp.dao.ItalkGroupDao;
import com.axp.dao.ItalkGroupMemberDao;
import com.axp.dao.ItalkGroupNoticeDao;
import com.axp.dao.JphScorerecordsDAO;
import com.axp.dao.MembersDAO;
import com.axp.dao.UserAddressConfigDAO;
import com.axp.dao.UsersDAO;
import com.axp.service.group.ItalkGroupNoticeService;
import com.axp.service.group.RongCloudService;
import com.axp.service.invoke.IBaseService;

public class BaseServiceImpl implements IBaseService {
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	UserAddressConfigDAO userAddressConfigDAO; 
	@Autowired
	MembersDAO membersDAO; 
	@Autowired
	FreeVoucherRecordDAO freeVoucherRecordDAO;
	@Autowired
	public DateBaseDAO dateBaseDAO;
	
	@Autowired
	public AdminUserDAO adminUserDao;
	@Autowired
	public ItalkGroupDao italkGroupDao;
	@Autowired
	public ItalkGroupNoticeDao italkGroupNoticeDao;
	@Autowired
	public ItalkGroupMemberDao italkGroupMemberDao;
	@Autowired
	public RongCloudService rongCloudService;
	@Autowired
	public JphScorerecordsDAO jphScorerecordsDao;
	
	
}
