package com.lyarc.tp.corp.contract.inquire.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Inquire {
	private String inquireId;

	private String customerId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inDate;

	private Integer isInquire;

	private Integer hasProcedure;

	private Integer isForeign;

	private Integer isCraftDesign;

	private String sales;

	private Integer creator;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tmstamp;

	private String remark;

	private String designer;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate completeTime;

	private Integer status;

	private String files;

	public String getInquireId() {
		return inquireId;
	}

	public void setInquireId(String inquireId) {
		this.inquireId = inquireId == null ? null : inquireId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public Integer getIsInquire() {
		return isInquire;
	}

	public void setIsInquire(Integer isInquire) {
		this.isInquire = isInquire;
	}

	public Integer getHasProcedure() {
		return hasProcedure;
	}

	public void setHasProcedure(Integer hasProcedure) {
		this.hasProcedure = hasProcedure;
	}

	public Integer getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(Integer isForeign) {
		this.isForeign = isForeign;
	}

	public Integer getIsCraftDesign() {
		return isCraftDesign;
	}

	public void setIsCraftDesign(Integer isCraftDesign) {
		this.isCraftDesign = isCraftDesign;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales == null ? null : sales.trim();
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public LocalDate getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(LocalDate completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

}