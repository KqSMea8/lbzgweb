package com.lyarc.tp.corp.warehouse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class StockBillDTO {

	private String stockBillId;
	private String storageId;

	// @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	// private LocalDate billDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate;

	private Integer operType;
	private Integer operSource;
	private String operId;
	private String relatedStorageId;
	private Integer isWeight;	
	private String inStockApplyId;	
	private String purchaseOrderId;	
	private String outStockApplyId;	
	private String purchaseBackOrderId;
	private Integer department;
	private String proposer;
	private String operator;
	private String receiver;
	private String shipper;
	private String inspector;
	private BigDecimal amount;
	private String remark;
	private Integer status;
	private String auditor;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate auditTime;

	private String auditRemark;
	private Integer creator;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/** 时间戳 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tmstamp;

	private List<StockBillDetailDTO> detailList;// 子菜单的详情页面

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

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Integer getOperSource() {
		return operSource;
	}

	public void setOperSource(Integer operSource) {
		this.operSource = operSource;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
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

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
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

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
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

	public List<StockBillDetailDTO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StockBillDetailDTO> detailList) {
		this.detailList = detailList;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public LocalDate getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(LocalDate auditTime) {
		this.auditTime = auditTime;
	}

}
