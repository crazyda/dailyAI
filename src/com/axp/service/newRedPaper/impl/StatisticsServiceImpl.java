package com.axp.service.newRedPaper.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.form.newRedPaper.NewRedPaperTotal;
import com.axp.model.NewRedPaperSetting;
import com.axp.model.Seller;
import com.axp.service.newRedPaper.StatisticsService;
import com.axp.util.DateUtil;
import com.axp.util.NewRedPaperCountUtil;
import com.axp.util.QueryModel;

@Service("statisticsService")
public class StatisticsServiceImpl extends BaseNewRedPaperServiceImpl implements StatisticsService{
	/**
     * 获取统计红包数据列表
     * @param sb    查询语句
     * @param objects	查询参数
     * @return		统计列表
     */
	@Override
	public List<NewRedPaperTotal> newRedPaperCountList(String search_id,String search_name,String sellerId,String allianceId,String cityId,String centerId) {
		QueryModel qm = new QueryModel();
		StringBuffer sb =new StringBuffer("select n.* from new_red_paper_setting n ");
		List<Object> objects =new  ArrayList<Object>();
		if(StringUtils.isNotBlank(search_id)||StringUtils.isNotBlank(search_name)){		
		
			if(search_id !=null){
				sb.append(" where sellerId = ? ");
				objects.add(search_id);
				
			}else if(search_name != null){
				qm.clearQuery();
				qm.combLike("name", search_name);
				qm.combPreEquals("isvalid",true);
				Seller s = dateBaseDao.findLists(Seller.class, qm).get(0);
				if(s !=null){
				sb.append(" where sellerId = ?");
				objects.add(s.getId());
				}else{
					sb.append(" where 1=2");
				}
			}else{
				sb.append(" where 1=2");
			}
		}else{	
			
			
			if(StringUtils.isNotBlank(sellerId)){
				if(allianceId==null){
					if(cityId==null){
						if(centerId==null){
						
						}else{
							sb.append("INNER JOIN (SELECT s.id FROM seller s INNER JOIN (SELECT ad1.id FROM admin_user  ad1 INNER JOIN admin_user ad2 WHERE ad1.parent_id = ad2.id AND ad2.id = ?) a WHERE s.adminuser_id  =a.id  ) AS tj WHERE n.sellerId =tj.id ");
							objects.add(centerId);
						}
					}else{
						sb.append(" INNER JOIN (SELECT s.id FROM seller s INNER JOIN admin_user a WHERE s.adminuser_id  =a.id AND a.parent_id=?) AS tj WHERE n.sellerId =tj.id ");
						objects.add(cityId);
					}
				}else{
					sb.append(" INNER JOIN (SELECT s.id FROM seller s INNER JOIN admin_user a WHERE s.adminuser_id  =a.id AND a.id=?) AS tj WHERE n.sellerId =tj.id ");
					objects.add(allianceId);
				}
			}else{
				try{
					centerId.length();
					sb.append(" where sellerId = ? ");
					objects.add(sellerId);
				}catch(Exception e){
					centerId = "";
					cityId = "";
					allianceId = "";
					sellerId = "";
					sb.append(" where 1 = 1 ");
				}

			}
		}
		
		
		//拿到今天的记录
		List<NewRedPaperSetting> todayList = newRedPaperSettingDAO.findTotalSetting(sb, objects,new Timestamp(DateUtil.getDayStart(new Date()).getTime()),new Timestamp(DateUtil.getDayEnd(new Date()).getTime()));			
		//拿到本周记录
		//拿到周一
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		c.set(Calendar.HOUR_OF_DAY, 0);    
		c.set(Calendar.MINUTE, 0);  
		c.set(Calendar.SECOND,0);   
		c.set(Calendar.MILLISECOND, 0); 
		c.add(Calendar.DATE, -day_of_week + 1);
		//拿到周末
		Calendar ca = Calendar.getInstance();
		day_of_week = ca.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		ca.set(Calendar.HOUR_OF_DAY, 0);    
		ca.set(Calendar.MINUTE, 0);  
		ca.set(Calendar.SECOND,0);   
		ca.set(Calendar.MILLISECOND, -1); 
		ca.add(Calendar.DATE, -day_of_week + 8);
		List<NewRedPaperSetting> weekList = newRedPaperSettingDAO.findTotalSetting(sb, objects, new Timestamp(c.getTimeInMillis()), new Timestamp(ca.getTimeInMillis()));
		//拿到本月最后一天
		Calendar month = Calendar.getInstance();
		month.setTime(new Date());
		int day_of_month = month.get(Calendar.DAY_OF_MONTH);
		month.set(Calendar.DAY_OF_MONTH, 1);
		month.set(Calendar.HOUR_OF_DAY, 0);
		month.set(Calendar.MINUTE, 0);
		month.set(Calendar.SECOND, 0);
		month.set(Calendar.MILLISECOND, -1); 
		month.set(Calendar.MONTH,day_of_month-1);
		List<NewRedPaperSetting> monthList = newRedPaperSettingDAO.findTotalSetting(sb, objects, new Timestamp(DateUtil.getMonthDayStart(new Date()).getTime()), new Timestamp(month.getTimeInMillis()));
		
		NewRedPaperCountUtil newRedPaperCountUtil = new NewRedPaperCountUtil();
		List<NewRedPaperTotal> tList  = new ArrayList<NewRedPaperTotal>();
		tList.add(newRedPaperCountUtil.getTotal("今天",todayList,new Timestamp(DateUtil.getDayStart(new Date()).getTime())));	
		
		tList.add(newRedPaperCountUtil.getTotal("本周", weekList, new Timestamp(c.getTimeInMillis())));
		
		tList.add(newRedPaperCountUtil.getTotal("本月", monthList, new Timestamp(DateUtil.getMonthDayStart(new Date()).getTime())));
		
		return tList;
	}

