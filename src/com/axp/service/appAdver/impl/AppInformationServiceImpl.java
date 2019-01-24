package com.axp.service.appAdver.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.impl.ReGoodsofextendmallDaoImpl;
import com.axp.model.AccountReceive;
import com.axp.model.AdminUser;
import com.axp.model.AppInformation;
import com.axp.model.Coin;
import com.axp.model.Extendlimits;
import com.axp.model.Goods;
import com.axp.model.ProvinceEnum;
import com.axp.model.Websites;
import com.axp.service.appAdver.AppInformationService;
import com.axp.service.appAdver.IAdverService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class AppInformationServiceImpl extends BaseServiceImpl implements AppInformationService{

	
	@Override
	public void findAppInformationList(HttpServletRequest request,HttpServletResponse response) {
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		List<AppInformation> list = dateBaseDao.findLists(AppInformation.class, model);
		request.setAttribute("list", list);
	}

	
	@Override
	public void findAppInformation(HttpServletRequest request,
			HttpServletResponse response, Integer id) {
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		model.combPreEquals("id", id);
		AppInformation app = (AppInformation) dateBaseDao.findOne(AppInformation.class, model);
		request.setAttribute("app", app);
		
	}


	
	@Override
	public void editAppInformation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> delAppInformation(Integer id) {
		 Map<String, Object> returnMap = new HashMap<>();
		QueryModel model = new QueryModel();
		model.combPreEquals("isValid", true);
		model.combPreEquals("id", id);
		AppInformation app = (AppInformation) dateBaseDao.findOne(AppInformation.class, model);
		if(app != null){
			app.setIsValid(false);
			app.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			appInformationDao.saveOrUpdate(app);
			returnMap.put("success", "success");
		}else{
			returnMap.put("false", "false");
		}
		return returnMap;
	}


	


	/* (non-Javadoc)
	 * @see com.axp.service.appAdver.AppInformationService#save(javax.servlet.http.HttpServletRequest, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Map<String, Object> save(HttpServletRequest request, Integer id,
			String aScore, String aDownloads, String aSize,
			String aDirectDownload, String aDirectUrl, String aMarketDownload,
			String aMarketUrl, String aVersion, String iScore,
			String iDownloads, String iMarketDownload, String iMarketUrl,
			String appVersion, String describe, String iNewVersionContents,
			Integer appType,String ISize) {
		 Map<String, Object> returnMap = new HashMap<>();
		try {
			AppInformation  app = null;
			if(id==null){ //新增信息
				app = new AppInformation();
				
				
			}else{  //更新对应的信息
				QueryModel model = new QueryModel();
				model.combPreEquals("isValid", true);
				model.combPreEquals("id", id);
				app = (AppInformation) dateBaseDao.findOne(AppInformation.class, model);
			}
			app.setAScore(Float.valueOf(aScore));
			app.setADownloads(Integer.valueOf(aDownloads));
			app.setASize(Float.valueOf(aSize));
			app.setADirectDownload(aDirectDownload);
			app.setADirectUrl(aDirectUrl);
			app.setAMarketDownload(aMarketDownload);
			app.setAMarketUrl(aMarketUrl);
			app.setAVersion(aVersion);
			
			app.setIScore(Float.valueOf(iScore));
			app.setIDownloads(Integer.valueOf(iDownloads));
			app.setIMarketDownload(iMarketDownload);
			app.setIMarketUrl(iMarketUrl);
			app.setAppVersion(appVersion);
			app.setDescribe(describe);
			app.setINewVersionContents(iNewVersionContents);
			app.setAppType(appType);
			app.setISize(Float.valueOf(ISize));
			app.setIsValid(true);
			app.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			appInformationDao.saveOrUpdate(app);
			returnMap.put("success", "success");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMap.put("false", "false");
		}
		
		return returnMap;
	}


	
	
	
	
	
	
	
	
}
 