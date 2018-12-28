package com.lyarc.tp.corp.workflowact.controller;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.bean.PageResultBean;
import com.lyarc.tp.corp.common.exception.CorpException;
import com.lyarc.tp.corp.common.util.CommonUtil;
import com.lyarc.tp.corp.login.form.LoginUser;
import com.lyarc.tp.corp.workflow.bean.WorkflowForm;
import com.lyarc.tp.corp.workflow.bean.WorkflowOperLog;
import com.lyarc.tp.corp.workflow.form.WorkflowOperLogParam;
import com.lyarc.tp.corp.workflow.service.WorkflowDraftService;
import com.lyarc.tp.corp.workflow.service.WorkflowFormService;
import com.lyarc.tp.corp.workflow.service.WorkflowOperLogService;
import com.lyarc.tp.corp.workflowact.bean.ActFlowElement;
import com.lyarc.tp.corp.workflowact.bean.ActProcess;
import com.lyarc.tp.corp.workflowact.constant.OperLogEnum;
import com.lyarc.tp.corp.workflowact.constant.WorkFlowConst;
import com.lyarc.tp.corp.workflowact.dto.ProcessInfoDto;
import com.lyarc.tp.corp.workflowact.dto.ProcessTaskDto;
import com.lyarc.tp.corp.workflowact.dto.TaskInfoDto;
import com.lyarc.tp.corp.workflowact.form.TaskQueryForm;
import com.lyarc.tp.corp.workflowact.service.ActivitiService;
import com.lyarc.tp.corp.workflowact.service.FlowCreateService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.*;

@Controller
@ResponseBody
@RequestMapping(path = "/api/wf/process")
public class ActivitiController  {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private FlowCreateService flowCreateService;

    @Autowired
    TaskService taskService;
    @Autowired
    IdentityService identityService;
    @Autowired
    FormService formService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    HistoryService historyService;
    @Autowired
    WorkflowDraftService draftService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    WorkflowOperLogService logService;
    @Autowired
    WorkflowFormService workflowFormService;


    private String getAssignee(){
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String empId = loginUser.getEmpId();

        String assignee = corpId + WorkFlowConst.ID_SPLIT + empId;
        return assignee;
    }
     /**
      *
      * @description 开始流程
      * @param parameterMap 参数map,id:草稿id
      * @return
      * @author wangting
      * @date 2018/5/10 0010 16:43
      *
      */
    @RequestMapping(path = "/start", method = RequestMethod.POST
            ,
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResultVo<TaskInfoDto> start(@RequestBody Map parameterMap, HttpServletRequest request){

        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String assignee = getAssignee();
        // 定义map用于往工作流数据库中传值。
//        Map<String, String> formProperties = new HashMap<String, String>();
        identityService.setAuthenticatedUserId(assignee);


        Map<String, String> formProperties = new HashMap<String, String>();
        String processKey = "";
        String businessKey = "";
        Integer draftId = null;
        // 从request中读取参数然后转换
        Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            String key = entry.getKey().toString();
            if(key.startsWith("fp_")){
                key = key.replaceFirst("fp_", "");
                formProperties.put(key, entry.getValue()==null ? "" : entry.getValue().toString());
            }else if("processKey".equals(key)){
                processKey = entry.getValue()==null ? "" : entry.getValue().toString();
            }else if("draftId".equals(key)){
                draftId = Integer.parseInt(entry.getValue()==null ? "0" : entry.getValue().toString());
            }
            else if("businessKey".equals(key)){
                businessKey = StringUtils.isEmpty(key) ? "" : entry.getValue().toString();
            }
        }

        Map<String, Object> vars = new HashMap<String, Object>(); //参数
        vars.put("starter", assignee);
        vars.put("assigneeList", new ArrayList());

        Integer formId = 0;
        try {
            formId = Integer.parseInt(processKey);
        }catch (Exception e){
            throw new CorpException(ResultVo.failure("processKey参数类型错误"));
        }

        //businessKey
        businessKey = processKey + "_" + businessKey;

        processKey = WorkFlowConst.TY_PROCESS_KEY + "_" + processKey;

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKeyAndTenantId(processKey,businessKey, vars,corpId);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, vars);

