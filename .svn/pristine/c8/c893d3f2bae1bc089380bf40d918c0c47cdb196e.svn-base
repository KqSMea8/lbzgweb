package com.lyarc.tp.corp.contacts.bean;


import com.googlecode.jmapper.annotations.JMap;
import me.chanjar.weixin.cp.bean.WxCpDepart;

import java.time.LocalDateTime;

/**
 * 
 * 部门信息类
 * 
 * @version 1.0
 * @since JDK1.7
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月15日 上午10:21:11
 */
public class DeptBean {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 企业ID
     */
    private String corpId;
    /**
     * 部门ID
     */
    @JMap(attributes = {"id"}, classes = {WxCpDepart.class})
    private Integer deptId;
    /**
     * 部门名称
     */
    @JMap(attributes = {"name"}, classes = {WxCpDepart.class})
    private String deptName;
    /**
     * 父级部门ID
     */
    @JMap(attributes = {"parentId"}, classes = {WxCpDepart.class})
    private Integer parentId;
    /**
     * 排序
     */
    @JMap(attributes = {"order"}, classes = {WxCpDepart.class})
    private Integer seq;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    private LocalDateTime lastUpdate;
    /**
     * 时间戳
     */
    private LocalDateTime tmstamp;

    public DeptBean() {
    }

    public DeptBean(Integer deptId, Integer parentId) {
        this.deptId = deptId;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public LocalDateTime getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(LocalDateTime tmstamp) {
        this.tmstamp = tmstamp;
    }

    @Override
    public String toString() {
        return "DeptBean [id=" + id + ", corpId=" + corpId + ", deptId=" + deptId + ", deptName=" + deptName + ", parentId=" + parentId + ", seq=" + seq + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate + ", tmstamp=" + tmstamp + "]";
    }



}
