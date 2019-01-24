/**
 * 2018-11-14
 * Administrator
 */
package com.axp.service.money;

import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.query.PageResult;

/**
 * @author da
 * @data 2018-11-14下午6:02:44
 */
public interface AdminuserCashpointRecordService {
	/**
	 * 查询退押金的信息
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageResult<AdminuserCashpointRecord> reDepositList(Integer currentPage,
			Integer pageSize,String state,String sellerName);
}
