/**
 * 
 */
package com.axp.service.system;

import java.util.List;

import com.axp.model.UploadFile;

/*** 
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州每天积分信息科技有限公司</p>
 * @author zhangpeng* @date 2015-6-11
 */
public interface UploadFileService extends IBaseService {

	/**新增
	 *@author zhangpeng
	 */
	boolean add(UploadFile uf);

	/**修改
	 *@time 2015-6-11
	 */
	boolean edit(UploadFile uf);

	/**
	 * 修改
	 */
	boolean edit(Integer id, String url);

	/**
	 * 修改
	 */
	boolean delete(Integer id);

	/**得到文件集合
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	List<UploadFile> getUploadFiles(Integer id, String tableName);

	/**得到文件原图路径
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	List<String> getUploadFilesUrl(Integer id, String tableName);

	/**得到文件原图路径（绝对路径）
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	List<String> getUploadFilesAbsoluteUrl(Integer id, String tableName, String basePath);

	/**得到文件缩略图路径
	 * @param id
	 * @param tableName
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	List<String> getUploadFilesSmallUrl(Integer id, String tableName, String basePath);

	List<String> getUploadFilesSmallRelativeUrl(Integer id, String tableName);
}
