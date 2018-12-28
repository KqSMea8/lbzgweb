package com.lyarc.tp.corp.role.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.power.dto.PowerDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年09月04日 15:32:32
 */
public class RoleDTO {
    /**
     * 角色ID
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
     * 功能点名称
     */
    private String powerNames;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private Integer creator;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate;
    /**
     * 功能点
     */
    private List<PowerDTO> powerDtoList;


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

    public String getPowerNames() {
        return powerNames;
    }

    public void setPowerNames(String powerNames) {
        this.powerNames = powerNames;
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

    public List<PowerDTO> getPowerDtoList() {
        return powerDtoList;
    }

    public void setPowerDtoList(List<PowerDTO> powerDtoList) {
        this.powerDtoList = powerDtoList;
    }

    @Override
    public String toString() {
        return "RoleDTO{ roleId=" + roleId + ", roleName='" + roleName
                + '\'' + ", powerIds='" + powerIds + '\'' + ", powerNames='" + powerNames + '\'' + ", remark='" + remark
                + '\'' + ", creator=" + creator + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate
                + ", powerDtoList=" + powerDtoList + '}';
    }
}
