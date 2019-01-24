package com.axp.service.appAdver;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axp.model.AppInformation;
import com.axp.service.system.IBaseService;

public interface AppInformationService extends IBaseService{

	public void findAppInformationList(HttpServletRequest request,HttpServletResponse response);

	/**
	 * 查找某一条信息
	 * @param request
	 * @param response
	 * @param id
	 */
	public void findAppInformation(HttpServletRequest request,
			HttpServletResponse response, Integer id);

	/**
	 * 添加 新的APP信息
	 */
	public void editAppInformation();

	/**
	 * 删除某条信息
	 * @param id
	 */
	public Map<String,Object> delAppInformation(Integer id);

	/**
	 * @param request
	 * @param id
	 * @param aScore
	 * @param aDownloads
	 * @param aSize
	 * @param aDirectDownload
	 * @param aDirectUrl
	 * @param aMarketDownload
	 * @param aMarketUrl
	 * @param aVersion
	 * @param iScore
	 * @param iDownloads
	 * @param iMarketDownload
	 * @param iMarketUrl
	 * @param appVersion
	 * @param describe
	 * @param iNewVersionContents
	 * @param appType
	 * @return
	 */
	public Map<String, Object> save(HttpServletRequest request, Integer id,
			String aScore, String aDownloads, String aSize,
			String aDirectDownload, String aDirectUrl, String aMarketDownload,
			String aMarketUrl, String aVersion, String iScore,
			String iDownloads, String iMarketDownload, String iMarketUrl,
			String appVersion, String describe, String iNewVersionContents,
			Integer appType,String ISize);

}