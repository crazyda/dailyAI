package com.axp.service.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.Seller;
import com.axp.query.PageResult;

public interface ReGoodsOfBaseService {

    /**
     * 为商品的列表展示界面，提供需要的商品列表，以供展示；
     *
     * @param sellerId    id值；
     * @param currentPage 当前页；
     * @param pageSize    每页展示的数据量；
     * @param goodsType   商品的类型（具体对应的类型在list方法中能找到）；
     * @param searchWord  搜索词；
     * @return
     */
    PageResult<ReGoodsOfBase> getGoodsForList(Integer sellerId, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord);

    /**
     * 保存商品操作；
     * 1，保存商品到ReGoodsOfBase表中；
     * 2，保存商品到快照表中；
     *
     * @param seller 商家；
     * @param name 商品名；
     * @param coverPic 封面图片；
     * @param typesId 商品类型的id值（这是一级分类的id值）；
     * @param lablesId 商品标签的id值；
     * @param descriptionPics 商品简述图片（最多五张）；
     * @param intro 商品简述；
     * @param details 商品详细信息；
     * @return
     */
    //	Map<String, Object> doSaveOfBaseGoods(Seller seller, String name, String coverPic, List<Integer> typesId, List<Integer> lablesId,
    //			List<String> descriptionPics, String intro, String details);

    /**
     * 根据所给的参数，将数据保存到RegoodsOfBase表中；
     *
     * @param seller 商家；
     * @param name 商品名；
     * @param coverPic 封面图片；
     * @param typesId 商品类型的id值；
     * @param lablesId 商品标签id值；
     * @param descriptionPics 商品简述图片（最多五张）；
     * @param intro 商品简述；
     * @param details 商品详细信息；
     * @return
     */
    //	ReGoodsOfBase save(Seller seller, String name, String coverPic, List<Integer> typesId, List<Integer> lablesId,
    //			List<String> descriptionPics, String intro, String details);

    /**
     * 根据id获取对象；
     *
     * @param baseGoodsId 获取对象的id值；
     * @return
     */
    ReGoodsOfBase get(Integer baseGoodsId);

    /**
     * 保存基础商品对象；
     *
     * @param baseGoods
     */
    void update(ReGoodsOfBase baseGoods);

    /**
     * 这个方法查询的是各个商城中的商品；
     *
     * @param <T>
     * @param seller      商家；
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @param goodsType   商城类型；
     * @param searchWord  模糊搜索词；
     * @return
     */
    <T> PageResult<T> getGoodsListInMall(Seller seller, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord);

    /**
     * 商品下架操作；
     *
     * @param mallId      需要下架的商城；
     * @param mallGoodsId 需要下架的商品id值；
     * @return
     */
    Map<String, Object> doUnPut(HttpServletRequest request,Integer mallId, Integer mallGoodsId) throws Exception;

    ReGoodsOfBase save(Integer id, Seller seller, String name, List<String> coverPic,
                       List<Integer> typesId, List<Integer> lablesId,
                       List<String> descriptionPics, String intro, String details,List<Integer> rightsId);

    /**
     * 保存基础商品对象；
     * 1，首先需要保存基础商品对象；
     * 2，其次需要保存到快照区域；
     *
     * @param seller
     * @param name
     * @param coverPic
     * @param typesId
     * @param lablesId
     * @param descriptionPics
     * @param intro
     * @param details
     * @param content
     * @return
     */
    Map<String, Object> doSaveOfBaseGoods(Integer id, Seller seller, String name,
            List<String> coverPic, List<Integer> typesId,
            List<Integer> lablesId, List<String> descriptionPics, String intro,
            String details, List<String> content,List<Integer> rightsId);

    /**
     * 删除未通过审核的商品；
     *
     * @param mallType
     * @param goodsId
     */
    void delUnpassGoods(Integer mallType, Integer goodsId) throws Exception;
    
    
    void backUpBaseGoods(Integer goodsId,int salesvolume,String mall);
    
    void setStandardDetails(JSONArray standardArray,Integer adminUserId,ReBaseGoods reBaseGoods);

	
    
}
