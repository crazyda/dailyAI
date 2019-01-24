package com.axp.action.professional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.GameActivity;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.Scoretypes;
import com.axp.model.Seller;
import com.axp.model.UserDarw;
import com.axp.query.PageResult;
import com.axp.service.professional.ShopService;

@Controller
@RequestMapping("/game")
public class gameDarwAction extends BaseAction{
	@Resource
	private ShopService shopservice;
	
	@NeedPermission(permissionName="游戏设置",classifyName="商城管理")
	@IsItem(firstItemName="商城管理",secondItemName="游戏设置")
	@RequestMapping("/gameList")
	public String merchantGame(HttpServletRequest request, Integer currentPage){
		//检查参数；
        //给当前页赋默认值；
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        AdminUser adminUser = adminUserService.findById(adminUserId);
        if (adminUser == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }

        gameActivityService.getGameList(adminUser,request,currentPage);
        request.setAttribute("adminUser", adminUser);
		return "/game/merchant_game";
	}
	
	
	@RequestMapping("/saveSignInfo")
	public String saveSignInfo(HttpServletRequest request,HttpServletResponse response,
							@RequestParam(value="coverPic[]",required=false) List<String> coverPics,
							@RequestParam(value="content[]",required=false)List<String> content){ //每天对应签到图
		 
	        //检查用户是否存在；
	        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
	        if (adminUserId == null) {
	            request.getSession().setAttribute("errorstr", "您尚未登录");
	            return "error/error";//登录出错情况；
	        }
		
		gameActivityService.saveSignInfo(request, response ,coverPics,content);
			
		return "redirect:gameList";
		
	}
	@RequestMapping("/addDraw")
	public String addDraw(HttpServletRequest request,HttpServletResponse response){
		
		//查询奖品类型
        List<Scoretypes> scoreTypes = scoreTypesService.getActivity();
        UserDarw userDarw = userDarwService.getById(1);
        //查询商家
        List<Seller> sellers = sellerService.findByAll();
        //查询所有周边商品
      List<ReGoodsOfSellerMall> goods = reGoodsOfSellerMallService.findByAll();
      request.setAttribute("goods", goods);
        request.setAttribute("userDarw", userDarw);
        request.setAttribute("scoreTypes", scoreTypes);//将查询结果放到session中；
        request.setAttribute("sellers", sellers);
		return "/game/activity";
		
	}
	
	@RequestMapping("/addSign")
	public String addSign(HttpServletRequest request,HttpServletResponse response,Integer id){
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        if(id != 0){
        	GameActivity game = gameActivityDAO.findById(id);
        	String[]array1 = game.getOneScore().split("-");
        	request.setAttribute("game", game);
        	request.setAttribute("array", array1);
        }
		return "game/addSignDarw1";
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/delSign")
	public Map<String, Object> delSign(Integer id,HttpServletRequest request,HttpServletResponse response){
		 Map<String, Object> returnMap = new HashMap<>();
		int result = gameActivityService.delSign(id);
		if(result==1){
			  returnMap.put("message", "删除成功；");
		}else{
			returnMap.put("message", "删除失败,请重新尝试;");
		}
		return returnMap;
		
	}

    
    @RequestMapping("activity")
    public String activity(HttpServletRequest request, HttpServletResponse response){
    	
    	Map<String,Object> activity = gameActivityService.saveDrawinfo(null,request,response);
    	
		return "redirect:gameList";
    }
    
    
    @RequestMapping("/_delActivity")
    @ResponseBody
    public Map<String, Object> delActivity(Integer id) {

        //返回数据；
        Map<String, Object> returnMap = new HashMap<>();

        
        GameActivity gameActivity = gameActivityService.findById(id);
        if (gameActivity!=null) {
        	gameActivity.setIsValid(false);
        	gameActivityDAO.saveOrUpdate(gameActivity);
        	
            returnMap.put("message", "删除成功；");
        } else {
            returnMap.put("message", "不能删除");
        }

        return returnMap;
    }
	
	@RequestMapping("saveUserDarw")
	public Map<String,Object> saveUserDarw(@RequestParam("coverPic[]") List<String> coverPics,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = userDarwService.saveUserDarw(coverPics,request,response);
		return result;
		
	}
	
	/**
	 * 查找对应的商家
	 * @param coverPics
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("adminUserToSeller")
	@ResponseBody
	public Map<String,Object> adminUserToSeller(HttpServletRequest request, HttpServletResponse response){
		return sellerService.findAdminUserToSeller(request,response);
		
	}
}
