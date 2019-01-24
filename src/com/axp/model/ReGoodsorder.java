package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ReGoodsorder entity. @author MyEclipse Persistence Tools
 */

public class ReGoodsorder implements java.io.Serializable {

    // Fields

    //订单
    public static Integer huan_cun  = -1;//待支付
    public static Integer dai_zhi_fu  = 0;//待支付
	public static Integer dai_pin_tuan=5;  //待拼团
    public static Integer dai_que_ren  =10;//待确认
    public static Integer dai_fa_huo  =20;//待发货
    public static Integer dai_dui_huan  =25;//待兑换
    public static Integer dai_shou_huo  =30;//待收货
    public static Integer dai_ping_jia  =40;//已完成(待评价)
    public static Integer yi_wan_cheng  =50;//已完成(已评价)
    //    public static Integer tui_hui  =60;//已完成(退货)
//    public static Integer yi_ping_jia  =70;//已完成(已评价)
    //退单
    public static Integer ke_tui_dan  = 10; //可退单(审核不通过)
    public static Integer zheng_zai_tui_dan  = 20; //正在退单
    public static Integer tong_yi_tui_dan = 30;//同意退单
    public static Integer bu_ke_tui_dan  = 40; //不可退单
    public static Integer yi_tui_dan  = 50; //已退单
    //支付类型
  	public static Integer PAYTYPE_zfb = 10;//支付宝
    public static Integer PAYTYPE_wx = 20;// 微信
    public static Integer PAYTYPE_axp = 30;// 每天积分钱包
    public static Integer PAYTYPE_yl = 40;// 易联
    /**
     * 运输方式
     */
    public static final Integer bao_you = 1;//包邮
    public static final Integer bu_bao_you = 2;//不包邮；
    public static final Integer shang_men_zi_qu = 3;//上门自取；
    public static final Integer dao_dian_xiao_fei = 4;//到店消费；
    private Integer id;
    private Boolean isValid;
    private Timestamp createTime;
    private Timestamp  consignmentTime;//发货时间
    private String orderCode;//订单编码；
	private Double payPrice = 0d;//需要支付的现金；
    
	private Integer payScore = 0;//需要支付的积分；
	private Double payCashpoint = 0d;//需要支付的红包；
	private Integer payType;//支付类型；
	private String payAccount;//支付账户；
	private String accountName;//账户名称；
	private String realname;//真实姓名； 
    private String address;//用户地址；


	private String phone;//用户电话；


	private Integer status;//订单状态；


	private String message;//买家留言；


	private String logisticsCompay;//物流公司；


	private Double logisticsType = 0d;//邮费（）


	private String logisticsCode;//快递单号；


	private String logistics;//配送类型；（）


	private String sellerPhone;//商家电话；


	private String sellerAddress;//商家地址；

	private Double walletPay=0d; //钱包支付
	
	private Integer isMerge;
	
	private Integer webType=0; //web端支付类型  1微信公众号 
	
	private String visitor; //订单来源
	private Integer adminId; //微信表id 
	private Integer isGame;
	
