package com.lyarc.tp.corp.role.form;

import java.util.Arrays;

/**
 * 角色
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月04日 15:30:37
 */
public class RoleForm {
    /**
     * 主键ID
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 功能点ID
     */
    private String powerIds;
    /**
     * 备注
     */
    private String remark;

    private Integer creator;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPowerIds() {
        return powerIds;
    }

    public void setPowerIds(String powerIds) {
        this.powerIds = powerIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "RoleForm{" + "roleId=" + roleId + ", roleName='" + roleName + '\'' + ", powerIds="
                + powerIds + ", remark='" + remark + '\'' + '}';
    }
}

