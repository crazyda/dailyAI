package com.axp.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 秒杀商城的商品表；
 *
 * @author Administrator
 */
public class ReGoodsOfSeckillMall extends ReBaseGoods {

    private Integer seckillCountLimit;//每人秒杀限购；
    private Integer seckillArea = 0;//秒杀区域；
    private Set<CashshopTimes> times = new HashSet<>();
    private String seckillDesc;//秒杀须知；
    private Integer releaseNum;//发布推广数量

    /**
     * 秒杀区域（默认为当地）
     */
    public static final Integer dangDi = 0;
    public static final Integer quanGuo = 1;

    /**
     * 获取回显时JavaScript需要的数组；
     */
    public String getArrayForReturnBack() {
        if (times.size() > 0) {
            StringBuilder sb = new StringBuilder(100);
            sb.append("[");
            Iterator<CashshopTimes> iterator = times.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next().getId()).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(",")).append("]");
            return sb.toString();
        }
        return "[]";
    }

    /**
     * getter and setter
     */
    
    

    public Integer getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}
    
    public Integer getSeckillCountLimit() {
        return seckillCountLimit;
    }

	public void setSeckillCountLimit(Integer seckillCountLimit) {
        this.seckillCountLimit = seckillCountLimit;
    }

    public Integer getSeckillArea() {
        return seckillArea;
    }

    public void setSeckillArea(Integer seckillArea) {
        this.seckillArea = seckillArea;
    }

    public Set<CashshopTimes> getTimes() {
        return times;
    }

    public void setTimes(Set<CashshopTimes> times) {
        this.times = times;
    }

    public String getSeckillDesc() {
        return seckillDesc;
    }

    public void setSeckillDesc(String seckillDesc) {
        this.seckillDesc = seckillDesc;
    }
}
