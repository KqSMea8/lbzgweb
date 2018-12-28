package com.lyarc.tp.corp.workflowact.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;

public class UpdateHiInstanceReasonCommand implements Command<Void> {
    protected String instanceId;
    protected String deleteReason;

    public UpdateHiInstanceReasonCommand(String instanceId, String deleteReason) {
        this.instanceId = instanceId;
        this.deleteReason = deleteReason;
    }
    @Override
    public Void execute(CommandContext commandContext) {
        HistoricProcessInstanceEntity historicTaskInstance = commandContext
                .getDbSqlSession().selectById(HistoricProcessInstanceEntity.class,instanceId);
        if (historicTaskInstance != null) {
            historicTaskInstance.markEnded(deleteReason);
        }
        return null;
    }
}
