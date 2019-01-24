package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.model.Users;
import com.axp.model.UsersMonitor;
import com.axp.query.PageResult;
import com.axp.service.money.GetmoneyRecordService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
@Service
public class GetmoneyRecordServiceImpl extends BaseServiceImpl implements GetmoneyRecordService{

	/*
	 *
	 *粉丝提现支付列表 */
	@Override
	public PageResult<GetmoneyRecord> getCheckedRecord(Integer currentPage,
			Integer pageSize) {
		return getmoneyRecordDao.getCheckedRecord(currentPage, pageSize);
	}

	
	@Override
	public Map<String, Object> doPay(Integer recordId,
			Map<String, Object> returnMap) {
		
		GetmoneyRecord getmoneyRecord = getmoneyRecordDao.findById(recordId);
	        if (getmoneyRecord == null) {
	            returnMap.put("message", "没有id值为" + recordId + "的申请；");
	        }
	        getmoneyRecord.setStatus(getmoneyRecord.yi_zhi_fu);	       
	        returnMap.put("message", "操作完成");
	        return returnMap;
	}


	/*
	 * 
	 * 粉丝提现已支付列表*/
	@Override
	public PageResult<GetmoneyRecord> getPaycompleteList(Integer currentPage,
			Integer pageSize) {
		return getmoneyRecordDao.getPaycompleteList(currentPage, pageSize);
	}

	
	/*
	 * 支付指定单条的粉丝提现
	 * */
	@Override
	public GetmoneyRecord findById(Integer Id) {
		return getmoneyRecordDao.findById(Id);
	}


	/*
	 * 处理是否给支付粉丝提现操作
	 * */
	@Override
	public Map<String, Object> isPay(Integer Id, Integer pass, String remark,
			Map<String, Object> returnMap) {
		GetmoneyRecord getmoneyRecord=getmoneyRecordDao.findById(Id);
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
				getmoneyRecord.setStatus(GetmoneyRecord.yi_zhi_fu);
				getmoneyRecord.setRemark(remark);
				getmoneyRecord.setChecktime(new Timestamp(System.currentTimeMillis()));
				getmoneyRecordDao.saveOrUpdate(getmoneyRecord);
				//=======lbr=======================
				urlUtil.sendM(getmoneyRecord.getUsers().getPhone(),"您好,"+getmoneyRecord.getUsers().getName()+"成功申请提现，金额为"+getmoneyRecord.getMoney()+"。提现人：姓名"+getmoneyRecord.getUsers().getName()+"，"+getmoneyRecord.getAddress()+"，账号："+getmoneyRecord.getAccount()+"，联系号码："+getmoneyRecord.getUsers().getPhone()+"，请注意确认。");
			   //==================================
			}
		} 
		returnMap.put("message", "操作成功；");
		return returnMap;
	}

}
