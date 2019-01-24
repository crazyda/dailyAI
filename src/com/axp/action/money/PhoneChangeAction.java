package com.axp.action.money;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.model.AdminUser;
import com.axp.util.StringUtil;

/**
 * 用户更换手机号码；
 */

@Controller
@RequestMapping("/changePhone")
public class PhoneChangeAction extends BaseAction {

    /**
     * 新增/更换手机号码页面；
     * <p>
     * 1，如果没有绑定手机，则跳转到绑定手机的界面；
     * 2，如果已经绑定了手机，就跳转到修改绑定手机的界面；
     */
    @RequestMapping("/changePhonePage")
    public String changePhonePage(HttpServletRequest request) throws ServletException, IOException {

        //获取手机号；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminuser = adminUserService.findById(adminUserId);
        String phone = adminuser.getPhone();

        //还没有绑定手机号；
        if (StringUtil.isEmpty(phone) || phone.length() != 11) {
            return "money/phone/bindPhonePage";
        }

        //已绑定，需要更改绑定手机；
        phone = phone.substring(0, 3) + "****" + phone.substring(7);
        request.setAttribute("bindPhone", phone);
        return "money/phone/changePhonePage";
    }

    /**
     * 绑定手机；
     */
    @RequestMapping("/bindPhone")
    @ResponseBody
    public Map<String, Object> bindPhone(HttpServletRequest request, String phoneNumber, String securityCode) {

        //返回值；
        Map<String, Object> returnMap = new HashedMap();
        returnMap.put("status", false);

        Boolean b = captchaService.contaionCaptcha(phoneNumber, securityCode);
        if (b) {
            Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
            Boolean bind = adminUserService.bindPhone(adminUserId, phoneNumber);

            if (bind) {
                returnMap.put("status", true);
                returnMap.put("message", "绑定手机成功");
                return returnMap;
            } else {
                returnMap.put("message", "绑定手机失败，没有此用户；");
                return returnMap;
            }
        } else {
            returnMap.put("message", "您输入的验证码有误；");
            return returnMap;
        }
    }

    /**
     * 更换手机；
     */
    @RequestMapping("/changePhone")
    @ResponseBody
    public Map<String, Object> chagePhone(HttpServletRequest request, String phoneNumber, String securityCode) {

        //处理逻辑和绑定手机完全相同，只是返回值可能有点不同；
        Map<String, Object> returnMap = bindPhone(request, phoneNumber, securityCode);

        //修改返回提示值；
        String message = returnMap.get("message").toString();
        if (message.startsWith("绑定")) {
            returnMap.put("message", "更换" + message.substring(2));
        }
        return returnMap;
    }

    //======================以下代码是用户通过提现资料审核后的提现验证逻辑=======================

    /**
     * 提现前的手机验证
     * 说明：在用户绑定手机后，验证完提现信息后，每次进行提现操作，都需要进行手机验证；
     */
    @RequestMapping("/confirmPhoneBeforeSellerWithdraw")
    public String comfirmPhoneBeforeSellerWihtdraw(HttpServletRequest request) {

        //获取手机号；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminuser = adminUserService.findById(adminUserId);
        String phone = adminuser.getPhone();

        request.setAttribute("bindPhone", phone);
        return "money/phone/confirmPhoneBeforeSellerWithdraw";
    }

    @RequestMapping("/confirmCaptchaBeforeSellerWithdraw")
    @ResponseBody
    public Map<String, Object> confirmCaptchaBeforeSellerWithdraw(String phoneNumber, String securityCode) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        //验证验证码；
        Boolean b = captchaService.contaionCaptcha(phoneNumber, securityCode);
        if (b) {
            returnMap.put("success", true);
            returnMap.put("message", "验证成功");
        } else {
            returnMap.put("message", "验证码不正确");
        }

        //返回结果；
        return returnMap;

    }
}

