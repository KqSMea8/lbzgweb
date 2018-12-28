package com.lyarc.tp.corp.production.bean;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SelfMaterMakeApplyBean {
	private String selfMaterMakeApplyId;

	private Integer department;

	private String proposer;

	private String remark;

	private Integer status;

	private String wfInstId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime wfInstTime;

	private String wfInstRemark;

	private Integer creator;

	private List<SelfMaterMakeApplyDetail> detailList;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tmstamp;

	public String getSelfMaterMakeApplyId() {
		return selfMaterMakeApplyId;
	}

	public void setSelfMaterMakeApplyId(String selfMaterMakeApplyId) {
		this.selfMaterMakeApplyId = selfMaterMakeApplyId;
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

	public String getWfInstId() {
		return wfInstId;
	}

	public void setWfInstId(String wfInstId) {
		this.wfInstId = wfInstId;
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

	public List<SelfMaterMakeApplyDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SelfMaterMakeApplyDetail> detailList) {
		this.detailList = detailList;
	}

	public LocalDateTime getWfInstTime() {
		return wfInstTime;
	}

	public void setWfInstTime(LocalDateTime wfInstTime) {
		this.wfInstTime = wfInstTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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

}