	@Override
	public List<NewRedPaperTotal> newRedPaperCountList(
			HttpServletRequest request) {
		int current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		QueryModel qm = new QueryModel();
		StringBuffer sb =new StringBuffer("select n.* from new_red_paper_setting n where n.adminUserId = ?");
		List<Object> objects =new  ArrayList<Object>();
		objects.add(current_user_id);
		//拿到今天的记录
		List<NewRedPaperSetting> todayList = newRedPaperSettingDAO.findTotalSetting(sb, objects,new Timestamp(DateUtil.getDayStart(new Date()).getTime()),new Timestamp(DateUtil.getDayEnd(new Date()).getTime()));			
		//拿到本周记录
		//拿到周一
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		c.set(Calendar.HOUR_OF_DAY, 0);    
		c.set(Calendar.MINUTE, 0);  
		c.set(Calendar.SECOND,0);   
		c.set(Calendar.MILLISECOND, 0); 
		c.add(Calendar.DATE, -day_of_week + 1);
		//拿到周末
		Calendar ca = Calendar.getInstance();
		day_of_week = ca.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
	    day_of_week = 7;
	    ca.set(Calendar.HOUR_OF_DAY, 0);    
	    ca.set(Calendar.MINUTE, 0);  
	    ca.set(Calendar.SECOND,0);   
	    ca.set(Calendar.MILLISECOND, -1); 
	    ca.add(Calendar.DATE, -day_of_week + 8);
	    List<NewRedPaperSetting> weekList = newRedPaperSettingDAO.findTotalSetting(sb, objects, new Timestamp(c.getTimeInMillis()), new Timestamp(ca.getTimeInMillis()));
	    //拿到本月最后一天
	    Calendar month = Calendar.getInstance();
	    month.setTime(new Date());
	    int day_of_month = month.get(Calendar.DAY_OF_MONTH);
	    month.set(Calendar.DAY_OF_MONTH, 1);
	    month.set(Calendar.HOUR_OF_DAY, 0);
	    month.set(Calendar.MINUTE, 0);
	    month.set(Calendar.SECOND, 0);
	    month.set(Calendar.MILLISECOND, -1); 
	    month.set(Calendar.MONTH,day_of_month-1);
	    List<NewRedPaperSetting> monthList = newRedPaperSettingDAO.findTotalSetting(sb, objects, new Timestamp(DateUtil.getMonthDayStart(new Date()).getTime()), new Timestamp(month.getTimeInMillis()));
	    
	    NewRedPaperCountUtil newRedPaperCountUtil = new NewRedPaperCountUtil();
	    List<NewRedPaperTotal> tList  = new ArrayList<NewRedPaperTotal>();
	    tList.add(newRedPaperCountUtil.getTotal("今天",todayList,new Timestamp(DateUtil.getDayStart(new Date()).getTime())));	
	    
	    tList.add(newRedPaperCountUtil.getTotal("本周", weekList, new Timestamp(c.getTimeInMillis())));
	    
	    tList.add(newRedPaperCountUtil.getTotal("本月", monthList, new Timestamp(DateUtil.getMonthDayStart(new Date()).getTime())));
	    	
	    return tList;
	}
}
