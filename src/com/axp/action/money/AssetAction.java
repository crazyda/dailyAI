package com.axp.action.money;

import java.io.Console;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminuserCashpointRecordDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.NewRedPaperAsset;
import com.axp.model.Seller;
import com.axp.model.SellerMoneyRecord;
import com.axp.util.CalcUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;


@Controller
@RequestMapping("/sellerWithdraw")
public class AssetAction extends BaseAction {
	@Autowired
	AdminuserCashpointRecordDAO adminuserCashpointRecordDAO;
    @IsItem(firstItemName = "资金管理", secondItemName = "资金查询")
    @RequestMapping("/sellerAsset")
    public String sellerAsset(HttpServletRequest request) {
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserDao.findById(adminUserId);
        request.setAttribute("adminUser", adminUser);
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        request.setAttribute("quantity", adminUser.getQuantity());//广告天数
        if (seller != null) {
            double money = seller.getMoney() == null ? 0d : seller.getMoney();
            double preMoney = assetService.getSellerPreMoney(seller.getId()) == null
                    ? 0d : assetService.getSellerPreMoney(seller.getId());
            request.setAttribute("seller", seller);
            request.setAttribute("sellerMoney", money);//确认收益
            request.setAttribute("sellerPreMoney", preMoney);//未确认收益
        }
        //获得发放人额度
        NewRedPaperAsset asset = issueService.getAsset(null, adminUser.getId() + "");
        if (asset != null) {
            request.setAttribute("redPaperAsset", asset);
            request.setAttribute("positionSurplus", asset.getPositionSurplus());
        } else {
            request.setAttribute("positionSurplus", 0);
        }

        return "money/asset/sellerAsset";
    }

    @IsItem(firstItemName = "资金管理", secondItemName = "资金明细")
    @RequestMapping("/sellerAssetLog")
    public String sellerAssetLog(HttpServletRequest request) {
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Boolean isConfirmed = (StringUtil.hasLength(request.getParameter("isConfirmed")))
                ? Boolean.parseBoolean(request.getParameter("isConfirmed")) : null;
        AdminUser adminUser = adminUserDao.findById(adminUserId);
        if (adminUser.getSellerId() != null) {
            String pagestr = request.getParameter("page");//页码
            StringBuffer paramString = new StringBuffer();//页码条件
            QueryModel model = new QueryModel();
            model.combPreEquals("isValid", true);
            model.combPreEquals("seller.id", adminUser.getSellerId(), "sellerId");
            if (isConfirmed != null) {
                model.combPreEquals("isConfirmed", isConfirmed);
            }
            PageInfo pageInfo = new PageInfo();
            int count = 0;
            count = dateBaseDao.findCount(SellerMoneyRecord.class, model);
            Utility utility = new Utility();
            utility.setPageInfomation(pageInfo, pagestr, "10", count);

            int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
            int end = pageInfo.getPageSize();

            List<SellerMoneyRecord> logList = dateBaseDao.findPageList(SellerMoneyRecord.class, model, start, end);

            pageInfo.setParam("&page=");
            request.setAttribute("count", count);
            request.setAttribute("sellerAssetLog", logList);
            request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
        }

        return "money/asset/sellerAssetLog";
    }
    @IsItem(firstItemName = "资金管理", secondItemName = "金额查询")
    @NeedPermission(permissionName = "金额查询", classifyName = "资金管理")
    @RequestMapping("/sellerAssetQuery")
    public String sellerAssetQuery(HttpServletRequest request){
    	Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
    	String type = (String)request.getParameter("type");
        AdminUser adminUser = adminUserDao.findById(adminUserId);
        
        //已确认
        Object[] veriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminUserId,1);
        if(veriryResult!=null){
        	//已确认收益
        	request.setAttribute("verifyIncome", (Double)veriryResult[0]);
        	//已确认支出
        	request.setAttribute("verifyExpend", (Double)veriryResult[1]);
        	
        	//总余额
            double totalMoney = CalcUtil.add((Double)veriryResult[0], (Double)veriryResult[1], 2);
            request.setAttribute("totalMoney", totalMoney);
        }
        
        
        
