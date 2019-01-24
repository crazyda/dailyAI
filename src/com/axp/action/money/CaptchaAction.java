package com.axp.action.money;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.util.StringUtil;

/**
 * 用户更换手机号码；
 */

@Controller
@RequestMapping("/captcha")
public class CaptchaAction extends BaseAction {

    /**
     * 更换手机号码页面；
     */
    @RequestMapping("/sendMessage")
    @ResponseBody
    public Map<String, Object> sendMessage(String phoneNumber) {

        //检查手机号；
        if (StringUtil.isEmpty(phoneNumber) || phoneNumber.length() != 11) {
            Map<String, Object> returnMap = new HashedMap();
            returnMap.put("status", "-1");
            returnMap.put("message", "请输入正确的手机号；");
            return returnMap;
        }

        //发送短信；
        return captchaService.sendCaptcha(phoneNumber);
    }
}

