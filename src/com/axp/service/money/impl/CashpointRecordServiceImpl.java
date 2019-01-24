package com.axp.service.money.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdminuserCashpointRecordDAO;
import com.axp.dao.AdminuserWithdrawalsBankDao;
import com.axp.dao.AdminuserWithdrawalsDataDao;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.AdminuserWithdrawalsBank;
import com.axp.model.AdminuserWithdrawalsData;
import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;
import com.axp.service.money.CashpointRecordService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;

@Service
public class CashpointRecordServiceImpl extends BaseServiceImpl implements CashpointRecordService{

	@Autowired
	AdminuserWithdrawalsBankDao bankDao;
	@Autowired
	AdminuserWithdrawalsDataDao dataDao;
	@Autowired
	AdminuserCashpointRecordDAO cashpointRecordDao;
	@Autowired
	AdminUserDAO adminUserDao;
	@Override
	public PageResult<AdminuserCashpointRecord> getMoneyChangeRecord(
			Integer adminuserId, Integer currentPage, Integer pageSize) {
		return adminuserCashpointRecordDAO.getMoneyChangeRecord(adminuserId, currentPage, pageSize);
	}

	@Override
	public Object[] getTotalIncomeAndExpend(Integer adminUserId,Integer type) {
		return adminuserCashpointRecordDAO.getTotalIncomeAndExpend(adminUserId,type);
	}
	
	@Override
	public void withdrawalApplyPage(HttpServletRequest request) {
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserDao.findById(adminUserId);
        request.setAttribute("adminUser", adminUser);
        
        AdminuserWithdrawalsBank bank = null;
        QueryModel queryModel=new QueryModel();
        queryModel.combPreEquals("isValid", true);
		queryModel.combEquals("adminUser.id", adminUser.getId());
		queryModel.combEquals("isDefault", 1);
		List<AdminuserWithdrawalsBank> bankList = dateBaseDao.findLists(AdminuserWithdrawalsBank.class, queryModel);
		if (bankList.size()>0) {
			bank = bankList.get(0);
			request.setAttribute("bank", bank);
		}
		
		AdminuserWithdrawalsData data = null;
		queryModel.clearQuery();
		queryModel.combPreEquals("isValid", true);
		queryModel.combEquals("adminUser.id", adminUser.getId());
		List<AdminuserWithdrawalsData> dataList = dateBaseDao.findLists(AdminuserWithdrawalsData.class, queryModel);
		if (dataList.size()>0) {
			data=dataList.get(0);
			request.setAttribute("data", data);
		}
	}

