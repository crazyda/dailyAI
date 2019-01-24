package com.axp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * AbstractReGoodsofextendmall entity provides the base persistence definition
 * of the ReGoodsofextendmall entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractReGoodsofextendmall implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;
	private String name;
	private String coverPic;
	private String descriptionPics;
	private Double price;
	private String type;
	private String lables;
	private Double ticketprice;
	private Integer ticketnum;
	private Integer coupons;
	private Timestamp validtime;
	private Integer mall;
	private Integer mallId;
	private ProvinceEnum provinceEnum;
	private Seller seller;
	private Double commission;
	private Integer sign;
	private String goodsMall;
	private CommodityType commodityType;
	private Boolean isChecked;
	private Integer display;//0,周边 1,全国

	private Boolean isTop;

	private String checkDesc;

	private String coverPicOne = "";

	private Integer isActivity;

	private AdminuserRedpaper adminuserRedpaper;

	private Integer isRed;

	private ReGoodsofextendmall reGoodsofextendmall;
	
	private Integer sellerMallId;

	private String rightsProtect;
	/** default constructor */ 
	public AbstractReGoodsofextendmall() {
	}
	public AbstractReGoodsofextendmall(Integer id, Boolean isValid,
			Timestamp createTime, String name, String coverPic,
			String descriptionPics, Double price, String type, String lables,
			Double ticketprice, Integer ticketnum, Integer coupons,
			Timestamp validtime, Integer mall, Integer mallId,
			ProvinceEnum provinceEnum, Seller seller, Double commission,
			Integer sign, String goodsMall, CommodityType commodityType,
			Boolean isChecked,Boolean isTop,String checkDesc,Integer display) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.createTime = createTime;
		this.name = name;
		this.coverPic = coverPic;
		this.descriptionPics = descriptionPics;
		this.price = price;
		this.type = type;
		this.lables = lables;
		this.ticketprice = ticketprice;
		this.ticketnum = ticketnum;
		this.coupons = coupons;
		this.validtime = validtime;
		this.mall = mall;
		this.mallId = mallId;
		this.provinceEnum = provinceEnum;
		this.seller = seller;
		this.commission = commission;
		this.sign = sign;
		this.goodsMall = goodsMall;
		this.commodityType = commodityType;
		this.isChecked= isChecked;
		this.isTop = isTop;
		this.checkDesc = checkDesc;
		this.display=display;
	}
	public AdminuserRedpaper getAdminuserRedpaper() {
		return adminuserRedpaper;
	}
	public String getCheckDesc() {
		return checkDesc;
	}
	public Double getCommission() {
		return this.commission;
	}
	public CommodityType getCommodityType() {
		return commodityType;
	}
	public Integer getCoupons() {
		return this.coupons;
	}
	public String getCoverPic() {
		return this.coverPic;
	}
	
	
	
	public String getCoverPicOne() {
	        if (StringUtils.isEmpty(coverPic)||!coverPic.startsWith("[{"))
	            return "";
	        JSONArray list = JSONObject.parseArray(coverPic);
	        if (list.size() == 0)
	            return "";
	        return list.getJSONObject(0).getString("imgUrl");
	    }

	public Timestamp getCreateTime() {
		return this.createTime;
	}
	public String getDescriptionPics() {
		return this.descriptionPics;
	}

	public String getGoodsMall() {
		return this.goodsMall;
	}
	public Integer getId() {
		return this.id;
	}
	public Integer getIsActivity() {
		return isActivity;
	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public Integer getIsRed() {
		return isRed;
	}

	// Constructors

	public Boolean getIsTop() {
		return isTop;
	}

	
	// Property accessors

	public Boolean getIsValid() {
		return this.isValid;
	}

	public String getLables() {
		return this.lables;
	}

	/**
     * 商品规格；
     */
	public String getLablesString() {
	    StringBuilder text = new StringBuilder();
	    try {
	        JSONArray list = JSONObject.parseArray(this.lables);
	        for (int i = 0; i < list.size(); i++) {
	            text.append(list.getJSONObject(i).getString("lableName")).append(" ");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	    return text.toString();
	}

	/**
     * 获取商品简述图片集合；
     */
    public List<String> getListOfDescriptionPics() {
        JSONArray jsonArray = JSONObject.parseArray(this.descriptionPics);
        if (jsonArray == null) {
            return null;
        } else {
            List<String> returnList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                returnList.add(jsonArray.getJSONObject(i).getString("imgUrl"));
            }
            return returnList;
        }
    }

	public Integer getMall() {
		return this.mall;
	}

	public Integer getMallId() {
		return this.mallId;
	}

	public String getName() {
		return this.name;
	}

	public Double getPrice() {
		return this.price;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public ReGoodsofextendmall getReGoodsofextendmall() {
		return reGoodsofextendmall;
	}

	public Seller getSeller() {
		return seller;
	}

	public Integer getSign() {
		return this.sign;
	}

	public Integer getTicketnum() {
		return this.ticketnum;
	}

	public Double getTicketprice() {
		return this.ticketprice;
	}

	public String getType() {
		return this.type;
	}

	public Timestamp getValidtime() {
		return this.validtime;
	}



	public void setAdminuserRedpaper(AdminuserRedpaper adminuserRedpaper) {
		this.adminuserRedpaper = adminuserRedpaper;
	}

	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public void setCoupons(Integer coupons) {
		this.coupons = coupons;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public void setCoverPicOne(String coverPicOne) {
		this.coverPicOne = coverPicOne;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setDescriptionPics(String descriptionPics) {
		this.descriptionPics = descriptionPics;
	}

	public void setGoodsMall(String goodsMall) {
		this.goodsMall = goodsMall;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	

	public void setIsRed(Integer isRed) {
		this.isRed = isRed;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public void setLables(String lables) {
		this.lables = lables;
	}

	public void setMall(Integer mall) {
		this.mall = mall;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public void setReGoodsofextendmall(ReGoodsofextendmall reGoodsofextendmall) {
		this.reGoodsofextendmall = reGoodsofextendmall;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

   public void setTicketnum(Integer ticketnum) {
	this.ticketnum = ticketnum;
}

	public void setTicketprice(Double ticketprice) {
		this.ticketprice = ticketprice;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	
    public void setValidtime(Timestamp validtime) {
		this.validtime = validtime;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public Integer getSellerMallId() {
		return sellerMallId;
	}
	public void setSellerMallId(Integer sellerMallId) {
		this.sellerMallId = sellerMallId;
	}
	public String getRightsProtect() {
		return rightsProtect;
	}
	public void setRightsProtect(String rightsProtect) {
		this.rightsProtect = rightsProtect;
	}
    
    
}