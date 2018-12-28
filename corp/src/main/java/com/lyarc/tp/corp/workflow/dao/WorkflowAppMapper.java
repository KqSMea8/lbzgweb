package com.lyarc.tp.corp.workflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.workflow.bean.WorkflowApp;
import com.lyarc.tp.corp.workflow.dto.WorkflowAppListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowAuthForm;
import com.lyarc.tp.corp.workflow.form.WorkflowMenuForm;

/**
 * 
 * 工作流接口
 * 应用
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月24日 上午9:53:29
 */
@Mapper
public interface WorkflowAppMapper {

    /**
     * 
     * 添加
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 上午9:54:21
     */
    Integer insert(WorkflowApp bean);
    /**
     * 
     * 编辑基本信息
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 上午9:55:27
     */
    Integer update(WorkflowApp bean);
    /**
     * 
     * 获取详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 上午10:00:51
     */
    WorkflowApp get(@Param("id") Integer id);
    /**
     *
     * 设置使用范围
     *
     *
     * @author jianghan
     * @date 2018年4月24日 上午10:00:55
     */
    void setAuth(WorkflowAuthForm form);
    /**
     *
     * 设置菜单
     *
     *
     * @author jianghan
     * @date 2018年4月24日 上午10:00:59
     */
    void setMenu(WorkflowMenuForm form);
    /**
     *
     * 应用列表
     *
     * @param corpId
     * @return
     *
     * @author jianghan
     * @date 2018年4月26日 上午10:19:35
     */
    List<WorkflowAppListDTO> list(@Param("corpId") String corpId);
    /**
     *
     * 我的应用列表
     *
     * @param corpId
     * @param empId
     * @return
     *
     * @author jianghan
     * @date 2018年6月15日 下午2:47:07
     */
    List<WorkflowAppListDTO> myApps(@Param("corpId") String corpId, @Param("empId") String empId);
}
