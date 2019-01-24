package com.axp.model;

/**
 * 商家商家商城的商品表；
 *
 * @author Administrator
 */
public class ReGoodsOfSellerMall extends ReBaseGoods {

    private Double noStandardRedPaper;//无商品规格时的红包可抵扣；

    /**
     * getter and setter
     */
    private Boolean isNotChange;
    
    private Boolean isSendScore;
    private Integer sendScoreNum;

   	public Boolean getIsSendScore() {
		return isSendScore;
	}

	public void setIsSendScore(Boolean isSendScore) {
		this.isSendScore = isSendScore;
	}

	public Integer getSendScoreNum() {
		return sendScoreNum;
	}

	public void setSendScoreNum(Integer sendScoreNum) {
		this.sendScoreNum = sendScoreNum;
	}

	public Boolean getIsNotChange() {
   		return isNotChange;
   	}

   	public void setIsNotChange(Boolean isNotChange) {
   		this.isNotChange = isNotChange;
   	}
    public Double getNoStandardRedPaper() {
        return noStandardRedPaper;
    }

    public void setNoStandardRedPaper(Double noStandardRedPaper) {
        this.noStandardRedPaper = noStandardRedPaper;
    }
}
