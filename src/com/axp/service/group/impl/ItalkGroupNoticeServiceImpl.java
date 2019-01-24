package com.axp.service.group.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


































import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.ItalkGroupDao;
import com.axp.dao.ItalkGroupMemberDao;
import com.axp.dao.ItalkGroupNoticeDao;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.ItalkGroup;
import com.axp.model.ItalkGroupMember;
import com.axp.model.ItalkGroupNotice;
import com.axp.model.Users;
import com.axp.service.group.ItalkGroupNoticeService;
import com.axp.service.group.ItalkGroupService;
import com.axp.service.group.RongCloudService;
import com.axp.service.invoke.impl.BaseServiceImpl;
import com.axp.util.StringUtil;
import com.rongcloud.models.CodeSuccessResult;


@Service
public class ItalkGroupNoticeServiceImpl extends BaseServiceImpl implements ItalkGroupNoticeService {
	
	
	
	@Override
	public void delete(ItalkGroupNotice italkGroupNotice) {
		italkGroupNoticeDao.delete(italkGroupNotice);
		
	}
	
	
	
	
	

}
