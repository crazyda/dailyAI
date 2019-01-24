package com.axp.action.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.ReItem;
import com.axp.model.RePermission;
import com.axp.model.RePermissionRole;
import com.axp.query.PageResult;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 操作权限相关的方法；
 */

@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {

    /**
     * 展示当前登录用户所拥有的角色；
     *
     * @param request     请求对象；
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @return
     */
    @NeedPermission(permissionName = "角色列表", classifyName = "权限管理")
    @IsItem(firstItemName = "权限管理", secondItemName = "角色列表")
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Integer currentPage, Integer pageSize, String permissionName) {

        //检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "登录异常");
            return "error/error";//登录出错情况；
        }
        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        PageResult<RePermissionRole> result = rePermissionRoleService.getRoleList(adminUserId, currentPage, pageSize, permissionName);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        request.getSession().setAttribute("permissionName", permissionName);
        return "permission/role/list";
    }

    @NeedPermission(permissionName = "删除角色", classifyName = "权限管理")
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer id) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        //删除时需要判断当前角色是否被使用，如果使用了就不可删除；
        Boolean result = rePermissionRoleService.doDelete(id);

        //返回值；
        if (result) {
            returnMap.put("success", true);
            returnMap.put("message", "删除成功");
        } else {
            returnMap.put("message", "角色已被使用，暂不能删除；");
        }
        return returnMap;
    }

    @NeedPermission(permissionName = "新增或编辑角色", classifyName = "权限管理")
    @RequestMapping("/edit")
    public String edit(Integer id, HttpServletRequest request) throws Exception {

        //获取后台用户；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);

        //获取用户拥有的权限，并进行分类；
        Map<String, List<RePermission>> map = rePermissionRoleService.getClassifyPermission(adminUser);
        request.setAttribute("ClassifyPermissions_in_Session", map);

        //获取用户拥有的菜单，并进行分类；
        Map<String, List<ReItem>> itemMap = reItemService.getClassifyItem(adminUser);
        request.setAttribute("ClassifyItem_in_Session", itemMap);

        //如果是编辑角色，那么需要回显数据；
        if (id != null) {
            RePermissionRole editRole = rePermissionRoleService.get(id);
            request.setAttribute("EditRole_in_Session", editRole);
            request.setAttribute("EditRolePermissions_Json_in_Session", getIdJsonForPermission(editRole));
            request.setAttribute("EditRoleItems_Json_in_Session", getIdJsonForItem(editRole));
        }

        return "permission/role/edit";
    }

    /**
     * 保存角色操作；
     *
     * @param roleId        角色id（编辑的时候有）；
     * @param roleName      角色名称；
     * @param rePermissions 角色的权限的id集合；
     * @param reItems       角色的菜单的id集合；
     * @return
     */
    @NeedPermission(permissionName = "保存角色", classifyName = "权限管理")
    @RequestMapping("/save")
    public String save(Integer roleId, String roleName, HttpServletRequest request,
                       @RequestParam("rePermissions") List<Integer> rePermissions, @RequestParam("reItems") List<Integer> reItems) {

        //获取后台用户；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        AdminUser adminUser = adminUserService.findById(adminUserId);

        rePermissionRoleService.doSave(roleId, roleName, rePermissions, reItems, adminUser);
        return "redirect:/role/list";
    }

    /**
     * 为了方便前段回显，将id拼成json，放到session中；
     *
     * @param editRole
     * @return
     */
    private String getIdJsonForPermission(RePermissionRole editRole) {
        Set<RePermission> permissionSet = editRole.getPermissionSet();
        List<Integer> returnList = new ArrayList<>();
        for (RePermission each : permissionSet) {
            returnList.add(each.getId());
        }
        return JSONObject.toJSONString(returnList);
    }

    private String getIdJsonForItem(RePermissionRole editRole) {
        Set<ReItem> itemSet = editRole.getItemSet();
        List<Integer> returnList = new ArrayList<>();
        for (ReItem each : itemSet) {
            returnList.add(each.getId());
        }
        return JSONObject.toJSONString(returnList);
    }

}
