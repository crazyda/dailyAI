package com.axp.model;

import java.sql.Timestamp;

/**
 * GetmoneyRecord entity. @author MyEclipse Persistence Tools
 */
public class GetmoneyRecord extends AbstractGetmoneyRecord implements
		java.io.Serializable {
	
	//提现支付状态；
    public static final Integer wei_zhi_fu = 0;//未支付状态；
    public static final Integer yi_shen_he =3;//改为已审核
    public static final Integer zhi_fu_cheng_gong =6;//支付成功
    public static final Integer zhi_fu_shi_bai =9;//支付失败
    public static final Integer yi_zhi_fu =10;//已支付；
	// Constructors

	/** default constructor */
	public GetmoneyRecord() {
	}

	public GetmoneyRecord(Promoter promoter, Double money, String remark,
			Integer status, Boolean isValid, Timestamp createtime,Timestamp checktime) {
		super(promoter, money, remark, status, isValid, createtime, checktime);
	}

}
