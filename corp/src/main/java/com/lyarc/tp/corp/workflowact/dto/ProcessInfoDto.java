package com.lyarc.tp.corp.workflowact.dto;

import java.util.List;
import java.util.Map;

/**
 *
 * @description 流程查询dto
 * @param
 * @return
 * @author wangting
 * @date 2018/5/9 0009 17:45
 *
 */
public class ProcessInfoDto {
    private String defKey;

    private Integer formId;

    private String taskId;

    private List<Map<String,String>> tasks;

    private Integer draftId;
   /**
    * 流程定义id
    */
   private String defId;
   /**
    * 流程实例id
    */
   private String instanceId;

   /**
    * 业务流程状态 1=待发送 2=处理中 3=结束
    */
   private String status;

   //来源
   private String source;

   //创建时间
    private String processCreateTime;

    /**
     * 流程名称
     */
    private String processName;

    //发起时间
    private String processStartTime;

    //发起人id
    private String starterId;

    //发起人
    private String starter;

    //当前处理人
    private String dealName;

    //接收时间
    private String receiveTime;

    //处理时间
    private String dealTime;
    //流程变量列表
    List<ProcessVariableDto> varList;

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProcessCreateTime() {
        return processCreateTime;
    }

    public void setProcessCreateTime(String processCreateTime) {
        this.processCreateTime = processCreateTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(String processStartTime) {
        this.processStartTime = processStartTime;
    }

    public String getStarterId() {
        return starterId;
    }

    public void setStarterId(String starterId) {
        this.starterId = starterId;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getDraftId() {
        return draftId;
    }

    public void setDraftId(Integer draftId) {
        this.draftId = draftId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<ProcessVariableDto> getVarList() {
        return varList;
    }

    public void setVarList(List<ProcessVariableDto> varList) {
        this.varList = varList;
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public List<Map<String, String>> getTasks() {
        return tasks;
    }

    public void setTasks(List<Map<String, String>> tasks) {
        this.tasks = tasks;
    }
}
