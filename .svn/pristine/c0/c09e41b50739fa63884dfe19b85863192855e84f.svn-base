package com.lyarc.tp.corp.workflowact.actListener;

import com.lyarc.tp.corp.timingtask.ModifyFlowStatusTask;
import com.lyarc.tp.corp.util.SpringContextUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEndEvent;
import com.lyarc.tp.corp.workflow.bean.WorkflowInstEvent;
import com.lyarc.tp.corp.workflow.dao.WorkflowInstEventMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CompleteInstListener implements ExecutionListener {

    RuntimeService runtimeService;
    
    Logger logger = LoggerFactory.getLogger("workflow");
    
    @Override
    public void notify(DelegateExecution delegateExecution) {
        try {
            String instId = delegateExecution.getProcessInstanceId();
            //根据实例 获取流程
            runtimeService = SpringContextUtils.getBean(RuntimeService.class);
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instId).singleResult();
            //使用流程实例对象获取BusinessKey
            String businessKey = pi.getBusinessKey();
            logger.info("工作流实例完成，实例：" + instId);
            if(StringUtils.isNotEmpty(businessKey)){
                logger.info("工作流实例：" + instId + "，businessKey:"+businessKey);
                String[] split = businessKey.split("_");  
                if(split.length >=2) {
                	WorkflowInstEndEvent wfEvent = new WorkflowInstEndEvent();
                    wfEvent.setInstId(instId); 
                    wfEvent.setWfId(Integer.parseInt(split[0]));
                    wfEvent.setBusinessKey(split[1]);
                    wfEvent.setCause(1);
                    ModifyFlowStatusTask modifyFlowStatusTask = SpringContextUtils.getBean(ModifyFlowStatusTask.class);
                    
                    modifyFlowStatusTask.modifyStatus(wfEvent);
                }  
            }
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	throw e;
        }
    }

}
