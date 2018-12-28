package com.lyarc.tp.corp.contacts.form;

import com.googlecode.jmapper.annotations.JGlobalMap;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * 添加部门表单
 * 
 * @version 1.0
 * @since JDK1.7
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月15日 上午11:44:36
 */
@JGlobalMap
public class DeptForm {

    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空|10012")
    private String deptName;

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

    @Override
    public String toString() {
        return "DeptForm [deptId=" + deptId + ", deptName=" + deptName + "]";
    }


}
