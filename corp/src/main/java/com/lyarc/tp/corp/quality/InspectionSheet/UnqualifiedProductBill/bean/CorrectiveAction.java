package com.lyarc.tp.corp.quality.InspectionSheet.UnqualifiedProductBill.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CorrectiveAction {
    private String remedialActionId;

    private String unqualifiedId;

    private String inspectionId;

    private String materId;

    private String materName;

    private String spec;

    private String drawing;

    private String casting;

    private String providerId;

    private BigDecimal quantity;

    private BigDecimal checkQuantity;

    private String unit;

    private String inspector;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date billDate;

    private String operator;

    private String nonconformingDescription;

    private String purchaseOrderId;

    private Integer status;

    private String wfInstId;

    private Date wfInstTime;

    private String wfInstRemark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Date updateTime;

    private Date tmstamp;

    List<CorrectiveAction> detailList;
    
    public List<CorrectiveAction> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CorrectiveAction> detailList) {
		this.detailList = detailList;
	}

	public String getRemedialActionId() {
        return remedialActionId;
    }

    public void setRemedialActionId(String remedialActionId) {
        this.remedialActionId = remedialActionId == null ? null : remedialActionId.trim();
    }

    public String getUnqualifiedId() {
        return unqualifiedId;
    }

    public void setUnqualifiedId(String unqualifiedId) {
        this.unqualifiedId = unqualifiedId == null ? null : unqualifiedId.trim();
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId == null ? null : inspectionId.trim();
    }

    public String getMaterId() {
        return materId;
    }

    public void setMaterId(String materId) {
        this.materId = materId == null ? null : materId.trim();
    }

    public String getMaterName() {
        return materName;
    }

    public void setMaterName(String materName) {
        this.materName = materName == null ? null : materName.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing == null ? null : drawing.trim();
    }

    public String getCasting() {
        return casting;
    }

    public void setCasting(String casting) {
        this.casting = casting == null ? null : casting.trim();
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(BigDecimal checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector == null ? null : inspector.trim();
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getNonconformingDescription() {
        return nonconformingDescription;
    }

    public void setNonconformingDescription(String nonconformingDescription) {
        this.nonconformingDescription = nonconformingDescription == null ? null : nonconformingDescription.trim();
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId == null ? null : purchaseOrderId.trim();
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

    public String getWfInstRemark() {
        return wfInstRemark;
    }

    public void setWfInstRemark(String wfInstRemark) {
        this.wfInstRemark = wfInstRemark == null ? null : wfInstRemark.trim();
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