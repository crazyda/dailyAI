/**
 * 2018-10-20
 * Administrator
 */
package com.axp.service.professional;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

import com.axp.model.UserDarw;

/**
 * @author da
 * @data 2018-10-20下午2:08:02
 */
public interface UserDarwService  {

	/**
	 * 恢复用户的抽奖次数
	 */
	public void changeSurplus() ;

	/**
	 * @param i
	 * @return
	 */
	public UserDarw getById(Integer id);

	/**
	 * 保存 幸运抽奖的信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, Object> saveUserDarw(List<String> coverPics,HttpServletRequest request,
			HttpServletResponse response);
	
	
}
