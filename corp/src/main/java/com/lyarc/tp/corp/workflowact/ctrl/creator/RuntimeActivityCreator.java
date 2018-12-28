package com.lyarc.tp.corp.workflowact.ctrl.creator;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionEntity;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

public interface RuntimeActivityCreator
{
	public ActivityImpl[] createActivities(ProcessEngine processEngine, ProcessDefinitionEntity processDefinition,
                                           RuntimeActivityDefinitionEntity info);
}