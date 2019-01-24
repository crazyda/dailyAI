/**
 * 2018-10-15
 * Administrator
 */
package com.axp.dao;

import com.axp.model.AppInformation;
import com.axp.model.GameActivity;
import com.axp.query.PageResult;
import com.axp.service.goods.GameActivityService;

/**
 * @author da
 * @data 2018-10-15下午5:56:57
 */
public interface GameActivityDAO extends IBaseDao<GameActivity>{

	/**
	 * 查询所有的活动列表
	 * @param currentPage
	 * @param pageSize
	 * @param searchWord
	 * @return
	 */
	PageResult<GameActivity> getAvtivityForList(Integer currentPage,
			Integer pageSize, String searchWord);

}
