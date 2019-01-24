package com.axp.service.system.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.axp.model.CommissionRate;
import com.axp.service.system.CommissionRateService;
import com.axp.util.QueryModel;
@Service
public class CommissionRateServiceImpl extends BaseServiceImpl implements CommissionRateService  {

	/**
	 * 倒序得到最近的佣金分配率
	 */
	@Override
	public CommissionRate findCommissionRate() {
		QueryModel model=new QueryModel();
		model.combEquals("isValid", 1);
		model.setOrder("createtime desc");
		List<CommissionRate> list = dateBaseDao.findLists(CommissionRate.class, model);
		return list.size()>0?list.get(0):null;
	}

}
