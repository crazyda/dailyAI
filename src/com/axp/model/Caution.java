package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Caution extends AbstractCaution implements Serializable {

	public Caution(){}
	
	public Caution(Integer cid,String phone,Timestamp createtime,Double exceedmoney,Boolean isvalid,Integer type,String receiverPhone){
		super();
	}
}
