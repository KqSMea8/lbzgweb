package com.lyarc.tp.corp.workflow.dto;

import java.time.LocalDateTime;

/**
 * 
 * 工作流应用列表返回数据
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月26日 上午10:01:53
 */
public class WorkflowAppListDTO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 企业编号
     */
    private String corpId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 图标
     */
    private Integer iconNo;
    /**
     * 图标颜色
     */
    private Integer iconColor;
    /**
     * 上传图标
     */
    private String iconSrc;
    /**
     * 使用范围
     */
    private String useScope;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIconNo() {
        return iconNo;
    }

    public void setIconNo(Integer iconNo) {
        this.iconNo = iconNo;
    }

    public Integer getIconColor() {
        return iconColor;
    }

    public void setIconColor(Integer iconColor) {
        this.iconColor = iconColor;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getUseScope() {
        return useScope;
    }

    public void setUseScope(String useScope) {
        this.useScope = useScope;
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
    
    
}
