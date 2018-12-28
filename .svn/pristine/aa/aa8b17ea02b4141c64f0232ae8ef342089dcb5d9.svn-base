package com.lyarc.tp.corp.warehouse.form;

import com.googlecode.jmapper.annotations.JGlobalMap;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JGlobalMap
public class StockOpertypeForm {
    @NotNull(message = "操作代码不能为空")
    private String operId;

    @NotNull(message = "操作名称不能为空")
    private String name;

    @NotNull(message = "操作类型不能为空")
    private Integer type;
    @NotNull(message = "操作来源不能为空")
    private Integer source;

    private Integer reject;

    private Integer repair;

    private Integer creator;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId == null ? null : operId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public Integer getRepair() {
        return repair;
    }

    public void setRepair(Integer repair) {
        this.repair = repair;
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