package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alipay.api.domain.Data;
import com.axp.dao.AdminuserWithdrawalsDataDao;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.model.MessageType;
import com.axp.model.Seller;
import com.axp.model.SystemMessageList;
import com.axp.model.UserSystemMessage;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.service.money.SellerWithdrawService;
import com.axp.service.money.WithdrawReviewService;
import com.axp.service.system.UserSystemMessageService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.ExcelUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.UrlUtil;
import com.axp.util.Utility;
import com.push.ImpAppInformation;
import com.yilian.service.util.Base64;
import com.yilian.service.util.Util;
import com.yilian.service.versionSMS.MsgBean;
import com.yilian.service.versionSMS.MsgBody;
import com.yilian.service.versionSMS.PayMen;

@Service
public class SellerWithdrawServiceImpl extends BaseServiceImpl implements SellerWithdrawService{

	@Resource
	UserSystemMessageService userSystemMessageService;
	protected UrlUtil urlUtil;
	@Override
	public PageResult<AdminuserWithdrawals> payList(Integer currentPage,
			Integer pageSize, Integer state,Integer timed) {
		PageResult<AdminuserWithdrawals> checkList=null;
		if(timed==-1){
			this.payQuery(sellerWithdrawalsDao.getSellerPayListByStatus());
		}else if(timed==1){
			 checkList = sellerWithdrawalsDao.getCheckList(currentPage, pageSize,state);
			 payQuery(checkList.getResult());
		}else{
			checkList = sellerWithdrawalsDao.getCheckList(currentPage, pageSize,state);
		}
		return checkList;
	}
	
	@Override
	public AdminuserWithdrawals findById(Integer Id) {
		return sellerWithdrawalsDao.findById(Id);
	}

	@Override
	public Map<String, Object> doCheck(Integer Id, Integer pass, String remark,
			Map<String, Object> returnMap) {
		AdminuserWithdrawals adminuserWithdrawals = sellerWithdrawalsDao.findById(Id);
	        if (adminuserWithdrawals == null) {
	            returnMap.put("message", "没有id值为" + Id + "的对象；");
	        }
	        /**
	         * 审核通过：
	         * 1，更改提现记录表中的数据状态为审核通过；
	         */
	        if (pass == 10) {
	            //更改商家提现记录的状态；
	        	adminuserWithdrawals.setState(AdminuserWithdrawals.shen_he_tong_guo);
	        	//====================lbr==========================
	        	//urlUtil.sendM(adminuserWithdrawals.getAdminUser().getPhone(), "您好,"+adminuserWithdrawals.getAdminUser().getUsername()+"成功申请提现，金额为"+adminuserWithdrawals.getMoney()+"。提现人：姓名"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getAdminUser().getUsername()+"，"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankName()+"，账号："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo()+"，联系号码："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getAdminUser().getPhone()+"，请注意确认。");
	            //===================================================
	        }
	        adminuserWithdrawals.setRemark(remark);
	        sellerWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
	        returnMap.put("message", "审核成功；");
	        return returnMap;
	}

