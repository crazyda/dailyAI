package com.axp.action.goods;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminUserDAO;
import com.axp.dao.GameActivityDAO;
import com.axp.dao.ProvinceEnumDAO;
import com.axp.dao.ReGoodsOfBaseDAO;
import com.axp.dao.ReGoodsStandardDAO;
import com.axp.dao.UserDarwDAO;

import com.axp.model.AdminUser;
import com.axp.model.CashshopTimes;
import com.axp.model.CommodityType;
import com.axp.model.GameActivity;
import com.axp.model.ProvinceEnum;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsOfLockMall;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.ReGoodsStandard;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.RePermission;
import com.axp.model.Scoretypes;
import com.axp.model.Seller;
import com.axp.model.UserDarw;
import com.axp.query.PageResult;
import com.axp.service.goods.GameActivityService;
import com.axp.service.professional.SellerService;
import com.axp.service.professional.UserService;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

/**
 * 基础商品的操作；
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/reBaseGoods")
public class ReBaseGoodsAction extends BaseAction {

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
    @Autowired
    private GameActivityDAO gameActivityDAO;
    /**
     * 展示所有的基础商品；
     */
    @NeedPermission(permissionName = "基础商品列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "基础商品列表")
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
        if (goodsType == null || goodsType < 0) {//给商品商城的选择赋默认值；
            goodsType = 0;
        }

        //将商品类型放到session中；
        List<Map<String, Object>> selectMall = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("type", 0);
        m1.put("name", "基础商品");
        selectMall.add(m1);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("type", ReGoodsOfBase.sellerMall);
        m2.put("name", "周边店铺商品");
        selectMall.add(m2);
        Map<String, Object> m3 = new HashMap<>();
        m3.put("type", ReGoodsOfBase.scoreMall);
        m3.put("name", "积分兑换商品");
        selectMall.add(m3);
        Map<String, Object> m4 = new HashMap<>();
        m4.put("type", ReGoodsOfBase.seckillMall);
        m4.put("name", "限时秒杀商品");
        selectMall.add(m4);
        Map<String, Object> m5 = new HashMap<>();
        m5.put("type", ReGoodsOfBase.localSpecialtyMall);
        m5.put("name", "总部商城商品");
        selectMall.add(m5);
        Map<String, Object> m6 = new HashMap<>();
        m6.put("type", ReGoodsOfBase.nineNineMall);
        m6.put("name", "99特惠商品");
        selectMall.add(m6);
        Map<String, Object> m7 = new HashMap<>();
        m7.put("type", ReGoodsOfBase.memberMall);
        m7.put("name", "免单专区商品");
        selectMall.add(m7);
        Map<String, Object> m8 = new HashMap<>();
        m8.put("type", ReGoodsOfBase.lockMall);
        m8.put("name", "游戏专区");
        selectMall.add(m8);
        request.getSession().setAttribute("selectMall", selectMall);

        //查询出所有的商品规格，并放到session中；
        PageResult<ReGoodsOfBase> result = reGoodsOfBaseService.getGoodsForList(null, currentPage, pageSize, goodsType, searchWord);

        request.setAttribute("pageResult", result);//将查询结果放到session中；
        request.setAttribute("goodsType", goodsType);//将查询条件放到session，用于回显；
        request.setAttribute("searchWord", searchWord);//将查询条件放到session，用于回显；
        return "goods/baseGoods/list";
    }
    
    
    /**
     * 开奖填写开奖号码的,
     */
    @NeedPermission(permissionName = "游戏商品编辑", classifyName = "基础商品")
    @IsItem(firstItemName = "基础商品", secondItemName = "游戏商品编辑")
    @RequestMapping("/lockEdit")
    public String lockEdit(Integer id, Integer flag,Integer gameTypeId ,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	 //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);
    	
    	ReGoodsOfLockMall lockMall = reGoodsofLockmallService.get(id);
    	request.setAttribute("lockMall", lockMall);
    	if(gameTypeId == 265){ //10积分抽奖
    		if(lockMall.getEndTime().before(new Timestamp(System.currentTimeMillis())) ){// 结束时间早于现在时间 可以填写开奖号码&& lockMall.getOpenYards() ==null
    			ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(lockMall.getBaseGoodsId());
        		request.setAttribute("reGoodsBase", reGoodsOfBase);
        		request.setAttribute("count", lockMall.getSalesVolume());
        	    return "/goods/baseGoods/lockEdit";
    		}else{
    			request.getRequestDispatcher("/reBaseGoods/lockEditGoods").forward(request, response);
                
    		}
    	}else if(gameTypeId == 266){ //幸运翻牌子
    		
    	}else if(gameTypeId == 267){ //倒计时夺宝
    		//后台是不能填中奖号码的
    		
    	}else{
    		
    	}
    
    		//这里还要找参与人数表的数据
//    		QueryModel model = new QueryModel();
//    		model.clearQuery();
//			model.combPreEquals("isValid", true);
//			model.combPreEquals("goodsId", lockMall.getId(),"good_id");
//			List<Object> participantNum = dateBaseDao.findDistinct(ReGoodsorderItem.class, model, "user.id");
//			map.put("participantNum", participantNum.size());
    	return null;
    }
    
    /**
     * 编辑商品时的提示；
     * 条件：未到开奖时间，否则提示不能编辑；
     */
    @RequestMapping("/lockEditGoods")
    @ResponseBody
    public Map<String, Object> lockEditGoods(HttpServletRequest request) {

    	 //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        if (StringUtil.isEmpty((String) request.getAttribute("page"))) {
            ReGoodsOfBase reGoodsOfBase = (ReGoodsOfBase) request.getAttribute("reGoodsBase");
            returnMap.put("message", "不能编辑原因:1.该商品已经投放到商城，暂不能进行编辑； 2.该商品已经开奖");
        } else {
            returnMap.put("success", true);
            returnMap.put("message", request.getAttribute("id"));
        }


        return returnMap;
    }
    
    
    
    /**
     * 由此方法决定是进行编辑还是新增商品；
     */
    @NeedPermission(permissionName = "基础商品编辑", classifyName = "基础商品")
    @IsItem(firstItemName = "基础商品", secondItemName = "商品新增或编辑")
    @RequestMapping("/edit")
    public String edit(Integer id, Integer flag, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //编辑商品时，既需要有json格式的提示，有需要有网页的跳转，所以在有flag时，表示编辑商品，没有flag时，只是表示请求查看是否能编辑；
        if (id != null) {//编辑商品；1，如果商品已经投放了，那么是不能进行编辑的；
            ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(id);

            if (flag == null) {//如果没有这个基础商品对象，或者这个基础商品对象已经在商城中上架了，需要给出提示；
                if (reGoodsOfBase == null || !"000000000".equals(reGoodsOfBase.getLaunchMall())) {
                    request.setAttribute("reGoodsBase", reGoodsOfBase);
                } else {
                    request.setAttribute("page", "goods/baseGoods/add");
                }
                request.getRequestDispatcher("/reBaseGoods/editGoods").forward(request, response);
                return null;
            } else {//编辑基础商品；
                request.setAttribute("reGoodsBase", reGoodsOfBase);

                //回显商品详情；
                try {
                    request.setAttribute("goodsDetails", reGoodsDetailsService.getByBaseGoods(reGoodsOfBase));
                    ReGoodsDetails byBaseGoods = reGoodsDetailsService.getByBaseGoods(reGoodsOfBase);
                    String picture = "";
                    String text = "";
                    String content = byBaseGoods.getContent();
                    List<String> img = new ArrayList<String>();
                    net.sf.json.JSONArray contentList = net.sf.json.JSONArray.fromObject(content);
                    JSONArray contentL= JSONArray.parseArray(content);
                    for(int i=0;i<contentList.size();i++){
                    	Map<String,Object> map = new  HashMap<String,Object>();
                    	Map<String,Object> cl = (Map<String, Object>) contentList.get(i);
                    	if(cl.get("picture") != null){
                    		picture =  cl.get("picture").toString();
                    		
                    		map.put("imgUrl", picture);
                    		img.add("{\"imgUrl\":"+"\""+picture+"\"}");
                    	}
                    	if(cl.get("text") != null){
                    		text = cl.get("text").toString();
                    	}
                    	
                    }
                  
                    request.setAttribute("img", img.toString());
                    request.setAttribute("detailPicture", picture);
                    request.setAttribute("detailTxt", text);
                } catch (Exception e) {
                    request.setAttribute("goodsDetails", "商品还没有详情信息，需要您进行录入；");
                    e.printStackTrace();
                }
            }
        }

        request.setAttribute("commodityType", commodityTypeDao.findByPropertyWithValid("level", 1));
        request.setAttribute("lableList", goodsLableDao.findByProperty("isValid", true));

        return "goods/baseGoods/add";
    }

    /**
     * 编辑商品时的提示；
     * 条件：商品必须是没有投放到任何商城，否则提示不能编辑；
     */
    @RequestMapping("/editGoods")
    @ResponseBody
    public Map<String, Object> editGoods(HttpServletRequest request) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        if (StringUtil.isEmpty((String) request.getAttribute("page"))) {
            ReGoodsOfBase reGoodsOfBase = (ReGoodsOfBase) request.getAttribute("reGoodsBase");
            if (reGoodsOfBase == null) {
                returnMap.put("message", "不存在此id值(id=" + request.getAttribute("id") + ")的基础商品对象");
            } else {
                returnMap.put("message", "该商品已经投放到商城，暂不能进行编辑；");
            }
        } else {
            returnMap.put("success", true);
            returnMap.put("message", request.getAttribute("id"));
        }

        return returnMap;
    }
    @ResponseBody
    @RequestMapping("/saveOpenYards")
    public Map<String, Object> saveOpenYards(HttpServletRequest request,String id,String name,String openYards,String openYardsUser,
    						String salesVolume,String score,String standardDetails) {
    	 
        //返回值；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        if (adminUserId == null) {//用户未登录或超时；
            map.put("msg", "用户尚未登录或者登录超时");
            return map;
        }
        //保存开奖号码
        return reGoodsofLockmallService.saveOpenYards(request,id,name,openYards,openYardsUser,salesVolume,score,standardDetails);
    }
    /**
     * 保存商品操作；
     * 1，保存商品到ReGoodsOfBase表中；
     * 2，保存商品到快照表中；
     *
     * @param name            商品名；
     * @param descriptionPics 商品简述图片（最多五张）；
     * @param intro           商品简述；
     * @param details         商品详细信息；
     */
    @ResponseBody
    @NeedPermission(permissionName = "基础商品保存", classifyName = "基础商品")
    @RequestMapping("/save")
    public Map<String, Object> save(HttpServletRequest request,
                                    Integer id,
                                    String name,
                                    @RequestParam(value="coverPic[]",required=false) List<String> coverPics,
                                    @RequestParam(value="typesId[]",required=false) List<Integer> typesId,
                                    @RequestParam(value="descriptionPic[]",required=false) List<String> descriptionPics,
                                    @RequestParam(value="content[]",required=false)List<String> content,String details//商品详情
                                    ) {
    	 //@RequestParam("lablesId[]") List<Integer> lablesId,
        //返回值；
    	
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        Seller seller = sellerService.getSellerByAdmin(adminUserId);
        if (adminUserId == null) {//用户未登录或超时；
            map.put("msg", "用户尚未登录或者登录超时");
            return map;
        }
        if (seller == null) {//找不到商家；
            map.put("msg", "未找到与此用户对应的商家");
            return map;
        }
        
        //保存之前先备份一份 审核时需要回显
        reGoodsOfBaseService.backUpBaseGoods(id,0,null);
        
        //保存商品、编辑商品；
        
        map = reGoodsOfBaseService.doSaveOfBaseGoods(id, seller, name, coverPics, typesId, null, descriptionPics, null, details, content,null); //da  rightsId
        return map;
    }

    /**
     * 跳转到投放页面；
     * 1，需要显示已投放了哪些商城；
     * 2，还可以投放哪些商城；
     */
    @NeedPermission(permissionName = "基础商品投放页", classifyName = "基础商品")
    @RequestMapping("/putPage")
    public String putPage(Integer baseGoodsId, HttpServletRequest request) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }

        //检查参数；
        if (baseGoodsId == null) {
            request.getSession().setAttribute("errorstr", "你提交的参数不符合要求；");
            return "error/error";//提交参数异常；
        }
        
        request.getSession().setAttribute("zbdp", "no");
        request.getSession().setAttribute("xsms", "no");
        request.getSession().setAttribute("jfdh", "no");
        request.getSession().setAttribute("99th", "no");
        request.getSession().setAttribute("gdtc", "no");
        request.getSession().setAttribute("mfdh", "no");
        request.getSession().setAttribute("hhh", "no");
        
      //验证权限；
        List<RePermission> adminUserPermissionList = (List<RePermission>) request.getSession().getAttribute("RePermissions_in_Session");//当前后台用户拥有的权限；
        
        if (adminUserPermissionList != null) {
            for (RePermission each : adminUserPermissionList) {//查看当前用户拥有的权限是否包含此权限；
                if("100".equals(each.getLinkAddress())){
                	request.getSession().setAttribute("zbdp", "show");
                }
                
//                else if("200".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("xsms", "show");
//                }else if("300".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("jfdh", "show");
//                }else if("400".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("jjth", "show");
//                }else if("500".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("gdtc", "show");
//                }else if("600".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("mfdh", "show");
//                }else if("700".equals(each.getLinkAddress())){
//                	request.getSession().setAttribute("hhh", "show");
//                }
            }
        }else{
        	request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        

        //秒杀时段
        List<CashshopTimes> timesList = cashshopTimesDao.findTimesOfHQ();
        request.setAttribute("timesList", timesList);

        //将前台需要的该商家的商品规格信息传送到前台；
        List<ReGoodsStandard> firstStandardList = reGoodsStandardService.getFirstStandardWithSecondStandard(adminUserId);
        request.getSession().setAttribute("firstStandardList", firstStandardList);
        
		//获取地址
		List<ProvinceEnum> zoneList=null;
		zoneList = userService.getZone(1);
		request.setAttribute("zoneList", zoneList);
		
		
        //回显信息(将需要回显的信息都放到session中)；(此处需要展示的是能编辑，能删除的表格；)
        reBaseGoodsService.returnBack(request, baseGoodsId, false, false);

        return "goods/baseGoods/put";
    }
    //将String类型的数组转为Integer的集合；
    private List<Integer> getIngerList(String[] s) {
        if (s == null || s.length == 0) {
            return null;
        }
        List<Integer> returnList = new ArrayList<>();
        for (String each : Arrays.asList(s)) {
            try {
                returnList.add(Integer.parseInt(each));
            } catch (Exception e) {
                returnList.add(0);
            }
        }
        return returnList;
    }

    @RequestMapping("/put")
    @ResponseBody
    @NeedPermission(permissionName = "基础商品投放到商城", classifyName = "基础商品")
    public Map<String, Object> put(Integer mallType, HttpServletRequest request) throws Exception {

        //返回参数；
        Map<String, Object> map = new HashMap<>();
        map.put("success", 0);

        //参数检查；
        if (mallType == null) {
            map.put("msg", "没有检测到商城的编号值");
            return map;
        }

        
        List<Integer> detailsValue3 = request.getParameterValues("detailsValue3[]") == null ? null : getIngerList(request.getParameterValues("detailsValue3[]"));
         List<Integer> detailsValue2 = request.getParameterValues("detailsValue2[]") == null ? null : getIngerList(request.getParameterValues("detailsValue2[]"));
         List<Integer> detailsValue1 = request.getParameterValues("detailsValue1[]") == null ? null : getIngerList(request.getParameterValues("detailsValue1[]"));
         
         ReGoodsStandard rgs=null;
         ReGoodsStandard rgs2=null;
         ReGoodsStandard rgs3=null;
         
         if(detailsValue1!=null&& detailsValue1.size()>0){
        	 rgs= reGoodsStandardDAO.findById(detailsValue1.get(0));
         }
         if(detailsValue2!=null&& detailsValue2.size()>0){
        	 rgs2= reGoodsStandardDAO.findById(detailsValue2.get(0));
         }
         if(detailsValue3!=null&& detailsValue3.size()>0){
        	 rgs3= reGoodsStandardDAO.findById(detailsValue3.get(0));
         }
         
         
         List<Integer> firstStandardKeyforItem=new ArrayList<Integer>();
         List<String> firstStandardNameforItem=new ArrayList<String>();
         if(rgs!=null){
        	 firstStandardKeyforItem.add(rgs.getParentStandardId());
        	 ReGoodsStandard rgs4=reGoodsStandardDAO.findById(rgs.getParentStandardId());
        	 firstStandardNameforItem.add(rgs4.getName());
         }
         if(rgs2!=null){
        	 firstStandardKeyforItem.add(rgs2.getParentStandardId());
        	 ReGoodsStandard rgs4=reGoodsStandardDAO.findById(rgs2.getParentStandardId());
        	 firstStandardNameforItem.add(rgs4.getName());
         }
         if(rgs3!=null){
        	 firstStandardKeyforItem.add(rgs3.getParentStandardId());
        	 ReGoodsStandard rgs4=reGoodsStandardDAO.findById(rgs3.getParentStandardId());
        	 firstStandardNameforItem.add(rgs4.getName());
         }
         
         
         if(firstStandardKeyforItem.size()==0){
        	 firstStandardKeyforItem=request.getParameterValues("firstStandardKey[]") == null ? null : getIngerList(request.getParameterValues("firstStandardKey[]"));
        	 firstStandardNameforItem = request.getParameterValues("firstStandardName[]") == null ? null : Arrays.asList(request.getParameterValues("firstStandardName[]"));
         }
         
         
         
        
        //获取所有的参数；
        ReBaseGoods.MallParamter paramter = new ReBaseGoods().new MallParamter(request, reGoodsOfBaseService, reGoodsSnapshotService,firstStandardKeyforItem,firstStandardNameforItem);

        //根据mallId决定上传到哪个商城；
        try {
            if (ReGoodsOfBase.sellerMall.equals(mallType)) {
                map.put("msg", "保存商品到周边店铺出错，请检查所填信息后再试");
                reGoodsOfSellerMallService.doSave(paramter);
            } else if (ReGoodsOfBase.scoreMall.equals(mallType)) {
                map.put("msg", "保存商品到积分兑换出错，请检查所填信息后再试");
                reGoodsOfScoreMallService.doSave(paramter);
            } else if (ReGoodsOfBase.seckillMall.equals(mallType)) {
                map.put("msg", "保存商品到秒杀专区出错，请检查所填信息后再试");
                reGoodsOfSeckillMallService.doSave(paramter);
            } else if (ReGoodsOfBase.localSpecialtyMall.equals(mallType)) {
                map.put("msg", "保存商品到总部商城出错，请检查所填信息后再试");
                reGoodsOfLocalSpecialtyMallService.doSave(paramter, request);
            } else if (ReGoodsOfBase.nineNineMall.equals(mallType)) {
                map.put("msg", "保存商品到99特惠出错，请检查所填信息后再试");
                reGoodsOfNineNineMallService.doSave(paramter);
            } else if (ReGoodsOfBase.memberMall.equals(mallType)) {
                map.put("msg", "保存商品到免单专区出错，请检查所填信息后再试");
                reGoodsOfMemberMallService.doSave(paramter);
            }else if (ReGoodsOfBase.changeMall.equals(mallType)) {
                map.put("msg", "保存商品到免单专区出错，请检查所填信息后再试");
                reGoodsOfChangeMallService.doSave(paramter);
            }  else {
                map.put("msg", "未知的商城类型");
                return map;
            }

            //修改基础商品中记录商品商家信息的上架信息，即launchMall的值；
            ReGoodsOfBase baseGoods = reGoodsOfBaseService.get(paramter.getBaseGoodsId());
            String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall()==null?"00000000":baseGoods.getLaunchMall(), mallType, true);
            baseGoods.setLaunchMall(changeLaunchMall);
            reGoodsOfBaseService.update(baseGoods);

            //返回结果值；
            map.put("success", 1);
            map.put("msg", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/_delete")
    @ResponseBody
    public Map<String, Object> delete(Integer id) {

        //返回数据；
        Map<String, Object> returnMap = new HashMap<>();

        //如果没有上架就可以删除；
        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(id);
        if (reGoodsOfBase.getLaunchMall().equals("0000000")) {
            reGoodsOfBase.setIsValid(false);
            reGoodsOfBaseDAO.updateIsValid(reGoodsOfBase.getId());

            returnMap.put("message", "删除成功；");
        } else {
            returnMap.put("message", "该商品已投放，暂不能删除");
        }

        return returnMap;
    }


    @RequestMapping("/goodsTypeAjax")
    @ResponseBody
    public List<CommodityType> goodsTypeAjax(Integer typeId, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        if (typeId == null || typeId == -1)
            return null;
        return commodityTypeDao.findByProperty("commodityType.id", typeId);
    }

    @RequestMapping("/standardAjax")
    @ResponseBody
    public List<ReGoodsStandard> standardAjax(Integer id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        if (id == null || id == -1)
            return null;
        return reGoodsStandardDao.findByProperty("parentStandardId", id);
    }

    @RequestMapping("/shelves")
    @ResponseBody
    public Map<String, Object> shelves(HttpServletRequest request) {
        String type = request.getParameter("type");
        switch (type) {
            case "lsm":
                return reGoodsOfLocalSpecialtyMallService.shelves(request);//总部商城
            case "nnm":
                return reGoodsOfNineNineMallService.shelves(request);//久久特惠
            default:
                break;
        }
        return null;
    }
    
    
    
    @RequestMapping("/changeSeller")
	@ResponseBody
	public Map<String,String> changeSeller(HttpServletRequest request,Integer seller_id) {
    	String a = request.getParameter("seller_id");
		 List<ReGoodsOfSellerMall> goods = reGoodsOfSellerMallService.findBySeller(seller_id);
		
		Map<String,String> map = new HashMap<String,String>();
		
		for (ReGoodsOfSellerMall s : goods) {
			map.put(s.getId().toString(), s.getSnapshotGoods().getName());
		}
		return map;
	}
    
}



