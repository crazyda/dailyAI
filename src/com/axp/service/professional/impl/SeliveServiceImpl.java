package com.axp.service.professional.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.AdminUserDAO;
import com.axp.dao.SeliveDao;
import com.axp.dao.SellerDAO;
import com.axp.model.AdminUser;
import com.axp.model.ProvinceEnum;
import com.axp.model.SeLive;
import com.axp.model.Seller;
import com.axp.service.professional.SeliveService;
import com.axp.service.professional.SellerService;
import com.axp.service.system.AdminUserService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;

@Service("SeliveService")
public class SeliveServiceImpl extends ProfessionalServiceImpl implements SeliveService{
	
	@Resource
	private SeliveDao seliveDao;
	@Resource
	private SellerDAO sellerDAO;
	@Resource 
	private SellerService sellerService;
	@Resource
	private AdminUserDAO adminUserDAO;
	@Resource
	private AdminUserService adminUserService;
	@Override
	public List<ProvinceEnum> getZoneById(Integer id, Integer level) {
		return null;
	}
	@Override
	public SeLive findById(Integer seLiveId) {
		return seliveDao.findById(seLiveId);
	}
	@Override
	public void del(String id) {
		seliveDao.del(id);
	}
	@Override
	public void getPageList(HttpServletRequest request,
			HttpServletResponse response) {
		String id = (String)request.getParameter("live_id");
		String search_name = (String)request.getParameter("search_name");
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
        List<Seller> sellerlist =sellerDAO.getSellerListByAdminId(current_user_id);
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);
        Integer current_level= adminUser.getLevel();
        Seller seller =null;;
		if(sellerlist!=null&&sellerlist.size()>0)
			seller = sellerlist.get(0);
		StringBuffer param = new StringBuffer();//页码条件
	    QueryModel model = new QueryModel().setOrder("istop desc,id desc");
		model.combPreEquals("id", id );
		model.combPreEquals("isvalid", true);
		model.combPreEquals("adminUser.id", current_user_id, "adminUserId");
		//model.orCondition("adminUser.level < "+current_level);
		model.combCompare("adminUser.level", current_level, 2);
		 
		//id
	    if(!StringUtils.isEmpty(id )){
	    	model.combPreEquals("id", Integer.parseInt(id ));
	    	param.append("&live_id="+id );
	    }
	    //搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("sellerName like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
	    String pagestr = request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(SeLive.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<SeLive> selivelist = dateBaseDao.findPageList(SeLive.class, model, start, end);
        request.setAttribute("seller", seller);
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("selivelist", selivelist);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	@Override
	public void add(HttpServletRequest request,Integer id) {
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		Seller seller = null;
		AdminUser current_user = adminUserDAO.findById(current_user_id);
		
		request.setAttribute("level", current_user.getLevel());
		
		
		if(id!=null){
			SeLive sl = seliveDao.findById(id);
			request.setAttribute("seLive", sl);
		}
		request.setAttribute("seller", seller);
		
		
	}
	@Override
	public void save(Integer id, Integer sId,String livename, String image, String liveUri,
			String sellerName, String sellerAddress, String sellerLogo,String remark,
			 Timestamp begintime,Timestamp endtime,String imgRecommend,
			HttpServletRequest request, HttpServletResponse response,String name,String istop) {
		int current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		AdminUser current_user = adminUserDAO.findById(current_user_id);
		SeLive se=null;
		if(id!=null){
			se = seliveDao.findById(id);
		}else{
			se=new SeLive();
			
		}
		
		Seller seller =null;
		if(current_user.getLevel()==60){
			List<Seller> selist = sellerDAO.findByCon(Seller.class, "adminUser.id="+current_user_id);
			if(selist.size()>0){
				seller=selist.get(0);
			}
		}
		if("1".equals(istop)){
			se.setIstop(true);
		}else{
			se.setIstop(false);
		}
		
		se.setAdminUser(current_user);		
		se.setSellerId(seller==null?0:seller.getId());
		se.setSeller(seller);
		se.setBegintime(begintime);
		se.setEndtime(endtime);
		se.setLivename(livename);
		se.setLiveUri(liveUri);
		se.setRemark(remark);
		se.setSellerAddress(sellerAddress);
		se.setSellerLogo(sellerLogo);
		se.setAdminuserId(current_user_id);
		se.setSellerName(sellerName);
		se.setImage(image);
		se.setImgRecommend(imgRecommend);
		se.setIsvalid(true);
		seliveDao.saveOrUpdate(se);
	}
	@Override
	public void ajaxTop(Integer sid,boolean istop) {
		seliveDao.ajaxTop(sid,istop);
	}

	
}