	/*
	 * 对商家提现进行是否支付操作
	 * */
	@Override
	public Map<String, Object> isPay(Integer Id, Integer pass, String remark,Map<String, Object> returnMap)  {
		
			
		
		AdminuserWithdrawals adminuserWithdrawals=sellerWithdrawalsDao.findById(Id);
		returnMap.put("status", 1);
		 returnMap.put("message", "审核成功");
		if (adminuserWithdrawals==null) {
			returnMap.put("status", -1);
			 returnMap.put("message", "没有id值为" + Id + "的对象；");
		}
		Seller seller =null;
		Users users =null;
		AdminUser adminUser=adminuserWithdrawals.getAdminUser();
		List<Seller> slist = sellerDAO.getSellerListByAdminId(adminUser.getId());
		if(slist!=null && slist.size()>0){
			seller= slist.get(0);
		}
		
		if(seller!=null){
			users = seller.getUsers();
		}
		
		/*
		 * 驳回提现
		 * 1，需要将商家已经扣除的钱补上；
         * 2，更改商家提现记录的状态为审核不通过；
         * 3，将尚未确定的AdminuserWithdrawals表中的记录删除掉；
		 * */
		if (pass==null || pass==0) {
			
			//驳回支付之后，要返回商家扣除的提现金额以及扣除的手续费
			
			Double beforeMoney=adminUser.getMoney();//设置用户提现时的金额
			Double money=adminuserWithdrawals.getMoney();//提现金额
			Double counterFee=adminuserWithdrawals.getCounterFee()==null?0:adminuserWithdrawals.getCounterFee();//获取当笔提现的手续费
			
				Double countMoney=CalcUtil.add(money, counterFee);//提现金额和手续费总和
				Double totalMoney=CalcUtil.add(adminUser.getMoney(), countMoney);//当前用户账户余额加上提现金额和手续费总和
				adminUser.setMoney(totalMoney);//保存用户提现退回之后的金额
			
				adminUserDAO.saveOrUpdate(adminUser);
			
			//删除adminuserWithdrawals表中的记录,并且在AdminuserCashpointRecord表中加入相关记录
			
				adminuserWithdrawals.setState(AdminuserWithdrawals.shen_he_bu_tong_guo);//修改adminuserWithdrawals表中的状态
				adminuserWithdrawals.setIsValid(false);
				adminuserWithdrawals.setRemark(remark);
				adminuserWithdrawals.setChecktime(new Timestamp(new Date().getTime()));
				adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
				
				AdminuserCashpointRecord adminuserCashpointRecord=new AdminuserCashpointRecord();
				adminuserCashpointRecord.setAdminUser(adminUser);
				adminuserCashpointRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
				adminuserCashpointRecord.setIsValid(true);
				adminuserCashpointRecord.setAfterpoint(adminUser.getMoney());
				adminuserCashpointRecord.setBeforepoint(beforeMoney);
				adminuserCashpointRecord.setRemark(remark+";提现审核不通过退回："+adminuserWithdrawals.getMoney());
				adminuserCashpointRecord.setCashpoint(money);
				adminuserCashpointRecord.setType(1);
				adminuserCashpointRecord.setIsDeposit(6);
				adminuserCashpointRecordDAO.save(adminuserCashpointRecord);
				
				AdminuserCashpointRecord adminuserCashpointRecord2=new AdminuserCashpointRecord();
				adminuserCashpointRecord2.setAdminUser(adminUser);
				adminuserCashpointRecord2.setCreateTime(new Timestamp(System.currentTimeMillis()));
				adminuserCashpointRecord2.setIsValid(true);
				adminuserCashpointRecord2.setAfterpoint(adminUser.getMoney());
				adminuserCashpointRecord2.setBeforepoint(beforeMoney);
				adminuserCashpointRecord2.setRemark(remark+"提现审核不通过退回手续费："+counterFee);
				adminuserCashpointRecord2.setCashpoint(counterFee);
				adminuserCashpointRecord2.setType(1);
				adminuserCashpointRecord2.setIsDeposit(6);
				adminuserCashpointRecordDAO.save(adminuserCashpointRecord2);
				
				
			/*	if (users!=null) {
					List<Users> ulist = new ArrayList<Users>();
					ulist.add(users);
					userSystemMessageService.saveMessage("商家："+adminUser.getLoginname()+"提现被驳回！", StringUtil.MESSAGE_TIXIAN, "提现支付", ulist, "", money,1);
				}*/
			
		}
		/*
		 * 确认支付
		 * */
		else if(pass==1){
			//更改adminuserWithdrawals表中的对应数据状态；
			if (adminuserWithdrawals!=null) {
				adminuserWithdrawals.setState(AdminuserWithdrawals.shen_he_tong_guo);
				adminuserWithdrawals.setRemark(remark);
				adminuserWithdrawals.setChecktime(new Timestamp(System.currentTimeMillis()));
				adminuserWithdrawals.setPayName(adminuserWithdrawals.getAdminuserWithdrawalsData().getName());
				adminuserWithdrawals.setPayCode(adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo());
				adminuserWithdrawals.setPayAddress("银行："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankName()+";地址："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankAddress());
				adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
/*
				 int length=  adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo().length();
				 users = (Users) adminUser.getUserses();

				if (users!=null) {
					List<Users> ulist = new ArrayList<Users>();
					ulist.add(users);
					userSystemMessageService.saveMessage("商家："+adminUser.getLoginname()+"提现已完成支付！", StringUtil.MESSAGE_TIXIAN, "提现支付", ulist, "", adminuserWithdrawals.getMoney(),1);
					//==============lbr===========
					SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); 					
					urlUtil.sendM(adminUser.getPhone(), "您好，您"+format.format(adminuserWithdrawals.getChecktime())+"提现的金额"+adminuserWithdrawals.getMoney()+"已经成功到您"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankName()+"****************"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo().substring(0, length-2)+"的银 行卡上，请留意查收。");
				    //==========================
				}*/
			}
		}
		

			
			// TODO: handle exception
		return returnMap;
	}

