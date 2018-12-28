package com.lyarc.tp.corp.workflowact.config;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionEntity;
import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionManager;
import com.lyarc.tp.corp.workflowact.ctrl.creator.RuntimeActivityCreator;
import com.lyarc.tp.corp.workflowact.util.ProcessDefinitionUtils;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineLifecycleListener;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @description 引擎启动listener
 * @param
 * @return
 * @author wangting
 * @date 2018/5/23 0023 14:32
 *
 */
@Component
public class MyEngineListener implements ProcessEngineLifecycleListener {
   @Autowired
   RuntimeActivityDefinitionManager activityDefinitionManager;

   @Override
   public void onProcessEngineBuilt(ProcessEngine processEngine) {
       try{
           for (RuntimeActivityDefinitionEntity entity : activityDefinitionManager.list())
           {
               ProcessDefinitionEntity processDefinition = ProcessDefinitionUtils.getProcessDefinition(processEngine,
                       entity.getProcessDefinitionId());
               if (processDefinition != null)
               {
                   RuntimeActivityCreator activitiesCreator = (RuntimeActivityCreator) Class.forName(
                           entity.getFactoryName()).newInstance();

                   //创建activity
                   entity.deserializeProperties();
                   activitiesCreator.createActivities(processEngine, processDefinition, entity);
               }
           }
       }catch (Exception e){
           LoggerFactory.getLogger(MyEngineListener.class).error("初始化加载自定义流程出错",e);
       }
   }

   @Override
   public void onProcessEngineClosed(ProcessEngine processEngine) {

   }
}
