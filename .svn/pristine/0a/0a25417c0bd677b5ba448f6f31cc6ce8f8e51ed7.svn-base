package com.lyarc.tp.corp.contacts.bean;

import com.googlecode.jmapper.annotations.JMap;
import com.lyarc.tp.corp.contacts.dto.EmpDTO;
import com.lyarc.tp.corp.contacts.form.EmpForm;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 10:13:22
 */

public class EmployeeBean {
    @JMap(attributes = {"id", "id"}, classes = {EmpForm.class, EmpDTO.class})
    private Integer id;
    /**
     * 员工编号
     */
    @JMap(attributes = {"empId", "empId", "userId"}, classes = {EmpForm.class, EmpDTO.class , WxCpUser.class})
    private String empId;
    /**
     * 部门编号
     */
    @JMap(attributes = {"deptId","deptId"}, classes = {EmpForm.class,EmpDTO.class})
    private String deptId;
    /**
     * 真实姓名
     */
    @JMap(attributes = {"trueName", "trueName", "name"}, classes = {EmpDTO.class, EmpForm.class, WxCpUser.class})
    private String trueName;
    /**
     * 真实姓名拼音
     */
    @JMap(attributes = {"tureNamePinyin"}, classes = {EmpDTO.class})
    private String tureNamePinyin;
    /**
     * 拼音简写
     */
    @JMap(attributes = {"pinyinAbbreviation"}, classes = {EmpDTO.class})
    private String pinyinAbbreviation;
    /**
     * 手机
     */
    @JMap(attributes = {"mobile", "mobile", "mobile"}, classes = {EmpForm.class, EmpDTO.class, WxCpUser.class})
    private String mobile;
    /**
     * 邮箱
     */
    @JMap(attributes = {"email", "email", "email"}, classes = {EmpForm.class, EmpDTO.class, WxCpUser.class})
    private String email;
    /**
     * 微信
     */
    @JMap(attributes = {"weixin", "weixin"}, classes = {EmpForm.class, EmpDTO.class})
    private String weixin;
    /**
     * QQ
     */
    @JMap(attributes = {"qq", "qq"}, classes = {EmpForm.class, EmpDTO.class})
    private String qq;
    /**
     * 性别
     */
    @JMap(attributes = {"sex", "sex"}, classes = {EmpForm.class, EmpDTO.class})
    private Integer sex;
    /**
     * 头像
     */
    @JMap(attributes = {"header","avatar"}, classes = {EmpDTO.class , WxCpUser.class})
    private String header;
    /**
     * 职位
     */
    @JMap(attributes = {"duty", "duty", "position"}, classes = {EmpForm.class, EmpDTO.class, WxCpUser.class})
    private String duty;
    /**
     * 出生日期(阳历)
     */
    @JMap(attributes = {"birthSolar", "birthSolar"}, classes = {EmpForm.class, EmpDTO.class})
    private LocalDate birthSolar;
    /**
     * 出生日期(阴历)
     */
    @JMap(attributes = {"birthLunar"}, classes = {EmpDTO.class})
    private LocalDate birthLunar;
    /**
     * 生日提醒（0.按阳历 1.按阴历）
     */
    @JMap(attributes = {"birthReminder", "birthReminder"}, classes = {EmpForm.class, EmpDTO.class})
    private Integer birthReminder;
    /**
     * 入职时间
     */
    @JMap(attributes = {"joinDate", "joinDate"}, classes = {EmpForm.class, EmpDTO.class})
    private LocalDate joinDate;
    /**
     * 所在区域
     */
    @JMap(attributes = {"area", "area"}, classes = {EmpForm.class, EmpDTO.class})
    private String area;
    /**
     * 地址
     */
    @JMap(attributes = {"address", "address"}, classes = {EmpForm.class, EmpDTO.class})
    private String address;
    /**
     * 备注
     */
    @JMap(attributes = {"remark", "remark"}, classes = {EmpForm.class, EmpDTO.class})
    private String remark;
    /**
     * 状态（微信同步）
     */
    @JMap(attributes = {"status"}, classes = {EmpDTO.class })
    private Integer status;
    /**
     * 创建时间
     */
    @JMap(attributes = {"createTime"}, classes = {EmpDTO.class})
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JMap(attributes = {"lastUpdate"}, classes = {EmpDTO.class})
    private LocalDateTime lastUpdate;
    /**
     * 时间戳
     */
    @JMap(attributes = {"tmstamp"}, classes = {EmpDTO.class})
    private LocalDateTime tmstamp;
    /**
     * 离职状态（1：已离职，2：在职）
     */
    @JMap(attributes = {"dimission"}, classes = {EmpDTO.class})
    private Integer dimission;


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

    public String getTureNamePinyin() {
        return tureNamePinyin;
    }

    public void setTureNamePinyin(String tureNamePinyin) {
        this.tureNamePinyin = tureNamePinyin;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public LocalDate getBirthLunar() {
        return birthLunar;
    }

    public void setBirthLunar(LocalDate birthLunar) {
        this.birthLunar = birthLunar;
    }

    public Integer getBirthReminder() {
        return birthReminder;
    }

    public void setBirthReminder(Integer birthReminder) {
        this.birthReminder = birthReminder;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDimission() {
        return dimission;
    }

    public void setDimission(Integer dimission) {
        this.dimission = dimission;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getPinyinAbbreviation() {
        return pinyinAbbreviation;
    }

    public void setPinyinAbbreviation(String pinyinAbbreviation) {
        this.pinyinAbbreviation = pinyinAbbreviation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmployeeBean [ empId=" + empId + ", deptId=" + deptId + ", trueName=" + trueName + ", tureNamePinyin=" + tureNamePinyin + ", pinyinAbbreviation=" + pinyinAbbreviation + ", mobile=" + mobile + ", email=" + email + ", weixin=" + weixin
                + ", qq=" + qq + ", sex=" + sex + ", header=" + header + ", duty=" + duty + ", birthSolar=" + birthSolar + ", birthLunar=" + birthLunar + ", birthReminder=" + birthReminder + ", joinDate=" + joinDate + ", area=" + area + ", address=" + address + ", remark=" + remark + ", status="
                + status + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate + ", tmstamp=" + tmstamp + ", dimission=" + dimission + "]";
    }

    
}
