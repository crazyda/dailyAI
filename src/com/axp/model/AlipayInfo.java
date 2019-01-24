package com.axp.model;

import java.util.Date;

/**
 * AlipayInfo entity. @author MyEclipse Persistence Tools
 */
public class AlipayInfo extends AbstractAlipayInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AlipayInfo() {
	}

	/** full constructor */
	public AlipayInfo(String alipayCode, String alipayName, double money,
			boolean mark, String reason, String alipayId, Date successTime,
			String remark, Integer status, String serialNum) {
		super(alipayCode, alipayName, money, mark, reason, alipayId,
				successTime, remark, status, serialNum);
	}

}
