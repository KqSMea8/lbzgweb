package com.lyarc.tp.corp.purchase.provider.bean;

import com.googlecode.jmapper.annotations.JMap;

import java.math.BigDecimal;
import java.util.Date;

public class ProviderMaterial extends ProviderMaterialKey {
    @JMap(attributes = {"materType"}, classes = {ProviderMaterial.class})
    private String materType;

    @JMap(attributes = {"origin"}, classes = {ProviderMaterial.class})
    private String origin;

    @JMap(attributes = {"use"}, classes = {ProviderMaterial.class})
    private String use;

    private Integer level;

    @JMap(attributes = {"price"}, classes = {ProviderMaterial.class})
    private BigDecimal price;

    @JMap(attributes = {"remark"}, classes = {ProviderMaterial.class})
    private String remark;

    private Integer status;

    private Integer creator;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    public String getMaterType() {
        return materType;
    }

    public void setMaterType(String materType) {
        this.materType = materType == null ? null : materType.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use == null ? null : use.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Date tmstamp) {
        this.tmstamp = tmstamp;
    }
}