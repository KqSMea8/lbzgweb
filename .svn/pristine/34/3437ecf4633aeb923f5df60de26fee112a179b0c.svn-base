package com.lyarc.tp.corp.workflowact.service;

import org.activiti.bpmn.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlowCreateService {

    /*任务节点*/
    public UserTask createUserTask(String id,String name, String assignee,String formKey) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(getId(id));
        userTask.setAssignee(assignee);
        if(StringUtils.isNotEmpty(formKey)){
            userTask.setFormKey(formKey);
        }
        return userTask;
    }

    /*多实例任务节点*/
    public UserTask createMultiInstUserTask(String id,String name, List<String> candidateGroups,String formKey) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(getId(id));
        if(StringUtils.isNotEmpty(formKey)){
            userTask.setFormKey(formKey);
        }
        MultiInstanceLoopCharacteristics multiInst = new MultiInstanceLoopCharacteristics();
        multiInst.setSequential(false);

        userTask.setLoopCharacteristics(multiInst);
        if(candidateGroups!= null){
            userTask.setCandidateGroups(candidateGroups);
        }

        return userTask;
    }

    /*连线*/
    public SequenceFlow createSequenceFlow(String id,String from, String to, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setId(getId(id));
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        if(StringUtils.isNotEmpty(conditionExpression)){
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }

    /*排他网关*/
    public ExclusiveGateway createExclusiveGateway(String id,String name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(getId(id));
        exclusiveGateway.setName(name);
        return exclusiveGateway;
    }

    /*包容网关*/
    public InclusiveGateway createInclusiveGateway(String id,String name) {
        InclusiveGateway inclusiveGateway = new InclusiveGateway();
        inclusiveGateway.setId(getId(id));
        inclusiveGateway.setName(name);
        return inclusiveGateway;
    }

    /*开始节点*/
    public StartEvent createStartEvent(String id) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(getId(id));
        return startEvent;
    }

    /*结束节点*/
    public EndEvent createEndEvent(String id) {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(getId(id));
        return endEvent;
    }

    public String getId(String id) {
        String prefix = "sid-";
        return StringUtils.isNotEmpty(id) ? id : prefix+UUID.randomUUID().toString();
    }
}
