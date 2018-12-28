package com.lyarc.tp.corp.aftersales.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

public class ComplaintDisposeQueryBean extends BaseQueryBean {
	private String contractId;
	private String customerId;
	private String customerName;
	private String materName;
	private String drawing;
	private String casting;
	private String materId;
	private String status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date CreateTimeStart;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date CreateTimeEnd;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMaterName() {
		return materName;
	}

	public void setMaterName(String materName) {
		this.materName = materName;
	}

	public String getDrawing() {
		return drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}

	public String getCasting() {
		return casting;
	}

	public void setCasting(String casting) {
		this.casting = casting;
	}

	public String getMaterId() {
		return materId;
	}

	public void setMaterId(String materId) {
		this.materId = materId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTimeStart() {
		return CreateTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		CreateTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return CreateTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		CreateTimeEnd = createTimeEnd;
	}

}
