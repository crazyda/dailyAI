package com.axp.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axp.dao.AccountManageDao;
import com.axp.dao.AccountReceiveDao;
import com.axp.model.AccountManage;
import com.axp.model.AccountReceive;
import com.axp.service.system.AccountService;
import com.axp.util.QueryModel;

@Service
public class AccountServiceImpl extends BaseServiceImpl implements AccountService{

	@Autowired
	AccountManageDao accountManageDao;
	@Autowired
	AccountReceiveDao accountReceiveDao;
	@Override
	public void accountManage(HttpServletRequest request, HttpServletResponse response) {
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		List<AccountReceive> list = dateBaseDao.findLists(AccountReceive.class, model);
		request.setAttribute("list", list);
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response, Integer id) {
		if (id!=null) {
			AccountManage accountManage = accountManageDao.findById(id);
			request.setAttribute("accounManage", accountManage);
			request.setAttribute("id", id);
		}
	}

	@Override
	public void save(HttpServletRequest request, HttpServletResponse response,String name) {
		accountManageDao.updateManage(request, response);
		accountReceiveDao.updateReceive(request);
		
		AccountReceive accountReceive = new AccountReceive();
		accountReceive.setIsValid(true);
		accountReceive.setAccountName(name);
		accountReceive.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		accountReceiveDao.save(accountReceive);
	}

	@Override
	public void saveAccount(HttpServletRequest request, HttpServletResponse response, Integer id, String firstselect,
			String secondselect, String appid, String secret, String mchid, String apikey,
			String aliappid,String priKey,String priKey2,String pubKey,String alisellerid,String aliapikey) {
		AccountManage accountManage = new AccountManage();
		AccountReceive accountReceive = accountReceiveDao.findById(id);
		if (firstselect.equals("1")) {
			accountManage.setAliAppId(aliappid);
			accountManage.setAliPrivateKey(priKey);
			accountManage.setAliPrivateKeyPkcs8(priKey2);
			accountManage.setAliPublicKey(pubKey);
			accountManage.setAliKey(aliapikey);
			accountManage.setAliSellerId(alisellerid);
			accountManage.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			accountManage.setIsValid(true);
			accountManage.setType(Integer.parseInt(secondselect));
			accountManage.setAccountReceive(accountReceive);
			accountManageDao.save(accountManage);
		}else if(firstselect.equals("2")){
			accountManage.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			accountManage.setIsValid(true);
			accountManage.setAppId(appid);
			accountManage.setAppSecret(secret);
			accountManage.setMchId(mchid);
			accountManage.setApiKey(apikey);
			accountManage.setType(Integer.parseInt(secondselect));
			accountManage.setAccountReceive(accountReceive);
			accountManageDao.save(accountManage);
		}
	}
	


}
