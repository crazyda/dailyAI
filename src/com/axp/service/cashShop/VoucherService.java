package com.axp.service.cashShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VoucherService {

	 void getList(HttpServletResponse response,HttpServletRequest request);
	 
	 void save(HttpServletRequest request,HttpServletResponse response,String Num,String faceValue,String remark);
	
}
