package com.lyarc.tp.corp.login.form;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * TODO 2017-9-11 版本号修改
 */

/**
 * 登录信息
 * 
 * @version 1.0
 * @since JDK1.7
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月10日 上午9:55:00
 */
public class LoginForm {

    /** 登录名*/
    @NotEmpty(message = "用户名或密码不能为空|10001")
    private String loginName;

    /** 密码*/
    @NotEmpty(message = "用户名或密码不能为空|10001")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
