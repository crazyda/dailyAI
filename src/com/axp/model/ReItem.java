package com.axp.model;

import java.io.Serializable;

/**
 * 菜单类：记录了后台系统中首页界面展示的菜单；
 *
 * @author Administrator
 */
public class ReItem extends ReBaseDomain implements Comparable<ReItem>, Serializable {

    private String name;//菜单名称；
    private String parentName;//父级菜单名称；
    private String linkAddress;//菜单的链接地址；

    @Override
    public int compareTo(ReItem item) {
        if (this.getId() >= item.getId()) {
            return 1;
        }
        return -1;
    }

    //===============getter and setter================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
    
    @Override
    public String toString() {
        return "ReItem{" +
                "name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                '}';
    }
}
