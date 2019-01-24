package com.axp.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.UploadFile;
import com.axp.service.system.UploadFileService;
import com.axp.util.ImageUploadUtil;
import com.axp.util.QueryModel;

/**
 * 
 */

/*** 
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州每天积分信息科技有限公司</p>
 * @author zhangpeng* @date 2015-6-11
 */
@SuppressWarnings("unchecked")
@Service("uploadFileService")
public class UploadFileServiceImpl extends BaseServiceImpl implements UploadFileService {

	/**新增
	 *@author zhangpeng
	 */
	public boolean add(UploadFile uf) {
		try {
			//上传文件
			ImageUploadUtil ia = new ImageUploadUtil();

			ia.setUploadtype(18);
			ia.setFilepath(uf.getUrl());
			ia.setFile_ext(uf.getType());
			ia.setImagefile(uf.getFile());
			ia.uploadimg();
			//保存到数据库
			uploadFileDAO.save(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**修改
	 *@time 2015-6-11
	 */
	public boolean edit(UploadFile uf) {
		try {
			uploadFileDAO.update(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改
	 */
	public boolean edit(Integer id, String url) {
		try {
			UploadFile uf = uploadFileDAO.findById(id);
			uf.setUrl(url);
			uploadFileDAO.update(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 修改
	 */
	public boolean delete(Integer id) {
		try {
			UploadFile uf = uploadFileDAO.findById(id);
			uploadFileDAO.delete(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**得到文件集合
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	public List<UploadFile> getUploadFiles(Integer id, String tableName) {
		if (id == null) {
			return null;
		}
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		return (List<UploadFile>) dateBaseDao.findList(UploadFile.class, queryModel);
	}

	/**得到文件原图路径
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	public List<String> getUploadFilesUrl(Integer id, String tableName) {
		if (id == null) {
			return null;
		}
		List<String> listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();

		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDao.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			listS.add(uploadFile.getUrl());
		}
		return listS;
	}

	/**得到文件原图路径（绝对路径）
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	public List<String> getUploadFilesAbsoluteUrl(Integer id, String tableName, String basePath) {
		if (id == null) {
			return null;
		}
		List<String> listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();

		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDao.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			if (StringUtils.isBlank(uploadFile.getUrl())) {
				continue;
			}
			listS.add(basePath + uploadFile.getUrl().substring(1));
		}
		return listS;
	}

	/**得到文件缩略图路径
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	public List<String> getUploadFilesSmallUrl(Integer id, String tableName, String basePath) {
		if (id == null) {
			return null;
		}
		List<String> listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDao.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			listS.add(basePath + uploadFile.getSmallUrl().substring(1));
		}
		return listS;
	}

	public List<String> getUploadFilesSmallRelativeUrl(Integer id, String tableName) {
		if (id == null) {
			return null;
		}
		List<String> listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();

		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDao.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			listS.add(uploadFile.getSmallUrl().substring(1));
		}
		return listS;
	}
}
