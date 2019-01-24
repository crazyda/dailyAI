package com.axp.action.system;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.CommissionRateDao;
import com.axp.dao.IBaseinfosDao;
import com.axp.model.AdminUser;
import com.axp.model.Baseinfos;
import com.axp.model.CommissionRate;
import com.axp.util.QueryModel;
import com.axp.util.ResponseResult;
import com.axp.util.Utility;

@Controller
@RequestMapping("baseinfo")
public class BaseInfoAction extends BaseAction {
	@Autowired
	private IBaseinfosDao baseinfosDao;
	@Autowired
	private AdminUserDAO adminUserDao;
	
	@Autowired
	private CommissionRateDao commissionRateDao;
	
	@NeedPermission(permissionName = "关于我们", classifyName = "系统管理")
	@IsItem(firstItemName = "系统管理", secondItemName = "关于我们")
	@RequestMapping("add")
    public String add(HttpServletRequest request){
		QueryModel model = new QueryModel().setOrder("id desc");
		model.combPreEquals("isvalid", true);
		List<Baseinfos> bilst = dateBaseDao.findPageList(Baseinfos.class, model, 0, 1);
		Baseinfos bi = null;
		if (bilst.size() > 0) {
			bi = bilst.get(0);
		}
		request.setAttribute("sc", bi);
		return "baseinfo/add";
	}
	

	@NeedPermission(permissionName = "分佣比例", classifyName = "系统管理")
	@IsItem(firstItemName = "系统管理", secondItemName = "分佣比例")
	@RequestMapping("/toCommissionRate ")
    public String commissionRate (HttpServletRequest request){
			request.setAttribute("CommissionRate", commissionRateService.findCommissionRate());
		return "baseinfo/commissionRate";
	}
	
	@NeedPermission(permissionName = "分佣比例", classifyName = "系统管理")
	@RequestMapping("/updateCommissionRate ")
	@ResponseBody
    public ResponseResult updateCommissionRate (HttpServletRequest request,CommissionRate commissionRate){
		ResponseResult result=new ResponseResult();
		try {
			commissionRate.setCreateTime(new Timestamp(System.currentTimeMillis()));
			commissionRate.setIsValid(true);
			commissionRateDao.save(commissionRate);
			request.setAttribute("CommissionRate",commissionRate);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
		}
		return result;
	}
	
	
	
	@RequestMapping("save")
	public String save(Integer id, String content, String linkurl, String agreement, HttpServletRequest request) {
		Baseinfos sc = new Baseinfos();
		if(id>0){
			sc = baseinfosDao.findById(id);
		}
		sc.setContent(content);
		sc.setLinkurl(linkurl);
		sc.setIsvalid(true);
		sc.setAgreement(agreement);
		sc.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		baseinfosDao.save(sc);	
		return "redirect:add";
	}
	
	@NeedPermission(permissionName = "修改密码", classifyName = "系统管理")
	@IsItem(firstItemName = "系统管理", secondItemName = "修改密码")
	@RequestMapping("updatePwd")
	public String updatePwd(HttpServletRequest request) {
		return "baseinfo/pwd";
	}
	
	@RequestMapping("savePwd")
	public String savePwd(String old_pwd, String new_pwd1,String new_pwd2, HttpServletRequest request) {
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		AdminUser user = adminUserDao.findById(current_user_id);
		if(!user.getPassword().equals(Utility.MD5(old_pwd))){
			return "error";
		}
		if(!new_pwd1.equals(new_pwd2)){
			return "error";
		}
		user.setPassword(Utility.MD5(new_pwd1));
		adminUserDao.update(user);	
		return "baseinfo/success";
	}
	
	
}
