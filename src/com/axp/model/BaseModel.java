package com.axp.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.axp.dao.DateBaseDAO;
import com.axp.dao.UploadFileDAO;

public class BaseModel {

	@Autowired
	UploadFileDAO uploadFileDAO;
	@Autowired
	DateBaseDAO dateBaseDAO;

}