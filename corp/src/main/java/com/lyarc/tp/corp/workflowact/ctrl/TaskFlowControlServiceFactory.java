package com.lyarc.tp.corp.workflowact.ctrl;

public interface TaskFlowControlServiceFactory
{

	TaskFlowControlService create(String processId);

}