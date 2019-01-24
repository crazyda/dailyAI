package com.axp.service.goods.impl;


import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jsx3.vector.Rectangle;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.dao.IReBackOrderDao;
import com.axp.dao.ReBaseGoodsDAO;
import com.axp.dao.ReGoodsOfTeamMallDao;
import com.axp.dao.ReGoodsorderItemDAO;
import com.axp.dao.impl.ReBackOrderDaoImpl;
import com.axp.model.ReBackOrder;
import com.axp.model.ReBaseGoods;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsOfSellerMall;
import com.axp.model.ReGoodsOfTeamMall;
import com.axp.model.ReGoodsSnapshot;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.model.Seller;
import com.axp.query.PageResult;
import com.axp.service.goods.ReGoodsOfTeamMallService;
import com.axp.service.order.ReBackOrderService;
import com.axp.service.system.impl.BaseServiceImpl;
import com.axp.util.DateUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;
@Service
public class ReGoodsOfTeamMallServiceImpl extends BaseServiceImpl implements ReGoodsOfTeamMallService{

	@Autowired
	ReGoodsorderItemDAO reGoodsorderItemDao;
	@Autowired
	ReBaseGoodsDAO reBaseGoodsDAO;
	@Autowired
	ReGoodsOfTeamMallDao reGoodsOfTeamMallDao;
	@Autowired
	ReBackOrderService reBackOrderService;
	public void findReGoodsTeamList(HttpServletRequest request,Integer currentPage,String search_name,Integer type){
		
		
		QueryModel model=new QueryModel();
		if(StringUtils.isNotBlank(search_name)){
			model.combLike("name", search_name,QueryModel.MATCH_ALL);
		}
		if(type>0){
			model.combEquals("topType", type);
		}
		model.combEquals("isValid", 1);
		model.combEquals("isChecked", 1);
		model.combCondition("shelvesTime >= sysdate()");
		model.combCondition("addedTime <= sysdate()");
		List<ReGoodsOfTeamMall> list = dateBaseDao.findPageList(ReGoodsOfTeamMall.class, model, (currentPage-1)*pageSize, pageSize);
		request.setAttribute("search_name", search_name);
		request.setAttribute("result", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("totalCount", dateBaseDao.findCount(ReGoodsOfTeamMall.class, model));
		request.setAttribute("type", type);
		
	}
	
	public Map<String, Object> stick(HttpServletRequest request){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			    map.put("status", 1);
			    map.put("message", "请求成功");
			
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		// 1大图 2小图 null无
		Integer type=StringUtils.isBlank(request.getParameter("type"))?null:Integer.parseInt(request.getParameter("type"));
		
		ReGoodsOfTeamMall teamMall = reGoodsOfTeamMallDao.findByIdValid(id);
		teamMall.setTopType(type);
		reGoodsOfTeamMallDao.merge(teamMall);
		
		} catch (Exception e) {
			System.out.println("图片上传异常"+e.getMessage());
		}
		return map;
		
		
	}
	

	@Override
	public PageResult<ReGoodsOfTeamMall> getCheckPageresult(Integer currentPage, Integer pageSize,
			Integer adminUserId) {
		return  reGoodsOfTeamMallDao.getCheckPageresult(currentPage, pageSize,adminUserId);
	}

	@Override
	public ReGoodsOfTeamMall get(Integer checkGoodsId) {
		return reGoodsOfTeamMallDao.findById(checkGoodsId);
	}

