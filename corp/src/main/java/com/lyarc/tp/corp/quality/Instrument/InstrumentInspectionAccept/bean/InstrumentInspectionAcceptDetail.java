package com.lyarc.tp.corp.quality.Instrument.InstrumentInspectionAccept.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InstrumentInspectionAcceptDetail {
    private String inspectionAcceptId;

    private String instrumentId;

    private String counterName;

    private String model;

    private String orgin;

    private String internalId;

    private String installationSite;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appraisalDate;

    private String retestingPeriod;

    private BigDecimal testCost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tmstamp;

    public String getInspectionAcceptId() {
        return inspectionAcceptId;
    }

    public void setInspectionAcceptId(String inspectionAcceptId) {
        this.inspectionAcceptId = inspectionAcceptId == null ? null : inspectionAcceptId.trim();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName == null ? null : counterName.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getOrgin() {
        return orgin;
    }

    public void setOrgin(String orgin) {
        this.orgin = orgin == null ? null : orgin.trim();
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId == null ? null : internalId.trim();
    }

    public String getInstallationSite() {
        return installationSite;
    }

    public void setInstallationSite(String installationSite) {
        this.installationSite = installationSite == null ? null : installationSite.trim();
    }

    public Date getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(Date appraisalDate) {
        this.appraisalDate = appraisalDate;
    }

    public String getRetestingPeriod() {
        return retestingPeriod;
    }

    public void setRetestingPeriod(String retestingPeriod) {
        this.retestingPeriod = retestingPeriod;
    }

    public BigDecimal getTestCost() {
        return testCost;
    }

    public void setTestCost(BigDecimal testCost) {
        this.testCost = testCost;
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