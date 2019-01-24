package com.axp.service.appAdver.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.dao.impl.ReGoodsofextendmallDaoImpl;
import com.axp.model.AdminUser;
import com.axp.model.Coin;
import com.axp.model.Extendlimits;
import com.axp.model.Goods;
import com.axp.model.ProvinceEnum;
import com.axp.model.Websites;
import com.axp.service.appAdver.IAdverService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class AdverServiceImpl extends BaseServiceImpl implements IAdverService{
	
	@Override
	public void getPageList(HttpServletRequest request, HttpServletResponse response){
		String good_id = (String)request.getAttribute("good_id");
		String search_name = (String)request.getParameter("search_name");
		Integer current_user_id = (Integer)request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDAO.findById(current_user_id);
		ProvinceEnum enum1 = adminUser.getProvinceEnum();
		StringBuffer param = new StringBuffer();//页码条件
	    QueryModel model = new QueryModel().setOrder("id desc");
		model.combPreEquals("type", 2);
		model.combPreEquals("isvalid", true);
		model.combPreEquals("adminUser.id", current_user_id, "adminUserId");
		StringBuffer sb = new StringBuffer();
		if (adminUser.getLevel()<95) {
			sb.append("( adminUser.id="+adminUser.getId()).
			append(" or adminUser.users.id="+adminUser.getId()).
			append(")");
			model.combCondition(sb.toString());
		}
		/*if (adminUser.getLevel()>=75&&adminUser.getLevel()<95) {
			if (enum1.getLevel()==3) {
				param.append("( adminUser.provinceEnum.id="+enum1.getId()).
				append(")");
				
			}else if(enum1.getLevel()==2){
				param.append("( adminUser.provinceEnum.id="+enum1.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id="+enum1.getId()+"" +" and '区'=SUBSTRING(REVERSE(adminUser.provinceEnum.name),1,1)").
				append(")");
			}else{
				param.append("( adminUser.provinceEnum.id="+enum1.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.id="+enum1.getId()).
				append(" or adminUser.provinceEnum.provinceEnum.provinceEnum.id="+enum1.getId()).
				append(")");
			}
			model.combCondition(param.toString());
		}*/
		//id
	    if(!StringUtils.isEmpty(good_id)){
	    	model.combPreEquals("id", Integer.parseInt(good_id));
	    	param.append("&good_id="+good_id);
	    }
	    //搜索
	    if(!StringUtils.isEmpty(search_name)){
	    	model.combCondition("name like '%"+search_name+"%'");
	    	param.append("&search_name="+search_name);
	    }
		String pagestr = (String)request.getParameter("page");
        PageInfo pageInfo = new PageInfo();
        int count = dateBaseDao.findCount(Goods.class, model);
        Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
        int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
        int end = pageInfo.getPageSize();
        pageInfo.setParam(param + "&page=");
        List<Goods> goodslist = dateBaseDao.findPageList(Goods.class, model, start, end); 
        request.setAttribute("page", pagestr);
        request.setAttribute("count", count);
        request.setAttribute("goodslist", goodslist);
        request.setAttribute("pageFoot", pageInfo.getCommonDefaultPageFootView());
	}
	
	@Override
	public void add(HttpServletRequest request, HttpServletResponse response){
		Extendlimits el = extendlimitsDao.findById(1);
		if (el!=null) {
			int per_adverscore = el.getPerAdverscore();
			request.setAttribute("per_adverscore", per_adverscore);
		}
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			Goods sc = goodsDao.findById(Integer.parseInt(id) );
			request.setAttribute("sc", sc);
		}
	}
	
	@Override
	public void save(Integer id, Integer type, String name,String description, Integer website_id, HttpServletRequest request, HttpServletResponse response){
		int current_user_id =(Integer)request.getSession().getAttribute("currentUserId");
		String outWebsite = request.getParameter("outWebsite");
		AdminUser current_user = adminUserDAO.findById(current_user_id);
		
		Goods sc = new Goods();
		
		if(id!=null){
			sc = goodsDao.findById(id);
		}else{
			sc.setAdverImgurls("");
			sc.setAdverImgurlSize(0);
			sc.setAdminUser(current_user);
		}
		
		sc.setOutWebsite(outWebsite);
		sc.setType(type);
		sc.setName(name);
		sc.setDescription(description);
		int scmode = 0;

		sc.setMode(scmode);
		sc.setRemark("");
		sc.setMoney(0.0);
		sc.setNeedScore(0);
		sc.setCash(0.00);
		sc.setPhone("");
		sc.setRemark("");
		sc.setTcount(0);
		sc.setImgurls("");
		sc.setAsort(-1);
		
		sc.setShoptypes(null);
		sc.setZones(null);

		Websites web = websitesDao.findById(website_id);
		sc.setWebsites(web);
		sc.setAdverImgurlsSmall("");
		sc.setSeller(null);
		sc.setAdverStatus(0);
		sc.setStatus(0);
		
		sc.setIsvalid(true);
		sc.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));

		goodsDao.save(sc);	
		
		if(id!=null){
			goodsAdversettingsDao.updateStatus(id,0);
		}
		
	}
	
	@Override
	public void del(String ids){
		goodsDao.del(ids);
	}
	
	@Override
	public void addAdverImg(Integer id, HttpServletRequest request){
		List<Coin> coinlist = coinDAO.findByCon(Coin.class, "isvalid =true");
		if(coinlist.size()>0){
			Coin coin = coinlist.get(0);
			request.setAttribute("coin", coin);
		}
		if(id>0){
			Goods sc = goodsDao.findById(id);
			request.setAttribute("sc", sc);
		}
	}
	
	@Override
	public void saveAdverImg(Integer id,Integer playtotal,String adver_imageurls,
			String adver_imageurls_small, String adver_imgurl_size, 
			HttpServletRequest request){
		if(id>0){
			goodsDao.saveAdverImgs(id, playtotal, adver_imageurls, adver_imageurls_small, adver_imgurl_size);
		}
	}
	
	@Override
	public void checkAdverList(HttpServletRequest request,Integer adminUserId){
		AdminUser adminUser = adminUserDAO.findById(adminUserId);
		ProvinceEnum provinceEnum = adminUser.getProvinceEnum();
		
		String pagestr = request.getParameter("page");
		PageInfo pageInfo = new PageInfo();
		QueryModel model = new QueryModel().setOrder("id desc");
		model.combPreEquals("type", 2);
		model.combPreEquals("adverStatus", 0);
		model.combCondition("adverImgurls is not null");
		model.combPreEquals("isvalid", true);
		if(adminUser.getLevel()>=65&&adminUser.getLevel()<95){
			String str=provinceEnumDAO.findProvinceEnumByAdminUser(provinceEnum);
	  		if(StringUtil.hasLength(str)){
	  			model.combCondition(str);
	  			//model.orCondition(str);
	  		}
		}
		int	count = dateBaseDao.findCount(Goods.class, model);
		Utility.setPageInfomation(pageInfo, pagestr, pageSize+"", count);
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		pageInfo.setParam("&page=");
		List<Goods> slst = dateBaseDao.findPageList(Goods.class, model, start, end);
		request.setAttribute("slst", slst);
		request.setAttribute("pageFoot",pageInfo.getCommonDefaultPageFootView());
	}
	
	@Override
	public void checkAdver(Integer id,HttpServletRequest request){
		List<Coin> coinlist = coinDAO.findByCon(Coin.class, "isvalid =true");
		if(coinlist.size()>0){
			Coin coin = coinlist.get(0);
			request.setAttribute("coin", coin);
		}
		if(id!=null){
			Goods sc = goodsDao.findById(id);
			request.setAttribute("sc", sc);
		}
	}
	
	@Override
	public void saveCheck(Integer id, Integer adver_status, String checkstr, HttpServletRequest request){
    	if(id!=null){
    		Goods  goods =goodsDao.findById(id);
    		goods.setAdverStatus(adver_status);
    		if(adver_status==-1){
				goods.setCheckstr(checkstr);
			}
    		goodsDao.update(goods);
    	}
	}

}
 