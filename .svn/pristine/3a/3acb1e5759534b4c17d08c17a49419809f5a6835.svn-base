package com.lyarc.tp.corp;


import com.lyarc.tp.corp.login.form.LoginUser;

/**
 * 
 * 获取当前登录用户信息
 * 
 * @version 1.0
 * @since JDK1.7
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月7日 下午1:55:24
 */
public class UserHolder{   
    
    /**
     * 用户线程
     */
    private static final ThreadLocal<Object> userThreadLocal = new ThreadLocal<Object>();
    
    
    private UserHolder(){
 
    }   
    
    public static void setUser(LoginUser value) {
        userThreadLocal.set(value);
    }
    
    public static LoginUser getUser() {
        return (LoginUser)userThreadLocal.get();
    }


}
