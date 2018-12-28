package com.lyarc.tp.corp.contacts.bean;


import com.lyarc.tp.corp.common.bean.BaseQueryBean;

/**
 * 查询用户参数
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 11:06:31
 */
public class QueryEmpBean extends BaseQueryBean {
//    /**
//     * 企业编号
//     */
//    private String corpId;
    /**
     * 部门编号
     */
    private Integer deptId;
    /**
     * 离职状态（0：在职，1：离职）
     */
    private Integer dimission;
    /**
     * 状态（0:未关注，1：已关注,-1：全部成员）
     */
    private Integer status;
    /**
     * 模糊查询内容
     */
    private String keyword;
    /**
     * 是否分配部门(1 分配 0未分配)
     */
    private Boolean distribution;

//    public String getCorpId() {
//        return corpId;
//    }
//
//    public void setCorpId(String corpId) {
//        this.corpId = corpId;
//    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDimission() {
        return dimission;
    }

    public void setDimission(Integer dimission) {
        this.dimission = dimission;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getDistribution() {
        return distribution;
    }

    public void setDistribution(Boolean distribution) {
        this.distribution = distribution;
    }

    @Override
    public String toString() {
        return "QueryEmpBean{"  + ", deptId=" + deptId + ", dimission=" + dimission
                + ", status=" + status + ", keyword='" + keyword + '\'' + ", distribution=" + distribution
                + '}';
    }
}
