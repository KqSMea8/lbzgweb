package com.lyarc.tp.corp.inspection.form;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class InspectionQueryForm extends BaseQueryBean {

	private String inspectionApplyId;
	private Integer status;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createTimeStart;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createTimeEnd;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDate getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(LocalDate createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public LocalDate getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(LocalDate createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getInspectionApplyId() {
		return inspectionApplyId;
	}

	public void setInspectionApplyId(String inspectionApplyId) {
		this.inspectionApplyId = inspectionApplyId;
	}

	@Override
	public String toString() {
		return "InspectionQueryForm [inspectionApplyId=" + inspectionApplyId + ", status=" + status
				+ ", createTimeStart=" + createTimeStart + ", createTimeEnd=" + createTimeEnd + "]";
	}

}
