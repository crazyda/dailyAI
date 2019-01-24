package com.axp.model;

/**
 * ProxyZones entity. @author MyEclipse Persistence Tools
 */
public class ProxyZones extends AbstractProxyZones implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProxyZones() {
	}

	/** full constructor */
	public ProxyZones(String name, Boolean isvalid, Integer parentId) {
		super(name, isvalid, parentId);
	}

}
