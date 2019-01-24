package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String name, String pwd, Boolean isvalid, Timestamp createtime, Integer score, String invitecode) {
		super(name, pwd, isvalid, createtime, score, invitecode);
	}

	/** full constructor */
	public Users(AdminUser adminUser, Seller parentSeller,
			Levels levels, String name, String phone, String pwd,
			Boolean isvalid, Boolean isVisitor, Timestamp createtime,
			Timestamp lasttime, String address, Integer score,
			String invitecode, Double savemoney, String realname,
			Timestamp birthday, Integer sex, Double money, String lat,
			String lng, Integer proxyzoneId, Integer proxyId, String mycode,
			Integer level, Boolean islogin, String channelid, String userid,
			String ccpsubaccountid, String ccpsubaccountpwd,
			String ccpvoipaccountid, String ccpvoipaccountpwd,
			String ccpcallsid, Double cashPoints, String headimage,
			String imgUrl, Integer visitorId, String sign, Integer version,
			Double redPaperSum, Set messagesesForFromUserId,
			Set goodsAdversettingses, Set userrecores, Set wxUserses,
			Set feeRecordsesForUserId, Set messagesesForUserId,
			Set scoreExchangeses, Set inviteRecordsesForUserId,
			Set voucherRecords, Set cashpointsRecords, Set proxyinfoses,
			Set extendResultses, Set applies, Set userProfitses, Set orderses,
			Set scorerecordses, Set inviteRecordsesForInviteUserId,
			Set userDrawCashRecords, Set feeRecordsesForOpUserId, Set plans,
			Set proxyuserses, Set userSettingses, Set userCashshopRecords,
			Set machines, Set sellers, Set userScoretypeses, Set promoters,
			OrderMessageList ordermessageLists, String openId,
			Set<SellerMallShoppingCar> sellerMallShoppingCar, Integer sellerId,
			Boolean isSeller,Integer jphScore){
		super(adminUser, levels, name, phone, pwd, isvalid, isVisitor, createtime, lasttime, address, score, invitecode, savemoney, realname, birthday, sex, money, lat, lng, proxyzoneId, proxyId, mycode, level,
				islogin, channelid, userid, ccpsubaccountid, ccpsubaccountpwd, ccpvoipaccountid, ccpvoipaccountpwd, ccpcallsid, cashPoints,sign,sign, level, sign, level, cashPoints, messagesesForFromUserId, goodsAdversettingses, userrecores,
				wxUserses, feeRecordsesForUserId, messagesesForUserId, scoreExchangeses, inviteRecordsesForUserId, voucherRecords, cashpointsRecords, proxyinfoses, extendResultses, applies,
				userProfitses, orderses, scorerecordses, inviteRecordsesForInviteUserId, userDrawCashRecords, feeRecordsesForOpUserId, plans, proxyuserses, userSettingses, userCashshopRecords,
				machines, sellers, userScoretypeses, userScoretypeses, ordermessageLists, sign, userScoretypeses, level, islogin,jphScore);
	}

}
