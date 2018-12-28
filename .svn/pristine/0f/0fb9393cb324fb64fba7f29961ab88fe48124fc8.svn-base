package com.lyarc.tp.corp.purchase.provider.form;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.googlecode.jmapper.annotations.JMap;
import com.lyarc.tp.corp.contacts.dto.EmpDTO;
import com.lyarc.tp.corp.contacts.form.EmpForm;
import com.lyarc.tp.corp.purchase.provider.bean.ProviderMaterial;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@JGlobalMap
public class NewProviderMaterialDetailForm {
    private String npmId;

    private String materId;

    private String materType;

    private String origin;

    private String use;

    private BigDecimal price;

    private BigDecimal quantity;

    private BigDecimal amount;

    private String remark;

    private Integer creator;

    private String files;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime tmstamp;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(LocalDateTime tmstamp) {
        this.tmstamp = tmstamp;
    }

    public String getNpmId() {
        return npmId;
    }

    public void setNpmId(String npmId) {
        this.npmId = npmId;
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}