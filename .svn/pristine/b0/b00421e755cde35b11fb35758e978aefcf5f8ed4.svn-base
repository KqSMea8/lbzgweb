package com.lyarc.tp.corp.workflowact.actListener;

import com.lyarc.tp.corp.util.SpringContextUtils;
import com.lyarc.tp.corp.workflow.service.WorkflowGroupService;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupTaskListenerImpl  implements TaskListener {
    IdentityService identityService;

    Expression roleOrDeptIds;
    
    @Autowired
    WorkflowGroupService workflowGroupService;
    @Override
    public void notify(DelegateTask delegateTask) {
        try {
            if (roleOrDeptIds == null) {
                return;
            }
            String groupIds = roleOrDeptIds.getExpressionText();
            if (StringUtils.isNotEmpty(groupIds)) {
                identityService = SpringContextUtils.getBean(IdentityService.class);
                String[] groupIdArr = groupIds.split(",");
                for (String groupId : groupIdArr) {
                    List<User> list = identityService.createUserQuery().memberOfGroup(groupId).list();
                    List<String> canditateUsers = list.stream().map(User::getId).collect(Collectors.toList());
                    delegateTask.addCandidateUsers(canditateUsers);
//                    delegateTask.setAssignee();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
