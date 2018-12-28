package com.lyarc.tp.corp.purchase.provider.form;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.jmapper.annotations.JGlobalMap;
import com.lyarc.tp.corp.common.bean.BaseQueryBean;

@JGlobalMap
public class ProviderPayApplyForm extends BaseQueryBean{
	
	private String paId;
	
	private String providerId;
	
	private BigDecimal amount;
	
	private String orderId;
	
	private String files;
	
	private String proposer;
	
	private String remark;
	
	private Integer status;
	
	private String wfInstId;
	
	private Date wfInstTime;
	
	private String wfInstRemark;
	
	private Integer creator;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeStart;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTimeEnd;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	
	private Date tmStamp;

	public String getPaId() {
		return paId;
	}

	public void setPaId(String paId) {
		this.paId = paId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
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

	public String getWfInstId() {
		return wfInstId;
	}

	public void setWfInstId(String wfInstId) {
		this.wfInstId = wfInstId;
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
		this.wfInstRemark = wfInstRemark;
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
	

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getTmStamp() {
		return tmStamp;
	}

	public void setTmStamp(Date tmStamp) {
		this.tmStamp = tmStamp;
	}
	
	
}
