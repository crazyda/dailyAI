package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Receiver extends AbstractReceiver implements Serializable {

	public Receiver(){}
	
	public Receiver(Integer rid,String name,String phone,String describe,Timestamp createtime,boolean isvalid){
		super();
	}
}
