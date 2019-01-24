package com.axp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.axp.util.StringUtil;

public class ReShoppingCar extends ReBaseDomain {

    //关联字段；
    private ReGoodsSnapshot snapshot = null;//该购物车明细关联的快照商品；
    private Users user;//对应的用户；
    private Seller seller;//对应的商家；

    //普通字段；
    private String goodsOrder;//商品序号；形成规则就是商城前缀+商品id；
    private Integer mallTypeId;//关联的商城id；
    private Integer goodsId;//关联的商品id；
    private Integer goodsQuantity = 0;//商品数量；
    private String goodsName;//商品名称；
    private String goodsImage;//商品图片；
    private String goodsStandardIds;//商品对应的规格明细id；(废弃这个字段)
    private String goodsStandardString;//商品对应的规格明细的name；（将商品规格信息，全部以json的形式保存到这个字段中）
    /**
     * 规格的json形式如下，如果没有值就是“”；
     * {
     * "firstStandard": {
     * "id1": "1",
     * "name1": "颜色",
     * "id2": "2",
     * "name2": "大小",
     * "id3": "3",
     * "name3": "款式"
     * },
     * "secondStandard": {
     * "id1": "1",
     * "name1": "红色",
     * "id2": "2",
     * "name2": "35码",
     * "id3": "3",
     * "name3": "运动款"
     * }
     * }
     */
    private Double goodsPrice = 0d;//商品价格；
    private Double displayPrice = 0d;//显示价格；（通常都是原价）
    private Double redPaper = 0d;//商品需要的红包金额；
    private Integer score = 0;//商品需要的积分；
    private Integer loginsticsType;//商品的物流方式；
    private Double logisticsPrice = 0d;//商品需要的物流价格；

    private String sellerName;//商家名称；

    //=====================该类的一些方法开始=====================

    /**
     * 设置购物车中商品的明细；
     */
    public void setGoodsStandardString(Integer fid1, String fname1, Integer fid2, String fname2, Integer fid3, String fname3, Integer sid1, String sname1, Integer sid2, String sname2, Integer sid3, String sname3) {
        Map<String, Object> map = new HashMap<>();

        Map<String, Object> firstStandardMap = new HashMap<>();
        firstStandardMap.put("id1", fid1);
        firstStandardMap.put("name1", fname1);
        firstStandardMap.put("id2", fid2);
        firstStandardMap.put("name2", fname2);
        firstStandardMap.put("id3", fid3);
        firstStandardMap.put("name3", fname3);
        map.put("firstStandard", firstStandardMap);

        Map<String, Object> secondStandardMap = new HashMap<>();
        secondStandardMap.put("id1", sid1);
        secondStandardMap.put("id2", sid2);
        secondStandardMap.put("id3", sid3);
        secondStandardMap.put("name1", sname1);
        secondStandardMap.put("name2", sname2);
        secondStandardMap.put("name3", sname3);
        map.put("secondStandard", secondStandardMap);

        this.goodsStandardString = JSONObject.toJSONString(map);
    }

    /**
     * 获取前台需要展示的商品规格的名称；形式如： 颜色：红色，款式：运动款
     *
     * @return
     */
    public String getStandardName() {
        JSONObject standardJson = JSONObject.parseObject(this.goodsStandardString);

        JSONObject firstStandardJson = standardJson.getJSONObject("firstStandard");
        JSONObject secondStandardJson = standardJson.getJSONObject("secondStandard");

        StringBuilder returnString = new StringBuilder(100);

        String fname1 = (String) firstStandardJson.get("name1");
        String sname1 = (String) secondStandardJson.get("name1");
        returnString.append(fname1).append(":").append(sname1);

        String fname2 = (String) firstStandardJson.get("name2");
        String sname2 = (String) secondStandardJson.get("name2");
        if (StringUtil.hasLength(fname2)) {
            returnString.append(",").append(fname2).append(":").append(sname2);

            String fname3 = (String) firstStandardJson.get("name3");
            String sname3 = (String) secondStandardJson.get("name3");
            if (StringUtil.hasLength(fname3)) {
                returnString.append(",").append(fname3).append(":").append(sname3);
            }
        }

        return returnString.toString();
    }

    /**
     * 获取这个购物车的二级规格的id的集合；
     *
     * @return
     */
    public List<Integer> getStandardIdList() {
        JSONObject jsonObject = JSONObject.parseObject(this.goodsStandardString).getJSONObject("secondStandard");

        String id1 = jsonObject.getString("id1");
        String id2 = jsonObject.getString("id2");
        String id3 = jsonObject.getString("id3");

        List<Integer> resultList = new ArrayList<>();
        resultList.add(Integer.parseInt(id1));
        if (StringUtil.hasLength(id2)) {
            resultList.add(Integer.parseInt(id2));
            if (StringUtil.hasLength(id3)) {
                resultList.add(Integer.parseInt(id3));
            }
        }

        return resultList;
    }

    //=====================该类的一些方法结束=====================

    //=====================get/set==========================

    public ReGoodsSnapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(ReGoodsSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Integer getMallTypeId() {
        return mallTypeId;
    }

    public void setMallTypeId(Integer mallTypeId) {
        this.mallTypeId = mallTypeId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsStandardIds() {
        return goodsStandardIds;
    }

    public void setGoodsStandardIds(String goodsStandardIds) {
        this.goodsStandardIds = goodsStandardIds;
    }

    public String getGoodsStandardString() {
        return goodsStandardString;
    }

    public void setGoodsStandardString(String goodsStandardString) {
        this.goodsStandardString = goodsStandardString;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(Double displayPrice) {
        this.displayPrice = displayPrice;
    }

    public Double getRedPaper() {
        return redPaper;
    }

    public void setRedPaper(Double redPaper) {
        this.redPaper = redPaper;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLoginsticsType() {
        return loginsticsType;
    }

    public void setLoginsticsType(Integer loginsticsType) {
        this.loginsticsType = loginsticsType;
    }

    public Double getLogisticsPrice() {
        return logisticsPrice;
    }

    public void setLogisticsPrice(Double logisticsPrice) {
        this.logisticsPrice = logisticsPrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(String goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

}
