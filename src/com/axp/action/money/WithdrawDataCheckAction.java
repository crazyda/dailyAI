package com.axp.action.money;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.ReSellerWithdrawData;
import com.axp.query.PageResult;

/**
 * 用户体现资料；
 */

@Controller
@RequestMapping("/withdrawDataCheck")
public class WithdrawDataCheckAction extends BaseAction {

    /**
     * 审核列表
     */
    @NeedPermission(permissionName = "商家提现信息申请列表", classifyName = "资金管理")
    @IsItem(firstItemName = "资金管理", secondItemName = "商家提现信息申请列表")
    @RequestMapping("/checkList")
    public String checkList(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //参数；
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        /**
         * 暂时不知道是谁要去审核这些信息，默认查出所有的商家提现申请；
         */
        //查询出所有的待审核商家提现信息，并放到session中；
        PageResult<ReSellerWithdrawData> result = reSellerWithdrawDataService.getCheckDataList(currentPage, pageSize);
        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；

        return "money/withdrawData/withdrawDataCheckList";
    }

    /**
     * 请求进入具体的审核页面；
     */
    @RequestMapping("/checkPage")
    public String checkPage(HttpServletRequest request, Integer withdrawDataId) {

        ReSellerWithdrawData data = reSellerWithdrawDataService.findById(withdrawDataId);
        request.setAttribute("data", data);

        return "money/withdrawData/withdrawDataCheckPage";
    }

    /**
     * 提交商家提现信息的审核操作；
     */
    @NeedPermission(permissionName = "审核商家提现账户信息", classifyName = "资金管理")
    @RequestMapping("/submitWithdrawDataCheck")
    @ResponseBody
    public Map<String, Object> submitWithdrawDataCheck(Integer id, Integer checkStatus, String checkInfo) throws Exception {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //检查参数；
        if (id == null || id <= 0) {
            returnMap.put("message", "参数错误：没有id值；");
            return returnMap;
        }

        //审核操作；
        return reSellerWithdrawDataService.doCheck(returnMap, id, checkStatus, checkInfo);
    }
}

