package com.axp.action.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ReGoodsOfSellerMallDAO;
import com.axp.model.CashshopTimes;
import com.axp.model.CashshopType;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.RePermission;
import com.axp.model.Seller;
import com.axp.model.ReBaseGoods.MallParamter;
import com.axp.query.PageResult;
import com.axp.service.cashShop.CashShopTypeService;
import com.axp.service.professional.UserService;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

/**
 * 六个商城中的商品操作；
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/reMallGoods")
public class ReMallGoodsAction extends BaseAction {

	@Autowired
	UserService userService;
	@Autowired
	ReGoodsOfSellerMallDAO reGoodsOfSellerMallDao;
    //===========================================投放商城列表开始================================================

    /**
     * 商品投放列表；
     * 说明：以前的做法是在一个页面，通过选择框进行选择，现在需要进行拆分，投放列表拆分成6个投放列表；
     */
    @NeedPermission(permissionName = "商城商品列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "商品投放列表")
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Integer currentPage, Integer pageSize, Integer goodsType, String searchWord) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
//        if (adminUserId == null || seller == null) {
//            request.getSession().setAttribute("errorstr", "您尚未登录或您还不是商家");
//            return "error/error";//登录出错情况；
//        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }
        if (goodsType == null) {//商城类型一定要有值，如果没有，就赋默认值；
            goodsType = ReGoodsOfBase.sellerMall;
        }
        String search;//sql中的所有条件；
        if (StringUtil.hasLength(searchWord)) {
            search = "'%" + searchWord + "%'";
        } else {
            search = "'%'";
        }

        //将商品类型放到session中；
        List<Map<String, Object>> selectMall = new ArrayList<>();
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("type", ReGoodsOfBase.sellerMall);
        m2.put("name", "周边店铺");
        selectMall.add(m2);
        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("type", ReGoodsOfBase.scoreMall);
        m3.put("name", "积分兑换");
        selectMall.add(m3);
        Map<String, Object> m4 = new HashMap<String, Object>();
        m4.put("type", ReGoodsOfBase.seckillMall);
        m4.put("name", "限时秒杀");
        selectMall.add(m4);
        Map<String, Object> m5 = new HashMap<String, Object>();
        m5.put("type", ReGoodsOfBase.localSpecialtyMall);
        m5.put("name", "总部商城");
        selectMall.add(m5);
        Map<String, Object> m6 = new HashMap<String, Object>();
        m6.put("type", ReGoodsOfBase.nineNineMall);
        m6.put("name", "99特惠");
        selectMall.add(m6);
        Map<String, Object> m7 = new HashMap<String, Object>();
        m7.put("type", ReGoodsOfBase.memberMall);
        m7.put("name", "免单专区");
        selectMall.add(m7);
        
        Map<String, Object> m8 = new HashMap<String, Object>();
        m8.put("type", ReGoodsOfBase.changeMall);
        m8.put("name", "换货商品");
        selectMall.add(m8);
        Map<String, Object> m9 = new HashMap<String, Object>();
        m9.put("type", ReGoodsOfBase.teamMall);
        m9.put("name", "拼团商品");
        selectMall.add(m9);
        Map<String, Object> m10 = new HashMap<String, Object>();
        m10.put("type", ReGoodsOfBase.lockMall);
        m10.put("name", "抽奖商品");
        selectMall.add(m9);
        
        
        request.getSession().setAttribute("selectMall", selectMall);

        //查询出所有的商品规格，并放到session中；
        PageResult<ReGoodsOfBase> result = reGoodsOfBaseService.getGoodsListInMall(null, currentPage, pageSize, goodsType, search);

        request.setAttribute("pageResult", result);//将查询结果放到session中；
        request.setAttribute("goodsType", goodsType);//将查询条件放到session，用于回显；
        request.setAttribute("searchWord", searchWord);//将查询条件放到session，用于回显；
        return "goods/mallGoods/list";
    }

    /**
     * 周边店铺投放列表；
     */
    @NeedPermission(permissionName = "周边店铺投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "周边店铺投放列表")
    @RequestMapping("/sellerMallList")
    public String sellerMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.sellerMall, searchWord);
        return "goods/mallGoods/sellerMallList";
    }
    
    /**
     * 游戏店铺投放列表；
     */
    @NeedPermission(permissionName = "游戏商品投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "游戏商品投放列表")
    @RequestMapping("/gameMallList")
    public String gameMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.lockMall, searchWord);
        return "goods/mallGoods/gameMallList";
    }

    /**
     * 总部商城投放列表；
     */
    @NeedPermission(permissionName = "全国特产投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "全国特产投放列表")
    @RequestMapping("/localSpecialList")
    public String localSpecialList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.localSpecialtyMall, searchWord);
        return "goods/mallGoods/localSpecialList";
    }

    /**
     * 99特惠投放列表；
     */
    @NeedPermission(permissionName = "99特惠投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "99特惠投放列表")
    @RequestMapping("/nineNineMallList")
    public String nineNineMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.nineNineMall, searchWord);
        return "goods/mallGoods/nineNineMallList";
    }

    /**
     * 积分兑换投放列表；
     */
    @NeedPermission(permissionName = "积分兑换投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "积分兑换投放列表")
    @RequestMapping("/scoreMallList")
    public String scoreMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.scoreMall, searchWord);
        return "goods/mallGoods/scoreMallList";
    }

    /**
     * 限时秒杀投放列表；
     */
    @NeedPermission(permissionName = "限时秒杀投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "限时秒杀投放列表")
    @RequestMapping("/seckillMallList")
    public String seckillMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.seckillMall, searchWord);
        return "goods/mallGoods/seckillMallList";
    }

    /**
     * 免单专区投放列表；
     */
    @NeedPermission(permissionName = "免单专区投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "免单专区投放列表")
    @RequestMapping("/memberMallList")
    public String memberMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.memberMall, searchWord);
        return "goods/mallGoods/memberMallList";
    }
    
    /**
     * 免单专区投放列表；
     */
    @NeedPermission(permissionName = "换货商品列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "换货商品列表")
    @RequestMapping("/changeMallList")
    public String changeMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
        list(request, currentPage, pageSize, ReGoodsOfBase.changeMall, searchWord);
        return "goods/mallGoods/changeMallList";
    }

    /*
     * 
     * 拼团推广列表
     * */
    @NeedPermission(permissionName = "拼团商品列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "拼团商品列表")
    @RequestMapping("/teamMallList")
    public String teamMallList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord){
    	list(request, currentPage, pageSize, ReGoodsOfBase.teamMall, searchWord);
    	return "goods/mallGoods/teamMallList";
    }
    
    
    //===========================================投放商城列表结束================================================

    /**
     * 将商品从商城下架；
     *
     * @param mallId      商城id值；
     * @param mallGoodsId 商城的商品id值；
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/unPut")
    @ResponseBody
    public Map<String, Object> unPut(Integer mallId, Integer mallGoodsId, HttpServletRequest request) throws Exception {

        //返回参数；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        //参数检查；
        if (mallGoodsId == null || mallId == null) {
            map.put("msg", "请求参数错误");
            return map;
        }

        //下架操作；
        return reGoodsOfBaseService.doUnPut(request,mallId, mallGoodsId);
    }

    @RequestMapping("/zhiding")
    @ResponseBody
    public Map<String, Object> zhiding(HttpServletRequest request, HttpServletResponse response) {

        return reGoodsOfSellerMallService.zhiding(request, response);
    }

    /**
     * 请求补充库存页面；
     */
    @RequestMapping("addRepertoryPage")
    public String addRepertoryPage(HttpServletRequest request, Integer mallType, Integer goodsId) throws Exception {

        ReBaseGoods reBaseGoods = reBaseGoodsService.getGoodsByMallTypeAndGoodsId(mallType, goodsId);
        reBaseGoodsService.returnBack(request, reBaseGoods.getBaseGoodsId(), false, true);

        request.setAttribute("mallType", mallType);
        request.setAttribute("goodsId", goodsId);

        Boolean isNoStandard = reBaseGoods.getIsNoStandard();
        if (isNoStandard) {
            request.setAttribute("noStandardRepertory", reBaseGoods.getNoStandardRepertory());
        }

        return "goods/mallGoods/addRepertoryPage";
    }

    /**
     * 补充库存操作；
     */
    @RequestMapping("addRepertory")
    public String addRepertory(HttpServletRequest request, HttpServletResponse response, Integer mallType, Integer goodsId) throws Exception {

        //是否有商品规格；
        Boolean isNoStandard = request.getParameter("isNoStandard") != null && "1".equals(request.getParameter("isNoStandard"));

        //商品对象；
        ReBaseGoods reBaseGoods = reBaseGoodsService.getGoodsByMallTypeAndGoodsId(mallType, goodsId);

        //增加库存操作；
        reBaseGoodsService.addRepertory(isNoStandard, reBaseGoods, request);
		switch (mallType) {
		case 1:
			return "redirect:sellerMallList";
		case 2:
			return "redirect:scoreMallList";	
		case 3:
			return "redirect:seckillMallList";	
		case 4:
			return "redirect:localSpecialList";	
		}

		return "";
    }

    /**
     * 删除审核拒绝的商品；
     */
    @RequestMapping("delUnpassGoods")
    @ResponseBody
    public Map<String, Object> delUnpassGoods(Integer goodsId, Integer mallType) throws Exception {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //删除具体商品对象；
        reGoodsOfBaseService.delUnpassGoods(mallType, goodsId);
        ReBaseGoods reBaseGoods = reBaseGoodsService.getGoodsByMallTypeAndGoodsId(mallType, goodsId);

        returnMap.put("message", "删除成功");
        return returnMap;
    }
    
    
    @NeedPermission(permissionName = "周边店铺投放页", classifyName = "周边商品")
    @RequestMapping("/promotePage")
    public String promotePage(Integer goodsId,HttpServletRequest request) throws Exception{
    	Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        
        if (goodsId == null) {
            request.getSession().setAttribute("errorstr", "你提交的参数不符合要求；");
            return "error/error";//提交参数异常；
        }
        request.getSession().setAttribute("qgtc", "no");
        request.getSession().setAttribute("yhq", "no");
        request.getSession().setAttribute("jfdh", "no");
        request.getSession().setAttribute("xsms", "no");
        request.getSession().setAttribute("hhh", "no");
        request.getSession().setAttribute("pt", "no");
        request.getSession().setAttribute("jfcj", "no");
    	   
       List<Map<String, Object>> statusMap =reGoodsOfSellerMallService.getGoodsPublishStatus(goodsId);
       for (int i = 0; i < statusMap.size(); i++) {
    	   Boolean status=(Boolean) statusMap.get(i).get("status");
//    	   if(i==0 && status==true)
//    		   request.getSession().setAttribute("qgtc", "show");
//    	   if(i==1 && status==true)
//    		   request.getSession().setAttribute("yhq", "show");
    	   if(i==2 && status==true)
    		   request.getSession().setAttribute("jfdh", "show");
//		   if(i==3 && status==true)
//			   request.getSession().setAttribute("xsms", "show");
//		   if(i==4 && status==true)
//			   request.getSession().setAttribute("pt", "show");
//		   if(i==5 && status==true)
//			   request.getSession().setAttribute("hhh", "show");
		   if(i==6 && status==true)
			   request.getSession().setAttribute("jfcj", "show"); 
       }
        ReGoodsOfSellerMall sellerMall = reGoodsOfSellerMallDao.findById(goodsId);
        if (sellerMall!=null) {
        	request.setAttribute("reGoodsOfSellerMall", sellerMall);
		}
        
        
        if (!sellerMall.getIsNoStandard()) {
        	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			String standard = sellerMall.getStandardDetails();
			JSONObject jsonObject = JSONObject.parseObject(standard);
			JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("standardDetails");
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String id1 = jsonArray.getJSONObject(i).getString("id1");
				String name1 = jsonArray.getJSONObject(i).getString("name1");
				String repertory = jsonArray.getJSONObject(i).getString("repertory");
				String price = jsonArray.getJSONObject(i).getString("price");
				map.put("id1", id1);
				map.put("name1", name1);
				map.put("repertory", repertory);
				map.put("price", price);
				list.add(map);
				
			}
			
			request.setAttribute("standard", JSON.toJSON(list));
		}
        //秒杀时段
        List<CashshopTimes> timesList = cashshopTimesDao.findTimesOfHQ();
        request.setAttribute("timesList", timesList);
        
        List<ProvinceEnum> zoneList=null;
		zoneList = userService.getZone(1);
		request.setAttribute("zoneList", zoneList);
		
		//获取游戏类型
		List<CashshopType> gameTypes = cashShopTypeService.getGameType();
		request.setAttribute("gameTypes", gameTypes);
		
		
    	return "goods/mallGoods/put/promotePage";
    }

    
    @RequestMapping("/promote")
    @ResponseBody
    @NeedPermission(permissionName = "周边店铺投放到其他商城", classifyName = "周边商品")
    public Map<String, Object> promote (Integer mallType,HttpServletRequest request) throws Exception{
    	//返回参数；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        //参数检查；
        if (mallType == null) {
            map.put("msg", "没有检测到商城的编号值");
            return map;
        }
        ReBaseGoods.MallParamter paramter = new ReBaseGoods().new MallParamter(request, reGoodsOfBaseService, reGoodsSnapshotService,null,null);
			   //根据mallId决定上传到哪个商城；
        try {
            if (ReGoodsOfBase.scoreMall.equals(mallType)) {
                map.put("msg", "推广商品到积分商城出错，请检查所填信息后再试");
                reGoodsOfScoreMallService.doSavePut(request);
            } else if (ReGoodsOfBase.seckillMall.equals(mallType)) {
                map.put("msg", "推广商品到限时秒杀出错，请检查所填信息后再试");
                reGoodsOfSeckillMallService.doSavePut(request);
            } else if (ReGoodsOfBase.localSpecialtyMall.equals(mallType)) {
                map.put("msg", "推广商品到全国特产出错，请检查所填信息后再试");
                reGoodsOfLocalSpecialtyMallService.doSavePut(request);;
            }
//            else if (ReGoodsOfBase.teamMall.equals(mallType)) {
//                map.put("msg", "推广商品到拼团商品出错，请检查所填信息后再试");
//                reGoodsOfTeamMallService.doSavePut(request);;
//            } 
            else if (ReGoodsOfBase.changeMall.equals(mallType)) {
                map.put("msg", "推广商品到换货会出错，请检查所填信息后再试");
                reGoodsOfChangeMallService.doSavePut(request);
            }else if (ReGoodsOfBase.couponMall.equals(mallType)) {
                map.put("msg", "推广商品到优惠券出错，请检查所填信息后再试");
                reGoodsofextendmallService.doSavePut(request);
            }else if(ReGoodsOfBase.lockMall.equals(mallType)){
            	 map.put("msg", "推广商品到游戏商城出错，请检查所填信息后再试");
                 reGoodsofLockmallService.doSavePut(request);
            }else {
                map.put("msg", "未知的商城类型");
                return map;
            }

            //返回结果值；
            map.put("success", 1);
            map.put("msg", "商品推广成功，且不能重复推广同一商城！");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return map;
    } 
    
    
    /**
     * 总部置顶商品列表
     */
    @NeedPermission(permissionName = "置顶商品投放列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "置顶商品投放列表")
    @RequestMapping("/zhidingGoodsList")
    public String zhidingGoodsList(HttpServletRequest request, Integer currentPage, Integer pageSize, String searchWord) {
    	String mallId = request.getParameter("mallId")==null?"3":request.getParameter("mallId");
    	
    	String search;//sql中的所有条件；
        if (StringUtil.hasLength(searchWord)) {
            search = "'%" + searchWord + "%'";
        } else {
            search = "";
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }
        PageResult<ReGoodsOfBase> result = reGoodsOfScoreMallService.getZhiDingGoods(currentPage, pageSize, Integer.valueOf(mallId), search);
        
    	
    	request.setAttribute("pageResult", result);//将查询结果放到session中；
    	request.setAttribute("mallId", mallId);
        request.setAttribute("searchWord", searchWord);//将查询条件放到session，用于回显；
        return "goods/mallGoods/zhidingGoodsList";
    } 
    
    
    
    
    
}
