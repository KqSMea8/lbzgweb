package com.lyarc.tp.corp.production.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap(excluded = { "detailList" })

public class OutStockApply {
	private String outStockApplyId;

	private String storageId;

	private Integer department;

	private String proposer;

	private String remark;

	private Integer status;

	private String stockBillId;

	private String auditRemark;

	private BigDecimal amount;

	private Integer creator;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	private Date updateTime;

	private Date tmstamp;

	private List<OutStockApplyDetail> detailList;

	public String getOutStockApplyId() {
		return outStockApplyId;
	}

	public void setOutStockApplyId(String outStockApplyId) {
		this.outStockApplyId = outStockApplyId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public List<OutStockApplyDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OutStockApplyDetail> detailList) {
		this.detailList = detailList;
	}

}
