package com.axp.action.cashShop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.dao.ImageTypeDAO;
import com.axp.model.ImageType;
import com.axp.service.cashShop.ImageTypeService;
import com.axp.util.PageInfo;
import com.axp.util.QueryModel;
import com.axp.util.Utility;


@RequestMapping("/cashShop/imageType")
@Controller
public class ImageTypeAction {
	@Resource
	private ImageTypeDAO imageTypeDAO;
	@Resource
	private DateBaseDAO dateBaseDao;
	@Resource
	private ImageTypeService imageTypeService;

	@NeedPermission(permissionName = "商城图文类别列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "商城图文类别管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
			
        String pagestr = request.getParameter("page");
		
		PageInfo pageInfo = new PageInfo();
		int count = 0;


	    count = imageTypeDAO.getAllCountAndCon(ImageType.class,"isvalid=true");


		Utility.setPageInfomation(pageInfo, pagestr, "20",
				count);
		
		int start = pageInfo.getCurrentPage() * pageInfo.getPageSize();
		int end = pageInfo.getPageSize();
		
		pageInfo.setParam("&page=");
		List<ImageType> slst = imageTypeDAO.findByPageOrderAndCon(ImageType.class, " isvalid=true ", "id", "desc",start,end);
		request.setAttribute("slst", slst);		
		request.setAttribute("pageFoot", pageInfo
				.getCommonDefaultPageFootView());
		return "cashShop/imageType/list";
	}
	
	@RequestMapping("/add")
	 public String add(HttpServletRequest request,Integer id){
		 QueryModel queryModel = new QueryModel();
			if(id!=null){
				queryModel.clearQuery();
				queryModel.combPreEquals("id", id);
				ImageType sc = (ImageType) dateBaseDao.findOne(ImageType.class, queryModel);	
				request.setAttribute("sc", sc);
			}
			return "cashShop/imageType/add";
		}
	@RequestMapping("/save")
	public String save(HttpServletRequest request,Integer id,Integer type){
		String name = request.getParameter("name");
		QueryModel queryModel = new QueryModel();
		ImageType sc = null;
		
		if(id!=null&&id>0){
			queryModel.clearQuery();
			queryModel.combPreEquals("id",id);
			sc = (ImageType) dateBaseDao.findOne(ImageType.class, queryModel);
		}else{
			sc = new ImageType();
		}
		sc.setName(name);
		sc.setType(type);
		sc.setIsValid(true);
		sc.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		imageTypeService.saveImageType(sc);
		
		return "redirect:list";
	}
	
	@RequestMapping("/del")
	public String del(Integer id){	
		if(id>0){
			imageTypeDAO.updatePropertyByID("isValid", false, id, ImageType.class);
		}
		
		return "redirect:list";
	}
}
