package com.axp.model;

import java.util.Date;

/**
 * Shoppingcar entity. @author MyEclipse Persistence Tools
 */
public class Shoppingcar extends AbstractShoppingcar implements
		java.io.Serializable {

	// Constructors
	public int surplus;

	/** default constructor */
	public Shoppingcar() {
	}

	/** minimal constructor */
	public Shoppingcar(Cashshop cashshop, Seller seller, Users users,
			boolean isValid) {
		super(cashshop, seller, users, isValid);
	}

	/** full constructor */
	public Shoppingcar(Cashshop cashshop, Seller seller, Users users,
			Integer amount, String parameter, double payCash, double payValue,
			boolean isValid, Date createTime,UserShoppingcar usershop,Integer isEvaluate) {
		super(cashshop, seller, users, amount, parameter, payCash, payValue,
				isValid, createTime,usershop,isEvaluate);
	}

	public int getSurplus() {
		int count = 0;
		if(this.getCashshop()!=null
				&&this.getCashshop().getSurplus()!=null
				&&this.getCashshop().getSurplus()>=0){
			count = this.getCashshop().getSurplus();
		}
		return count;
	}
	
	

}
