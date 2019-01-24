package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperAssetlog entity. @author MyEclipse Persistence Tools
 */
public class NewRedPaperAssetlog extends AbstractNewRedPaperAssetlog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NewRedPaperAssetlog() {
	}

	/** full constructor */
	public NewRedPaperAssetlog(AdminUser adminUser, Seller seller,
			Double positions, Timestamp createTime, AdminUser remitter,
			Boolean isValid) {
		super(adminUser, seller, positions, createTime, remitter, isValid);
	}

}
