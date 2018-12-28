package com.lyarc.tp.corp.purchase.provider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.purchase.provider.form.NewProviderMaterialDetailForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NewProviderMaterialDto {
    private String npmId;

    private String providerId;

    private String buyer;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate submitTime;

    private String submiter;

    private String files;

    private String reporter;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportSubmitTime;

    private Integer reportStatus;

    private Integer wfStatus;

    private String wfInstId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime wfInstTime;

    private String wfInstRemark;

    private Integer creator;

    private List<NewProviderMaterialDetailDto> detailList;

    private String linkMan;

    private String linkPhone;

    private String providerName;

    private String address;

    public String getNpmId() {
        return npmId;
    }

    public void setNpmId(String npmId) {
        this.npmId = npmId == null ? null : npmId.trim();
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter == null ? null : submiter.trim();
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(Integer wfStatus) {
        this.wfStatus = wfStatus;
    }

    public String getWfInstId() {
        return wfInstId;
    }

    public void setWfInstId(String wfInstId) {
        this.wfInstId = wfInstId == null ? null : wfInstId.trim();
    }

    public String getWfInstRemark() {
        return wfInstRemark;
    }

    public void setWfInstRemark(String wfInstRemark) {
        this.wfInstRemark = wfInstRemark == null ? null : wfInstRemark.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }


    public LocalDate getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDate submitTime) {
        this.submitTime = submitTime;
    }

    public LocalDate getReportSubmitTime() {
        return reportSubmitTime;
    }

    public void setReportSubmitTime(LocalDate reportSubmitTime) {
        this.reportSubmitTime = reportSubmitTime;
    }

    public LocalDateTime getWfInstTime() {
        return wfInstTime;
    }

    public void setWfInstTime(LocalDateTime wfInstTime) {
        this.wfInstTime = wfInstTime;
    }

    public List<NewProviderMaterialDetailDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<NewProviderMaterialDetailDto> detailList) {
        this.detailList = detailList;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}