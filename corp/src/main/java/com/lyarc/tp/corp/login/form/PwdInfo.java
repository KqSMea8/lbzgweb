package com.lyarc.tp.corp.login.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取密码信息
 * 
 * @version 1.0
 * @since JDK1.7
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月12日 上午10:33:37
 */
public class PwdInfo {

    /** id*/
    private Integer id;
    
    /** 登录名*/
    @NotEmpty(message="登录名不能为空|10005")
    private String loginName;
    
    /** 是否设置密码信息*/
    private int hasPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(int hasPassword) {
        this.hasPassword = hasPassword;
    }
    
    
}
