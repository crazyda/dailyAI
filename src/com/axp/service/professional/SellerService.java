package com.axp.service.professional;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AdminUser;
import com.axp.model.SeLive;
import com.axp.model.Seller;
import com.axp.query.PageResult;

public interface SellerService extends IProfessionalService {

    public Seller getSellerByAdmin(Integer current_user_id);
    
	public SeLive getSeliveBySeller(Integer current_seller_id);


    public void updateSeller(Seller seller, String mainnum, String[] nums);

    /**
     * 根据id获取对象；
     *
     * @param sellerId
     * @return
     */
    Seller findById(Integer sellerId);

    /**
     * 获取商家的提现信息状态；
     * 1，如果没有值，那么要根据是否有电话号码，是否申请了提现信息，去判断其应该有的状态值，并进行赋值；
     * 2，如果有值，直接返回；
     *
     * @param seller
     * @return
     */
    Integer withdrawStatus(Seller seller);
    
    
    
    /**
     * 获取待审核商家资料信息；
     *
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @return
     */
    
    public void getSellerList(HttpServletRequest request,HttpServletResponse response);
    public void getVerifycompletedList(HttpServletRequest request,HttpServletResponse response);
    
    void detailOfStoreVerify(HttpServletRequest request,Integer sellerId);
    
    
    Map<String, Object> doReview(Map<String, Object> returnMap, Integer id, Integer verifyStatus,Integer adminuserId,HttpServletRequest request,Integer zoneId) throws Exception;

	/**
	 * 查询所有的商家
	 * @return
	 */
	public List<Seller> findByAll();

	/**
	 * 查询该代理下的所有商家
	 * @param request
	 * @param response
	 */
	public Map<String, Object> findAdminUserToSeller(HttpServletRequest request,
			HttpServletResponse response);
    
}
