package com.axp.action.adver;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.dao.CoinDAO;
import com.axp.dao.IBuyDao;
import com.axp.dao.impl.ReGoodsofextendmallDaoImpl;
import com.axp.model.AdminUser;
import com.axp.model.Buy;
import com.axp.model.ProvinceEnum;
import com.axp.service.appAdver.IAdverCheckService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;


@Controller
@RequestMapping("adverCheck")
public class AdverCheckAction extends BaseAction{

	@Autowired
	private IBuyDao buyDao;
	@Autowired
	private CoinDAO coinDAO;
	@Autowired
	private IAdverCheckService adverCheckService;
	
	
    @IsItem(firstItemName = "广告管理", secondItemName = "广告天数审核")
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		int current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		StringBuffer sb = new StringBuffer();
     	AdminUser user = adminUserDao.findById(current_user_id);
     	ProvinceEnum provinceEnum = user.getProvinceEnum();
		String con ="isvalid=true and status=1 ";
		QueryModel queryModel =new QueryModel();

        String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		int count = 0;
	
		queryModel.clearQuery();
		queryModel.combCondition(con);
		if(user.getLevel()>=75&&user.getLevel()<95){
			if (provinceEnum.getLevel()==1) {
				sb.append("( adminUser.provinceEnum.id="+provinceEnum.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id="+provinceEnum.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.provinceEnum.id="+provinceEnum.getId()).
				append(")");
			}else if(provinceEnum.getLevel()==2){
				sb.append("( adminUser.provinceEnum.id="+provinceEnum.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id="+provinceEnum.getId()+"" +" and '区'=SUBSTRING(REVERSE(adminUser.provinceEnum.name),1,1)").
				append(")");
			}else{
				sb.append("( adminUser.provinceEnum.id="+provinceEnum.getId()).
				append(")");
			}
			queryModel.combCondition(sb.toString());
		}
		count = dateBaseDao.findCount(Buy.class, queryModel);
	    
		Utility.setPageInfomation(pageInfo, pagestr, "20",count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		pageInfo.setParam("&page=");
		List<Buy> buylist = dateBaseDao.findPageList(Buy.class, queryModel, start, end);
		Hashtable<Integer, String> quantityHash = StringUtil.getQuantityHash();
		request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
		request.setAttribute("type", 1);
		request.setAttribute("buylist", buylist);
		request.setAttribute("quantityHash", quantityHash);
		return "adverCheck/adminlist";
	}
	

    @RequestMapping("confirm")
    public String confirm(HttpServletRequest request){
		adverCheckService.confirm(request);	
		return "redirect:list";
		
	}
    
    @RequestMapping("del")
    public String del(HttpServletRequest request){
		adverCheckService.del(request);
		return "redirect:list";
	}
}
