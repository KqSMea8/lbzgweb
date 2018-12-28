package com.lyarc.tp.corp.contacts.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树结构根节点
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月29日 16:52:36
 */
public class DeptTree extends DeptNode {

    /**
     * 方法描述 构建树
     * 
     * @param deptBeans 部门集合
     * @param employeeBeans 员工集合
     * @return DeptNode
     *
     * @author huanghaoqi
     * @date 2017年08月29日 16:53:38
     */
    public static DeptNode build(List<DeptBean> deptBeans, List<EmployeeBean> employeeBeans) {
        // 数据源转换成树
        List<DeptNode> deptTrees = convert(deptBeans, employeeBeans);
        if (deptTrees == null) {
            return null;
        }
        List<DeptNode> topNodes = new ArrayList<>();
        for (DeptNode children : deptTrees) {
            Integer pid = children.getDept().getParentId();
            if (pid == 0) {
                topNodes.add(children);
                continue;
            }
            for (DeptNode parent : deptTrees) {
                Integer id = parent.getDept().getDeptId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                }
            }
        }
        if (topNodes.size() > 0) {
            return topNodes.get(0);
        }
        return null;
    }

    /**
     * 方法描述 将数据源转为DeptNode集合
     * 
     * @param deptBeans 部门集合
     * @param employeeBeans 员工集合
     * @return List<DeptNode>
     *
     * @author huanghaoqi
     * @date 2017年08月29日 16:56:11
     */
    private static List<DeptNode> convert(List<DeptBean> deptBeans, List<EmployeeBean> employeeBeans) {
        List<DeptNode> list = new ArrayList<>(50);
        deptBeans.forEach(deptBean -> {
            DeptNode deptNode = new DeptNode();
            deptNode.setDept(deptBean);
            String deptIdStr = deptBean.getDeptId() + "";
            deptNode.setEmployeeBeans(employeeBeans.stream()
                    .filter(employeeBean -> Arrays.asList(employeeBean.getDeptId().split(",")).contains(deptIdStr))
                    .collect(Collectors.toList()));
            list.add(deptNode);
        });
        return list;
    }

}
