package com.axp.action.money;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.model.AdminUser;
import com.axp.model.ReSellerWithdrawData;
import com.axp.model.Seller;

/**
 * 用户体现资料；
 */

@Controller
@RequestMapping("/withdrawData")
public class WithdrawDataAction extends BaseAction {

    /**
     * 进入提现资料填写界面；
     */
    @RequestMapping("/withdrawDataPage")
    public String withdrawDataPage(HttpServletRequest request) {

        //获取登录用户；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminuser = adminUserService.findById(adminUserId);

        //获取商家；
        Integer sellerId = adminuser.getSellerId();
        Seller seller = sellerService.findById(sellerId);
        request.setAttribute("seller", seller);

        //获取商家对应的商圈；
        AdminUser shangQuan = seller.getAdvertwo();
        request.setAttribute("shangQuan", shangQuan);

        return "money/withdrawData/withdrawDataPage";
    }

    /**
     * 提交审核资料；
     */
    @RequestMapping("/submitWithdrawData")
    @ResponseBody
    public Map<String, Object> submitWithdrawData(HttpServletRequest request,
                                                  String name, String zhifubao, String weixin, String yinhangka, String kaihuhang,
                                                  String zhengmiantu, String fanmiantu) {

        //返回值；
        Map<String, Object> returnMap = new HashedMap();
        returnMap.put("success", false);

        //获取商家；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminuser = adminUserService.findById(adminUserId);
        Integer sellerId = adminuser.getSellerId();
        Seller seller = sellerService.findById(sellerId);

        //商家体现记录对象；
        ReSellerWithdrawData data = new ReSellerWithdrawData();
        data.setSeller(seller);
        data.setName(name);
        data.setZhengMianTu(zhengmiantu);
        data.setFanMianTu(fanmiantu);
        data.setZhiFuBao(zhifubao);
        data.setWeiXin(weixin);
        data.setYinHang(yinhangka);
        data.setKaiHuHang(kaihuhang);

        return reSellerWithdrawDataService.doSave(seller, data, returnMap);
    }
}
