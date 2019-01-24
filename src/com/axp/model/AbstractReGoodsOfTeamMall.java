package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AbstractReGoodsOfTeamMall  extends ReBaseGoods implements Serializable{

	
	
	private String coverPic; //轮播图
	
	private Timestamp createTime;

	private String descriptionPics; //简述图
	
	private Double discountPrice;

	private String carouselPic;

	public String getCarouselPic() {
		return carouselPic;
	}




	public void setCarouselPic(String carouselPic) {
		this.carouselPic = carouselPic;
	}



	private Integer id;


	private Boolean isRestrict; //是否限购

	private String name;
	
	private Integer releaseNum; //发布数量

	private Integer restrictNum; //限购数量

	private Seller seller;
	
	private Integer teamNum; //最低拼团人数 

	private Integer topType;
	
	public Integer getTopType() {
		return topType;
	}




	public void setTopType(Integer topType) {
		this.topType = topType;
	}




	public AbstractReGoodsOfTeamMall(){
		
	}




	public String getCoverPic() {
		return coverPic;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getDescriptionPics() {
		return descriptionPics;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}




	public Integer getId() {
		return id;
	}

	


	public Boolean getIsRestrict() {
		return isRestrict;
	}

	public String getName() {
		return name;
	}


	public Integer getReleaseNum() {
		return releaseNum;
	}

	public Integer getRestrictNum() {
		return restrictNum==null?0:restrictNum;
	}


	public Seller getSeller() {
		return seller;
	}


	public Integer getTeamNum() {
		return teamNum;
	}



	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public void setDescriptionPics(String descriptionPics) {
		this.descriptionPics = descriptionPics;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}


	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setIsRestrict(Boolean isRestrict) {
		this.isRestrict = isRestrict;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}
	
	public void setRestrictNum(Integer restrictNum) {
		this.restrictNum = restrictNum;
	}
	
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}
	
	
	
	public String getCoverPicOne() {
        if (StringUtils.isEmpty(coverPic))
            return "";
        JSONArray list = JSONObject.parseArray(coverPic);
        if (list.size() == 0)
            return "";
        return list.getJSONObject(0).getString("imgUrl");
    }
	
}
