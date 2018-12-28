package com.lyarc.tp.corp.workflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lyarc.tp.corp.workflow.bean.WorkflowForm;
import com.lyarc.tp.corp.workflow.dto.WorkflowFormListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowFormFlow;
import com.lyarc.tp.corp.workflow.form.WorkflowFormParam;

/**
 * 
 * 工作流
 * 表单接口
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月25日 上午11:19:23
 */
@Mapper
public interface WorkflowFormMapper {

    /**
     * 
     * 创建表单
     *
     * @param form
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:19:53
     */
    Integer insert(WorkflowForm form);
    /**
     * 
     * 编辑表单
     *
     * @param form
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:20:34
     */
    void update(WorkflowForm form);
    /**
     * 
     * 设置流程
     *
     * @param form
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:20:51
     */
    void setFlow(WorkflowFormFlow form);
    /**
     * 
     * 获取详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 下午5:16:52
     */
    WorkflowForm get(@Param("id") Integer id);
    /**
     * 
     * 为应用设置表单
     *
     * @param appId
     * @param ids
     * 
     * @author jianghan
     * @date 2018年5月28日 下午5:13:43
     */
    void setForm(@Param("appId") Integer appId, @Param("ids") Integer[] ids);
    
    /**
     * 
     * 表单列表
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年4月26日 上午11:38:18
     */
    List<WorkflowFormListDTO> list(WorkflowFormParam param);
    /**
     * 
     * 我的表单
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年6月15日 下午2:59:40
     */
    List<WorkflowFormListDTO> myForms(WorkflowFormParam param);
}
