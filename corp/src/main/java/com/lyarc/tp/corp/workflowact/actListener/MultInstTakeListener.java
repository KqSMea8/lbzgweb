package com.lyarc.tp.corp.workflowact.actListener;

import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.EmpService; 
import com.lyarc.tp.corp.role.bean.Role;
import com.lyarc.tp.corp.user.service.UserRoleService;
import com.lyarc.tp.corp.user.service.UserService; 
import com.lyarc.tp.corp.util.SpringContextUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowGroup;
import com.lyarc.tp.corp.workflow.service.WorkflowGroupService;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.*;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MultInstTakeListener implements ExecutionListener {

	IdentityService identityService;

	Expression roleOrDeptIds;

	EmpService empservice; 

	UserRoleService userRoleService;	 

	WorkflowGroupService workflowGroupService;
	@Override
	public void notify(DelegateExecution delegateExecution) {
		try {
			if (roleOrDeptIds == null) {
				return;
			}
			String groupIds = roleOrDeptIds.getExpressionText();
			if (StringUtils.isNotEmpty(groupIds)) {
				String[] groupIdArr = groupIds.split(",");
				List<String> assigneeList = new ArrayList<String>();
				for (String groupId : groupIdArr) {
					if(groupId.startsWith("wxid#-#role_")) {
						String id=groupId.substring(12); 
						workflowGroupService=SpringContextUtils.getBean(WorkflowGroupService.class);
						WorkflowGroup workflowGroup = workflowGroupService.get(Integer.parseInt(id));
						if(workflowGroup!=null) {
							String[] empId=workflowGroup.getEmpIds().split(",");
							assigneeList.add("wxid#-#"+empId); 
							userRoleService=SpringContextUtils.getBean(UserRoleService.class);
							List<com.lyarc.tp.corp.user.bean.User> roles = userRoleService.selectUserByRole(Integer.parseInt(id));
							if(roles!=null) {
								for(com.lyarc.tp.corp.user.bean.User role:roles) {
									if(StringUtils.isNotEmpty(role.getEmpId())) {
										assigneeList.add("wxid#-#"+role.getEmpId());                				
									}
								} 
							}
						}
						if(groupId.startsWith("wxid#-#department_")) {
							String deptId=groupId.substring(18);
							empservice=SpringContextUtils.getBean(EmpService.class);
							List<EmployeeBean> emps = empservice.getEmpsByDept(Integer.parseInt(deptId));
							if(emps!=null) {
								for(EmployeeBean emp:emps) {
									assigneeList.add("wxid#-#"+emp.getEmpId());
								}
							}
						} 
						List<User> list = identityService.createUserQuery().memberOfGroup(groupId).list(); 
						delegateExecution.setVariableLocal("assigneeList", assigneeList);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
