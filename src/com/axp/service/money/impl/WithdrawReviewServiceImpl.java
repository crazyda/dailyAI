package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.Seller;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.service.money.WithdrawReviewService;
import com.axp.service.system.UserSystemMessageService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.StringUtil;
import com.axp.util.UrlUtil;

@Service
public class WithdrawReviewServiceImpl extends BaseServiceImpl implements WithdrawReviewService{

	@Resource
	UserSystemMessageService userSystemMessageService;
	protected UrlUtil urlUtil;
	@Override
	public PageResult<AdminuserWithdrawals> getCheckList(Integer currentPage,
			Integer pageSize, Integer state) {
		return adminuserWithdrawalsDao.getCheckList(currentPage, pageSize,state);
	}

	@Override
	public AdminuserWithdrawals findById(Integer Id) {
		return adminuserWithdrawalsDao.findById(Id);
	}

	@Override
	public Map<String, Object> doCheck(Integer Id, Integer pass, String remark,
			Map<String, Object> returnMap) {
		AdminuserWithdrawals adminuserWithdrawals = adminuserWithdrawalsDao.findById(Id);
	        if (adminuserWithdrawals == null) {
	            returnMap.put("message", "没有id值为" + Id + "的对象；");
	        }
	        /**
	         * 审核通过：
	         * 1，更改提现记录表中的数据状态为审核通过；
	         */
	        if (pass == 10) {
	            //更改商家提现记录的状态；
	        	adminuserWithdrawals.setState(AdminuserWithdrawals.ti_xian_wan_cheng);
	        	//====================lbr==========================
	        	urlUtil.sendM(adminuserWithdrawals.getAdminUser().getPhone(), "您好,"+adminuserWithdrawals.getAdminUser().getUsername()+"成功申请提现，金额为"+adminuserWithdrawals.getMoney()+"。提现人：姓名"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getAdminUser().getUsername()+"，"+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankName()+"，账号："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo()+"，联系号码："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getAdminUser().getPhone()+"，请注意确认。");
	            //===================================================
	        }
	        adminuserWithdrawals.setRemark(remark);
	        adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
	        returnMap.put("message", "审核成功；");
	        return returnMap;
	}

	/*
	 * 对商家提现进行是否支付操作
	 * */
	@Override
	public Map<String, Object> isPay(Integer Id, Integer pass, String remark,Map<String, Object> returnMap) {
		AdminuserWithdrawals adminuserWithdrawals=adminuserWithdrawalsDao.findById(Id);
		if (adminuserWithdrawals==null) {
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
			
				adminuserWithdrawals.setState(-1);//修改adminuserWithdrawals表中的状态
				adminuserWithdrawals.setIsValid(false);
				adminuserWithdrawals.setRemark(remark);
				adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
				
				AdminuserCashpointRecord adminuserCashpointRecord=new AdminuserCashpointRecord();
				adminuserCashpointRecord.setAdminUser(adminUser);
				adminuserCashpointRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
				adminuserCashpointRecord.setIsValid(true);
				adminuserCashpointRecord.setAfterpoint(adminUser.getMoney());
				adminuserCashpointRecord.setBeforepoint(beforeMoney);
				adminuserCashpointRecord.setRemark(remark+";退回金额："+adminuserWithdrawals.getMoney());
				adminuserCashpointRecord.setCashpoint(money);
				adminuserCashpointRecord.setType(1);
				adminuserCashpointRecordDAO.save(adminuserCashpointRecord);
				
				AdminuserCashpointRecord adminuserCashpointRecord2=new AdminuserCashpointRecord();
				adminuserCashpointRecord2.setAdminUser(adminUser);
				adminuserCashpointRecord2.setCreateTime(new Timestamp(System.currentTimeMillis()));
				adminuserCashpointRecord2.setIsValid(true);
				adminuserCashpointRecord2.setAfterpoint(adminUser.getMoney());
				adminuserCashpointRecord2.setBeforepoint(beforeMoney);
				adminuserCashpointRecord2.setRemark(remark+"退回手续费："+counterFee);
				adminuserCashpointRecord2.setCashpoint(counterFee);
				adminuserCashpointRecord2.setType(1);
				adminuserCashpointRecordDAO.save(adminuserCashpointRecord2);
				
				
				if (users!=null) {
					List<Users> ulist = new ArrayList<Users>();
					ulist.add(users);
					userSystemMessageService.saveMessage("商家："+adminUser.getLoginname()+"提现被驳回！", StringUtil.MESSAGE_TIXIAN, "提现支付", ulist, "", money,1);
				}
			
		}
		/*
		 * 确认支付
		 * */
		else if(pass==1){
			//更改adminuserWithdrawals表中的对应数据状态；
			if (adminuserWithdrawals!=null) {
				adminuserWithdrawals.setState(AdminuserWithdrawals.ti_xian_wan_cheng);
				adminuserWithdrawals.setRemark(remark);
				adminuserWithdrawals.setChecktime(new Timestamp(System.currentTimeMillis()));
				adminuserWithdrawals.setPayName(adminuserWithdrawals.getAdminuserWithdrawalsData().getName());
				adminuserWithdrawals.setPayCode(adminuserWithdrawals.getAdminuserWithdrawalsBank().getCardNo());
				adminuserWithdrawals.setPayAddress("银行："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankName()+";地址："+adminuserWithdrawals.getAdminuserWithdrawalsBank().getBankAddress());
				adminuserWithdrawalsDao.saveOrUpdate(adminuserWithdrawals);
				
			}
			
		}
		returnMap.put("message", "操作成功；");
		return returnMap;
	}

}
