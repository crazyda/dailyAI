package com.axp.model;


/**
 * 权限类：记录了系统中的权限列表；
 *
 * @author Administrator
 */
public class RePermission extends ReBaseDomain implements java.io.Serializable {

    private String name;//权限名称；
    private String order;//权限编号，组成方式为，NeedPersmission标签所在的方法的全限定名（作用是为了防止权限的重复）
    private String linkAddress;//权限对应的请求地址；
    private String intro;//权限说明；
    private RePermission parentPermission;//这个对象是为了记录权限的所属分类，所以这个对象只有name字段有值，表示权限的分类名称；

    //===============getter and setter================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public RePermission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(RePermission parentPermission) {
        this.parentPermission = parentPermission;
    }

    @Override
    public String toString() {
        return "RePermission{" +
                "name='" + name + '\'' +
                ", order='" + order + '\'' +
                ", linkAddress='" + linkAddress + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
