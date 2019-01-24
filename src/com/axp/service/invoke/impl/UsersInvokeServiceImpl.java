package com.axp.service.invoke.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.DataBaseDao;
import com.axp.model.Captcha;
import com.axp.model.JphScorerecords;
import com.axp.model.Members;
import com.axp.model.UserAddressConfig;
import com.axp.model.Users;
import com.axp.service.invoke.UsersInvokeService;
import com.axp.util.CalcUtil;
import com.axp.util.DateUtil;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class UsersInvokeServiceImpl extends BaseServiceImpl implements UsersInvokeService{
	@Override
	public String login(String name, String pwd, String inviteCode, 
			String latitude, String longitude, HttpServletRequest request){

		String commonReasult = "";

		if (StringUtils.isBlank(name) || StringUtils.isBlank(pwd)) {
			commonReasult = "[{\"status\":\"-1\"}]";
			return commonReasult;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[{\"status\":\"1\"}");
		Users user = usersDAO.findByLoginName(name);
		if (user!=null) {
			if (!Utility.MD5(pwd).equals(user.getPwd())) {
				commonReasult = "[{\"status\":\"-2\"}]";
				return commonReasult;
			}
			//项目基本路径
			String basePath = request.getServletContext().getAttribute("BASEPATH_IN_SESSION").toString();
			//安卓app路径
			String surl = StringUtil.getAPPurl(basePath);
			int score = user.getScore();
			int applystatus = 0;
			name = user.getName();
			String qr = StringUtil.getQr(basePath);
			List<Users> myfans = usersDAO.findFans(user);
			String inviteCount = myfans.size() + "";
			String cashpoint = user.getCashPoints() == null ? "" : user.getCashPoints().toString();
			String money = user.getMoney() == null ? "" : user.getMoney().toString();
			String phone = user.getPhone() == null ? "" : user.getPhone();
			String address = user.getAddress() == null ? "" : user.getAddress();
			String realname = user.getRealname() == null ? "" : user.getRealname();
			String invitecode = user.getInvitecode();
			String level = user.getLevel() == null ? "" : user.getLevel().toString();
			String birthday = user.getBirthday() == null ? "" : user.getBirthday().toString();
			String sex = user.getSex() == null ? "" : user.getSex().toString();
			String imgUrl = user.getImgUrl() == null ? "" : (basePath + user.getImgUrl());
			
			//网络电话废除
			//===========
			if (user.getMycode() == null || "".equals(user.getMycode())) {
				user.setMycode("2" + user.getId());
				usersDAO.update(user);
			}
			
			user.setRealname("hzc123");
			usersDAO.update(user);
			
			int maxscore = -1;
			int proxy_user_id = -1;
			int isVisitor = 0;
			String provincialUrbanAreas = "";
			String provincialUrbanAreasId = "";
			String defaulAddress = "";
			String defaulName = "";
			String defaulPhone = "";
			
			String promoterId = "";
			String commission = "";
			
			UserAddressConfig userAddressConfig = userAddressConfigDAO.findUserDefinedAddress(user.getId());
			if (userAddressConfig!=null) {
				provincialUrbanAreas = userAddressConfig.getProvincialUrbanAreas();
				provincialUrbanAreasId = userAddressConfig.getId()+"";
				defaulAddress = userAddressConfig.getAddress();
				defaulName = userAddressConfig.getRecipients();
				defaulPhone = userAddressConfig.getRecipientsPhone();
			}
			
			//会员属性
			String vipType = "10";
			String vipChar = "";
			Members vip = membersDAO.findByUserId(user.getId());
			if(vip!=null){
				vipType = vip.getMembersTypeIncome().getId()+"";
				vipChar = vip.getMembersTypeIncome().getTypeName();
			}
			
			//免单券数
			int count = freeVoucherRecordDAO.getUsersValidFVCount(user.getId());
			
			sb.append(",{\"user_id\":\"" + user.getId() + "\",");
			sb.append("\"score\":\"" + score + "\",");
			sb.append("\"name\":\"" + name + "\",");
			sb.append("\"phone\":\"" + phone + "\",");
			sb.append("\"level\":\"" + level + "\",");
			sb.append("\"address\":\"" + address + "\",");
			sb.append("\"isVisitor\":\"" + isVisitor + "\",");
			sb.append("\"Invitecode\":\"" + user.getMycode() + "\",");
			sb.append("\"realname\":\"" + realname + "\",");
			sb.append("\"maxScore\":\"" + 4000 + "\",");
			sb.append("\"money\":\"" + money + "\",");
			sb.append("\"cashpoint\":\"" + cashpoint + "\",");
			sb.append("\"isShow\":\"" + 1 + "\",");//临时限制会员商城的显示
			
			sb.append("\"promoterId\":\"" + promoterId + "\",");
			sb.append("\"commission\":\"" + commission + "\",");
			
			sb.append("\"provincialUrbanAreasId\":\"" + provincialUrbanAreasId + "\",");
			sb.append("\"provincialUrbanAreas\":\"" + provincialUrbanAreas + "\",");
			sb.append("\"defaulAddress\":\"" + defaulAddress + "\",");
			sb.append("\"defaulName\":\"" + defaulName + "\",");
			sb.append("\"defaulPhone\":\"" + defaulPhone + "\",");
			
			sb.append("\"vipType\":\"" + vipType + "\",");
			sb.append("\"vipChar\":\"" + vipChar + "\",");
			sb.append("\"freeVoucherCount\":\""+count+"\",");
			sb.append("\"imgUrl\":\"" + imgUrl + "\",");
			sb.append("\"surl\":\"" + surl + "\",");
			sb.append("\"visitorId\":\"\",");
			sb.append("\"qr\":\"" + qr + "\",");
			sb.append("\"version\":\"" + 38 + "\",");
			sb.append("\"invite_count\":\"" + inviteCount + "\",");
			sb.append("\"birthday\":\"" + birthday + "\",");
			sb.append("\"sex\":\"" + sex + "\",");
			sb.append("\"applystatus\":\"" + applystatus + "\",");
			sb.append("\"ccpSubAccountId\":\"" + user.getCcpsubaccountid() + "\",");
			sb.append("\"ccpSubAccountPwd\":\"" + user.getCcpsubaccountpwd() + "\",");
			sb.append("\"ccpVoipAccountId\":\"" + user.getCcpvoipaccountid() + "\",");
			sb.append("\"ccpVoipAccountPwd\":\"" + user.getCcpvoipaccountpwd() + "\"");
			sb.append("}]");
			commonReasult = sb.toString();
			
//			try{
//				//保存所属商家
//				MembersServiceImpl util = MembersServiceImpl.getInstance();
//				util.changeParentSeller(user);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			
		} else {
			commonReasult = "[{\"status\":\"-3\"}]";
		}
		
		return commonReasult;
	}

	@Override
	public List<Users> saveUserGoldList(HttpServletRequest request, String phone,
			Integer gold) {
		
		 QueryModel model = new QueryModel();
		 model.clearQuery();
		 model.combCondition("phone <> ''");
		
		 model.setOrder("lasttime desc");
		 if(phone != "" && gold != null){
			 model.combPreEquals("phone", phone);
			List<Users> users = dateBaseDAO.findLists(Users.class, model);
			 if(users != null){
				Users user = users.get(0);
				user.setJphScore((user.getJphScore()==null?0:user.getJphScore())+gold);
				user.setLasttime(new Timestamp(System.currentTimeMillis()));
				usersDAO.saveOrUpdate(user);
				
				
				JphScorerecords scorerecords = new JphScorerecords();
				scorerecords.setBeforeScore((int)CalcUtil.sub(user.getScore(), gold));
				scorerecords.setAfterScore(user.getScore());
				scorerecords.setIsvalid(true);
				scorerecords.setCreatetime(new Timestamp(System.currentTimeMillis()));
				scorerecords.setValidityTime(new Timestamp(DateUtil.addDay2Date(180, new Date()).getTime()));
				scorerecords.setScore(gold);
				scorerecords.setScoretype("平台赠送金币");
				scorerecords.setRemark("平台赠送金币,可用于1:1兑换积分或者转增别人");
				scorerecords.setType(17);
				//scorerecords.setAdminuserId();
				//scorerecords.setForeignId(Integer.parseInt(presenterId));
				scorerecords.setUsers(user);
				jphScorerecordsDao.save(scorerecords);
				
				
			 }
			 
		 }
		 String pagestr = request.getParameter("pageindex");//分页框架每次查询传过来当前是第几页，页面第一次加载为空
		 int count = dateBaseDAO.findCount(Users.class, model);
		 
		 PageInfo pageInfo = new PageInfo();
		 Utility.setPageInfomation(pageInfo, pagestr, "20", count);//第三个参数每页显示多少条。
		 int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();//页面刚进来getCurrentPage = 0 点击下一页 为 1
		 int end = pageInfo.getPageSize();//mysql 分页limit 10 10 从第10条记录开始查询 查10条记录
		 request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		 
		 List<Users> userlist = dateBaseDAO.findPageList(Users.class, model, start, end);
		 request.setAttribute("list", userlist);
		return userlist;
		 
		 
	}
}