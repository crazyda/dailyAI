package com.axp.model;

/**
 * TkThePartnerIndex1 entity. @author MyEclipse Persistence Tools
 */
public class TkThePartnerIndex1 extends AbstractTkThePartnerIndex1 implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TkThePartnerIndex1() {
	}

	/** minimal constructor */
	public TkThePartnerIndex1(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public TkThePartnerIndex1(Integer userId, Integer pidId, String usname,
			String id_1, String uid, String allFeeYesterday,
			String selfFeeYesterday, String allFeeToweek, String selfFeeToweek,
			String allFeeThismonth, String selfFeeThismonth,
			String allFeeLastmonth, String selfFeeLastmonth,
			String orderCountYesterday, String selfCountYesterday,
			String orderCountToweek, String selfCountToweek,
			String orderCountThismonth, String selfCountThismonth,
			String orderCountLastmonth, String selfCountLastmonth, String date,
			String allFeeToday, String selfFeeToday, String orderCountToday,
			String selfCountToday, String userprice, String lastMonthPrice,
			String thisMonthPrice, Boolean isValid) {
		super(userId, pidId, usname, id_1, uid, allFeeYesterday,
				selfFeeYesterday, allFeeToweek, selfFeeToweek, allFeeThismonth,
				selfFeeThismonth, allFeeLastmonth, selfFeeLastmonth,
				orderCountYesterday, selfCountYesterday, orderCountToweek,
				selfCountToweek, orderCountThismonth, selfCountThismonth,
				orderCountLastmonth, selfCountLastmonth, date, allFeeToday,
				selfFeeToday, orderCountToday, selfCountToday, userprice,
				lastMonthPrice, thisMonthPrice, isValid);
	}

}
