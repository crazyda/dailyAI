package com.axp.action.money;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ISellerMoneyRecordDao;
import com.axp.query.PageResult;
import com.axp.util.CalcUtil;
import com.axp.util.StringUtil;

/**
 * 商家体现功能；
 * <p>
 * Created by Administrator on 2016/7/30 0030.
 */

@Controller
@RequestMapping("/sellerWithdraw")
public class SellerWithdrawAction extends BaseAction {

    @IsItem(firstItemName = "资金管理", secondItemName = "商家提现")
    @RequestMapping("/list")
    public String sellerWithdrawList(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        if (adminUserId == null || seller == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        //查询出所有的商家提现记录，并放到session中；
        AdminUser adminUser = adminUserService.findById(adminUserId);
        List<AdminUser> list = new ArrayList<>();
        list.add(adminUser);
        PageResult<ReSellerWithdrawRecord> result = reSellerWithdrawRecordService.getRecordList(currentPage, pageSize, list);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/sellerWithdraw/list";
    }

    /**
     * 申请体现（第一次）
     * <p>
     * 1，如果尚未绑定手机或者验证个人信息，需要先绑定手机验证信息；（adminUser的phone字段；）
     * 2，在绑定了手机的前提下，每次进入都需要进行手机的验证；(即使是还没有进行提现信息审核)
     *
     * @param request
     * @return
     */
    @RequestMapping("/requestWithdraw")
    @ResponseBody
    public Map<String, Object> requestWithdraw(HttpServletRequest request) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //参数；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);//后台用户；
        Seller seller = sellerService.getSellerByAdmin(adminUserId);//后台用户对应的商家；

        //处理跳转逻辑；
        return reSellerWithdrawRecordService.doRequestWithdraw(adminUser, seller, returnMap);

    }

    /**
     * 申请提现（第二次）
     * 说明：这一次的请求主要目的就在于，验证完手机后，需要根据商家的状态，做相对应的跳转；
     */
    @RequestMapping("/requestWithdraw2")
    @ResponseBody
    public Map<String, Object> requestWithdraw2(HttpServletRequest request) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //参数；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Seller seller = sellerService.getSellerByAdmin(adminUserId);

