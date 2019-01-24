/**
 * 
 */
package com.axp.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axp.action.tag.ImagehandleAction;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.UploadFileDAO;
import com.axp.dao.impl.UploadFileDAOImpl;
import com.axp.model.UploadFile;

/*** 
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州爱小屏信息科技有限公司</p>
 * @author zhangpeng* @date 2015-6-11
 */
public  class UploadFileUtil {
	
	private DateBaseDAO dateBaseDAO;
	public UploadFileUtil(){
		dateBaseDAO = (DateBaseDAO) ToolSpring.getBean("dateBaseDAO");
	}
	
	
	private  UploadFileDAO dao = new UploadFileDAOImpl();
	
	/**新增
	 *@author zhangpeng
	 */
	public  boolean add( UploadFile uf)
	{
		try {
			//上传文件
			ImagehandleAction ia = new ImagehandleAction();
		
			ia.setUploadtype(18);
			ia.setFilepath(uf.getUrl());
			ia.setFile_ext(uf.getType());
			ia.setImagefile(uf.getFile());
			ia.uploadimg();
			//保存到数据库
			dao.save(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**修改
	 *@time 2015-6-11
	 */
	public boolean edit( UploadFile uf)
	{
		try {
			dao.update(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 修改
	 */
	public boolean edit(Integer id , String url)
	{
		try {
			UploadFile uf = dao.findById(id);
			uf.setUrl(url);
			dao.update(uf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 修改
	 */
	public boolean delete(Integer id)
	{
		try {
			UploadFile uf = dao.findById(id);
			dao.delete(uf);
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
		if(id == null )
		{
			return null;
		}
		QueryModel queryModel = new QueryModel();
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		return (List<UploadFile>) dateBaseDAO.findList(UploadFile.class, queryModel);
	}
	/**得到文件原图路径
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	public List<String> getUploadFilesUrl(Integer id, String tableName) {
		if(id == null )
		{
			return null;
		}
		List<String > listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDAO.findList(UploadFile.class, queryModel);
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
		if(id == null )
		{
			return null;
		}
		List<String > listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDAO.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			if(StringUtils.isBlank(uploadFile.getUrl())){
				continue;
			}
			listS.add(basePath+uploadFile.getUrl().substring(1));
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
	public List<String> getUploadFilesSmallUrl(Integer id, String tableName ,String basePath) {
		if(id == null )
		{
			return null;
		}
		List<String > listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDAO.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			listS.add(basePath+uploadFile.getSmallUrl().substring(1));
		}
		return listS;
	}
	
	public List<String> getUploadFilesSmallRelativeUrl(Integer id, String tableName) {
		if(id == null )
		{
			return null;
		}
		List<String > listS = new ArrayList<String>();
		QueryModel queryModel = new QueryModel();
		
		queryModel.combPreEquals("relatedId", id);
		queryModel.combPreEquals("tableName", tableName);
		List<UploadFile> list = (List<UploadFile>) dateBaseDAO.findList(UploadFile.class, queryModel);
		queryModel = null;
		for (UploadFile uploadFile : list) {
			listS.add(uploadFile.getSmallUrl().substring(1));
		}
		return listS;
	}
}
