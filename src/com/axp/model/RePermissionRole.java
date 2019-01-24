package com.axp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.axp.util.StringUtil;

/**
 * 角色类：记录用户角色
 *
 * @author Administrator
 */
public class RePermissionRole extends ReBaseDomain {

    private String name;//角色名称；

    private Set<RePermission> permissionSet = new HashSet<>();//当前角色包含的权限；
    private Set<ReItem> itemSet = new HashSet<>();//当前角色包含的菜单；

    private AdminUser adminusers;//创建角色的后台用户；
    private Seller seller;//创建角色的商家；（目前只要Adminusers用户可以登录后台，未来会增加seller用户，此字段留在那个时候使用）;

    //===============getter and setter================

    public String getPermissionDetails() {
        Map<String, List<RePermission>> map = new HashMap<>();
        for (RePermission each : permissionSet) {
            if (StringUtil.isEmpty(each.getOrder())) {
                map.put(each.getName(), new ArrayList<RePermission>());
            }
        }
        for (RePermission each : permissionSet) {
            if (StringUtil.hasLength(each.getOrder())) {
                List<RePermission> list = map.get(each.getParentPermission().getName());
                list.add(each);
            }
        }
        Set<Map.Entry<String, List<RePermission>>> entries = map.entrySet();
        StringBuilder sb = new StringBuilder(200);
        for (Map.Entry each : entries) {
            sb.append("[").append(each.getKey());
            for (RePermission eachPermission : (List<RePermission>) each.getValue()) {
                sb.append(eachPermission.getName()).append(" ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public String getItemDetails() {
        Map<String, List<ReItem>> map = new HashMap<>();
        for (ReItem each : itemSet) {
            List<ReItem> items = map.get(each.getParentName());
            if (items == null) {
                items = new ArrayList<>();
                items.add(each);
                map.put(each.getParentName(), items);
            } else {
                items.add(each);
            }
        }
        Set<Map.Entry<String, List<ReItem>>> entries = map.entrySet();
        StringBuilder sb = new StringBuilder(200);
        for (Map.Entry each : entries) {
            sb.append("[").append(each.getKey());
            for (ReItem eachItem : (List<ReItem>) each.getValue()) {
                sb.append(eachItem.getName()).append(" ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RePermission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<RePermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<ReItem> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<ReItem> itemSet) {
        this.itemSet = itemSet;
    }

    public AdminUser getAdminusers() {
        return adminusers;
    }

    public void setAdminusers(AdminUser adminusers) {
        this.adminusers = adminusers;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "RePermissionRole{" +
                "name='" + name + '\'' +
                ", permissionSet=" + getPermissionDetails() +
                ", itemSet=" + getItemDetails() +
                ", adminusers=" + adminusers +
                ", seller=" + seller +
                '}';
    }
}
