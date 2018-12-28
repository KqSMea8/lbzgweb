package com.lyarc.tp.corp.purchase.order.bean;

import java.util.Date;
import java.util.List;

import com.lyarc.tp.corp.purchase.order.dto.InStockApplyDetailDto;

public class InStockApply {
	private String inStockApplyId;

	private String purchaseOrderId;

	private Integer department;

	private String proposer;

	private String remark;

	private Integer creator;

	private Date createTime;

	private Date updateTime;

	private Date tmstamp;
	
	private String storageId;

	private Integer status;

	private String stockBillId;

	private String auditRemark;
	
	private String buyer;
	
	private List<InStockApplyDetailDto> detailList;
	
	
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public List<InStockApplyDetailDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InStockApplyDetailDto> detailList) {
		this.detailList = detailList;
	}

	public String getInStockApplyId() {
		return inStockApplyId;
	}

	public void setInStockApplyId(String inStockApplyId) {
		this.inStockApplyId = inStockApplyId == null ? null : inStockApplyId.trim();
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId == null ? null : purchaseOrderId.trim();
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
		this.proposer = proposer == null ? null : proposer.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
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

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStockBillId() {
		return stockBillId;
	}

	public void setStockBillId(String stockBillId) {
		this.stockBillId = stockBillId;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	
}