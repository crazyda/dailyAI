package com.axp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.util.StringUtil;

/**
 * ReBlackOrder entity. @author MyEclipse Persistence Tools
 */

public class ReBackOrder implements java.io.Serializable {

	// Fields

	private Integer id;
	private String backCode;
	private Boolean isValid;
	private Timestamp createTime;
	private Users user;
	private ReGoodsorderItem orderItem;
	private Integer goodid;
	private Integer goodQuantity;
	private Integer sellerCode;
	private Integer mallId;
	private String mallClass;
	private Double backmoney=0d;
	private Double paymoney=0d;
	private Integer payScore =0;
	private Double payCashpoint=0d;
	private String checkMessage;
	private Seller seller;
	private Integer backtype;
	private Integer backstate;
	private Integer paytype;
	private String payAccout;
	private String accountName;
	private String reason;
	private String image;
	private Integer type;
	
	private String sellerAddress;
	private String stateChar;
	private String specDetail;
	private String priceDetail;
	
	private Integer exOrderStatus;//退单前订单状态
	
	//退款类型
	public static Integer BACKTYPR_yuanlufanhui = 10;// 原路返回
	public static Integer BACKTYPR_axp = 20;// 每天积分钱包
	//退单状态
	public static final int BACKSTATE_buketuidan = -30;//不可退单
	public static final int BACKSTATE_butongguo  = -15;//审核不通过
	public static final int BACKSTATE_weiqueren = 0;//商家未确认，直接退款
	public static final int BACKSTATE_daishenhe = 10;//待审核
	public static final int BACKSTATE_yishenhe = 20;// 已审核通过
	public static final int BACKSTATE_yizhifu = 30;// 已支付
	public static final int BACKSTATE_tuidanwancheng = 40;// 退单完成
	//支付类型
	public static Integer PAYTYPE_zfb = 10;//支付宝
	public static Integer PAYTYPE_wx = 20;// 微信
	public static Integer PAYTYPE_axp = 30;// 每天积分钱包
	//退款途径
	public static Integer TYPE_yuanlufanhui = 10;//原路返回
	public static Integer TYPE_axp = 20;// 我的钱包


	
	
	// Constructors

	/** default constructor */
	public ReBackOrder() {
	}

	
	// Property accessors

	
	public Integer getId() {
		return this.id;
	}

	public Integer getExOrderStatus() {
		return exOrderStatus;
	}


