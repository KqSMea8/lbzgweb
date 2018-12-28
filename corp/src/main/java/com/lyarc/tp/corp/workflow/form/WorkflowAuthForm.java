package com.lyarc.tp.corp.workflow.form;

import java.time.LocalDateTime;

/**
 * 
 * 使用权限
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月24日 下午4:04:52
 */
public class WorkflowAuthForm {

    private Integer id;
    
    private String useScope;
    
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUseScope() {
        return useScope;
    }

    public void setUseScope(String useScope) {
        this.useScope = useScope;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    
    
}
