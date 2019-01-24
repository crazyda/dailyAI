package com.axp.action.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.IReGoodsStandardDao;
import com.axp.model.ReGoodsStandard;
import com.axp.query.PageResult;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Controller
@RequestMapping("/reGoodsStandard")
public class ReGoodsStandartAction extends BaseAction {
	@Autowired
	private IReGoodsStandardDao reGoodsStandardDAO;
    /**
     * 展示商品规格的具体属性；
     *
     * @return
     */
    @NeedPermission(permissionName = "商品规格列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "商品规格列表")
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Integer currentPage, Integer pageSize) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        //查询出所有的商品规格，并放到session中；
        PageResult<ReGoodsStandard> result = reGoodsStandardService.getReGoodsStandardsByAdminUserId(adminUserId, currentPage, pageSize);

        request.getSession().setAttribute("pageResult", result);
        return "goods/goodsStandard/list";
    }

    /**
     * 进入编辑界面，根据有无id值，决定是新增还是编辑；
     *
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Integer id, HttpServletRequest request) {
        if (id != null) {//编辑商品；
        	ReGoodsStandard r = reGoodsStandardDAO.findById(id);
            List<ReGoodsStandard> list = reGoodsStandardService.getGoodsStandardAndDetails(id);
            request.setAttribute("reGoodsStandard", r);
          // request.setAttribute("reGoodsStandardList", list);
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
        } else if (details == null || details.size() == 0) {
            map.put("message", "请输入商品规格明细；");
            return map;
        }
        //遍历集合，将空字符串剔除掉；
        for (int i = 0; i < details.size(); i++) {
			String each = details.get(i);
			if (StringUtil.isEmpty(each)) {
				details.remove(i);
			}
		}
        
        
        for (int i = 0; i < detailsId.size(); i++) {
			String each = detailsId.get(i);
			String [] split = each.split(",");
			if (split.length==1) {
				detailsId.remove(i);
			}
		}
        //再检查一遍；
        if (details.size() == 0) {
            map.put("message", "请输入商品规格明细；");
            return map;
        }

        //检查规格是否已经存在；
        if(id==null){
        	boolean bb = reGoodsStandardService.checkExist(name, adminUserId);
        	if (bb) {
        		map.put("message", "该规格分类已经存在；");
        		return map;
        	}
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
    
    @RequestMapping("getGoodsStandardByParent")
    @ResponseBody
    public Map<String,Object> getGoodsStandardByParent(HttpServletRequest request){
    	String id = request.getParameter("id");
    	QueryModel queryModel = new QueryModel();
    	queryModel.combPreEquals("isValid", true);
    	queryModel.combPreEquals("parentStandardId", Integer.parseInt(id));  	
    	List<ReGoodsStandard> standardList = dateBaseDao.findLists(ReGoodsStandard.class, queryModel);
    	Map<String,Object> map = new HashMap<>();
    	List<Map<String,Object>> list = new ArrayList<>();
    	Map<String,Object> standardMap = null;
    	for(ReGoodsStandard r:standardList){
    		standardMap = new HashMap<>();
    		standardMap.put("id", r.getId());
    		standardMap.put("name", r.getName());
    		list.add(standardMap);
    	}
    	map.put("data", list);
    	return map;
    }
}
