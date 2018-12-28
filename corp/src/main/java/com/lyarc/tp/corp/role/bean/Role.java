package com.lyarc.tp.corp.role.bean;

import com.googlecode.jmapper.annotations.JMap;
import com.lyarc.tp.corp.role.dto.RoleDTO;
import com.lyarc.tp.corp.role.form.RoleForm;

import java.time.LocalDateTime;
import java.util.Date;

public class Role {
    /**
     * 角色ID
     */
    @JMap(attributes = {"roleId", "roleId"}, classes = {RoleDTO.class, RoleForm.class})
    private Integer roleId;
    /**
     * 角色名称
     */
    @JMap(attributes = {"roleName", "roleName"}, classes = {RoleDTO.class, RoleForm.class})
    private String roleName;

    /**
     * 备注
     */
    @JMap(attributes = {"remark", "remark"}, classes = {RoleDTO.class, RoleForm.class})
    private String remark;
    /**
     * 创建者
     */
    @JMap(attributes = {"creator","creator"}, classes = {RoleDTO.class,RoleForm.class})
    private Integer creator;
    /**
     * 创建时间
     */
    @JMap(attributes = {"createTime"}, classes = {RoleDTO.class})
    private LocalDateTime createTime;
    /**
     * 更新时间
     */ 
    private LocalDateTime lastUpdate;

    private Date tmstamp;

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
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Date getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Date tmstamp) {
        this.tmstamp = tmstamp;
    }
}