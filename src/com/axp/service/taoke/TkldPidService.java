package com.axp.service.taoke;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminUser;
import com.axp.model.TkldPid;
import com.axp.model.Users;
import com.axp.query.PageResult;
public interface TkldPidService  {

	
	
		/**
		 * 判断pid是否唯一
		 * @param pid
		 * @return
		 */
	 boolean checkPid(String pid);
	
	 /**
	  * 根据level查询上级TkldPid
	  * @param level
	  * @return
	  */
	 List<TkldPid> findParentTkldPid(Integer level);
	 
	 /**
	  * 录入关系
	  * @param tkldPid
	  * @return
	  */
	 boolean saveTkldPid(HttpServletRequest request);
	 
	 
	 boolean saveTkldPid2(HttpServletRequest request);
	 
	 void findTkldPidList(HttpServletRequest request);
	 
	 TkldPid getTkldPidById(Integer id);
	 
	 /**
	  * 根据父id查找下拉级联对象 如果父id为空 那么就查出所有运营中心
	  */
	 List<AdminUser> findAdminUserByParentId(Integer parentId);
	 
	 /**
	  * 修改分配代理商
	  * @param userId  分配的用户
	  * @param daiLiPid 要被分配的代理级别Pid
	  * @param desc  分配说明
	  * @return
	  */
	  boolean updateDistributePid(Integer userId,Integer daiLiPid,String desc);
		 
	 
	 /**
	  * 判断是否已经被赋予过Pid
	  */
	  boolean checkPidByAdminUserId(Integer id);
	 
	 
	  /**
	   * 反向查找用户所在的用户列表
	   */
	  void findAdminUserSelect(Integer id,HttpServletRequest request);
	 
	 
	 void editAddPid(Integer id,HttpServletRequest request);  
	 
	 
	 boolean cancelDistributePid(Integer id,HttpServletResponse response);
	 
	 
	 boolean delTkldPid(Integer id);
	 
	 
	 Map<String, String> saveOrUpdatePartnerPid(Integer id,String phone,String usersRemark,Integer checkbox,Integer current_user_id );
	 
	 Map<String, Integer> currentCityIsValid(HttpServletRequest request);
	 
	 /**
	  * 根据搜索条件来查找pid
	  * @param request
	  */
	  void  findPidByCondition(HttpServletRequest request );
	  
	  /**
	   * 查询事业下合伙人明细
	   */
	  void	partnerDetail(Integer id,HttpServletRequest request);
	  
	  /**
	   * 升级事业合伙人
	   */
	  boolean upgradeCareer(HttpServletRequest request);
	  
	  	/**
	  	 * 查询pid之前先得到用户level
	  	 * @param request
	  	 */
	  void SearchPid(HttpServletRequest request);
	 
	  /**
	   * 根据用户 查找下一级粉丝集合
	   */
	  PageResult<Users> findNextLevelByUserId(HttpServletRequest request,Integer currentPage, Integer pageSize,Integer userId);
	  
	  
}
