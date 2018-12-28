package com.lyarc.tp.corp.contacts.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门节点
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月29日 14:49:19
 */
public class DeptNode {
    /**
     * 部门ID
     */
    private DeptBean dept;
    /**
     * 员工信息
     */
    private List<EmployeeBean> employeeBeans;
    /**
     * 子节点
     */
    private List<DeptNode> children = new ArrayList<>(0);

    /**
     * 方法描述 根据部门ID 查询节点
     * 
     * @param deptId 部门ID
     * @return DeptNode
     *
     * @author huanghaoqi
     * @date 2017年08月29日 14:50:40
     */
    public DeptNode getByDeptId(Integer deptId) {
        if (deptId != null && deptId.equals(dept.getDeptId())) {
            return this;
        } else {
            for (DeptNode deptNode : children) {
                DeptNode node = deptNode.getByDeptId(deptId);
                if (node != null) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 方法描述 获取所有节点
     * 
     * @return List<DeptNode>
     *
     * @author huanghaoqi
     * @date 2017年08月29日 14:51:23
     */
    public List<DeptNode> listNodes() {
        List<DeptNode> deptNodeList = new ArrayList<>(100);
        deptNodeList.add(getByDeptId(this.dept.getDeptId()));
        children.forEach(deptNode -> deptNodeList.addAll(deptNode.listNodes()));
        return deptNodeList;
    }

    /**
     * 方法描述 获取所有的员工
     * 
     * @return List<EmployeeBean>
     *
     * @author huanghaoqi
     * @date 2017年08月29日 14:52:08
     */
    public List<EmployeeBean> listEmployeeBeans() {
        List<EmployeeBean> employeeBeanList = new ArrayList<>(100);
        employeeBeanList.addAll(this.employeeBeans);
        children.forEach(deptNode -> {
            deptNode.listEmployeeBeans().forEach(employeeBean -> {
                if (!employeeBeanList.contains(employeeBean)) {
                    employeeBeanList.add(employeeBean);
                }
            });
        });
        return employeeBeanList;
    }

    /**
     * 方法描述 获取当前节点下的员工数
     * 
     * @return Integer
     *
     *
     * @author huanghaoqi
     * @date 2017年08月29日 14:53:16
     */
    public Integer getEmpCount() {
        return employeeBeans.size();
    }

    /**
     * 方法描述 方法描述 获取当前节点下的总员工数（包含子节点）
     *
     * @author huanghaoqi
     * @date 2017年08月29日 17:23:46
     */
    public Integer getAllEmpCount() {
        return listEmployeeBeans().size();
    }

    public DeptBean getDept() {
        return dept;
    }

    public void setDept(DeptBean dept) {
        this.dept = dept;
    }

    public List<EmployeeBean> getEmployeeBeans() {
        return employeeBeans;
    }

    public void setEmployeeBeans(List<EmployeeBean> employeeBeans) {
        this.employeeBeans = employeeBeans;
    }

    public List<DeptNode> getChildren() {
        return children;
    }

    public void setChildren(List<DeptNode> children) {
        this.children = children;
    }
}
