package com.axp.action.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.DateBaseDAO;
import com.axp.model.AdminUser;
import com.axp.model.OrderComment;
import com.axp.model.ReGoodsOfBase;
import com.axp.model.ReGoodsStandard;
import com.axp.model.ReGoodsorder;
import com.axp.model.ReGoodsorderItem;
import com.axp.query.PageResult;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Controller
@RequestMapping("/reOrder")
public class ReOrderAction extends BaseAction {

	@Autowired
	private DateBaseDAO dateBaseDAO;

    /**
     * 展示商品规格的具体属性；
     *
     * @return
     */
    @NeedPermission(permissionName = "订单列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "订单列表")
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Integer currentPage,Integer draw) {

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

        reGoodsorderService.getOrderList(adminUserId,request,currentPage,draw);
        return "order/merchant-order";
    }
    
    @RequestMapping("/ordersDetail")
    public String ordersDetail(HttpServletRequest request){
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
        reGoodsorderService.getOrdersDetailList(adminUserId,request);
        return "/order/ordersDetail";
    }
    /**
     * 跳转至订单详情-待确认页面
     * @return
     */
    @RequestMapping("/confirm")
    public String confirm(HttpServletRequest request){
    	Integer status = Integer.parseInt(request.getParameter("status"));
    	switch (status) {
		case 0:reGoodsorderService.getOrderById(request);return "order/confirm";
		case 1:reGoodsorderService.getOrderById(request);return "order/to-ship";
		case 2:reGoodsorderService.getOrderById(request);return "order/exchange";
		default:
			break;
		}
    	//reGoodsorderService.getOrderById(request);
    	return "order/confirm";
    }
    /**
     * 详情页面
     * @param request
     * @return
     */
    @RequestMapping("/details")
    public String details(HttpServletRequest request){
    	Integer status = Integer.parseInt(request.getParameter("status"));
    	switch (status) {
		case 0:reGoodsorderService.getOrderById(request);return "order/detail";
		case 1:reGoodsorderService.getOrderById(request);return "order/detail";
		case 2:reGoodsorderService.getOrderById(request);return "order/detail";
		case 3:reGoodsorderService.getOrderById(request);return "order/inbound";
		case 4:reGoodsorderService.getOrderById(request);return "order/completed";
		case 5:reGoodsorderService.getItemById(request);return "order/evaluat-ed";
		default:
			break;
		}
    	return null;
    }
    /**
     * 修改订单状态
     * @param request
     * @return
     */
    @RequestMapping("/changeStatus")
    public String changeStatus(HttpServletRequest request,RedirectAttributes reAttributes){
    	Integer status = Integer.parseInt(request.getParameter("status"));
    	reAttributes.addAttribute("status", status);
    	switch (status) {
		case 0:reGoodsorderService.changeStatus(request,status);return "redirect:list";
		case 1:reGoodsorderService.changeStatus(request,status);return "redirect:list";	
		default:
			break;
		}
    	return "redirect:list";
    }
    
    /**
     * 判断兑换码是否正确，正确则兑换
     * @param request
     * @return
     */
    @RequestMapping("/exchange")
    @ResponseBody
    public Map<String,Object> exchange(HttpServletRequest request,RedirectAttributes reAttributes){
    	String code = request.getParameter("code");
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	Integer status = Integer.parseInt(request.getParameter("status"));
    	reAttributes.addAttribute("status", status);
    	QueryModel queryModel = new QueryModel();
    	queryModel.clearQuery();
    	queryModel.combPreEquals("id", id);
    	ReGoodsorder reGoodsorder = (ReGoodsorder) dateBaseDAO.findOne(ReGoodsorder.class, queryModel);
    	Map<String,Object> map = new HashMap<>();
    	if(code.contains(reGoodsorder.getCode())){
    		map.put("status", true);
    		reGoodsorderService.changeStatus(request,status);
    	}else{
    		map.put("status", false);
    	}
    	return map;
    }
    
    @RequestMapping("/comment")
    @ResponseBody
    public Map<String,Object> comment(HttpServletRequest request){
    	Map<String,Object> map = new HashMap<>();
    	String licenseother = request.getParameter("licenseother");
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	QueryModel queryModel = new QueryModel();
    	queryModel.combPreEquals("reGoodsorderItem.id", id,"itemId");
    	List<OrderComment> list = dateBaseDAO.findLists(OrderComment.class, queryModel);
    	if(list.size()>0){
    		OrderComment orderComment = list.get(0);
    		String comment = orderComment.getComment();
    		List<JSONObject> jList = JSONArray.parseArray(comment,JSONObject.class);
    		if(jList.size()>0){
    			JSONObject jsonObject = jList.get(0);
    			jsonObject.put("reply", licenseother);
    			JSONArray jsonArray = new JSONArray();
    			jsonArray.add(jsonObject);
    			orderComment.setComment(jsonArray.toString());
    			reGoodsorderService.updateComment(orderComment);
    			map.put("status", true);
    			return map;
    		}
    	}
    	map.put("status", false);
    	return map;
    }
    
