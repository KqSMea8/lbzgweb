package com.lyarc.tp.corp.power.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 功能点
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月05日 11:29:23
 */
public class PowerDTO {
    /**
     * 功能点ID
     */
    private Integer powerId;
    /**
     * 功能点key
     */
    private String powerKey;
    /**
     * 类型（1.模块、2.页面、3.功能点）
     */
    private Integer powerType;
    /**
     * 图标
     */
    private String powerIcon;
    /**
     * 标题
     */
    private String powerCaption;
    /**
     * 功能url
     */
    private String powerUrl;
    /**
     * 是否显示 1.显示 0.不显示
     */
    private Integer isShow;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 创建人
     */
    private Integer creator;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerKey() {
        return powerKey;
    }

    public void setPowerKey(String powerKey) {
        this.powerKey = powerKey;
    }

    public Integer getPowerType() {
        return powerType;
    }

    public void setPowerType(Integer powerType) {
        this.powerType = powerType;
    }

    public String getPowerIcon() {
        return powerIcon;
    }

    public void setPowerIcon(String powerIcon) {
        this.powerIcon = powerIcon;
    }

    public String getPowerCaption() {
        return powerCaption;
    }

    public void setPowerCaption(String powerCaption) {
        this.powerCaption = powerCaption;
    }

    public String getPowerUrl() {
        return powerUrl;
    }

    public void setPowerUrl(String powerUrl) {
        this.powerUrl = powerUrl;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "PowerDTO{" + "powerId=" + powerId + ", powerKey='" + powerKey + '\'' + ", powerType=" + powerType
                + ", powerIcon='" + powerIcon + '\'' + ", powerCaption='" + powerCaption + '\'' + ", powerUrl='"
                + powerUrl + '\'' + ", isShow=" + isShow + ", parentId=" + parentId + ", seq=" + seq + ", creator="
                + creator + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate + '}';
    }
}
