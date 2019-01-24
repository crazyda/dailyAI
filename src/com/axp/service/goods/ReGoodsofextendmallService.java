package com.axp.service.goods;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.ReGoodsofextendmall;
import com.axp.query.PageResult;
import com.axp.service.system.IBaseService;
import com.axp.util.ResponseResult;

public interface ReGoodsofextendmallService extends IBaseService{
	  /**
     * 获取所有的待审核的推广商品；
     *
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @return
     */
    PageResult<ReGoodsofextendmall> getCheckPageresult(Integer currentPage, Integer pageSize,Integer currentUserId);
    
    
    /**
     * 根据id获取对象；
     *
     * @param checkGoodsId
     * @return
     */
    ReGoodsofextendmall getcheckGoodsById(Integer checkGoodsId);
    
    /**
     * 审核商品；
     *
     * @param returnMamp
     * @param isPass
     * @param checkDesc
     * @param goodsId
     * @return
     */
    Map<String, Object> doCheck(Map<String, Object> returnMamp, Boolean isPass, String checkDesc, Integer goodsId) throws Exception;
    
    void extendMallTop(Integer id);
    
    /**
     * 推广商品置顶列表
     */
    PageResult<ReGoodsofextendmall> findTopPageresult(Integer currentPage, Integer pageSize,Integer currentUserId,String searchName);
    
    
     void  rejectExtendMallTop(Integer id);
     
     void doSavePut(HttpServletRequest request) throws Exception;//推广优惠券
    
}