        //处理跳转逻辑；
        return reSellerWithdrawRecordService.doRequestWithdraw2(adminUser, seller, returnMap);

    }

    /**
     * 申请提现（第三次）
     * 说明：经过前面的两次请求，只有完成了手机绑定和商家提现信息审核，并且完成了手机验证，才能进行第三次请求，这一次，直接跳转到提现页面；
     */
    @RequestMapping("/requestWithdraw3")
    public String requestWithdraw3(HttpServletRequest request) {

        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        request.setAttribute("seller", seller);

        //从数据库中获取提现比例和提现工作日天数；
        Integer tx = 6;//提现，默认为6；
        Integer gzr = 3;//提现工作日，默认为3；
        try {
            SystemConfig txSystemConfig = systemConfigService.getConfigByParameter("tx");
            SystemConfig gzrSystemConfig = systemConfigService.getConfigByParameter("gzr");
            if (txSystemConfig != null && txSystemConfig.getVersion() != null && txSystemConfig.getVersion() > 0) {
                tx = txSystemConfig.getVersion();
            }
            if (gzrSystemConfig != null && gzrSystemConfig.getVersion() != null && gzrSystemConfig.getVersion() > 0) {
                gzr = gzrSystemConfig.getVersion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("tx", tx);
        request.setAttribute("gzr", gzr);

        return "money/sellerWithdraw/requestWithdraw";
    }

    @RequestMapping("/withdraw")
    @ResponseBody
    public Map<String, Object> withdraw(HttpServletRequest request, String realName, String account, String bank, Double money, Integer type) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        //参数；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        ReSellerWithdrawRecord withdraw = new ReSellerWithdrawRecord();
        if (money == null || money <= 0d) {
            returnMap.put("message", "不合法的提现金额；");
            return returnMap;
        } else {
            withdraw.setMoney(money);
        }
        withdraw.setAccount(account);
        withdraw.setRealName(realName);
        withdraw.setStyle(type);
        if (StringUtil.hasLength(bank)) {
            withdraw.setBank(bank);
        }
        AdminUser adminUser = adminUserService.findById(adminUserId);
        withdraw.setAdminUser(adminUser);
        withdraw.setSeller(seller);
        returnMap = reSellerWithdrawRecordService.addWithdraw(withdraw, returnMap);

        /**
         * 记录商家金额变动到sellerMoneyRecord表中；
         * 1，记录提现的金额；
         * 2，记录手续费扣除金额；
         * 3，更改商家的余额；
         */

        try {
            //保存商家的提现金额；
            Double balance = CalcUtil.sub(seller.getMoney(), money); // 商家最终余额
            sellerMoneyRecordDao.saveRecord(-money, balance, seller, false, ISellerMoneyRecordDao.CASH, withdraw.getId(), ReSellerWithdrawRecord.class);

            //保存商家提现的手续费；
            SystemConfig txSystemConfig = systemConfigService.getConfigByParameter("tx");
            Integer withdrawPercent = txSystemConfig == null ? 6 : txSystemConfig.getVersion();//默认手续费就定位千分之六；
            Double shouXuFei = CalcUtil.mul(money, withdrawPercent == null ? 6 : withdrawPercent, 2);//获取手续费；
            shouXuFei = CalcUtil.div(shouXuFei, 1000, 2);//必须分这样的两部，否则千分之几就被当成0.0了；
            balance = CalcUtil.sub(balance, shouXuFei);
            sellerMoneyRecordDao.saveRecord(-shouXuFei, balance, seller, false, ISellerMoneyRecordDao.SHOUXUFEI, withdraw.getId(), ReSellerWithdrawRecord.class);

            //更改商家余额；
            seller.setMoney(balance);
            sellerDao.update(seller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnMap;
    }

    //=====================以下是商家提现审核代码========================

    /**
     * 商家提现审核列表
     */
    @IsItem(firstItemName = "资金管理", secondItemName = "商家提现审核列表")
    @NeedPermission(permissionName = "商家提现审核列表", classifyName = "资金管理")
    @RequestMapping("/sellerWithdrawCheckList")
    public String sellerWithdrawCheckList(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        //查询出所有的商家提现记录，并放到session中；
        AdminUser adminUser = adminUserService.findById(adminUserId);
        List<AdminUser> allCildAdminUsers;//所有需要被查询提现记录的后台用户；
        if (adminUser.getLevel() == StringUtil.SUPERADMIN) {
            allCildAdminUsers = null;
        } else {
            allCildAdminUsers = adminUserService.getAllCildAdminUsers(adminUser);
        }
        PageResult<ReSellerWithdrawRecord> result = reSellerWithdrawRecordService.getRecordList(currentPage, pageSize, allCildAdminUsers);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/sellerWithdraw/sellerWithdrawCheckList";
    }

    /**
     * 请求审核商家提现信息的页面；
     */
    @RequestMapping("/sellerWithdrawCheckPage")
    public String sellerWithdrawCheckPage(HttpServletRequest request, Integer recordId) {
        ReSellerWithdrawRecord reSellerWithdrawRecord = reSellerWithdrawRecordService.findById(recordId);
        request.setAttribute("record", reSellerWithdrawRecord);
        return "money/sellerWithdraw/sellerWithdrawCheckPage";
    }

    /**
     * 商家提现信息审核操作；
     */
    @RequestMapping("/sellerWithdrawCheck")
    @ResponseBody
    public Map<String, Object> sellerWithdrawCheck(HttpServletRequest request, Integer recordId, Integer pass, String message) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //审核操作；
        return reSellerWithdrawRecordService.doCheck(recordId, pass, message, returnMap);
    }

    //=================以下是给通过了审核的商家提现进行打钱操作==================

    /**
     * 确认支付商家提现的列表页面；
     */
    @IsItem(firstItemName = "资金管理", secondItemName = "支付商家提现列表")
    @NeedPermission(permissionName = "支付商家提现列表", classifyName = "资金管理")
    @RequestMapping("/confirmSellerWithdrawList")
    public String confirmSellerWithdrawList(HttpServletRequest request, Integer currentPage,Integer pageSize){
    	  //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

       //查询出所有的已经审核通过的商家提现申请，并放到session中；
        PageResult<AdminuserWithdrawals> result=withdrawReviewService.getCheckList(currentPage, pageSize, 0);
        request.getSession().setAttribute("pageResult", result);
    	return "money/sellerWithdraw/confirmSellerWithdrawList";
    }
    
    
    /*
     * 请求提现支付信息页面；
     * */
    @RequestMapping("/getsellerPayPage")
    public String getsellerPayPage (HttpServletRequest request,Integer Id){
    	AdminuserWithdrawals adminuserWithdrawals=withdrawReviewService.findById(Id);
    	request.setAttribute("wd", adminuserWithdrawals);
    	return "money/sellerWithdraw/sellerWithdrawPayPage";
    }
    

    /**
     * 给提现审核通过的商家进行打钱：
     * <p>
     * 1，这里只是确认操作，将审核状态更改为提现完成；
     * 2，真正的转钱操作在线下手动完成；
     */
    @RequestMapping("/confirmSellerWithdraw")
    @ResponseBody
    public Map<String, Object> confirmSellerWithdraw(HttpServletRequest request, Integer Id, Integer pass, String remark) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //审核操作；
        return withdrawReviewService.isPay(Id, pass, remark, returnMap);
    }
    
  

    //=================================以下是查看商家金额变动的详情代码===========================

    @RequestMapping("/getSellerMoneyChangeRecord")
    public String getSellerMoneyChangeRecord(HttpServletRequest request, Integer sellerId, Integer currentPage, Integer pageSize) {

        //参数检查；
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        //查询出所有的商家金额变动情况，并放到session中；
        PageResult<SellerMoneyRecord> result = sellerMoneyRecordService.getSellerMoneyChangeRecord(sellerId, currentPage, pageSize);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/sellerWithdraw/sellerMoneyChangeRecord";
    }
    
    
    
    /**
     * 商家提现审核列表
     */
    @IsItem(firstItemName = "资金管理", secondItemName = "商家积分推广")
    @NeedPermission(permissionName = "商家积分推广", classifyName = "资金管理")
    @RequestMapping("/sellerByScoreToScoreExtend")
    public String sellerByScoreToScoreExtend(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        //查询出所有的商家提现记录，并放到session中；
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Seller  seller = null;
        if(adminUser.getSellerId() != null){
        	
        	//查询对应的商家,
        	seller = sellerService.findById(adminUser.getSellerId());
        }else{
        	 request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
             return "error/error";//登录出错情况；
        }

        PageResult<SellerScoreExtend> result = sellerScoreExtendService.getRecordList(currentPage, pageSize, seller);
       
        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/scoretoextend/scoreToExtendlist";
    }
    
    
    @RequestMapping("/edit")
    public String edit(Integer id, Integer flag, HttpServletRequest request, HttpServletResponse response){
    	//检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        //查询出所有的商家提现记录，并放到session中；
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        SellerScoreExtend scoreExtend = null;
    	if(id != null ){ // 编辑或者新增
     		scoreExtend = sellerScoreExtendService.findById(id);
     		request.setAttribute("scoreExtend", scoreExtend);
    	}
    	return "money/scoretoextend/add";
    	
    }
    
    
    @RequestMapping("/_delete")
    public String delete(Integer id, HttpServletRequest request, HttpServletResponse response){
    	//检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        //查询出所有的商家提现记录，并放到session中；
        if (adminUserId == null) {
            request.getSession().setAttribute("data", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        
    	if(id == null){
    		 request.getSession().setAttribute("data", "删除错误");
             return "error/error";//删除错误；
    	}
    	int delete = sellerScoreExtendService.deleteById(id);
    	return "";
    	
    }
    
    
    @RequestMapping("/saveExtend")
    public String saveExtend(Integer id, HttpServletRequest request, HttpServletResponse response){
    	//检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        //查询出所有的商家提现记录，并放到session中；
        if (adminUserId == null) {
            request.getSession().setAttribute("data", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
        
        sellerScoreExtendService.saveScoreExtend(id,request, response);
    	
    	
    	return "money/scoretoextend/scoreToExtendlist";
    	
    }
    
    
}

