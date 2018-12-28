package com.lyarc.tp.corp.workflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyarc.tp.corp.workflow.bean.WorkflowApp;
import com.lyarc.tp.corp.workflow.dao.WorkflowAppMapper;
import com.lyarc.tp.corp.workflow.dto.WorkflowAppListDTO;
import com.lyarc.tp.corp.workflow.form.WorkflowAuthForm;
import com.lyarc.tp.corp.workflow.form.WorkflowMenuForm;

/**
 * 
 * 工作流
 * 应用
 * 
 * @version 1.0
 * @since JDK1.8
 * @author jianghan
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2018 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2018年4月24日 上午11:58:15
 */
@Service
public class WorkflowAppService {

    @Autowired
    private WorkflowAppMapper mapper;
    /**
     * 
     * 获取应用详情
     *
     * @param id
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 上午11:58:36
     */
    public WorkflowApp get(Integer id){
        return mapper.get(id);
    }
    /**
     * 
     * 创建应用
     *
     * @param workflow
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午2:01:19
     */
    public Integer create(WorkflowApp workflow){
        mapper.insert(workflow);
        return workflow.getId();
    }
    /**
     * 
     * 编辑应用基本信息
     *
     * @param workflow
     * @return
     * 
     * @author jianghan
     * @date 2018年4月24日 下午2:08:46
     */
    public Integer update(WorkflowApp workflow){
        return mapper.update(workflow);
    }
    /**
     * 
     * 设置使用权限
     *
     * @param id
     * @param useScope
     * 
     * @author jianghan
     * @date 2018年4月24日 下午2:27:23
     */
    public void setAuth(WorkflowAuthForm form){
       mapper.setAuth(form);
    }
    /**
     * 
     * 设置菜单
     *
     * @param id
     * @param menuJson
     * 
     * @author jianghan
     * @date 2018年4月24日 下午2:41:25
     */
    public void setMenu(WorkflowMenuForm form){
        mapper.setMenu(form);
    }
    /**
     * 
     * 应用列表
     *
     * @param corpId
     * @return
     * 
     * @author jianghan
     * @date 2018年4月26日 上午10:21:55
     */
    public List<WorkflowAppListDTO> list(String corpId){
        return mapper.list(corpId);
    }
    /**
     * 
     * 我的应用列表
     *
     * @param corpId
     * @param empId
     * @return
     * 
     * @author jianghan
     * @date 2018年6月15日 下午2:48:18
     */
    public List<WorkflowAppListDTO> myApps(String corpId,String empId){
        return mapper.myApps(corpId, empId);
    }
}
