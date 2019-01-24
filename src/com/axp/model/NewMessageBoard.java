package com.axp.model;

import java.sql.Timestamp;

/**
 * NewMessageBoard entity. @author MyEclipse Persistence Tools
 */

public class NewMessageBoard extends AbstractNewMessageBoard implements java.io.Serializable {

	public NewMessageBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewMessageBoard(Users users, Timestamp sumbitTime, String title,
			String detail, String name, String phone, String email,
			Integer state, Boolean isValid) {
		super(users, sumbitTime, title, detail, name, phone, email, state, isValid);
		// TODO Auto-generated constructor stub
	}

	

}