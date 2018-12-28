package com.lyarc.tp.corp.role.bean;


import com.lyarc.tp.corp.common.bean.BaseQueryBean;

/**
 * 角色查询参数
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月04日 17:52:25
 */
public class RoleQueryBean extends BaseQueryBean {

    /**
     * 角色名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