	@Override
	public PageResult<AdminuserWithdrawals> getSellerPayList(
			Integer currentPage, Integer pageSize) {
		
		
		return  sellerWithdrawalsDao.getSellerPayList(currentPage, pageSize);
	}

	
	@Override
	public Map<String, Object> sellerArtificialPay(String ids) {
		Map<String, Object> ret=new HashMap<String, Object>();
		try {
			ret.put("message", "操作成功");
			ret.put("status", 1);
			
			  AdminuserWithdrawals withdrawals= adminuserWithdrawalsDao.findByIdValid(Integer.parseInt(ids));
			
			if(withdrawals.getState()!=AdminuserWithdrawals.shen_he_tong_guo){
				ret.put("message", "该订单并未审核通过");
				ret.put("status", -2);
			}else if(withdrawals.getState()==AdminuserWithdrawals.yi_zhi_fu||withdrawals.getState()==AdminuserWithdrawals.zhi_fu_cheng_gong){
				ret.put("message", "请勿重复支付");
				ret.put("status", -3);
			}else{
				withdrawals.setState(AdminuserWithdrawals.yi_zhi_fu);
				withdrawals.setMessage("人工手动支付");
				withdrawals.setYiLianPayTime(new Timestamp(System.currentTimeMillis()));
				adminuserWithdrawalsDao.merge(withdrawals);
			}
			
		} catch (Exception e) {
			ret.put("status", -1);
			ret.put("message", "操作失败");
			if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		return ret;
	}
	
	
	
	
	
	@Override
	public Map<String, Object> sellerPay(String ids) {
		 Map<String, Object> ret=new HashMap<String, Object>();
		try {
			 QueryModel model=new QueryModel();
			 model.combInStr("id",ids);
			 List<AdminuserWithdrawals> list = dateBaseDao.findLists(AdminuserWithdrawals.class, model);
			 //支付
			 
			 double sum=0d;
			 for (int i = 0; i < list.size(); i++) {
				sum+=list.get(i).getMoney(); 
			}
			 
			 MsgBean userMoney = PayMen.getUserMoney();
			 double amount=userMoney.getAMOUNT()==null?0:Double.parseDouble(userMoney.getAMOUNT());
			 if(sum>amount){
				 ret.put("status", -1);
				 ret.put("message", "账户余额不足");
				 return ret;
			 }
			 
			 ret=pay(list);
			 for (AdminuserWithdrawals record : list) {
				 if(AdminuserWithdrawals.yi_zhi_fu.equals(record.getState())){
				 record.setChecktime(new Timestamp(System.currentTimeMillis()));
				 adminuserWithdrawalsDao.update(record);
				 }
			   }
			} catch (Exception e) {
				e.printStackTrace();
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return ret;
	}


	@Override
	public  Map<String, Object> pay(List<AdminuserWithdrawals> recordList){
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		map.put("message", "代付成功");
		try {
		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.1");
		req_bean.setMSG_TYPE("100001");
		req_bean.setUSER_NAME(PayMen.userName);//系统后台登录名
		for (AdminuserWithdrawals record : recordList) {
			String batchNo="AXP"+new Date().getTime()+new String(Base64.decode(Util.generateKey(99999,5)));
			req_bean.setBATCH_NO(batchNo);//每笔订单不可重复，建议：公司简称缩写+yymmdd+流水号
			MsgBody body = new MsgBody();
			body.setSN(record.getAdminUser().getId()+String.valueOf((int)(Math.random()*6*100000)));//流水号，同一批次不重复即可
			body.setACC_NO(record.getPayCode().trim());
			body.setACC_NAME(record.getPayName().trim());
			body.setAMOUNT(record.getMoney().toString()); 
			record.setBatchNo(batchNo); 
			req_bean.getBODYS().add(body);
			String res = PayMen.sendAndRead(PayMen.signANDencrypt(req_bean));
			MsgBean res_bean = PayMen.decryptANDverify(res);
			if("0000".equals(res_bean.getTRANS_STATE())&&("0000".equals(res_bean.getPAY_STAT())||"00A4".equals(res_bean.getPAY_STAT()))) {
				System.out.println("请求成功");
				System.out.println(res_bean.toXml());
				record.setState(AdminuserWithdrawals.zhi_fu_cheng_gong);
				record.setReplyStatus(res_bean.getPAY_STAT());
				record.setMessage(res_bean.getREMARK());
			}
			else{
				record.setReplyStatus(res_bean.getPAY_STAT());
				record.setMessage(res_bean.getREMARK());
				record.setState(AdminuserWithdrawals.zhi_fu_shi_bai);
				System.out.println(res_bean.toXml());
			}
			adminuserWithdrawalsDao.update(record);
			req_bean.getBODYS().clear();
		}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status",- 1);
			map.put("message", "交易失败");
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
}

	
		@Override
		public  void payQuery(List<AdminuserWithdrawals> recordList) {
			 StringBuffer sb=new StringBuffer();
			MsgBean req_bean = new MsgBean();
			req_bean.setVERSION("2.1");
			req_bean.setMSG_TYPE("100002");
			req_bean.setUSER_NAME(PayMen.userName);//系统后台登录名
			ImpAppInformation push = new ImpAppInformation();
			 String title="提现到账提醒";
				SystemMessageList systemMessageList = new SystemMessageList();
				UserSystemMessage userMessage = new UserSystemMessage();
				MessageType messageType = messageTypeDao.findById(6);
				systemMessageList.setMessageType(messageType);
				systemMessageList.setIsValid(true);
				systemMessageList.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
				systemMessageList.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				systemMessageList.setMoneyState(1);
				userMessage.setMessageType(messageType);
				userMessage.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				userMessage.setIsRead(0);
				userMessage.setSystemMessageList(systemMessageList);
				userMessage.setIsValid(true);
			for (AdminuserWithdrawals record : recordList) {
				if(StringUtils.isNotBlank(record.getBatchNo())&&record.getState()==AdminuserWithdrawals.zhi_fu_cheng_gong){
					req_bean.setBATCH_NO(record.getBatchNo());//同代付交易请求批次号
					String res = PayMen.sendAndRead(PayMen.signANDencrypt(req_bean));
					
					MsgBean res_bean = PayMen.decryptANDverify(res);
					//成功 1 改状态  2存返回消息  3 发推送提醒
					if("0000".equals(res_bean.getTRANS_STATE())) {
						if("0000".equals(res_bean.getPAY_STAT())){
							System.out.println("提现成功");
							record.setState(AdminuserWithdrawals.yi_zhi_fu);
							sb.append("您好,").append(record.getAdminUser().getUsername()).
							append(",提现已到账，金额为").append(record.getMoney()).append("。提现人：姓名").
							append(record.getPayName()).append(",手机:").append(record.getAdminuserWithdrawalsData().getPhone());
							 record.setMessage(res_bean.getREMARK());
							 record.setReplyStatus(res_bean.getPAY_STAT());
							 record.setYiLianPayTime(res_bean.getYiLianPayTime());
							 systemMessageList.setContent(sb.toString());
							 systemMessageList.setTitle(title);
							 systemMessageList.setAdminUser(record.getAdminUser());
							 systemMessageList.setMoney(record.getMoney());
							 systemMessageListDao.save(systemMessageList);
							 userMessage.setAdminUser(record.getAdminUser());
							 userSystemMessageDao.save(userMessage);
							 if(record.getAdminUser().getChannelid()!=null&&record.getAdminUser().getChannelid().length()==32){
							 // push.pushMessageForUsers(record.getAdminUser().getId().toString(), record.getAdminUser().getChannelid(), title, sb.toString(),systemMessageList.getId().toString() , null, "1", "商家消息", record.getAdminUser().getDevicetoken());
							  push.pushMessageToAdminUser(record.getAdminUser().getId().toString(),record.getAdminUser().getChannelid(), title, sb.toString(), systemMessageList.getId().toString(), "1", "商家消息" ,"提现到账提醒");
							 }
							 sb.setLength(0);
						}else if("00A4".equals(res_bean.getPAY_STAT())){
							System.out.println("订单中间状态,请再次发起请求AdminuserWithdrawals=======id="+record.getId());
							record.setReplyStatus(res_bean.getPAY_STAT());
							record.setMessage(res_bean.getREMARK());
						}else{
							System.out.println("提现失败");
							record.setReplyStatus(res_bean.getPAY_STAT());
							record.setState(AdminuserWithdrawals.zhi_fu_shi_bai);
							record.setMessage(res_bean.getREMARK());
						}
						
						adminuserWithdrawalsDao.update(record); //修改状态
					}
					
					System.out.println(res_bean.toXml());
			 }
			}
		}

		@Override
		public Map<String, Object> rejectSellerPay(int id) {
			Map<String, Object> returnMap=new HashMap<String, Object>();
			try {
			AdminuserWithdrawals adminuserWithdrawals=adminuserWithdrawalsDao.findByIdValid(id);
			List<AdminuserCashpointRecord> cashpointRecordList = adminuserCashpointRecordDAO.findByProperty("tradeNo",String.valueOf(id));
			if(adminuserWithdrawals!=null){
				returnMap.put("status", 1);
			 returnMap.put("message", "操作成功");
			}
			if (adminuserWithdrawals==null) {
				returnMap.put("status", -1);
				 returnMap.put("message", "没有id值为" + id + "的对象；");
				 return returnMap;
			}
			if(cashpointRecordList.size()>0){
				returnMap.put("status", -1);
				 returnMap.put("message", "该订单已退过款:单号" + id + "；");
				 return returnMap;
			}
			AdminUser adminUser=adminuserWithdrawals.getAdminUser();
			/*
			 * 驳回提现
			 * 1，需要将商家已经扣除的钱补上；
	         * 2，更改商家提现记录的状态为审核不通过；
	         * 3，将尚未确定的AdminuserWithdrawals表中的记录删除掉；
			 * */
				
				//驳回支付之后，要返回商家扣除的提现金额以及扣除的手续费
				
					Double beforeMoney=adminUser.getMoney();//设置用户提现时的金额
					Double money=adminuserWithdrawals.getMoney();//提现金额
					Double counterFee=adminuserWithdrawals.getCounterFee()==null?0:adminuserWithdrawals.getCounterFee();//获取当笔提现的手续费
				
					Double countMoney=CalcUtil.add(money, counterFee);//提现金额和手续费总和
					Double totalMoney=CalcUtil.add(adminUser.getMoney(), countMoney);//当前用户账户余额加上提现金额和手续费总和
					adminUser.setMoney(totalMoney);//保存用户提现退回之后的金额
				
					adminUserDAO.saveOrUpdate(adminUser);
				
				//删除adminuserWithdrawals表中的记录,并且在AdminuserCashpointRecord表中加入相关记录
				
					adminuserWithdrawals.setState(AdminuserWithdrawals.zhi_fu_shi_bai);//修改adminuserWithdrawals表中的状态
					adminuserWithdrawals.setIsValid(false);
					adminuserWithdrawals.setChecktime(new Timestamp(new Date().getTime()));
					adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
					
					AdminuserCashpointRecord adminuserCashpointRecord=new AdminuserCashpointRecord();
					adminuserCashpointRecord.setAdminUser(adminUser);
					adminuserCashpointRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
					adminuserCashpointRecord.setIsValid(true);
					adminuserCashpointRecord.setAfterpoint(adminUser.getMoney());
					adminuserCashpointRecord.setBeforepoint(beforeMoney);
					adminuserCashpointRecord.setRemark("信息错误银行驳回:："+adminuserWithdrawals.getMoney());
					adminuserCashpointRecord.setCashpoint(money);
					adminuserCashpointRecord.setType(1);
					adminuserCashpointRecord.setTradeNo(adminuserWithdrawals.getId().toString());
					adminuserCashpointRecordDAO.save(adminuserCashpointRecord);
					
					AdminuserCashpointRecord adminuserCashpointRecord2=new AdminuserCashpointRecord();
					adminuserCashpointRecord2.setAdminUser(adminUser);
					adminuserCashpointRecord2.setCreateTime(new Timestamp(System.currentTimeMillis()));
					adminuserCashpointRecord2.setIsValid(true);
					adminuserCashpointRecord2.setAfterpoint(adminUser.getMoney());
					adminuserCashpointRecord2.setBeforepoint(beforeMoney);
					adminuserCashpointRecord2.setRemark("提现银行审核不通过退回手续费："+counterFee);
					adminuserCashpointRecord2.setCashpoint(counterFee);
					adminuserCashpointRecord2.setType(1);
					adminuserCashpointRecord2.setTradeNo(adminuserWithdrawals.getId().toString());
					adminuserCashpointRecordDAO.save(adminuserCashpointRecord2);
			} catch (Exception e) {
			  	e.printStackTrace();
				returnMap.put("status", -1);
				returnMap.put("message",e.getMessage());
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
					return returnMap;
		}

		@Override
		public PageResult<AdminuserWithdrawals> getCheckListByStatus(
				Integer currentPage, Integer pageSize, Integer state) {
			
			return sellerWithdrawalsDao.getCheckListByStatus(currentPage, pageSize, state);
		}

		@Override
		public void getWithdrawalsList(HttpServletRequest request, HttpServletResponse response, String sTM,
				String eTM, String userId, String state,String type,String btn) {
			String pagestr = request.getParameter("page");
			PageInfo pageInfo = new PageInfo();
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNotBlank(sTM))sb.append("&sTM=").append(sTM).append("00:00:00");
		    if(StringUtils.isNotBlank(eTM))sb.append("&eTM=").append(eTM).append("23:59:59");
		    if(StringUtils.isNotBlank(userId))sb.append("&userId=").append(userId);
		    int count = 0;
		    if (type!=null) {
				count = sellerWithdrawalsDao.getSum(request); 
			}
		    String str="type="+type;
		    String str1 ="&btn=" +btn;
		    String str2="&sTM=" +sTM;
		    String str3 = "&eTM=" +eTM;
		    String str4 = "&userId=" +userId;
		    pageInfo.setParam(str+str1+str2+str3+str4+"&page=");
		    Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		    int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		    int end = pageInfo.getPageSize();
		    if (StringUtils.isNotBlank(type) || sb.length()>0) {
		    	//excel文件的表头
		    	List<String> titleList = new ArrayList<String>();
		    	titleList.add("序号");
		        titleList.add("账号");
		        titleList.add("提现金额");
		        titleList.add("手续费");
		        titleList.add("提现时间");
		        titleList.add("银行户名");
		        titleList.add("手机号");
		        titleList.add("银行账户");
		        titleList.add("银行地址");
		        titleList.add("批次号");
		        titleList.add("审核时间");
		        titleList.add("支付时间");
		        titleList.add("商家名");
		        titleList.add("审核说明");
		        titleList.add("审核状态");
		        //excel 文件的数据内容
		        List<Object[]> value = new ArrayList<Object[]>();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		    	if (StringUtils.isNotBlank(type) && type.equals("1")) {
					List<AdminuserWithdrawals> adminList = sellerWithdrawalsDao.getSellerWithdrawalList(request, start, end);
					if (adminList.size()>0) {
						request.setAttribute("list", adminList);
						
						if (btn.equals("导出Excel") || btn!=null ) {
							List<AdminuserWithdrawals> adminLists = sellerWithdrawalsDao.getSellerWithdrawalList(request, 0, 0);
							for (AdminuserWithdrawals match : adminLists){
					            String[] temp = new String[20];
					            temp[0] = String.valueOf(match.getId());
					            temp[1] = match.getAdminUser().getLoginname();
					            temp[2] = String.valueOf(match.getMoney());
					            temp[3] = String.valueOf(match.getCounterFee());
					            if (match.getCreatetime()!=null) {
					            	temp[4] = sdf.format(match.getCreatetime());
								}else{
									temp[4] = "无";
								}
					            
					            temp[5] = match.getPayName();
					            temp[6] = match.getAdminUser().getPhone();
					            temp[7] = match.getPayCode();
					            temp[8] = match.getAdminuserWithdrawalsBank().getBankAddress();
					            temp[9] =match.getBatchNo();
					            if (match.getChecktime()!=null) {
					            	temp[10] =sdf.format(match.getChecktime());
								}else{
									temp[10] = "无";
								}
					            if (match.getYiLianPayTime()!=null) {
					            	temp[11] =sdf.format(match.getYiLianPayTime());
								}else{
									temp[11] ="无";
								}
					            
					            temp[12] = match.getAdminUser().getProviderSeller().getName();
					            temp[13] = match.getRemark();
					            temp[14] ="已支付";
					            value.add(temp);
					        }
						}
						
					}
				}else if(StringUtils.isNotBlank(type) && type.equals("2")){
					List<GetmoneyRecord> userList = sellerWithdrawalsDao.getUserWithdrawalList(request, start, end);
					if (userList.size()>0) {
						 request.setAttribute("list", userList);
						 
						 
						 if (btn.equals("导出Excel") || StringUtils.isNotBlank(btn)) {
							 List<GetmoneyRecord> usersList = sellerWithdrawalsDao.getUserWithdrawalList(request, 0, 0);
							 for (GetmoneyRecord match : usersList){
								 String[] temp = new String[20];
						            temp[0] = String.valueOf(match.getId());
						            temp[1] = match.getUsers().getName();
						            temp[2] = String.valueOf(match.getMoney());
						            temp[3] = String.valueOf(match.getCounterFee());
						            if (match.getCreateTime()!=null) {
						            	temp[4] = sdf.format(match.getCreateTime());
									}else{
										temp[4] = "无";
									}
						            temp[5] = match.getAccount();
						            temp[6] = match.getUsers().getPhone();
						            temp[7] = match.getAccount();
						            temp[8] = match.getAddress();
						            temp[9] = match.getBatchNo();
						            if (match.getChecktime()!=null) {
						            	temp[10] =sdf.format(match.getChecktime());
									}else{
										temp[10] = "无";
									}
						            if (match.getYiLianPayTime()!=null) {
						            	temp[11] =sdf.format(match.getYiLianPayTime());
									}else{
										temp[11] ="无";
									}
						            temp[12] = "无";
						            temp[13] = match.getRemark();
						            temp[14] ="已支付";
						            value.add(temp);
						       }
						}
						 
					}
					
				}
		    	
		    	if (btn.equals("导出Excel")) {
		    		int[] dataType = new int[15];
			        String fileName = "提现数据"+System.currentTimeMillis();
			        ExcelUtil.ExportFile(ExcelUtil.getWorkBook(), titleList, value, response, fileName, dataType,false,null);
				}
		    	request.setAttribute("page", pagestr);
	            request.setAttribute("count", count);
	            request.setAttribute("sTM", sTM);
	     		request.setAttribute("eTM", eTM);
	     		request.setAttribute("type", type);
	     		request.setAttribute("btn", btn);
	            request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
			}
			
			
		}
		
		
}
