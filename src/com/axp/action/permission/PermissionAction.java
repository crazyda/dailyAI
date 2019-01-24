package com.axp.action.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.util.FileUtil;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 操作权限相关的方法；
 */

@Controller
@RequestMapping("/permission")
public class PermissionAction extends BaseAction {

    /**
     * 扫描action包中的所有类，添加权限；
     *
     * @return
     * @throws Exception
     */
    @NeedPermission(permissionName = "扫描权限和角色标签", classifyName = "权限管理")
    @IsItem(firstItemName = "权限管理", secondItemName = "扫描权限和角色标签")
    @RequestMapping("/scanAllPermissionAndItemAnnotation")
    @ResponseBody
    public Map<String, Object> scanAllPermissionAnnotation() throws Exception {

        //获取指定包下的所有字节码文件；
        List<Class<?>> clazzes = FileUtil.getClassFromPackage("com.axp.action");

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("success", false);

        //扫描标签，并保存；
        try {
            rePermissionService.scanPermissionOrItemAnnotation(clazzes);
            returnMap.put("success", true);
            returnMap.put("message", " 操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("message", "扫描标签时出错");
        }
        return returnMap;
    }
}
