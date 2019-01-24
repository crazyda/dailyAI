package com.axp.model;

import java.util.HashSet;
import java.util.Set;


/**
 * 秒杀商城的商品表；
 *
 * @author Administrator
 */
public class ReGoodsOfLockMall extends ReBaseGoods {

    private Integer peopleNum;//设定参与人数
  
    private String LockDesc;//抽奖须知
    private Integer releaseNum;//需要积分
    private Integer openYards;//开奖号码
    private Integer openType; //
    private Integer gameType;//游戏类型
    private CommodityType CommodityType;//游戏类型
    private String startTime267;//倒计时商品购买开始
    private String endTime267;//倒计时商品购买结束
    private Set<CashshopTimes> timesId = new HashSet<>();  //倒计时时间范围
  
	
	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public Set<CashshopTimes> getTimesId() {
		return timesId;
	}

	public void setTimesId(Set<CashshopTimes> timesId) {
		this.timesId = timesId;
	}

	public CommodityType getCommodityType() {
		return CommodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		CommodityType = commodityType;
	}

	public Integer getGameType() {
		return gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public Integer getOpenYards() {
		return openYards;
	}

	public void setOpenYards(Integer openYards) {
		this.openYards = openYards;
	}

    public Integer getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}
    
    public Integer getPeopleNum() {
        return peopleNum;
    }

	public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }


	public String getLockDesc() {
		return LockDesc;
	}

	public void setLockDesc(String lockDesc) {
		LockDesc = lockDesc;
	}

	public String getStartTime267() {
		return startTime267;
	}

	public void setStartTime267(String startTime267) {
		this.startTime267 = startTime267;
	}

	public String getEndTime267() {
		return endTime267;
	}

	public void setEndTime267(String endTime267) {
		this.endTime267 = endTime267;
	}

   
}
