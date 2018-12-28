package com.lyarc.tp.corp.login.form;

/**
 * 修改密码form
 * 
 * @version 1.0
 * @since JDK1.8
 * @author lidoudou
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年9月11日 下午2:44:10
 */
public class ChangePwdForm {

    /** 旧密码 */
    private String oldPwd;

    /** 新密码 */
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

}