   /* @RequestMapping("/batchHandle")
    @ResponseBody
    public String batchHandle(HttpServletRequest request){
    	String[] checkbox = request.getParameterValues("checkbox");
    	Integer status = Integer.parseInt(request.getParameter("status"));
    	for(String orderId:checkbox){
    		reGoodsorderService.changeStatus(request,status);
    	}
    
    	
    	return "";
    }*/
    

    /**
     * 进入编辑界面，根据有无id值，决定是新增还是编辑；
     *
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Integer id, HttpServletRequest request) {
        if (id != null) {//编辑商品；
            List<ReGoodsStandard> list = reGoodsStandardService.getGoodsStandardAndDetails(id);
            request.getSession().setAttribute("reGoodsStandard", list);
        }
        return "goods/goodsStandard/add";
    }

    /**
     * 保存商品规格，及其明细；
     *
     * @param name
     * @param details
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> save(HttpServletRequest request, String name, @RequestParam("details[]") List<String> details, @RequestParam("detailsId[]") List<String> detailsId,Integer id) {

        //返回值；
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "0");

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            map.put("message", "请登录");
            return map;
        }

        //参数检查--要求name一定要有值；
        if (StringUtil.isEmpty(name)) {
            map.put("message", "请输入一级商品规格");
            return map;
        }

        Boolean b;
        if (id == null) {//保存商品规格；
            b = reGoodsStandardService.doSave(name, details, adminUserId);
        } else {//修改商品规格；
            b = reGoodsStandardService.doEdit(id, adminUserId, name, details,detailsId);
        }
        if (b) {//保存或者修改成功；
            map.put("success", "1");
            map.put("message", "保存或修改成功");
            return map;
        }

        //保存失败；
        map.put("message", "保存或修改失败");
        return map;
    }

    /**
     * 删除数据：
     * 1，删除id为id值的一级商品规格对象；
     * 2，删除与这个一级商品规格对象对应的二级商品规格对象；
     *
     * @param id      需要删除的一级商品规格的id值；
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id, HttpServletRequest request) {

        //返回值；
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "0");

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            map.put("message", "请登录");
            return map;
        }

        //参数检查，id不能为空；
        if (id == null) {
            map.put("message", "您输入的id值为空");
            return map;
        }

        //执行删除操作；
        Boolean b = reGoodsStandardService.doDelete(id);
        if (b) {
            map.put("success", "1");
            map.put("message", "删除数据成功");
            return map;
        }

        //删除失败；
        map.put("message", "未知错误，删除失败");
        return map;
    }
    
    @RequestMapping("/ensureByAjax")
    @ResponseBody
    public Map<String,Object> ensureByAjax(HttpServletRequest request){
    	
    	return reGoodsorderService.ensureByAjax(request);
    }
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request){
    	String id = request.getParameter("id");
    	QueryModel queryModel = new QueryModel();
    	queryModel.clearQuery();
    	queryModel.combPreEquals("id",Integer.parseInt(id));
    	ReGoodsorder reGoodsorder = (ReGoodsorder) dateBaseDAO.findOne(ReGoodsorder.class, queryModel);
    	request.setAttribute("reGoodsorder", reGoodsorder);
    	return "order/detail";
    }
   
    @RequestMapping("/updateDetail")
    public String updateDetail(HttpServletRequest request,Integer id){  	
    	reGoodsorderService.updateDetail(request, id);
    	return "redirect:list";
    }
    /**
     * 中奖信息
     * @param request
     * @param currentPage
     * @param pageSize
     * @param searchWord
     * @return
     */
    @NeedPermission(permissionName = "中奖信息", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "中奖信息")
    @RequestMapping("/lockMallList")
    public String lockMallList(HttpServletRequest request, HttpServletResponse response,Integer currentPage,String searchWord) {
    	//reGoodsorderService.getItemList(request,currentPage);
    	 if (currentPage == null || currentPage < 1) {
             currentPage = 1;
         }
    	 String btn = request.getParameter("btn");
    	 String typeId = request.getParameter("typeId")==null?"3":request.getParameter("typeId");
    	 if("导出Excel".equals(btn)){
    		 reGoodsorderService.excelPortList(request,response,typeId);
    	 }else{
    		 PageResult<ReGoodsorderItem> result = reGoodsorderService.getItemList(request,currentPage,typeId);
    		 
    		 request.setAttribute("pageResult", result);
    	 }
    	request.setAttribute("typeId", typeId);
        return "goods/mallGoods/gameDarw";
    }
    
    @RequestMapping("/excelPortList")
    @ResponseBody
    public String excelPortList(HttpServletRequest request,HttpServletResponse response){
    	String typeId = request.getParameter("typeId")==null?"3":request.getParameter("typeId");
    	reGoodsorderService.excelPortList(request,response,typeId);
		return "goods/mallGoods/gameDarw";
    	
    }
    
    
} 