	@Override
	public Map<String, Object> saveWithdrawApply(HttpServletRequest request,String phone, String cardNo,double money) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		try {
			Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId"); 
			AdminuserWithdrawals adminuserWithdrawals = new AdminuserWithdrawals();
			AdminUser adminUser = adminUserDAO.findById(adminUserId);
			
			QueryModel queryModel = new QueryModel();
			
			AdminuserWithdrawalsBank bank =null;
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("cardNo", cardNo);
			queryModel.combPreEquals("adminUser.id", adminUserId, "adminuserId");
			List<AdminuserWithdrawalsBank> bankList = dateBaseDao.findLists(AdminuserWithdrawalsBank.class, queryModel);
			if (bankList!=null&&bankList.size()>0) {
				bank = bankList.get(0);
				adminuserWithdrawals.setAdminuserWithdrawalsBank(bank);
			}
			double fee = bank.getCounterFee()==null?0.005:bank.getCounterFee()*0.01;
			double feeV  = CalcUtil.mul(money, fee, 2)<2.00?2.00:CalcUtil.mul(money, fee, 2);
			
			AdminuserWithdrawalsData data = null;
			queryModel.clearQuery();
			queryModel.combPreEquals("isValid", true);
			queryModel.combPreEquals("phone", phone);
			queryModel.combPreEquals("adminUser.id", adminUserId, "adminuserId");
			List<AdminuserWithdrawalsData> dataList = dateBaseDao.findLists(AdminuserWithdrawalsData.class, queryModel);
			if (dataList.size()>0) {
				data = dataList.get(0);
				adminuserWithdrawals.setAdminuserWithdrawalsData(data);
			}

			adminuserWithdrawals.setMoney(money);
			adminuserWithdrawals.setIsValid(true);
			adminuserWithdrawals.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			adminuserWithdrawals.setAdminUser(adminUser);
			adminuserWithdrawals.setState(0);
			adminuserWithdrawals.setCounterFee(Double.valueOf(feeV));
			adminuserWithdrawalsDao.save(adminuserWithdrawals);
			
			adminUser.setMoney(adminUser.getMoney()-(money+feeV));
			adminUserDao.update(adminUser);
			
			
			AdminuserCashpointRecord acr = new AdminuserCashpointRecord();
			acr.setAdminUser(adminUser);
			acr.setBeforepoint(adminUser.getMoney());
			acr.setCashpoint(-money);
			acr.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			acr.setIsValid(true);
			acr.setRemark("提现扣除"+money);
			acr.setType(1);
			cashpointRecordDao.save(acr);
			
			AdminuserCashpointRecord acr2 = new AdminuserCashpointRecord();
			acr2.setAdminUser(adminUser);
			acr2.setBeforepoint(adminUser.getMoney());
			acr2.setCashpoint(-feeV);
			acr2.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			acr2.setIsValid(true);
			acr2.setRemark("提现手续费扣除"+feeV);
			acr2.setType(1);
			cashpointRecordDao.save(acr2);
			
			statusMap.put("status", 1);
			statusMap.put("message", "提交成功-等待审核！");
		} catch (Exception e) {
			e.printStackTrace();
			statusMap.put("message", "提交失败！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}
		return statusMap;
	}

	//保存用户提现资料
	@Override
	public Map<String, Object> saveUserInfo(HttpServletRequest request,String name, String phone, String code, String coverPics,
			String descriptionPics) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		try {
			Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
			AdminUser adminUser = adminUserDAO.findById(adminUserId);
			AdminuserWithdrawalsData data = new AdminuserWithdrawalsData();
			QueryModel queryModel=new QueryModel();
			queryModel.clearQuery();
			queryModel.combEquals("isvalid", 1);
			queryModel.combEquals("adminUser.id", adminUser.getId());
			List<AdminuserWithdrawalsData> datalist = dateBaseDao.findLists(AdminuserWithdrawalsData.class, queryModel);
			if (datalist!=null&&datalist.size()>0) {
				queryModel.clearQuery();
				queryModel.combEquals("isvalid", 1);
				queryModel.combEquals("adminUser.id", adminUser.getId());
				queryModel.combEquals("status", 0);
				List<AdminuserWithdrawalsDataLog> loglist=dateBaseDao.findPageList(AdminuserWithdrawalsDataLog.class, queryModel, 0, pageSize);
				AdminuserWithdrawalsDataLog adminuserWithdrawalsDataLog=null;
				if(loglist !=null && loglist.size()>0){
					adminuserWithdrawalsDataLog =loglist.get(0);
				}else{
					adminuserWithdrawalsDataLog=new AdminuserWithdrawalsDataLog();
				}
				adminuserWithdrawalsDataLog.setIsValid(true);
				adminuserWithdrawalsDataLog.setName(name);
				adminuserWithdrawalsDataLog.setPhone(phone);
				adminuserWithdrawalsDataLog.setCode(code);
				adminuserWithdrawalsDataLog.setCretatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				adminuserWithdrawalsDataLog.setAdminUser(adminUser);
				adminuserWithdrawalsDataLog.setStatus(0);
				adminuserWithdrawalsDataLog.setImage(coverPics);
				adminuserWithdrawalsDataLog.setImage2(descriptionPics);
				adminuserWithdrawalsDataLogDao.saveOrUpdate(adminuserWithdrawalsDataLog);
				statusMap.put("status", 1);
				statusMap.put("message", "提交成功-等待审核！");
			}else{
				AdminuserWithdrawalsDataLog adminuserWithdrawalsDataLog=new AdminuserWithdrawalsDataLog();
				adminuserWithdrawalsDataLog.setIsValid(true);
				adminuserWithdrawalsDataLog.setName(name);
				adminuserWithdrawalsDataLog.setPhone(phone);
				adminuserWithdrawalsDataLog.setCode(code);
				adminuserWithdrawalsDataLog.setCretatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				adminuserWithdrawalsDataLog.setAdminUser(adminUser);
				adminuserWithdrawalsDataLog.setStatus(10);
				adminuserWithdrawalsDataLog.setImage(coverPics);
				adminuserWithdrawalsDataLog.setImage2(descriptionPics);
				adminuserWithdrawalsDataLogDao.save(adminuserWithdrawalsDataLog);
				
				data.setIsValid(true);
				data.setName(name);
				data.setPhone(phone);
				data.setCode(code);
				data.setCretatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				data.setAdminUser(adminUser);
				data.setImage(coverPics);
				data.setImage2(descriptionPics);
				dataDao.save(data);
				statusMap.put("status", 1);
				statusMap.put("message", "提交成功-审核通过");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			statusMap.put("message", "提交失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}
		return statusMap;
		
	}

	//保存提现银行卡信息
	@Override
	public Map<String, Object> saveBankInfo(HttpServletRequest request,String bankName, String cardNo, String bankAddress) {
		Map<String, Object> statusMap = new HashMap<String, Object>();
		try {
			Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
			AdminuserWithdrawalsBank bank = new AdminuserWithdrawalsBank();
			AdminUser adminUser = adminUserDAO.findById(adminUserId);
			bank.setAdminUser(adminUser);
			bank.setBankAddress(bankAddress);
			bank.setBankName(bankName);
			bank.setCardNo(cardNo);
			bank.setIsValid(true);
			bank.setIsDefault(true);
			bank.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			bank.setCounterFee(0.5);//手续费0.5%
			bankDao.save(bank);
			statusMap.put("status", 1);
			statusMap.put("message", "提交成功-等待审核");
		} catch (Exception e) {
			e.printStackTrace();
			statusMap.put("message", "提交失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return statusMap;
	}

}
