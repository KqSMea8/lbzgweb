package com.lyarc.tp.corp.quality.Instrument.ExternalInspectionApply.Form;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 外检申请单
 */
public class ExternalInspectionApplyForm {
    private String externalApplyId;

    private String proposer;

    private String instrumentId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime applicationDate;

    private String department;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date lastTestingTime;

    private Integer status;

    private String wfInstId;

    private Date wfInstTime;
    
    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    public String getExternalApplyId() {
        return externalApplyId;
    }

    public void setExternalApplyId(String externalApplyId) {
        this.externalApplyId = externalApplyId == null ? null : externalApplyId.trim();
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getLastTestingTime() {
        return lastTestingTime;
    }

    public void setLastTestingTime(Date lastTestingTime) {
        this.lastTestingTime = lastTestingTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWfInstId() {
        return wfInstId;
    }

    public void setWfInstId(String wfInstId) {
        this.wfInstId = wfInstId == null ? null : wfInstId.trim();
    }

    public Date getWfInstTime() {
        return wfInstTime;
    }

    public void setWfInstTime(Date wfInstTime) {
        this.wfInstTime = wfInstTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Date tmstamp) {
        this.tmstamp = tmstamp;
    }
}