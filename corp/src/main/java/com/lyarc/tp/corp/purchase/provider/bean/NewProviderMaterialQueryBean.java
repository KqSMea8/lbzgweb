package com.lyarc.tp.corp.purchase.provider.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NewProviderMaterialQueryBean extends BaseQueryBean {
    private String npmId;

    private String providerId;

    private String materName;

    private String materId;

    private Integer reportStatus;

    private Integer wfStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeStart;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeEnd;

    public String getNpmId() {
        return npmId;
    }

    public void setNpmId(String npmId) {
        this.npmId = npmId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public LocalDate getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDate createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDate getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDate createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId;
    }

    public Integer getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(Integer wfStatus) {
        this.wfStatus = wfStatus;
    }
}