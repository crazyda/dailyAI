package com.axp.action.goods.goodsTop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.ReGoodsOfBaseDAO;
import com.axp.dao.ReGoodsStandardDAO;
import com.axp.model.AdminUser;
import com.axp.model.CashshopTimes;
import com.axp.model.CommodityType;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsStandard;
import com.axp.model.RePermission;
import com.axp.model.Seller;
import com.axp.query.PageResult;
import com.axp.service.professional.UserService;
import com.axp.util.ResponseResult;
import com.axp.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.LSTORE;


@Controller
@RequestMapping("/reGoodsTop")
public class ReGoodsTopAction extends BaseAction {

    @Autowired
    private ReGoodsOfBaseDAO reGoodsOfBaseDAO;

    @Autowired
    private ReGoodsStandardDAO reGoodsStandardDAO;
    
    @Autowired
    private AdminUserDAO adminUserDAO;
    
    @Autowired
    private ProvinceEnumDAO provinceEnumDAO;
    
    @Resource
	private UserService userService;
    /**
     * 展示所有的基础商品；
     */
    @NeedPermission(permissionName = "拼团商品置顶", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "拼团商品置顶")
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
       
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }
        
        String search_name=request.getParameter("search_name");
       
        Integer type=request.getParameter("type")==null?0:Integer.parseInt(request.getParameter("type"));
        
        
        
        
        reGoodsOfTeamMallService.findReGoodsTeamList(request, currentPage, search_name,type);
        
      return "goods/goodsTop/reGoodsOfTeamTopList";
    }
    
    /**
     * 置顶及取消置顶
     */
    @RequestMapping("/stick")
    @ResponseBody
    public Map<String, Object> stick(HttpServletRequest request,HttpServletResponse response){
    	Map<String, Object> stick =null;
    	try {
    		stick = reGoodsOfTeamMallService.stick(request);
    	//ResponseResult.printResult(response);
    	} catch (Exception e) {
			e.printStackTrace();
			//ResponseResult.printResult(response,false,null);
		}
		return stick;
    }
    
    /**
     * 上传轮播图
     */
    @RequestMapping("/uploadPic")
    @ResponseBody
    public Map<String, Object> uploadPic(HttpServletRequest request,HttpServletResponse response){
    		return reGoodsOfTeamMallService.uploadPic(request);
    }
    
}



