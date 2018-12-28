package com.lyarc.tp.corp.workflowact.service;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.contacts.bean.DeptBean;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.DeptService;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.corp.bean.Corp;
import com.lyarc.tp.corp.corp.service.CorpService;
import com.lyarc.tp.corp.workflow.bean.WorkflowGroup;
import com.lyarc.tp.corp.workflow.dto.WorkflowGroupListDTO;
import com.lyarc.tp.corp.workflow.service.WorkflowGroupService;
import com.lyarc.tp.corp.workflowact.constant.WorkFlowConst;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  *
  * @description 用户信息同步服务
  * @param
  * @return
  * @author wangting
  * @date 2018/5/15 0015 10:39
  *
  */
@Service
public class IndentitySyncService {

    private String groupDept = "dept";

    private String groupRole = "role";

    private String roleIdPrefix = "role_";
    @Autowired
    private CorpService corpService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmpService empService;

    @Autowired
    private IdentityService identityService;
    @Autowired
    private WorkflowGroupService groupService;

    @Scheduled(cron = "* * 23 * * ?") //
    public void syncData() {
        //获取所有corp
        List<Corp> list = corpService.list();
        for (Corp corp : list) {
            String corpId = corp.getCorpId();
            //获取所有部门
            List<DeptBean> deptList = deptService.list();

            syncDept(deptList);
            //获取所有emp
            List<EmployeeBean> empList = empService.listEmp();
            syncUser(empList);

            //获取所有groupS
            WorkflowGroup param = new WorkflowGroup();
            param.setCorpId(corpId);
            List<WorkflowGroupListDTO> groupList = groupService.list(param);
            syncGroup(groupList);
        }
    }

    /**
     *
     * @description 同步部门
     * @param
     * @return
     * @author wangting
     * @date 2018/5/15 0015 10:46
     *
     */
    @Transactional
    public void syncDept(List<DeptBean> deptList) {
        for (DeptBean deptBean : deptList) {
            String actDeptId = deptBean.getCorpId() + WorkFlowConst.ID_SPLIT + deptBean.getDeptId();
            List<Group> list = identityService.createGroupQuery().groupId(actDeptId).groupType(groupDept).list();
            if(list== null || list.size() == 0){
                Group group = identityService.newGroup(actDeptId);
                group.setType(groupDept);
                group.setName(deptBean.getDeptName());
                identityService.saveGroup(group);
            }
        }
    }

 /**
  *
  * @description 同步用户
  * @param
  * @return
  * @author wangting
  * @date 2018/5/15 0015 10:46
  *
  */
    @Transactional
    public void syncUser(List<EmployeeBean> empList) {
        String corpId = UserHolder.getUser().getCorpId();
        for (EmployeeBean emp : empList) {
            String actEmpId = corpId + WorkFlowConst.ID_SPLIT + emp.getEmpId();
            List<User> list = identityService.createUserQuery().userId(actEmpId).list();
            if(list== null || list.size() == 0){
                User user = identityService.newUser(actEmpId);
                identityService.saveUser(user);
                String actDeptId = corpId+ WorkFlowConst.ID_SPLIT + emp.getDeptId();

                User existUser = identityService.createUserQuery().memberOfGroup(actDeptId).userId(user.getId()).singleResult();
                if(existUser == null){
                    identityService.createMembership(actEmpId,actDeptId);
                }

            }
        }
    }
     /**
      *
      * @description 同步角色
      * @param
      * @return
      * @author wangting
      * @date 2018/5/21 0021 9:49
      *
      */
    public void syncGroup(List<WorkflowGroupListDTO> groupList){
        for (WorkflowGroupListDTO group : groupList) {
            String empIds = group.getEmpIds();
            if (StringUtils.isNotEmpty(empIds)) {
                //构造 activit roleId 由于grouprole 和groupDept 存一张表 id 构造格式一致有可能重复 故将roleid 加roleIdPrefix 区分
                String actRoleId = group.getCorpId() + WorkFlowConst.ID_SPLIT +roleIdPrefix+ group.getId();
                //判断是否已存在
                Group existRole = identityService.createGroupQuery().groupId(actRoleId).groupType(groupRole).singleResult();
                if(existRole== null || existRole.getId() == null){
                    Group role = identityService.newGroup(actRoleId);
                    role.setType(groupRole);
                    role.setName(group.getTitle());
                    identityService.saveGroup(role);
                }
                //保存用户及用户group 关系
                String[] empIdArr = empIds.split(",");
                for (int i = 0; i < empIdArr.length; i++) {
                    String empId = empIdArr[i];
                    String actEmpId = group.getCorpId() + WorkFlowConst.ID_SPLIT + empId;
                    User user = identityService.createUserQuery().userId(actEmpId).singleResult();
                    //用户不存在则插入
                    if(user == null || StringUtils.isEmpty(user.getId())){
                        user = identityService.newUser(actEmpId);
                        identityService.saveUser(user);
                    }
                    //保存角色 用户关系
                    User userRoleMember = identityService.createUserQuery().memberOfGroup(actRoleId).userId(user.getId()).singleResult();
                    if(userRoleMember == null){
                        identityService.createMembership(actEmpId,actRoleId);
                    }
                }
            }
        }
    }
}
