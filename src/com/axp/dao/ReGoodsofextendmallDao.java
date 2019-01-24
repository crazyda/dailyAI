package com.axp.dao;

import com.axp.model.AdminUser;
import com.axp.model.ReGoodsofextendmall;
import com.axp.query.PageResult;


public interface ReGoodsofextendmallDao extends IBaseDao<ReGoodsofextendmall>{
	 /**
     * 获取所有的待审核的推广商品；
     *
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @return
     */
    PageResult<ReGoodsofextendmall> getCheckPageresult(Integer currentPage, Integer pageSize,Integer currentUserId);
    
    
    /**
     * 推广商品置顶列表
     */
    PageResult<ReGoodsofextendmall> findTopPageresult(Integer currentPage, Integer pageSize,Integer currentUserId,String searchName);
    
    
    void doUnPutCoupon(AdminUser adminUser,String goodsOrder);
    
}
