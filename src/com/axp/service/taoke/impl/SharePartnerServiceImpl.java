package com.axp.service.taoke.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.SharePartner;
import com.axp.model.TkldPid;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.service.taoke.SharePartnerService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;





@Service("sharePartnerService")
public class SharePartnerServiceImpl extends BaseServiceImpl implements SharePartnerService {

	@Override
	public void findSharePartnerList(HttpServletRequest request) {
		String pagestr=request.getParameter("page");
		Integer adminUserId =Integer.parseInt( request.getSession().getAttribute("currentUserId").toString());
		AdminUser adminUser = adminUserDAO.findById(adminUserId);
		
		StringBuffer param = new StringBuffer();
		QueryModel queryModel=new QueryModel();
		queryModel.combPreEquals("isValid", true);
		queryModel.combPreEquals("isCheck", false);
		queryModel.setOrder("id desc");
		if(adminUser.getLevel()<95){
			if(adminUser.getProvinceEnum()!=null&&adminUser.getProvinceEnum().getLevel()==3){ //区级
				queryModel.combCondition("provinceEnum.id="+adminUser.getProvinceEnum().getId()); 
			}else if(adminUser.getProvinceEnum()!=null&&adminUser.getProvinceEnum().getLevel()==2){//市级
					queryModel.combCondition("provinceEnum.id="+adminUser.getProvinceEnum().getId()+
							" or provinceEnum.provinceEnum.id="+adminUser.getProvinceEnum().getId());
			}else if(adminUser.getProvinceEnum()!=null&&adminUser.getProvinceEnum().getLevel()==1){ //直辖市 省级
						queryModel.combCondition("provinceEnum.id="+adminUser.getProvinceEnum().getId()+""
								+ " or provinceEnum.provinceEnum.id="+adminUser.getProvinceEnum().getId()+""
										+ " or provinceEnum.provinceEnum.provinceEnum.id="+adminUser.getProvinceEnum().getId());	
			}else{
				queryModel.combCondition("1<>1");  //无结果集
			}
			
		}
	
		PageInfo pageInfo=new PageInfo();
		Integer count= dateBaseDao.findCount(SharePartner.class, queryModel);
		 Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		   int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		     int end = pageInfo.getPageSize();
		     pageInfo.setParam(param + "&page=");
		     List<SharePartner> list = dateBaseDao.findPageList(SharePartner.class, queryModel, start,end ); 
		     request.setAttribute("adminUser", adminUser);
			 request.setAttribute("count", count);
		     request.setAttribute("list", list);
		     request.setAttribute("page", pagestr);
		     request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
		
}
	

