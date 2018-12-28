package com.lyarc.tp.corp.warehouse.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap(excluded = { "detailList" })

public class StockBillForm {

	private String stockBillId;

	private List<StockBillDetailForm> detailList;

	private String storageId;

	private String operId;

	private Integer department;

	private String proposer;

	private String operator;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate;

	private String receiver;

	private BigDecimal amount;

	private String remark;

	private Integer status;

	private String auditRemark;

	private Integer creator;

	private Integer isWeight;

	private String inStockApplyId;

	private String purchaseOrderId;

	private String outStockApplyId;

	private String purchaseBackOrderId;
	
	private Integer operType;
	
	private String operSource;

	private String relatedStorageId;
	
	private String shipper;
	
	private String inspector;
	
	private String auditor;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate auditTime;
	
	public LocalDate getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(LocalDate auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getRelatedStorageId() {
		return relatedStorageId;
	}

	public void setRelatedStorageId(String relatedStorageId) {
		this.relatedStorageId = relatedStorageId;
	}

	public Integer getIsWeight() {
		return isWeight;
	}

	public void setIsWeight(Integer isWeight) {
		this.isWeight = isWeight;
	}

	public String getInStockApplyId() {
		return inStockApplyId;
	}

	public void setInStockApplyId(String inStockApplyId) {
		this.inStockApplyId = inStockApplyId;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getOutStockApplyId() {
		return outStockApplyId;
	}

	public void setOutStockApplyId(String outStockApplyId) {
		this.outStockApplyId = outStockApplyId;
	}

	public String getPurchaseBackOrderId() {
		return purchaseBackOrderId;
	}

	public void setPurchaseBackOrderId(String purchaseBackOrderId) {
		this.purchaseBackOrderId = purchaseBackOrderId;
	}

	public String getStockBillId() {
		return stockBillId;
	}

	public void setStockBillId(String stockBillId) {
		this.stockBillId = stockBillId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public List<StockBillDetailForm> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StockBillDetailForm> detailList) {
		this.detailList = detailList;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	
	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	
	public String getOperSource() {
		return operSource;
	}

	public void setOperSource(String operSource) {
		this.operSource = operSource;
	}


}
