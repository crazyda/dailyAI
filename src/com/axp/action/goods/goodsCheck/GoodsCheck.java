package com.axp.action.goods.goodsCheck;

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
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.ReBaseGoodsDAO;
import com.axp.dao.ReGoodsOfChangeMallDAO;
import com.axp.model.ChangeNote;
import com.axp.model.ReGoodsDetails;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsOfChangeMall;
import com.axp.model.ReGoodsOfLocalSpecialtyMall;
import com.axp.model.ReGoodsOfLockMall;
import com.axp.model.ReGoodsOfMemberMall;
import com.axp.model.ReGoodsOfNineNineMall;
import com.axp.model.ReGoodsOfScoreMall;
import com.axp.model.ReGoodsOfSeckillMall;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.ReGoodsOfTeamMall;
import com.axp.model.ReGoodsSnapshot;
import com.axp.model.ReGoodsofextendmall;
import com.axp.query.PageResult;
import com.axp.service.goods.ReBaseGoodsService;
import com.axp.service.goods.ReChangeNoteService;
import com.axp.service.goods.ReGoodsOfTeamMallService;
import com.axp.util.ResponseResult;
import com.axp.util.StringUtil;

/**
 * 基础审核操作；
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/goodsCheck")
public class GoodsCheck extends BaseAction {

	@Autowired
	ReChangeNoteService reChangeNoteService;
	@Autowired
	ReGoodsOfTeamMallService reGoodsOfTeamMallService;
	@Autowired
	ReBaseGoodsDAO reBaseGoodsDAO;
	@Autowired
	ReBaseGoodsService reBaseGoodsService;
	@Autowired
	ReGoodsOfChangeMallDAO reGoodOfChangeMallDao;
    @NeedPermission(permissionName = "周边店铺商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "周边店铺审核")
    @RequestMapping("/listOfSellerMall")
    public String listOfSellerMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        
        PageResult<ReGoodsOfSellerMall> pageResult = reGoodsOfSellerMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfSellerMall";
    }

    @RequestMapping("/detailOfSellerMall")
    public String detailOfSellerMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfSellerMall reGoodsOfSellerMall = reGoodsOfSellerMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfSellerMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfSellerMall";
    }
    
    @NeedPermission(permissionName = "换货商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "换货商品审核")
    @RequestMapping("/listOfChangeMall")
    public String listOfChangeMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");

        PageResult<ReGoodsOfChangeMall> pageResult = reGoodsOfChangeMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfChangeMall";
    }

    @RequestMapping("/detailOfChangeMall")
    public String detailOfChangeMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfChangeMall reGoodsOfChangeMall = reGoodsOfChangeMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfChangeMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfChangeMall";
    }
    
    
    @RequestMapping("/checkOfChangeMall")
    @ResponseBody
    public Map<String, Object> checkOfChangeMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfChangeMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }
    

    @RequestMapping("/checkOfSellerMall")
    @ResponseBody
    public Map<String, Object> checkOfSellerMall(String checkDesc, Boolean isPass, Integer goodsId,Integer transportationType) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);
//da
        returnMamp.put("transportationType", transportationType);

        return reGoodsOfSellerMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }

    //========================================================================================================================================

    @NeedPermission(permissionName = "积分兑换商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "积分兑换审核")
    @RequestMapping("/listOfScoreMall")
    public String listOfScoreMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsOfScoreMall> pageResult = reGoodsOfScoreMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfScoreMall";
    }
    @NeedPermission(permissionName = "游戏商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "游戏商品审核")
    @RequestMapping("/listOfLockMall")
    public String listOfLockMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsOfLockMall> pageResult = reGoodsofLockmallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfLockMall";
    }
    
    
    
    @RequestMapping("/detailOfScoreMall")
    public String detailOfScoreMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfScoreMall reGoodsOfScoreMall = reGoodsOfScoreMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfScoreMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfScoreMall";
    }
    
    @RequestMapping("/detailOfLockMall")
    public String detailOfLockMall(HttpServletRequest request, Integer checkGoodsId) {

    	ReGoodsOfLockMall reGoodsOfLockMall = reGoodsofLockmallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfLockMall.getBaseGoodsId();
        String goodsOrder = reGoodsOfLockMall.getGoodsOrder();
        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

       // reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        reBaseGoodsService.returnBack_lock(request, baseGoodsId, true, false,goodsOrder);
        return "goods/goodsCheck/detailOfLockMall";
    }
    
    

    @RequestMapping("/checkOfScoreMall")
    @ResponseBody
    public Map<String, Object> checkOfScoreMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfScoreMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }

    @RequestMapping("/checkOfLockMall")
    @ResponseBody
    public Map<String, Object> checkOfLockMall(String checkDesc, Boolean isPass, String goodsOrder) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsofLockmallService.doCheck(returnMamp, isPass, checkDesc, goodsOrder);
    }
    
    
    
    //========================================================================================================================================

    @NeedPermission(permissionName = "限时秒杀商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "限时秒杀审核")
    @RequestMapping("/listOfSeckillMall")
    public String listOfSeckillMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsOfSeckillMall> pageResult = reGoodsOfSeckillMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfSeckillMall";
    }

    @RequestMapping("/detailOfSeckillMall")
    public String detailOfSeckillMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfSeckillMall reGoodsOfSeckillMall = reGoodsOfSeckillMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfSeckillMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfSeckillMall";
    }

    @RequestMapping("/checkOfSeckillMall")
    @ResponseBody
    public Map<String, Object> checkOfSeckillMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfSeckillMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }

    //========================================================================================================================================

    @NeedPermission(permissionName = "总部商城商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "总部商城审核")
    @RequestMapping("/listOfLocalSpecialMall")
    public String listOfLocalSpecialMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsOfLocalSpecialtyMall> pageResult = reGoodsOfLocalSpecialtyMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfLocalSpecialMall";
    }

    @RequestMapping("/detailOfLocalSpecialMall")
    public String detailOfLocalSpecialMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfLocalSpecialtyMall reGoodsOfLocalSpecialtyMall = reGoodsOfLocalSpecialtyMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfLocalSpecialtyMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfLocalSpecialMall";
    }

    @RequestMapping("/checkOfLocalSpecialMall")
    @ResponseBody
    public Map<String, Object> checkOfLocalSpecialMall(String checkDesc, Boolean isPass, Integer goodsId,Integer fenyong) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfLocalSpecialtyMallService.doCheck(returnMamp, isPass, checkDesc, goodsId,fenyong);
    }

   
    
    @NeedPermission(permissionName="换货帖子审核列表",classifyName="商品审核")
    @IsItem(firstItemName="商品审核",secondItemName="换货帖子审核")
    @RequestMapping("/listOfChangeNoteMall")
    public String listOfChangeNoteMall(HttpServletRequest request, Integer currentPage, Integer pageSize){
    	 //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        PageResult<ChangeNote> pageResult = reChangeNoteService.getCheckPageresult(currentPage, pageSize);
        request.setAttribute("pageResult", pageResult);
    	return "goods/goodsCheck/listOfChangeNoteMall";
    }
    
    
    @RequestMapping("/detailOfChangeNoteMall")
    public String detailOfChangeNoteMall(HttpServletRequest request, Integer checkId) throws Exception{
    	
    	ChangeNote changeNote = reChangeNoteService.get(checkId);
    	request.setAttribute("changeNote", changeNote);
    	return "goods/goodsCheck/detailOfChangeNoteMall";
    }
    
    @RequestMapping("/checkOfChangeNoteMall")
    @ResponseBody
    public Map<String, Object> checkOfChangeNoteMall(Boolean isPass, Integer checkId,String checkRemark) throws Exception{
    	 //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);
    	return reChangeNoteService.doCheck(returnMamp, isPass, checkId,checkRemark);
    }
    
    
    @RequestMapping("/noteGoodsdetail")
    public String noteGoodsdetail(HttpServletRequest request, Integer checkId) throws Exception {
    	String basePath = "http://jifen.aixiaoping.cn:8080/dailyRes";
    	ChangeNote changeNote = reChangeNoteService.get(checkId);
    	List<Map<String, Object>> detailList = new ArrayList<Map<String,Object>>();
    	if (changeNote.getGoodsIds()!=null) {
    		String goodsIds = changeNote.getGoodsIds();
    		if (goodsIds.startsWith("[")) {
    			Map<String, Object> detailMap = new HashMap<String, Object>();
    			goodsIds = goodsIds.substring(1,goodsIds.length()-1);
				List<ReGoodsOfChangeMall> clist = reGoodOfChangeMallDao.findByIds(goodsIds);
				for (ReGoodsOfChangeMall changeMall : clist) {
					ReGoodsSnapshot goodsSnapshot = changeMall.getSnapshotGoods();
					if (goodsSnapshot!=null) {
						String covers = basePath+goodsSnapshot.getCoverPicOne();
						detailMap.put("imgUrl", covers);
						detailList.add(detailMap);
					}
				}
			
			}
    		request.setAttribute("noteGoodsdetail", JSON.toJSON(detailList));
		}else{
			request.setAttribute("noteGoodsdetail", detailList);
		}
    	
    	return "goods/goodsCheck/noteGoodsDetails";
    	
    }
    
    //========================================================================================================================================
    @NeedPermission(permissionName = "推广商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "推广商品审核")
    @RequestMapping("/listOfExtendMall")
    public String listOfExtendMall(HttpServletRequest request, Integer currentPage, Integer pageSize){
    	//检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        Integer adminId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsofextendmall> pageResult = reGoodsofextendmallService.getCheckPageresult(currentPage, pageSize,adminId);
        request.setAttribute("pageResult", pageResult);
    	return "goods/goodsCheck/listOfExtendMall";
    }
    
    @NeedPermission(permissionName = "优惠券商品置顶", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "优惠券商品置顶")
    @RequestMapping("/listOfCouponMallTop")
    public String listOfCouponMallTop(HttpServletRequest request, Integer currentPage, Integer pageSize,String searchName){
    	//检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        request.setAttribute("searchName", searchName);
        Integer adminId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsofextendmall> pageResult = reGoodsofextendmallService.findTopPageresult(currentPage, pageSize,adminId,searchName);
        request.setAttribute("pageResult", pageResult);
    	return "goods/mallGoods/couponMallTopList";
    }
    
    @ResponseBody
    @RequestMapping("/rejectExtendMallTop")
    public ResponseResult  rejectExtendMallTop(Integer id){
    	ResponseResult result=new ResponseResult();
    	try {
    		reGoodsofextendmallService.rejectExtendMallTop(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("失败");
			result.setSuccess(false);
		}
    	
    	return result;
    }
    
    
    @RequestMapping("/detailOfExtendMall")
    public String detailOfExtendMall(HttpServletRequest request, Integer checkGoodsId){
    	ReGoodsofextendmall reGoodsofextendmall = reGoodsofextendmallService.getcheckGoodsById(checkGoodsId);
    	request.setAttribute("reGoodsofextendmall", reGoodsofextendmall);
    	return "goods/goodsCheck/detailOfExtendsMall";
    }
    
    @RequestMapping("/checkOfExtendMall")
    @ResponseBody
    public Map<String, Object> checkOfExtendMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsofextendmallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    } 
    
    
    /**
     * 推广商品置顶
     * @return
     */
    @ResponseBody
    @RequestMapping("/extendMallTop")
    public ResponseResult extendMallTop(HttpServletRequest request,Integer id){
    		ResponseResult result=new ResponseResult();
    	try {
    		reGoodsofextendmallService.extendMallTop(id);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("更新失败");
			e.printStackTrace();
		}
    	return result;
    }
    
    
    //========================================================================================================================================

    @NeedPermission(permissionName = "会员免单商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "免单专区审核")
    @RequestMapping("/listOfMemberMall")
    public String listOfMemberMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        PageResult<ReGoodsOfMemberMall> pageResult = reGoodsOfMemberMallService.getCheckPageresult(currentPage, pageSize);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfMemberMall";
    }

    @RequestMapping("/detailOfMemberMall")
    public String detailOfMemberMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfMemberMall reGoodsOfMemberMall = reGoodsOfMemberMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfMemberMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfMemberMall";
    }

    @RequestMapping("/checkOfMemberMall")
    @ResponseBody
    public Map<String, Object> checkOfMemberMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfMemberMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }

    //========================================================================================================================================

    @NeedPermission(permissionName = "99特惠商品审核列表", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "99特惠审核")
    @RequestMapping("/listOfNineNineMall")
    public String listOfNimeNimeMall(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        PageResult<ReGoodsOfNineNineMall> pageResult = reGoodsOfNineNineMallService.getCheckPageresult(currentPage, pageSize);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfNineNineMall";
    }

    @RequestMapping("/detailOfNineNineMall")
    public String detailOfNineNineMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfNineNineMall reGoodsOfNineNineMall = reGoodsOfNineNineMallService.get(checkGoodsId);
        Integer baseGoodsId = reGoodsOfNineNineMall.getBaseGoodsId();

        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
        request.setAttribute("goodsOfBase", reGoodsOfBase);

        reBaseGoodsService.returnBack(request, baseGoodsId, true, false);
        return "goods/goodsCheck/detailOfNineNineMall";
    }

    @RequestMapping("/checkOfNineNineMall")
    @ResponseBody
    public Map<String, Object> checkOfNineNineMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception {

        //返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);

        return reGoodsOfNineNineMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }

    //========================================================================================================================================

    @RequestMapping("/goodsDetails")
    public String goodsDetails(HttpServletRequest request, Integer baseGoodsId) throws Exception {
    	String basepath = "http://jifen.aixiaoping.cn:8080/dailyRes";
        try {
        	
            ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(baseGoodsId);
            ReGoodsDetails goodsDetails = reGoodsDetailsService.getByBaseGoods(reGoodsOfBase);
            if (goodsDetails.getIsNew()!=null && goodsDetails.getIsNew()==1) {
            	Map<String, Object> detailMap = null;
            	List<Map<String, Object>> detailList = new ArrayList<Map<String,Object>>();
				String contect = goodsDetails.getContent();
				JSONArray jsonArray = JSONArray.parseArray(contect);
				for (int i = 0; i < jsonArray.size(); i++) {
					 String keyValue =jsonArray.getJSONObject(i).getString("detailType");
					 String text = null;
					 String picture = null;
					 if (keyValue.equals("text")) {
						 detailMap = new HashMap<String, Object>();
						text = jsonArray.getJSONObject(i).getString("text");
						 detailMap.put("text", text);
						 detailList.add(detailMap);
					}else{
						detailMap = new HashMap<String, Object>();
						picture= basepath+jsonArray.getJSONObject(i).getString("picture");
						 detailMap.put("picture", picture);
						 detailList.add(detailMap);
					}
					
				}
				request.setAttribute("contect",JSONArray.toJSON(detailList));
				return "goods/goodsCheck/newGoodsDetails";
			}else{
				request.setAttribute("content", goodsDetails.getContent());
			}
            
        } catch (Exception e) {
            request.setAttribute("content", "商品没有录入详情信息，需要您重新录入；");
            e.printStackTrace();
        }

        return "goods/goodsCheck/goodsDetails";
    }
    
    
    
    
    //========================================================================================================================================

    @NeedPermission(permissionName = "优惠券全国置顶", classifyName = "商品审核")
    @IsItem(firstItemName = "商品审核", secondItemName = "优惠券全国置顶")
    @RequestMapping("/CouponisTop")
    public String CouponisTop(HttpServletRequest request,HttpServletResponse response){
    	request.setAttribute("uri", "http://hhh.aixiaoping.cn/index.php/Admin/Topper/index");
    	return "goods/goodsCheck/coupon";
    }

    
    
    @NeedPermission(permissionName="拼团商品审核",classifyName = "商品审核")
    @IsItem(firstItemName="商品审核" ,secondItemName="拼团商品审核")
    @RequestMapping("/listOfTeamMall")
    public String listOfTeamMall(HttpServletRequest request, Integer currentPage, Integer pageSize){
    	//检查参数；
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;

        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        PageResult<ReGoodsOfTeamMall> pageResult = reGoodsOfTeamMallService.getCheckPageresult(currentPage, pageSize,adminUserId);
        request.setAttribute("pageResult", pageResult);
        return "goods/goodsCheck/listOfTeamMall";
    }
    
    
    @RequestMapping("/detailOfTeamMall")
    public String detailOfTeamMall(HttpServletRequest request, Integer checkGoodsId) {

        ReGoodsOfTeamMall reGoodsOfTeamMall = reGoodsOfTeamMallService.get(checkGoodsId);

        ReGoodsSnapshot goodsSnapshot = reGoodsOfTeamMall.getSnapshotGoods();
        ReGoodsOfBase reGoodsOfBase = reGoodsOfBaseService.get(goodsSnapshot.getBaseGoodsId());
        ReGoodsOfSellerMall reGoodsOfSellerMall = reBaseGoodsDAO.getMallObjByBaseGoods(reGoodsOfBase, ReGoodsOfSellerMall.class);
        request.setAttribute("goodsSnapshot", goodsSnapshot);
        request.setAttribute("reGoodsOfTeamMall", reGoodsOfTeamMall);
        request.setAttribute("goodsOfBase", reGoodsOfBase);
        if (reGoodsOfSellerMall!=null && !reGoodsOfSellerMall.getIsNoStandard()) {
			request.setAttribute("reGoodsOfTeamMallFirstStandards", getFirstStandardsInputs(reGoodsOfSellerMall.getParentStandardList()));
			request.setAttribute("reGoodsOfTeamMallTableHead", getTableHead(reGoodsOfSellerMall.getParentStandardList()));
			request.setAttribute("reGoodsOfTeamMallTableLine",
					getTableLine(reGoodsOfSellerMall.getStandardDetailsList(), reGoodsOfBase.getId(), true, false));
		}
        return "goods/goodsCheck/detailOfTeamMall";
    }
    
    @RequestMapping("/checkOfTeamMall")
    @ResponseBody
    public Map<String, Object> checkOfTeamMall(String checkDesc, Boolean isPass, Integer goodsId) throws Exception{
    	//返回值；
        Map<String, Object> returnMamp = new HashMap<>();
        returnMamp.put("success", false);
        return reGoodsOfTeamMallService.doCheck(returnMamp, isPass, checkDesc, goodsId);
    }
    
    
    private String getFirstStandardsInputs(List<Map<String, Object>> parentStrandardList) {
        StringBuilder sb = new StringBuilder(500);
        if (parentStrandardList != null) {
            for (Map<String, Object> each : parentStrandardList) {
                sb.append("<input type=\"hidden\" name=\"firstStandardKey[]\" value=\"").append(each.get("standardId")).append("\">");
                sb.append("<input type=\"hidden\" name=\"firstStandardName[]\" value=\"").append(each.get("standardName")).append("\">");
            }
        }
        return sb.toString();
    }
    
    
    private String getTableHead(List<Map<String, Object>> parentStrandardList) {
        StringBuilder sb = new StringBuilder(100);
        if (parentStrandardList != null) {
        	for (int i = 0; i <parentStrandardList.size(); i++) {
        		 sb.append("<th>").append(parentStrandardList.get(i).get("standardName")).append("</th>");
			}
        }
        return sb.toString();
    }
    
    private String getTableLine(List<Map<String, Object>> secondStandards, Integer mallType, Boolean isForShow, Boolean isEditWithoutDel) {

        isForShow = isForShow == null ? false : isForShow;
        isEditWithoutDel = isEditWithoutDel == null ? false : isEditWithoutDel;

        //不能同时既是为了展示，有事为了补充库存；
        if (isForShow && isEditWithoutDel) {
            isForShow = false;
            isEditWithoutDel = false;
        }

        StringBuilder sb = new StringBuilder(500);
        if (secondStandards == null)
            return sb.toString();
        for (Map<String, Object> each : secondStandards) {
            sb.append("<tr>");

          //如果有第一个规格属性，拼接第一个；
            if (StringUtil.hasLength((String) each.get("id1"))) {
                sb.append("<td>").
                        append((String) each.get("name1")).
                        append("<input name=\"detailsName1[]\" type=\"hidden\" value=\"").append((String) each.get("id1")).append("\">").
                        append("<input name=\"detailsValue1[]\" type=\"hidden\" value=\"").append((String) each.get("name1")).append("\">").
                        append("</td>");
            }
            

            //如果有第二个规格属性，再拼接第二个；
            if (StringUtil.hasLength((String) each.get("id2"))) {
                sb.append("<td>").
                        append((String) each.get("name2")).
                        append("<input name=\"detailsName2[]\" type=\"hidden\" value=\"").append((String) each.get("name2")).append("\">").
                        append("<input name=\"detailsValue2[]\" type=\"hidden\" value=\"").append((String) each.get("id2")).append("\">").
                        append("</td>");
            }
          //如果有第三个规格属性，拼接第三个；
            if (StringUtil.hasLength((String) each.get("id3"))) {
                sb.append("<td>").
                        append((String) each.get("name3")).
                        append("<input name=\"detailsName3[]\" type=\"hidden\" value=\"").append((String) each.get("id3")).append("\">").
                        append("<input name=\"detailsValue3[]\" type=\"hidden\" value=\"").append((String) each.get("name3")).append("\">").
                        append("</td>");
            }
            //拼接删除按钮，和结尾；
            if (isEditWithoutDel || isForShow) {
                sb.append("<td><input type=\"button\" value=\"不可操作\"></td></tr>");
            } else {
                sb.append("<td><input type=\"button\" onclick=\"delTr(this);\" value=\"删除\"></td></tr>");
            }
        }
        return sb.toString();
    }
}





