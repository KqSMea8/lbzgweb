package com.lyarc.tp.corp.inspection.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

@JGlobalMap(excluded = { "detailList", "endTime" })

public class InspectionForm extends BaseQueryBean {
	// 检验单的详情
	private List<InspectionDetailForm> detailList;

	// @NotEmpty(message = "单号不能为空")
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

	/** 更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/** 时间戳 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tmstamp;

	// 结束时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	public List<InspectionDetailForm> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<InspectionDetailForm> detailList) {
		this.detailList = detailList;
	}

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

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "InspectionForm [detailList=" + detailList + ", inspectionApplyId=" + inspectionApplyId
				+ ", purchaseOrderId=" + purchaseOrderId + ", department=" + department + ", proposer=" + proposer
				+ ", applyDate=" + applyDate + ", operator=" + operator + ", status=" + status
				+ ", qualityInspectionId=" + qualityInspectionId + ", creator=" + creator + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", tmstamp=" + tmstamp + ", endTime=" + endTime + "]";
	}

}
