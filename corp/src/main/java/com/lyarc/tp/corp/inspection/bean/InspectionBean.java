package com.lyarc.tp.corp.inspection.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * 检验单对应字段
 * 
 * @author shiyong
 *
 */
@JGlobalMap
public class InspectionBean {

	private String inspectionApplyId;

	private String purchaseOrderId;

	private Integer department;

	private String proposer;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate applyDate;

	private String operator;

	private Integer status;

	private String qualityInspectionId;

	private Integer creator;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tmstamp;

	public String getInspectionApplyId() {
		return inspectionApplyId;
	}

	public void setInspectionApplyId(String inspectionApplyId) {
		this.inspectionApplyId = inspectionApplyId;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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

	public LocalDate getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDate applyDate) {
		this.applyDate = applyDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getQualityInspectionId() {
		return qualityInspectionId;
	}

	public void setQualityInspectionId(String qualityInspectionId) {
		this.qualityInspectionId = qualityInspectionId;
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

	@Override
	public String toString() {
		return "InspectionBean [inspectionApplyId=" + inspectionApplyId + ", purchaseOrderId=" + purchaseOrderId
				+ ", department=" + department + ", proposer=" + proposer + ", applyDate=" + applyDate + ", operator="
				+ operator + ", status=" + status + ", qualityInspectionId=" + qualityInspectionId + ", creator="
				+ creator + ", createTime=" + createTime + ", updateTime=" + updateTime + ", tmstamp=" + tmstamp + "]";
	}

}
