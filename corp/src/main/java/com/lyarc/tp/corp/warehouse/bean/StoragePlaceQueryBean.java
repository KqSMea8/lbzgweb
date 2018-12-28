package com.lyarc.tp.corp.warehouse.bean;

import com.lyarc.tp.corp.common.bean.BaseQueryBean;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class StoragePlaceQueryBean extends BaseQueryBean {
    private String storageId;

    private String placeId;

    private String name;

    private String description;

    private String parentPlaceId;

    private Integer creator;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = StringUtils.isEmpty(storageId) ? null : storageId.trim();
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = StringUtils.isEmpty(placeId) ? null : placeId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.isEmpty(name) ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentPlaceId() {
        return parentPlaceId;
    }

    public void setParentPlaceId(String parentPlaceId) {
        this.parentPlaceId = StringUtils.isEmpty(parentPlaceId) ? null : parentPlaceId.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}