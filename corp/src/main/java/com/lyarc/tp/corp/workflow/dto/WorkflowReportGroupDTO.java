package com.lyarc.tp.corp.workflow.dto;

import java.util.List;

public class WorkflowReportGroupDTO {

    private Integer formId;
    
    private String title;
    
    private List<WorkflowReportDTO> reports;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<WorkflowReportDTO> getReports() {
        return reports;
    }

    public void setReports(List<WorkflowReportDTO> reports) {
        this.reports = reports;
    }
    
    
}
