package com.lyarc.tp.corp.pack.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PackBean {
	
	private String packId;
	private Integer department;
	private String operator;
	private String remark;
	private Integer status;
	private Integer creator;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	private Date timestamp;
	private List<PackDetailBean> detailList;
	
	
	public List<PackDetailBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<PackDetailBean> detailList) {
		this.detailList = detailList;
	}
	public String getPackId() {
		return packId;
	}
	public void setPackId(String packId) {
		this.packId = packId;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
