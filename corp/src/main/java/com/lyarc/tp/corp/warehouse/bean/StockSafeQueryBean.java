package com.lyarc.tp.corp.warehouse.bean;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.math.BigDecimal;
import java.util.Date;

public class StockSafeQueryBean  extends BaseQueryBean {
    private String keyword;

    private String storageId;

    private String materId;

    private String materName;

    private String spec;

    private String model;

    private String unit;

    private BigDecimal minQuantity;

    private BigDecimal safeQuantity;

    private BigDecimal maxQuantity;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName == null ? null : materName.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(BigDecimal minQuantity) {
        this.minQuantity = minQuantity;
    }

    public BigDecimal getSafeQuantity() {
        return safeQuantity;
    }

    public void setSafeQuantity(BigDecimal safeQuantity) {
        this.safeQuantity = safeQuantity;
    }

    public BigDecimal getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(BigDecimal maxQuantity) {
        this.maxQuantity = maxQuantity;
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

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}