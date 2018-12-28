package com.lyarc.tp.corp.workflow.form;

import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * 工作流
 * 应用基本信息
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月24日 下午3:01:04
 */
@JGlobalMap
public class WorkflowAppInfoForm {

    private Integer id;
    
    private String title;
    
    private String description;
    
    private Integer iconNo;
    
    private Integer iconColor;
    
    private String iconSrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    
}