        //未确认金额
        Object[] noVeriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminUserId,-1);
        if(noVeriryResult!=null){
        	request.setAttribute("noVerifyMoney", (Double)noVeriryResult[0]);
        }
    	
        String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
        QueryModel model = new QueryModel();
        model.combPreEquals("isValid", true);
        model.combPreEquals("adminUser.id", adminUserId, "adminuserId");
        
        if (!StringUtils.isEmpty(type)) {
        	model.combPreEquals("type", Integer.parseInt(type));
        	paramString.append("&type="+type);
		}
        
        PageInfo pageInfo = new PageInfo();
        int count = 0;
        count = dateBaseDao.findCount(AdminuserCashpointRecord.class, model);
        Utility utility = new Utility();
        utility.setPageInfomation(pageInfo, pagestr, "10", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        List<AdminuserCashpointRecord> list = dateBaseDao.findPageList(AdminuserCashpointRecord.class, model, start, end);
        String changePage = "&page=";
        pageInfo.setParam(paramString.toString() + changePage);
        request.setAttribute("cashpointsList", list);
        request.setAttribute("count", count);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
    	return "money/asset/sellerAssetQuery";
    }
    
    //提现申请
    @RequestMapping("/withdrawalApplyPage")
    public String withdrawalApplyPage(HttpServletRequest request){
    	Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
    	 //已确认
        Object[] veriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminUserId,1);
        if(veriryResult!=null){
        	request.setAttribute("totalMoney", CalcUtil.add((Double)veriryResult[0], (Double)veriryResult[1], 2));
        }
        cashpointRecordService.withdrawalApplyPage(request);
    	return "money/asset/WithdrawApplyPage";
    }
    
    //提现申请保存
    @RequestMapping("/saveWithdrawApply")
    @ResponseBody
    public Map<String, Object> saveWithdrawApply(HttpServletRequest request,String phone, String cardNo,double money){
    	return  cashpointRecordService.saveWithdrawApply(request, phone, cardNo, money);
    	

    }
    
    //提现资料填写
    @RequestMapping("/withdrawApplyUserInfo")
    public String withdrawApplyUserInfo(HttpServletRequest request){
    	cashpointRecordService.withdrawalApplyPage(request);
    	return"money/asset/WithdrawUserInfoApplyPage";
    }
    //提现资料保存
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Map<String, Object> saveUserInfo(HttpServletRequest request,String name,String phone,String code,String coverPics,String descriptionPics){
    	return cashpointRecordService.saveUserInfo(request, name, phone, code, coverPics, descriptionPics);
    }
    
    //提现银行信息填写
    @RequestMapping("/withdrawApplyBankInfo")
    public String withdrawApplyBankInfo(HttpServletRequest request){
    	cashpointRecordService.withdrawalApplyPage(request);
    	return"money/asset/WithdrawBankInfoApplyPage";
    }
    
    //银行信息保存
    @RequestMapping("/saveBankInfo")
    @ResponseBody
    public Map<String, Object> saveBankInfo(HttpServletRequest request,String bankName,String cardNo,String bankAddress){
     	return cashpointRecordService.saveBankInfo(request, bankName, cardNo, bankAddress);
    }
    
    
    //资金类型搜索
    @RequestMapping("/findRecordByType")
    public void findRecordByType(HttpServletRequest request,HttpServletResponse response,Integer type){
    }
    
    
    @IsItem(firstItemName = "资金管理", secondItemName = "城市代理金额查询")
    @NeedPermission(permissionName = "城市代理金额查询", classifyName = "资金管理")
    @RequestMapping("/dlAdminUserAssetQuery")
    public String dlAdminUserAssetQuery(HttpServletRequest request){
    	Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
    	String type = (String)request.getParameter("type");
        AdminUser adminUser = adminUserDao.findById(adminUserId);
        
        //已确认
        Object[] veriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminUserId,1);
        if(veriryResult!=null){
        	
        	request.setAttribute("verifyIncome", 1);
        	
            request.setAttribute("totalMoney", 1);
        }
        //未确认金额
        Object[] noVeriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminUserId,-1);
        if(noVeriryResult!=null){
        	request.setAttribute("noVerifyMoney", (Double)noVeriryResult[0]);
        }
        String pagestr = request.getParameter("page");//页码
        StringBuffer paramString = new StringBuffer();//页码条件
        QueryModel model = new QueryModel();
        model.combPreEquals("isValid", true);
        model.combPreEquals("adminUser.id", adminUserId, "adminuserId");
        
        if (!StringUtils.isEmpty(type)) {
        	model.combPreEquals("type", Integer.parseInt(type));
        	paramString.append("&type="+type);
		}
        
        PageInfo pageInfo = new PageInfo();
        int count = 0;
        count = dateBaseDao.findCount(AdminuserCashpointRecord.class, model);
        Utility utility = new Utility();
        utility.setPageInfomation(pageInfo, pagestr, "10", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        List<AdminuserCashpointRecord> list = dateBaseDao.findPageList(AdminuserCashpointRecord.class, model, start, end);
        String changePage = "&page=";
        pageInfo.setParam(paramString.toString() + changePage);
        request.setAttribute("cashpointsList", list);
        request.setAttribute("count", count);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
    	return "money/asset/dlAdminUserAssetQuery";
    }
    
    
    
}