        String instanceId = processInstance.getId();
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).active().singleResult();
        //将formData 保存
//        formService.saveFormData();
        String taskId = task.getId();
        formService.submitTaskFormData(taskId, formProperties);
        //插入日志
        insertOpLog(instanceId, OperLogEnum.SEND.getType(),null);

        //如果有草稿了将草稿删除
        if(draftId !=null){
            draftService.delete(draftId);
        }
        //流程启动后根据配置修改流程标题
        modifyProcessTitle(instanceId,formId);

        TaskInfoDto taskInfo  = new TaskInfoDto();
        taskInfo.setTaskId(taskId);
        taskInfo.setProcInstId(instanceId);
        return ResultVo.success(taskInfo);
    }

     /**
      *
      * @description 修改流程id
      * @param
      * @return
      * @author wangting
      * @date 2018/8/27 0027 10:56
      *
      */
    private void modifyProcessTitle(String instanceId,Integer formId) {
        WorkflowForm workflowForm = workflowFormService.get(formId);
        if(workflowForm == null || workflowForm.getId() == null){
            return;
        }
        String flowTitle = workflowForm.getFlowTitle();
        List<String> titleParam = CommonUtil.extractMessageByRegular(flowTitle);
        String processName = activitiService.getProcessNameByParam(flowTitle,titleParam,instanceId);
        runtimeService.setProcessInstanceName(instanceId,processName);
    }


    /**
     *
     * @description 提交任务
     * @param
     * @return
     * @author wangting
     * @date 2018/5/10 0010 15:51
     *
     */
    @RequestMapping(path = "/task/complete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo complete(@RequestBody Map parameterMap, HttpServletRequest request){
        LoginUser loginUser = UserHolder.getUser();
        String empId = loginUser.getEmpId();

        String assignee = getAssignee();

        Map<String, String> formProperties = new HashMap<String, String>();
        String taskId = "";
        String comment = "";
        // 从request中读取参数然后转换
        Set entrySet = parameterMap.entrySet();
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            String key = entry.getKey().toString();
            if(key.startsWith("fp_")){
                key = key.replaceFirst("fp_", "");
                formProperties.put(key, entry.getValue()==null ? "" : entry.getValue().toString());
            }else if("taskId".equals(key)){
                taskId = entry.getValue()==null ? "" : entry.getValue().toString();
            }
            else if("comment".equals(key)){
                comment = entry.getValue()==null ? "" : entry.getValue().toString();
            }
        }
        if(StringUtils.isEmpty(taskId)){
            return ResultVo.failure("请求参数不完整");
        }

        Task task = taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(assignee).singleResult();
        if(task == null || task.getId() == null){
            return ResultVo.failure("没有该任务");
        }

        logger.debug("start form parameters: {}", formProperties);
        if(StringUtils.isNotEmpty(comment)){
            taskService.addComment(taskId, null, comment);
        }

        formService.submitTaskFormData(taskId, formProperties);

        identityService.setAuthenticatedUserId(empId);
        //插入日志
        String instanceId = task.getProcessInstanceId();
        insertOpLog(instanceId, OperLogEnum.SUBMIT.getType(),null);
        return ResultVo.success("任务已提交");
    }


     /**
      *
      * @description 获取流程实例的流程变量 用于表单展示
      * @param
      * @return
      * @author wangting
      * @date 2018/5/10 0010 15:56
      *
      */
    @RequestMapping(value = "/getProcessInst")
    @ResponseBody
    public ResultVo<ProcessInfoDto> getProcVariable(@RequestParam("instId") String processInstanceId, HttpServletRequest request) {
        ProcessInfoDto processInfo = activitiService.getProcessInfo(processInstanceId);
        return ResultVo.success(processInfo);
    }

     /**
      *
      * @description 创建activiti流程图，根据 自定义对象 用于自己设计流程图生成activiti 流程图
      * @param
      * @return
      * @author wangting
      * @date 2018/5/10 0010 15:59
      *
      */
    @RequestMapping(value = "/createMyProcess")
    @ResponseBody
    public String createMyProcess(){
        ActProcess actProcess = getActProcess();
        try {
            activitiService.buildBpmnModel(actProcess);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        activitiService.startMyProcess();
        return "ok";
    }
    private ActProcess getActProcess(){
        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
//        String empId = loginUser.getEmpId();

        ActProcess process = new ActProcess();
        process.setCorpId(corpId);
        process.setFormId(11);
        return process;
    }


    private ActFlowElement getActFlowElement(String type){
        ActFlowElement element = new ActFlowElement();
        element.setId(getId());
        element.setNodeType(type);
        return element;
    }

    private  String getId(){
        return flowCreateService.getId("");
    }



     /**
      *
      * @description 我发起的任务
      * @param queryBo status 1:待发，2.已发，3.待处理,4.已处理，
      * @return
      * @author wangting
      * @date 2018/5/11 0011 15:04
      *
      */
     @RequestMapping(path = "/myProcess", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
             produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     public ResultVo myProcess(HttpServletRequest request, @RequestBody TaskQueryForm queryBo){
         Integer pageSize = queryBo.getPageSize();
         Integer pageNo = queryBo.getPage();
         if(pageSize == null){
             pageSize = 10;
         }
         LoginUser loginUser = UserHolder.getUser();
         String corpId = loginUser.getCorpId();
         String empId = loginUser.getEmpId();
         String assignee = corpId + WorkFlowConst.ID_SPLIT + empId;
         List<ProcessInfoDto> resList = new ArrayList();
         queryBo.setAssignee(assignee);
         queryBo.setCorpId(corpId);
//         queryBo.setWorkflowName(processName);
         String status = queryBo.getStatus();
         int start = (pageNo - 1) * pageSize;
         if("1".equals(status)){
             //我的待发送是自建表 里面有corpid 字段 故assignee 为empId
             queryBo.setAssignee(empId);
             PageResultBean myWaitSendProcess = activitiService.getMyWaitSendProcess(start, pageSize, queryBo);
             return ResultVo.success(myWaitSendProcess);
         }else if("2".equals(status)){
             PageResultBean mySendProcess = activitiService.getMySendProcess(start, pageSize, queryBo);
             return ResultVo.success(mySendProcess);
         }
         else if("3".equals(status)){
             PageResultBean myTodoProcess = activitiService.getMyTodoProcess(start, pageSize, queryBo);
             return ResultVo.success(myTodoProcess);
         }
         else if("4".equals(status)){
             PageResultBean myHandlerProcess = activitiService.getMyHandlerProcess(start, pageSize, queryBo);
             return ResultVo.success(myHandlerProcess);
         }


         return ResultVo.success(resList);
     }

    /**
     *
     * @description 获取流程实例的流程日志
     * @param
     * @return
     * @author wangting
     * @date 2018/5/11 0011 15:04
     *
     */
    @RequestMapping(path = "/processLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo processLog(HttpServletRequest request, @RequestBody TaskQueryForm queryForm){

        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();

        WorkflowOperLogParam param = new WorkflowOperLogParam();
        param.setInstId(queryForm.getProcInstId());

        param.setCorpId(corpId);

        List<WorkflowOperLog> list = null;
        try {
            list = logService.list(param);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return ResultVo.failure(e.getMessage());
        }
        return ResultVo.success(list);
    }


    /**
     * 驳回到任务发起人，重新编辑提交
     *
     * @param processTaskDto
     * @return
     */
    @RequestMapping(value = "/backStartUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> backStartUser(@RequestBody ProcessTaskDto processTaskDto, HttpServletRequest request) {
        ResultVo<String> result = null;
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, Object> params = new LinkedCaseInsensitiveMap<>();
            for (String key : parameterMap.keySet()) {
                params.put(key, parameterMap.get(key)[0]);
            }
            activitiService.taskRollBack(processTaskDto, params,"1");
            //插入日志
            String comment = processTaskDto.getRemark();
            String instanceId = processTaskDto.getInstanceId();
            insertOpLog(instanceId, OperLogEnum.BACK.getType(),comment);
            result = ResultVo.success("驳回到发起人,成功");
        } catch (Exception e) {
            result = ResultVo.failure("驳回到发起人失败,"+e.getMessage());
        }
        return result;
    }

    /**
     * 驳回到上一步，重新编辑提交
     *
     * @param processTaskDto
     * @return
     */
    @RequestMapping(value = "/backToPrev", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> backToPrev(@RequestBody ProcessTaskDto processTaskDto, HttpServletRequest request) {
        ResultVo<String> result = null;
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, Object> params = new LinkedCaseInsensitiveMap<>();
            for (String key : parameterMap.keySet()) {
                params.put(key, parameterMap.get(key)[0]);
            }
            activitiService.taskRollBack(processTaskDto, params,null);
            //插入日志
            String comment = processTaskDto.getRemark();
            String instanceId = processTaskDto.getInstanceId();
            insertOpLog(instanceId, OperLogEnum.BACK.getType(),comment);
            result = ResultVo.success("驳回上一步,成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure("驳回到上一步失败,"+e.getMessage());
        }
        return result;
    }

    /**
     * 后加签
     *
     * @param processTaskDto
     * @return
     */
    @RequestMapping(value = "/insertAfterTask", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> insertAfterTask(@RequestBody ProcessTaskDto processTaskDto, HttpServletRequest request) {
        ResultVo<String> result = null;
        try {
            String assignee = getAssignee();
            // 定义map用于往工作流数据库中传值。
            identityService.setAuthenticatedUserId(assignee);
            activitiService.insertAfterTask(processTaskDto);
            result = ResultVo.success("加签完成");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure("加签失败");
        }
        return result;
    }

    @RequestMapping(value = "/testprocess", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> insertAfterTask(HttpServletRequest request) {
        ResultVo<String> result = null;
        String PROCESSID = "tyProcess_8";
        try {
//            // 5. Check if task is available
//            List<Task> tasks = taskService.createTaskQuery().processInstanceId("72501").list();
//
//
//            // 6. Save process diagram to a file
//            InputStream processDiagram = repositoryService.getProcessDiagram("tyProcess_8:1:65005");
//            try {
//                FileUtils.copyInputStreamToFile(processDiagram, new File("e://deployments/"+PROCESSID+".png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            repositoryService.createProcessDefinitionQuery().process
////            historyService.createHistoricProcessInstanceQuery().processInstanceId("72501").singleResult().getProcessDefinitionId()
//
//
//            // 7. Save resulting BPMN xml to a file
//            InputStream processBpmn = repositoryService.getResourceAsStream("65001", PROCESSID+".bpmn20.xml");
//            try {
//                FileUtils.copyInputStreamToFile(processBpmn,new File("e://deployments/"+PROCESSID+".bpmn20.xml"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition("tyProcess_11:1:122505");
            List<ActivityImpl> activitiList = def.getActivities();

            System.out.println("111");
            result = ResultVo.success("加签完成");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure("加签失败");
        }
        return result;
    }

     /**
      *
      * @description 终止流程
      * @param
      * @return
      * @author wangting
      * @date 2018/8/10 0010 14:52
      *
      */
    @RequestMapping(value = "/endProcess", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> endProcess(@RequestBody ProcessTaskDto taskInfoDto) {
        ResultVo<String> result = null;
        try {
            activitiService.endProcess(taskInfoDto);

            //插入日志
            String instanceId = taskInfoDto.getInstanceId();
            insertOpLog(instanceId,OperLogEnum.TERMINATION.getType(),null);
            result = ResultVo.success("终止完成");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure("终止失败");
        }
        return result;
    }

     /**
      *
      * @description 撤回
      * @param
      * @return
      * @author wangting
      * @date 2018/5/29 0029 15:58
      *
      */
    @RequestMapping(value = "/recall", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> recall(@RequestBody ProcessTaskDto taskInfoDto) {
        ResultVo<String> result = null;
        try {

            //获取当前用户
            String assignee = getAssignee();
            taskInfoDto.setStartUserId(assignee);
            activitiService.recall(taskInfoDto);
            //插入日志
            String instanceId = taskInfoDto.getInstanceId();
            insertOpLog(instanceId,OperLogEnum.RECALL.getType(),null);
            result = ResultVo.success("撤回完成");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure(e.getMessage());
        }
        return result;
    }

     /**
      *
      * @description 撤销任务
      * @param
      * @return
      * @author wangting
      * @date 2018/5/30 0030 17:06
      *
      */
    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo<String> revoke(@RequestBody ProcessTaskDto taskInfoDto) {
        ResultVo<String> result = null;
        try {
            //获取当前用户
            String assignee = getAssignee();
            taskInfoDto.setStartUserId(assignee);

            activitiService.revoke(taskInfoDto);
            //插入日志
            String instanceId = taskInfoDto.getInstanceId();
            insertOpLog(instanceId,OperLogEnum.REVOKE.getType(),null);
            result = ResultVo.success("撤销完成");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultVo.failure(e.getMessage());
        }
        return result;
    }

     /**
      *
      * @description 启动一个线程记录流程操作日志
      * @param
      * @return
      * @author wangting
      * @date 2018/5/30 0030 14:21
      *
      */
     @Async
    public void insertOpLog(String instanceId, String opType,String comment){
        LoginUser loginUser = UserHolder.getUser();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(
            () -> {
                String corpId = loginUser.getCorpId();
                String empId = loginUser.getEmpId();
                WorkflowOperLog operLog = new WorkflowOperLog();
                operLog.setCorpId(corpId);
                operLog.setInstId(Integer.parseInt(instanceId));
                operLog.setOperEmpId(empId);
                operLog.setOperEmpName(loginUser.getTrueName());
                operLog.setOperType(opType);
                operLog.setComment(comment);

                //根据实例id 获取流程
                HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(instanceId)
                        .singleResult();

                String defId = processInstance.getProcessDefinitionId();
                if(StringUtils.isNotEmpty(defId)){
                    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(defId).singleResult();
                    String remark = new StringBuilder(loginUser.getTrueName())
                            .append(OperLogEnum.getName(opType))
                            .append("流程")
                            .append(processDefinition.getName())
                            .toString();
                    operLog.setOperRemark(remark);
                }

                return logService.insert(operLog);
            }
        );
    }

    /**
     *
     * @description 我的任务数量（微信端使用）
     * @param queryBo
     *
     * @return
     * @author wangting
     * @date 2018/5/11 0011 15:04
     *
     */
    @RequestMapping(path = "/myProcessCount", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo myProcessCount(HttpServletRequest request, @RequestBody TaskQueryForm queryBo){

        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String empId = loginUser.getEmpId();
        String assignee = corpId + WorkFlowConst.ID_SPLIT + empId;
        queryBo.setAssignee(assignee);
        queryBo.setCorpId(corpId);

        Integer count = activitiService.getMyTodoProcessCount(queryBo);
        return ResultVo.success(count);
    }

    /**
     *
     * @description 我发起的任务
     * @param queryBo
     * status 1:待发，2.已发，3.待处理,4.已处理，
     * @return
     * @author wangting
     * @date 2018/5/11 0011 15:04
     *
     */
    @RequestMapping(path = "/wx/myProcess", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo myProcessWx(HttpServletRequest request, @RequestBody TaskQueryForm queryBo){

        String lastId = queryBo.getLastId();
        if (StringUtils.isEmpty(lastId)) {
            queryBo.setLastId(null);
        }
        Integer pageSize = queryBo.getPageSize();
        if (pageSize == null || pageSize < 10 || pageSize > 100) {
            pageSize = 10;
        }

        LoginUser loginUser = UserHolder.getUser();
        String corpId = loginUser.getCorpId();
        String empId = loginUser.getEmpId();
        String assignee = corpId + WorkFlowConst.ID_SPLIT + empId;
        List<ProcessInfoDto> resList = new ArrayList();
        queryBo.setAssignee(assignee);
        queryBo.setCorpId(corpId);
        String status = queryBo.getStatus();

        if("1".equals(status)){
            //我的待发送是自建表 里面有corpid 字段 故assignee 为empId
            queryBo.setAssignee(empId);
            resList = activitiService.getMyWaitSendProcess(lastId, pageSize, queryBo);
        }else if("2".equals(status)){
            resList = activitiService.getMySendProcess(lastId, pageSize, queryBo);
        }
        else if("3".equals(status)){
            resList = activitiService.getMyTodoProcess(lastId, pageSize, queryBo);
        }
        else if("4".equals(status)){
            resList = activitiService.getMyHandlerProcess(lastId, pageSize, queryBo);
        }

        return ResultVo.success(resList);
    }


}
