/**
 * 2018-10-15
 * Administrator
 */
package com.axp.service.goods;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminUser;
import com.axp.model.GameActivity;
import com.axp.query.PageResult;

/**
 * @author da
 * @data 2018-10-15下午5:21:49
 */
public interface GameActivityService {

	/**
	 * 查询所有游戏活动列表
	 * @param currentPage
	 * @param pageSize
	 * @param searchWord
	 * @return
	 */
	PageResult<GameActivity> getAvtivityForList(Integer currentPage,Integer pageSize, String searchWord);

	/**
	 * 保存翻牌商品
	 * @param request
	 * @param response
	 * @return
	 */
	Map<String, Object> saveDrawinfo( List<String> content,HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 查询某条数据
	 * @param id
	 * @return
	 */
	GameActivity findById(Integer id);

	/**
	 * 查询代理下的游戏信息
	 * @param adminUser
	 * @param request
	 * @param currentPage
	 */
	void getGameList(AdminUser adminUser, HttpServletRequest request,
			Integer currentPage);

	/**
	 * 保存签到奖品
	 * @param request
	 * @param response
	 * @param content 
	 * @param coverPics 
	 */
	void saveSignInfo(HttpServletRequest request, HttpServletResponse response, List<String> coverPics, List<String> content);

	/**
	 * 删除某条记录
	 * @param id
	 */
	int delSign(Integer id);

}