	public void setExOrderStatus(Integer exOrderStatus) {
		this.exOrderStatus = exOrderStatus;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackCode() {
		return this.backCode;
	}

	public void setBackCode(String backCode) {
		this.backCode = backCode;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}

	public ReGoodsorderItem getOrderItem() {
		return orderItem;
	}


	public void setOrderItem(ReGoodsorderItem orderItem) {
		this.orderItem = orderItem;
	}


	public Integer getGoodid() {
		return this.goodid;
	}

	public void setGoodid(Integer goodid) {
		this.goodid = goodid;
	}

	public Integer getGoodQuantity() {
		return this.goodQuantity;
	}

	public void setGoodQuantity(Integer goodQuantity) {
		this.goodQuantity = goodQuantity;
	}

	public Integer getSellerCode() {
		return this.sellerCode;
	}

	public void setSellerCode(Integer sellerCode) {
		this.sellerCode = sellerCode;
	}

	public Integer getMallId() {
		return this.mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getMallClass() {
		return this.mallClass;
	}

	public void setMallClass(String mallClass) {
		this.mallClass = mallClass;
	}

	public Double getBackmoney() {
		return backmoney;
	}

	public void setBackmoney(Double backmoney) {
		this.backmoney = backmoney;
	}

	public Double getPaymoney() {
		return this.paymoney;
	}

	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}

	public String getCheckMessage() {
		return this.checkMessage;
	}

	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Integer getBacktype() {
		return this.backtype;
	}

	public void setBacktype(Integer backtype) {
		this.backtype = backtype;
	}

	public Integer getBackstate() {
		return this.backstate;
	}

	public void setBackstate(Integer backstate) {
		this.backstate = backstate;
	}

	public Integer getPaytype() {
		return this.paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getPayAccout() {
		return this.payAccout;
	}

	public void setPayAccout(String payAccout) {
		this.payAccout = payAccout;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getPayScore() {
		return payScore;
	}


	public void setPayScore(Integer payScore) {
		this.payScore = payScore;
	}


	public Double getPayCashpoint() {
		return payCashpoint;
	}


	public void setPayCashpoint(Double payCashpoint) {
		this.payCashpoint = payCashpoint;
	}


	public String getSpecDetail() {
		String spc = orderItem.getGoodsStandardString();
		StringBuffer buffer = new StringBuffer();
		if(StringUtil.hasLength(spc)){
			JSONObject ob = JSONObject.parseObject(spc);
			for(int i=1;i<=3;i++){
				String name = ob.getJSONObject("firstStandard").getString("name"+i);
				String value = ob.getJSONObject("secondStandard").getString("name"+i);
				if(StringUtil.hasLength(name)){
					buffer.append(name+":"+value+" ");
				}
			}
		}
		specDetail = buffer.toString();
		return specDetail;
	}


	public void setSpecDetail(String specDetail) {
		this.specDetail = specDetail;
	}


	public String getPriceDetail() {
		StringBuffer buffer = new StringBuffer();
		if(paymoney!=null&&paymoney>0){
			buffer.append("现金："+paymoney+"<br/>");
		}
		if(payCashpoint!=null&&payCashpoint>0){
			buffer.append("红包："+payCashpoint+"<br/>");
		}
		if(payScore!=null&&payScore>0){
			buffer.append("积分："+payScore+"<br/>");
		}
		priceDetail = buffer.toString();
		return priceDetail;
	}


	public void setPriceDetail(String priceDetail) {
		this.priceDetail = priceDetail;
	}


	public String getSellerAddress() {
		return sellerAddress;
	}


	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}


	public String getStateChar() {
		if(this.backstate!=null){
			int a = this.backstate;
			switch (a) {
			case BACKSTATE_buketuidan:
				this.stateChar = "不可退单";
				break;
			case BACKSTATE_butongguo:
				this.stateChar = "审核不通过";
				break;
			case BACKSTATE_weiqueren:
				this.stateChar = "商家未确认，直接退款";
				break;
			case BACKSTATE_daishenhe:
				this.stateChar = "待审核";
				break;
			case BACKSTATE_yishenhe:
				this.stateChar = "已审核通过";
				break;
			case BACKSTATE_yizhifu:
				this.stateChar = "已支付";
				break;
			case BACKSTATE_tuidanwancheng:
				this.stateChar = "退单完成";
				break;
			default:
				this.stateChar = "";
			}
		}
		return this.stateChar;
	}


	public void setStateChar(String stateChar) {
		this.stateChar = stateChar;
	}
	
	public List<String> getImgList(){
		if(this.image!=null){
			List<JSONObject> list = JSONArray.parseArray(this.image,JSONObject.class);
			List<String> imgList = new ArrayList<>();
			for(JSONObject jsonObject :list ){
				imgList.add(jsonObject.getString("image"));
			}
			return imgList;
		}
		return null;
		
	}

	/*public String getUserReason(){
		if(this.reason!=null&&StringUtils.isNotBlank(this.reason)){
			JSONObject jsonObject = JSONObject.parseObject(this.reason);
			return jsonObject.getString("reason");
		}
		return null;
	}
	public String getReply(){
		if(this.reason!=null&&StringUtils.isNotBlank(this.reason)){
			JSONObject jsonObject = JSONObject.parseObject(this.reason);
			return jsonObject.getString("reply");
		}
		return null;
	}*/
	
}