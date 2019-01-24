package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * GoodsAuction entity. @author MyEclipse Persistence Tools
 */
public class SystemConfig implements Serializable {

    private Integer id;
    private String paramter;//参数名；
    private Integer version;//版本号（记录的数据不一定代表版本）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamter() {
        return paramter;
    }

    public void setParamter(String paramter) {
        this.paramter = paramter;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
