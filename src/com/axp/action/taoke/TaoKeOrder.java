package com.axp.action.taoke;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.service.taoke.TkThePidOrderService;

@Controller
@RequestMapping("/taoke/order/")
public class TaoKeOrder extends BaseAction  {

	
	@RequestMapping("concurrentOrder")
	public void concurrentOrder(HttpServletRequest request,Integer pid) {
		try {
			 tkThePidOrderService.saveOrUpdateOrder(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
