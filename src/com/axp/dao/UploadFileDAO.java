package com.axp.dao;

import java.util.List;

import com.axp.model.UploadFile;

public interface UploadFileDAO extends IBaseDao<UploadFile> {

	List<String> getImgUrls(Integer tableId, String tableName);

}