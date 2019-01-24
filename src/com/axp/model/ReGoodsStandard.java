package com.axp.model;

import java.util.ArrayList;
import java.util.List;

import com.axp.util.StringUtil;

/**
 * 重构项目的商品规格表；
 * 用来记录商家自定义的商品规格，及其商品规格的明细；
 *
 * @author Administrator
 */
public class ReGoodsStandard extends ReBaseDomain implements java.io.Serializable {

    private String name;//规格名称；
    private Integer parentStandardId;//父级规格的id值，如果没有则表示是第二级规格，规格一共就分两级；
    private Integer adminUserId;//该规格对应的商家；

    private List<ReGoodsStandard> secondStandards = new ArrayList<>();//这是冗余字段，不保存到数据库；

    /**
     * 该商品对应的二级分类明细，拼接而成的字符串；
     * 1,这个字段不保存到数据库,做为一个冗余字段存在；
     * 2，在展示用户的商品规格时，临时的保存一级商品规格对应的所有二级商品规格，仅做此作用；
     */
    private String goodsStandardDetails;

    /**
     * 获取该一级商品规格的具体明细；
     *
     * @return
     */
    public String getGoodsStandardDetails() {
        if (StringUtil.hasLength(goodsStandardDetails)) {
            return goodsStandardDetails;
        }
        return "暂无规格明细；";
    }

    public void setGoodsStandardDetails(String goodsStandardDetails) {
        this.goodsStandardDetails = goodsStandardDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentStandardId() {
        return parentStandardId;
    }

    public void setParentStandardId(Integer parentStandardId) {
        this.parentStandardId = parentStandardId;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public List<ReGoodsStandard> getSecondStandards() {
        return secondStandards;
    }

    public void setSecondStandards(List<ReGoodsStandard> secondStandards) {
        this.secondStandards = secondStandards;
    }
}
