package com.lyarc.tp.corp.warehouse.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoragePlace extends StoragePlaceKey {
    private String name;

    private String description;

    private String parentPlaceId;

    private Integer creator;

    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    private List<StoragePlace> children  = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParentPlaceId() {
        return parentPlaceId;
    }

    public void setParentPlaceId(String parentPlaceId) {
        this.parentPlaceId = parentPlaceId == null ? "-1" : parentPlaceId.trim();
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

    public List<StoragePlace> getChildren() {
        return children;
    }

    public void setChildren(List<StoragePlace> children) {
        this.children = children;
    }
}