	@Override
	public Map<String, Object> doCheck(Map<String, Object> returnMamp, Boolean isPass, String checkDesc,
			Integer goodsId) throws Exception {
		ReGoodsOfTeamMall goodsOfTeamMall = reGoodsOfTeamMallDao.findById(goodsId);
		if (isPass) {
			goodsOfTeamMall.setIsChecked(true);
		}else{
			goodsOfTeamMall.setIsChecked(null);
		}
		goodsOfTeamMall.setCheckDesc(checkDesc);
		reGoodsOfTeamMallDao.update(goodsOfTeamMall);
		
		//返回返回值；
        returnMamp.put("success", true);
        returnMamp.put("message", "操作完成；");
        return returnMamp;
	}
	
	
	/**
	 * 拼团自动退单
	 */
	public void teamOrderTimedtask(){
		try {
		
		List<ReGoodsorderItem> orderList = reGoodsOfTeamMallDao.getTeamOrderOverdue();
		
		for (ReGoodsorderItem item : orderList) {
			
			List<ReBackOrder> backOrderList = reBackOrderDao.findByPropertyWithValid("orderItem.id",item.getId());
			if(backOrderList.size()>0){
				System.out.println("该订单已有退款记录  reGoodsOrder表订单号为:"+item.getOrder().getId());
				continue;
			}
			
			ReGoodsorder goodsorder = item.getOrder();
			if(goodsorder.getPayType()==ReGoodsorder.PAYTYPE_zfb){
				//如果是支付宝支付那么就待退款
				
			reBackOrderDao.saveBackOrder(item, item.getUser(), "拼团超时自动退单", 0, item.getGoodImg(),ReBackOrder.BACKSTATE_yishenhe);
			item.setIsBack(ReGoodsorder.tong_yi_tui_dan); //修改订单项状态
			reGoodsorderItemDao.update(item);
				
			goodsorder.setIsHasItems(false);
			regoodsorderDAO.update(goodsorder);
			
			adminuserCashpointRecordDAO.delRecordByOrderItemId(item);
			
			iCashmoneyRecordDao.updateMoneyById(item.getId());
			}else{
				ReBackOrder reBackOrder= reBackOrderDao.saveBackOrder(item, item.getUser(), "拼团超时自动退单", 0, item.getGoodImg(),ReBackOrder.BACKSTATE_yishenhe);
				reBackOrderService.chargeback(reBackOrder.getId());
			}
		}
			
		} catch (Exception e) {
			if(!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
	}

	@Override
	public Map<String, Object> uploadPic(HttpServletRequest request) {
		Map<String, Object> m=new HashMap<>();
		m.put("status", 1);
		m.put("message", "请求成功");
			try {
			Integer id = Integer.parseInt( request.getParameter("id"));
			String path=request.getParameter("path");
			
			ReGoodsOfTeamMall teamMall = reGoodsOfTeamMallDao.findByIdValid(id);
			teamMall.setCarouselPic(path);
			
			reGoodsOfTeamMallDao.merge(teamMall);
			} catch (Exception e) {
				m.put("status", -1);
				m.put("message", "请求失败");			
			}
		return m;
	}

	@Override
	public void doSavePut(HttpServletRequest request) throws Exception {
		String repertory=request.getParameter("repertory"); //库存 
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String goodsOrder = request.getParameter("goodsOrder");
		String purchaseNum=request.getParameter("purchaseNum"); //限购数量
		String  discountPrice=request.getParameter("discountPrice"); //拼团折扣价
		ReGoodsOfSellerMall reGoodsOfSellerMall=null;
		ReGoodsOfTeamMall goodsOfTeamMall=null;
		Integer goodsId=Integer.parseInt(goodsOrder.substring(3,goodsOrder.length()));
		if(goodsOrder.startsWith("tim")){
			goodsOfTeamMall=reGoodsOfTeamMallDao.findById(goodsId);
			reGoodsOfSellerMall=goodsOfTeamMall.getReGoodsOfSellerMall();
			
		}else{
			goodsOfTeamMall=new ReGoodsOfTeamMall();
			reGoodsOfSellerMall = reGoodsOfSellerMallDAO.findById(goodsId);
		}
		
		
		ReGoodsOfBase baseGoods = reGoodsOfBaseDAO.findById(reGoodsOfSellerMall.getBaseGoodsId());
		ReGoodsSnapshot reGoodsSnapshot = reGoodsOfSellerMall.getSnapshotGoods();
		Seller seller=baseGoods.getSeller();
		ReGoodsOfTeamMall teamMall = new ReGoodsOfTeamMall();
		teamMall.setIsValid(true);
		teamMall.setCreateTime(new Timestamp(System.currentTimeMillis()));
		teamMall.setName(reGoodsSnapshot.getName());
		teamMall.setBaseGoodsId(baseGoods.getId());
		teamMall.setCoverPic(baseGoods.getCoverPic());
		teamMall.setDescriptionPics(baseGoods.getDescriptionPics());
		teamMall.setReGoodsOfSellerMall(reGoodsOfSellerMall);
		teamMall.setTeamNum(2);
		teamMall.setNoStandardPrice(reGoodsOfSellerMall.getNoStandardPrice());
		teamMall.setTransportationType(reGoodsOfSellerMall.getTransportationType());
		teamMall.setTransportationPrice(reGoodsOfSellerMall.getTransportationPrice());
		teamMall.setReleaseNum(Integer.parseInt(repertory));
		teamMall.setDiscountPrice(Double.valueOf(discountPrice));
		teamMall.setDisplayPrice(reGoodsOfSellerMall.getDisplayPrice());
		teamMall.setStandardDetails(reGoodsOfSellerMall.getStandardDetails());
		teamMall.setRightsProtect(reGoodsOfSellerMall.getRightsProtect());
		teamMall.setIsNoStandard(reGoodsOfSellerMall.getIsNoStandard());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		Date d2;
		try {
			d = sf.parse(startTime);
			d2= sf.parse(endTime);
			teamMall.setAddedTime(new Timestamp(d.getTime()));
			teamMall.setShelvesTime(new Timestamp(d2.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		teamMall.setSeller(seller);
		teamMall.setIsChecked(false);
		teamMall.setSnapshotGoods(reGoodsSnapshot);
		if (StringUtils.isNotBlank(purchaseNum)) {
			teamMall.setIsRestrict(true);
			teamMall.setRestrictNum(Integer.parseInt(purchaseNum));
		}else{
			teamMall.setIsRestrict(false);
		}
		reGoodsOfTeamMallDao.saveOrUpdate(teamMall);
		teamMall.setGoodsOrder(ReBaseGoods.teamMall+teamMall.getId());
		reGoodsOfSellerMallDAO.update(reGoodsOfSellerMall);
	
		
		try {
			String changeLaunchMall = baseGoods.changeLaunchMall(baseGoods.getLaunchMall()==null?"000000000":baseGoods.getLaunchMall(), 8, true);
			baseGoods.setLaunchMall(changeLaunchMall);
			reGoodsOfBaseDAO.update(baseGoods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
