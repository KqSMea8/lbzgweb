package com.lyarc.tp.corp.workflowact.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.DateUtil;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.contacts.bean.EmployeeBean;
import com.lyarc.tp.corp.contacts.service.EmpService;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.timingtask.ModifyFlowStatusTask;
import com.lyarc.tp.corp.util.SpringContextUtils;
import com.lyarc.tp.corp.workflow.bean.WorkflowDraft;
import com.lyarc.tp.corp.workflow.bean.WorkflowForm;
import com.lyarc.tp.corp.workflow.form.WorkflowDraftParam;
import com.lyarc.tp.corp.workflow.service.WorkflowDraftService;
import com.lyarc.tp.corp.workflow.service.WorkflowFormService;
import com.lyarc.tp.corp.workflow.service.WorkflowReportService;
import com.lyarc.tp.corp.workflowact.actListener.GroupTaskListenerImpl;
import com.lyarc.tp.corp.workflowact.bean.ActFlowElement;
import com.lyarc.tp.corp.workflowact.bean.ActProcess;
import com.lyarc.tp.corp.workflowact.command.UpdateHiInstanceReasonCommand;
import com.lyarc.tp.corp.workflowact.command.UpdateHiTaskReasonCommand;
import com.lyarc.tp.corp.workflowact.constant.WorkFlowConst;
import com.lyarc.tp.corp.workflow.dao.WorkflowReportQueryMapper;
import com.lyarc.tp.corp.workflowact.ctrl.TaskFlowControlService;
import com.lyarc.tp.corp.workflowact.ctrl.TaskFlowControlServiceFactory;
import com.lyarc.tp.corp.workflowact.dto.ProcessInfoDto;
import com.lyarc.tp.corp.workflowact.dto.ProcessTaskDto;
import com.lyarc.tp.corp.workflowact.dto.ProcessVariableDto;
import com.lyarc.tp.corp.workflowact.dto.TaskInfoDto;
import com.lyarc.tp.corp.workflowact.form.TaskQueryForm;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.*;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.history.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.*;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActivitiService{
    private static Logger logger = LoggerFactory.getLogger(ActivitiService.class);

    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    ThreadLocal<Map<String,String>> t1 = new ThreadLocal<>();

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FormService formService;
    @Autowired
    private EmpService empService;
    @Autowired
    private ActivitiToolsService activitiToolsService;
    @Autowired
    private FlowCreateService flowCreateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WorkflowFormService wfFormService;

    @Autowired
    WorkflowDraftService draftService;

    @Autowired
    TaskFlowControlServiceFactory taskFlowControlServiceFactory;

    @Autowired
    GroupTaskListenerImpl groupTaskListener;



     /**
      *
      * @description 我的待发送
      * @param
      * @return
      * @author wangting
      * @date 2018/5/11 0011 16:15
      *
      */
    public PageResultBean getMyWaitSendProcess(int start, int pageSize, TaskQueryForm taskQueryBo){
//        if (pageNo <= 1) {
//            pageNo = 1;
//        }
//        if (pageSize <= 1) {
//            pageSize = 999;
//        }
//        int start = (pageNo - 1) * pageSize;
//        int limit = pageSize;

        WorkflowDraftParam param = new WorkflowDraftParam();
        param.setCorpId(taskQueryBo.getCorpId());
        param.setEmpId(taskQueryBo.getAssignee());
        param.setOffset(start);
        param.setPageSize(pageSize);
        List<WorkflowDraft> list = draftService.list(param);
        List<ProcessInfoDto> resList = new ArrayList<>();
        Long count = draftService.count(param);
        for (WorkflowDraft draft : list) {
            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setSource("普通发起");
            dto.setDraftId(draft.getId());
            dto.setProcessName(draft.getTitle());
            dto.setProcessCreateTime(DateUtil.getDateStr(draft.getCreateTime(),""));
            resList.add(dto);
        }

        return PageResultBean.success((Long) count,resList);
    }



/**
      *
      * @description 我的已发送
      * @param
      * @return
      * @author wangting
      * @date 2018/5/11 0011 16:15
      *
      */
    public PageResultBean getMySendProcess(int start, int pageSize, TaskQueryForm taskQueryBo){

        int limit = pageSize;

//        List<HistoricProcessInstance> historicProcessInstances = (historyService.createHistoricProcessInstanceQuery()
//                .startedBy(taskQueryBo.getAssignee())
//                .processInstanceTenantId(taskQueryBo.getCorpId())
//                .orderByProcessInstanceStartTime().desc())
//                .listPage(start, limit);
        StringBuffer sb = new StringBuffer("");
        sb.append(" SELECT DISTINCT RES.*, DEF.KEY_ AS PROC_DEF_KEY_, DEF.NAME_ AS PROC_DEF_NAME_," +
                " DEF.VERSION_ AS PROC_DEF_VERSION_, DEF.DEPLOYMENT_ID_ AS DEPLOYMENT_ID_\n" +
                "FROM ACT_HI_PROCINST RES\n" +
                "LEFT OUTER\n" +
                "JOIN ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "WHERE RES.START_USER_ID_ = #{assigneeId} AND RES.TENANT_ID_ = #{tenantId}" );
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and RES.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        StringBuffer countsb = new StringBuffer(" select count(1) from (").append(sb.toString()).append(") t");

        sb.append(" ORDER BY RES.START_TIME_ DESC ");
        List<HistoricProcessInstance> historicProcessInstances = historyService.createNativeHistoricProcessInstanceQuery().sql(
                sb.toString())
                .parameter("assigneeId", taskQueryBo.getAssignee())
                .parameter("tenantId", taskQueryBo.getCorpId()).listPage(start,limit);


        long count = historyService.createNativeHistoricProcessInstanceQuery().sql(
                countsb.toString())
                .parameter("assigneeId", taskQueryBo.getAssignee())
                .parameter("tenantId", taskQueryBo.getCorpId()).count();

        //构建员工map
        getEmpMap(taskQueryBo.getCorpId());

        List<HistoricTaskInstance> instFirstList = historyService.createNativeHistoricTaskInstanceQuery()
                .sql("select * from ACT_HI_TASKINST  where ASSIGNEE_='"+taskQueryBo.getAssignee()+"' group by PROC_INST_ID_ ORDER BY ID_ ASC")
                .list();
        //构造map<instid,firstTaskId>
        Map<String, String> map = instFirstList.stream().collect(Collectors.toMap(HistoricTaskInstance::getProcessInstanceId, HistoricTaskInstance::getId));


        List<ProcessInfoDto> resList = new ArrayList<>();
        for (HistoricProcessInstance hisProcess : historicProcessInstances) {
            //获取流程实例下第一个任务

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(map.get(hisProcess.getId()));
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));
            //设置流程状态和处理人
            setDtoStatus(dto, hisProcess,taskQueryBo.getCorpId());

            resList.add(dto);
        }
        return PageResultBean.success((Long)count,resList);
    }

     /**
      *
      * @description 设置流程dto 的状态和处理人
      * @param
      * @return
      * @author wangting
      * @date 2018/5/28 0028 17:33
      *
      */
    private void setDtoStatus(ProcessInfoDto dto,HistoricProcessInstance hisProcess,String corpId){
        ProcessInstance rpi = runtimeService
                .createProcessInstanceQuery()//创建流程实例查询对象
                .processInstanceId(hisProcess.getId())
                .singleResult();
        if(rpi == null){
            dto.setStatus("已结束");
        }else{
            dto.setStatus("处理中");
        }

        //获取流程实例名称
        String processName = StringUtils.isNotEmpty(hisProcess.getName()) ? hisProcess.getName() : hisProcess.getProcessDefinitionName();
        dto.setProcessName(processName);
        //当前流程的待处理人 ，若是终止流程 则显示流程被xx终止
        String currentDealer = "";
        if(WorkFlowConst.TASK_REASON_TERMINATION.equals(hisProcess.getDeleteReason())){
            dto.setStatus("已终止");
            //获取实例的最后处理人
            List<HistoricTaskInstance> taskList = historyService.createNativeHistoricTaskInstanceQuery()
                    .sql("select * from ACT_HI_TASKINST  where PROC_INST_ID_='"+hisProcess.getId()+"' ORDER BY ID_ DESC")
                    .list();
            if(taskList !=null && taskList.size()>0){
                HistoricTaskInstance historicTaskInstance = taskList.get(0);
                if(historicTaskInstance.getDeleteReason().equals(WorkFlowConst.TASK_REASON_TERMINATION)){
                    currentDealer ="流程被"+getUserName(historicTaskInstance.getAssignee())+"终止";
                }
            }
        }else{
            currentDealer = getCurrentDealer(hisProcess.getId(), corpId);
        }
        dto.setDealName(currentDealer);
    }


    /**
     *
     * @description 获取流程定义
     * @param
     * @return
     * @author wangting
     * @date 2018/5/22 0022 10:33
     *
     */
    private ProcessDefinition getProcessDefineByDefId(String defId){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(defId).singleResult();
        return processDefinition;
    }

     /**
      *
      * @description 获取员工map(empid,trueName)
      * @param
      * @return
      * @author wangting
      * @date 2018/5/28 0028 17:57
      *
      */
    private void getEmpMap(String corpId){

        List<EmployeeBean> employeeBeans = empService.listEmp();
        Map<String, String> map = employeeBeans.stream().collect(Collectors.toMap(EmployeeBean::getEmpId, a -> a.getTrueName(), (k1, k2) -> k1));
        t1.set(map);

    }
 /**
  *
  * @description 工具方法 获取流程的当前处理人
  * @param
  * @return
  * @author wangting
  * @date 2018/5/22 0022 10:33
  *
  */
    private String getCurrentDealer(String instId,String corpId){
        Map<String, String> map = t1.get();
        //当前流程待执行的任务
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instId).list();
        StringBuffer sb = new StringBuffer("");
        for (Task task : taskList) {
            String assignee = task.getAssignee();
            if(StringUtils.isNotEmpty(assignee)){
                //assignee 结构wxdca02fc5f641dabb#-#renxin
                String[] split = assignee.split(WorkFlowConst.ID_SPLIT);
                sb.append(map.get(split[1])).append(",");
            }else{//多实例任务为多个处理人
                String id = task.getId();
                List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(id);
                identityLinksForTask.forEach(item->{
                    String[] split = item.getUserId().split(WorkFlowConst.ID_SPLIT);
                    sb.append(map.get(split[1])).append(",");
                });
            }
        }
        if(StringUtils.isNotEmpty(sb.toString())){
            return sb.toString().substring(0,sb.length()-1);
        }
        return "";
    }


    /**
     *
     * @description 我的的已处理
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public PageResultBean getMyHandlerProcess(int start, int pageSize, TaskQueryForm taskQueryBo){
//        if (pageNo <= 1) {
//            pageNo = 1;
//        }
//        if (pageSize <= 1) {
//            pageSize = 999;
//        }
//        int start = (pageNo - 1) * pageSize;
        int limit = pageSize;

        String assignee = taskQueryBo.getAssignee();
        List<Group> groupList = identityService.createGroupQuery().groupMember(assignee).list();
        List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
        String groupIds = groupIdList.stream().collect(Collectors.joining(","));
        //查询我的已办任务

        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT DISTINCT RES.*\n" +
                "FROM ACT_HI_TASKINST RES\n" +
                "left JOIN ACT_HI_IDENTITYLINK HI_OR0 ON HI_OR0.TASK_ID_ = RES.ID_\n" +
//                "left join ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "left join ACT_HI_PROCINST INST on RES.PROC_INST_ID_ = INST.PROC_INST_ID_ "+
                "WHERE RES.END_TIME_ IS NOT NULL AND RES.TENANT_ID_ = #{tenantId} AND \n" +
                " (RES.ASSIGNEE_ = #{assigneeId} OR (RES.ASSIGNEE_ IS NULL AND HI_OR0.TYPE_ = 'candidate' AND (HI_OR0.USER_ID_ = #{assigneeId}\n" +
                "))) and TASK_DEF_KEY_ !='sid_start'" );
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and INST.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        StringBuffer countsb = new StringBuffer(" select count(1) from (").append(sb.toString()).append(") t");

            sb.append(" ORDER BY RES.START_TIME_ DESC");
        List<HistoricTaskInstance> list = historyService.createNativeHistoricTaskInstanceQuery().sql(
                sb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee()).listPage(start,limit);

        List<ProcessInfoDto> resList = new ArrayList<>();
        if(list.size() == 0){
            return PageResultBean.success(new Long(0),resList);
        }

        long count = historyService.createNativeHistoricTaskInstanceQuery().sql(
                countsb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee()).count();

        //获取任务的流程定义（去重）
        List<HistoricTaskInstance> instFirstList = historyService.createNativeHistoricTaskInstanceQuery().sql("select * from ACT_HI_TASKINST group by PROC_INST_ID_ ORDER BY ID_ ASC")
                .list();
        //构造map<instid,firstTaskId>
        Map<String, String> map = instFirstList.stream().collect(Collectors.toMap(HistoricTaskInstance::getProcessInstanceId, HistoricTaskInstance::getId));
        //构建员工map
        getEmpMap(taskQueryBo.getCorpId());
        //构造 实例idset 用于查询实例列表，避免在循环中查
        Set<String> instIdSet = list.stream().map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, Object> instMap = getInstMap(instIdSet);
        for (HistoricTaskInstance historicTaskInstance : list) {
            HistoricProcessInstance hisProcess = (HistoricProcessInstance)instMap.get(historicTaskInstance.getProcessInstanceId());
            //如果流程的发起人和任务处理人是一个人，并且该任务为流程的第一个任务 则该任务在我发起的列表 不应再我的已处理
            String firstTaskId = map.get(hisProcess.getId());
            if (hisProcess.getStartUserId().equals(historicTaskInstance.getAssignee()) && firstTaskId.equals(historicTaskInstance.getId())){
                continue;
            }

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(historicTaskInstance.getId());
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));

            //处理时间
            dto.setDealTime(DateUtil.getDateStr(historicTaskInstance.getEndTime(),""));
            //设置流程状态和处理人
            setDtoStatus(dto, hisProcess,taskQueryBo.getCorpId());
            resList.add(dto);
        }
        return PageResultBean.success(count,resList);
    }

    private Map<String,Object> getInstMap(Set<String> instIds){
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                .processInstanceIds(instIds).list();
        return list.stream().collect(Collectors.toMap(HistoricProcessInstance::getId, Function.identity()));
    }


    /**
     *
     * @description 我的待处理
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public PageResultBean getMyTodoProcess(int start, int pageSize, TaskQueryForm taskQueryBo){
        int limit = pageSize;

//        TaskQuery taskQuery = getTaskQuery(taskQueryBo);
//        // 根据用户id得到任务列表
//        List<Task> todoList = ((TaskQuery) ((TaskQuery) taskQuery
//                .orderByTaskPriority().desc()).orderByTaskCreateTime().desc())
//                .list();

        String assignee = taskQueryBo.getAssignee();
        List<Group> groupList = identityService.createGroupQuery().groupMember(assignee).list();
        List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
        String groupIds = groupIdList.stream().collect(Collectors.joining(","));

        StringBuffer sb = new StringBuffer("SELECT DISTINCT RES.*\n" +
                "FROM ACT_RU_TASK RES\n" +
                "LEFT JOIN ACT_RU_IDENTITYLINK I ON I.TASK_ID_ = RES.ID_\n" +
                //"left join ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "left join ACT_HI_PROCINST INST on RES.PROC_INST_ID_ = INST.PROC_INST_ID_ "+
                "WHERE RES.TENANT_ID_ = #{tenantId} AND (RES.ASSIGNEE_ = #{assigneeId} OR (RES.ASSIGNEE_ IS NULL AND I.TYPE_ = 'candidate' AND (I.USER_ID_ = #{assigneeId}\n" +
                ")))");
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and INST.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        StringBuffer countsb = new StringBuffer(" select count(1) from (").append(sb.toString()).append(") t");

        sb.append(" ORDER BY RES.CREATE_TIME_ DESC");

        NativeTaskQuery nativeTaskQuery = taskService.createNativeTaskQuery().sql(
                sb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee());

        List<Task> todoList = nativeTaskQuery.listPage(start,limit);

        List<ProcessInfoDto> resList = new ArrayList<>();
        if(todoList.size() == 0){
            return PageResultBean.success(new Long(0),resList);
        }

        long count = taskService.createNativeTaskQuery().sql(
                countsb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee()).count();

                //构建员工map
        //getEmpMap(taskQueryBo.getCorpId());
        //构造 实例idset 用于查询实例列表，避免在循环中查
        Set<String> instIdSet = todoList.stream().map(Task::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, Object> instMap = getInstMap(instIdSet);
        for (Task task : todoList) {
            HistoricProcessInstance hisProcess = (HistoricProcessInstance)instMap.get(task.getProcessInstanceId());

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(task.getId());
//            dto.setProcessName(hisProcess.getName());
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));
            ProcessInstance rpi = runtimeService
                    .createProcessInstanceQuery()//创建流程实例查询对象
                    .processInstanceId(hisProcess.getId())
                    .singleResult();
            if(rpi == null){
                dto.setStatus("已结束");
            }else{
                dto.setStatus("处理中");
            }
            //接收时间
            dto.setReceiveTime(DateUtil.getDateStr(task.getCreateTime(),""));
            //获取流程实例名称
            String processName = StringUtils.isNotEmpty(hisProcess.getName()) ? hisProcess.getName() : hisProcess.getProcessDefinitionName();
            dto.setProcessName(processName);
            resList.add(dto);
        }
        return PageResultBean.success(count,resList);
    }

    public Integer getMyTodoProcessCount(TaskQueryForm taskQueryBo){

        String assignee = taskQueryBo.getAssignee();
        List<Group> groupList = identityService.createGroupQuery().groupMember(assignee).list();
        List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
        String groupIds = groupIdList.stream().collect(Collectors.joining(","));

        StringBuffer sb = new StringBuffer("SELECT DISTINCT RES.*\n" +
                "FROM ACT_RU_TASK RES\n" +
                "LEFT JOIN ACT_RU_IDENTITYLINK I ON I.TASK_ID_ = RES.ID_\n" +
//                "left join ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "left join ACT_HI_PROCINST INST on RES.PROC_INST_ID_ = INST.PROC_INST_ID_ "+
                "WHERE RES.TENANT_ID_ = #{tenantId} AND (RES.ASSIGNEE_ = #{assigneeId} OR (RES.ASSIGNEE_ IS NULL AND I.TYPE_ = 'candidate' AND (I.USER_ID_ = #{assigneeId}\n" +
                ")))");
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and INST.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        StringBuffer countsb = new StringBuffer(" select count(1) from (").append(sb.toString()).append(") t");

        sb.append(" ORDER BY RES.CREATE_TIME_ DESC");


        Long count = taskService.createNativeTaskQuery().sql(
                countsb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee()).count();

        return count.intValue();
    }


 /**
  *
  * @description 根据不同的条件获得任务列表 TaskQuery
  * @param
  * @return
  * @author wangting
  * @date 2018/5/31 0031 10:16
  *
  */
    private TaskQuery getTaskQuery(TaskQueryForm taskQueryBo) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        if (taskQueryBo != null) {
            String participant = taskQueryBo.getAssignee();
            if (StringUtils.isNotEmpty(participant)) {
                taskQuery = taskQuery.taskCandidateOrAssigned(participant);
            }
            String systemName = taskQueryBo.getSystemName();
            if (StringUtils.isNotEmpty(systemName)) {
                taskQuery = taskQuery.processVariableValueEquals(
                        "processCategory", systemName);
            }
            String workflowName = taskQueryBo.getWorkflowName();
            if (StringUtils.isNotEmpty(workflowName)) {
                taskQuery = taskQuery.taskDefinitionKey(workflowName);
            }
            String taskId = taskQueryBo.getTaskId();
            if (StringUtils.isNotEmpty(taskId)) {
                taskQuery = taskQuery.taskId(taskId);
            }
            if (StringUtils.isNotEmpty(taskQueryBo.getCorpId())) {
                taskQuery = taskQuery.taskTenantId(taskQueryBo.getCorpId());
            }
        }
        return taskQuery;
    }

    public TaskInfoDto getTaskDetail(String taskId) {
        TaskInfoDto taskBo;
        HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) historyService
                .createHistoricTaskInstanceQuery().taskId(taskId)
                .singleResult();
        taskBo = null;
        if (historicTaskInstance != null) {
            taskBo = setTaskBo(historicTaskInstance);
        }
        return taskBo;
    }

     /**
      *
      * @description 设置页面需要的字段信息 HistoricTaskInstance
      * @param
      * @return
      * @author wangting
      * @date 2018/5/9 0009 17:04
      *
      */
    private TaskInfoDto setTaskBo(HistoricTaskInstance task) {
        TaskInfoDto taskBo = new TaskInfoDto();
        taskBo.setTaskId(task.getId());
        taskBo.setTaskName(task.getName());
        taskBo.setApproveUserName(task.getAssignee() != null ? getUserName(task
                .getAssignee()) : "");
        taskBo.setStartTime(task.getStartTime() != null ? DateUtil.getDateStr(task.getStartTime(),"yyyy-MM-dd HH:mm:ss")
                : "");
        if (task.getEndTime() == null) {
            taskBo.setEndTime("");
        } else {
            taskBo.setEndTime(DateUtil.getDateStr(task.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
        }

        ProcessDefinition processDefinition = (ProcessDefinition) repositoryService
                .createProcessDefinitionQuery().processDefinitionId(
                        task.getProcessDefinitionId()).singleResult();
        taskBo.setBusinessName(processDefinition.getName());
        taskBo.setWorkflowName(processDefinition.getKey());
        //流程状态
        taskBo.setStatus(processDefinition.isSuspended() ? "已挂起" : "正常");
        taskBo.setVersion(String.valueOf(processDefinition.getVersion()));
        HistoricProcessInstance historicProcessInstance = (HistoricProcessInstance) historyService
                .createHistoricProcessInstanceQuery().processInstanceId(
                        task.getProcessInstanceId()).singleResult();
        //得到业务id即eventid
        taskBo.setBusinessEventId(historicProcessInstance.getBusinessKey());
        //发起人姓名
        if (historicProcessInstance.getStartUserId() != null)
        {
            taskBo.setStartUserId(getUserName(historicProcessInstance
                    .getStartUserId()));
        }
        else{
            taskBo.setStartUserId("");
        }

        taskBo.setProcInstId(task.getProcessInstanceId());
//        taskBo.setWorkflowSubject(getWorkflowVariable(historicProcessInstance
//                .getId(), WorkflowConstants.WORKFLOWSUBJECT));
        List<Comment> taskComments = taskService.getTaskComments(task.getId());
        if(taskComments!=null && taskComments.size() > 0){
            taskBo.setComment( taskComments.get(0).getFullMessage());//审批意见
        }
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery() // 创建流程实例查询
                .processInstanceId(task.getProcessInstanceId()) // 用流程实例ID查询
                .singleResult();
        if(processInstance != null){
            taskBo.setProcInstStatus("处理中");
        }else{
            taskBo.setProcInstStatus("已结束");
        }
        return taskBo;
    }


    private String getUserName(String userId){
        String res="";
        if(StringUtils.isNotEmpty(userId)){
            String[] split = userId.split(WorkFlowConst.ID_SPLIT);
            if(split != null && split.length == 2){
                Map<String, String> map = t1.get();
                if(map == null){
                    getEmpMap(split[0]);
                    map = t1.get();
                }
                res = map.get(split[1]);
            }
        }
        return res;
    }

    public List getTaskListByProcInstId(TaskQueryForm taskQueryBo) {
        List tasks = new ArrayList();

        List<HistoricTaskInstance> list=historyService
                .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .processInstanceId(taskQueryBo.getProcInstId())
                .finished()
                .orderByTaskCreateTime().desc()
                .list();


        tasks.addAll(list);
        String processDefId = "";
        if(list.size()> 0){
            processDefId = list.get(0).getProcessDefinitionId();
        }
        //获取流程名称
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefId).singleResult();

        List taskBoList = new ArrayList();
        int index = 0;
        TaskInfoDto taskBo;
        // TaskQuery转换成Task
        for (Iterator iterator = tasks.iterator(); iterator.hasNext(); taskBoList
                .add(taskBo)) {
            index++;
            HistoricTaskInstance task = (HistoricTaskInstance) iterator.next();
            taskBo = getTaskDetail(task.getId());
            if(index == tasks.size()){
                taskBo.setOperatType("发送");
            }else{
                taskBo.setOperatType("提交");
            }

            if(WorkFlowConst.TASK_REASON_BACK.equals(task.getDeleteReason())){
                taskBo.setOperatType("回退");
            }
            if(WorkFlowConst.TASK_REASON_TERMINATION.equals(task.getDeleteReason())){
                taskBo.setOperatType("终止");
            }
            taskBo.setDesc(taskBo.getApproveUserName()+taskBo.getOperatType()+processDefinition.getName());

        }
        return taskBoList;
    }

    public List getProcessFormData(TaskQueryForm taskQueryBo) {
        List resList = new ArrayList();
        List<HistoricTaskInstance> list=historyService
                .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .taskAssignee(taskQueryBo.getAssignee())
                .orderByTaskCreateTime().desc()
                .list();

        list.forEach(item ->{
            String processDefinitionId = item.getProcessDefinitionId();
            StartFormData startFormData = formService.getStartFormData(processDefinitionId);
            resList.add(startFormData);
        });
        return resList;
    }


    public List formQuery(TaskQueryForm taskQueryBo) {
        List resList = new ArrayList();

        List<HistoricVariableInstance> list = historyService.createNativeHistoricVariableInstanceQuery()
                .sql("select RES.* from ACT_HI_VARINST RES WHERE RES.NAME_ = 'title' and RES.VAR_TYPE_ = 'string' and RES.TEXT_ LIKE '%04%' order by RES.ID_ asc")
                .listPage(1, 20);

        list.forEach(item->{
            List<HistoricDetail> listDetail = historyService.createHistoricDetailQuery().
                    processInstanceId(item.getProcessInstanceId()).formProperties().list();
            Map<String, Object> resMap = new HashMap<>();
            listDetail.forEach(va ->{
                HistoricFormProperty formProp = (HistoricFormProperty)va;
                resMap.put(formProp.getPropertyId(), formProp.getPropertyValue());
            });

            resList.add(resMap);
        });

        return resList;
    }
     /**
      *
      * @description 任务回退
      * @param
      * @return
      * @author wangting
      * @date 2018/4/25 0025 16:10
      *
      */
    @Transactional
    public void taskRollBack(ProcessTaskDto processTaskDto, Map<String, Object> map, String startFlag) throws Exception {
        if (StringUtils.isEmpty(processTaskDto.getTaskId())) {
            throw new CorpException(ResultVo.failure("任务id不能为空"));
        }
        if (StringUtils.isEmpty(processTaskDto.getInstanceId())) {
            throw new CorpException(ResultVo.failure("流程实例id不能为空"));
        }
        ActivityImpl currentNode =  activitiToolsService.findActivitiImpl(processTaskDto.getTaskId(), null);
        String processDefinitionKey = currentNode.getProcessDefinition().getKey();
        String defId = currentNode.getProcessDefinition().getId();
        String activitiId = currentNode.getId();
        String instanceId = processTaskDto.getInstanceId();

        if(StringUtils.isNotEmpty(startFlag) && "1".equals(startFlag)){
            //获取第一个节点
            List<PvmActivity> nextActNodes =  activitiToolsService.getNextActNodes(defId, "", null);
            if(nextActNodes != null && nextActNodes.size() >0){
                String toActivitiId =nextActNodes.get(0).getId();
                //判断当前任务是否会签任务 若是会签任务 则节点只有一个income ，不能使用并行任务的回退（并行任务有两个income） 故只需回退一次 不使用循环
                //退一次后act_ru_task 表已没有当前流程实例的任务了会报错
                ActivityImpl activitiImpl =  activitiToolsService.findActivitiImpl(processTaskDto.getTaskId(),"");
                Object sign = activitiImpl.getProperties().get("multiInstance");
                //会签
                if(sign != null){
                     activitiToolsService.turnTransition(processTaskDto.getTaskId(), toActivitiId, null, processTaskDto.getRemark());
                }else{
                    List<Task> taskList =  activitiToolsService.getTaskByProcessInst(instanceId);
                    //防止每一个任务都给发起人发一条任务消息 只需将第一条回退，其他任务完成即可
                    for (int i = 0; i < taskList.size(); i++) {
                        String taskId = taskList.get(i).getId();
                        if(taskId.equals(processTaskDto.getTaskId())){
                             activitiToolsService.turnTransition(processTaskDto.getTaskId(), toActivitiId, null, processTaskDto.getRemark());
                        }else{
                             activitiToolsService.deleteTask(taskId);
                        }
                    }
                }
            }
        }
        else{
            //获取上一步的activitiId
            String toActivitiIds =  activitiToolsService.getProActivityId(processDefinitionKey, defId,activitiId, instanceId, null);
            if(StringUtils.isNotEmpty(toActivitiIds)){
                String[] actIdArr = toActivitiIds.split(",");
                List<String> idList = Arrays.asList(actIdArr);
                //有可能当前任务是个并行任务，根据当前taskid 获取和改任务同级的任务 将该任务回退并将同级任务完成
                List<Task> taskList =  activitiToolsService.findTaskListByTaskId(processTaskDto.getTaskId());
                for (int i = 0; i < taskList.size(); i++) {
                    String taskId = taskList.get(i).getId();
                    if(taskId.equals(processTaskDto.getTaskId())){
                         activitiToolsService.turnTransition(processTaskDto.getTaskId(), idList, null, processTaskDto.getRemark());
                    }else{
                         activitiToolsService.deleteTask(taskId);
                    }
                }
            }
        }
        //驳回后修改历史任务状态
        activitiToolsService.executeCommand(new UpdateHiTaskReasonCommand(processTaskDto.getTaskId(),WorkFlowConst.TASK_REASON_BACK));
    }


 /**
  *
  * @description 创建模型
  * @param
  * @return
  * @author wangting
  * @date 2018/5/9 0009 17:49
  *
  */
    public String createModeler(String tenantId,WorkflowForm workflowForm) throws Exception {
        // editorInfo
        ObjectNode editorNode = objectMapper.createObjectNode();
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);

        // 构建模型
        Model model = repositoryService.newModel();

        // metaInfo 元信息
        ObjectNode metaNode = objectMapper.createObjectNode();
        metaNode.put(ModelDataJsonConstants.MODEL_NAME, workflowForm.getTitle());
        metaNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
        metaNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, workflowForm.getDescription());
        model.setName(workflowForm.getTitle());
        model.setMetaInfo(metaNode.toString());

        model.setTenantId(tenantId);
        // 保存模型
        repositoryService.saveModel(model);
        repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));

        return model.getId();
    }

    public void buildBpmnModel(ActProcess actProcess)throws Exception{
        Integer formId = actProcess.getFormId();

        WorkflowForm workflowForm = wfFormService.get(formId);
        String corpId = actProcess.getCorpId();
        //activiti模型暂时不用
//        //todo modelId = workflowForm.getActModerId()
//        String modelId = "";
//
//        //	如果form 没有流程创建模型
//
//        String corpId = actProcess.getCorpId();
//        try {
//            if(StringUtils.isEmpty(modelId)){
//                modelId = this.createModeler(actProcess.getCorpId(),workflowForm);
////                wfFormService.update();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Model model = repositoryService.getModel(modelId);
        actProcess.setId(WorkFlowConst.TY_PROCESS_KEY+"_"+formId);
        actProcess.setName(workflowForm.getTitle());
        BpmnModel bmodel = new BpmnModel();
        try {
            bmodel = this.convertToBpmnModel(actProcess,workflowForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Generate graphical information
        new BpmnAutoLayout(bmodel).execute();


        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bmodel);
        System.out.println(new String(bpmnBytes));
        // 3. Deploy the process to the engine
//		Deployment deployment = repositoryService.createDeployment().addBpmnModel(PROCESSID+".bpmn", model).name(PROCESSID+"_deployment").deploy();
        String actProcessId = actProcess.getId();


        Deployment deployment = repositoryService.createDeployment().name(actProcessId)
                .tenantId(corpId)
                .addBpmnModel(actProcessId+".bpmn20.xml", bmodel)
                .addString("flowjson",workflowForm.getFlowJson())
//                .addInputStream("start.form", ReflectUtil.getResourceAsStream("forms/start.form"))
                .deploy();

        // 更新模型部署id
//        model.setDeploymentId(deployment.getId());
//        repositoryService.saveModel(model);

    }

     /**
      *
      * @description 启动流程----暂时无用
      * @param `
      * @return
      * @author wangting
      * @date 2018/5/15 0015 10:01
      *
      */
    public void startMyProcess(){
        final String PROCESSID ="myProcess";
        List<ProcessDefinition> list= repositoryService//与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()//创建一个流程定义查询
                        /*指定查询条件,where条件*/
                //.deploymentId(deploymentId)//使用部署对象ID查询
                //.processDefinitionId(processDefinitionId)//使用流程定义ID查询
                .processDefinitionKey(PROCESSID)//使用流程定义的KEY查询
                //.processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询
                        /*排序*/
                .orderByProcessDefinitionVersion().desc()//按照版本的升序排列
                //.orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列
                .list();//返回一个集合列表，封装流程定义
//        .singleResult();//返回唯一结果集
        //.count();//返回结果集数量
        //.listPage(firstResult, maxResults)//分页查询


        ProcessDefinition prodef = list.get(0);


        // 4. Start a process instance
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESSID);
        List<String> assigneeList=new ArrayList<String>(); //分配任务的人员
        assigneeList.add("9390b6e3683c425ca389efe7aa6af4b1");
        assigneeList.add("98df0e92788c416a9fb983e89a8d01ce");
//            assigneeList.add("mary");
        Map<String, Object> vars = new HashMap<String, Object>(); //参数
        vars.put("assigneeList", assigneeList);
        vars.put("starter", "026a564bbfd84861ac4b65393644beef");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(prodef.getId(), vars);

        // 5. Check if task is available
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();


        // 6. Save process diagram to a file
        InputStream processDiagram = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        try {
            FileUtils.copyInputStreamToFile(processDiagram, new File("e://deployments/"+PROCESSID+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 7. Save resulting BPMN xml to a file
        InputStream processBpmn = repositoryService.getResourceAsStream(prodef.getDeploymentId(), PROCESSID+".bpmn20.xml");
        try {
            FileUtils.copyInputStreamToFile(processBpmn,new File("e://deployments/"+PROCESSID+".bpmn20.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(".........end...");
    }


    public BpmnModel convertToBpmnModel(ActProcess actProcess,WorkflowForm workflowForm) throws Exception{
        if(actProcess ==null){
            throw new CorpException(ResultVo.failure("流程对象不能为空"));
        }

        String flowJson = workflowForm.getFlowJson();
        if (StringUtils.isEmpty(flowJson)) {
            throw new CorpException(ResultVo.failure("流程json不能为空"));
        }

        JSONArray jsonArray = JSON.parseArray(flowJson);

        BpmnModel bmodel = new BpmnModel();
        Process process=new Process();
        bmodel.addProcess(process);
        String processId = actProcess.getId();
        String processName =actProcess.getName();
        String corpId = actProcess.getCorpId();
        if(StringUtils.isEmpty(processId)){
            throw new CorpException(ResultVo.failure("流程对象id不能为空"));
        }
        if(StringUtils.isEmpty(processName)){
            throw new CorpException(ResultVo.failure("流程对象name不能为空"));
        }
        process.setId(processId);
        process.setName(processName);
        process.setExecutable(true);
        StartEvent startEvent = flowCreateService.createStartEvent("");
        process.addFlowElement(startEvent);

        //开始
        List<ActFlowElement> actFlowElementList = actProcess.getActFlowElementList();

        Map<String, JSONObject> nodeMap = new HashMap<>();
        String startNodeId = "";
        for(int i=0;i<jsonArray.size();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            String id = "sid_"+obj.getString("id");
            String nodeType = obj.getString("type");

            if(StringUtils.isEmpty(id)){
                throw new CorpException(ResultVo.failure("流程节点id不能为空"));
            }
            if(StringUtils.isEmpty(nodeType)){
                throw new CorpException(ResultVo.failure("流程节点类型不能为空"));
            }
            if("start".equals(nodeType)){
                startNodeId = id;
            }
            nodeMap.put(id, obj);
        }

        stringLocal.set(corpId);
        createProcessNode(nodeMap, startNodeId, startEvent.getId(), process);
        addListener(process);
        return bmodel;
    }

    private void addListener(Process process){
        List<ActivitiListener> list = new ArrayList<>();
        ActivitiListener listener = new ActivitiListener();
        listener.setEvent("end");
        //Spring配置以变量形式调用无法写入，只能通过继承TaskListener方法，
        listener.setImplementationType("class");
        listener.setImplementation("com.lyarc.tp.corp.workflowact.actListener.CompleteInstListener");
        list.add(listener);
        process.setExecutionListeners(list);
    }

    /**
     *
     * @description 创建流程对象
     * @param
     * @return
     * @author wangting
     * @date 2018/5/4 0004 11:35
     *
     */
    private void createProcessNode(Map<String, JSONObject> nodeMap,String startNodeId,String pid,Process process) throws Exception{
        String formKey = "";
        JSONObject obj = nodeMap.get(startNodeId);
        if(obj == null){
            throw new CorpException(ResultVo.failure("无节点id为"+startNodeId));
        }
        String nodeType = obj.getString("type");
        String executor = obj.getString("executor");
        String next = "sid_"+obj.getString("next");
        String nodeId = "sid_"+obj.getString("id");
        //将本节点添加到流程
        if("start".equals(nodeType)){
            //创建发起人节点
            UserTask task = flowCreateService.createUserTask(nodeId,
                    executor, "${starter}", formKey);
            task.setNotExclusive(true);
            this.addToProcess(process,task,nodeMap);

            //创建起点到发起人连线
            SequenceFlow sequenceFlowIn = flowCreateService.createSequenceFlow("",pid, task.getId(), "", "");
            this.addToProcess(process,sequenceFlowIn,nodeMap);

            //创建发起人到下一节点连线
            SequenceFlow sequenceFlowOut = flowCreateService.createSequenceFlow("", task.getId(),next, "", "");
            this.addToProcess(process,sequenceFlowOut,nodeMap);


            createProcessNode(nodeMap, next, nodeId, process);
        }else if("split".equals(nodeType)){
            InclusiveGateway inclusiveGateway = flowCreateService.createInclusiveGateway(nodeId,"");
            this.addToProcess(process,inclusiveGateway,nodeMap);

            JSONArray items = obj.getJSONArray("items");
            for(int i = 0;i < items.size();i++){
                String cid = "sid_"+items.getString(i);
                //split next 为item 项
                //创建网关输出连线
                SequenceFlow sequenceFlow = flowCreateService.createSequenceFlow("", inclusiveGateway.getId(),cid, "", "");
                this.addToProcess(process,sequenceFlow,nodeMap);
                createProcessNode(nodeMap, cid, nodeId, process);
            }
        }else if("person".equals(nodeType)){
            String executorId = obj.getString("executorId");
            executorId = getExecutor(executorId);
            UserTask task = flowCreateService.createUserTask(nodeId,
                    executor, executorId, formKey);
            task.setNotExclusive(true);
            this.addToProcess(process,task,nodeMap);

            //创建连线
            SequenceFlow sequenceFlow = flowCreateService.createSequenceFlow("",task.getId(),next,  "", "");
            this.addToProcess(process,sequenceFlow,nodeMap);
            createProcessNode(nodeMap, next, nodeId, process);
        }else if("role".equals(nodeType) || "department".equals(nodeType)){ 
        	
        	//1.单人 2.多人 3.并发  4.竞争
        	Integer execPattern = obj.getInteger("execPattern");
        	
            UserTask task = flowCreateService.createMultiInstUserTask(nodeId,executor,null, formKey);
            MultiInstanceLoopCharacteristics multiInst = task.getLoopCharacteristics();
            multiInst.setInputDataItem("assigneeList");
            multiInst.setElementVariable("assignee");
            task.setAssignee("${assignee}");
            
            if(execPattern == 4) {
            	multiInst.setCompletionCondition("${nrOfCompletedInstances>0}");
            }

            task.setNotExclusive(true);
            this.addToProcess(process,task,nodeMap);

            //创建连线
            SequenceFlow sequenceFlow = flowCreateService.createSequenceFlow("",task.getId(),next,  "", "");
            this.addToProcess(process,sequenceFlow,nodeMap);
            createProcessNode(nodeMap, next, nodeId, process); 
        }else if("join".equals(nodeType)){
            InclusiveGateway inclusiveGateway = flowCreateService.createInclusiveGateway(nodeId,"");
            this.addToProcess(process,inclusiveGateway,nodeMap);
            //创建连线
            SequenceFlow sequenceFlow = flowCreateService.createSequenceFlow("",inclusiveGateway.getId(),next,  "", "");
            this.addToProcess(process,sequenceFlow,nodeMap);
            createProcessNode(nodeMap, next, nodeId, process);
        }else if("end".equals(nodeType)){
            EndEvent endEvent = flowCreateService.createEndEvent(nodeId);
            this.addToProcess(process,endEvent,nodeMap);
        }
    }

    private String getExecutor(String executor){
        String corpId = stringLocal.get();
        return corpId + WorkFlowConst.ID_SPLIT + executor;
    }

     /**
      *
      * @description 递归创建 不通流向会创建重复
      * @param
      * @return
      * @author wangting
      * @date 2018/5/4 0004 15:52
      *
      */
    private void addToProcess(Process process,FlowElement item,Map<String, JSONObject> nodeMap){
        if(item instanceof SequenceFlow){
            SequenceFlow sf = (SequenceFlow)item;
            FlowElement sequenceFlow = this.findSequenceFlow(process, sf);
            //若连线也不存在 则添加
            if(sequenceFlow == null){
                String targetRef = ((SequenceFlow) item).getTargetRef();
//                String idKey = targetRef.replace("sid_", "");
                JSONObject obj = nodeMap.get(targetRef);
                if(obj!=null){
                    String nodeType = obj.getString("type");
                    if("role".equals(nodeType) || "department".equals(nodeType)){
                        //todo 判断类型是否是每个成员审核 ,暂时无参数先不判断
                        boolean everyAudit = true;
                        if(everyAudit){
                            String roleIds = obj.getString("executorId");
                            String[] split = roleIds.split(",");
                            List<FieldExtension> feList = new ArrayList<>();
                            StringBuffer sb = new StringBuffer("");
                            String corpId = stringLocal.get();
                            for (String roleOrDeptId : split) {
                                sb.append(corpId).append(WorkFlowConst.ID_SPLIT).append(nodeType+"_").append(roleOrDeptId).append(",");
                            }

                            FieldExtension fe = new FieldExtension();
                            fe.setFieldName("roleOrDeptIds");
                            if(sb.toString().endsWith(",")){
                                fe.setStringValue(sb.toString().substring(0,sb.length()-1));
                                feList.add(fe);
                            }

                            List<ActivitiListener> list = new ArrayList<>();

                            ActivitiListener listener = new ActivitiListener();
                            listener.setEvent("take");
                            //Spring配置以变量形式调用无法写入，只能通过继承TaskListener方法，
                            listener.setImplementationType("class");
                            listener.setImplementation("com.lyarc.tp.corp.workflowact.actListener.MultInstTakeListener");
                            listener.setFieldExtensions(feList);
                            list.add(listener);

                            item.setExecutionListeners(list);
                        }

                    }
                }
                process.addFlowElement(item);
            }
        }else{
            FlowElement flowElement = process.getFlowElement(item.getId());
            if(flowElement == null){
                process.addFlowElement(item);
            }
        }
    }
     /**
      *
      * @description 查询是否有重复的连线 source target 相同
      * @param
      * @return
      * @author wangting
      * @date 2018/5/4 0004 16:12
      *
      */
    private FlowElement findSequenceFlow(Process process,SequenceFlow sf) {
        Iterator eleList = process.getFlowElements().iterator();
        FlowElement f = null;
        while(eleList.hasNext()){
            FlowElement item = (FlowElement)eleList.next();
            if((item instanceof SequenceFlow)
                    && ((SequenceFlow)item).getSourceRef().equals(sf.getSourceRef())
                    && ((SequenceFlow)item).getTargetRef().equals(sf.getTargetRef())){
                f = item;
                break;
            }
        }
        return f;
    }

    public String getAssignee(String corpId,String empId) {
        if (StringUtils.isEmpty(corpId)) {
            corpId = "wxdca02fc5f641dabb";
        }
        return corpId + WorkFlowConst.ID_SPLIT + empId;
    }
     /**
      *
      * @description 后加签 还未完成
      * @param
      * @return
      * @author wangting
      * @date 2018/5/31 0031 10:46
      *
      */
    public void insertAfterTask(ProcessTaskDto processTaskDto) throws Exception{
        String taskId = processTaskDto.getTaskId();
        //根据taskid 获取activitiId
        ActivityImpl activitiImpl = activitiToolsService.findActivitiImpl(taskId,null);
        String instanceId = processTaskDto.getInstanceId();
        TaskFlowControlService tfcs = taskFlowControlServiceFactory.create(instanceId);
        ActivityImpl[] activities = tfcs.insertTasksAfter(taskId,activitiImpl.getId(), "wxdca02fc5f641dabb#-#zhangpengfei", "wxdca02fc5f641dabb#-#huanghaoqi");

        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition("tyProcess_8:1:65005");
        List<ActivityImpl> activitiList = def.getActivities();
        System.out.println("111");
    }


    public ProcessInfoDto getProcessInfo(String processInstanceId){
        //根据实例id 获取流程
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        ProcessInfoDto resDto = new ProcessInfoDto();

        String defId = processInstance.getProcessDefinitionId();
        if(StringUtils.isNotEmpty(defId)){
            ProcessDefinition processDefine = getProcessDefineByDefId(defId);
            resDto.setProcessName(processDefine.getName());
        }

        resDto.setProcessStartTime(DateUtil.getDateStr(processInstance.getStartTime(),""));
        resDto.setStarterId(processInstance.getStartUserId());
        resDto.setStarter(getUserName(processInstance.getStartUserId()));
        resDto.setInstanceId(processInstanceId);
        String defKey = processInstance.getProcessDefinitionKey();
        if(StringUtils.isNotEmpty(defKey)){
            resDto.setDefKey(defKey);
            String[] split = defKey.split("_");
            if(split.length==2){
                resDto.setFormId(Integer.parseInt(split[1]));
            }
        }


        List<ProcessVariableDto> varList = new ArrayList<>();
        List<HistoricVariableInstance> list = historyService
                .createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询
                .processInstanceId(processInstanceId)
                .list();

        if(list != null && list.size()>0){
            for(HistoricVariableInstance hiv : list){
                ProcessVariableDto pv = new ProcessVariableDto();
                pv.setId(hiv.getId());
                pv.setVariableName(hiv.getVariableName());
                pv.setVariableTypeName(hiv.getVariableTypeName());
                pv.setValue(hiv.getValue());
                varList.add(pv);
            }
        }
        resDto.setVarList(varList);

        //获取完成的任务
        List<HistoricTaskInstance> taskInstList = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        List<Map<String, String>> taskList = new ArrayList<>();
        for (HistoricTaskInstance taskInstance : taskInstList) {
            Map<String, String> map = new HashMap<>();
            map.put("taskId", taskInstance.getId());
            String flowId = taskInstance.getTaskDefinitionKey().split("_")[1];
            map.put("flowId", flowId);
            map.put("status", taskInstance.getDeleteReason());
            taskList.add(map);
        }
        resDto.setTasks(taskList);
        return resDto;
    }
     /**
      *
      * @description 终止流程
      * @param
      * @return
      * @author wangting
      * @date 2018/5/28 0028 16:18
      *
      */
     @Transactional
    public void endProcess(ProcessTaskDto taskInfoDto) throws Exception{
        String taskId = taskInfoDto.getTaskId();
        if(StringUtils.isEmpty(taskId)){
            throw new CorpException(ResultVo.failure("当前任务id为空"));
        }
        String instId = taskInfoDto.getInstanceId();
        if(StringUtils.isEmpty(instId)){
            throw new CorpException(ResultVo.failure("当前实例id为空"));
        }
        String comment = taskInfoDto.getRemark();
        activitiToolsService.endProcess(taskId,instId,comment);


        //根据实例 获取流程
//        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instId).singleResult();
//        //使用流程实例对象获取BusinessKey
//        String businessKey = pi.getBusinessKey();
//        if(StringUtils.isNotEmpty(businessKey)){
//            //modelName_beanName_methodName_paramId
//            String[] split = businessKey.split("_");
//            if(split.length == 3 && SpringContextUtils.containsBean(split[0])){
//                String beanName = split[0];
//                String methodName = "rejectCallBack";
//                String id = split[1];
//                String opType = split[2];
//                Method mh = ReflectionUtils.findMethod(SpringContextUtils.getBean(beanName).getClass(), methodName,new Class[]{String.class} );
//                Object obj = ReflectionUtils.invokeMethod(mh,  SpringContextUtils.getBean(beanName),id,opType);
//            }
//        }

    }

     /**
      *
      * @description 流程撤回
      * @param
      * @return
      * @author wangting
      * @date 2018/5/28 0028 16:18
      *
      */
     @Transactional
    public void recall(ProcessTaskDto taskInfoDto) throws Exception{
        String taskId = taskInfoDto.getTaskId();
        if(StringUtils.isEmpty(taskId)){
            throw new CorpException(ResultVo.failure("当前任务id为空"));
        }
        String instId = taskInfoDto.getInstanceId();
        if(StringUtils.isEmpty(instId)){
            throw new CorpException(ResultVo.failure("当前实例id为空"));
        }
        //获取历史任务列表 判断撤回的任务处理人是否为当前用户
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if(!historicTaskInstance.getAssignee().equals(taskInfoDto.getStartUserId())){
            throw new CorpException(ResultVo.failure("无权撤回该任务"));
        }
         //根据实例获取是否有正在处理的任务
        List<Task> list = taskService.createTaskQuery().processInstanceId(instId).list();
        if(list == null || list.size() == 0){
            throw new CorpException(ResultVo.failure("该流程未开始或已结束"));
        }
        //根据taskid 获取下级activiti
        ActivityImpl currentNode =  activitiToolsService.findActivitiImpl(taskId, null);
        String defId = currentNode.getProcessDefinition().getId();
        String activitiId = currentNode.getId();

        List<PvmActivity> nextActNodes =  activitiToolsService.getNextActNodes(defId, activitiId, null);
        if(nextActNodes != null && nextActNodes.size() >0){
            if(list.size() != nextActNodes.size()){
                throw new CorpException(ResultVo.failure("当前任务无法撤回，检查是否已提交"));
            }
            String keysStr = list.stream().map(Task::getTaskDefinitionKey).sorted().collect(Collectors.joining(","));
            String idsStr =nextActNodes.stream().map(PvmActivity::getId).sorted().collect(Collectors.joining(","));
            //下级节点已审批（当前流程待处理任务和流程定义下级节点不一致）
            if(!keysStr.equals(idsStr)){
                throw new CorpException(ResultVo.failure("流程下级节点已被审批，无法撤回"));
            }
            //撤回
            for (int i = 0; i < list.size(); i++) {
                String tId = list.get(i).getId();
                if(i == 0){
                    activitiToolsService.turnTransition(tId, activitiId, null, null);
                }else{
                    activitiToolsService.deleteTask(tId);
                }
                historyService.deleteHistoricTaskInstance(tId);
            }
            //删除已处理的任务
            historyService.deleteHistoricTaskInstance(taskId);
        }
    }

     /**
      *
      * @description 撤销流程 逻辑：将当前任务和历史任务都清空，把当前表单插入草稿箱
      * @param
      * @return
      * @author wangting
      * @date 2018/5/31 0031 9:21
      *
      */
     @Transactional
    public void revoke(ProcessTaskDto taskInfoDto) throws Exception{
        String taskId = taskInfoDto.getTaskId();
        if(StringUtils.isEmpty(taskId)){
            throw new CorpException(ResultVo.failure("当前任务id为空"));
        }
        String instId = taskInfoDto.getInstanceId();
        if(StringUtils.isEmpty(instId)){
            throw new CorpException(ResultVo.failure("当前实例id为空"));
        }
        //获取当前流程实例 判断是否为当前用户发起
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instId).singleResult();
        if(!historicProcessInstance.getStartUserId().equals(taskInfoDto.getStartUserId())){
         throw new CorpException(ResultVo.failure("无权操作该流程"));
        }
         //根据实例获取是否有正在处理的任务
        List<Task> list = taskService.createTaskQuery().processInstanceId(instId).list();
        if(list == null || list.size() == 0){
            throw new CorpException(ResultVo.failure("该流程未开始或已结束"));
        }

        //完成正在处理的任务
        for (int i = 0; i < list.size(); i++) {
            String tId = list.get(i).getId();
            activitiToolsService.deleteTask(tId);
        }
        //将流程置为完成
        activitiToolsService.executeCommand(new UpdateHiInstanceReasonCommand(instId, WorkFlowConst.TASK_REASON_REVOKE));

         //获取流程表单数据插入到草稿表
//        saveDraft(instId);

        //将历史流程删除
//        historyService.deleteHistoricProcessInstance(instId);
    }

    private void saveDraft(String instId){

        List<HistoricDetail> detailList = historyService.createHistoricDetailQuery().processInstanceId(instId).formProperties().list();
        Map<String, String> formProperties = new HashMap<String, String>();
        for (HistoricDetail historicDetail : detailList) {
         HistoricFormProperty formProperty = (HistoricFormProperty) historicDetail;
         formProperties.put(formProperty.getPropertyId(), formProperty.getPropertyValue());
        }
        LoginUser user = UserHolder.getUser();
        String corpId = user.getCorpId();
        String empId = user.getEmpId();
        WorkflowDraft wfd = new WorkflowDraft();
        wfd.setCorpId(corpId);
        wfd.setEmpId(empId);

        String fields = JSON.toJSONString(formProperties);
        wfd.setFieldsJson(fields);
        //获取流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instId).singleResult();
        //processDefinitionKey 格式tyProcess_11:1:122505 下划线后面的11 为formId
        String processDefinitionKey = historicProcessInstance.getProcessDefinitionKey();
        String[] split = processDefinitionKey.split(":");
        if(split.length>0){
            String formId = split[0].split("_")[1];
            if(StringUtils.isNotEmpty(formId)){
                wfd.setFormId(Integer.parseInt(formId));
            }
        }
        // 插入草稿
        draftService.insert(wfd);
    }

     /**
      *
      * @description 根据流程属性配置动态获取流程名称
      * @param
      * @return
      * @author wangting
      * @date 2018/8/27 0027 14:40
      *
      */
    public String getProcessNameByParam(String flowTitle,List<String> titleParam,String instanceId) {
        for (String param : titleParam) {
            if (param.startsWith("field_")) {
                //根据param 获取value
                HistoricVariableInstance varInst = historyService.createHistoricVariableInstanceQuery().processInstanceId(instanceId).variableName(param).singleResult();
                if(varInst == null){
                    logger.error("获取流程标题出错，请检查工作流流程定义-属性设置-流程属性-流程标题，实例id"+instanceId);
                }
                String value = varInst.getValue() == null ? "" : varInst.getValue().toString();
                flowTitle = replaceStr(flowTitle, param, value);
            } else if ("当前登录人".equals(param)) {
                EmployeeBean empInfo = UserHolder.getUser().getEmpInfo();
                String empName = empInfo.getTrueName();
                flowTitle = replaceStr(flowTitle, param, empName);

            } else if ("当前人员所在部门".equals(param)) {
                String deptName = UserHolder.getUser().getDeptName();
                flowTitle = replaceStr(flowTitle, param, deptName);

            } else if ("当前登录人员所属兼职部门".equals(param)) {
                String deptName = UserHolder.getUser().getDeptName();
                flowTitle = replaceStr(flowTitle, param, deptName);

            } else if ("当前登陆人员的手机号".equals(param)) {
                EmployeeBean empInfo = UserHolder.getUser().getEmpInfo();
                String mobile = empInfo.getMobile();
                flowTitle = replaceStr(flowTitle, param, mobile);

            } else if ("当前人员所在单位".equals(param)) {
                String corpName = UserHolder.getUser().getCorpName();
                flowTitle = replaceStr(flowTitle, param, corpName);

            } else if ("系统日期时间".equals(param)) {
                String dateStr = DateUtil.getYYYYMMDDHHMMSSStr(new Date());
                flowTitle = replaceStr(flowTitle, param, dateStr);

            } else if ("系统日期".equals(param)) {
                String timeStr = DateUtil.getDateStr(new Date(), "yyyy-MM-dd");
                flowTitle = replaceStr(flowTitle, param, timeStr);

            } else if ("本月初日期".equals(param)) {
                String monthStart = DateUtil.getDateStr(DateUtil.getMonthStartTime(new Date()), "yyyy-MM-dd");
                flowTitle = replaceStr(flowTitle, param, monthStart);

            } else if ("本月末日期".equals(param)) {
                String monthEnd = DateUtil.getDateStr(DateUtil.getMonthEndTime(new Date()), "yyyy-MM-dd");
                flowTitle = replaceStr(flowTitle, param, monthEnd);

            } else if ("本年初日期".equals(param)) {
                String yearStart = DateUtil.getDateStr(DateUtil.getCurrentYearStartTime(), "yyyy-MM-dd");
                flowTitle = replaceStr(flowTitle, param, yearStart);

            } else if ("本年末日期".equals(param)) {
                String yearEnd = DateUtil.getDateStr(DateUtil.getCurrentYearEndTime(), "yyyy-MM-dd");
                flowTitle = replaceStr(flowTitle, param, yearEnd);
            }
        }
        return flowTitle;
    }

    private String replaceStr(String flowTitle, String replaceStr,String newStr){
        String perfix="{{";
        String suffix="}}";
        flowTitle = flowTitle.replace(perfix+replaceStr+suffix, newStr);
        return flowTitle;
    }


//    public List<Comment> getProcessComments(String instId) {
////       2）通过流程实例查询所有的(用户任务类型)历史活动
//        List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery().processInstanceId(instId).activityType("userTask")
//                .orderby.list();
////       3）查询每个历史任务的批注
//        for (HistoricActivityInstance hai : hais) {
//            String historytaskId = hai.getTaskId();
//            List<Comment> comments = taskService.getTaskComments(historytaskId);
//            // 4）如果当前任务有批注信息，添加到集合中
//            if(comments!=null && comments.size()>0){
//                historyCommnets.addAll(comments);
//            }
//        }
////       5）返回
//        return historyCommnets;
//    }




    /**
     *
     * @description 微信端 我的待发送
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public List<ProcessInfoDto> getMyWaitSendProcess(String lastId, int pageSize, TaskQueryForm taskQueryBo){
        WorkflowDraftParam param = new WorkflowDraftParam();
        param.setCorpId(taskQueryBo.getCorpId());
        param.setEmpId(taskQueryBo.getAssignee());
//        param.setOffset(start);
        param.setLastId(lastId);
        param.setPageSize(pageSize);
        List<WorkflowDraft> list = draftService.appList(param);
        List<ProcessInfoDto> resList = new ArrayList<>();
        for (WorkflowDraft draft : list) {
            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setSource("普通发起");
            dto.setDraftId(draft.getId());
            dto.setProcessName(draft.getTitle());
            dto.setProcessCreateTime(DateUtil.getDateStr(draft.getCreateTime(),""));
            resList.add(dto);
        }

        return resList;
    }



    /**
     *
     * @description 微信端 我的已发送
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public List<ProcessInfoDto> getMySendProcess(String lastId, int pageSize, TaskQueryForm taskQueryBo){

//        int limit = pageSize;


        StringBuffer sb = new StringBuffer("");
        sb.append(" SELECT DISTINCT RES.*, DEF.KEY_ AS PROC_DEF_KEY_, DEF.NAME_ AS PROC_DEF_NAME_," +
                " DEF.VERSION_ AS PROC_DEF_VERSION_, DEF.DEPLOYMENT_ID_ AS DEPLOYMENT_ID_\n" +
                "FROM ACT_HI_PROCINST RES\n" +
                "LEFT OUTER\n" +
                "JOIN ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "WHERE RES.START_USER_ID_ = #{assigneeId} AND RES.TENANT_ID_ = #{tenantId}" );
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and DEF.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }
        if(lastId !=null)
        {
            sb.append(" and (" +
                    "      RES.START_TIME_ < (SELECT pro.START_TIME_ FROM ACT_HI_PROCINST pro WHERE pro.ID_='"+lastId+"')\n" +
                    "      OR (RES.START_TIME_ = (SELECT pro.START_TIME_ FROM ACT_HI_PROCINST pro WHERE pro.ID_='"+lastId+"') AND RES.ID_ < '"+lastId+"')\n" +
                    "      ) ");
        }
        sb.append(" ORDER BY RES.START_TIME_ DESC ");
        if(pageSize >=0){
            sb.append("limit ").append(pageSize);
        }
        List<HistoricProcessInstance> historicProcessInstances = historyService.createNativeHistoricProcessInstanceQuery().sql(
                sb.toString())
                .parameter("assigneeId", taskQueryBo.getAssignee())
                .parameter("tenantId", taskQueryBo.getCorpId()).list();


        //构建员工map
        getEmpMap(taskQueryBo.getCorpId());

        List<HistoricTaskInstance> instFirstList = historyService.createNativeHistoricTaskInstanceQuery()
                .sql("select * from ACT_HI_TASKINST  where ASSIGNEE_='"+taskQueryBo.getAssignee()+"' group by PROC_INST_ID_ ORDER BY ID_ ASC")
                .list();
        //构造map<instid,firstTaskId>
        Map<String, String> map = instFirstList.stream().collect(Collectors.toMap(HistoricTaskInstance::getProcessInstanceId, HistoricTaskInstance::getId));


        List<ProcessInfoDto> resList = new ArrayList<>();
        for (HistoricProcessInstance hisProcess : historicProcessInstances) {
            //获取流程实例下第一个任务

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(map.get(hisProcess.getId()));
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));
            //设置流程状态和处理人
            setDtoStatus(dto, hisProcess,taskQueryBo.getCorpId());
            resList.add(dto);
        }
        return resList;
    }

    /**
     *
     * @description 微信 我的的已处理
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public List<ProcessInfoDto> getMyHandlerProcess(String lastId, int pageSize, TaskQueryForm taskQueryBo){
        int limit = pageSize;

        String assignee = taskQueryBo.getAssignee();
        List<Group> groupList = identityService.createGroupQuery().groupMember(assignee).list();
        List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
        String groupIds = groupIdList.stream().collect(Collectors.joining(","));
        //查询我的已办任务

        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT DISTINCT RES.*\n" +
                "FROM ACT_HI_TASKINST RES\n" +
                "left JOIN ACT_HI_IDENTITYLINK HI_OR0 ON HI_OR0.TASK_ID_ = RES.ID_\n" +
//                "left join ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "left join ACT_HI_PROCINST INST on RES.PROC_INST_ID_ = INST.PROC_INST_ID_ "+
                "WHERE RES.END_TIME_ IS NOT NULL AND RES.TENANT_ID_ = #{tenantId} AND \n" +
                " (RES.ASSIGNEE_ = #{assigneeId} OR (RES.ASSIGNEE_ IS NULL AND HI_OR0.TYPE_ = 'candidate' AND (HI_OR0.USER_ID_ = #{assigneeId} OR \n" +
                "find_in_set(HI_OR0.GROUP_ID_ ,#{groupIds})" +
                "))) and TASK_DEF_KEY_ !='sid_start'" );
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and INST.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        if(lastId !=null)
        {
            sb.append(" and (" +
                    "      RES.START_TIME_ < (SELECT task.START_TIME_ FROM ACT_HI_TASKINST task WHERE task.ID_='"+lastId+"')\n" +
                    "      OR (RES.START_TIME_ = (SELECT task.START_TIME_ FROM ACT_HI_TASKINST task WHERE task.ID_='"+lastId+"') AND RES.ID_ < '"+lastId+"')\n" +
                    "      ) ");
        }
        sb.append(" ORDER BY RES.START_TIME_ DESC ");
        if(pageSize >=0){
            sb.append("limit ").append(pageSize);
        }

        List<HistoricTaskInstance> list = historyService.createNativeHistoricTaskInstanceQuery().sql(
                sb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee())
                .parameter("groupIds", groupIds).list();

        List<ProcessInfoDto> resList = new ArrayList<>();
        if(list.size() == 0){
            return resList;
        }
        //获取任务的流程定义（去重）
        List<HistoricTaskInstance> instFirstList = historyService.createNativeHistoricTaskInstanceQuery().sql("select * from ACT_HI_TASKINST group by PROC_INST_ID_ ORDER BY ID_ ASC")
                .list();
        //构造map<instid,firstTaskId>
        Map<String, String> map = instFirstList.stream().collect(Collectors.toMap(HistoricTaskInstance::getProcessInstanceId, HistoricTaskInstance::getId));
        //构建员工map
        getEmpMap(taskQueryBo.getCorpId());
        //构造 实例idset 用于查询实例列表，避免在循环中查
        Set<String> instIdSet = list.stream().map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, Object> instMap = getInstMap(instIdSet);

        for (HistoricTaskInstance historicTaskInstance : list) {
            HistoricProcessInstance hisProcess = (HistoricProcessInstance)instMap.get(historicTaskInstance.getProcessInstanceId());
            //如果流程的发起人和任务处理人是一个人，并且该任务为流程的第一个任务 则该任务在我发起的列表 不应再我的已处理
            String firstTaskId = map.get(hisProcess.getId());
            if (hisProcess.getStartUserId().equals(historicTaskInstance.getAssignee()) && firstTaskId.equals(historicTaskInstance.getId())){
                continue;
            }

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(historicTaskInstance.getId());
//            dto.setProcessName(hisProcess.getName());
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));

            //处理时间
            dto.setDealTime(DateUtil.getDateStr(historicTaskInstance.getEndTime(),""));
            //设置流程状态和处理人
            setDtoStatus(dto, hisProcess,taskQueryBo.getCorpId());
            resList.add(dto);
        }
        return resList;
    }

    /**
     *
     * @description 微信 我的待处理
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 16:15
     *
     */
    public List<ProcessInfoDto> getMyTodoProcess(String lastId, int pageSize, TaskQueryForm taskQueryBo){

        String assignee = taskQueryBo.getAssignee();
        List<Group> groupList = identityService.createGroupQuery().groupMember(assignee).list();
        List<String> groupIdList = groupList.stream().map(Group::getId).collect(Collectors.toList());
        String groupIds = groupIdList.stream().collect(Collectors.joining(","));

        StringBuffer sb = new StringBuffer("SELECT DISTINCT RES.*\n" +
                "FROM ACT_RU_TASK RES\n" +
                "LEFT JOIN ACT_RU_IDENTITYLINK I ON I.TASK_ID_ = RES.ID_\n" +
                //"left join ACT_RE_PROCDEF DEF ON RES.PROC_DEF_ID_ = DEF.ID_\n" +
                "left join ACT_HI_PROCINST INST on RES.PROC_INST_ID_ = INST.PROC_INST_ID_ "+
                "WHERE RES.TENANT_ID_ = #{tenantId} AND (RES.ASSIGNEE_ = #{assigneeId} OR (RES.ASSIGNEE_ IS NULL AND I.TYPE_ = 'candidate' AND (I.USER_ID_ = #{assigneeId} OR \n" +
                "find_in_set(I.GROUP_ID_ ,#{groupIds})" +
                ")))");
        if(StringUtils.isNotEmpty(taskQueryBo.getWorkflowName())){
            sb.append(" and INST.NAME_ like '%" + taskQueryBo.getWorkflowName() + "%'");
        }

        if(lastId !=null)
        {
            sb.append(" and (" +
                    "      RES.CREATE_TIME_ < (SELECT task.CREATE_TIME_ FROM ACT_RU_TASK task WHERE task.ID_='"+lastId+"')\n" +
                    "      OR (RES.CREATE_TIME_ = (SELECT task.CREATE_TIME_ FROM ACT_RU_TASK task WHERE task.ID_='"+lastId+"') AND RES.ID_ < '"+lastId+"')\n" +
                    "      ) ");
        }
        sb.append(" ORDER BY RES.CREATE_TIME_ DESC ");
        if(pageSize >=0){
            sb.append("limit ").append(pageSize);
        }

        NativeTaskQuery nativeTaskQuery = taskService.createNativeTaskQuery().sql(
                sb.toString()).parameter("tenantId", taskQueryBo.getCorpId())
                .parameter("assigneeId", taskQueryBo.getAssignee())
                .parameter("groupIds", groupIds);

        List<Task> todoList = nativeTaskQuery.list();

        List<ProcessInfoDto> resList = new ArrayList<>();
        if(todoList.size() == 0){
            return resList;
        }
        //构建员工map
        getEmpMap(taskQueryBo.getCorpId());
        //构造 实例idset 用于查询实例列表，避免在循环中查
        Set<String> instIdSet = todoList.stream().map(Task::getProcessInstanceId).collect(Collectors.toSet());
        Map<String, Object> instMap = getInstMap(instIdSet);
        for (Task task : todoList) {
            HistoricProcessInstance hisProcess = (HistoricProcessInstance)instMap.get(task.getProcessInstanceId());

            ProcessInfoDto dto = new ProcessInfoDto();
            dto.setTaskId(task.getId());
//            dto.setProcessName(hisProcess.getName());
            dto.setDefId(hisProcess.getProcessDefinitionId());
            dto.setInstanceId(hisProcess.getId());
            dto.setSource("普通发起");
            dto.setProcessStartTime(DateUtil.getDateStr(hisProcess.getStartTime(),""));
            dto.setStarterId(hisProcess.getStartUserId());
            dto.setStarter(getUserName(hisProcess.getStartUserId()));
            ProcessInstance rpi = runtimeService
                    .createProcessInstanceQuery()//创建流程实例查询对象
                    .processInstanceId(hisProcess.getId())
                    .singleResult();
            if(rpi == null){
                dto.setStatus("已结束");
            }else{
                dto.setStatus("处理中");
            }
            //接收时间
            dto.setReceiveTime(DateUtil.getDateStr(task.getCreateTime(),""));
            //获取流程实例名称
            String processName = StringUtils.isNotEmpty(hisProcess.getName()) ? hisProcess.getName() : hisProcess.getProcessDefinitionName();
            dto.setProcessName(processName);
            resList.add(dto);
        }
        return resList;
    }

}
