package com.lyarc.tp.corp.contacts.dao;

import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.bean.QueryEmpBean;
import com.lyarc.tp.corp.contacts.dto.ContactDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;


/**
 * 员工
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月15日 10:46:23
 */
@Mapper
public interface EmpMapper {

    /**
     * 方法描述 获取员工列表
     * 
     * @param queryEmpBean 查询参数对象
     * @return List<EmployeeBean>
     *
     * @author huanghaoqi
     * @date 2017年08月15日 11:26:26
     */
    List<EmployeeBean> list(QueryEmpBean queryEmpBean);

    /**
     * 方法描述 获取员工总数
     * 
     * @param queryEmpBean 查询参数对象
     * @return Long
     *
     * @author huanghaoqi
     * @date 2017年08月17日 12:49:10
     */
    Long count(QueryEmpBean queryEmpBean);

    /**
     * 方法描述 添加
     * 
     * @param employeeBean 添加对象
     * 
     * @author huanghaoqi
     * @date 2017年08月15日 14:14:08
     */
    void insert(EmployeeBean employeeBean);

    /**
     * 方法描述 删除员工
     * 
     * @param empIds 用户编号数组
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:06:14
     */
    void delete(  @Param("empIds") String[] empIds);

    /**
     * 方法描述 更新员工信息
     * 
     * @param employeeBean 员工对象
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:16:59
     */
    void update(EmployeeBean employeeBean);

    /**
     * 方法描述 更新员工所在部门
     * 
     * @param empIds 员工编号数组
     * @param deptId 部门编号数组
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:34:52
     */
    void updateDept(  @Param("empIds") String[] empIds, @Param("deptId") String deptId);

    /**
     * 方法描述 更新员工离职状态
     * 
     * @param empIds 员工编号
     * @param dimissedStatus 离职状态
     *
     * @author huanghaoqi
     * @date 2017年08月15日 15:46:31
     */
    void updateDimissed(  @Param("empIds") String[] empIds,
                        @Param("dimissedStatus") Integer dimissedStatus);

    /**
     * 方法描述 员工信息详情
     *
     * @param empId 员工编号
     * @return EmployeeBean
     *
     * @author huanghaoqi
     * @date 2017年08月15日 17:37:13
     */
    EmployeeBean get(@Param("empId") String empId);

    EmployeeBean getById(@Param("id") Integer id);

    /**
     * 批量添加
     *
     * @param empList
     *
     * @author lidoudou
     * @date 2017年8月23日 上午10:58:59
     */
    void insertBatch(List<EmployeeBean> empList);

    /**
     * 批量更新
     *
     * @param empList
     *
     * @author lidoudou
     * @date 2017年8月23日 上午10:58:59
     */
    void updateBatch(List<EmployeeBean> empList);

    /**
     * 查询已存在用户
     *
     * @param userIds
     * @return
     *
     * @author lidoudou
     * @date 2017年8月23日 上午11:00:43
     */
    List<String> getExistUserId(  @Param("userIds") List<String> userIds);

    /**
     *
     * 方法描述 依据empId获取基本信息
     *
     * @param list
     * @return
     *
     * @author zhangjingtao
     * @date 2017年8月24日 下午1:47:53
     */
    Set<ContactDTO> getBaseInfoByEmpId(@Param("list") List<String> list);

    /**
     *
     * 方法描述 依据部门获取基本信息
     *
     * @param deptId
     * @return
     *
     * @author zhangjingtao
     * @date 2017年8月24日 下午1:48:24
     */
    Set<ContactDTO> getBaseInfoByDeptId(@Param("deptId") String deptId);

    /**
     * 方法描述 根据用户ID数组获取信息
     *
     * @param empIds 用户ID
     * @return List<EmpDTO>
     *
     * @author huanghaoqi
     * @date 2017年08月24日 16:07:54
     */
    List<EmployeeBean> getEmpByEmpIdArray(  @Param("empIds") String[] empIds);
    /**
     *
     * 方法描述  根据empId获取有重复的账号的员工
     *
     * @param empId
     * @return
     *
     * @author jianghan
     * @date 2018年3月15日 上午11:59:53
     */
    List<EmployeeBean> getListByEmpId(  @Param("empId") String empId);

    /**
     *
     * 查询账号是否已存在
     *
     * @param empId
     * @return
     *
     * @author jianghan
     * @date 2017年9月10日 下午2:46:03
     */
    Integer getExist(  @Param("empId") String empId);

    /**
     * 方法描述 根据用户姓名或拼音缩写模糊查询
     *
     * @param likeContent 模糊查询内容
     *
     * @author huanghaoqi
     * @date 2017年09月13日 11:55:09
     */
    List<EmployeeBean> listByNameOrPinyin(  @Param("likeContent") String likeContent, @Param("deptId") Integer deptId);

    /**
     * 通过企业编号以及手机号获取
     *
     * @param mobile
     * @return
     *
     * @author lidoudou
     * @date 2017年9月29日 下午5:52:36
     */
    EmployeeBean getByMobile(  @Param("mobile") String mobile);
    /**
     *
     * 查询是否存在相同的手机号或者邮箱
     *
     * @param email
     * @return
     *
     * @author jianghan
     * @date 2018年3月20日 下午5:03:53
     */
    EmployeeBean getByEmail(  @Param("email") String email);
    
    List<EmployeeBean> getEmpsByDept(@Param("deptId") Integer deptId);
}
