package com.lyarc.tp.corp.contacts.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * 员工
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 13:38:15
 */
public class EmpForm {
    /**
     * id
     */
    private Integer id;
    /**
     * 员工编号
     */
    private String empId;
    /**
     * 部门编号
     */
    private String deptId;
    /**
     * 真实姓名
     */
    private String trueName;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信
     */
    private String weixin;
    /**
     * QQ
     */
    private String qq;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 职位
     */
    private String duty;
    /**
     * 出生日期(阳历)
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate birthSolar;
    /**
     * 生日提醒(0.按阳历 1.按阴历)
     */
    private Integer birthReminder;
    /**
     * 入职时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate joinDate;
    /**
     * 所在区域
     */
    private String area;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public LocalDate getBirthSolar() {
        return birthSolar;
    }

    public void setBirthSolar(LocalDate birthSolar) {
        this.birthSolar = birthSolar;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Integer getBirthReminder() {
        return birthReminder;
    }

    public void setBirthReminder(Integer birthReminder) {
        this.birthReminder = birthReminder;
    }

    @Override
    public String toString() {
        return "EmpForm [id=" + id + ", empId=" + empId + ", deptId=" +deptId + ", trueName=" + trueName + ", mobile=" + mobile + ", email=" + email + ", weixin=" + weixin + ", qq=" + qq + ", sex=" + sex + ", duty=" + duty + ", birthSolar=" + birthSolar + ", birthReminder="
                + birthReminder + ", joinDate=" + joinDate + ", area=" + area + ", address=" + address + ", remark=" + remark + "]";
    }

    
}
