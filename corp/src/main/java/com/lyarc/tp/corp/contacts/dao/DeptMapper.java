package com.lyarc.tp.corp.contacts.dao;

import com.lyarc.tp.corp.contacts.bean.DeptBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 部门 dao 层
 * 
 * @version 1.0
 * @since JDK1.7
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年8月15日 上午10:49:02
 */
@Mapper
public interface DeptMapper {

    /**
     * 
     * 获取所有部门
     *
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 上午11:46:55
     */
    List<DeptBean> list();

    /**
     * 
     * 添加子部门
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 上午11:47:09
     */
    Integer add(DeptBean bean);

    /**
     * 
     * 获取最后一个部门ID
     *
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午5:05:39
     */
    Integer getLastDeptId();

    /**
     * 
     * 修改部门名称
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:21:20
     */
    Integer edit(DeptBean bean);

    /**
     * 
     * 删除部门
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年8月15日 下午2:31:32
     */
    Integer delete(@Param("deptId") Integer deptId);

    /**
     * 
     * 是否有子部门
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年8月16日 上午9:14:24
     */
    Integer havaDepts( @Param("deptId") Integer deptId);
    /**
     * 
     * 是否存在此部门名称
     *
     * @param deptName
     * @return
     * 
     * @author jianghan
     * @date 2017年9月19日 下午3:21:57
     */
    Integer getDeptByName(@Param("deptName") String deptName,@Param("deptId") Integer deptId);
    /**
     * 
     * 根据部门查询是否有员工
     *
     * @param deptId
     * @return
     * 
     * @author jianghan
     * @date 2017年8月31日 下午2:35:06
     */
    Integer empCountByDept(@Param("deptId") Integer deptId);

    /**
     * 批量添加部门
     *
     * @param deptList
     * 
     * @author lidoudou
     * @date 2017年8月23日 下午3:30:47
     */
    void insertBatch(List<DeptBean> deptList);

    /**
     * 批量更新部门
     *
     * @param deptList
     * 
     * @author lidoudou
     * @date 2017年8月23日 下午3:31:10
     */
    void updateBatch(List<DeptBean> deptList);

    List<Integer> getExistDeptId(@Param("deptIds") List<Integer> deptIds);
    
    /**
     * 
     * 依据部门ids 获取部门列表信息 
     * 
     * @param list
     * @return
     * 
     * @author zhangjingtao 
     * @date 2017年9月12日 下午1:08:43
     */
    List<DeptBean> deptByDeptIds(@Param("list") List<Integer> list);

    DeptBean getDeptById(@Param("deptId") Integer deptId);
}
