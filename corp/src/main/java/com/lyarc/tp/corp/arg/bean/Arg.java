package com.lyarc.tp.corp.arg.bean;

import java.util.Date;

public class Arg {
    private Integer id;

    private String argGroup;

    private String argCode;

    private String argText;

    private String argRemark;

    private Integer isInit;

    private Integer seq;

    private Integer creator;

    private Date createTime;

    private Date lastUpdate;

    private Date tmstamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArgGroup() {
        return argGroup;
    }

    public void setArgGroup(String argGroup) {
        this.argGroup = argGroup == null ? null : argGroup.trim();
    }

    public String getArgCode() {
        return argCode;
    }

    public void setArgCode(String argCode) {
        this.argCode = argCode == null ? null : argCode.trim();
    }

    public String getArgText() {
        return argText;
    }

    public void setArgText(String argText) {
        this.argText = argText == null ? null : argText.trim();
    }

    public String getArgRemark() {
        return argRemark;
    }

    public void setArgRemark(String argRemark) {
        this.argRemark = argRemark == null ? null : argRemark.trim();
    }

    public Integer getIsInit() {
        return isInit;
    }

    public void setIsInit(Integer isInit) {
        this.isInit = isInit;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Date tmstamp) {
        this.tmstamp = tmstamp;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}