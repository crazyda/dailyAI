package com.axp.service.professional.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.AdminUserScoreRecordDAO;
import com.axp.dao.AdminUserZonesDAO;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.ScoreMarkDAO;
import com.axp.dao.SellerAccountNumberDAO;
import com.axp.dao.SellerDAO;
import com.axp.dao.SellerMainPageDAO;
import com.axp.dao.ZonesDAO;
import com.axp.dao.impl.SellerMainPageDAOImpl;
import com.axp.model.AdminUser;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminUserZones;
import com.axp.model.ProvinceEnum;
import com.axp.model.ScoreMark;
import com.axp.model.Seller;
import com.axp.model.SellerAccountNumber;
import com.axp.model.SellerMainPage;
import com.axp.model.Shoptypes;
import com.axp.model.UserDarw;
import com.axp.model.Zones;
import com.axp.service.professional.UserDarwService;
import com.axp.service.professional.UserService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service("userDarwService")
public class UserDarwServiceImpl extends BaseServiceImpl implements UserDarwService{

	
	@Override
	public void changeSurplus() {
		List <UserDarw> userDarwList = userDarwDAO.findAll();
		int drawNum = userDarwList.get(0).getDrawNum();
		for(UserDarw u:userDarwList){
			u.setSurplus(drawNum);
			
			userDarwDAO.saveOrUpdate(u);
		}
		
	}

	
	@Override
	public UserDarw getById(Integer id) {
		
		return userDarwDAO.findById(id);
	}

	@Override
	public Map<String, Object> saveUserDarw(List<String> coverPics,HttpServletRequest request,
			HttpServletResponse response) {
		String drawNum = request.getParameter("drawNum")==""?"10":request.getParameter("drawNum"); 
		String drawScore=request.getParameter("drawScore")==""?"1":request.getParameter("drawScore");
		//轮播广告
		String details = request.getParameter("details");
		Map<String,Object> statMap = new HashMap<String,Object>();
		//保存5张图片
		try {
			List<Map<String, Object>> coverList = new ArrayList<>();
			for (int i = 0; i < coverPics.size(); i++) {
			    String each = coverPics.get(i).replaceAll("\\\\", "/");
			    if (StringUtil.hasLength(each)) {
			        Map<String, Object> map = new HashMap<>();
			        map.put("imgUrl", each);
			        coverList.add(map);
			    }
			}
			
			UserDarw userdarw = userDarwDAO.findById(1); //保存活动信息, 几个人参加,几积分,
			userdarw.setDrawNum(Integer.valueOf(drawNum));
			userdarw.setScore(Integer.valueOf(drawScore));
			userdarw.setDetails(details);
			userdarw.setCoverPic(JSONObject.toJSONString(coverList));
			userDarwDAO.saveOrUpdate(userdarw);
			statMap.put("result", "success");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			statMap.put("result", "false");
		}
		
		return statMap;
	}
	
	
	
	
	
	
}
