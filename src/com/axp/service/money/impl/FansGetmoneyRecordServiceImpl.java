package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.model.AdminuserWithdrawals;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.model.MessageType;
import com.axp.model.SystemMessageList;
import com.axp.model.UserSystemMessage;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.service.money.FansGetmoneyRecordService;
import com.axp.service.money.GetmoneyRecordService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.QueryModel;


import com.axp.util.StringUtil;
import com.push.ImpAppInformation;
import com.yilian.service.util.Base64;
import com.yilian.service.util.Util;
import com.yilian.service.versionSMS.MsgBean;
import com.yilian.service.versionSMS.MsgBody;
import com.yilian.service.versionSMS.PayMen;
@Service
public class FansGetmoneyRecordServiceImpl extends BaseServiceImpl implements FansGetmoneyRecordService{

	/*
	 *
	 *粉丝提现支付列表 */
	@Override
	public PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage,
			Integer pageSize) {
		return fansGetmoneyRecordDao.getCheckedRecord(currentPage, pageSize);
	}

	

	/*
	 * 
	 * 粉丝提现已支付列表*/
	@Override
	public PageResult<GetmoneyRecord> payList(Integer currentPage,
			Integer pageSize,Integer timed) {
		
		PageResult<GetmoneyRecord> pageResult=null;
		
		if(timed==-1){
			payQuery(fansGetmoneyRecordDao.getMoenyRecordListByStatus());
		}
		else if(timed==1){
			pageResult = fansGetmoneyRecordDao.getPaycompleteList(currentPage, pageSize);
			payQuery(pageResult.getResult());
		}else{
			pageResult = fansGetmoneyRecordDao.getPaycompleteList(currentPage, pageSize);
		}
		return pageResult;
	}
	/*
	 * 支付指定单条的粉丝提现
	 * */
	@Override
	public GetmoneyRecord findById(Integer Id) {
		return fansGetmoneyRecordDao.findById(Id);
	}

	
	
	
	
	
	
	
	
	

	/*
	 * 处理是否给支付粉丝提现操作
	 * */
	@Override
	public Map<String, Object> isPay(Integer Id, Integer pass, String remark,
			Map<String, Object> returnMap) {
		GetmoneyRecord getmoneyRecord=fansGetmoneyRecordDao.findById(Id);
		if (getmoneyRecord==null) {
			returnMap.put("message", "没有id值为" + Id + "的对象；");
		}
		/*
		 *驳回提现
		 * 1，需要将用户已经扣除的钱补上；
         * 2，将尚未确定的GetmoneyRecord表中的记录删除掉；
		 * */
		if (pass==null || pass==0) {
			Users users=getmoneyRecord.getUsers();
			Double money=getmoneyRecord.getMoney();//提现金额
			Double counterFee=getmoneyRecord.getCounterFee()==null?0d:getmoneyRecord.getCounterFee();//当笔提现的手续费
			Double beforeMoney=users.getMoney()==null?0:users.getMoney();
			
			Double countMoney=CalcUtil.add(money, counterFee);//提现金额和手续费总和
			Double totalMoney=CalcUtil.add(beforeMoney, countMoney);//当前用户账户余额加上提现金额和手续费总和
			users.setMoney(totalMoney);
			
		
			usersDAO.saveOrUpdate(users);
			
			//提现失败删除getmoneyRecord表中的记录并且新增CashmoneyRecord记录表中的记录
			
				getmoneyRecord.setStatus(0);//修改审核状态
				getmoneyRecord.setIsValid(false);//删除当前数据
				getmoneyRecord.setChecktime(new Timestamp(new Date().getTime()));
				getmoneyRecordDao.saveOrUpdate(getmoneyRecord);
				CashmoneyRecord cashmoneyRecord=new CashmoneyRecord();
				cashmoneyRecord.setIsValid(true);
				cashmoneyRecord.setMoney(money);
				cashmoneyRecord.setAfterMoney(CalcUtil.add(beforeMoney, money));
				cashmoneyRecord.setUsersByUserId(users);
				cashmoneyRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cashmoneyRecord.setAccount(getmoneyRecord.getAccount());
				cashmoneyRecord.setBeforeMoney(beforeMoney);
				cashmoneyRecord.setRemark(remark+"提现审核不通过退回："+money);
				cashmoneyRecord.setType(1);
				iCashmoneyRecordDao.saveOrUpdate(cashmoneyRecord);
				
				CashmoneyRecord cashmoneyRecord2=new CashmoneyRecord();
				cashmoneyRecord2.setIsValid(true);
				cashmoneyRecord2.setBeforeMoney(CalcUtil.add(beforeMoney, money));
				cashmoneyRecord2.setMoney(counterFee);
				cashmoneyRecord2.setAfterMoney(CalcUtil.add(counterFee,cashmoneyRecord2.getBeforeMoney()));
				cashmoneyRecord2.setUsersByUserId(users);
				cashmoneyRecord2.setAccount(getmoneyRecord.getAccount());
				cashmoneyRecord2.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cashmoneyRecord2.setRemark(remark+"提现审核不通过退回手续费："+counterFee);
				cashmoneyRecord2.setType(1);
				iCashmoneyRecordDao.saveOrUpdate(cashmoneyRecord2);
			
		}
		/*
		 * 确认支付
		 * */
		else if(pass==1){
			//更改GetmoneyRecord表中的对应数据状态；
			if (getmoneyRecord!=null) {
				getmoneyRecord.setStatus(GetmoneyRecord.yi_shen_he);
				getmoneyRecord.setRemark(remark);
				getmoneyRecord.setChecktime(new Timestamp(System.currentTimeMillis()));
				fansGetmoneyRecordDao.saveOrUpdate(getmoneyRecord);
				//=======lbr=======================
				//urlUtil.sendM(getmoneyRecord.getUsers().getPhone(),"您好,"+getmoneyRecord.getUsers().getName()+"成功申请提现，金额为"+getmoneyRecord.getMoney()+"。提现人：姓名"+getmoneyRecord.getUsers().getName()+"，"+getmoneyRecord.getAddress()+"，账号："+getmoneyRecord.getAccount()+"，联系号码："+getmoneyRecord.getUsers().getPhone()+"，请注意确认。");
			   //==================================
			}
		} 
		returnMap.put("message", "操作成功；");
		return returnMap;
	}

	
	@Override
	public Map<String, Object> artificialPay(String ids) {
		Map<String, Object> ret=new HashMap<String, Object>();
		try {
			ret.put("message", "操作成功");
			ret.put("status", 1);
			
			GetmoneyRecord getmoneyRecord = getmoneyRecordDao.findByIdValid(Integer.parseInt(ids));
			
			if(getmoneyRecord.getStatus()!=GetmoneyRecord.yi_shen_he){
				ret.put("message", "该订单并未审核通过");
				ret.put("status", -2);
			}else if(getmoneyRecord.getStatus()==GetmoneyRecord.yi_zhi_fu||getmoneyRecord.getStatus()==GetmoneyRecord.zhi_fu_cheng_gong){
				ret.put("message", "请勿重复支付");
				ret.put("status", -3);
			}else{
				getmoneyRecord.setStatus(GetmoneyRecord.yi_zhi_fu);
				getmoneyRecord.setMessage("人工手动支付");
				getmoneyRecord.setYiLianPayTime(new Timestamp(System.currentTimeMillis()));
				getmoneyRecordDao.merge(getmoneyRecord);
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
	
	
	/**
	 * 粉丝提现支付
	 */
	@Override
	public Map<String, Object> fansPay(String ids) {
		 Map<String, Object> ret=new HashMap<String, Object>();
		try {
		
		 	
		 QueryModel model=new QueryModel();
		 model.combInStr("id",ids);
		 List<GetmoneyRecord> recordList = dateBaseDao.findLists(GetmoneyRecord.class, model);
		 double sum=0d;
		 for (int i = 0; i < recordList.size(); i++) {
			sum+=recordList.get(i).getMoney(); 
		}
		 
		 MsgBean userMoney = PayMen.getUserMoney();
		 double amount=userMoney.getAMOUNT()==null?0:Double.parseDouble(userMoney.getAMOUNT());
		 if(sum>amount){
			 ret.put("status", -1);
			 ret.put("message", "账户余额不足");
			 return ret;
		 }
		 
		 //支付
		 ret=pay(recordList);
		 for (GetmoneyRecord record : recordList) {
			 if(GetmoneyRecord.yi_zhi_fu.equals(record.getStatus())){
			 record.setChecktime(new Timestamp(System.currentTimeMillis()));
			 getmoneyRecordDao.update(record);
			 }
		 }
		} catch (Exception e) {
			e.printStackTrace();
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return ret;
	}



	@Override
	public PageResult<GetmoneyRecord> getFansPayList(Integer currentPage,
			Integer pageSize) {
		
			return  fansGetmoneyRecordDao.getFansPayList(currentPage,pageSize);
		
	}


	@Override
	public  void payQuery(List<GetmoneyRecord> recordList) {
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
		for (GetmoneyRecord record : recordList) {  //5条
			if(StringUtils.isNotBlank(record.getBatchNo())&&record.getStatus()==GetmoneyRecord.zhi_fu_cheng_gong){
				req_bean.setBATCH_NO(record.getBatchNo());//同代付交易请求批次号
				String res = PayMen.sendAndRead(PayMen.signANDencrypt(req_bean));
				MsgBean res_bean = PayMen.decryptANDverify(res);
			//成功 1 改状态  2存返回消息  3 发推送提醒
			if("0000".equals(res_bean.getTRANS_STATE())) {
				if("0000".equals(res_bean.getPAY_STAT())){
					System.out.println("提现成功");
					record.setStatus(GetmoneyRecord.yi_zhi_fu);
					sb.append("您好,").append(record.getUsers().getName()).
					append(",提现已到账，金额为").append(record.getMoney()).append("。提现人：姓名").
					append(record.getUsers().getName()).append(",手机:")
					.append(record.getUsers().getPhone());
					 systemMessageList.setContent(sb.toString());
					 systemMessageList.setTitle(title);
					 systemMessageList.setUsers(record.getUsers());
					 systemMessageList.setMoney(record.getMoney());
					 systemMessageListDao.save(systemMessageList);
					 userMessage.setUsers(record.getUsers());
					 userSystemMessageDao.save(userMessage);
					 record.setMessage(res_bean.getREMARK());
					 record.setReplyStatus(res_bean.getPAY_STAT());
					 record.setYiLianPayTime(res_bean.getYiLianPayTime());
					 if(record.getUsers().getUserid()!=null&&record.getUsers().getUserid().length()==32){
						 push.pushMessageForUsers(record.getUsers().getId().toString(), record.getUsers().getUserid(), title, sb.toString(),systemMessageList.getId().toString() , null, "1", "用户消息", record.getUsers().getDevicetoken());
					 }
					 sb.setLength(0);
				}else if("00A4".equals(res_bean.getPAY_STAT())){
					record.setMessage(res_bean.getREMARK());
					record.setReplyStatus(res_bean.getPAY_STAT());
				}else{
					System.out.println("提现失败");
					record.setStatus(GetmoneyRecord.zhi_fu_shi_bai);
					record.setMessage(res_bean.getREMARK());
					record.setReplyStatus(res_bean.getPAY_STAT());
				}
				
				fansGetmoneyRecordDao.update(record); //修改状态
			}
			System.out.println(res_bean.toXml());
		 }
		}

	}
	
	
	
	/**
	 * 代付失败 驳回提现请求 
	 */
	@Override
	public Map<String, Object> rejectPay(int id) {
		
		Map<String, Object> returnMap=new HashMap<String, Object>();
		returnMap.put("status", 1);
		returnMap.put("message", "操作成功");
		try {
		GetmoneyRecord getmoneyRecord=fansGetmoneyRecordDao.findByIdValid(id);
		if (getmoneyRecord==null) {
			returnMap.put("message", "没有id值为" + id + "的对象；");
			returnMap.put("status", -1);
			return returnMap;
		}
		/*
		 *驳回提现
		 * 1，需要将用户已经扣除的钱补上；
         * 2，将尚未确定的GetmoneyRecord表中的记录删除掉；
		 * */
			Users users=getmoneyRecord.getUsers();
			Double money=getmoneyRecord.getMoney();//提现金额
			Double counterFee=getmoneyRecord.getCounterFee()==null?0d:getmoneyRecord.getCounterFee();//当笔提现的手续费
			Double beforeMoney=users.getMoney()==null?0:users.getMoney();
			Double countMoney=CalcUtil.add(money, counterFee);//提现金额和手续费总和
			Double totalMoney=CalcUtil.add(beforeMoney, countMoney);//当前用户账户余额加上提现金额和手续费总和
			users.setMoney(totalMoney);
			usersDAO.saveOrUpdate(users);
			//提现失败删除getmoneyRecord表中的记录并且新增CashmoneyRecord记录表中的记录
				getmoneyRecord.setStatus(GetmoneyRecord.zhi_fu_shi_bai);//修改审核状态
				getmoneyRecord.setIsValid(false);//删除当前数据
				fansGetmoneyRecordDao.saveOrUpdate(getmoneyRecord);
				CashmoneyRecord cashmoneyRecord=new CashmoneyRecord();
				cashmoneyRecord.setIsValid(true);
				cashmoneyRecord.setMoney(money);
				cashmoneyRecord.setAfterMoney(CalcUtil.add(beforeMoney, money));
				cashmoneyRecord.setUsersByUserId(users);
				cashmoneyRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cashmoneyRecord.setAccount(getmoneyRecord.getAccount());
				cashmoneyRecord.setBeforeMoney(beforeMoney);
				cashmoneyRecord.setRemark("信息错误银行驳回:"+getmoneyRecord.getMessage());
				cashmoneyRecord.setType(1);
				iCashmoneyRecordDao.saveOrUpdate(cashmoneyRecord);
				CashmoneyRecord cashmoneyRecord2=new CashmoneyRecord();
				cashmoneyRecord2.setIsValid(true);
				cashmoneyRecord2.setBeforeMoney(CalcUtil.add(beforeMoney, money));
				cashmoneyRecord2.setMoney(counterFee);
				cashmoneyRecord2.setAfterMoney(CalcUtil.add(counterFee,cashmoneyRecord2.getBeforeMoney()));
				cashmoneyRecord2.setUsersByUserId(users);
				cashmoneyRecord2.setAccount(getmoneyRecord.getAccount());
				cashmoneyRecord2.setCreateTime(new Timestamp(System.currentTimeMillis()));
				cashmoneyRecord2.setRemark("提现银行审核不通过退回手续费："+counterFee);
				cashmoneyRecord2.setType(1);
				iCashmoneyRecordDao.saveOrUpdate(cashmoneyRecord2);
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("status", -1);
			returnMap.put("message",e.getMessage());
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnMap;
	}



	@Override
	public  Map<String, Object> pay(List<GetmoneyRecord> recordList){
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		map.put("message", "代付成功");
		try {
		MsgBean req_bean = new MsgBean();
		req_bean.setVERSION("2.1");
		req_bean.setMSG_TYPE("100001");
		req_bean.setUSER_NAME(PayMen.userName);//系统后台登录名
		for (GetmoneyRecord record : recordList) {
			String batchNo="AXP"+new Date().getTime()+new String(Base64.decode(Util.generateKey(99999,5)));
			req_bean.setBATCH_NO(batchNo);//每笔订单不可重复，建议：公司简称缩写+yymmdd+流水号
			MsgBody body = new MsgBody();
			body.setSN(record.getUsers().getId()+String.valueOf((int)(Math.random()*6*100000)));//流水号，同一批次不重复即可
			body.setACC_NO(record.getAccount().trim());
			body.setACC_NAME(record.getName().trim());
			body.setAMOUNT(record.getMoney().toString()); 
			
			record.setBatchNo(batchNo); 
			
			req_bean.getBODYS().add(body);
			String res = PayMen.sendAndRead(PayMen.signANDencrypt(req_bean));
			MsgBean res_bean = PayMen.decryptANDverify(res);
			if("0000".equals(res_bean.getTRANS_STATE())&&("0000".equals(res_bean.getPAY_STAT())||"00A4".equals(res_bean.getPAY_STAT()))) {
				record.setStatus(GetmoneyRecord.zhi_fu_cheng_gong);
				record.setReplyStatus(res_bean.getPAY_STAT());
				record.setMessage(res_bean.getREMARK().trim());
				System.out.println("请求成功");
				System.out.println(res_bean.toXml());
			}
			else{
				record.setReplyStatus(res_bean.getPAY_STAT());
				record.setMessage(res_bean.getREMARK());
				record.setStatus(GetmoneyRecord.zhi_fu_shi_bai);
				System.out.println("请求失败GetmoneyRecord"+record.getId());
				System.out.println(res_bean.toXml());
				//throw new RuntimeException("请求失败GetmoneyRecord"+record.getId());
			}
			fansGetmoneyRecordDao.update(record);
			req_bean.getBODYS().clear();
		}
			
		} catch (Exception e) {
			System.out.println("请求失败GetmoneyRecord");
			map.put("status",- 1);
			map.put("message", "交易失败");
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
}

	
	public static void main(String[] args) {
			String a=String.valueOf((int)(Math.random()*6*100000000));
			System.out.println(a);
		
	}



}
