package com.lyarc.tp.corp.contract.contract.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JGlobalMap(excluded ={"detailList"} )
public class ContractProjectForm {
    private String contractId;

    private String projectType;

    private String designer;

    private String designerName;

    private BigDecimal quantity;

    private Integer status;

    private String wfInstId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime wfInstTime;

    private String wfInstRemark;

    private Integer revisionStatus;

    private String revisionWfInstId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime revisionWfInstTime;

    private String revisionWfInstRemark;

    private Integer creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tmstamp;

    private List<ContractProjectDetailForm> detailList;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer == null ? null : designer.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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


    public String getWfInstRemark() {
        return wfInstRemark;
    }

    public void setWfInstRemark(String wfInstRemark) {
        this.wfInstRemark = wfInstRemark == null ? null : wfInstRemark.trim();
    }

    public Integer getRevisionStatus() {
        return revisionStatus;
    }

    public void setRevisionStatus(Integer revisionStatus) {
        this.revisionStatus = revisionStatus;
    }

    public String getRevisionWfInstId() {
        return revisionWfInstId;
    }

    public void setRevisionWfInstId(String revisionWfInstId) {
        this.revisionWfInstId = revisionWfInstId == null ? null : revisionWfInstId.trim();
    }

    public String getRevisionWfInstRemark() {
        return revisionWfInstRemark;
    }

    public void setRevisionWfInstRemark(String revisionWfInstRemark) {
        this.revisionWfInstRemark = revisionWfInstRemark == null ? null : revisionWfInstRemark.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public LocalDateTime getWfInstTime() {
        return wfInstTime;
    }

    public void setWfInstTime(LocalDateTime wfInstTime) {
        this.wfInstTime = wfInstTime;
    }

    public LocalDateTime getRevisionWfInstTime() {
        return revisionWfInstTime;
    }

    public void setRevisionWfInstTime(LocalDateTime revisionWfInstTime) {
        this.revisionWfInstTime = revisionWfInstTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(LocalDateTime tmstamp) {
        this.tmstamp = tmstamp;
    }

    public List<ContractProjectDetailForm> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ContractProjectDetailForm> detailList) {
        this.detailList = detailList;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }
}