	public Integer getIsGame() {
		return isGame;
	}
	public void setIsGame(Integer isGame) {
		this.isGame = isGame;
	}
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getWebType() {
		return webType;
	}
	public void setWebType(Integer webType) {
		this.webType = webType;
	}
	public Integer getIsMerge() {
		return isMerge;
	}
	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}
	public Double getWalletPay() {
		return walletPay;
	}
	public void setWalletPay(Double walletPay) {
		this.walletPay = walletPay;
	}


	private Timestamp confirmtime;//自动确认时间；（）
    private String code;//随机码（）；
    private String alipayCode;

	private String weixinCode;
    private Integer totalFee;
	private String transactionId;//支付订单号
	private String outTradeNo;//商户订单号
	private String timeEnd;//支付时间
	private String openid;//
    private OrderMessageList orderMessageLists;
    private Users user;//订单对应的用户；
    private Seller seller;//对应的商家；
    private List<ReGoodsorderItem> items;//总订单对应的订单明细；（冗余数据不保存到数据库）
    private Boolean isHasItems;//有效主订单数量
    /**
     * default constructor
     */
    public ReGoodsorder() {
    }
  
    public ReGoodsorder(Integer id, Boolean isValid, Timestamp createTime,
			Timestamp consignmentTime, String orderCode, Double payPrice,
			Integer payScore, Double payCashpoint, Integer payType,
			String payAccount, String accountName, String realname,
			String address, String phone, Integer status, String message,
			String logisticsCompay, Double logisticsType, String logisticsCode,
			String logistics, String sellerPhone, String sellerAddress,
			Double walletPay, Integer isMerge, Integer webType, String visitor,
			Integer adminId, Integer isGame, Timestamp confirmtime,
			String code, String alipayCode, String weixinCode,
			Integer totalFee, String transactionId, String outTradeNo,
			String timeEnd, String openid, OrderMessageList orderMessageLists,
			Users user, Seller seller, List<ReGoodsorderItem> items,
			Boolean isHasItems) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.createTime = createTime;
		this.consignmentTime = consignmentTime;
		this.orderCode = orderCode;
		this.payPrice = payPrice;
		this.payScore = payScore;
		this.payCashpoint = payCashpoint;
		this.payType = payType;
		this.payAccount = payAccount;
		this.accountName = accountName;
		this.realname = realname;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.message = message;
		this.logisticsCompay = logisticsCompay;
		this.logisticsType = logisticsType;
		this.logisticsCode = logisticsCode;
		this.logistics = logistics;
		this.sellerPhone = sellerPhone;
		this.sellerAddress = sellerAddress;
		this.walletPay = walletPay;
		this.isMerge = isMerge;
		this.webType = webType;
		this.visitor = visitor;
		this.adminId = adminId;
		this.isGame = isGame;
		this.confirmtime = confirmtime;
		this.code = code;
		this.alipayCode = alipayCode;
		this.weixinCode = weixinCode;
		this.totalFee = totalFee;
		this.transactionId = transactionId;
		this.outTradeNo = outTradeNo;
		this.timeEnd = timeEnd;
		this.openid = openid;
		this.orderMessageLists = orderMessageLists;
		this.user = user;
		this.seller = seller;
		this.items = items;
		this.isHasItems = isHasItems;
	}
	public String getAccountName() {
        return this.accountName;
    }

    public String getAddress() {
        return this.address;
    }
  	public String getAlipayCode() {
		return alipayCode;
	}
  	public String getCode() {
        return code;
    }
    public Timestamp getConfirmtime() {
        return this.confirmtime;
    }
    public Timestamp getConsignmentTime() {
		return consignmentTime;
	}
    public Timestamp getCreateTime() {
        return this.createTime;
    }
	public Integer getId() {
        return this.id;
    }
    public Boolean getIsHasItems() {
		return isHasItems;
	}
    

   
    public Boolean getIsValid() {
        return this.isValid;
    }


	// Property accessors

    public List<ReGoodsorderItem> getItems() {
        return items;
    }

    public String getLogistics() {
        return this.logistics;
    }


    public String getLogisticsCode() {
        return this.logisticsCode;
    }


    public String getLogisticsCompay() {
        return this.logisticsCompay;
    }


    public Double getLogisticsType() {
        return this.logisticsType;
    }

    public String getMessage() {
        return this.message;
    }

    public String getOpenid() {
		return openid;
	}

    public String getOrderCode() {
        return this.orderCode;
    }

    public OrderMessageList getOrderMessageLists() {
		return orderMessageLists;
	}

    public String getOutTradeNo() {
		return outTradeNo;
	}

    public String getPayAccount() {
        return this.payAccount;
    }

    public Double getPayCashpoint() {
        return this.payCashpoint;
    }

    public Double getPayPrice() {
        return this.payPrice;
    }

    public Integer getPayScore() {
        return this.payScore;
    }

    public Integer getPayType() {
        return this.payType;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRealname() {
        return this.realname;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getSellerAddress() {
        return this.sellerAddress;
    }

    public String getSellerPhone() {
        return this.sellerPhone;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getTimeEnd() {
		return timeEnd;
	}

    public Integer getTotalFee() {
		return totalFee;
	}

    public String getTransactionId() {
		return transactionId;
	}

    public Users getUser() {
        return user;
    }

    public String getWeixinCode() {
		return weixinCode;
	}

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

    public void setCode(String code) {
        this.code = code;
    }

    public void setConfirmtime(Timestamp confirmtime) {
        this.confirmtime = confirmtime;
    }

    public void setConsignmentTime(Timestamp consignmentTime) {
		this.consignmentTime = consignmentTime;
	}

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIsHasItems(Boolean isHasItems) {
		this.isHasItems = isHasItems;
	}

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public void setItems(List<ReGoodsorderItem> items) {
        this.items = items;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public void setLogisticsCompay(String logisticsCompay) {
        this.logisticsCompay = logisticsCompay;
    }

    public void setLogisticsType(Double logisticsType) {
        this.logisticsType = logisticsType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOpenid(String openid) {
		this.openid = openid;
	}

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setOrderMessageLists(OrderMessageList orderMessageLists) {
		this.orderMessageLists = orderMessageLists;
	}

    public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}


    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }


    public void setPayCashpoint(Double payCashpoint) {
        this.payCashpoint = payCashpoint;
    }


    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }


    public void setPayScore(Integer payScore) {
        this.payScore = payScore;
    }


    public void setPayType(Integer payType) {
        this.payType = payType;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


	public void setRealname(String realname) {
        this.realname = realname;
    }


	public void setSeller(Seller seller) {
        this.seller = seller;
    }


	public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }


	public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }


	public void setStatus(Integer status) {
        this.status = status;
    }


	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}


	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public void setUser(Users user) {
        this.user = user;
    }


	public void setWeixinCode(String weixinCode) {
		this.weixinCode = weixinCode;
	}
     
	
   

     
	


	


}