package com.lyarc.tp.corp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.workflow.bean.WorkflowForm;
import com.lyarc.tp.corp.workflow.dao.WorkflowFormMapper;
import com.lyarc.tp.corp.workflow.dto.WorkflowFormListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowFormFlow;
import com.lyarc.tp.corp.workflow.form.WorkflowFormParam;

/**
 * 
 * 表单
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月25日 上午11:30:15
 */
@Service
public class WorkflowFormService {

    @Autowired
    private WorkflowFormMapper mapper;
    /**
     * 
     * 创建表单
     *
     * @param bean
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:30:05
     */
    public Integer insert(WorkflowForm bean){
        mapper.insert(bean);
        return bean.getId();
    }
    /**
     * 
     * 编辑表单
     *
     * @param bean
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:37:45
     */
    public void update(WorkflowForm bean){
        mapper.update(bean);
    }
    /**
     * 
     * 设置流程
     *
     * @param form
     * 
     * @author jianghan
     * @date 2018年4月25日 上午11:41:26
     */
    public void setFlow(WorkflowFormFlow form){
        mapper.setFlow(form);
    }
    /**
     * 
     * 获取详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年4月25日 下午5:18:45
     */
    public WorkflowForm get(Integer id){
        return mapper.get(id);
    }
    /**
     * 
     * 表单列表
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年4月26日 上午11:39:30
     */
    public List<WorkflowFormListDTO> list(WorkflowFormParam param){
        return mapper.list(param);
    }
    /**
     * 
     * 为应用设置表单
     *
     * @param appId
     * @param ids
     * 
     * @author jianghan
     * @date 2018年5月28日 下午5:15:14
     */
    public void setForm(Integer appId,Integer[] ids){
        mapper.setForm(appId, ids);
    }
    /**
     * 
     * 我的应用列表
     *
     * @param param
     * @return
     * 
     * @author jianghan
     * @date 2018年6月15日 下午3:33:34
     */
    public List<WorkflowFormListDTO> myForms(WorkflowFormParam param){
        return mapper.myForms(param);
    }
}
