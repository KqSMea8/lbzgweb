package com.lyarc.tp.corp.workflowact.ctrl.impl;

import com.lyarc.tp.corp.workflowact.ctrl.RuntimeActivityDefinitionManager;
import com.lyarc.tp.corp.workflowact.ctrl.TaskFlowControlService;
import com.lyarc.tp.corp.workflowact.ctrl.TaskFlowControlServiceFactory;
import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaskFlowControlServiceFactory implements TaskFlowControlServiceFactory
{
	@Autowired
	RuntimeActivityDefinitionManager _activitiesCreationStore;

	@Autowired
	ProcessEngine _processEngine;

	@Override
	public TaskFlowControlService create(String processId)
	{
		return new DefaultTaskFlowControlService(_activitiesCreationStore, _processEngine, processId);
	}
}
